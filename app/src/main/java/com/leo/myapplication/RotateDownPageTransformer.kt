package com.leo.myapplication

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class RotateDownPageTransformer : PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val width: Int = page.getWidth()
        val rotation = ROTATION * position
        page.setPivotX(width * 0.5f)
        page.setPivotY(0f)
        page.setTranslationX(0f)
        page.setRotation(rotation)
    }

    companion object {
        private const val ROTATION = -15f
    }
}
