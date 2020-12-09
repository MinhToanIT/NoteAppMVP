package com.toannm.noteappmvp.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.toannm.noteappmvp.R;
import com.toannm.noteappmvp.presenter.MainPresenter;
import com.toannm.noteappmvp.util.Util;
import com.toannm.noteappmvp.view.base.BaseDialog;

/**
 * Created by Minh Toan on 14/03/2018.
 */

public class ConfirmDialog extends BaseDialog {
    private MainPresenter mainPresenter;
    public ConfirmDialog(@NonNull Context context, MainPresenter mainPresenter) {
        super(context);
        this.mainPresenter = mainPresenter;
        setContentView(R.layout.confirm_dialog);
        initViews();
        initControl();
    }

    private void initViews() {
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);

        tvTitle.setText(Util.getStringById(R.string.confirm));
        tvContent.setText(Util.getStringById(R.string.confirm_delete));

        btnOK = findViewById(R.id.btn_ok);
        btnCancel = findViewById(R.id.btn_close);
    }

    private void initData() {

    }

    private void initControl() {
        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_ok) {
            mainPresenter.deleteNote();
            dismiss();
        }
    }
}
