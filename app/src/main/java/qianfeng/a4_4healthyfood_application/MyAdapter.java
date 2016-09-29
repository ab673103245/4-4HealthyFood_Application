package qianfeng.a4_4healthyfood_application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class MyAdapter extends FragmentPagerAdapter {
    private List<ClassfyBean> list;
    private List<Fragment> fragments;

    public MyAdapter(FragmentManager fm, List<ClassfyBean> list, List<Fragment> fragments) {
        super(fm);
        this.list = list;
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

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName(); // 标题是名字
    }
}
