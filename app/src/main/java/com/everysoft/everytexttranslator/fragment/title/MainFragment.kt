package com.everysoft.everytexttranslator.fragment.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.everysoft.everytexttranslator.R
import com.everysoft.everytexttranslator.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    companion object {
        fun newInstance() =
            MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false)
        binding.translateButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToTranslateFragment())

        }
        return binding.root
    }



}
