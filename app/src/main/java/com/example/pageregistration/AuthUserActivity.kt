package com.example.pageregistration

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AuthUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_authorisation_user)
        val userLogin: EditText =findViewById(R.id.userLoginAuth)
        val userPass: EditText =findViewById(R.id.userPassAuth)
        val buttonReg: Button =findViewById(R.id.buttonRegAuth)
        val linkToReg:TextView=findViewById(R.id.linkToReg)
        linkToReg.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        buttonReg.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if(login==""||pass=="") {
                Toast.makeText(this, "Заполните недостатоющие поля", Toast.LENGTH_LONG).show()
                }
            else{
                 val db = DbHalper(this,null)
                 val isAuth=db.getUser(login,pass)
                 if(isAuth)
                     {
                     Toast.makeText(this,"Пользователь $login авторизован",Toast.LENGTH_LONG).show()
                     userLogin.text.clear()
                     userPass.text.clear()
                     }
                 else
                      {
                      Toast.makeText(this,"Пользователь $login НЕ авторизован",Toast.LENGTH_LONG).show()
                      }

                 }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
