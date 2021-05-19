package com.example.beerdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.beerdelivery.dados.Usuario;

public class Fragment_bebidas extends Fragment {

        View view;
        Usuario usuario;
        ImageView vodka, vinho, whisky, cerveja, energetico, cachaca;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bebidas, container, false);

        vodka = (ImageView) view.findViewById(R.id.imgVod);
        vinho = (ImageView) view.findViewById(R.id.imgVin);
        whisky = (ImageView) view.findViewById(R.id.imgW);
        cerveja = (ImageView) view.findViewById(R.id.imgCerv);
        energetico = (ImageView) view.findViewById(R.id.imgEnerg);
        cachaca = (ImageView) view.findViewById(R.id.imgCach);


        Bundle daddos = getActivity().getIntent().getExtras();
        if ((daddos!= null) && (daddos.containsKey("Logado"))) {
            usuario = (Usuario) daddos.getSerializable("Logado");

        }else {
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
            getActivity().finish();
        }

        Chamarlistas();

        return view;
    }

    public void Chamarlistas(){


        vodka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setCateg("Vodka");
                Intent intent = new Intent(getActivity(), Listar_Bebidas.class);
                intent.putExtra("Logado", usuario);
                startActivity(intent);
                getActivity().finish();
            }
        });
        vinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setCateg("Vinho");
                Intent intent = new Intent(getActivity(), Listar_Bebidas.class);
                intent.putExtra("Logado", usuario);
                startActivity(intent);
                getActivity().finish();
            }
        });
        whisky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setCateg("Whisky");
                Intent intent = new Intent(getActivity(), Listar_Bebidas.class);
                intent.putExtra("Logado", usuario);
                startActivity(intent);
                getActivity().finish();
            }
        });
        cerveja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setCateg("Cerveja");
                Intent intent = new Intent(getActivity(), Listar_Bebidas.class);
                intent.putExtra("Logado", usuario);
                startActivity(intent);
                getActivity().finish();
            }
        });

        energetico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setCateg("Energético");
                Intent intent = new Intent(getActivity(), Listar_Bebidas.class);
                intent.putExtra("Logado", usuario);
                startActivity(intent);
                getActivity().finish();
            }
        });

        cachaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setCateg("Cachaça");
                Intent intent = new Intent(getActivity(), Listar_Bebidas.class);
                intent.putExtra("Logado", usuario);
                startActivity(intent);
                getActivity().finish();
            }
        });



    }
}
