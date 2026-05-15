package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    private Context context;
    private List<Post> postList;
    private OnPostClickListener listener;

    public interface OnPostClickListener {
        void onAuthorClick(Post post);
    }

    public PostAdapter(Context context, List<Post> postList, OnPostClickListener listener) {
        this.context = context;
        this.postList = postList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        }

        Post post = postList.get(position);

        ImageView imgAvatar = convertView.findViewById(R.id.img_post_avatar);
        TextView tvName = convertView.findViewById(R.id.tv_post_name);
        TextView tvDate = convertView.findViewById(R.id.tv_post_date);
        TextView tvContent = convertView.findViewById(R.id.tv_post_content);

        tvName.setText(post.getAuthorName());
        tvDate.setText(post.getDate());
        tvContent.setText(post.getContent());

        if (post.getAvatarUrl() != null && !post.getAvatarUrl().isEmpty()) {
            Glide.with(context).load(post.getAvatarUrl()).circleCrop().into(imgAvatar);
        } else {
            imgAvatar.setImageResource(R.drawable.baseline_image_24);
        }

        View.OnClickListener authorClickListener = v -> {
            if (listener != null) {
                listener.onAuthorClick(post);
            }
        };

        imgAvatar.setOnClickListener(authorClickListener);
        tvName.setOnClickListener(authorClickListener);

        return convertView;
    }

    public void setPostList(List<Post> newList) {
        this.postList = newList;
        notifyDataSetChanged();
    }
}
