package com.geeks.retrofit_5hw2.ui.fragments.loveCalculator

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.geeks.retrofit_5hw2.R
import com.geeks.retrofit_5hw2.adapter.HistoryAdapter
import com.geeks.retrofit_5hw2.databinding.FragmentHistoryBinding
import com.geeks.retrofit_5hw2.di.AppModule
import com.geeks.retrofit_5hw2.room.LoveEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var adapter: HistoryAdapter
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(LoveViewModel::class.java)
        adapter = HistoryAdapter(this)
        val list = AppModule().provideRoomDataBase(requireContext()).getHistoryDao().getHistory()
        binding.rvHistory.adapter = adapter
        adapter.submitList(list.value)
        initListener()

        viewModel.historyList.observe(viewLifecycleOwner, Observer { historyList ->
            adapter.submitList(historyList)
        })
    }

    private fun initListener() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().navigate(R.id.firstFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    private fun updateList() {
        val historyList =
            AppModule().provideRoomDataBase(requireContext()).getHistoryDao().getHistory()
        adapter.submitList(historyList.value)
    }

    override fun onItemLongClicked(historyEntity: LoveEntity) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удалить элемент")
            .setMessage("Вы уверены, что хотите удалить этот элемент?")
            .setPositiveButton("Да") { _, _ ->
                AppModule().provideRoomDataBase(requireContext()).getHistoryDao()
                    .deleteHistory(historyEntity)
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}