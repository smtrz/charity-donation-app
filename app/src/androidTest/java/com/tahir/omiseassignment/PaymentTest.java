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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(AndroidJUnit4.class)

public class PaymentTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @InjectMocks
    AppRepository dbrep;
    @Mock
    Observer<DonationResponse> data_observer;
    DonationActivityViewModel viewModel;
    @Mock
    Context context;
    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;

    @Before
    public void setUp() throws Exception {


        MockitoAnnotations.initMocks(this);
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new DonationActivityViewModel(App.app);


        viewModel.getDonationResponse().observeForever(data_observer);
    }


    // card with sufficient balance
    // Successful transaction.
    @Test
    public void testCardsufficientBalance() {
        viewModel.postDonation("Test User", "4242424242424242", "2000");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Assert.assertEquals(viewModel.getDonationResponse().getValue().getStatus(), Codes.successful.toString());


    }

    // card with in-sufficient balance
// failed transaction
    @Test
    public void testCardInsufficientBalance() {
        viewModel.postDonation("Test User", "4111 1111 1114 0011", "2000");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Assert.assertEquals(viewModel.getDonationResponse().getValue().getStatus(), Codes.failed.toString());


    }


    @After
    public void tearDown() throws Exception {

        viewModel = null;
        dbrep = null;
    }
}
