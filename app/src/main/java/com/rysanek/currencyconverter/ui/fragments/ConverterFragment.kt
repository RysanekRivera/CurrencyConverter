package com.rysanek.currencyconverter.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rysanek.currencyconverter.databinding.FragmentConverterBinding
import com.rysanek.currencyconverter.viewmodels.ExchangeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ConverterFragment: Fragment() {
    
    private val viewModel: ExchangeViewModel by viewModels()
    private lateinit var binding: FragmentConverterBinding
    
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConverterBinding.inflate(layoutInflater)
        
        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString(),
            )
        }
        
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect {
                when (it) {
                    is ExchangeViewModel.CurrencyEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.BLACK)
                        binding.tvResult.text = it.resultText
                    }
                    is ExchangeViewModel.CurrencyEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.RED)
                        binding.tvResult.text = it.errorText
                    }
    
                    else                                       -> Unit
                }
            }
        }
        
        return binding.root
        
    }
}