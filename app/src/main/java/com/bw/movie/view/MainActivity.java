package com.bw.movie.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

import java.sql.Time;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.text_time)
    TextView textTime;
    public static int TIME=5;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                TIME--;
                textTime.setText(TIME+"S");
                if (TIME>0){
                    Message message = handler.obtainMessage(1);
                    handler.sendMessageDelayed(message,1000);
                }else{
                    Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message,1000);
    }
}
