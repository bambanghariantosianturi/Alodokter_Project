package com.androidexercise.admin.alodokterproject.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidexercise.admin.alodokterproject.R
import com.androidexercise.admin.alodokterproject.adapter.HomeAdapter
import com.androidexercise.admin.alodokterproject.datalocal.Items
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val data = ArrayList<Items>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_home.layoutManager = LinearLayoutManager(context)
        rv_home.adapter = HomeAdapter(data)

        initData()
    }

    private fun initData() {
        var items = Items("Cat 1", R.drawable.kucing)
        data.add(items)

        items = Items("Cat 2", R.drawable.kucing_manis)
        data.add(items)

        items = Items("Cat 3", R.drawable.kucing_liar)
        data.add(items)

    }
}
