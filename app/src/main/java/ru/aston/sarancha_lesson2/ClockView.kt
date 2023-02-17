package ru.aston.sarancha_lesson2

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.cos
import kotlin.math.sin
import kotlin.properties.Delegates

class ClockView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
    ) : View (context, attributeSet, defStyleAttr, defStyleRes){

    private var clockColor by Delegates.notNull<Int>()
    private var hourHandColor by Delegates.notNull<Int>()
    private var minuteHandColor by Delegates.notNull<Int>()
    private var secondHandColor by Delegates.notNull<Int>()

    private lateinit var clockPaint: Paint
    private lateinit var hourHandPaint: Paint
    private lateinit var minuteHandPaint: Paint
    private lateinit var secondHandPaint: Paint

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this (context, attributeSet, defStyleAttr, R.style.DefaultClockStyle)
        constructor(context: Context, attributeSet: AttributeSet?) : this (context, attributeSet, R.attr.clockStyle)
        constructor(context: Context) : this (context, null)

    init {
        if (attributeSet != null) {
            initAttributes(attributeSet, defStyleAttr, defStyleRes)
        } else {
            initDefaultColors()
        }
        initPaints()
    }

    private fun initAttributes (attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ClockView, defStyleAttr, defStyleRes)
        clockColor = typedArray.getColor(R.styleable.ClockView_clockColor, CLOCK_DEFAULT_COLOR)
        hourHandColor = typedArray.getColor(R.styleable.ClockView_hourHandColor, HOUR_HAND_DEFAULT_COLOR)
        minuteHandColor = typedArray.getColor(R.styleable.ClockView_minuteHandColor, MINUTE_HAND_DEFAULT_COLOR)
        secondHandColor = typedArray.getColor(R.styleable.ClockView_secondHandColor, SECOND_HAND_DEFAULT_COLOR)


        typedArray.recycle()
    }

    private fun initDefaultColors() {
        clockColor = CLOCK_DEFAULT_COLOR
        hourHandColor = HOUR_HAND_DEFAULT_COLOR
        minuteHandColor = MINUTE_HAND_DEFAULT_COLOR
        secondHandColor = SECOND_HAND_DEFAULT_COLOR
    }

    private fun initPaints() {
        clockPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        clockPaint.apply {
            color = clockColor
            style = Paint.Style.STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics)
        }

        hourHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        hourHandPaint.apply {
            color = hourHandColor
            style = Paint.Style.STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)
        }

        minuteHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        minuteHandPaint.apply {
            color = minuteHandColor
            style = Paint.Style.STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics)
        }

        secondHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        secondHandPaint.apply {
            color = secondHandColor
            style = Paint.Style.STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerWidth = (width / 2).toFloat()
        val centerHeight = (height / 2).toFloat()

        val hourStartWidth = (width / 2 - 25).toFloat()
        val hourStartHeight = (height / 2).toFloat()
        val hourEndWidth = (width / 2 + 75).toFloat()
        val hourEndHeight = (height / 2).toFloat()

        val minuteStartWidth = (width / 2 - 25).toFloat()
        val minuteStartHeight = (height / 2 + 25).toFloat()
        val minuteEndWidth = (width / 2 + 100).toFloat()
        val minuteEndHeight = (height / 2 - 100).toFloat()

        val secondStartWidth = (width / 2).toFloat()
        val secondStartHeight = (height / 2 + 50).toFloat()
        val secondEndWidth = (width / 2).toFloat()
        val secondEndHeight = (height / 2 - 200).toFloat()

        canvas.drawCircle(centerWidth, centerHeight, 300f, clockPaint)

        for (i in 0..360 step 30) {
            canvas.drawLine(getXCoordinateOnCircle(300f, i.toDouble()), getYCoordinateOnCircle(300f, i.toDouble()), getXCoordinateOnCircle(270f, i.toDouble()), getYCoordinateOnCircle(270f, i.toDouble())  , clockPaint)
        }
        canvas.drawLine(centerWidth, centerHeight, hourStartWidth, hourStartHeight, hourHandPaint)
        canvas.drawLine(centerWidth, centerHeight, hourEndWidth, hourEndHeight, hourHandPaint)

        canvas.drawLine(centerWidth, centerHeight, minuteStartWidth, minuteStartHeight, minuteHandPaint)
        canvas.drawLine(centerWidth, centerHeight, minuteEndWidth, minuteEndHeight, minuteHandPaint)

        canvas.drawLine(centerWidth, centerHeight, secondStartWidth, secondStartHeight, secondHandPaint)
        canvas.drawLine(centerWidth, centerHeight, secondEndWidth, secondEndHeight, secondHandPaint)
    }

    private fun getXCoordinateOnCircle(radius: Float, corner: Double) : Float {
        val x = (width / 2) + radius * cos(Math.toRadians(corner))
        return x.toFloat()
    }

    private fun getYCoordinateOnCircle(radius: Float, corner: Double) : Float {
        val y = (height / 2) + radius * sin(Math.toRadians(corner))
        return y.toFloat()
    }

    companion object {
        const val CLOCK_DEFAULT_COLOR = Color.BLACK
        const val HOUR_HAND_DEFAULT_COLOR = Color.BLACK
        const val MINUTE_HAND_DEFAULT_COLOR = Color.BLACK
        const val SECOND_HAND_DEFAULT_COLOR = Color.BLACK
    }
}