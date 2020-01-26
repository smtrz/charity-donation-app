package com.tahir.omiseassignment;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.tahir.omiseassignment.Configurations.App;
import com.tahir.omiseassignment.Enums.Codes;
import com.tahir.omiseassignment.Models.DonationResponse;
import com.tahir.omiseassignment.Repository.AppRepository;
import com.tahir.omiseassignment.ViewModels.DonationActivityViewModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CountDownLatch;


@RunWith(AndroidJUnit4.class)

public class PaymentTest {
    @Rule
    public TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @InjectMocks
    AppRepository dbrep;
    //  @Mock
    Observer<DonationResponse> data_observer;
    Observer<DonationResponse> data_observer2;
    DonationActivityViewModel viewModel;
    @Mock
    Context context;
    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;
    //@Mock
    DonationResponse dr;

    @Before
    public void setUp() throws Exception {


        MockitoAnnotations.initMocks(this);
        //lifecycleOwner
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new DonationActivityViewModel(App.app);


    }


    // card with sufficient balance
    // Successful transaction.
    @Test
    public void testCardsufficientBalance() throws InterruptedException {

        final CountDownLatch signal = new CountDownLatch(1);

        viewModel.postDonation("Test User", "4242424242424242", "2000");


        data_observer = new Observer<DonationResponse>() {
            @Override
            public void onChanged(DonationResponse donationResponse) {
                signal.countDown();
                dr = donationResponse;
                System.out.println("this is === " + donationResponse.getStatus());


            }
        };
        viewModel.getDonationResponse().observeForever(data_observer);
        signal.await();
        System.out.println(" this is obs === " + viewModel.getDonationResponse().hasActiveObservers());
        Assert.assertEquals(dr.getStatus(), Codes.successful.toString());

        removeObservers();

        //  signal.await();
        // MutableLiveData<DonationResponse> allCategories = viewModel.getDonationResponse();
        //  viewModel.getDonationResponse().observeForever(data_observer);

        //     data_observer.onChanged(new DonationResponse());

        // viewModel.getDonationResponse().observeForever(data_observer);


   /*     viewModel.getDonationResponse().observe(lifecycleOwner, new Observer<DonationResponse>() {
            @Override
            public void onChanged(DonationResponse donationResponse) {
                signal.countDown();
            }
        });*/
        //   signal.await();
        // data_observer.onChanged(dr);
        //viewModel.getDonationResponse().observe(lifecycleOwner,data_observer);


        //viewModel.getDonationResponse().observe();
      /*  viewModel.getDonationResponse().observe(lifecycleOwner, new Observer<DonationResponse>() {
            @Override
            public void onChanged(DonationResponse donationResponse) {
                signal.countDown();// notify the count down latch

            }
        });*/


        //   viewModel.getDonationResponse().observeForever(data_observer);

        //  await().forever().atMost(20, TimeUnit.SECONDS).until(() -> == null);





      /*  try {
            tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //    Assert.assertEquals(viewModel.getDonationResponse().get(), Codes.successful.toString());
       /* viewModel.getDonationResponse().observe(lifecycleOwner, new Observer<DonationResponse>() {
            @Override
            public void onChanged(DonationResponse donationResponse) {

            }
        });*/
        //  data_observer.onChanged(new DonationResponse());
        // Assert.assertEquals(viewModel.getDonationResponse().getValue().getStatus(), Codes.successful.toString());

        //   verify(observer).onChanged(Collections.singletonList(todo));


        //data_observer.onChanged(Assert.assertEquals(new DonationResponse().getStatus(),Codes.successful.toString()));

        //  Assert.assertEquals(donationResponse.getStatus(), Codes.successful.toString());

       /* viewModel.getDonationResponse().observe(lifecycleOwner,data_observer);
         data_observer.onChanged(dr);*/


        //  boolean istrue = viewModel.getDonationResponse().getValue().getStatus().equals(Codes.successful.toString());
        /*Awaitility.await().forever().until(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return null;
            }
        });*/
        //  userRepository::size, size -> size == 1
        //await().forever().until(viewModel.getDonationResponse()::);


        //await().until(() -> viewModel.getDonationResponse() == null);
        /*await().forever().timeout(25, TimeUnit.SECONDS).untilAsserted(new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                Assert.assertEquals(viewModel.getDonationResponse().getValue().getStatus(), Codes.successful.toString());

                //Assertions.assertThat(personRepository.size()).isEqualTo(6);
            }
        });*/

        //  Assert.assertEquals(viewModel.getDonationResponse().getValue().getStatus(), Codes.successful.toString());

       /* Assert.assertEquals(viewModel.getDonationResponse().getValue().getStatus(), Codes.successful.toString());
        await().forever().until(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return viewModel.getDonationResponse().getValue().getStatus().equals(Codes.successful.toString());
            }
        });*/

        //    A.forever().until(conditionIsSatisfied());

/*
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      if (viewModel.getDonationResponse() == null) {

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/


        //  Assert.assertEquals(viewModel.getDonationResponse().getValue().getStatus(), Codes.successful.toString());


    }

    /* private Callable<Boolean> newUserIsAdded() {
         return () -> viewModel.getDonationResponse() != null; // The condition that must be fulfilled
     }*/
    // card with in-sufficient balance
// failed transaction
    @Test
    public void testCardInsufficientBalance() throws InterruptedException {


        final CountDownLatch signal = new CountDownLatch(1);

        viewModel.postDonation("Test User", "4111 1111 1114 0011", "2000");


        data_observer = new Observer<DonationResponse>() {
            @Override
            public void onChanged(DonationResponse donationResponse) {
                if (donationResponse != null) {

                    signal.countDown();
                    dr = donationResponse;
                    System.out.println("this is 2 === " + donationResponse.getStatus());
                }


            }
        };
        viewModel.getDonationResponse().observeForever(data_observer);
        signal.await();


        Assert.assertEquals(dr.getStatus(), Codes.failed.toString());
        removeObservers();


    }


    @After
    public void tearDown() throws Exception {

        viewModel = null;
        dbrep = null;
    }

    public void removeObservers() {
        viewModel.getDonationResponse().removeObserver(data_observer);
        viewModel.getDonationResponse().removeObservers(lifecycleOwner);
        viewModel.getDonationResponse().postValue(null);

    }
}
