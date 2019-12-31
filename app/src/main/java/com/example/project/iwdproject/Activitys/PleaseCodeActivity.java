package com.example.project.iwdproject.Activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.project.iwdproject.Beans.InvitationCodeBean;
import com.example.project.iwdproject.Beans.LoginBean;
import com.example.project.iwdproject.MainActivity;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.PackagUtils;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;
import retrofit2.http.Header;
import util.UpdateAppUtils;

public class PleaseCodeActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_tilte)
    TextView tvTilte;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.titleBar)
    LinearLayout titleBar;
    @BindView(R.id.tv_pleasecode)
    TextView tvPleasecode;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_twocode)
    ImageView ivTwocode;
    @BindView(R.id.tv_disser)
    TextView tvDisser;
    @BindView(R.id.tv_link)
    TextView tvLink;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private PleaseCodeActivity instance;
    private String apkUrl;
    private InvitationCodeBean mInvitationCodeData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_please);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("邀请码");
        String  token = SharedPreferencesUtility.getAccessToken(instance);
        getInvitationCodeData(token);
    }





    /**
     * 获取邀请码
     */
    private void getInvitationCodeData(String token) {
        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getInvitationCode(1,token,application)
                .compose(this.<InvitationCodeBean>bindToLifecycle())
                .compose(SchedulerTransformer.<InvitationCodeBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<InvitationCodeBean>() {
                    @Override
                    protected void onSuccess(InvitationCodeBean mInvitationCodeBean) {
                        if (mInvitationCodeBean != null) {
                            if (mInvitationCodeBean.getCode() == 10086) {
                                ToastShort(instance,mInvitationCodeBean.getMessage());
                                mInvitationCodeData = mInvitationCodeBean;
                                tvPleasecode.setText("邀请码"+mInvitationCodeBean.getData().getNum());
                                tvPhone.setText(mInvitationCodeBean.getData().getPhone()+"的分享");
                                Glide.with(instance).load(mInvitationCodeBean.getData().getImg_url()).placeholder(R.mipmap.img_rwm_1).into(ivTwocode);
                                apkUrl = mInvitationCodeBean.getData().getUrl();
//                                update3(apkUrl);
                            } else {
                                ToastShort(instance, mInvitationCodeBean.getMessage());
                            }

                        }
                    }

                    @Override
                    protected void onFailed(HttpResponseException responseException) {
                        super.onFailed(responseException);
//                        ToastShort.showShortToast("网络错误！");
                        ToastShort(instance, "error code : " + responseException.getStatus());
//                        ToastShort(instance, "网络有误！");
                    }
                });
    }

//
//    //强制更新
//    private void update3(String apkUrl) {
//        UpdateAppUtils.from( instance)
//                .serverVersionCode(2)
//                .serverVersionName("2.0")
//                .apkPath(apkUrl)
//                .updateInfo("1.修复若干bug\n2.美化部分页面\n3.增加微信支付方式")
//                .isForce(true)
//                .update();
//    }



    @OnClick({R.id.iv_left, R.id.rl_back, R.id.iv_right,R.id.tv_save,R.id.tv_link})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.iv_right:

                break;
            case R.id.tv_link:
                String mAdress = tvDisser.getText().toString().trim();
                PackagUtils.copy(instance,mAdress);
                break;

            case R.id.tv_save:   //保存图片
                Glide.with(instance)
                        .load(mInvitationCodeData.getData().getImg_url()) .asBitmap()
                         .into(new SimpleTarget<Bitmap>() {
                             @Override
                             public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                 saveImage(resource);
                             }
                         });

                break;
        }
    }





    //保存资源文件中的图片到本地相册,实时刷新
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));

    }






    private void saveImage(Bitmap image) {
                String saveImagePath = null;
                 Random random = new Random();
                 String imageFileName = "JPEG_" + "down" + random.nextInt(10) + ".jpg";
                 File storageDir = new File(Environment.getExternalStoragePublicDirectory
                         (Environment.DIRECTORY_PICTURES) + "test");

                 boolean success = true;
                 if(!storageDir.exists()){
                         success = storageDir.mkdirs();
                     }
                 if(success){
                         File imageFile = new File(storageDir, imageFileName);
                         saveImagePath = imageFile.getAbsolutePath();
                         try {
                                 OutputStream fout = new FileOutputStream(imageFile);
                                 image.compress(Bitmap.CompressFormat.JPEG, 100, fout);
                                fout.close();
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }

                         // Add the image to the system gallery
                         galleryAddPic(saveImagePath);
                         Toast.makeText(instance, "IMAGE SAVED", Toast.LENGTH_LONG).show();
                     }
         //        return saveImagePath;
             }

             private void galleryAddPic(String imagePath) {
                 Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                 File f = new File(imagePath);
                 Uri contentUri = Uri.fromFile(f);
                 mediaScanIntent.setData(contentUri);
                 sendBroadcast(mediaScanIntent);
             }








}
