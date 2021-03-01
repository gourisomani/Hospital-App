package com.example.fbgoogle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstPage extends AppCompatActivity {
    SearchView search;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        ref = FirebaseDatabase.getInstance().getReference("Patient");
    }

    public void NewPage(View v)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);

    }
    public void ShowRec(View v)
    {
        Intent i=new Intent(this,Records.class);
        startActivity(i);
    }

}