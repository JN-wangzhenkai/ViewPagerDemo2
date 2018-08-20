package com.pdd.viewpagerdemo2;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private int[] imageResIds; //存放图片资源id的数组
    private List<ImageView> imageViews; //存放图片的集合
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageViews = new ArrayList<>();
        imageResIds = new int[]{R.mipmap.avatar,
                R.mipmap.avatar,
                R.mipmap.avatar,
                R.mipmap.ic_launcher,
                R.mipmap.avatar};
        ViewPager mViewPager = findViewById(R.id.viewPager);


        for (int i = 0; i < imageResIds.length; i++) {

            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResIds[i]);
            imageViews.add(imageView);

        }
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(imageViews);
        mViewPager.setAdapter(myPagerAdapter);

//
//        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                Log.e("TAG", "imageview" + page.getTag() + "------:" + position);
//                if (position > 0 && position <= 1) {
//                    page.setPivotX(0);//是为了让相邻两页在滑动时紧紧粘连在一起
//                    page.setScaleX(1 - position);//设置滑出页的缩放值从1渐渐变成0.
//                } else if (position <= 0 && position >= -1) {
//                    page.setPivotX(page.getWidth());
//                    page.setScaleX(1 + position);
//                }
//
//
//            }
//        });

        //1.让ViewPager一页显示三个Item；
        //2.ViewPager滑动时，自定义切换动画，让当前页两边的Page的Y轴方向尺寸比当前页小一定的高度；

        //  在ViewPager的父布局里加入一条属性android:clipChildren="false"，意思是让子控件不局限于自己的内部
        //然后在代码还需要设置下两个Page之间的距离， 然后设置下ViewPager的margin值
        // 不然是粘在一起的，给不了用户一种既欢喜又悲伤的感觉，调用mViewPager.setPageMargin() 方法即可设置
//        接着就要实现当前Item两边的Item要比当前Item矮一点的效果了，先来分析下情况：
//        1.当前页滑动到左边，position从0渐变到-1；当前页滑动到右边，position从0渐变到1；
//        2.随着position的变化，page页View的高度要跟着变化，需要注意的是，高度和position并不是等量渐变的，
// 例如手指向右滑动时，position从0渐变到1，View的高度要从1渐变到0.8（scale值，假设UI要求当前页两边的高度是当前页高度的80%），
// 那么图像大概是这样的

        mViewPager.setPageMargin(50);
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float v = Math.abs(position);
                float v1 = (float) (0.2 * (v * v));
                page.setScaleY(1 - v1);
            }
        });

    }


    public class MyPagerAdapter extends PagerAdapter {
        private List<ImageView> views;

        public MyPagerAdapter(List<ImageView> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // return super.instantiateItem(container, position);
//            View v = views.get(position);
//            ViewGroup parent = (ViewGroup) v.getParent();
//            if (parent != null) {
//                parent.removeAllViews();
//            }
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView(views.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return super.getPageTitle(position);
            return "标题" + position;


        }
    }

}
