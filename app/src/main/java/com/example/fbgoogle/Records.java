package com.example.fbgoogle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Records extends AppCompatActivity {
    DatabaseReference ref;
    ListView listView;
    Manager manager;
    SearchView search;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        listView = (ListView)findViewById(R.id.listView);
        ref=FirebaseDatabase.getInstance().getReference("Patient");

      final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
      listView.setAdapter(arrayAdapter);

      ref.addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              Manager value = dataSnapshot.getValue(Manager.class);
              arrayList.add("NAME: "+"\t"+value.getName()+"\n"+"AGE: "+"\t"+value.getAge()+"\n"+"ADDRESS: "+"\t"+value.getAddress()+"\n"+"CONTACT NUMBER: "+"\t"+value.getContact_number()+"\n"+"COMPLAINTS: "+"\t"+value.getComplaints()+"\n"+"MEDICINES: "+"\t"+value.getMedicines());
              arrayAdapter.notifyDataSetChanged();
          }

          @Override
          public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

          }

          @Override
          public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

          }

          @Override
          public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });
    }

    @Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.searchmenu,menu);
    MenuItem item=menu.findItem(R.id.search);
    SearchView searchView = (SearchView)item.getActionView();

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            searchPatient(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            searchPatient(query);
            return false;
        }
    });
    return super.onCreateOptionsMenu(menu);
}

        private void searchPatient(final String name) {
        if(name.isEmpty()){
            return ;
        }

        Query query=ref.orderByChild("name").startAt(name).endAt(name+"\uf8ff");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ArrayList<String> listpatients = new ArrayList<>();
                    ArrayAdapter adapter=null;
                    if (snapshot.exists()) {

                        for (DataSnapshot ds : snapshot.getChildren()) {

                            Manager manager = new Manager(ds.child("name").getValue(String.class), ds.child("age").getValue(String.class), ds.child("address").getValue(String.class), ds.child("contact_number").getValue(String.class), ds.child("complaints").getValue(String.class), ds.child("medicines").getValue(String.class));
                            listpatients.add("NAME:"+"\t"+manager.getName() + "\n"+"Age:"+"\t"+ manager.getAge()+"\n" +"ADDRESS:"+"\t"+ manager.getAddress() + "\n" +"CONTACT NUMBER:"+"\t"+manager.getContact_number()+"\n" +"COMPLAINTS:"+"\t"+manager.getComplaints()+"\n" +"MEDICINES :"+"\t"+manager.getMedicines());
                            arrayList.add("NAME: " + "\t" + manager.getName() + "\n" + "AGE: " + "\t" + manager.getAge() + "\n" + "ADDRESS: " + "\t" + manager.getAddress() + "\n" + "CONTACT NUMBER: " + "\t" + manager.getContact_number() + "\n" + "COMPLAINTS: " + "\t" + manager.getComplaints() + "\n" + "MEDICINES: " + "\t" + manager.getMedicines());

                        }
                        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listpatients);
                        listView.setAdapter(adapter);

                    } else {
                        Log.d("Manager", "No data found");
                        Toast.makeText(getApplicationContext(),"Data not found",Toast.LENGTH_SHORT).show();
                        listpatients.clear();
                        listView.setAdapter(adapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

}
