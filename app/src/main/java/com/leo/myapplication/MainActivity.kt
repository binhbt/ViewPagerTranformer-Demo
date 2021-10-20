package com.leo.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var introViewPagerAdapter = IntroViewPagerAdapter(supportFragmentManager)
        viewpager.adapter = introViewPagerAdapter
        viewpager.setPageTransformer(false, TabletPageTransformer())
    }
    inner class IntroViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager) {
        override fun getItem(position: Int): Fragment {
            if(position%2==0){
                return TestFragment1()
            }
            if(position%2==1){
                return TestFragment2()
            }
            return TestFragment1()
        }
        override fun getCount(): Int {
            return Int.MAX_VALUE
        }
    }
}
