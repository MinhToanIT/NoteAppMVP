package com.toannm.noteappmvp.interfaces;

import com.toannm.noteappmvp.model.entity.Note;

import java.util.ArrayList;

/**
 * Created by Minh Toan on 15/03/2018.
 */

public interface OnTaskFinishListener {
    void onStartTask();

    void onFinishTask();

    void onGetDataSuccess(ArrayList<Note> noteList);

    void onGetDataFail();

    void onAddNoteSuccess();

    void onAddNoteFail();

    void onDeleteNoteSuccess();

    void onDeleteNoteFail();

    void onUpdateNoteSuccess();

    void onUpdateNoteFail();

}
