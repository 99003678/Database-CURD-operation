package com.callbackinterfaces.student_database_app

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [StudentEntity::class], version = 1)
abstract class StudentDB : RoomDatabase() {
    abstract val studentDao: StudentDao

    private class PopulateDBAsyncTask(database: StudentDB?) : AsyncTask<Void?, Void?, Void?>() {
        private val studentDao: StudentDao
        protected override fun doInBackground(vararg params: Void?): Void? {
            studentDao.insertStudent(StudentEntity("Abhishek", "0001", "LTTS-Android Studio"))
            studentDao.insertStudent(StudentEntity("Jai", "0002", "LTTS-Android Studio"))
            studentDao.insertStudent(StudentEntity("Vinayak", "0003", "LTTS-Android Studio"))
            studentDao.insertStudent(StudentEntity("Roshan", "0004", "LTTS-Android Studio"))
            studentDao.insertStudent(StudentEntity("Ansari", "0005", "LTTS-Android Studio"))
            return null
        }

        init {
            studentDao = database!!.studentDao
        }

    }

    companion object {
        private var database: StudentDB? = null
        @Synchronized
        fun retrieveInstance(context: Context?): StudentDB? {
            if (database == null) {
                database = Room.databaseBuilder(context!!, StudentDB::class.java, "student_database")
                        .addCallback(callback)
                        .fallbackToDestructiveMigrationFrom()
                        .build()
            }
            return database
        }

        private val callback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDBAsyncTask(database).execute()
            }
        }
    }
}