package com.leo.myapplication

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.leo.myapplication.FragmentA.Companion.newInstance
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val colors by lazy { Content.values() }
    private val argbEvaluator = ArgbEvaluator()
    private val numLevels = 28
    var currentPos =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var introViewPagerAdapter = IntroViewPagerAdapter(supportFragmentManager)
//        viewpager.adapter = introViewPagerAdapter
//        viewpager.setPageTransformer(false, TabletPageTransformer())

        //Day-night trans demo
        var introViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpager.adapter = introViewPagerAdapter
//        viewpager.setPageTransformer(false, IntroPageTransformer())
        viewpager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val a = position - currentPos
                Log.e("onPageScrolled",  a.toString())
                Log.e("onPageScrolled", positionOffset.toString())
                val pos = Math.abs(position - currentPos)
//                if (pos==0) {
//                    viewpager.setBackgroundColor(
//                        argbEvaluator.evaluate(
//                            positionOffset,
//                            colors[0].color,
//                            colors[1].color
//                        ) as Int
//                    )
                    if(positionOffset!=0f) {
                        val progress = (numLevels * positionOffset).toInt()
                        ivSunMoon.setImageLevel(progress)
                    }

//                } else {
//                    val color = colors[colors.size - 1].color
//                    // the last page color
//                    viewpager.setBackgroundColor(color)
//                    ivSunMoon.setImageLevel(numLevels)
//                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPos = position
                val color = colors[colors.size - 1].color
//                viewpager.setBackgroundColor(color)
                ivSunMoon.setImageLevel(numLevels)
            }
        })
    }
    inner class IntroViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager) {
        override fun getItem(position: Int): Fragment {
            if(position%2==0){
                return TestFragment1()
            }
            if(position%2==1){
                return TestFragment1()
            }
            return TestFragment1()
        }
        override fun getCount(): Int {
            return Int.MAX_VALUE
        }
    }
    class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return newInstance(position)
        }

        override fun getCount(): Int {
            return Int.MAX_VALUE
        }
    }
}
