package com.example.order_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Coffee_pick extends AppCompatActivity {

    ImageView iv;
    TextView tvName,tvPrice, tvPay, num;
    Button btn_choice, btn_pick, plus, minus;
    CheckBox check1;
    Toolbar mtoolbar;

    //수량
    private int mQuantity = 1;
    private int sizeup = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffe_pick);

        //toolbar 활성화
        mtoolbar = (Toolbar)findViewById(R.id.mtoolbar);
        setSupportActionBar(mtoolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//기본 타이틀 안보이기



        //화면 이미지, 이름, 가격 데이터 가져오기
        iv = (ImageView)findViewById(R.id.coffee);
        tvName = (TextView)findViewById(R.id.name);
        tvPrice = (TextView)findViewById(R.id.price);

        final Intent intent = getIntent();
        iv.setImageResource(intent.getIntExtra("img",0));
        tvName.setText(intent.getStringExtra("name"));
        tvPrice.setText(""+intent.getIntExtra("price",2));


        //상품의 수량에 따른 가격 변동
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);
        num= (TextView)findViewById(R.id.num);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantity++;
                num.setText(""+mQuantity);
                if (check1.isChecked()) {
                    //체크박스 체크후 +버튼시 에러 코드
                    tvPay.setText(String.valueOf(intent.getIntExtra("price", 2)*mQuantity + (mQuantity * sizeup)));
                }
                else {
                    tvPay.setText("" + intent.getIntExtra("price", 2) * mQuantity);
                }
            }
        });



        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantity--;
                if (mQuantity < 1)mQuantity=1;
                num.setText(""+mQuantity);
                if (check1.isChecked()) {
                    //체크박스 체크후 -버튼시 에러 코드
                    tvPay.setText(String.valueOf(intent.getIntExtra("price", 2)*mQuantity + (mQuantity * sizeup)));
                }
                else {
                    tvPay.setText("" + intent.getIntExtra("price", 2) * mQuantity);
                }
            }
        });


        //사이즈업 체크박스를 통한 가격추가 및 취소
        tvPay = (TextView)findViewById(R.id.tvPay);
        check1 = (CheckBox)findViewById(R.id.check1);

        tvPay.setText(""+intent.getIntExtra("price",2));
        check1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1.isChecked()) {
                    tvPay.setText(String.valueOf(intent.getIntExtra("price", 2)*mQuantity + (mQuantity * sizeup)));
                }else {
                    tvPay.setText(String.valueOf(intent.getIntExtra("price", 2)*mQuantity + (mQuantity*(sizeup - sizeup))));
                }
            }
        });

        //바로 주문(터치시 다이얼로그창이 띄어진다)
        btn_choice = (Button)findViewById(R.id.btn_choic);
        btn_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Coffee_pick.this);
                builder.setTitle("커피를 주문하시겠습니까?");
                builder.setCancelable(true);
                if (check1.isChecked()){
                    builder.setMessage("주문하신 커피 : " + intent.getStringExtra("name") + "\n"
                            + "가격 : " + String.valueOf(intent.getIntExtra("price", 2)*mQuantity + (mQuantity * sizeup)));
                }
                else {
                    builder.setMessage("주문하신 커피 : " + intent.getStringExtra("name") + "\n"
                            + "가격 : " + String.valueOf(intent.getIntExtra("price", 2)*mQuantity));
                }
                builder.setPositiveButton("예",yesButton);
                builder.setNegativeButton("아니오",noButton);
                builder.show();
            }

            //다이얼로그 예 버튼 클릭시, 상품 주문과함께 메인화면으로 이동
            private DialogInterface.OnClickListener yesButton = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    Toast.makeText(getApplicationContext(),"주문이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                }
            };

            //다이얼로그 취소 버튼 클릭시, 주문창을 다시 보여줌
            private DialogInterface.OnClickListener noButton = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "주문이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                }
            };
        });

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
