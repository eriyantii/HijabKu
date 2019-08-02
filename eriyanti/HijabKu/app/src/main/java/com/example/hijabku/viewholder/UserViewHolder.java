package com.example.hijabku.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.example.hijabku.R;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    View mview;
    ImageView imageView;
    TextView post_desc;
    TextView post_title;
    TextView post_url;


    public UserViewHolder(View itemView) {
        super(itemView);
        mview = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }

    public void setTitle(String title) {
        post_title = (TextView) mview.findViewById(R.id.txt_title);
        post_title.setText(title);
    }

    public void setDesc(String desc) {
        post_desc = (TextView) mview.findViewById(R.id.txt_desripsi);
        post_desc.setText(desc);
    }

    public void setUrl(String url) {

    }

    public void setImage(Context ctx, String image) {
        imageView = (ImageView) mview.findViewById(R.id.img_ootd);
        Picasso.get().load(image).into(imageView);
    }

    private UserViewHolder.ClickListener mClickListener;


    public interface ClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(UserViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }


}
