package com.example.tubes10119089;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UbahCatatan extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    Button btn_save;
    EditText title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_catatan);
        database = new Database(this);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        btn_save = findViewById(R.id.btn_simpan);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM notes WHERE title = '" +
                getIntent().getStringExtra("title") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            title.setText(cursor.getString(0).toString());
            description.setText(cursor.getString(1).toString());
        }
        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("UPDATE notes SET  title= '"+ title.getText().toString() + "', description= '" +
                        description.getText().toString() + "' where title = '" +
                        getIntent().getStringExtra("title")+"'");
                Toast.makeText(UbahCatatan
                        .this, "Data Berhasil di Update!", Toast.LENGTH_SHORT).show();
                Catatan.notes.RefreshList();
                finish();
            }
        });
    }
}