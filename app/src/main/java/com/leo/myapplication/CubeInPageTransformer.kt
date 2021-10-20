package com.leo.myapplication

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class CubeInPageTransformer : PageTransformer {
    override fun transformPage(page: View, position: Float) {
        // Rotate the fragment on the left or right edge
        page.setPivotX((if (position > 0) 0 else page.getWidth()).toFloat())
        page.setPivotY(0F)
        page.setRotationY(-90f * position)
    }
}