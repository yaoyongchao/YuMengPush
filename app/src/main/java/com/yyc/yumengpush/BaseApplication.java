package com.yyc.yumengpush;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

public class BaseApplication extends Application{
    private String umAppKey = "5ba9e002b465f55a37000545";
    private String umPushSecret = "28ad38ed866dedc65afed1326aaa65e9";

    @Override
    public void onCreate() {
        super.onCreate();
        initUmetn();
    }

    private void initUmetn() {
        UMConfigure.init(this,umAppKey,"weicai",UMConfigure.DEVICE_TYPE_PHONE,umPushSecret);
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("aa","注册成功：" + deviceToken);
            }
            @Override
            public void onFailure(String s, String s1) {
                Log.e("aa","注册失败：" + s + "--" + s1);
            }
        });

        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                Log.e("aa",msg.custom) ;
                context.startActivity(new Intent(context,TwoActivity.class));
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

//        mPushAgent.
    }
}



















