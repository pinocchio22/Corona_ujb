package com.example.corona_ujb

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */
class FragmentSearch : Fragment() {

    private lateinit var vm: CoronaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        vm = ViewModelProvider(this).get(SearchViewModel::class.java)
        vm.getNews()
        vm.uri?.observe(viewLifecycleOwner, Observer { uri ->
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })

        binding.coronaViewModel = vm
        binding.lifecycleOwner = this
        return binding.root
    }
}