package com.example.war.adapter;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.war.Menu;
import com.example.war.Player;
import com.example.war.R;
import com.example.war.War;
import com.example.war.model.MenuItems;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ProfileViewHolder>  {
    private ArrayList<MenuItems> profileList;
    Animation  animation = AnimationUtils.loadAnimation(Menu.context, R.anim.fadeout);;
    String selectedGame;




    public static class ProfileViewHolder extends RecyclerView.ViewHolder {

         ImageView image;
         TextView text;
         ConstraintLayout layout;


        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.name);
            layout = itemView.findViewById(R.id.layout);
        }

    }


    public AdapterMenu(ArrayList<MenuItems> mprofileList){
        profileList = mprofileList;
    }


    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_items, parent, false);
        ProfileViewHolder pvh = new ProfileViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        final MenuItems current = profileList.get(position);

        holder.image.setImageBitmap(BitmapFactory.decodeResource(Menu.context.getResources(),current.getImage()));
        holder.text.setText(current.getName());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0){
                    view.startAnimation(animation);
                    Intent intent = new Intent(Menu.context, Player.class);
                    Menu.context.startActivity(intent);
                }
                if(position == 1){
                    view.startAnimation(animation);
                    Toast.makeText(Menu.context, "Coming soon", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return profileList.size();
    }



}
