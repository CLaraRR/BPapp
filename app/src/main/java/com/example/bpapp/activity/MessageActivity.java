package com.example.bpapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import com.example.bpapp.adapter.FriendsAdapter;
import com.example.bpapp.bpapp.R;
import com.example.bpapp.entity.Friends;

import java.util.ArrayList;
import java.util.List;

/**
 * 好友消息Activity
 */
public class MessageActivity extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener{
    private List<Friends> friendsList=new ArrayList<Friends>();
    private ListView listView;
    private Button gobackButton;
    private Button addContactsButton;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_frame);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
        swipeLayout.setOnRefreshListener(this);
        gobackButton=(Button)findViewById(R.id.toolbar_left_btn);
        gobackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addContactsButton=(Button)findViewById(R.id.toolbar_right_btn2);
        addContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MessageActivity.this,AddContactsActivity.class);
//                intent.putExtra();
                startActivity(intent);
            }
        });
        initFriends(20);

        FriendsAdapter friendsAdapter=new FriendsAdapter(MessageActivity.this,R.layout.layout_friends,friendsList);
        listView=(ListView)findViewById(R.id.listView_friends);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0)
                    swipeLayout.setEnabled(true);
                else
                    swipeLayout.setEnabled(false);
            }
        });
        listView.setAdapter(friendsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inten=new Intent(MessageActivity.this,ChatCommunityActivity.class);
                startActivity(inten);
            }
        });

    }

    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case 1:
//                    mDatas.addAll(Arrays.asList("Lucene", "Canvas", "Bitmap"));
//                    mAdapter.notifyDataSetChanged();
                    //数据更新操作

                    swipeLayout.setRefreshing(false);
                    break;

            }
        }
    };
    private void initFriends(int n){
        for(int i=0;i<n;i++){
            Friends friends=new Friends("Matthow");
            friendsList.add(friends);
        }

    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(1, 2000);//刷新完成
    }
}
