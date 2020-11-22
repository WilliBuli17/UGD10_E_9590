package com.styephenwilliambuli.ugd10_e_9590.databse;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private static DatabaseClient databaseClient;
    private final AppDatabase database;

    private DatabaseClient(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, "mahasiswa").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if(databaseClient == null) {
            databaseClient = new DatabaseClient(context);
        }

        return databaseClient;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
