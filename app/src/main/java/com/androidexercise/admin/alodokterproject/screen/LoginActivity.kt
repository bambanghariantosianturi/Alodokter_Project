package com.androidexercise.admin.alodokterproject.screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.androidexercise.admin.alodokterproject.R
import com.androidexercise.admin.alodokterproject.datalocal.DataHelper
import com.androidexercise.admin.alodokterproject.util.Config
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var stName = ""
    private var stEmail = ""
    private var stPass = ""

    private val dbHelper = DataHelper(this, null, null, 1)
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE)

        tvRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (tvRegister == p0){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        } else if (btnLogin == p0){
            val email = edtEmail.text.toString().trim()
            val pass = edtPassword.text.toString().trim()
            validateInput(email, pass)
        }
    }

    private fun validateInput(email: String, pass : String){

        if (email.isEmpty() || email == " "){
            edtEmail.error = "Tidak boleh kosong"
            edtPassword.error = "Tidak boleh kosong"
            edtEmail.requestFocus()
        } else if (pass.isEmpty() || pass == " "){
            edtPassword.error = "Tidak boleh kosong"
            edtPassword.requestFocus()
        } else{
            cekUser(email, pass)
        }
    }

    private fun cekUser(email: String, pass: String){
        val db = dbHelper.readableDatabase
        val cursor =
            db.rawQuery("SELECT * FROM tUser WHERE email = '$email' AND password = '$pass'", null)
        val arrayNama = arrayOfNulls<String>(cursor.count)
        cursor.moveToFirst()

        for (cc in 0 until cursor.count) {
            cursor.moveToPosition(cc)
            arrayNama[cc] = cursor.getString(0).toString()

            stName = cursor.getString(1).toString()
            stEmail = cursor.getString(2).toString()
            stPass = cursor.getString(3).toString()
        }

        if (stName != null) {

            val editor = sharedPreferences.edit()
            editor.putBoolean(Config.LOGIN_SHARED_PREF, true)
            editor.putBoolean(Config.REGISTER_SHARED_PREF, true)

            editor.putString(Config.NAME_USER_SHARED, stName)
            editor.putString(Config.EMAIL_USER_SHARED, stEmail)
            editor.commit()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Email atau Password Anda salah !!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        val loginShared = sharedPreferences.getBoolean(Config.LOGIN_SHARED_PREF, false)
        if (loginShared) {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
