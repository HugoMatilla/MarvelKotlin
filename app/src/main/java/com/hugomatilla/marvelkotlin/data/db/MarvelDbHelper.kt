package com.hugomatilla.marvelkotlin.data.db

/**
 * Created by hugomatilla on 22/02/16.
 */

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hugomatilla.marvelkotlin.ui.App
import org.jetbrains.anko.db.*

class MarvelDbHelper(ctx: Context = App.instance) :
        ManagedSQLiteOpenHelper(ctx, MarvelDbHelper.DB_NAME, null, MarvelDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "marvel-kotlin.db"
        val DB_VERSION = 2
        val instance by lazy { MarvelDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(HeroTable.TABLE_NAME, true,
                HeroTable.ID to INTEGER + PRIMARY_KEY,
                HeroTable.NAME to TEXT,
                HeroTable.DESCRIPTION to TEXT,
                HeroTable.IMAGE_URL to TEXT,
                HeroTable.EXTERNAL_URL to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(HeroTable.TABLE_NAME, true)
        onCreate(db)
    }
}
