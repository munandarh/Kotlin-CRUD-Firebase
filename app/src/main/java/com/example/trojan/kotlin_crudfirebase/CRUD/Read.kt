package com.example.trojan.kotlin_crudfirebase.CRUD

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.example.trojan.kotlin_crudfirebase.Activity.MainActivity
import com.example.trojan.kotlin_crudfirebase.Adapter.Adapter
import com.example.trojan.kotlin_crudfirebase.Model.Users
import com.example.trojan.kotlin_crudfirebase.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_create.*

class Read : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Users>
    lateinit var listView: ListView
    lateinit var btnAddd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        ref = FirebaseDatabase.getInstance().getReference("USERS")
        list = mutableListOf()
        listView = findViewById(R.id.listView)
        btnAddd = findViewById(R.id.btnAdd)

        btnAddd.setOnClickListener {
            addNew()
        }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    list.clear()
                    for (h in p0.children){
                        val user = h.getValue(Users::class.java)
                        list.add(user!!)
                    }
                    val adapter = Adapter(this@Read,R.layout.activity_users,list)
                    listView.adapter = adapter
                }
            }


        })
    }

    private fun addNew(){
        val intent = Intent (this@Read, Create::class.java)
        startActivity(intent)
    }
}
