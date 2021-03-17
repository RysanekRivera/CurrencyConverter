package com.rysanek.currencyconverter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rysanek.currencyconverter.databinding.FragmentGraphBinding

class GraphFragment: Fragment() {

    private lateinit var binding: FragmentGraphBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        
        binding = FragmentGraphBinding.inflate(layoutInflater)
        
        return binding.root
    }

}