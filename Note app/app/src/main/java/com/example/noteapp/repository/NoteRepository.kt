package com.example.noteapp.repository

import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.model.Note

class NoteRepository (private val db: NoteDatabase){

    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)


    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)


}