package com.ronggggggggg;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class ConversationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);
        ConversationListFragment conversationListFragment = new ConversationListFragment(); Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon() .appendPath("conversationlist") //设置私聊会话，该会话聚合显示
        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置系统会话，该会话非聚合显示
         .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true") .build();
        conversationListFragment.setUri(uri); FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.conversationlist,conversationListFragment);
        transaction.commit();
//        RongIM.getInstance().startPrivateChat(ConversationListActivity.this,"123","123");
//        RongIM.getInstance().startConversationList(this);
//        RongIM.getInstance().startPrivateChat(this, "123", "123");
//        RongIM.getInstance().setCurrentUserInfo(new UserInfo("1234566","123456", Uri.parse("url")));
    }
    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType
     * @param mTargetId
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversationlist);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);
    }
}
