package com.example.hung.githubusermvp.screen.screen.userlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hung.githubusermvp.R;
import com.example.hung.githubusermvp.screen.data.model.Item;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.GithubUserViewHolder>{
    private OnClickItem mOnClickItem;
    private ArrayList<Item> mGithubUsers;
    private LayoutInflater mInflater;

    public UserAdapter(ArrayList<Item> githubUsers, Context context) {
        this.mGithubUsers = githubUsers;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GithubUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GithubUserViewHolder(mInflater.inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GithubUserViewHolder holder, int position) {
        holder.fillData(mGithubUsers.get(position));
    }

    @Override
    public int getItemCount() {
        if (mGithubUsers == null) {
            return 0;
        }
        return mGithubUsers.size();
    }

    public class GithubUserViewHolder extends RecyclerView.ViewHolder {
        private Item mItem;
        private TextView mTextName;

        public GithubUserViewHolder(View itemView) {
            super(itemView);

            mTextName = itemView.findViewById(R.id.txt_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickItem.getUser(mItem);
                }
            });
        }

        public void fillData(Item item) {
            mItem = item;

            mTextName.setText(item.getLogin());
        }
    }

    public void setmOnClickItem(OnClickItem mOnClickItem) {
        this.mOnClickItem = mOnClickItem;
    }
}
