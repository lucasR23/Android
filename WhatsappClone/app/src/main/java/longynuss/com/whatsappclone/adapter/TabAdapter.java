package longynuss.com.whatsappclone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import longynuss.com.whatsappclone.fragments.Contacts;
import longynuss.com.whatsappclone.fragments.Conversations;


public class TabAdapter extends FragmentStatePagerAdapter {
    private String[] titles;

    public TabAdapter(FragmentManager fm,String[] titles) {
        super(fm);
        this.titles = titles.clone();
    }
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new Conversations();
                break;
            case 1:
                fragment = new Contacts();
                break;
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}