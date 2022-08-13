package com.example.tubes10119089;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailCatatan extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    Button btn_save;
    TextView title,description,tgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catatan);
        database = new Database(this);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        tgl = findViewById(R.id.tgl);
        btn_save = findViewById(R.id.btn_simpan);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM notes WHERE title = '" +
                getIntent().getStringExtra("title") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            title.setText(cursor.getString(0).toString());
            description.setText(cursor.getString(1).toString());
            tgl.setText(cursor.getString(2).toString());

        }
    }
}