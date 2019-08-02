package com.example.hijabku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hijabku.model.ModelHijab;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.hijabku.viewholder.UserViewHolder;

public class Menu extends AppCompatActivity {

    RecyclerView rvHijab;
    private DatabaseReference userDatabase;
    private FirebaseRecyclerAdapter<ModelHijab, UserViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userDatabase = FirebaseDatabase.getInstance().getReference("Data");

        rvHijab = findViewById(R.id.recycler_container);

        rvHijab.setHasFixedSize(true);
        rvHijab.setLayoutManager(new LinearLayoutManager(this));

        onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelHijab, UserViewHolder>
                (ModelHijab.class, R.layout.recycle_menu, UserViewHolder.class, userDatabase) {
            @Override
            protected void populateViewHolder(UserViewHolder userViewHolder, ModelHijab modelHijab, int i) {

                userViewHolder.setTitle(modelHijab.getTitle());
                userViewHolder.setDesc(modelHijab.getDescription());
                userViewHolder.setImage(getBaseContext().getApplicationContext(), modelHijab.getImage());

                userViewHolder.setOnClickListener(new UserViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent Intent = new Intent(getBaseContext().getApplicationContext(), DetailActivity.class);
                        Intent intent = Intent.putExtra("DataId", firebaseRecyclerAdapter.getRef(position).getKey());
                        startActivity(intent);

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });

            }
        };
        rvHijab.setAdapter(firebaseRecyclerAdapter);
    }

}
