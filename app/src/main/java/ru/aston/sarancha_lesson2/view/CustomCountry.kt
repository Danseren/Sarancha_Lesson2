package ru.aston.sarancha_lesson2.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.aston.sarancha_lesson2.R
import ru.aston.sarancha_lesson2.databinding.PartCountryBinding

class CustomCountry(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: PartCountryBinding

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
        inflater.inflate(R.layout.part_country, this, true)
        binding = PartCountryBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomCountry, defStyleAttr, defStyleRes)

        val imageSrc = typedArray.getInteger(R.styleable.CustomCountry_countryFlagSrc, R.drawable.belarus)
        setImageSrc(imageSrc)

        val headerText = typedArray.getString(R.styleable.CustomCountry_countryText)
        setHeaderText(headerText)

        val bodyText = typedArray.getString(R.styleable.CustomCountry_cityText)
        setBodyText(bodyText)

        typedArray.recycle()
    }

    fun setHeaderText(headerText: String?) {
        binding.tvCountryHeader.text = headerText ?: context.getString(R.string.tvCountryHeader)
    }

    fun setBodyText(bodyText: String?) {
        binding.tvCityText.text = bodyText ?: context.getString(R.string.tvCityText)
    }

    fun setImageSrc(imageSrc: Int) {
        binding.ivLogo.setImageResource(imageSrc)
    }
}