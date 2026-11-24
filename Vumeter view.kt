package com.soundcheck.proeq

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class VuMeterView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var level = 0f
    private val paint = Paint()
    private val totalBars = 24

    fun setLevel(newLevel: Float) {
        level = newLevel.coerceIn(0f, 1f)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val barWidth = width / (totalBars * 1.3f)
        val spacing = barWidth * 0.3f
        val activeBars = (level * totalBars).toInt()

        for (i in 0 until totalBars) {
            paint.color = when {
                i >= activeBars -> Color.parseColor("#222222")
                i < 16 -> Color.parseColor("#00FF66")
                i < 21 -> Color.parseColor("#FFD600")
                else -> Color.parseColor("#FF1744")
            }
            val left = i * (barWidth + spacing)
            canvas.drawRoundRect(left, 0f, left + barWidth, height.toFloat(), 6f, 6f, paint)
        }
    }
}
