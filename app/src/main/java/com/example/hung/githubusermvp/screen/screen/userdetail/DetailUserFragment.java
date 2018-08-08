package com.example.hung.githubusermvp.screen.screen.userdetail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hung.githubusermvp.R;
import com.example.hung.githubusermvp.screen.data.model.Item;

public class DetailUserFragment extends Fragment implements OnDisplayDetailUser {
    private ImageView mImageAvatar;
    private TextView mTextName;
    private TextView mTextId;
    private TextView mTextUrl;
    private UserDetailPresenter mUserDetailPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
    }

    private void initializeComponents() {
        mUserDetailPresenter = new UserDetailPresenter(this);
        mImageAvatar = getActivity().findViewById(R.id.img_avatar);
        mTextName = getActivity().findViewById(R.id.txt_name_detail);
        mTextId = getActivity().findViewById(R.id.txt_id);
        mTextUrl = getActivity().findViewById(R.id.txt_url);
    }

    @Override
    public void displayDetailUser(Item item) {
//        mUserDetailPresenter = new UserDetailPresenter(this);
        mTextName.setText(item.getLogin().toString());
        mTextUrl.setText(item.getUrl().toString());
        mTextId.setText(item.getId().toString());
        Glide.with(this).load(item.getAvatarUrl()).into(mImageAvatar);
    }
}
