package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import org.jetbrains.annotations.NotNull;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments.DoneTicket;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments.InProgressTicket;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments.ToDoTicket;

public class TabAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 3;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;
    public static final int FRAGMENT_3 = 2;

    public TabAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment;
        switch (position) {
            case FRAGMENT_1:
                fragment = new ToDoTicket();
                break;
            case FRAGMENT_2:
                fragment = new InProgressTicket();
                break;
            default:
                fragment = new DoneTicket();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case FRAGMENT_1: return "ToDo";
            case FRAGMENT_2: return "In progress";
            default: return "Done";
        }
    }
}
