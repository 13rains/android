package com.a13rain.unodir.aqualife.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class AquaDB extends SQLiteOpenHelper {

    static final String strCreateQuery = "CREATE TABLE [aquarium] (" +
            "[idx] INTEGER  PRIMARY KEY AUTOINCREMENT NULL," +
            "[name] TEXT  NOT NULL," +
            "[date] timestamp DEFAULT CURRENT_TIMESTAMP NULL," +
            "[size_t] INTEGER DEFAULT '0' NULL," +
            "[x] INTEGER DEFAULT '30' NULL," +
            "[y] INTEGER DEFAULT '30' NULL," +
            "[Z] INTEGER DEFAULT '30' NULL," +
            "[summary] TEXT  NULL," +
            "[sumnail] BLOB  NULL" +
            ");";

    public AquaDB(@Nullable Context context) {
        super(context, "aqualife", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(strCreateQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
