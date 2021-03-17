package com.rysanek.currencyconverter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rysanek.currencyconverter.databinding.FragmentWebBinding

class WebFragment: Fragment() {
    
    private lateinit var binding: FragmentWebBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        
        binding = FragmentWebBinding.inflate(layoutInflater)
        
        return binding.root
    }
}