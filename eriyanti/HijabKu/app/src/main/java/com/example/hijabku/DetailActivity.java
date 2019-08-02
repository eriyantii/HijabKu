package com.example.hijabku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.hijabku.viewholder.UserViewHolder;
import com.example.hijabku.model.ModelHijab;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference rvHijab;


    TextView txtTitle;
    TextView txtDesc;
    ImageView imgHijab;
    FloatingActionButton btnUrl;
    CollapsingToolbarLayout collapsingToolbarLayout;
    String dataId = "";
    FirebaseRecyclerAdapter<ModelHijab, UserViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        rvHijab = database.getReference("Data");

        //Init View
        btnUrl = (FloatingActionButton) findViewById(R.id.btn_url);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtDesc = (TextView) findViewById(R.id.txt_desc);
        imgHijab = (ImageView) findViewById(R.id.img_hijab);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        if (getIntent() != null)
            dataId = getIntent().getStringExtra("DataId");
        if (!dataId.isEmpty()) {

            getdataDataId(dataId);
        }


    }

    private void getdataDataId(String dataId) {
        rvHijab.child(dataId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final ModelHijab modelHijab = dataSnapshot.getValue(ModelHijab.class);


                Picasso.get().load(modelHijab.getImage()).into(imgHijab);

                collapsingToolbarLayout.setTitle(modelHijab.getTitle());

                txtTitle.setText(modelHijab.getTitle());
                txtDesc.setText(modelHijab.getDescription());

                FloatingActionButton url = findViewById(R.id.btn_url);
                url.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(modelHijab.getUrl()));
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
