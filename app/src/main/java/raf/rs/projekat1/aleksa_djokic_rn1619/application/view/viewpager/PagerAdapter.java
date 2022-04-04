package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import org.jetbrains.annotations.NotNull;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments.ListTickets;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments.NewTicket;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments.Profile;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments.Statistics;

public class PagerAdapter extends FragmentPagerAdapter {

    private final int FRAGMENT_COUNT = 4;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;
    public static final int FRAGMENT_3 = 2;
    public static final int FRAGMENT_4 = 3;

    public PagerAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT); // samo trenutni fragment prolazi kroz ovo stanje
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case FRAGMENT_1: fragment = new Statistics(); break;
            case FRAGMENT_2: fragment = new NewTicket(); break;
            case FRAGMENT_3: fragment = new ListTickets(); break;
            default: fragment = new Profile(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case FRAGMENT_1: return "Statistics";
            case FRAGMENT_2: return "New";
            case FRAGMENT_3: return "Tickets";
            default: return "Profile";
        }
    }
}
