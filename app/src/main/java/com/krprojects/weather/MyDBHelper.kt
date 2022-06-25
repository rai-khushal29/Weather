package com.krprojects.weather

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(
    context: Context?,
    name: String? = "Weather",
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = 1
) : SQLiteOpenHelper(context, name, factory, version) {
    var TABLE_NAME: String = "Cities"
    var COLUMN_S_NO: String = "S_No"
    var COLUMN_NAME: String = "Name"
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("Create Table ${TABLE_NAME} (${COLUMN_S_NO} Integer Primary Key autoincrement , ${COLUMN_NAME} text Not Null)")
    }

    fun addData(dataModel: CitiesModel) {
        val db : SQLiteDatabase = writableDatabase
        val cv = ContentValues()
        cv.apply {
            put(COLUMN_NAME, dataModel.name)
        }
        db.insert(TABLE_NAME, null, cv)
    }

    fun fetchData(): ArrayList<CitiesModel> {
        val db: SQLiteDatabase = readableDatabase
        val cursor: Cursor = db.rawQuery("Select * from ${TABLE_NAME}", null)
        val arrData: ArrayList<CitiesModel> = ArrayList()
        while (cursor.moveToNext()) {
           arrData.add(CitiesModel(cursor.getString(1)))
        }
        return arrData
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}