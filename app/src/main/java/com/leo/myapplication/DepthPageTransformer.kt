package com.leo.myapplication

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class DepthPageTransformer : PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageWidth: Int = page.getWidth()
        if (position < -1) { // [ -Infinity,-1 )
            // This page is way off-screen to the left.
            page.setAlpha(0F)
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            page.setAlpha(1F)
            page.setTranslationX(0F)
            page.setScaleX(1F)
            page.setScaleY(1F)
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            page.setAlpha(1 - position)

            // Counteract the default slide transition
            page.setTranslationX(pageWidth * -position)

            // Scale the page down ( between MIN_SCALE and 1 )
            val scaleFactor = (MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position)))
            page.setScaleX(scaleFactor)
            page.setScaleY(scaleFactor)
        } else { // ( 1, +Infinity ]
            // This page is way off-screen to the right.
            page.setAlpha(0F)
        }
    }

    companion object {
        private const val MIN_SCALE = 0.75f
    }
}