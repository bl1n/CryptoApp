package team.lf.cryptoapp.mvp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_currencies_list.*
import team.lf.cryptoapp.adapter.BaseAdapter

abstract class BaseFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    protected lateinit var viewAdapter: BaseAdapter<*>
    protected lateinit var viewManager:RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context)
        viewAdapter = createAdapterInstance()
        recyclerView = list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    abstract fun createAdapterInstance(): BaseAdapter<*>
}