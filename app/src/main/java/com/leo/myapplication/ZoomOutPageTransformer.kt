package com.leo.myapplication

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class ZoomOutPageTransformer : PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageWidth: Int = page.getWidth()
        val pageHeight: Int = page.getHeight()
        if (position < -1) { // [ -Infinity,-1 )
            // This page is way off-screen to the left.
            page.setAlpha(0F)
        } else if (position <= 1) { // [ -1,1 ]
            // Modify the default slide transition to shrink the page as well
            val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
            val vertMargin = pageHeight * (1 - scaleFactor) / 2
            val horzMargin = pageWidth * (1 - scaleFactor) / 2
            if (position < 0) {
                page.setTranslationX(horzMargin - vertMargin / 2)
            } else {
                page.setTranslationX(-horzMargin + vertMargin / 2)
            }

            // Scale the page down ( between MIN_SCALE and 1 )
            page.setScaleX(scaleFactor)
            page.setScaleY(scaleFactor)

            // Fade the page relative to its size.
            page.setAlpha(
                MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                        (1 - MIN_SCALE) * (1 - MIN_ALPHA)
            )
        } else { // ( 1,+Infinity ]
            // This page is way off-screen to the right.
            page.setAlpha(0F)
        }
    }

    companion object {
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }
}