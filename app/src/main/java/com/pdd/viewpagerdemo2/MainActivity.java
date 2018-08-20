package com.pdd.viewpagerdemo2;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private List<String> tabNames;
    //viewpager的数据集合
    private List<Fragment> fragments;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();
        setDataToView();
    }

    //绑定数据
    private void setDataToView() {

        mTablayout=  findViewById(R.id.tabLayout);
        mViewPager=  findViewById(R.id.viewPager);
        VpSouyAdapter vpfAdapter = new VpSouyAdapter(getSupportFragmentManager(),tabNames,fragments);
        //设置适配器
        mViewPager.setAdapter(vpfAdapter);
        //tabLayout和ViewPager绑定
        mTablayout.setupWithViewPager(mViewPager);

        one= mTablayout.getTabAt(0);
        two= mTablayout.getTabAt(1);
        three= mTablayout.getTabAt(2);
        four= mTablayout.getTabAt(3);


        one.setIcon(R.mipmap.ic_launcher);
        two.setIcon(R.mipmap.ic_launcher);
        three.setIcon(R.mipmap.ic_launcher);
        four.setIcon(R.mipmap.shopcar);

        mTablayout.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#09b6f2"));

        Log.d("1111111", "setDataToView: "+mTablayout.getTabTextColors());

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                if (tab == mTablayout.getTabAt(0)) {
                    one.setIcon(R.mipmap.sel_check);
                    mViewPager.setCurrentItem(0);

                } else if (tab == mTablayout.getTabAt(1)) {
                    two.setIcon(R.mipmap.sel_no);
                    mViewPager.setCurrentItem(1);
                } else if(tab==mTablayout.getTabAt(2)){
                    three.setIcon(R.mipmap.shopcar);
                    mViewPager.setCurrentItem(2);
                }
                else if (tab == mTablayout.getTabAt(3)) {
                    four.setIcon(R.mipmap.shopcar_choose);
                    mViewPager.setCurrentItem(3);
                }
                mTablayout.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#09b6f2"));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                if (tab == mTablayout.getTabAt(0)) {
                    one.setIcon(R.mipmap.ic_launcher);
                    return;
                } else if (tab == mTablayout.getTabAt(1)) {
                    two.setIcon(R.mipmap.ic_launcher);
                    return;
                } else if (tab == mTablayout.getTabAt(2)) {
                    three.setIcon(R.mipmap.ic_launcher);
                    return;
                } else if (tab == mTablayout.getTabAt(3)) {
                    four.setIcon(R.mipmap.ic_launcher);
                    return;
                }


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });



    }



    //数据集合
    private void initData() {
        //tab标签名称集合
        tabNames = new ArrayList();
        //Fragment集合
        fragments = new ArrayList();
        //tablayout数据集合
        tabNames.add("口罩");
        tabNames.add("卡贴");
        tabNames.add("手表/手环");
        tabNames.add("眼镜");
        // 添加页面
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
            }


    public class VpSouyAdapter  extends FragmentPagerAdapter{

        private List<String> tabNames;
        private List<Fragment> fragments;

        public VpSouyAdapter(FragmentManager fm, List<String> tabNames, List<Fragment> fragments) {
            super(fm);
            this.tabNames = tabNames;
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }
    }

}
