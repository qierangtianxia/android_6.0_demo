package com.example.android60demo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class FourActivity extends AppCompatActivity {
    private ViewPager mViewpager;
    private TabLayout mTabLayout;
    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 0; i < 100; i++) {
            datas.add(i + "");
        }

        mTabLayout = (TabLayout) findViewById(R.id.id_four_tab_layout);
        mViewpager = (ViewPager) findViewById(R.id.id_four_viewpager);

        mViewpager.setAdapter(new FourActivity.MyPagerAdapter());
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
            RecyclerView recyclerView = new RecyclerView(FourActivity.this);

            recyclerView.setAdapter(new FourActivity.MyPagerAdapter.MyRecyclerAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(FourActivity.this));
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

        class MyRecyclerAdapter extends RecyclerView.Adapter<FourActivity.MyPagerAdapter.MyViewHolder> {

            @Override
            public FourActivity.MyPagerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView view = new TextView(FourActivity.this);
                view.setGravity(Gravity.CENTER);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                FourActivity.MyPagerAdapter.MyViewHolder holder = new FourActivity.MyPagerAdapter.MyViewHolder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(FourActivity.MyPagerAdapter.MyViewHolder holder, int position) {
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
