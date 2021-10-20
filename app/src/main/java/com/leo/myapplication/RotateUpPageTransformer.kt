package com.leo.myapplication

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class RotateUpPageTransformer : PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        val width: Int = page.getWidth()
        val height: Int = page.getHeight()
        val rotation = ROTATION * pos * -1.25f
        page.setPivotX(width * 0.5f)
        page.setPivotY(height.toFloat())
        page.setRotation(rotation)
    }

    companion object {
        private const val ROTATION = -15f
    }
}
