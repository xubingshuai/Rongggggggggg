package com.ronggggggggg.net;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ronggggggggg.DialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by apy on 2017/8/14.
 * Retrofit 联网框架回调
 */

public abstract class MyCallBack<T> implements Callback {

    private Dialog loadingDialog;
//    private PullRecycler recycler;
    private boolean netWork;

    protected MyCallBack(Context mContext, boolean isShowDialog) {
//        this.recycler = recycler;
//        netWork = UIUtils.getNetWork();
//        if (!netWork) {
//            AToast.showTextToast("请检查您的网络设置");
//            return;
//        }
        if (isShowDialog) {
            loadingDialog = DialogUtils.createLoadingDialog(mContext, "加载中~~~~");
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog.show();
            }
        }
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) {
//        if (recycler != null) {
//            recycler.onRefreshCompleted();
//        }
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        T k = (T) response.body();
        onResponse(k);
//        BaseResponse body = (BaseResponse) response.body();
//        if (body != null && body.resultCode != null) {
//            if ("109".equals(body.resultCode)) {
//                AToast.showTextToast("您的账户已过期，请重新登录");
//                accountOverdue();
//                return;
//            }
//            if ("000".equals(body.resultCode)) {
//                K k = (K) body;
//                onResponse(k);
//            } else {
//                onResponseNoBody(body.message);
//            }
//        } else {
//            AToast.showTextToast("网络异常");
//            onResponseFailure();
//        }
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
//        if (recycler != null) {
//            recycler.onRefreshCompleted();
//        }
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        onFailure(t);
//        if (netWork) {
//            AToast.showTextToast("网络异常");
//            onFailure(t);
//        }

    }

    /**
     * 获取到服务器数据
     *
     * @param response
     */
    public abstract void onResponse(T response);

    /**
     * 连接到服务器但是没有获取到数据
     */
    public void onResponseFailure() {

    }

    /**
     * 请求到数据不是000
     */
    public void onResponseNoBody(String msg) {

    }

    /**
     * 账号冻结
     */
    public void freezeResponse() {

    }

    /**
     * 账号过期
     */
    public void accountOverdue() {

    }

    /**
     * 连接服务器失败
     *
     * @param t
     */
    public void onFailure(Throwable t) {

    }
}
