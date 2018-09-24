package com.example.abacus.roomdatabase.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.abacus.roomdatabase.db.dao.NoteDao;
import com.example.abacus.roomdatabase.db.entity.NoteEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NotesRepository {

    private NoteDao mNoteDao;
    private LiveData<List<NoteEntity>> mAllNotes;


    public NotesRepository(Application application)
    {
        AppDatabase db = AppDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();


    }

    public LiveData<List<NoteEntity>> getmAllNotes() {
        return mAllNotes;
    }

    public NoteEntity getNote(int noteId) throws ExecutionException, InterruptedException
    {

        return new getNoteAsync(mNoteDao).execute(noteId).get();
    }

    public void insertNote(NoteEntity note)
    {
        new insertNotesAsync(mNoteDao).execute(note);
    }

    public void updateNote(NoteEntity note)
    {
        new updateNotesAsync(mNoteDao).execute(note);
    }

    public void deleteNote(NoteEntity note)
    {
        new deleteNotesAsync(mNoteDao).execute(note);
    }

    public void deleteAllNotes()
    {
        new deleteAllNotesAsync(mNoteDao).execute();
    }


    private static class getNoteAsync extends AsyncTask<Integer,Void,NoteEntity>
    {
        private NoteDao mNoteDaoAsync;

        getNoteAsync(NoteDao noteDao)
        {
            mNoteDaoAsync = noteDao;
        }


        @Override
        protected NoteEntity doInBackground(Integer... integers) {
            return mNoteDaoAsync.getNoteById(integers[0]);

        }
    }

    private static class insertNotesAsync extends AsyncTask<NoteEntity,Void,Long>
    {
        private NoteDao mNoteDaoAsync;

        insertNotesAsync(NoteDao noteDao)
        {
            mNoteDaoAsync = noteDao;
        }


        @Override
        protected Long doInBackground(NoteEntity... integers) {

            long id = mNoteDaoAsync.insert(integers[0]);
            return id;
        }
    }

    private static class updateNotesAsync extends AsyncTask<NoteEntity,Void,Void>
    {
        private NoteDao mNoteDaoAsync;

        updateNotesAsync(NoteDao noteDao)
        {
            mNoteDaoAsync = noteDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            mNoteDaoAsync.update(noteEntities[0]);
            return null;
        }
    }

    private static class deleteNotesAsync extends AsyncTask<NoteEntity,Void,Void>
    {
        private NoteDao mNoteDaoAsync;

        deleteNotesAsync(NoteDao noteDao)
        {
            mNoteDaoAsync = noteDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            mNoteDaoAsync.delete(noteEntities[0]);
            return null;
        }
    }

    private static class deleteAllNotesAsync extends AsyncTask<NoteEntity,Void,Void>
    {
        private NoteDao mNoteDaoAsync;

        deleteAllNotesAsync(NoteDao noteDao)
        {
            mNoteDaoAsync = noteDao;
        }


        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            mNoteDaoAsync.deleteAll();
            return null;
        }
    }
}
