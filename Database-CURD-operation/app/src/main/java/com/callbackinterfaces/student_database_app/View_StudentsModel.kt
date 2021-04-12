package com.callbackinterfaces.student_database_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class View_StudentsModel(application: Application) : AndroidViewModel(application) {
    private val repo: Repository
    val allStudents: LiveData<List<StudentEntity?>?>?
    fun insert(student: StudentEntity?) {
        repo.insert(student)
    }

    fun delete(student: StudentEntity?) {
        repo.delete(student)
    }

    fun update(student: StudentEntity?) {
        repo.delete(student)
    }

    fun deleteAll() {
        repo.deleteAll()
    }

    init {
        repo = Repository(application)
        allStudents = repo.allStudents
    }
}