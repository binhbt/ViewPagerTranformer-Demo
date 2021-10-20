package com.leo.myapplication

import android.graphics.Camera
import android.graphics.Matrix
import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class TabletPageTransformer : PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        val rotation = (if (pos < 0) 30f else -30f) * Math.abs(pos)
        page.setTranslationX(getOffsetX(rotation, page.getWidth(), page.getHeight()))
        page.setPivotX(page.getWidth() * 0.5f)
        page.setPivotY(0F)
        page.setRotationY(rotation)
    }

    private fun getOffsetX(rotation: Float, width: Int, height: Int): Float {
        MATRIX_OFFSET.reset()
        CAMERA_OFFSET.save()
        CAMERA_OFFSET.rotateY(Math.abs(rotation))
        CAMERA_OFFSET.getMatrix(MATRIX_OFFSET)
        CAMERA_OFFSET.restore()
        MATRIX_OFFSET.preTranslate(-width * 0.5f, -height * 0.5f)
        MATRIX_OFFSET.postTranslate(width * 0.5f, height * 0.5f)
        TEMP_FLOAT_OFFSET[0] = width.toFloat()
        TEMP_FLOAT_OFFSET[1] = height.toFloat()
        MATRIX_OFFSET.mapPoints(TEMP_FLOAT_OFFSET)
        return (width - TEMP_FLOAT_OFFSET[0]) * if (rotation > 0.0f) 1.0f else -1.0f
    }

    companion object {
        private val MATRIX_OFFSET: Matrix = Matrix()
        private val CAMERA_OFFSET: Camera = Camera()
        private val TEMP_FLOAT_OFFSET = FloatArray(2)
    }
}