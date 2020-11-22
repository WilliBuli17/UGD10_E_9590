package com.styephenwilliambuli.ugd10_e_9590.databse;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.styephenwilliambuli.ugd10_e_9590.model.Mahasiswa;

@Database(entities = {Mahasiswa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MahasiswaDAO mahasiswaDAO();
}
