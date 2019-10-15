package com.example.order_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton imageButton_home;
    private long pressedTime = 0;
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //drawer navigation bar 활성화
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayShowTitleEnabled(false);//기본 타이틀 안보이기

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.item1);
        }

        title = (TextView)findViewById(R.id.coffee_title);
        imageButton_home = (ImageButton)findViewById(R.id.btn_home);
        imageButton_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                title.setText("커피만땅");
            }
        });
        /*imageButton_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(MainActivity.this, Cart.class);
                startActivity(cart);
            }
        });*/
    }





    //아이템 선택창
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        title = (TextView)findViewById(R.id.coffee_title);
        switch (item.getItemId()){
            case R.id.item1 :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                title.setText("커피만땅");
                break;
            case R.id.item2 :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new EventFragment()).commit();
                title.setText("이밴트");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    //뒤로가기 버튼 터치시
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else if (pressedTime == 0){
            Toast.makeText(MainActivity.this, "한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }else {
            int seconds = (int)(System.currentTimeMillis() - pressedTime);

            if (seconds>2000){
                Toast.makeText(MainActivity.this, "한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                pressedTime = 0;
            }
            else {
                super.onBackPressed();
            }
        }

    }
}
