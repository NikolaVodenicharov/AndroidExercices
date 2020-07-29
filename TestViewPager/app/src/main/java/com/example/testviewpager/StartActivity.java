package com.example.testviewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StartActivity extends AppCompatActivity {
    private static final int PAGE_NUM = 2;

    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        pagerAdapter = new PagerAdapter(this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 1){
                    tab.setText("Weather");
                    tab.setIcon(R.drawable.ic_baseline_wb_sunny_24);
                }
                else{
                    tab.setText("Search");
                    tab.setIcon(R.drawable.ic_baseline_search_24);
                }
            }
        });

        tabLayoutMediator.attach();
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }
        else{
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private class PagerAdapter extends FragmentStateAdapter{
        public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 1){
                return new WeatherFragment();
            }
            else{
                return new SearchFragment();
            }
        }

        @Override
        public int getItemCount() {
            return PAGE_NUM;
        }
    }
}