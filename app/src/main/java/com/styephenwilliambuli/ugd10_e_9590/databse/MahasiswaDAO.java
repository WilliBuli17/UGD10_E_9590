package com.styephenwilliambuli.ugd10_e_9590.databse;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.styephenwilliambuli.ugd10_e_9590.model.Mahasiswa;

import java.util.List;

@Dao
public interface MahasiswaDAO {
    @Query(value = "SELECT * FROM mahasiswa")
    List<Mahasiswa> getAll();

    @Insert
    void insert(Mahasiswa mahasiswa);

    @Delete
    void delete(Mahasiswa mahasiswa);
}
