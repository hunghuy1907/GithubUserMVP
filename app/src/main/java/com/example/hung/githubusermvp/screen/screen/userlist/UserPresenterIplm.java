package com.example.hung.githubusermvp.screen.screen.userlist;
import com.example.hung.githubusermvp.screen.data.model.Item;
import java.util.ArrayList;

public interface UserPresenterIplm {
    void fetchSucces(ArrayList<Item> items);

    void fetchfail(Error error);
}
