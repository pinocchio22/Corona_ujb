package com.example.corona_ujb

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */
class VPAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!) {
    private val items: ArrayList<Fragment> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    init {
        items.add(FragmentSearch())
        items.add(FragmentBoard())
        items.add(FragmentMap())
        items.add(FragmentTreat())
    }
}