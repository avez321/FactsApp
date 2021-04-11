package com.example.factsapp.ui.facts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.factsapp.FactsApp
import com.example.factsapp.R
import com.example.factsapp.databinding.FragmentFactsListBinding


import javax.inject.Inject


class FactsFragment : Fragment() {
    private lateinit var fragmentFactsListBinding: FragmentFactsListBinding
    @Inject lateinit var factsViewModel: FactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factsApp = requireActivity().application as FactsApp
        factsApp.appComponent.inject(this)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().title = ""
        fragmentFactsListBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_facts_list, container, false)
        fragmentFactsListBinding.viewmodel = factsViewModel

        initRecycleView()
        setListener()
        initObservers()

        return fragmentFactsListBinding.root
    }

    private fun initObservers() {
        factsViewModel.getTitleMutableLiveData().observe(viewLifecycleOwner, Observer {
            requireActivity().title = it
        })

        factsViewModel.getSwipeRefreshMutableLiveData().observe(viewLifecycleOwner, Observer {
            fragmentFactsListBinding.swipeRefresh.isRefreshing = it
        })
    }

    private fun initRecycleView() {
        val adapter = FactsAdapter()
        val layoutManager =
                 LinearLayoutManager(requireContext())
        
        fragmentFactsListBinding.rvFacts.layoutManager = layoutManager
        fragmentFactsListBinding.rvFacts.adapter = adapter

    }


    private fun setListener() {
        fragmentFactsListBinding.swipeRefresh.setOnRefreshListener {
            factsViewModel.onFactsRefresh(fragmentFactsListBinding.swipeRefresh.isRefreshing)
        }
    }
}
