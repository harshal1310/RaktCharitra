package com.harsh1310.rakkktcharitr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class plasmaadapter extends FirebaseRecyclerAdapter<plasmamodel,plasmaadapter.MyviewHolder> {

    private Context context;
    private ArrayList<plasmamodel> courseModelArrayList;
    public plasmaadapter.OnItemClickListener ItemClickListener;
    public interface OnItemClickListener
    {
        void onclick(int pos);
        void delete(int pos);
    }
    public void setOnItemClickListener(plasmaadapter.OnItemClickListener onItemClickListener) {
        this.ItemClickListener = (plasmaadapter.OnItemClickListener) onItemClickListener;
    }

    public plasmaadapter (@NonNull FirebaseRecyclerOptions<plasmamodel> options, Context con)
    {
        super(options);
        this.context=con;
    }
    @NonNull
    @Override
    public plasmaadapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listofplasma, parent, false);
        return new MyviewHolder(view,ItemClickListener);
    }


    @Override
    protected void onBindViewHolder(@NonNull MyviewHolder holder, int position, @NonNull plasmamodel model) {
        holder.name.setText(model.getName());
        holder.city.setText( model.getCity());
        holder.phonenum.setText(model.getPhonenum());
       // holder.avalabilty.setText(model.getAvailability());
        holder.gender.setText(model.getGender());
        holder.medtext.setText(model.getDisease());
        holder.allergytext.setText(model.getAllergy());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Intent intent=new Intent(context,Profileofplamsa.class);
        ArrayList<String>arlist=new ArrayList<>();
        arlist.add(model.getGender());arlist.add(model.getPhonenum());
        arlist.add(model.getAvailability());
        arlist.add(model.getCity());
        arlist.add(model.getPickup());
        arlist.add(model.getPic());
//        model.getAvailability(),model.getCity(),model.getPickup());
      //  intent.putExtra("key6",arlist);
       // v.getContext().startActivity(intent);
    }
});
    }



    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView name,phonenum,city,avalabilty,gender,nameicon,locicon,allergyicon,medicon,contacticon,allergytext,medtext,grptext,grpicon;
        CircleImageView dimg;

        public MyviewHolder(@NonNull View itemView, plasmaadapter.OnItemClickListener itemClickListener) {
            super(itemView);
            name=itemView.findViewById(R.id.plsmadtext);
            nameicon=itemView.findViewById(R.id.plsmadicon);

            phonenum=itemView.findViewById(R.id.dpcontacttext);
            contacticon=itemView.findViewById(R.id.dpcontacticon);
            locicon=itemView.findViewById(R.id.plsmadicon);
            //grpicon=itemView.findViewById(R.id.dpbfrpicon);

            city=itemView.findViewById(R.id.plsmadloctext);
            allergyicon=itemView.findViewById(R.id.dpallergyicon);
            allergytext=itemView.findViewById(R.id.dpallergytext);
            medicon=itemView.findViewById(R.id.dpmedicon);
            medtext=itemView.findViewById(R.id.dpmedtext);
            gender=itemView.findViewById(R.id.psmagendertext);
         //   avalabilty=itemView.findViewById(R.id.dpavailabilitytext);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            itemClickListener.onclick(position);
                        }
                    }
                }
            });
        }
    }
}
