package com.issa.anime.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnimeDao {

    @Query("SELECT * FROM FavAnime ORDER BY id")
    LiveData<List<FavoriteAnime>> loadAllAnime();

    @Insert
    void insertAnime(FavoriteAnime favoriteAnime);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAnime(FavoriteAnime favoriteAnime);

    @Delete
    void deleteAnime(FavoriteAnime favoriteAnime);


}
