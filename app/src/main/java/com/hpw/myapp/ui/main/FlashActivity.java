package com.hpw.myapp.ui.main;

import android.Manifest;
import android.os.Bundle;

import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.mvpframe.utils.helper.RxUtil;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.zhihu.activity.ZhihuMainActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;


/**
 * Created by hpw on 16/10/28.
 */

public class FlashActivity extends CoreBaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_flash;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
//        Observable.timer(2000, TimeUnit.MILLISECONDS)
//                .compose(RxPermissions.getInstance(this).ensureEach(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE))
//                .compose(RxUtil.rxSchedulerHelper())
//                .subscribe(permission -> {
//                    if (permission.granted) {
//                        startActivity(ZhihuMainActivity.class);
//                        finish();
//                    }
//                });


        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(RxPermissions.getInstance(this).ensureEach(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE))
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Action1<RxPermissions>() {
                    @Override
                    public void call(RxPermissions permission) {
                        if (permission.granted) { // 在android 6.0之前会默认返回true
                            // 已经获取权限
                            startActivity(ZhihuMainActivity.class);
                        finish();
                        }
                    }

                });
    }
}
