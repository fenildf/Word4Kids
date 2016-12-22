package com.daming.wordkids;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.daming.wordkids.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends Activity {

    private static final int HANDLER_MSG_OVER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWelcomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        binding.setRight("Copyright ©2016 longzhuangzhaung.com");
        binding.setVer("App Version: " + BuildConfig.VERSION_NAME);
        handler.sendEmptyMessageDelayed(HANDLER_MSG_OVER, 1500);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_MSG_OVER:
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
