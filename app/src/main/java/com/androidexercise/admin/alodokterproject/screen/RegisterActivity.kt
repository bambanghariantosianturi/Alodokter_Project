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
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private var regShared = false
    private val dbHelper = DataHelper(this,null, null, 1)
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE)

        btnRegister.setOnClickListener(this)
        tvrRegister.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (btnRegister == p0) {

            val nama = edtNama.text.toString()
            val email = edtEmailReg.text.toString()
            val pass = edtPassReg.text.toString()
            val tyPass = edtTypePassReg.text.toString()

            validasiInput(nama, email, pass, tyPass)

        } else if (tvrRegister == p0) {

            val itn = Intent(this, LoginActivity::class.java)
            startActivity(itn)
            finish()

        }
    }

    private fun validasiInput(nama: String, email: String, pass: String, tyPass: String) {
        var valid = true

        if (nama.isEmpty() && email.isEmpty() && pass.isEmpty() && tyPass.isEmpty()) {

            edtNama.error = "Tidak boleh kosong"
            edtEmailReg.error = "Tidak boleh kosong"
            edtPassReg.error = "Tidak boleh kosong"
            edtTypePassReg.error = "Tidak boleh kosong"
            edtNama.requestFocus()

        } else if (nama.isEmpty() || nama == " ") {

            edtNama.error = "Tidak boleh kosong"
            edtNama.requestFocus()
            valid = false

        } else if (email.isEmpty() || email == " ") {

            edtEmailReg.error = "Tidak boleh kosong"
            edtEmailReg.requestFocus()
            valid = false

        } else if (pass.isEmpty() || pass == " ") {

            edtPassReg.error = "Tidak boleh kosong"
            edtPassReg.requestFocus()
            valid = false

        } else if (tyPass.isEmpty() || tyPass == " ") {

            edtTypePassReg.error = "Tidak boleh kosong"
            edtTypePassReg.requestFocus()
            valid = false

        } else if (pass == tyPass) {
            insertLocalDB(nama, email, pass)
        } else {
            Toast.makeText(this, "Type Password Anda tidak sama", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertLocalDB(nama: String, email: String, pass: String) {

        val db = dbHelper.writableDatabase
        db.execSQL(
            "INSERT INTO tUser(name,email,password) " +
    "VALUES('" + nama + "','" + email + "','" + pass + "');"
        )

        Toast.makeText(this, "Behasil menyimpan profile", Toast.LENGTH_SHORT).show()
        val itn = Intent(this, LoginActivity::class.java)
        startActivity(itn)
        finish()
    }

    override fun onResume() {
        super.onResume()

        regShared = sharedPreferences.getBoolean(Config.REGISTER_SHARED_PREF, false)
        if (regShared) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
