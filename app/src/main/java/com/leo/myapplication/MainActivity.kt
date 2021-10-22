package com.leo.myapplication

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var introViewPagerAdapter = IntroViewPagerAdapter(supportFragmentManager)
//        viewpager.adapter = introViewPagerAdapter
//        viewpager.setPageTransformer(false, TabletPageTransformer())

        //Day-night trans demo
        var introViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpager.adapter = introViewPagerAdapter
        viewpager.setPageTransformer(false, IntroPageTransformer())
        viewpager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position < introViewPagerAdapter.count - 1 && position < colors.size - 1) {
                    viewpager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            colors[position].color,
                            colors[position + 1].color
                        ) as Int
                    )

                    val progress = (numLevels * positionOffset).toInt()
                    ivSunMoon.setImageLevel(progress)

                } else {
                    val color = colors[colors.size - 1].color
                    // the last page color
                    viewpager.setBackgroundColor(color)
                }
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
            return 2
        }
    }
}
