package com.github.ejchathuranga.kotlin_mvvm_android.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.github.ejchathuranga.kotlin_mvvm_android.R

class ButtonFilter@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes)  {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.component_filter, this, true)

        orientation = VERTICAL
    }

}