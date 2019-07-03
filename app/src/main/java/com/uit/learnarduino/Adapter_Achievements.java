package com.uit.learnarduino;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.annotations.Nullable;
import com.uit.learnarduino.R;

import java.io.ByteArrayOutputStream;

public class Adapter_Achievements extends ArrayAdapter<achievement> {

    Activity context;
    int resource;
    public Adapter_Achievements(Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View custom=context.getLayoutInflater().inflate(resource,null);
        final View none=context.getLayoutInflater().inflate(resource,null);
        TextView txtName=custom.findViewById(R.id.name_achieve);
        TextView txtDescription=custom.findViewById(R.id.description_achieve);
        final TextView txtExp=custom.findViewById(R.id.exp);
        ImageView imageView = custom.findViewById(R.id.image_of_lv);

        achievement achievement=getItem(position);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        Log.d("zzzzz", "getView: "+achievement.getImg());
        imageBytes = Base64.decode(achievement.getImg(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        imageView.setImageBitmap(decodedImage);

        txtName.setText(achievement.getName());
        txtDescription.setText(achievement.getDescription());
        txtExp.setText(String.valueOf(achievement.getExp()));
        return custom;
    }
}
