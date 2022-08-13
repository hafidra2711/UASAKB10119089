package com.example.tubes10119089;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class vPager extends AppCompatActivity {

    private androidx.viewpager.widget.ViewPager viewPager;
    private ArrayList<vModel> modelArrayList;
    private vAdapter adapter;
    private MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpager);
        Dialog dialog = new Dialog(vPager.this);
        dialog.setCanceledOnTouchOutside(true);
        viewPager = findViewById(R.id.viewPager);
        loadCards();viewPager.addOnPageChangeListener(new androidx.viewpager.widget.ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String title = modelArrayList.get(position).getTitle();
                if(position == modelArrayList.size()-1){
                    button.setText("Mulai");
                }else {
                    button.setText("Lanjut");
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager.getCurrentItem()+1<modelArrayList.size()){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }else{
                    //kembali ke main activity
                    startActivity(new Intent(vPager.this, Login.class));
                    finish();
                }

            }
        });
    }

    private void loadCards() {
        modelArrayList = new ArrayList<>();

        modelArrayList.add(new vModel("CATATSKUY","Aplikasi catat menyatat!",R.drawable.od));
        modelArrayList.add(new vModel("Tulis!","Buat catatan !",R.drawable.qw));
        modelArrayList.add(new vModel("Ubah Yo!","Ubah, Edit ,",R.drawable.we));

        adapter = new vAdapter(this,modelArrayList);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100,0,100,0);
    }
}