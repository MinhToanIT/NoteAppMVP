package com.toannm.noteappmvp.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.toannm.noteappmvp.R;
import com.toannm.noteappmvp.adapter.NoteAdapter;
import com.toannm.noteappmvp.interfaces.MainView;
import com.toannm.noteappmvp.model.DBManager;
import com.toannm.noteappmvp.model.entity.Note;
import com.toannm.noteappmvp.presenter.MainPresenter;
import com.toannm.noteappmvp.util.Util;
import com.toannm.noteappmvp.view.dialog.ConfirmDialog;
import com.toannm.noteappmvp.view.dialog.ViewNoteDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final int ACTIVITY_CREATE_NOTE = 1001;
    public static final String NOTE_UPDATE = "NOTE_UPDATE";
    private static final int ACTIVITY_UPDATE_NOTE = 1002;
    private RecyclerView rcvNote;
    private ImageView btnAdd;
    private DBManager dbManager;
    private MainPresenter mainPresenter;
    private NoteAdapter noteAdapter;
    private ProgressDialog progressDialog;
    private ConfirmDialog confirmDialog;
    private ViewNoteDialog viewNoteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initComponents();
        initControl();
        mainPresenter.getData();
    }

    private void initViews() {
        rcvNote = (RecyclerView) findViewById(R.id.list_view_note);
        rcvNote.setHasFixedSize(true);
        rcvNote.setLayoutManager(new LinearLayoutManager(this));

        btnAdd = (ImageView) findViewById(R.id.btn_add);

    }

    private void initComponents() {
        dbManager = new DBManager();
        mainPresenter = new MainPresenter(this, dbManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(Util.getStringById(R.string.doi_teo));

        viewNoteDialog = new ViewNoteDialog(this);
        confirmDialog = new ConfirmDialog(this, mainPresenter);
        noteAdapter = new NoteAdapter(MainActivity.this, mainPresenter);
    }

    private void initControl() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.onClickAddNote();
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showNoteDialog(Note note) {
        viewNoteDialog.setContent(note);
        viewNoteDialog.show();
    }

    @Override
    public void showCreateNoteActivity() {
        startActivityForResult(new Intent(this, CreateNoteActivity.class), ACTIVITY_CREATE_NOTE);
    }

    @Override
    public void showUpdateNoteActivity(Note note) {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        intent.putExtra(NOTE_UPDATE, note);
        startActivityForResult(intent, ACTIVITY_UPDATE_NOTE);
    }

    @Override
    public void showConfirmDialog() {
        confirmDialog.show();
    }

    @Override
    public void showContent(ArrayList<Note> noteList) {
        noteAdapter.setData(noteList);
        rcvNote.setAdapter(noteAdapter);
    }

    @Override
    public void showMessageGetDataFail() {
        showToast(Util.getStringById(R.string.tai_du_lieu_that_bai));
    }

    @Override
    public void showMessageAddNoteSuccess() {
        showToast(Util.getStringById(R.string.them_moi_thanh_cong));
    }

    @Override
    public void showMessageAddNoteFail() {
        showToast(Util.getStringById(R.string.them_moi_that_bai));
    }

    @Override
    public void showMessageUpdateNoteSuccess() {
        showToast(Util.getStringById(R.string.cap_nhat_thanh_cong));
    }

    @Override
    public void showMessageUpdateNoteFail() {
        showToast(Util.getStringById(R.string.cap_nhat_that_bai));
    }

    @Override
    public void showMessageDeleteNoteSuccess() {
        showToast(Util.getStringById(R.string.xoa_thanh_cong));
    }

    @Override
    public void showMessageDeleteNoteFail() {
        showToast(Util.getStringById(R.string.xoa_that_bai));
    }

    @Override
    public void notifyDataInsert() {
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyDataUpdate(int position) {
        noteAdapter.notifyItemChanged(position);
    }

    @Override
    public void notifyDataDelete(int position) {
        noteAdapter.notifyItemRemoved(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_CREATE_NOTE) {
            if (resultCode == CreateNoteActivity.RESULT_OK) {
                Note note = (Note) data.getSerializableExtra(CreateNoteActivity.NOTE_DATA);
                mainPresenter.addNote(note);
            }
        }
        if (requestCode == ACTIVITY_UPDATE_NOTE) {
            Note note = (Note) data.getSerializableExtra(CreateNoteActivity.NOTE_DATA);
            mainPresenter.updateNote(note);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
