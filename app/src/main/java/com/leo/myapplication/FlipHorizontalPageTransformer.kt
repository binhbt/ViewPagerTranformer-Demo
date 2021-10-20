package com.leo.myapplication

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class FlipHorizontalPageTransformer : PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        val rotation = 180f * pos
        if(rotation > 90f || rotation < -90f) {
            page.setAlpha(0f)
        }else{
            page.setAlpha(1f)
        }
        page.setPivotX(page.getWidth() * 0.5f)
        page.setPivotY(page.getHeight() * 0.5f)
        page.setRotationY(rotation)
    }
}

