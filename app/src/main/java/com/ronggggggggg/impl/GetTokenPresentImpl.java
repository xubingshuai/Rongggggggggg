package com.ronggggggggg.impl;

import android.content.Context;

import com.ronggggggggg.model.getTokenModel;
import com.ronggggggggg.net.Api;
import com.ronggggggggg.net.MyCallBack;
import com.ronggggggggg.view.GetTokenView;

/**
 * Created by Administrator on 2017/12/29.
 */

public class GetTokenPresentImpl implements GetTokenPresent {
    private Context context;
    private GetTokenView getTokenView;

    public GetTokenPresentImpl(Context context, GetTokenView getTokenView) {
        this.context = context;
        this.getTokenView = getTokenView;
    }

    @Override
    public void getTokean(String userid, String name, String url) {
        Api.getInstance().service.login(userid,name,url).enqueue(new MyCallBack<getTokenModel>(context,true) {
            @Override
            public void onResponse(getTokenModel response) {
                getTokenView.afterGetToken(response);
            }
        });
    }
}
