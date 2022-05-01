package com.harsh1310.rakkktcharitr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register_page1 extends AppCompatActivity {
EditText bod,profession,last_bd,last_platellets,smokedisease;
Button next2;
    String[] yesno = {"Yes","No"};

String bgrp,gender;
    private RequestQueue mRequestQueue;
    String getdata="tt";
    TextView tv;
    private Calendar mcalendar;

    private int day,month,year,age;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page1);
        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("key1");
   // for(String s:list)
    //    Log.d("check",s);
    mRequestQueue = Volley.newRequestQueue(register_page1.this);
     bod=findViewById(R.id.dateofbirth);

     profession=findViewById(R.id.profession);
     last_bd=findViewById(R.id.lastblooddonation);
        last_platellets=findViewById(R.id.lastplateletsdonation);
        smokedisease=findViewById(R.id.smokedrink);

     next2=findViewById(R.id.next2);

bod.setOnClickListener(v->opencal());
//last_bd.setOnClickListener(v->opencal1());
//last_platellets.setOnClickListener(v->opencal2());
   getDataFromPinCode(list.get(3));
   next2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                 if (profession.getText().toString().length() == 0) {
                    profession.setError("Profession not entered");
                    profession.requestFocus();
                }

           else if (bod.getText().toString().length() == 0) {
                bod.setError("BOD not entered");
                bod.requestFocus();

            }
           else  if(age<18||age>60)
                 {
                        bod.setError("Age should betwwen 18 and 60"+age);
                        bod.requestFocus();
                 }
           else if(last_bd.getText().toString().equals("Yes")==false&&last_bd.getText().toString().equals("No")==false)
                 {
                     last_bd.setError("should be Yes or No");
                     last_bd.requestFocus();
                 }
else if(last_platellets.getText().toString().equals("Yes")==false&&last_platellets.getText().toString().equals("No")==false)
                 {
                     last_platellets.setError("Should be Yes or No");
                     last_platellets.requestFocus();
                 }
else if(smokedisease.getText().toString().equals("Yes")==false&&smokedisease.getText().toString().equals("No")==false)
                 {
                     smokedisease.setError("Should be Yes or No");
                     smokedisease.requestFocus();
                 }
else  if(last_bd.getText().toString().equals("No")&&last_platellets.getText().toString().equals("No"))
                 {
                     Toast.makeText(register_page1.this,"User should be blood or platelets donor",Toast.LENGTH_SHORT).show();

                 }
                else{
                     list.add(profession.getText().toString());
                    list.add(bod.getText().toString());

                    String lb=last_bd.getText().toString();

                                list.add(lb);
                            lb=last_platellets.getText().toString();
                     //if(lb.length()==0)
                       //  list.add("No");
                     //else
                         list.add(lb);


                 Log.d("check",getdata);

                    list.add(getdata);

                     //if(smokedisease.getText().toString().trim().length()==0)
                       //  list.add("No");
                    // else
                         list.add(smokedisease.getText().toString());




                     Intent intent=new Intent(register_page1.this,Otp_activity.class);
                    intent.putExtra("key2",list);
                    startActivity(intent);}

        }
    });


}



    private void opencal() {
mcalendar=Calendar.getInstance();
year=mcalendar.get(Calendar.YEAR);
month=mcalendar.get(Calendar.MONTH);
day=mcalendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog.OnDateSetListener  listener=new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year1, int month, int dayOfMonth) {
                  //  myCalendar.set(Calendar.YEAR, year);
                    //myCalendar.set(Calendar.MONTH, month);
                    //myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                  //  updateDate();
                    bod.setText(dayOfMonth + "/" + month + "/" + year1);
                  age=  year-year1;
                    Log.d("check","a->"+age);
                }
            };

        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();
        }








    private void getDataFromPinCode(String pinCode) {
        mRequestQueue.getCache().clear();
        // clearing our cache of request queue.
        //  mRequestQueue.getCache().clear();

        // below is the url from where we will be getting
        // our response in the json format.
        String url = "http://www.postalpincode.in/api/pincode/" + pinCode;

        // below line is use to initialize our request queue.
        RequestQueue queue = Volley.newRequestQueue(register_page1.this);
        // private void updateAndroidSecurityProvider() { try { ProviderInstaller.installIfNeeded(this); } catch (Exception e) { e.getMessage(); } }
        // in below line we are creating a
        // object request using volley.
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // inside this method we will get two methods
                // such as on response method
                // inside on response method we are extracting
                // data from the json format.
                try {
                    // we are getting data of post office
                    // in the form of JSON file.
                    JSONArray postOfficeArray = response.getJSONArray("PostOffice");
                    if (response.getString("Status").equals("Error")) {
                        // validating if the response status is success or failure.
                        // in this method the response status is having error and
                        // we are setting text to TextView as invalid pincode.
                        Toast.makeText(register_page1.this, "Invalid pincode", Toast.LENGTH_SHORT).show();
                    } else {
                        // if the status is success we are calling this method
                        // in which we are getting data from post office object
                        // here we are calling first object of our json array.
                        JSONObject obj = postOfficeArray.getJSONObject(0);
                        // inside our json array we are getting district name,
                        // state and country from our data.
                        String district = obj.getString("District");
                        String city = obj.getString("Taluk");
                        String country = obj.getString("Country");
                        //  tv.setText(district);
                        getdata=city;
                        //// Log.d("ash",getdata);

                        // list1.add(state);
                        // after getting all data we are setting this data in
                        // our text view on below line.
                       // Toast.makeText(register_page1.this, "Details of pin code is : \n" + "District is : " + district + "\n" + "State : "
                         //       + state + "\n" + "Country : " + country, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // if we gets any error then it
                    // will be printed in log cat.
                    e.printStackTrace();
                    Toast.makeText(register_page1.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // below method is called if we get
                // any error while fetching data from API.
                // below line is use to display an error message.
                Toast.makeText(register_page1.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        //  Log.d("ash",getdata);
        queue.add(objectRequest);


    }


}