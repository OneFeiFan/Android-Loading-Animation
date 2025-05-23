package com.example.loadinganimation

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import pl.droidsonroids.gif.GifDrawable



class LoadingAnimation(context: Context, attributeSet: AttributeSet?) :
    RelativeLayout(context, attributeSet) {
    private lateinit var imageView: ImageView;

    // Set Default value
    private val default_msgTextView = "Please Wait..."
    private val default_textColor = Color.BLACK
    private val default_enlarge = 1
    private val default_textSize: Float = 15F

    // Set some attribute get form constructor
    private var mContext: Context;
    private var attrs: AttributeSet?;
    private var styleAttr: Int = 0;
    // Create some instance
    private var drawableFile: Int = R.drawable.triad_ring
    private lateinit var bgprogress: View
    private lateinit var view: View
    private lateinit var tvMsg: TextView
    private var costumeMsg = default_msgTextView
    private var textColor = default_textColor
    private var enlarge = default_enlarge
    private var textSize: Float = default_textSize
    private var boldText = false;
    private var isVisible = false;

    // setting context and defult value get from primary constructor
    init {
        mContext = context;
        attrs = attributeSet;
        styleAttr = 0
        this.view = this
        inflate(context, R.layout.layout, this);
        val arr = context.obtainStyledAttributes(attributeSet, R.styleable.LoadingAnimation)
        initTypeArray(arr)
    }

    // second constructor
    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : this(
        context,
        attributes
    ) {
        mContext = context;
        attrs = attributes;
        styleAttr = defStyle
        var typedArray =
            context.obtainStyledAttributes(attributes, R.styleable.LoadingAnimation, defStyle, 0)
        initTypeArray(typedArray)
    }




    //    Extracting Attribute form XML
    private fun initTypeArray(typedArray: TypedArray) {
        drawableFile = typedArray.getResourceId(R.styleable.LoadingAnimation_barType, R.drawable.triad_ring);
        textSize = typedArray.getDimension(R.styleable.LoadingAnimation_textSize, default_textSize)
        textColor = typedArray.getColor(R.styleable.LoadingAnimation_textColor, default_textColor);
        costumeMsg = typedArray.getString(R.styleable.LoadingAnimation_text).toString();
        enlarge = typedArray.getInt(R.styleable.LoadingAnimation_enlarge, default_enlarge);
        boldText = typedArray.getBoolean(R.styleable.LoadingAnimation_boldText, false);
        isVisible = typedArray.getBoolean(R.styleable.LoadingAnimation_isVisible, false);
        imageView = findViewById(R.id.progressImg);
        bgprogress = findViewById(R.id.progBg);
        tvMsg = findViewById(R.id.textMsg);

        drawableFile?.let { setProgressVector(it) }

        if (costumeMsg != null) {
            setTextMsg(costumeMsg);
        }

        setTextColor(textColor);
        setTextSize(textSize);
        setEnlarge(enlarge);
        setTextStyle(boldText);
        setTextViewVisibility(isVisible)
        typedArray.recycle()
    }

    //    Set Gif on View
    fun setProgressVector(drawableFile:Int) {
        val gifDrawable: GifDrawable = GifDrawable(resources, drawableFile)
        imageView.setImageDrawable(gifDrawable);
    }

    //    Set Massage Text
    fun setTextMsg(massage: String) {
        tvMsg.setText(massage)
    }

    //    Set text Bold
    fun setTextStyle(boldText: Boolean) {
        if (boldText)
            tvMsg.setTypeface(Typeface.DEFAULT_BOLD);
    }

    //    Set TextColor
    fun setTextColor(textColor: Int) {
        tvMsg.setTextColor(textColor)
    }

    //    SetText Size
    fun setTextSize(textSize: Float) {
        tvMsg.textSize = textSize;
    }

    //    Enlarge the gif file
    fun setEnlarge(enlarge: Int) {
        if (enlarge >= 1 && enlarge <= 10)
            imageView.getLayoutParams().height = enlarge * 100;
    }

    //    Set TextView visibility
    fun setTextViewVisibility(isVisible:Boolean){
        if (isVisible)
            tvMsg.visibility=View.VISIBLE
        else
            tvMsg.visibility=View.GONE
    }


}