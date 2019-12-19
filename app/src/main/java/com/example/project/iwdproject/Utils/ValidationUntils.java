package com.example.project.iwdproject.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By zdd on 2018/8/14
 */
public class ValidationUntils {
    /**
     * 手机号验证
     */
    public static Boolean checkRegister(Context instance, String str_account) {
        if (TextUtils.isEmpty(str_account)) {
            Toast.makeText(instance, "手机号不能为空", Toast.LENGTH_SHORT).show();
        }else {
            String reg_acc = "^1[0-9]{10}$";
            Pattern pattern_acc = Pattern.compile(reg_acc);
            Matcher matcher_acc = pattern_acc.matcher(str_account);
            boolean b_acc = matcher_acc.matches();
            if (!b_acc) {
                Toast.makeText(instance, "请输入正确手机号码", Toast.LENGTH_SHORT).show();
            } else {
                return true;
            }
        }
        return false;
    }
}
