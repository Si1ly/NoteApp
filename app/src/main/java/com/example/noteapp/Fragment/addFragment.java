package com.example.noteapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.noteapp.Data.Note;
import com.example.noteapp.Data.NoteDatabase;
import com.example.noteapp.IDataSend;
import com.example.noteapp.MainActivity;
import com.example.noteapp.R;


public class addFragment extends DialogFragment {
    private EditText edt_name;
    private EditText edt_note;
    private MainActivity mainActivity;
    private IDataSend iDataSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edt_name = view.findViewById(R.id.name_cs);
        edt_note = view.findViewById(R.id.note_cs);
        Button add_bt = view.findViewById(R.id.add_bt);
//        String name = edt_name.getText().toString();
//        String text = edt_note.getText().toString();
//        Note note = new Note(name,text);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("NOTE_VALUE",note);
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDatatoMain();
                saveDatatoRoom();
                dismiss();
            }
        });
    }

    private void sendDatatoMain() {
        String name = edt_name.getText().toString();
        String text = edt_note.getText().toString();
        Note noteSend = new Note(name,text);
        iDataSend = mainActivity;
        iDataSend.sendData(noteSend);
    }

    private void saveDatatoRoom() {
        String name = edt_name.getText().toString();
        String text = edt_note.getText().toString();
        Note noteSend = new Note(name,text);
        NoteDatabase.getInstance(getContext()).noteDAO().insert(noteSend);
    }

}