package com.example.noteapp.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

     @Query("SELECT * FROM note")
     List<Note> getAll();

     @Insert()
     void insert(Note note);

     @Delete
     void delete(Note note);

     @Update
     void update(Note note);

//     @Query("UPDATE text WHERE name = {$note.name}")
//     void updateNote(Note note);
}
