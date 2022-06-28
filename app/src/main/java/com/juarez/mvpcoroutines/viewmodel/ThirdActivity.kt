package com.juarez.mvpcoroutines.viewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.juarez.mvpcoroutines.R

class ThirdActivity : AppCompatActivity() {
    private val viewModel: PresenterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        viewModel.save()
        viewModel.getAge()
    }
}