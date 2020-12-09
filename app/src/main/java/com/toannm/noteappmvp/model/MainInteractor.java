package com.toannm.noteappmvp.model;

import android.os.AsyncTask;

import com.toannm.noteappmvp.interfaces.MainAction;
import com.toannm.noteappmvp.interfaces.OnTaskFinishListener;
import com.toannm.noteappmvp.model.entity.Note;

import java.util.ArrayList;

/**
 * Created by Minh Toan on 14/03/2018.
 */

public class MainInteractor implements MainAction {
    private DBManager dbManager;
    private OnTaskFinishListener onTaskFinishListener;
    private int currentPosition;
    private Note currentNote;

    public MainInteractor(DBManager dbManager, OnTaskFinishListener onTaskFinishListener) {
        this.dbManager = dbManager;
        this.onTaskFinishListener = onTaskFinishListener;
    }

    @Override
    public void getData() {
        class GetDataTask extends AsyncTask<Void, Void, ArrayList<Note>> {
            @Override
            protected void onPreExecute() {
                onTaskFinishListener.onStartTask();
                super.onPreExecute();
            }

            @Override
            protected ArrayList<Note> doInBackground(Void... voids) {
                delay();
                return dbManager.getNoteListFromDatabase();
            }

            @Override
            protected void onPostExecute(ArrayList<Note> noteArrayList) {
                onTaskFinishListener.onFinishTask();
                if (noteArrayList.size() != 0) {
                    onTaskFinishListener.onGetDataSuccess(noteArrayList);
                } else {
                    onTaskFinishListener.onGetDataFail();
                }
                super.onPostExecute(noteArrayList);
            }
        }
        new GetDataTask().execute();
    }

    @Override
    public void addNote(final Note note) {
        class AddNoteTask extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected void onPreExecute() {
                onTaskFinishListener.onStartTask();
                currentNote = note;
                super.onPreExecute();
            }

            @Override
            protected Boolean doInBackground(Void... voids) {
                delay();
                return dbManager.addNote(note);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    onTaskFinishListener.onAddNoteSuccess();
                } else {
                    onTaskFinishListener.onAddNoteFail();
                }
                onTaskFinishListener.onFinishTask();
                super.onPostExecute(aBoolean);
            }
        }
        new AddNoteTask().execute();
    }

    @Override
    public void updateNote(final Note note) {
        class UpDateNoteTask extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected void onPreExecute() {
                onTaskFinishListener.onStartTask();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    currentNote.setColor(note.getColor());
                    currentNote.setContent(note.getContent());
                    currentNote.setDate(note.getDate());
                    onTaskFinishListener.onUpdateNoteSuccess();
                } else {
                    onTaskFinishListener.onUpdateNoteFail();
                }
                onTaskFinishListener.onFinishTask();
                super.onPostExecute(aBoolean);
            }

            @Override
            protected Boolean doInBackground(Void... voids) {
                delay();
                note.setId(currentNote.getId());
                return dbManager.updateNote(note);
            }
        }
        new UpDateNoteTask().execute();
    }

    @Override
    public void deleteNote() {
        class DeleteNoteTask extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected void onPreExecute() {
                onTaskFinishListener.onStartTask();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    onTaskFinishListener.onDeleteNoteSuccess();
                } else {
                    onTaskFinishListener.onDeleteNoteFail();
                }
                onTaskFinishListener.onFinishTask();
                super.onPostExecute(aBoolean);
            }

            @Override
            protected Boolean doInBackground(Void... voids) {
                delay();
                return dbManager.deleteNote(currentNote);
            }
        }
        new DeleteNoteTask().execute();
    }

    private void delay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentNote(int position) {
        this.currentNote = dbManager.getNoteAt(position);
    }

    public Note getNoteAt(int position) {
        return dbManager.getNoteAt(position);
    }
}
