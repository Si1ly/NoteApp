package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.noteapp.Data.Note;
import com.example.noteapp.Data.NoteDatabase;
import com.example.noteapp.Fragment.addFragment;
import com.example.noteapp.Fragment.updateFragment;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IDataSend {

    ListView lv_Note;
    MaterialButton bt_add;
    NoteAdapter noteAdapter;
    List<Note> list = new ArrayList<>();
    updateFragment update;
    addFragment add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_Note = findViewById(R.id.note_list);
        bt_add = findViewById(R.id.bt_add);

        if(list.isEmpty()){
            list.add(new Note("test","test"));
        }else{
            list = NoteDatabase.getInstance(getApplicationContext()).noteDAO().getAll();
        }

        noteAdapter = new NoteAdapter(list,getApplicationContext());
        lv_Note.setAdapter(noteAdapter);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add = new addFragment();
                add.show(getSupportFragmentManager(),"fragmentAdd");
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.commit();
            }
        });

        lv_Note.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                update = new updateFragment();
                update.show(getSupportFragmentManager(),"fragmentUpdate");
                Bundle bundle = new Bundle();
                bundle.putSerializable("NOTE_VALUE",list.get(i));
                update.setArguments(bundle);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.commit();
            }
        });
        lv_Note.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    Thread thread = new Thread(new Runnable() {
                        @Override public void run() {
                            NoteDatabase.getInstance(getApplicationContext()).noteDAO().delete(list.get(i));
                        }
                    });
                    thread.start();
                    if(thread.isAlive()){
                        loadData();
                    }
                }catch (Exception e){
                    e.getMessage();
                }
                Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        noteAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    private void loadData(){
        list = NoteDatabase.getInstance(getApplicationContext()).noteDAO().getAll();
        noteAdapter.setData(list);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void sendData(Note note) {
        list.add(note);
    }

}