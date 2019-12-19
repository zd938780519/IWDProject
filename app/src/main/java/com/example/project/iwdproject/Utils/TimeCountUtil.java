package com.example.project.iwdproject.Utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created By zdd on 2018/8/14
 */
public class TimeCountUtil extends CountDownTimer {
    private Activity mActivity;
    private TextView btn;

    public TimeCountUtil(Activity mActivity, long millisInFuture, long countDownInterval, TextView btn) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.btn =btn;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setClickable(false);
        btn.setText(millisUntilFinished / 1000 + "秒后重送");

        Spannable span = new SpannableString(btn.getText().toString());
        span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        btn.setText(span);

    }

    @Override
    public void onFinish() {
        btn.setText("获取验证码");
        btn.setClickable(true);
    }
}
