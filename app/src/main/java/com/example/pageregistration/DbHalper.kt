package com.example.pageregistration
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHalper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "appDb", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                login TEXT NOT NULL,
                email TEXT NOT NULL,
                password TEXT NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users ")
        onCreate(db)

    }

    fun addUser(user: User){
        val values =ContentValues()
        values.put("login",user.login)
        values.put("email",user.email)
        values.put("pass",user.pass)
        val db =this.writableDatabase
        db.insert("users",null,values)
        db.close()
       }



    fun getUser(login: String, pass: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE login = ? AND password = ?", arrayOf(login, pass))
        val userExists = cursor.moveToFirst()
        cursor.close()  // Ensure cursor is closed after usage
        return userExists
    }



}