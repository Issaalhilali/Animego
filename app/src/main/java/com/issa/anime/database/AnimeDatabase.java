package com.issa.anime.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteAnime.class}, version = 1, exportSchema = false)
public abstract class AnimeDatabase extends RoomDatabase {
    private static final String LOG_TAG = AnimeDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "animeslist";
    private static  AnimeDatabase sInstance;

    public static AnimeDatabase getInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AnimeDatabase.class, AnimeDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return sInstance;
    }
    public abstract AnimeDao animeDao();
}
