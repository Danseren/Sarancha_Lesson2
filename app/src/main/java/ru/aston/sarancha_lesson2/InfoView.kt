package ru.aston.sarancha_lesson2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.aston.sarancha_lesson2.databinding.PartInfoBinding

class InfoView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: PartInfoBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_info, this, true)
        binding = PartInfoBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes (attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InfoView, defStyleAttr, defStyleRes)

        with(binding) {
            val imageSrc = typedArray.getDrawable(R.styleable.InfoView_imageSrc)
            ivLogo.setImageDrawable(imageSrc)

            val headerText = typedArray.getText(R.styleable.InfoView_headerText)
            tvLogoHeader.text = headerText ?: context.getString(R.string.tvLogoHeader)

            val bodyText = typedArray.getText(R.styleable.InfoView_bodyText)
            tvLogoText.text = bodyText ?: context.getString(R.string.tvLogoText)
        }

        typedArray.recycle()
    }
}