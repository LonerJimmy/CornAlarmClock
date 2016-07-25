package com.example.jimmy.cornalarmclock.context;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import xiaofei.library.datastorage.database.DbConfig;

public class AppApplication extends Application {

    private static AppApplication mInstance;

    public static AppApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        if (isMainProcess()) {
            onMainApplicationCreate();
        }
    }

    public Context getAppContext() {
        return mInstance;
    }

    private void onMainApplicationCreate() {
        //数据库版本设置
        DbConfig.setVersion(11);
//        PreferenceUtil.init();
//        ToastUtil.init(this);
    }

    /**
     * umeng统计
     */
//    private void setUmeng() {
//        MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
//        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(getApplicationContext(), "55d3fa3de0f55a28dc002bd8", "Channel ID"));
//        MobclickAgent.setDebugMode(AppConfig.DEBUG);
//    }
//
//    private void setPush() {
//        switch (AppCookie.getHost()) {
//            case DebugModeActivity.RELEASE_HOST:
//                PushManager.setEnv(PushEnv.PRODUCTION);
//                break;
//            case DebugModeActivity.ALPHA_HOST:
//                PushManager.setEnv(PushEnv.ALPHA);
//                break;
//            case DebugModeActivity.BATE_HOST:
//                PushManager.setEnv(PushEnv.BETA);
//                break;
//            default:
//                break;
//        }
//        PushManager.init(GeTui.getInstance(), MiPush.getInstance());
//        PushManager.setProviderRegisteredListener(new PushManager.ProviderRegisteredListener() {
//            @Override
//            public void providerRegistered(IPushProvider iPushProvider, String s) {
//                KLog.e("注册推送成功:" + s);
//            }
//        });
//
//        PushManager.setTransmissionMessageHandler(new MessageHandler<String>() {
//            @Override
//            public void onMessage(Message<String> msg) {
//                GePushMessage message = mGson.fromJson(msg.getContent(), GePushMessage.class);
//                ToastUtil.showDebugToast("推送信息：" + message.getEvent() + "," + message.getData());
//                PushMessageOperate pushMessageOperate = PushOperateFactory.createMessageOperate(message);
//                if (pushMessageOperate != null) {
//                    pushMessageOperate.operate();
//                }
//            }
//        });
//    }
    public boolean isMainProcess() {
        return getPackageName().equals(getCurrentProcessName());
    }

    public String getCurrentProcessName() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : activityManager
                .getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }
//
//    private void initImageLoader() {
//        //  ImageLoaderConfiguration.createDefault(this);
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(me.ele.hbdteam.R.drawable.ic_default_image)
//                .showImageOnFail(me.ele.hbdteam.R.drawable.ic_default_image)
//                .showImageForEmptyUri(me.ele.hbdteam.R.drawable.ic_default_image)
//                .cacheOnDisk(true)
//                .cacheInMemory(true)
//                .displayer(new FadeInBitmapDisplayer(500, true, true, true))
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .build();
//        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(getAppContext());
//        config.threadPriority(Thread.NORM_PRIORITY - 2);
//        config.denyCacheImageMultipleSizesInMemory();
//        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
//        config.defaultDisplayImageOptions(defaultOptions);
//        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
//        config.tasksProcessingOrder(QueueProcessingType.LIFO);
//        config.writeDebugLogs(); // Remove for release app
//        ImageLoader.getInstance().init(config.build());
//
//    }
//
//    public static long getCorrectTime() {
//        return System.currentTimeMillis() + correctTimeDiff;
//    }
//
//    public static void setCorrectTimeDiff(long diff) {
//        AppApplication.correctTimeDiff = diff;
//    }
}
