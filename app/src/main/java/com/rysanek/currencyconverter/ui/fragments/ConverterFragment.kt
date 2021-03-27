package com.rysanek.currencyconverter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rysanek.currencyconverter.data.remote.models.RatesMap
import com.rysanek.currencyconverter.databinding.FragmentConverterBinding
import com.rysanek.currencyconverter.utils.showSnackBar
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
        binding.btnExchange.setOnClickListener {
            if (binding.etAmount.text.isNullOrEmpty()){
                showSnackBar("Please input an amount")
            } else {
                viewModel.convert(
                    binding.etAmount.text.toString(),
                    RatesMap.rates[binding.spFromCurrency.selectedItem.toString()]!!,
                    RatesMap.rates[binding.spToCurrency.selectedItem.toString()]!!,
                )
            }
        }
        
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect {
                when (it) {
                    is ExchangeViewModel.CurrencyEvent.Success -> {
                        binding.tvResult.text = it.resultText
                        binding.cvResult.visibility = View.VISIBLE
                    }
                    is ExchangeViewModel.CurrencyEvent.Failure -> {
                        binding.tvResult.text = it.errorText
                    }
    
                    else                                       -> Unit
                }
            }
        }
        
        binding.spFromCurrency.setSelection(0)
        binding.spToCurrency.setSelection(1)
        
        return binding.root
        
    }
}