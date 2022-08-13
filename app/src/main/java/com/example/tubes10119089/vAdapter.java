package com.example.tubes10119089;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class vAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<vModel> modelArrayList;
    public vAdapter(Context context, ArrayList<vModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }



    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,container,false);

        ImageView banner = view.findViewById(R.id.banner);
        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.description);


        vModel mdl = modelArrayList.get(position);
        String jdl = mdl.getTitle();
        String desc = mdl.getDescription();

        int image = mdl.getImage();

        banner.setImageResource(image);
        title.setText(jdl);
        description.setText(desc);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, title+"\n"+description, Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view,position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
