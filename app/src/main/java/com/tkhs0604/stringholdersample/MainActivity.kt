package com.tkhs0604.stringholdersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tkhs0604.stringholdersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.textForPlain.observe(this) {
            binding.textViewForPlain.text = it.getString(this)
        }
        viewModel.textForResource.observe(this) {
            binding.textViewForResource.text = it.getString(this)
        }
    }
}