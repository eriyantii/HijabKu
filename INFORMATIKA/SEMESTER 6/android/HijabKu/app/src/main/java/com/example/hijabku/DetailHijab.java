package com.example.hijabku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailHijab extends AppCompatActivity {

    public TextView txt_title, txt_description;
    public ImageView img_hijab;
    public FirebaseDatabase database;
    public DatabaseReference hijab;
    String hijabId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_hijab);

        database = FirebaseDatabase.getInstance();
        hijab = database.getReference("Hijab");
        txt_title = findViewById(R.id.txt_title);
        txt_description = findViewById(R.id.txt_description);
        img_hijab= findViewById(R.id.img_hijab);

        if (getIntent() != null) {
            hijabId = getIntent().getStringExtra("OOTD HIJAB");
            if (!hijabId.isEmpty()) {
                getDetailHijab(hijabId);
            }
        }

    }

    private void getDetailHijab(String hijabId) {
        hijab.child(hijabId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ModelHijab modelHijab = dataSnapshot.getValue(ModelHijab.class);
                txt_title.setText(modelHijab.getTitle());
                txt_description.setText(modelHijab.getDescription());

                Glide.with(getBaseContext()).load(

                        modelHijab.getImage()).into(img_hijab);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
