package com.example.loginlogout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListCay extends AppCompatActivity {
    ListView listViewCocktail ;
    ArrayList<Cay> arrCocktail;
    CayAdapter adapter;
    DatabaseReference database ;
    CardView btnAdd , btnDelete ;
    TextView tenthuongoi,tenkhoahoc,maula;
    Cay cay ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        listViewCocktail = findViewById(R.id.listOrder);
        arrCocktail = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Users");
        tenthuongoi = findViewById(R.id.tenthuonggoilist);
        tenkhoahoc = findViewById(R.id.tenkhoahoclist);
        maula = findViewById(R.id.maulalist);
        database.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                                               for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                   Cay cay = dataSnapshot.getValue(Cay.class);
                                                   arrCocktail.add((cay));
                                               }
                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError error) {

                                           }
                                       });
        adapter = new CayAdapter(this , R.layout.row_order , arrCocktail);
        listViewCocktail.setAdapter(adapter);

        listViewCocktail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cay cocktail = arrCocktail.get(i);

            }
        });
        listViewCocktail.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alerdialog = new AlertDialog.Builder(ListCay.this);
                alerdialog.setTitle("Thong bao");
                alerdialog.setMessage("Ban co muon xoa khong");
                alerdialog.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int ih) {
                        arrCocktail.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                });
                alerdialog.setNegativeButton("khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int ih) {
                    }
                });
                alerdialog.show();
                return false;
            }
        });
    }
    public void handleAdd(View view){
        cay = new Cay(tenthuongoi.getText().toString(),tenkhoahoc.getText().toString(),maula.getText().toString(),2);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                database.setValue(cay);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void handleDelete(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query cayQuery = ref.child("cay").orderByChild("tenkhoahoc").equalTo(tenkhoahoc.getText().toString());

        cayQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}