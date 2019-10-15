package com.example.order_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Event_pick extends AppCompatActivity {

    Toolbar mtoolbar;
    ImageView pick_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pick);

        //toolbar 활성화
        mtoolbar = (Toolbar)findViewById(R.id.mtoolbar);
        setSupportActionBar(mtoolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//기본 타이틀 안보이기


        //화면 이미지, 이름, 가격 데이터 가져오기
        pick_picture = (ImageView)findViewById(R.id.pick_picture);

        final Intent intent = getIntent();
        pick_picture.setImageResource(intent.getIntExtra("picture",0));
    }


    //back버튼 터치시 main화면으로 이동
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{        //home으로 설정을 하면 뒤로가기 버튼이 눌러졌을때로 판단
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
