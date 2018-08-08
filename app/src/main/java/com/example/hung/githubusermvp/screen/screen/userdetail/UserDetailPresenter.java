package com.example.hung.githubusermvp.screen.screen.userdetail;


import com.example.hung.githubusermvp.screen.data.model.Item;
import com.example.hung.githubusermvp.screen.screen.userlist.ListUserFragment;

public class UserDetailPresenter implements UserDetailPresenterIplm {
    private OnDisplayDetailUser mOnDisplayDetailUser;
    private ListUserFragment mListUserFragment;

    public UserDetailPresenter(OnDisplayDetailUser onDisplayDetailUser){
        this.mOnDisplayDetailUser = onDisplayDetailUser;
        mListUserFragment = new ListUserFragment();
        mListUserFragment.setmUserDetailPresenterIplm(this);
    }

    @Override
    public void getUserDetail(Item item) {
        mOnDisplayDetailUser.displayDetailUser(item);
    }
}
