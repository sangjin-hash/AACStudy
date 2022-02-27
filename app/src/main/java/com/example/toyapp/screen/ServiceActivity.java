package com.example.toyapp.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.toyapp.R;
import com.example.toyapp.adapter.DataAdapter;
import com.example.toyapp.databinding.ActivityServiceBinding;
import com.example.toyapp.viewModel.crud.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ServiceActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }
}