package com.callbackinterfaces.student_database_app

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: StudentEntity?)

    @Update
    fun updateStudent(student: StudentEntity?)

    @Delete
    fun deleteStudent(student: StudentEntity?)

    @Query("DELETE FROM student_table")
    fun deleteAllStudents()

    @get:Query("SELECT * FROM student_table")
    val allStudents: LiveData<List<StudentEntity?>?>?
}