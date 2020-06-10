package com.abs.clase14.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import com.abs.clase14.R
import com.abs.clase14.ui.history.GifFragment
import com.abs.clase14.utils.loadGif
import kotlinx.android.synthetic.main.fragment_gif.*
import org.koin.android.viewmodel.ext.android.viewModel



class MainFragment : Fragment() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.main_fragment, container, false)
        val getGifButton = view.findViewById<Button>(R.id.get_gif)
        val historyButton = view.findViewById<Button>(R.id.show_history)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        viewModel.bindView()
        getGifButton.setOnClickListener {
            viewModel.getNewGif()
            progressBar.visibility  = View.VISIBLE

        }
        historyButton.setOnClickListener {
            val dialog = GifFragment.newInstance
            dialog.show(childFragmentManager,"historyDialog")
        }
        viewModel.gif.observe(viewLifecycleOwner, Observer {
            progressBar.visibility  = View.GONE
            gif_image_view.loadGif(it.url)
        })
        return view
    }

}