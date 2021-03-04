package com.example.basecomponentbutton

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat

class TouchListener(private val colorFilter:String?,private val isAnimate:Boolean?) : View.OnTouchListener{
    private val duration:Long = 70
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (colorFilter != null){
                    v?.background?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.parseColor(colorFilter), BlendModeCompat.SRC_ATOP)
                    v?.invalidate()
                }
                if (isAnimate == true){
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




            }
            MotionEvent.ACTION_UP -> {
                if (colorFilter != null){
                    v?.background?.clearColorFilter()
                    v?.invalidate()
                }
                if (isAnimate == true){

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
        }
        return false
    }

}