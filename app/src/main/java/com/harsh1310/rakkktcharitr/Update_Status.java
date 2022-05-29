package com.harsh1310.rakkktcharitr;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Update_Status extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText reason;
    TextView datetange;
Button save;
FirebaseAuth auth;
DatabaseReference users;
stored_credentials pref;
String id,bgrp;int year,month,day;
Spinner grpspinner;
Calendar mcalendar;
int age;
long daydiff=100,d=90;
    Calendar cal;
    String[] courses = { "Yes","No","Maybe"};
    ArrayList<String>   ar;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__status);

reason=findViewById(R.id.reason);
datetange=findViewById(R.id.daterange1);
save=findViewById(R.id.ssave);
grpspinner=findViewById(R.id.sspinners);
    //   ar  = (ArrayList<String>) getIntent().getSerializableExtra("key7");
        grpspinner.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        grpspinner.setAdapter(ad);
datetange.setOnClickListener(v->opencal());
        auth=FirebaseAuth.getInstance();
pref=stored_credentials.getInstance(this);

id=pref.getuserid();
String date=pref.getlastdate();
String tmpdate="a",tmpmonth="a",tmpyear="a";


        if(pref.getlastdate().equals("No")==false) {
            int i = 0;
            for (i = 0; i < date.length(); i++) {
                if (date.charAt(i) == '/')
                    break;

                tmpdate += date.charAt(i);
            }
            i++;
            for (; i < date.length(); i++) {
                if (date.charAt(i) == '/')
                    break;
                tmpmonth += date.charAt(i);

            }
            i++;
            for (; i < date.length(); i++) {
                if (date.charAt(i) == '/')
                    break;
                tmpyear += date.charAt(i);

            }

          //  Log.d("harshal", "y->" + tmpdate);
           // Log.d("harshal", "m->" + tmpmonth);
            //Log.d("harshal", "d->" + tmpyear);
            LocalDate dateBefore = LocalDate.of(Integer.parseInt(tmpyear.substring(1)), Integer.parseInt(tmpmonth.substring(1)), Integer.parseInt(tmpdate.substring(1)));
            //29-July-2017, change this to your desired End Date
            //Log.d("harshal", dateBefore.toString());
            Calendar cal = Calendar.getInstance();

            LocalDate dateAfter = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
             daydiff = ChronoUnit.DAYS.between(dateBefore, dateAfter);
            //Log.d("harshal","days->"+daydiff);
        }
        Log.d("check",pref.getupdate());
if (daydiff<90)
    reason.setText("Already blood donated");
   settime();
save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
if(daydiff<90)
{
    if(bgrp.equals("No")==false)
    {long x=90-daydiff;
        Toast.makeText(Update_Status.this,"You can't available to donate until "+x+" Update availability to No",Toast.LENGTH_LONG).show();

    }
    else
        updatestatusfun();
}
else
        updatestatusfun();
    }
});

    }

    private void opencal() {
        mcalendar=Calendar.getInstance();
        year=mcalendar.get(Calendar.YEAR);
        month=mcalendar.get(Calendar.MONTH);
        day=mcalendar.get(Calendar.DAY_OF_MONTH);
month++;
        DatePickerDialog.OnDateSetListener  listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int month2, int dayOfMonth) {
month2++;
                datetange.setText(dayOfMonth + "/" + month2 + "/" + year1);
                age=  year-year1;

            }
        };
        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();
    }


    private void updatestatusfun() {


            ArrayList<String>list=new ArrayList<>();
            String user = auth.getUid();
           users = FirebaseDatabase.getInstance().getReference("Users");
users.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.hasChild(id)||pref.getupdate().equals("1")) {
            pref.checkupdate("0");
        }
        else
        {
            Toast.makeText(Update_Status.this,"User should be login",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

           users.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //Log.d("check",snapshot.child(Constants.bgrp).getValue().toString()+" "+snapshot.child(Constants.pincode).getValue().toString()+" "+snapshot.child(Constants.name).getValue().toString()+" "+snapshot.child(Constants.).getValue().toString());
//Log.d("check",snapshot.child(Constants.bgrp).getValue().toString());

                    list.add(snapshot.child(Constants.bgrp).getValue().toString());
                    list.add(snapshot.child(Constants.pincode).getValue().toString());
                   list.add(snapshot.child(Constants.add).getValue().toString());
                    list.add(snapshot.child(Constants.name).getValue().toString());
                    list.add(snapshot.child(Constants.phone).getValue().toString());
                    list.add(snapshot.child(Constants.add).getValue().toString());
                    list.add(snapshot.child(Constants.gender).getValue().toString());
                    list.add(snapshot.child(Constants.pimg).getValue().toString());
                    list.add(snapshot.child(Constants.allergy).getValue().toString());

                    list.add(snapshot.child(Constants.disease).getValue().toString());

                    list.add(snapshot.child(Constants.operaion).getValue().toString());
                    list.add(snapshot.child(Constants.pickup).getValue().toString());
                    list.add(snapshot.child(Constants.avail).getValue().toString());
                    list.add(snapshot.child("id").getValue().toString());
                   String s= snapshot.child("dtype").getValue().toString();
              //   list.add(snapshot.child(Constants.profession).getValue().toString());
                   // String name, String phone, String bgrp, String add, String gender, String pic, String allergy, String disease, String op, String availability,String pickup,String pincode
                      FirebaseDatabase.getInstance().getReference().child(list.get(0)).child(list.get(1)).child(id).setValue(new donorsmodel(list.get(3),list.get(4),list.get(0),list.get(2),list.get(6), list.get(7),list.get(8),list.get(9),list.get(10),bgrp,list.get(11),list.get(1),s,list.get(13),list.get(13))).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent=new Intent(Update_Status.this,Login_activity.class);
                                startActivity(intent);
                            //    finish();
                            }
                            else {
                                Toast.makeText(Update_Status.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
Log.d("check",error.getMessage());
                }
            });


        }

     //   else{
     //       Toast.makeText(Update_Status.this,"Pls enter availibility",Toast.LENGTH_SHORT).show();
     //   }
    //}
    private void settime() {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent,0);

            if(daydiff>90)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000*60*10, 1000*60*60*5,pendingIntent);
        else
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000*60*10*daydiff, 1000*60*60*5,pendingIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       bgrp= courses[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}