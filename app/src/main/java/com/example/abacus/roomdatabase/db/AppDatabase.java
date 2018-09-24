package com.example.abacus.roomdatabase.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.abacus.roomdatabase.db.converter.DateConverter;
import com.example.abacus.roomdatabase.db.dao.NoteDao;
import com.example.abacus.roomdatabase.db.entity.NoteEntity;

@Database(entities = {NoteEntity.class},version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static AppDatabase INSTANCE;


    static AppDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (AppDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"notes_database").build();
                }
            }
        }

        return INSTANCE;
    }


}
