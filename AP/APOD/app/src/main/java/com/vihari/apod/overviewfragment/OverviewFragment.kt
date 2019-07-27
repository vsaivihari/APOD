package com.vihari.apod.overviewfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController



import com.vihari.apod.databinding.OverViewFragment
import com.vihari.apod.overviewfragment.OverviewFragmentDirections.actionOverviewFragmentToDetailFragment as overviewFragmentDirectionsActionOverviewFragmentToDetailFragment

class OverviewFragment : Fragment() {

//    companion object {
//        fun newInstance() = OverviewFragment()
//    }

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }
    //private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = OverViewFragment.inflate(inflater)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.apodList.adapter = OverViewAdapter(OverViewAdapter.OnClickListener {viewModel.displayPropertyDetails(it)})
        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })
        // Observer for the network error.
        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
        return binding.root

    }
    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}
