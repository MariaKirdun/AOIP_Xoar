package com.example.aoip_xoar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var viewManager : RecyclerView.LayoutManager
    private lateinit var viewAdapter : RecyclerView.Adapter<*>

    private var myDataset = Array(1_000_000){0}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myDataset)

        itemsRecyclerView.layoutManager = viewManager
        itemsRecyclerView.adapter = viewAdapter

        for (i in 0 until myDataset.size){
            myDataset[i] =  Random.nextInt(0,1000000)
        }

        sortButton.setOnClickListener {
            val xoar = XoarSort(myDataset)
            myDataset = xoar.sort()
            viewAdapter.notifyDataSetChanged()
        }
    }
}

class MyAdapter(private val myDataSet: Array<Int>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val textView: TextView ) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val textView = LayoutInflater.from(parent.context)
                                     .inflate(R.layout.my_text_view, parent, false) as TextView

        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = myDataSet[position].toString()
    }

    override fun getItemCount() = myDataSet.size
}
