package com.harsh1310.rakkktcharitr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class register_page3 extends AppCompatActivity {
EditText allergy,ifallergy,ifdisease,op,ifop,drop_pick,remark;
Button next3;
    ArrayList<String>list;
    stored_credentials pref;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page3);
pref=stored_credentials.getInstance(this);
     list = (ArrayList<String>) getIntent().getSerializableExtra("key3");

     //for(String s:list)
      //  Log.d("check",s);
//Log.d("check",list.get(10));
        allergy=findViewById(R.id.allergy);

        ifdisease=findViewById(R.id.disease);
        op=findViewById(R.id.operation);
     //   drop_pick=findViewById(R.id.pickup_drop);

        next3=findViewById(R.id.next3);

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(allergy.getText().toString().equals("Yes")==false&&allergy.getText().toString().equals("No")==false)
                {
                    allergy.setError("Should be Yes or No");
                    allergy.requestFocus();
                }
                else if(ifdisease.getText().toString().equals("Yes")==false&&ifdisease.getText().toString().equals("No")==false)
                {
                    ifdisease.setError("Should be Yes or No");
                    ifdisease.requestFocus();
                }
                else if(op.getText().toString().equals("Yes")==false&&op.getText().toString().equals("No")==false)
                {
                    op.setError("Should be Yes or No");
                    op.requestFocus();
                }
                else {
                    list.add(allergy.getText().toString());
                    list.add(ifdisease.getText().toString());
                    list.add(op.getText().toString());

                    // String allergyy=allergy.getText().toString().trim();
                    // if(allergyy.length()==0)
                    //   list.add("No");
                    // else
                    //   list.add(allergyy);
                    // String diseases=ifdisease.getText().toString().trim();
                    // if(diseases.length()==0)
                    //   list.add("No");
                    // else
                    //   list.add(diseases);
                    // String opp=op.getText().toString().trim();
                    // if(opp.length()==0)
                    //   list.add("No");
                    // else
                    //   list.add(opp);
                    Intent intent = new Intent(register_page3.this, registration4.class);
                    intent.putExtra("key4", list);
                    startActivity(intent);
                }
               //createuser();
            }
        });
    }

    private String getextension() {
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(getContentResolver().getType(Uri.parse(list.get(6))));
    }

}

