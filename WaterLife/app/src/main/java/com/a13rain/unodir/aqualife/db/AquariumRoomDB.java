package com.a13rain.unodir.aqualife.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.a13rain.unodir.aqualife.utils.Converters;

@Database(entities = {Aquarium.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AquariumRoomDB extends RoomDatabase {
    public abstract AquariumDao aquariumDao();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    private static AquariumRoomDB INSTANCE;

    public static AquariumRoomDB getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AquariumRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AquariumRoomDB.class, "aquarium").build();
                }
            }
        }

        return INSTANCE;
    }
}
