package com.example.notesapp.general

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.general.MyApplication
import com.example.notesapp.note.data.model.dataroom.NoteDao
import com.example.notesapp.note.data.model.dataroom.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabaseClient(): AppDataBase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this){
                INSTANCE = Room
                    .databaseBuilder(
                        MyApplication.getInstance().applicationContext ,
                        AppDataBase::class.java,
                        "AppDataBase").
                    fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}