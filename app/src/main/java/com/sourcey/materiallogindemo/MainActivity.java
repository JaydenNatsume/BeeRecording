package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private android.support.v4.app.FragmentManager botFragManager;
    private android.support.v4.app.FragmentTransaction transaction;
    private Fragment fragHome;
    private Fragment fragNotes;
    private Fragment fragTransaction;
    private Fragment fragCapital;
    private Fragment fragInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 首先进入登录页面
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        // 底部导航栏点击
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragHome = new HomeFrag();
        fragNotes = new NotesFrag();
        fragTransaction = new TransactionFrag();
        fragCapital = new CapitalFrag();
        fragInformation = new InformationFrag();

        // beginTransaction 是一个事务，执行完commit()后失效，再次执行commit()之前需要再次使用
        transaction = botFragManager.beginTransaction();
        // 给fragment容器中添加fragment
        transaction.add(R.id.container_layout, fragHome);
        transaction.commit();
    }

    // 底部导航栏点击监听
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    swiftFragments(fragHome);    //显示Home
                    return true;
                case R.id.navigation_notes:
                    swiftFragments(fragNotes);    // 显示Notes
                    return true;
                case R.id.navigation_transaction:
                    swiftFragments(fragTransaction);    // 显示Transaction
                    return true;
                case R.id.navigation_capital:
                    swiftFragments(fragCapital);    // 显示Capital
                    return true;
                case R.id.navigation_information:
                    swiftFragments(fragInformation);    // 显示Information
                    return true;
            }
            return false;
        }
    };

    // 切换fragment方法
    private void swiftFragments (Fragment fragment) {
        // beginTransaction 是一个事务，执行完commit()后失效
        transaction = botFragManager.beginTransaction();
        transaction.replace(R.id.container_layout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
