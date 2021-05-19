package com.example.beerdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beerdelivery.dados.MeuBanco;
import com.example.beerdelivery.dados.Pedidos;
import com.example.beerdelivery.dados.Usuario;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Fragment_pedidos extends Fragment {

    View view;
    Usuario usuario;
    RecyclerView listView;
    TextView Tx;
    List<Pedidos> listaPedidos;
    MeuBanco meuBanco;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frament_pedidos, container, false);

        meuBanco = new MeuBanco(getActivity());
        listView = (RecyclerView) view.findViewById(R.id.lista_pedidos);
        Tx = (TextView) view.findViewById(R.id.txtPedidosvazio);
        listaPedidos = new ArrayList<>();

        Bundle daddos = getActivity().getIntent().getExtras();

        if ((daddos!= null) && (daddos.containsKey("Logado"))) {
            usuario = (Usuario) daddos.getSerializable("Logado");

        }else {
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
            getActivity().finish();
        }

        ListarMpedidos();

        return view;
    }


    public void ListarMpedidos(){

        listaPedidos.clear();

        listaPedidos = meuBanco.MeusPedidos(usuario.getIdUsr());

        if (!listaPedidos.isEmpty())
        {
            BebidasListasPedidos bebidasListasPedidos = new BebidasListasPedidos(getActivity(), listaPedidos, usuario);
            listView.setLayoutManager(new LinearLayoutManager(getActivity()));
            listView.setHasFixedSize(true);
            listView.setAdapter(bebidasListasPedidos);



        }else Tx.setText("Você não adicionou nenhum pedido às compras!");




    }
}
