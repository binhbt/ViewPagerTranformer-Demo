package com.leo.myapplication

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class FlipVerticalPageTransformer : PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        val rotation = -180f * pos
        page.setAlpha(if (rotation > 90f || rotation < -90f) 0f else 1f)
        page.setPivotX(page.getWidth() * 0.5f)
        page.setPivotY(page.getHeight() * 0.5f)
        page.setRotationX(rotation)
    }
}