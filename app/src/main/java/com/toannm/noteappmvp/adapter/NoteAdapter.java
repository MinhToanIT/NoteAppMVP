package com.toannm.noteappmvp.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toannm.noteappmvp.R;
import com.toannm.noteappmvp.model.entity.Note;
import com.toannm.noteappmvp.presenter.MainPresenter;

import java.util.ArrayList;

/**
 * Created by Minh Toan on 14/03/2018.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private Activity context;
    private ArrayList<Note> noteList = new ArrayList<>();
    private LayoutInflater inflater;
    private MainPresenter mainPresenter;

    public NoteAdapter(Activity context, MainPresenter mainPresenter) {
        this.context = context;
        this.mainPresenter = mainPresenter;
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.lnItem.setBackgroundColor(Color.parseColor(noteList.get(position).getColor()));
        holder.date.setText(noteList.get(position).getDate());
        holder.content.setText(noteList.get(position).getContent());
        holder.date.setText(noteList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        if (noteList != null) {
            return noteList.size();
        }
        return 0;
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lnItem;
        private TextView date;
        private TextView content;
        private ImageView delete;
        private ImageView edit;

        public MyViewHolder(View itemView) {
            super(itemView);
            lnItem = (LinearLayout) itemView.findViewById(R.id.ln_item);
            date = (TextView) itemView.findViewById(R.id.txt_date);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            delete = (ImageView) itemView.findViewById(R.id.btn_delete);
            edit = (ImageView) itemView.findViewById(R.id.btn_edit);

            lnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainPresenter.onClickNoteAt(getAdapterPosition());
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainPresenter.onDeleteNoteAt(getAdapterPosition());
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainPresenter.onUpdateNoteAt(getAdapterPosition());
                }
            });
        }
    }
}
