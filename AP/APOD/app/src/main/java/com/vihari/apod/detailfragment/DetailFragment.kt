package com.vihari.apod.detailfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vihari.apod.R
import com.vihari.apod.databinding.DetailFragment

class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = DetailFragment.inflate(inflater)
        binding.setLifecycleOwner(this)
        val apodProperty = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(apodProperty, application)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
        return binding.root
    }



}
