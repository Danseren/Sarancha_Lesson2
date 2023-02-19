package ru.aston.sarancha_lesson2

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import ru.aston.sarancha_lesson2.databinding.PartInfoBinding

class InfoView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: PartInfoBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_info, this, true)
        binding = PartInfoBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.InfoView, defStyleAttr, defStyleRes)

        val imageSrc = typedArray.getDrawable(R.styleable.InfoView_imageSrc)
        setImageSrc(imageSrc)

        val headerText = typedArray.getString(R.styleable.InfoView_headerText)
        setHeaderText(headerText)

        val bodyText = typedArray.getString(R.styleable.InfoView_bodyText)
        setBodyText(bodyText)
        typedArray.recycle()
    }

    fun setHeaderText(headerText: String?) {
        binding.tvLogoHeader.text = headerText ?: context.getString(R.string.tvLogoHeader)
    }

    fun setBodyText(bodyText: String?) {
        binding.tvLogoText.text = bodyText ?: context.getString(R.string.tvLogoText)
    }

    fun setImageSrc(imageSrc: Drawable?) {
        binding.ivLogo.setImageDrawable(imageSrc ?: ResourcesCompat.getDrawable(
            resources,
            R.drawable.logo,
            null
        ))
    }
}