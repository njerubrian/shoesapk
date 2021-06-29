package com.example.shoesapk

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class shoesDatabase(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABSE_VERSION){

    // define the companion variable.
    companion object{
        private val DATABSE_VERSION = 1
        private val DATABASE_NAME = "Selers_Databse"
        private val TABLE_SELLERS = "sellers_table"
        private val KEY_ID = "id"
        private val KEY_PRICE ="price"
        private val KEY_BRAND = "brand"
        private val KEY_CONTACT= "contacts"
        private val KEY_NAME= "name"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        // define the query to create our table
        val CREATE_SELLER_DETAILS = ("CREATE TABLE IF NOT EXISTS" + TABLE_SELLERS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTO INCREMENT,"
                + KEY_BRAND + " TEXT," + KEY_PRICE + "INTEGER," + KEY_CONTACT + " INTEGER," + KEY_NAME + " TEXT" + ")")
        db?.execSQL(CREATE_SELLER_DETAILS)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_SELLERS)
        onCreate(db)
    }

    // saving data into the database
    fun addseller(sqliteModel: sqliteModel):Long{
        // telling the db what to do

        val db = this.writableDatabase
        //define and place our values into the table
        val contentvalue = ContentValues()
        //put data to respective fields.
        contentvalue.put(KEY_CONTACT,sqliteModel.phoneContact)
        contentvalue.put(KEY_NAME,sqliteModel.comtactName)
        contentvalue.put(KEY_BRAND,sqliteModel.shoeBrand)
        contentvalue.put(KEY_PRICE,sqliteModel.retailPrice)
        contentvalue.put(KEY_ID,sqliteModel.sellerId)

        //QUERY TO INSERT TO DB
        val success = db.insert(TABLE_SELLERS, null,contentvalue)
        //closing the database
        db.close()

        //return output
        return success




    }

}