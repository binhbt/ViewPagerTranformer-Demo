package com.leo.myapplication

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class CubeOutPageTransformer : PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        page.setPivotX((if (pos < 0f) page.getWidth() else 0f) as Float)
        page.setPivotY(page.getHeight() * 0.5f)
        page.setRotationY(90f * pos)
    }
}