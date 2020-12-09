package com.toannm.noteappmvp.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.toannm.noteappmvp.R;
import com.toannm.noteappmvp.model.entity.Note;
import com.toannm.noteappmvp.view.base.BaseDialog;

/**
 * Created by Minh Toan on 14/03/2018.
 */

public class ViewNoteDialog extends BaseDialog {

    private LinearLayout lnItem;
    private ImageButton btnClose;

    public ViewNoteDialog(Context context) {
        super(context);
        setContentView(R.layout.view_note_dialog);

        lnItem = findViewById(R.id.ln_item);

        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
        tvContent.setMovementMethod(new ScrollingMovementMethod());

        btnClose = findViewById(R.id.btn_close);

        btnClose.setOnClickListener(this);
    }

    public void setContent(Note note) {
        tvTitle.setText(note.getDate());
        tvContent.setText(note.getContent());
        lnItem.setBackgroundColor(Color.parseColor(note.getColor()));

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}