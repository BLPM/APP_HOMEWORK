package com.example.lab7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int rabprogress=0,turprogress=0;
    private SeekBar seekBar1,seekBar2;
    private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar1=findViewById(R.id.seekBar);
        seekBar2=findViewById(R.id.seekBar2);
        btn_start=findViewById(R.id.button);
        btn_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                btn_start.setEnabled(false);
                rabprogress=0;
                turprogress=0;
                seekBar1.setProgress(0);
                seekBar2.setProgress(0);
                runThread();
                runAsyncRask();
            }
        });
    }
    private void runThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (rabprogress<=100&&turprogress<=100)
                {
                    try{
                        Thread.sleep(100);
                        rabprogress+=(int)(Math.random()*3);
                        Message msg = new Message();
                        msg.what=1;
                        mHandler.sendMessage(msg);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case 1:
                    seekBar1.setProgress(rabprogress);
                break;
            }
            if(rabprogress>=100 && turprogress<100)
            {
                Toast.makeText(MainActivity.this,"rabbit Win",Toast.LENGTH_SHORT).show();
                btn_start.setEnabled(true);
            }
            return false;
        }
    });
    private void runAsyncRask(){
        new AsyncTask<Void,Integer,Boolean>(){
            @Override
            protected Boolean doInBackground(Void... voids) {
                while (turprogress<=100&&rabprogress<100){
                    try{
                        Thread.sleep(100);
                        turprogress+=(int)(Math.random()*3);
                        publishProgress(turprogress);
                    }catch (InterruptedException e )
                    {
                        e.printStackTrace();
                    }
                }
                return true;
            }


            @Override
            protected void onProgressUpdate(Integer... values)
            {
                super.onProgressUpdate(values);
                seekBar2.setProgress(values[0]);
            }
            protected void onPostExcute(Boolean aBoolean){
                super.onPostExecute(aBoolean);
                if(turprogress>=100 && rabprogress<100)
                {
                    Toast.makeText(MainActivity.this,"turtle Win",Toast.LENGTH_SHORT).show();
                    btn_start.setEnabled(true);
                }
            }

        }.execute();
    }
}
