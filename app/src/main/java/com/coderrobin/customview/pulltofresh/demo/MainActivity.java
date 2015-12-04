package com.coderrobin.customview.pulltofresh.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.coderrobin.customview.R;

import terranovaproductions.newcomicreader.FloatingActionMenu;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_main);
        FloatingActionMenu floatingActionMenu=(FloatingActionMenu)findViewById(R.id.floating_action_menu);
        floatingActionMenu.setOnMenuItemClickListener(new FloatingActionMenu.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(FloatingActionMenu floatingActionMenu, int i, FloatingActionButton floatingActionButton) {
                if(i==0){
                    changeToDemo1Fragment();
                }
                else if(i==1){
                    changeToDemo2Fragment();
                }
            }
        });
    }

    private void changeToDemo1Fragment(){
        Fragment fragment=new Demo1Fragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
    }

    private void changeToDemo2Fragment(){
        Fragment fragment=new Demo2Fragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
    }

}
