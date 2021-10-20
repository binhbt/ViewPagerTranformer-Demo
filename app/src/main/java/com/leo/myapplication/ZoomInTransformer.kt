package com.leo.myapplication

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class ZoomInTransformer : PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        val scale = if (pos < 0) pos + 1f else Math.abs(1f - pos)
        page.setScaleX(scale)
        page.setScaleY(scale)
        page.setPivotX(page.getWidth() * 0.5f)
        page.setPivotY(page.getHeight() * 0.5f)
        page.setAlpha(if (pos < -1f || pos > 1f) 0f else 1f - (scale - 1f))
    }

    companion object {
        const val MAX_ROTATION = 90.0f
    }
}