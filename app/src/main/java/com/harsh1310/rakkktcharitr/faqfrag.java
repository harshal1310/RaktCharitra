package com.harsh1310.rakkktcharitr;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class faqfrag extends Fragment {
ImageView i1,i2,i3,i4;
    public faqfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_faqfrag, container, false);
i1=rootview.findViewById(R.id.p1);
        i2=rootview.findViewById(R.id.p2);
        i3=rootview.findViewById(R.id.p3);
        i4=rootview.findViewById(R.id.p4);

        /*BitmapDrawable drawable1 = (BitmapDrawable) i1.getDrawable();
        Bitmap bmp = drawable1.getBitmap();
        Bitmap b = Bitmap.createScaledBitmap(bmp, 1200, 1400,true);
i1.setImageBitmap(b);
        BitmapDrawable drawable2 = (BitmapDrawable) i1.getDrawable();
        Bitmap bmp1 = drawable2.getBitmap();
        Bitmap b1 = Bitmap.createScaledBitmap(bmp1, 1200, 1400,true);
        i2.setImageBitmap(b1);
        BitmapDrawable drawable3 = (BitmapDrawable) i1.getDrawable();
        Bitmap bmp2 = drawable2.getBitmap();
        Bitmap b2 = Bitmap.createScaledBitmap(bmp2, 1200, 1400,true);
        i3.setImageBitmap(b2);
        BitmapDrawable drawable4 = (BitmapDrawable) i1.getDrawable();
        Bitmap bmp3 = drawable3.getBitmap();
        Bitmap b3 = Bitmap.createScaledBitmap(bmp3, 1200, 1400,true);
        i4.setImageBitmap(b3);

         */
//i2.setImageBitmap(b);
//i3.setImageBitmap(b);
//i4.setImageBitmap(b);
        return rootview;
    }
}