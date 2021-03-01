package com.example.fbgoogle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
DatabaseReference ref;
EditText name,age,address,contact_number,complaints,medicines;
Button btn_insert;
int max_id = 0;
Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        contact_number = findViewById(R.id.contact_number);
        complaints = findViewById(R.id.complaints);
        medicines = findViewById(R.id.medicines);
        btn_insert = findViewById(R.id.btn_insert);

        manager =new Manager();
        ref = FirebaseDatabase.getInstance().getReference().child("Patient");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    max_id=(int) dataSnapshot.getChildrenCount();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> listpatients = new ArrayList<>();
                ArrayAdapter adapter=null;
                manager.setName(name.getText().toString());
                manager.setAge(age.getText().toString());
                manager.setAddress(address.getText().toString());
                manager.setContact_number(contact_number.getText().toString());
                manager.setComplaints(complaints.getText().toString());
                manager.setMedicines(medicines.getText().toString());

                ref.child(String.valueOf(max_id+1)).setValue(manager);
                Toast.makeText(MainActivity.this,"Inserted Successfully",Toast.LENGTH_SHORT).show();

            }
        });

    }

}