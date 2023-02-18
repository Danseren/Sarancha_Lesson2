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
    private var hourHandLength by Delegates.notNull<Float>()
    private var minuteHandLength by Delegates.notNull<Float>()
    private var secondHandLength by Delegates.notNull<Float>()

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
        hourHandLength =
            typedArray.getFloat(R.styleable.ClockView_hourHandLength, HOUR_HAND_DEFAULT_LENGTH)
        minuteHandLength =
            typedArray.getFloat(R.styleable.ClockView_minuteHandLength, MINUTE_HAND_DEFAULT_LENGTH)
        secondHandLength =
            typedArray.getFloat(R.styleable.ClockView_secondHandLength, SECOND_HAND_DEFAULT_LENGTH)
        typedArray.recycle()
    }

    private fun initDefaultColors() {
        clockColor = CLOCK_DEFAULT_COLOR
        hourHandColor = HOUR_HAND_DEFAULT_COLOR
        minuteHandColor = MINUTE_HAND_DEFAULT_COLOR
        secondHandColor = SECOND_HAND_DEFAULT_COLOR
        hourHandLength = HOUR_HAND_DEFAULT_LENGTH
        minuteHandLength = MINUTE_HAND_DEFAULT_LENGTH
        secondHandLength = SECOND_HAND_DEFAULT_LENGTH
    }

    /**
     * Инициализируем наши кисти для рисования
     */
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
        postInvalidateDelayed(1000)
        invalidate()
    }

    /**
     * Рисуем циферблат наших часов
     */
    private fun drawClock(canvas: Canvas) {
        val centerWidth = (width / 2).toFloat()
        val centerHeight = (height / 2).toFloat()

        canvas.drawCircle(centerWidth, centerHeight, CLOCK_RADIUS, clockPaint)

        for (i in 0..360 step 30) {
            canvas.drawLine(
                getXCoordinateOnCircle(CLOCK_RADIUS, i.toDouble()),
                getYCoordinateOnCircle(CLOCK_RADIUS, i.toDouble()),
                getXCoordinateOnCircle(LABEL_RADIUS, i.toDouble()),
                getYCoordinateOnCircle(LABEL_RADIUS, i.toDouble()),
                clockPaint
            )
        }
    }

    /**
     * Отрисовываем стрелки
     */
    private fun drawHands(canvas: Canvas) {
        drawHand(canvas, HOUR_HAND, hourHandPaint)
        drawHand(canvas, MINUTE_HAND, minuteHandPaint)
        drawHand(canvas, SECOND_HAND, secondHandPaint)
    }

    /**
     * Рисуем одну конкретную стрелку в зависимости от её типа
     * @canvas - наш канвас
     * @handType - тип стрелки (часовая, минутная, секундная)
     * @paintTape - стиль кисти, которой рисуем
     */
    private fun drawHand(canvas: Canvas, handType: Int, paintTape: Paint) {
        val calendar = Calendar.getInstance()
        val corner: Double
        val handLength: Float
        when (handType) {
            HOUR_HAND -> {
                handLength = hourHandLength
                corner = HAND_CORNER + calendar[handType] * 30
            }
            MINUTE_HAND -> {
                handLength = minuteHandLength
                corner = HAND_CORNER + calendar[handType] * 6
            }
            else -> {
                handLength = secondHandLength
                corner = HAND_CORNER + calendar[handType] * 6
            }
        }
        canvas.drawLine(
            getXCoordinateOnCircle(TAIL_OF_HAND_LENGTH, corner),
            getYCoordinateOnCircle(TAIL_OF_HAND_LENGTH, corner),
            getXCoordinateOnCircle(handLength, corner + TAIL_OF_HAND_CORNER),
            getYCoordinateOnCircle(handLength, corner + TAIL_OF_HAND_CORNER),
            paintTape
        )
    }

    /**
     * Получаем Х-координату точки лежащей на окружности заданного радиуса
     * @radius - радиус окружности
     * @corner - угол для вычисления координаты
     */
    private fun getXCoordinateOnCircle(radius: Float, corner: Double): Float {
        val xCoordinate = (width / 2) + radius * cos(Math.toRadians(corner))
        return xCoordinate.toFloat()
    }

    /**
     * Получаем Y-координату точки лежащей на окружности заданного радиуса
     * @radius - радиус окружности
     * @corner - угол для вычисления координаты
     */
    private fun getYCoordinateOnCircle(radius: Float, corner: Double): Float {
        val yCoordinate = (height / 2) + radius * sin(Math.toRadians(corner))
        return yCoordinate.toFloat()
    }

    companion object {
        const val CLOCK_DEFAULT_COLOR = Color.BLACK
        const val HOUR_HAND_DEFAULT_COLOR = Color.BLACK
        const val MINUTE_HAND_DEFAULT_COLOR = Color.BLACK
        const val SECOND_HAND_DEFAULT_COLOR = Color.BLACK
        const val HOUR_HAND_DEFAULT_LENGTH = 100f
        const val MINUTE_HAND_DEFAULT_LENGTH = 150f
        const val SECOND_HAND_DEFAULT_LENGTH = 200f

        const val TAIL_OF_HAND_LENGTH = 30f
        const val CLOCK_RADIUS = 300f
        const val LABEL_RADIUS = 270f
        const val TAIL_OF_HAND_CORNER = 180
        const val HAND_CORNER = 90.0

        const val HOUR_HAND = Calendar.HOUR
        const val MINUTE_HAND =  Calendar.MINUTE
        const val SECOND_HAND = Calendar.SECOND
    }
}