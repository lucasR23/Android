package com.longynuss.homeworkapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inpText;
    private SQLiteDatabase dataBase;
    private ListView list;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button btn;
        btn = findViewById(R.id.button);

        inpText = findViewById(R.id.editText);
        View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
        inpText.setOnFocusChangeListener(ofcListener);

        list = findViewById(R.id.list);

        dataBase = openOrCreateDatabase("app",MODE_PRIVATE,null);
        dataBase.execSQL("CREATE TABLE IF NOT EXISTS work(id INTEGER PRIMARY KEY AUTOINCREMENT,text VARCHAR)");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inpText.getText().toString();

                SaveItem(input);
            }
        });

        list.setLongClickable(true);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeNote(ids.get(i));

                return true;
            }
        });

        TakeitInformation();
    }

    private void removeNote(Integer id) {
        try{
            dataBase.execSQL("DELETE FROM work WHERE id="+id);
            TakeitInformation();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void TakeitInformation() {
        ArrayAdapter<String> adp;
        ArrayList<String> itens;

        Cursor cursor = dataBase.rawQuery("SELECT * FROM work ORDER BY id DESC",null);

        int idIndex = cursor.getColumnIndex("id");
        int textIndex = cursor.getColumnIndex("text");

        cursor.moveToFirst();

        itens = new ArrayList<>();
        ids = new ArrayList<>();
        adp = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens);
        list.setAdapter(adp);

        while(!cursor.isAfterLast()) {
            itens.add(cursor.getString(textIndex));
            ids.add(Integer.parseInt(cursor.getString(idIndex)));
            cursor.moveToNext();
        }

        cursor.close();
    }

    private void SaveItem(String input) {
        try {
            if (!input.equals("")) {
                dataBase.execSQL("INSERT INTO work(text) VALUES ('"+input+"')");
                TakeitInformation();
                inpText.setText("");
                inpText.clearFocus();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyFocusChangeListener implements View.OnFocusChangeListener {
        public void onFocusChange(View v, boolean hasFocus){
            try{
                if(v!= null && !hasFocus) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
