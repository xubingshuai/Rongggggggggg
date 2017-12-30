package com.ronggggggggg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ronggggggggg.impl.GetTokenPresentImpl;
import com.ronggggggggg.model.getTokenModel;
import com.ronggggggggg.view.GetTokenView;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends AppCompatActivity implements GetTokenView{
private Button button ;
    private GetTokenPresentImpl getTokenPresent;
//    private String getToken = "q1pBuhdEzLuXgtWT+FeezuN/WFNThYE+3lZCXlfJ7piurp0lPe43S5bqPeAG46VI0B2g1ArX41D0FOb2ixLcjQ==";
    private String tokean = "rKiGNffYeBem/36+Vl1/M+N/WFNThYE+3lZCXlfJ7piurp0lPe43S6BXfTJVn8/JlqtmKdH/5CgoKBHBPuB1CPQU5vaLEtyN";
//    private String token = "fvFv1rYOAHkqudwvHQNkBgstjF0kIy1OUcTPSdTjSE/32rfMcBliTQJDcIvRNxYgHbBvPA4SnUY=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.but_connect);
        getTokenPresent = new GetTokenPresentImpl(this,this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getTokenPresent.getTokean("123","123","urrl");
//                Toast.makeText(MainActivity.this,"1233",Toast.LENGTH_LONG).show();
                RongIM.connect(tokean, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                        Log.e("MainActivity", "--onTokenIncorrect");
                    }
                    @Override
                    public void onSuccess(String userid) {
                        Log.e("MainActivity", "--onSuccess--" + userid);
                        Toast.makeText(MainActivity.this, "登录成功,用户：" + userid, Toast.LENGTH_SHORT).show();
                        //服务器连接成功，跳转消息列表
                startActivity(new Intent(MainActivity.this, ConversationListActivity.class));
                    }
                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        Log.e("MainActivity", "--onError");
                    }
                });



            }
        });
//        RongIM.getInstance().setCurrentUserInfo(new UserInfo(myId, myName, Uri.parse(myHeadUrl)));

    }


    @Override
    public void afterGetToken(getTokenModel getTokenModel) {
        Log.e("toaken111111111111111111111"," "+getTokenModel.getToken());
    }
}
