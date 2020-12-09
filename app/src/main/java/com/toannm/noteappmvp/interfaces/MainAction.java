package com.toannm.noteappmvp.interfaces;

import com.toannm.noteappmvp.model.entity.Note;

/**
 * Created by Minh Toan on 14/03/2018.
 */

public interface MainAction {
    void getData();

    void addNote(Note note);

    void updateNote(Note note);

    void deleteNote();
}
