package com.example.basecomponentbutton

import android.view.View
import java.util.concurrent.atomic.AtomicBoolean

fun View.setOnSingleClickListener(clickListener: View.OnClickListener) {
    val colorFilter = this.context.getString(R.string.codeColorDefaultClick)
    clickListener.also {
        setOnClickListener(OnSingleClickListener(it))
        setOnTouchListener(TouchListener(colorFilter,true))
    }
}


fun View.setOnSingleClickListener(colorFilter: String?,clickListener: View.OnClickListener) {
    clickListener.also {
        setOnClickListener(OnSingleClickListener(it))
        setOnTouchListener(TouchListener(colorFilter,null))

    }
}
fun View.setOnSingleClickListener(colorFilter: String?,isAnimate:Boolean?,clickListener: View.OnClickListener) {
    clickListener.also {
        setOnClickListener(OnSingleClickListener(it))
        setOnTouchListener(TouchListener(colorFilter,isAnimate))

    }
}
class OnSingleClickListener(
    private val clickListener: View.OnClickListener
) : View.OnClickListener {
    private val intervalMs: Long = 1000L
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