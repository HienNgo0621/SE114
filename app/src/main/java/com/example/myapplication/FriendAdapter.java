package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.bumptech.glide.Glide;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    private List<Friend> friendList;

    public FriendAdapter(List<Friend> friendList) { this.friendList = friendList; }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_suggestion, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Friend friend = friendList.get(position);
        holder.tvName.setText(friend.getName());
        Glide.with(holder.itemView.getContext())
                .load(friend.getAvtURL()) // Hoặc friend.getProfileImage() tùy bạn đặt tên
                .placeholder(R.drawable.ic_launcher_background) // Ảnh hiển thị tạm trong lúc đang chờ tải mạng
                .error(R.drawable.ic_launcher_background) // Ảnh hiển thị nếu link bị hỏng/lỗi
                .into(holder.imgFriend);
    }

    @Override
    public int getItemCount() { return friendList.size(); }

    class FriendViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFriend;
        TextView tvName;
        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFriend = itemView.findViewById(R.id.imgFriend);
            tvName = itemView.findViewById(R.id.tvFriendName);
        }
    }
}