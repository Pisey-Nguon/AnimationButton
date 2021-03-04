package com.example.basecomponentbutton

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import java.util.concurrent.atomic.AtomicBoolean


fun View.setOnSingleClickListener(clickListener: View.OnClickListener?) {
    clickListener?.also {
        setOnClickListener(OnSingleClickListener(it))
        setOnTouchListener(OnTouchListener())

    } ?: setOnClickListener(null)
}
class OnSingleClickListener(
    private val clickListener: View.OnClickListener,
    private val intervalMs: Long = 1000L
) : View.OnClickListener {
    private var canClick = AtomicBoolean(true)
    override fun onClick(v: View?) {
        if (canClick.getAndSet(false)) {
            v?.run {
                postDelayed({
                    canClick.set(true)
                }, intervalMs)
                clickListener.onClick(v)
            }
        }
    }
}

class OnTouchListener :View.OnTouchListener{
    private val duration:Long = 70
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                v?.background?.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        Color.parseColor("#8B000000"),
                        BlendModeCompat.SRC_ATOP
                    )
                v?.invalidate()

                val scaleDownX = ObjectAnimator.ofFloat(
                    v,
                    "scaleX", 0.94f
                )
                val scaleDownY = ObjectAnimator.ofFloat(
                    v,
                    "scaleY", 0.94f
                )
                scaleDownX.duration = duration
                scaleDownY.duration = duration

                val scaleDown = AnimatorSet()
                scaleDown.play(scaleDownX).with(scaleDownY)

                scaleDown.start()

            }
            MotionEvent.ACTION_UP -> {
                v?.background?.clearColorFilter()
                v?.invalidate()

                val scaleDownX2 = ObjectAnimator.ofFloat(
                    v, "scaleX", 1f
                )
                val scaleDownY2 = ObjectAnimator.ofFloat(
                    v, "scaleY", 1f
                )
                scaleDownX2.duration = duration
                scaleDownY2.duration = duration

                val scaleDown2 = AnimatorSet()
                scaleDown2.play(scaleDownX2).with(scaleDownY2)

                scaleDown2.start()


            }
        }
        return false
    }

}