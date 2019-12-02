package com.example.aoip_xoar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.NumberFormatException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val viewManager = LinearLayoutManager(this)
    private val viewAdapter = MyAdapter()

    private var myDataset: ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsRecyclerView.layoutManager = viewManager
        itemsRecyclerView.adapter = viewAdapter

        sortButton.setOnClickListener {
            try {
                field?.let {
                    setArray(it.text.toString().toInt())
                }
            } catch (e: NumberFormatException) {
                e.stackTrace
            }
            myDataset?.let {
                    val xoar = XoarSort(it)
                GlobalScope.launch {
                    myDataset = async { xoar.sort() }.await()
                }
                viewAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun setArray(n: Int) {
        myDataset = ArrayList()
        for (i in 0 until n) {
            myDataset!!.add(Random.nextInt(0, n))
        }
        viewAdapter.data = myDataset
        viewAdapter.notifyDataSetChanged()
    }


    class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        var data: ArrayList<Int>? = null

        class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false) as TextView

            return MyViewHolder(textView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = data?.get(position)?.toString()
        }

        override fun getItemCount(): Int = data?.size ?: 0
    }
}
