package com.example.hung.githubusermvp.screen.screen.userlist;
import com.example.hung.githubusermvp.screen.data.model.Item;
import com.example.hung.githubusermvp.screen.data.repository.GetDataFromURI;

import java.util.ArrayList;

public class UserPresenter implements UserPresenterIplm {
    private GetDataFromURI mGetDataFromURI;
    private OnDisplayListUser mOnDisplayListUser;

    public UserPresenter(OnDisplayListUser onDisplayListUser) {
        this.mOnDisplayListUser = onDisplayListUser;
        mGetDataFromURI = new GetDataFromURI();
        mGetDataFromURI.setmOnFetchDataListener(this);
    }

    public void loadData(String url) {
        mGetDataFromURI.execute(url);
    }

    @Override
    public void fetchSucces(ArrayList<Item> items) {
        ListUserFragment.mDialog.dismiss();
        mOnDisplayListUser.displayListUser(items);
    }

    @Override
    public void fetchfail(Error error) {

    }
}
