package com.example.trojan.kotlin_crudfirebase.CRUD

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trojan.kotlin_crudfirebase.Model.Users
import com.example.trojan.kotlin_crudfirebase.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create.*

class Create : AppCompatActivity() {

    lateinit var ref : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        ref = FirebaseDatabase.getInstance().getReference("USERS")


        btnSave.setOnClickListener {
            savedata()
        }
    }

    private fun savedata(){

        val nama = inputNama.text.toString()
        val status = inputStatus.text.toString()

        val userId = ref.push().key.toString()
        val user = Users(userId,nama,status)

        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            inputNama.setText("")
            inputStatus.setText("")
        }

        val intent = Intent (this, Read::class.java)
        startActivity(intent)

    }
}
