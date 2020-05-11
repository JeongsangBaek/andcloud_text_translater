package com.everysoft.everytexttranslator.fragment.translate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
        binding.translateButton.setOnClickListener {
            val inputText = binding.translateInputText.text.toString()
            viewModel.doTranslate(
                binding.fromLanguageSpinner.selectedItem.toString(),
                binding.toLanguageSpinner.selectedItem.toString(),
                inputText)
        }

        //TODO: Create dropdown list from API
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.translate_language_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.fromLanguageSpinner.adapter = adapter
            binding.toLanguageSpinner.adapter = adapter

            //TODO. set selection based on saved state
            val fromPos = adapter.getPosition("EN")
            binding.fromLanguageSpinner.setSelection(fromPos);
            val toPos = adapter.getPosition("KO")
            binding.toLanguageSpinner.setSelection(toPos)
        }

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
