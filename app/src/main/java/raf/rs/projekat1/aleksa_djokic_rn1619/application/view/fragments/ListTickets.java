package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.application.R;
import com.google.android.material.tabs.TabLayout;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.viewpager.TabAdapter;

public class ListTickets extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ListTickets() {
        super(R.layout.activity_tabs);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initTabs();
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.viewPagerTab);
        tabLayout = view.findViewById(R.id.tabLayout);
    }

    private void initTabs() {
        viewPager.setAdapter(new TabAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
