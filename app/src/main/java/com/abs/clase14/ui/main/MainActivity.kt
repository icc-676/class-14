package com.abs.clase14.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.abs.clase14.R
import com.abs.clase14.service.GifServiceType
import com.abs.clase14.ui.history.GifFragment
import com.abs.clase14.utils.loadGif
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        get_gif.setOnClickListener {
//            gifService.fetchRandomGif()
//        }
//        gifService.getLastGif().observe(this, Observer {
//            if (it != null) {
//                gif_image_view.loadGif(it.url)
//            }
//        })
//        show_history.setOnClickListener {
//            val gifHistory = GifFragment.newInstance()
//            gifHistory.show(
//                supportFragmentManager,
//                "gif_history"
//            )
//        }
//    }


}
