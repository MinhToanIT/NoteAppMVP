package com.toannm.noteappmvp.interfaces;

import com.toannm.noteappmvp.model.entity.Note;

import java.util.ArrayList;

/**
 * Created by Minh Toan on 15/03/2018.
 */

public interface MainView {
    void showProgressDialog();

    void hideProgressDialog();

    void showNoteDialog(Note note);

    void showCreateNoteActivity();

    void showUpdateNoteActivity(Note note);

    void showConfirmDialog();

    void showContent(ArrayList<Note> noteList);

    void showMessageGetDataFail();

    void showMessageAddNoteSuccess();

    void showMessageAddNoteFail();

    void showMessageUpdateNoteSuccess();

    void showMessageUpdateNoteFail();

    void showMessageDeleteNoteSuccess();

    void showMessageDeleteNoteFail();

    void notifyDataInsert();

    void notifyDataUpdate(int position);

    void notifyDataDelete(int position);
}
