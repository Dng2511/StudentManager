package com.example.studentmanager

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class MyCustomAdapter(private var items: ArrayList<Item>, private val context: Context) : BaseAdapter() {
    override fun getCount(): Int = items.size
    override fun getItem(p0: Int): Any = items[p0]
    override fun getItemId(p0: Int): Long = p0.toLong()

    @SuppressLint("MissingInflatedId")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val row: View = LayoutInflater.from(p2?.context).inflate(R.layout.custom_row_icon_label, p2, false)
        val tvusername = row.findViewById<TextView>(R.id.name)
        val tvmssv = row.findViewById<TextView>(R.id.mssv)
        val imageView = row.findViewById<ImageView>(R.id.icon)



        val tdelete = row.findViewById<Button>(R.id.delete)

        val edit = row.findViewById<Button>(R.id.edit)

        tvusername.text = items[p0].username
        tvmssv.text = items[p0].mssv
        imageView.setImageResource(items[p0].imageResource)
        tdelete.setOnClickListener{
            items.removeAt(p0);
            notifyDataSetChanged()
        }


        edit.setOnClickListener {

        }




        return row
    }



    fun updateList(newList: ArrayList<Item>) {
        items = newList
        notifyDataSetChanged()
    }



}