package com.tahir.omiseassignment.Modules

import com.google.gson.Gson
import com.tahir.omiseassignment.AppConstant
import com.tahir.omiseassignment.Interfaces.EndpointsInterface

import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    internal val httpLoggingInterceptor: HttpLoggingInterceptor
        @Provides
        @Singleton

        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }


    @Provides
    @Singleton

    internal fun getApiInterface(retroFit: Retrofit): EndpointsInterface {
        return retroFit.create(EndpointsInterface::class.java)
    }

    @Named("charity")
    @Provides
    @Singleton

    internal fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL_Charity)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient_CharityList(httpLoggingInterceptor))
            .build()
    }


    internal fun getOkHttpClient_CharityList(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .certificatePinner(
                getCertificatePinner(
                    AppConstant.CHARITY_HostName,
                    AppConstant.charity_pins
                )
            )

            .build()
    }

    internal fun getOkHttpClient_Omise(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .certificatePinner(
                getCertificatePinner(
                    AppConstant.OMISE_HostName,
                    AppConstant.omise_pins
                )
            )
            .build()
    }

    @Provides
    @Singleton

    internal fun getGson(): Gson {
        return Gson()
    }

    @Named("omise")
    @Provides
    @Singleton

    internal fun getRetrofitOmise(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL_OMISE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient_Omise(httpLoggingInterceptor))
            .build()
    }

    @Named("omise_token")
    @Provides
    @Singleton

    internal fun getRetrofitOmise_token(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL_OMISE_TOKEN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient_Omise(httpLoggingInterceptor))
            .build()
    }

    fun getCertificatePinner(BaseUrl: String, pins: Array<String>): CertificatePinner {

        var certificatePinner = CertificatePinner.Builder()
            .add(
                BaseUrl,
                pins.get(0)
            )

            .add(
                BaseUrl,
                pins.get(1)
            )

            .add(
                BaseUrl,
                pins.get(2)
            )

            .build()

        return certificatePinner

    }
}
