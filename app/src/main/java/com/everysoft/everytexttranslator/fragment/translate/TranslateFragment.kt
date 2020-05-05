package com.everysoft.everytexttranslator.fragment.translate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.everysoft.everytexttranslator.R
import com.everysoft.everytexttranslator.databinding.TranslateFragmentBinding


class TranslateFragment : Fragment() {

    companion object {
        fun newInstance() =
            TranslateFragment()
    }

    private lateinit var viewModel: TranslateViewModel
    private lateinit var binding: TranslateFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO: Init binding
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.translate_fragment,
            container,
            false
        )

        //Getting viewModel
        viewModel = ViewModelProvider(this).get(TranslateViewModel::class.java)
        //Setting observer
        viewModel.translateInputText.observe(viewLifecycleOwner, Observer { newInput ->
            binding.translateInputText.setText(newInput)

        })
        viewModel.translateOutputText.observe(viewLifecycleOwner, Observer {newOutput ->
            binding.translateOutputText.text = newOutput
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
