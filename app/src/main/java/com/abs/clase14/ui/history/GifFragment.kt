package com.abs.clase14.ui.history

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import androidx.room.Room
import com.abs.clase14.R
import com.abs.clase14.model.Database
import com.abs.clase14.model.GifDao
import com.abs.clase14.ui.main.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel

class GifFragment : BottomSheetDialogFragment() {

    val viewModel: GifHistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gif_list, container, false)
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            viewModel.bindView()
            val adapter = GifAdapter()
            view.adapter = adapter
            viewModel.gifHistory.observe(viewLifecycleOwner, Observer {
                adapter.setData(it)
            })
        }
        return view
    }

    companion object{
        var newInstance = GifFragment()
    }

}