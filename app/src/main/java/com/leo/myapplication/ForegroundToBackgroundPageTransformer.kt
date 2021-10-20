package com.leo.myapplication

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager.PageTransformer
import java.lang.Float.min


class ForegroundToBackgroundPageTransformer : PageTransformer {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun transformPage(page: View, pos: Float) {
        val height: Int = page.getHeight()
        val width: Int = page.getWidth()
        val scale: Float = min(if (pos > 0) 1f else Math.abs(1f + pos), 1f)
        page.setScaleX(scale)
        page.setScaleY(scale)
        page.setPivotX(width * 0.5f)
        page.setPivotY(height * 0.5f)
        page.setTranslationX(if (pos > 0) width * pos else -width * pos * 0.25f)
    }
}