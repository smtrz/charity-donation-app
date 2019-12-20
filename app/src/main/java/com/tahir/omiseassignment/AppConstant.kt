package com.tahir.omiseassignment

import java.lang.System.loadLibrary


object AppConstant {

    init {
        loadLibrary("keys")
    }

    external fun getBaseUrlOmise(): String
    external fun getBaseUrlCharity(): String
    external fun getSecretKey(): String
    external fun getPublicKey(): String
    external fun getOmiseHostName(): String
    external fun getCharityHostName(): String
    external fun getCharityKey1(): String
    external fun getCharityKey2(): String
    external fun getCharityKey3(): String
    external fun getOmiseKey1(): String
    external fun getOmiseKey2(): String
    external fun getOmiseKey3(): String

    external fun getBaseUrlOmiseToken(): String

    val OMISE_HostName = getOmiseHostName()
    val CHARITY_HostName = getCharityHostName()
    val OMISE_PKEY = getPublicKey()
    val BASE_URL_Charity = getBaseUrlCharity()
    val BASE_URL_OMISE = getBaseUrlOmise()
    val BASE_URL_OMISE_TOKEN = getBaseUrlOmiseToken()

    val OMISE_SKEY = getSecretKey()
    val charity_pins = arrayOf(
        getCharityKey1(),
        getCharityKey2(),
        getCharityKey3()
    )
    val omise_pins = arrayOf(
        getOmiseKey1(),
        getOmiseKey2(),
        getOmiseKey3()
    )
}