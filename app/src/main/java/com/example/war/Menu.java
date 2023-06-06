package com.example.war;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.war.adapter.AdapterMenu;
import com.example.war.model.MenuItems;
import java.util.ArrayList;

/**
 * The class controls the adapter and and the list of games listed.
 */
public class Menu extends AppCompatActivity {
    public  static Context context;
    public static RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    public static RecyclerView.LayoutManager layoutManager;
    ArrayList<MenuItems> profileList;
    Animation fadeout;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        context = this;

        actionBar = getSupportActionBar();
        actionBar.hide();

        String PACKAGE_NAME = getApplicationContext().getPackageName();

        fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
        profileList = new ArrayList<>();

        recyclerView = findViewById(R.id.menuView);
        recyclerView.setHasFixedSize(true);
        adapter = new AdapterMenu(profileList);

        layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        profileList.add(new MenuItems(getResources().getIdentifier(PACKAGE_NAME+":drawable/war"
                , null, null), "War"));
    }

    @Override
    public void onBackPressed() {
        // Simply Do noting!
    }
}