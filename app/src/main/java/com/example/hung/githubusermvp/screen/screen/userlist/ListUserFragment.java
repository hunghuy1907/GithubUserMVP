package com.example.hung.githubusermvp.screen.screen.userlist;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hung.githubusermvp.R;
import com.example.hung.githubusermvp.screen.data.model.Item;
import com.example.hung.githubusermvp.screen.screen.userdetail.DetailUserFragment;
import com.example.hung.githubusermvp.screen.screen.userdetail.UserDetailPresenterIplm;

import java.util.ArrayList;

public class ListUserFragment extends Fragment implements OnDisplayListUser, OnClickItem{
    public static String url;
    private EditText mEditTextKeyWork;
    private EditText mEditTextLimitNumber;
    private Button mButtonSearch;
    public static ProgressDialog mDialog;
    private UserPresenter mPresenter;
    private UserAdapter mUserAdapter;
    private RecyclerView mRecyclerViewUser;
    private UserDetailPresenterIplm mUserDetailPresenterIplm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycle_view_user_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeComponents();
    }

    private void initializeComponents() {
        mRecyclerViewUser = getActivity().findViewById(R.id.rcv_github_user);

        mRecyclerViewUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewUser.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        mEditTextKeyWork = getActivity().findViewById(R.id.edt_key_work);
        mEditTextLimitNumber = getActivity().findViewById(R.id.edt_limit_number);
        mButtonSearch = getActivity().findViewById(R.id.btn_search);
        mDialog = new ProgressDialog(getActivity());
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               clickButtonSearch();
            }
        });
        mDialog.setMessage("Loading");
    }

    private void clickButtonSearch() {
        mPresenter = new UserPresenter(ListUserFragment.this);
        String keyword = mEditTextKeyWork.getText().toString();
        String limit = mEditTextLimitNumber.getText().toString();
        if (keyword.isEmpty() || limit.isEmpty()) {
            return;
        }
        mDialog.show();
        url = "https://api.github.com/search/users?per_page=" + limit + "&q=" + keyword;
        mPresenter.loadData(url);
    }

    @Override
    public void displayListUser(ArrayList<Item> items) {
        mUserAdapter = new UserAdapter(items, getActivity());
        mUserAdapter.setmOnClickItem(this);
        mRecyclerViewUser.setAdapter(mUserAdapter);
    }

    @Override
    public void getUser(Item item) {
        getFragmentManager().beginTransaction().replace(R.id.frame_layout, new DetailUserFragment())
                .addToBackStack(null)
                .commit();
        mUserDetailPresenterIplm.getUserDetail(item);
    }

    public void setmUserDetailPresenterIplm(UserDetailPresenterIplm mUserDetailPresenterIplm) {
        this.mUserDetailPresenterIplm = mUserDetailPresenterIplm;
    }
}
