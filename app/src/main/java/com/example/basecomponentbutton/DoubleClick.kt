package com.example.basecomponentbutton

import android.os.Handler
import android.os.SystemClock
import android.view.View


fun View.setOnDoubleClickListener(clickListener: DoubleClick.DoubleClickListener){
    val colorFilter = this.context.getString(R.string.codeColorClick)
    clickListener.also {
        setOnClickListener(DoubleClick(clickListener))
        setOnTouchListener(TouchListener(colorFilter,true))
    }
}
fun View.setOnDoubleClickListener(colorFilter:String,clickListener: DoubleClick.DoubleClickListener){
    clickListener.also {
        setOnClickListener(DoubleClick(clickListener))
        setOnTouchListener(TouchListener(colorFilter,true))
    }
}
fun View.setOnDoubleClickListener(colorFilter:String?,isAnimation:Boolean?,clickListener: DoubleClick.DoubleClickListener){
    clickListener.also {
        setOnClickListener(DoubleClick(clickListener))
        setOnTouchListener(TouchListener(colorFilter,isAnimation))

    }
}
class DoubleClick(private val listener:DoubleClickListener) : View.OnClickListener {
    private var isSingleEvent = false
    private val doubleClickQualificationSpanInMillis: Long = DEFAULT_QUALIFICATION_SPAN
    private var timestampLastClick: Long
    private val handler: Handler
    private val runnable: Runnable
    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - timestampLastClick < doubleClickQualificationSpanInMillis) {
            isSingleEvent = false
            handler.removeCallbacks(runnable)
            listener.onDoubleClick()
            return
        }
        isSingleEvent = true
        handler.postDelayed(runnable, DEFAULT_QUALIFICATION_SPAN)
        timestampLastClick = SystemClock.elapsedRealtime()
    }


    companion object {
        private const val DEFAULT_QUALIFICATION_SPAN: Long = 200
    }

    init {
        timestampLastClick = 0
        handler = Handler()
        runnable = Runnable {
            if (isSingleEvent) {
                listener.onSingleClick()
            }
        }
    }

    interface DoubleClickListener{
        fun onDoubleClick()
        fun onSingleClick()

    }
}