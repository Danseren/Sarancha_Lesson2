package ru.aston.sarancha_lesson2

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.properties.Delegates

class ClockView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributeSet, defStyleAttr, defStyleRes) {

    private var clockColor by Delegates.notNull<Int>()
    private var hourHandColor by Delegates.notNull<Int>()
    private var minuteHandColor by Delegates.notNull<Int>()
    private var secondHandColor by Delegates.notNull<Int>()

    private lateinit var clockPaint: Paint
    private lateinit var hourHandPaint: Paint
    private lateinit var minuteHandPaint: Paint
    private lateinit var secondHandPaint: Paint

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        R.style.DefaultClockStyle
    )

    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        R.attr.clockStyle
    )

    constructor(context: Context) : this(context, null)

    init {
        if (attributeSet != null) {
            initAttributes(attributeSet, defStyleAttr, defStyleRes)
        } else {
            initDefaultColors()
        }
        initPaints()
    }

    private fun initAttributes(attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray: TypedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.ClockView,
            defStyleAttr,
            defStyleRes
        )

        clockColor = typedArray.getColor(R.styleable.ClockView_clockColor, CLOCK_DEFAULT_COLOR)
        hourHandColor =
            typedArray.getColor(R.styleable.ClockView_hourHandColor, HOUR_HAND_DEFAULT_COLOR)
        minuteHandColor =
            typedArray.getColor(R.styleable.ClockView_minuteHandColor, MINUTE_HAND_DEFAULT_COLOR)
        secondHandColor =
            typedArray.getColor(R.styleable.ClockView_secondHandColor, SECOND_HAND_DEFAULT_COLOR)
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
            strokeWidth =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, resources.displayMetrics)
        }

        hourHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        hourHandPaint.apply {
            color = hourHandColor
            style = Paint.Style.STROKE
            strokeWidth =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, resources.displayMetrics)
        }

        minuteHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        minuteHandPaint.apply {
            color = minuteHandColor
            style = Paint.Style.STROKE
            strokeWidth =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics)
        }

        secondHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        secondHandPaint.apply {
            color = secondHandColor
            style = Paint.Style.STROKE
            strokeWidth =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, resources.displayMetrics)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawClock(canvas)
        drawHands(canvas)
        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawClock(canvas: Canvas) {
        val centerWidth = (width / 2).toFloat()
        val centerHeight = (height / 2).toFloat()

        canvas.drawCircle(centerWidth, centerHeight, 300f, clockPaint)

        for (i in 0..360 step 30) {
            canvas.drawLine(
                getXCoordinateOnCircle(300f, i.toDouble()),
                getYCoordinateOnCircle(300f, i.toDouble()),
                getXCoordinateOnCircle(270f, i.toDouble()),
                getYCoordinateOnCircle(270f, i.toDouble()),
                clockPaint
            )
        }
    }

    private fun drawHands(canvas: Canvas) {
        drawHand(canvas, Calendar.HOUR, hourHandPaint)
        drawHand(canvas, Calendar.MINUTE, minuteHandPaint)
        drawHand(canvas, Calendar.SECOND, secondHandPaint)
    }

    private fun drawHand(canvas: Canvas, handType: Int, paintTape: Paint) {
        val calendar = Calendar.getInstance()
        var corner = 90.0

        var handLength = 100f
        when (handType) {
            Calendar.HOUR -> {
                handLength = 100f
                corner = 90.0 + calendar[handType] * 30
            }
            Calendar.MINUTE -> {
                handLength = 150f
                corner = 90.0 + calendar[handType] * 6
            }
            else -> {
                handLength = 200f
                corner = 90.0 + calendar[handType] * 6
            }
        }
        canvas.drawLine(
            getXCoordinateOnCircle(30f, corner),
            getYCoordinateOnCircle(30f, corner),
            getXCoordinateOnCircle(handLength, corner + 180.0),
            getYCoordinateOnCircle(handLength, corner + 180.0),
            paintTape
        )
    }

    private fun getXCoordinateOnCircle(radius: Float, corner: Double): Float {
        val x = (width / 2) + radius * cos(Math.toRadians(corner))
        return x.toFloat()
    }

    private fun getYCoordinateOnCircle(radius: Float, corner: Double): Float {
        val y = (height / 2) + radius * sin(Math.toRadians(corner))
        return y.toFloat()
    }

    companion object {
        const val CLOCK_DEFAULT_COLOR = Color.BLACK
        const val HOUR_HAND_DEFAULT_COLOR = Color.BLACK
        const val MINUTE_HAND_DEFAULT_COLOR = Color.BLACK
        const val SECOND_HAND_DEFAULT_COLOR = Color.BLACK
        const val HOUR_HAND = "HOUR_HAND"
        const val MINUTE_HAND = "MINUTE_HAND"
        const val SECOND_HAND = "SECOND_HAND"
    }
}