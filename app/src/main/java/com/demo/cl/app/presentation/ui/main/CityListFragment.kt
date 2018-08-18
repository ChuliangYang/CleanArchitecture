package com.demo.cl.app.presentation.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.cl.app.presentation.di.base.Injectable
import com.demo.cl.app.presentation.entity.CityModel
import com.demo.cl.app.presentation.ui.adapter.CityAdapterBind
import com.demo.cl.app.presentation.ui.widgets.ViewStatePagerAdapter
import com.demo.cl.app.presentation.utils.autoCleared
import com.demo.cl.app.presentation.viewmodel.CityListViewModel
import com.demo.cl.clean_architecture.R
import kotlinx.android.synthetic.main.fragment_city_list.*
import javax.inject.Inject

class CityListFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: CityListViewModel
    private var viewPagerAdapter by autoCleared<CityListPageAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CityListViewModel::class.java)
        viewModel.getTwoCityList().observe(this, Observer {
            it?.original?.run {
                viewPagerAdapter= CityListPageAdapter(this)
                vp_city_list.adapter = viewPagerAdapter
            }
        })
        viewModel.getTitle().observe(this, Observer {
            titlebar.title = it?.original
        })
        tabLayout.setupWithViewPager(vp_city_list)
        vp_city_list.currentItem=0
    }

    companion object {
        const val TAG = "CityListFragment"
        fun newInstance(): CityListFragment {
            return CityListFragment().apply {
            }
        }
    }
}

class CityListPageAdapter(val twoList: Array<List<CityModel>>) : ViewStatePagerAdapter() {
    override fun createView(container: ViewGroup?, position: Int): View {
        val baseContext = container?.context
        val rv = LayoutInflater.from(baseContext).inflate(R.layout.recyclerview, container, false) as RecyclerView
        rv.apply {
            rv.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
            if (position == 0) {
                rv.adapter = CityAdapterBind().apply {
                    submitList(twoList[position])
                }
            } else {
                rv.adapter = CityAdapterBind().apply {
                    submitList(twoList[position])
                }
            }
        }

        return rv
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            "NO.ONE"
        } else {
            "TWO"
        }
    }

    override fun getCount(): Int {
        return twoList.size
    }
}