package com.toannm.noteappmvp.presenter;

import com.toannm.noteappmvp.interfaces.MainAction;
import com.toannm.noteappmvp.interfaces.OnTaskFinishListener;
import com.toannm.noteappmvp.model.DBManager;
import com.toannm.noteappmvp.model.MainInteractor;
import com.toannm.noteappmvp.model.entity.Note;
import com.toannm.noteappmvp.interfaces.MainView;

import java.util.ArrayList;

/**
 * Created by Minh Toan on 14/03/2018.
 */

public class MainPresenter implements MainAction, OnTaskFinishListener {
    private MainView mainView;
    private MainInteractor mainInteractor;
    private DBManager dbManager;

    public MainPresenter(MainView mainView, DBManager dbManager) {
        this.mainView = mainView;
        this.dbManager = dbManager;
        mainInteractor = new MainInteractor(dbManager, this);
    }

    @Override
    public void onStartTask() {
        mainView.showProgressDialog();
    }

    @Override
    public void onFinishTask() {
        mainView.hideProgressDialog();
    }

    @Override
    public void getData() {
        mainInteractor.getData();
    }

    @Override
    public void onGetDataSuccess(ArrayList<Note> noteList) {
        mainView.showContent(noteList);
    }

    @Override
    public void onGetDataFail() {
        mainView.showMessageGetDataFail();
    }

    public void onClickAddNote() {
        mainView.showCreateNoteActivity();
    }

    public void onClickNoteAt(int position) {
        mainView.showNoteDialog(mainInteractor.getNoteAt(position));
    }

    @Override
    public void addNote(Note note) {
        mainInteractor.addNote(note);
    }

    @Override
    public void onAddNoteSuccess() {
        mainView.notifyDataInsert();
        mainView.showMessageAddNoteSuccess();
    }

    @Override
    public void onAddNoteFail() {
        mainView.showMessageAddNoteFail();
    }

    public void onUpdateNoteAt(int position) {
        mainInteractor.setCurrentPosition(position);
        mainInteractor.setCurrentNote(position);
        mainView.showUpdateNoteActivity(mainInteractor.getNoteAt(position));
    }

    @Override
    public void updateNote(Note note) {
        mainInteractor.updateNote(note);
    }

    @Override
    public void onUpdateNoteSuccess() {
        mainView.notifyDataUpdate(mainInteractor.getCurrentPosition());
        mainView.showMessageUpdateNoteSuccess();
    }

    @Override
    public void onUpdateNoteFail() {
        mainView.showMessageUpdateNoteFail();
    }

    public void onDeleteNoteAt(int position) {
        mainInteractor.setCurrentPosition(position);
        mainInteractor.setCurrentNote(position);
        mainView.showConfirmDialog();
    }

    @Override
    public void deleteNote() {
        mainInteractor.deleteNote();
    }

    @Override
    public void onDeleteNoteSuccess() {
        mainView.notifyDataDelete(mainInteractor.getCurrentPosition());
        mainView.showMessageDeleteNoteSuccess();
    }

    @Override
    public void onDeleteNoteFail() {
        mainView.showMessageDeleteNoteFail();
    }
}
