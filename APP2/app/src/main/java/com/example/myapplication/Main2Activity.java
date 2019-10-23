package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main2Activity extends AppCompatActivity {
    private Button send_btn;
    private EditText set_drink;
    private RadioGroup rg1,rg2;
    private  String sugar = "無糖";
    private String ice_opt = "微冰";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rg1=findViewById(R.id.Group2);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i){
                    case R.id.radioButton5:
                        sugar="無糖";
                        break;
                    case R.id.radioButton6:
                        sugar="少糖";
                        break;
                    case R.id.radioButton7:
                        sugar="半糖";
                        break;
                    case R.id.radioButton8:
                        sugar="全糖";
                        break;
                }
            }
        });
        rg2=findViewById(R.id.Group1);

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i){
                    case R.id.radioButton:
                        sugar="微冰";
                        break;
                    case R.id.radioButton2:
                        sugar="少冰";
                        break;
                    case R.id.radioButton3:
                        sugar="正常冰";
                        break;
                }
            }
        });
    send_btn=findViewById(R.id.button);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_drink = findViewById(R.id.ed_drink);
                String drink = set_drink.getText().toString();
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putString("sugar", sugar);
                b.putString("drink", drink);
                b.putString("ice", ice_opt);

                i.putExtras(b);
                setResult(101, i);
                finish();
            }
        });






    }
}
