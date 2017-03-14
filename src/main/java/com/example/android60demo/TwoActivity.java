package com.example.android60demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android60demo.R.id.id_fab;
import static com.example.android60demo.R.id.viewpager;

public class TwoActivity extends AppCompatActivity {

    private ViewPager mViewpager;
    private TabLayout mTabLayout;
    private ArrayList<String> datas = new ArrayList<>();
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        for (int i = 0; i < 100; i++) {
            datas.add(i + "");
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.third_activity_toolbar);
        setSupportActionBar(toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewpager = (ViewPager) findViewById(viewpager);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.id_fab);


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TwoActivity.this, ThreeActivity.class));
            }
        });

        mViewpager.setAdapter(new MyPagerAdapter());
        mTabLayout.setupWithViewPager(mViewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    class MyPagerAdapter extends PagerAdapter {

        String[] title = {"Tab1", "Tab2", "Tab3"};

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            RecyclerView recyclerView = new RecyclerView(TwoActivity.this);

            recyclerView.setAdapter(new MyRecyclerAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(TwoActivity.this));
            container.addView(recyclerView);
            return recyclerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView view = new TextView(TwoActivity.this);
                view.setGravity(Gravity.CENTER);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                MyViewHolder holder = new MyViewHolder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {
                holder.view.setText(datas.get(position));
            }

            @Override
            public int getItemCount() {
                return datas.size();
            }
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView view;

            public MyViewHolder(View itemView) {
                super(itemView);
                view = (TextView) itemView;
            }
        }
    }
}

