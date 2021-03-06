package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.example.application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.viewpager.PagerAdapter;

public class BottomNavigationActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        init();
    }

    private void init() {
        initViewPager();
        initNavigation();
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        //viewPager.setOffscreenPageLimit(4);
    }

    private void initNavigation() {
        ((BottomNavigationView)findViewById(R.id.bottomNavigation)).setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                // setCurrentItem metoda viewPager samo obavesti koji je Item trenutno aktivan i onda metoda getItem u adapteru setuje odredjeni fragment za tu poziciju
                case R.id.navigation_1: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_1, false); break;
                case R.id.navigation_2: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_2, false); break;
                case R.id.navigation_3: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_3, false); break;
                case R.id.navigation_4: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_4, false); break;
            }
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Cant back press", Toast.LENGTH_SHORT).show();
    }
}