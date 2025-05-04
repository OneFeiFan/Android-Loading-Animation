package com.example.loadinganimation

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //基于xml文件加载动画
        setContentView(R.layout.loading_anim)//fork仓库后就是报错的，但是仍然可以使用
        FullScreencall()
        val loadingAnim=findViewById<LoadingAnimation>(R.id.loadinAnim)//fork仓库后就是报错的，但是仍然可以使用

        loadingAnim.setProgressVector(R.drawable.triad_ring)
        loadingAnim.setTextViewVisibility(true)
        loadingAnim.setTextStyle(true)
        loadingAnim.setTextColor(Color.YELLOW)
        loadingAnim.setTextSize(12F)
        loadingAnim.setTextMsg("Please Wait")
        loadingAnim.setEnlarge(5)

        // 作为Dialog使用
        val dialog = LoadingAnimationDialog(this)
        dialog.setProgressVector(R.drawable.triad_ring)
        dialog.setTextViewVisibility(true)
        dialog.setTextStyle(true)
        dialog.setTextColor(Color.YELLOW)
        dialog.setTextSize(12F)
        dialog.setTextMsg("Please Wait")
        dialog.setEnlarge(5)
        dialog.show()


    }

    fun FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = window.decorView
            val uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView.systemUiVisibility = uiOptions
        }
    }
}