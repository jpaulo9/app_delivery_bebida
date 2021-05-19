package com.example.beerdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.beerdelivery.dados.Bebidas;
import com.example.beerdelivery.dados.MeuBanco;
import com.example.beerdelivery.dados.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Listar_Bebidas extends AppCompatActivity {

    Usuario usuario;
    RecyclerView listview;
    TextView volt;
    MeuBanco meuBanco;
    List<Bebidas> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar__bebidas);


        listview = (RecyclerView) findViewById(R.id.lista_bebidas_ver);
        volt = (TextView) findViewById(R.id.txtVoltarhome);

        meuBanco = new MeuBanco(Listar_Bebidas.this);
        lista = new ArrayList<>();

        Bundle daddos = getIntent().getExtras();
        if ((daddos!= null) && (daddos.containsKey("Logado"))) {
            usuario = (Usuario) daddos.getSerializable("Logado");

        }else {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
          finish();
        }

        volt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Listar_Bebidas.this, LayoutHome.class);
                intent.putExtra("Logado", usuario);
                startActivity(intent);
            }
        });
       listarbebidas();


    }


    public void listarbebidas(){

        lista.clear();
        int idb = 0;

        if (usuario.getCateg().equals("Whisky")){

            String categoria = "Whisky";
            String [] Tmedida = {"L"};
            String [] nomes = {"White Horse", "Red Label", "Old Parr"};
            double[] preco = {90.00,100.00, 175.00};
            int quantidade = 1;
            int  [] medida ={1};
            int [] bitmaps = {R.drawable.white_horse,R.drawable.red_label, R.drawable.old_parr};

            idb +=1;
            Bebidas whisky = new Bebidas( nomes[0],  categoria, preco[0], quantidade, idb,  Tmedida[0],  medida[0], bitmaps[0]);
            idb +=1;
            Bebidas whisky2 = new Bebidas( nomes[1],  categoria, preco[1], quantidade, idb,  Tmedida[0],  medida[0], bitmaps[1]);
            idb +=1;
            Bebidas whisky3 = new Bebidas(nomes[2],  categoria, preco[2], quantidade, idb,  Tmedida[0],  medida[0], bitmaps[2]);


            lista.add(whisky);
            lista.add(whisky2);
            lista.add(whisky3);


            BebidasListasView bebidasListasView = new BebidasListasView(Listar_Bebidas.this, lista, usuario);
            listview.setLayoutManager(new LinearLayoutManager(Listar_Bebidas.this));
            listview.setHasFixedSize(true);
            listview.setAdapter(bebidasListasView);

        }else if (usuario.getCateg().equals("Vodka")){

            String categoria = "Vodka";
            String [] Tmedida = {"L", "ML"};
            String [] nomes = {"Grey Goose", "Belvedere", "Ciroc", "Montilla"};
            double[] preco = {180.00,200.00, 150.00, 25.00};
            int quantidade = 1;
            int  [] medida ={1, 750};
            int [] bitmaps = {R.drawable.grey_goose,R.drawable.belvedere, R.drawable.ciroc
                    , R.drawable.montilla};

            idb +=1;
            Bebidas vodka = new Bebidas( nomes[0],  categoria, preco[0], quantidade, idb,  Tmedida[0],  medida[0], bitmaps[0]);
            idb +=1;
            Bebidas vodka1 = new Bebidas( nomes[1],  categoria, preco[1], quantidade, idb,  Tmedida[0],  medida[0], bitmaps[1]);
            idb +=1;
            Bebidas vodka2 = new Bebidas(nomes[2],  categoria, preco[2], quantidade, idb,  Tmedida[1],  medida[1], bitmaps[2]);
            idb +=1;
            Bebidas vodka3 = new Bebidas( nomes[3],  categoria, preco[3], quantidade, idb,  Tmedida[0],  medida[0], bitmaps[3]);

            lista.add(vodka);
            lista.add(vodka1);
            lista.add(vodka2);
            lista.add(vodka3);

            BebidasListasView bebidasListasView = new BebidasListasView(Listar_Bebidas.this, lista, usuario);
            listview.setLayoutManager(new LinearLayoutManager(Listar_Bebidas.this));
            listview.setHasFixedSize(true);
            listview.setAdapter(bebidasListasView);



        }else if (usuario.getCateg().equals("Vinho")){


            String categoria = "Vinho";
            String [] Tmedida = {"L", "ML"};
            String [] nomes = {"Campos Marina", "Campos Reales", "Sinuelo"};
            double[] preco = {97.00,235.80, 12.90};
            int quantidade = 1;
            int  [] medida ={1, 750};
            int [] bitmaps = {R.drawable.vinho_campos_marina,R.drawable.vinho_campos_reales, R.drawable.vinho_sinuelo};

            idb +=1;
            Bebidas vinho = new Bebidas( nomes[0],  categoria, preco[0], quantidade, idb,  Tmedida[1],  medida[1], bitmaps[0]);
            idb +=1;
            Bebidas vinho2 = new Bebidas( nomes[1],  categoria, preco[1], quantidade, idb,  Tmedida[1],  medida[1], bitmaps[1]);
            idb +=1;
            Bebidas vinho3 = new Bebidas(nomes[2],  categoria, preco[2], quantidade, idb,  Tmedida[1],  medida[1], bitmaps[2]);


            lista.add(vinho);
            lista.add(vinho2);
            lista.add(vinho3);


            BebidasListasView bebidasListasView = new BebidasListasView(Listar_Bebidas.this, lista, usuario);
            listview.setLayoutManager(new LinearLayoutManager(Listar_Bebidas.this));
            listview.setHasFixedSize(true);
            listview.setAdapter(bebidasListasView);

        }else if (usuario.getCateg().equals("Cerveja")){

            String categoria = "Cerveja";
            String [] Tmedida = {"L", "ML"};
            String [] nomes = {"Brahma", "Bohemia", "Skol", "Heineken"};
            double[] preco = {3.99,4.99, 3.89, 6.05};
            int quantidade = 1;
            int  [] medida ={1,350, 473, 330};
            int [] bitmaps = {R.drawable.bhrama,R.drawable.bhoemia, R.drawable.skol
                    , R.drawable.heiniken};

            idb +=1;
            Bebidas cerveja = new Bebidas( nomes[0],  categoria, preco[0], quantidade, idb,  Tmedida[1],  medida[1], bitmaps[0]);
            idb +=1;
            Bebidas cerveja2 = new Bebidas( nomes[1],  categoria, preco[1], quantidade, idb,  Tmedida[1],  medida[1], bitmaps[1]);
            idb +=1;
            Bebidas cerveja3 = new Bebidas(nomes[2],  categoria, preco[2], quantidade, idb,  Tmedida[1],  medida[2], bitmaps[2]);
            idb +=1;
            Bebidas cerveja4 = new Bebidas( nomes[3],  categoria, preco[3], quantidade, idb,  Tmedida[1],  medida[3], bitmaps[3]);

            lista.add(cerveja);
            lista.add(cerveja2);
            lista.add(cerveja3);
            lista.add(cerveja4);

            BebidasListasView bebidasListasView = new BebidasListasView(Listar_Bebidas.this, lista, usuario);
            listview.setLayoutManager(new LinearLayoutManager(Listar_Bebidas.this));
            listview.setHasFixedSize(true);
            listview.setAdapter(bebidasListasView);


        }else if (usuario.getCateg().equals("Cachaça")){

            String categoria = "Cachaça";
            String [] Tmedida = {"L", "ML"};
            String [] nomes = {"Cachaça 51", "Velho Barreiro", "Ypióca Ouro", "Ypióca Prata"};
            double[] preco = {11.00, 22.00};
            int quantidade = 1;
            int  [] medida ={965, 910};
            int [] bitmaps = {R.drawable.cachaca_51,R.drawable.velho_barreiro, R.drawable.ypioca_outo
                    , R.drawable.ypioca_prata};

            idb +=1;
            Bebidas cach1 = new Bebidas( nomes[0],  categoria, preco[0], quantidade, idb,  Tmedida[1],  medida[0], bitmaps[0]);
            idb +=1;
            Bebidas cach2 = new Bebidas( nomes[1],  categoria, preco[0], quantidade, idb,  Tmedida[1],  medida[1], bitmaps[1]);
            idb +=1;
            Bebidas cach3 = new Bebidas(nomes[2],  categoria, preco[1], quantidade, idb,  Tmedida[1],  medida[0], bitmaps[2]);
            idb +=1;
            Bebidas cach4 = new Bebidas( nomes[3],  categoria, preco[1], quantidade, idb,  Tmedida[1],  medida[0], bitmaps[3]);

            lista.add(cach1);
            lista.add(cach2);
            lista.add(cach3);
            lista.add(cach4);

            BebidasListasView bebidasListasView = new BebidasListasView(Listar_Bebidas.this, lista, usuario);
            listview.setLayoutManager(new LinearLayoutManager(Listar_Bebidas.this));
            listview.setHasFixedSize(true);
            listview.setAdapter(bebidasListasView);

        }else if (usuario.getCateg().equals("Energético")){

            String categoria = "Energético";
            String [] Tmedida = {"L", "ML"};
            String [] nomes = {"Red Bull", "Baly sem Açúcar", "Monster Energy", "TNT Original"};
            double[] preco = {13.00,5.00, 9.09, 6.54};
            int quantidade = 1;
            int  [] medida ={473, 250};
            int [] bitmaps = {R.drawable.red_bull,R.drawable.baly, R.drawable.monster_energetico
                    , R.drawable.tnt_original};

            idb +=1;
            Bebidas energ = new Bebidas( nomes[0],  categoria, preco[0], quantidade, idb,  Tmedida[1],  medida[0], bitmaps[0]);
            idb +=1;
            Bebidas energ1 = new Bebidas( nomes[1],  categoria, preco[1], quantidade, idb,  Tmedida[1],  medida[1], bitmaps[1]);
            idb +=1;
            Bebidas energ2 = new Bebidas(nomes[2],  categoria, preco[2], quantidade, idb,  Tmedida[1],  medida[0], bitmaps[2]);
            idb +=1;
            Bebidas energ3 = new Bebidas( nomes[3],  categoria, preco[3], quantidade, idb,  Tmedida[1],  medida[0], bitmaps[3]);



            lista.add(energ);
            lista.add(energ1);
            lista.add(energ2);
            lista.add(energ3);


            BebidasListasView bebidasListasView = new BebidasListasView(Listar_Bebidas.this, lista, usuario);
            listview.setLayoutManager(new LinearLayoutManager(Listar_Bebidas.this));
            listview.setHasFixedSize(true);
            listview.setAdapter(bebidasListasView);




        }







    }


}