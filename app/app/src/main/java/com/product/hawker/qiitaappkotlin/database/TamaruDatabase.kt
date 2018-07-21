package com.product.hawker.qiitaappkotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "TamaruDatabase"
private const val DB_VERSION = 1

class TamaruDatabaseOpenHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null , DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        // -------------------
        // テーブルの作成
        // -------------------

        // 質問テーブル
        db?.execSQL("""
            CREATE TABLE QUESTION (
            _id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            delete_flag INTEGER NOT NULL DEFAULT 0,
            created_at TIMESTAMP DEFAULT (DATETIME('now','localtime')),
            updated_at TIMESTAMP DEFAULT (DATETIME('now','localtime')))
        """.trimIndent())

        db?.execSQL("INSERT INTO QUESTION(title) VALUES('質問テーブルのレコードその壱')")


        db?.execSQL("""
            CREATE TRIGGER Question_update_UpdatedAt_Trigger
            AFTER UPDATE On QUESTION
            BEGIN
               UPDATE QUESTION SET updated_at = STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW') WHERE id = NEW.id;
            END;
        """.trimIndent())

    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        // バージョン更新時のSQL発行


    }

}