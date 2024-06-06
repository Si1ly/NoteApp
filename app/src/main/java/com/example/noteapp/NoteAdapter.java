package com.example.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.Data.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    List<Note> listNote;
    Context context;

    public NoteAdapter(List<Note> listNote, Context context) {
        this.listNote = listNote;
        this.context = context;
    }

    public void setData(List<Note> list){
        this.listNote = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listNote.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView name,note;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_note,null);
            viewHolder.name= convertView.findViewById(R.id.name_note);
            viewHolder.note = convertView.findViewById(R.id.misstion_note);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.name.setText(listNote.get(position).getName());
        viewHolder.note.setText(listNote.get(position).getNote());

        return convertView;

    }
}
