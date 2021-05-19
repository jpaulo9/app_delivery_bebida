package com.example.beerdelivery;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Rolagem_Tela extends FragmentStatePagerAdapter {

    int Countab;
    @SuppressWarnings("deprecation")
    public Rolagem_Tela(FragmentManager fm, int Contab) {
        super(fm);
        this.Countab = Contab;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position){

            case 0:
               frag = new Fragment_home();
                break;
            case 1:
                frag = new Fragment_bebidas();
                break;
            case 2:
                frag = new Fragment_pedidos();
                break;
            case 3:
                frag = new Fragment_conta();
                break;



        }

        return frag;
    }

    @Override
    public int getCount() {
        return Countab;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
