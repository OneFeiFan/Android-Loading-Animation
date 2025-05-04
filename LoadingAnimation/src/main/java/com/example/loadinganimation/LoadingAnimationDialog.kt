package com.example.loadinganimation

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Window

class LoadingAnimationDialog(context: Context) : Dialog(context) {

    // 委托给内部的 LoadingAnimationView
    private val contentView: LoadingAnimation
    private var canClose: Boolean = true

    init {
        // 设置对话框无标题、透明背景等样式
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        // 初始化内容视图
        contentView = LoadingAnimation(context, null)
        setContentView(contentView)
    }

    // 将所有公共方法委托给 contentView
    fun setProgressVector(drawableFile: Int) = contentView.setProgressVector(drawableFile)
    fun setTextMsg(message: String) = contentView.setTextMsg(message)
    fun setTextStyle(boldText: Boolean) = contentView.setTextStyle(boldText)
    fun setTextColor(textColor: Int) = contentView.setTextColor(textColor)
    fun setTextSize(textSize: Float) = contentView.setTextSize(textSize)
    fun setEnlarge(enlarge: Int) = contentView.setEnlarge(enlarge)
    fun setTextViewVisibility(isVisible: Boolean) = contentView.setTextViewVisibility(isVisible)
    fun setCloseOnClick(canClose: Boolean) {
        this.canClose = canClose
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if(canClose){
            dismiss()
        }else{
            hide()
        }
    }
    // 如果需要覆盖show方法确保设置正确
//    override fun show() {
//        super.show()
//        // 可以在这里添加额外的对话框显示逻辑
//    }
}