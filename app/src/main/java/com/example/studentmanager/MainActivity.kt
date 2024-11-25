package com.example.studentmanager

import android.os.Bundle
import android.widget.ListView
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random
import android.content.Intent
import android.widget.Button


class MainActivity : AppCompatActivity() {
    private lateinit var itemList: ArrayList<Item>
    private lateinit var adapter: MyCustomAdapter
    val avatar = arrayOf(R.drawable.avatar, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        val name  = arrayOf("Nguyễn Minh Anh", "Lê Thanh Huy", "Phạm Gia Hân", "Trần Bảo Ngọc", "Đỗ Trung Hiếu",
            "Vũ Thị Mai", "Phan Quang Vinh", "Bùi Ngọc Lan", "Trịnh Minh Khang", "Lý Thùy Trang",
            "Nguyễn Hải Đăng", "Phạm Thị Thanh Hằng", "Dương Quang Minh", "Lê Bảo Châu", "Hồ Ngọc Quỳnh",
            "Tạ Đức Phúc", "Phạm Khánh Linh", "Nguyễn Hồng Sơn", "Lương Phương Anh", "Đặng Xuân Trường",
            "Trần Thị Bích Hồng", "Phan Minh Tuấn", "Lê Vân Anh", "Trần Thanh Tâm", "Phạm Thái Bình",
            "Nguyễn Mỹ Duyên", "Đỗ Hoàng Phúc")

        itemList = arrayListOf()
        for (i in 1..27) {
            if (i < 10) {
                itemList.add(Item(name[i-1], "2022000$i", avatar[Random.nextInt(0, 3)]))
            } else {
                itemList.add(Item(name[i-1], "202200$i", avatar[Random.nextInt(0, 3)]))
            }
        }
        adapter = MyCustomAdapter(itemList, this)
        val listView = findViewById<ListView>(R.id.listview)
        listView.adapter = adapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnAddItem = findViewById<Button>(R.id.AddButton)
        btnAddItem.setOnClickListener {
            val intent = Intent(/* packageContext = */ this,  /* cls = */ AddActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val name = data?.getStringExtra("name")
            val mssv = data?.getStringExtra("mssv")
            name?.let {
                mssv?.let {
                    val existingItemIndex = itemList.indexOfFirst { it.mssv == mssv }
                    if (existingItemIndex != -1) {

                        itemList[existingItemIndex] = Item(
                            name,
                            mssv,
                            avatar[Random.nextInt(0, 3)]
                        )}else {
                            itemList.add(
                                Item(
                                    name,
                                    mssv,
                                    avatar[Random.nextInt(0, 3)]
                                )
                            )

                            adapter.updateList(itemList)
                        }
                }
            }
        }
        else if (requestCode == 200 && resultCode == RESULT_OK){
            val name = data?.getStringExtra("name")
            val mssv = data?.getStringExtra("mssv")
            val index = data?.getIntExtra("index", -1)
            name?.let {
                mssv?.let {
                    index?.let {
                        if (index != -1) {
                            itemList[index].username = name;
                            itemList[index].mssv = mssv;
                            adapter.updateList(itemList);
                        }
                    }
                }
            }
        }
    }

    private fun filterList(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            itemList
        } else {
            itemList.filter {
                it.username.contains(query, ignoreCase = true) || it.mssv.contains(query, ignoreCase = true)
            } as ArrayList<Item>
        }
        adapter.updateList(filteredList)
    }


}




