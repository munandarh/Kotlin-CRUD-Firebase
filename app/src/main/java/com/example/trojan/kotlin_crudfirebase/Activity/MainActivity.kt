package com.example.trojan.kotlin_crudfirebase.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.trojan.kotlin_crudfirebase.CRUD.Create
import com.example.trojan.kotlin_crudfirebase.CRUD.Read
import com.example.trojan.kotlin_crudfirebase.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClickLestener()

    }

    private fun onClickLestener(){

        btnLogin.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            val progressDialog = ProgressDialog(this,
                R.style.Theme_MaterialComponents_Light_Dialog
            )



            if (email.isEmpty()|| password.isEmpty()) {
                Toast.makeText(this, "Please Insert Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{

                    if (!it.isSuccessful){ return@addOnCompleteListener
                        val intent = Intent (this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else
                        Toast.makeText(this, "Succesfully Login", Toast.LENGTH_SHORT).show()
                    progressDialog.isIndeterminate = true
                    progressDialog.setMessage("Loading...")
                    progressDialog.show()

                    val intent = Intent (this, Read::class.java)
                    startActivity(intent)

                }
                .addOnFailureListener{
                    Log.d("Main", "Failed Login: ${it.message}")
                    Toast.makeText(this, "Email/Password incorrect", Toast.LENGTH_SHORT).show()
                    progressDialog.hide()

                }
        }

    }

}
