package com.example.beerdelivery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beerdelivery.dados.MeuBanco;
import com.example.beerdelivery.dados.Pedidos;
import com.example.beerdelivery.dados.Usuario;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class Pedir_Bebida extends AppCompatActivity {

    Button btnAdd;
    TextView btMeno, btMaior, valorTotal,preco, unidades, nomeBebida, btn_vot;
    ImageView imgBebiPedido;

    MeuBanco meuBanco;
    Usuario usuario;
    int [] img = {0,1} ;
    int valorUnitario;
    double precoX;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir__bebida);

        meuBanco = new MeuBanco(Pedir_Bebida.this);

        imgBebiPedido = (ImageView) findViewById(R.id.B_image) ;
        btnAdd = (Button) findViewById(R.id.button_add_pedido);
        btMeno = (TextView) findViewById(R.id.B_menor);
        btMaior = (TextView) findViewById(R.id.B_maior);
        valorTotal = (TextView) findViewById(R.id.B_totalpago);
        preco = (TextView) findViewById(R.id.B_valor_unidade);
        unidades = (TextView) findViewById(R.id.B_unidade);
        nomeBebida = (TextView) findViewById(R.id.B_titlebebida);
        btn_vot = (TextView) findViewById(R.id.btn_vot);



        Bundle daddos = getIntent().getExtras();
        if ((daddos!= null) && (daddos.containsKey("Logado"))) {
            usuario = (Usuario) daddos.getSerializable("Logado");

        }else {
            Intent intent = new Intent(Pedir_Bebida.this, Login.class);
            startActivity(intent);
            finish();
        }

            MostrarDados();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPedido();
            }
        });



        valorUnitario = usuario.getUnidadeBebida();

        precoX = usuario.getPrecoBebida();

        btMaior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                precoX = usuario.getPrecoBebida();
                valorUnitario += 1;
                precoX = valorUnitario*precoX;
                DecimalFormat formatador = new DecimalFormat("0.00");
                unidades.setText(""+valorUnitario);
                valorTotal.setText("  Valor Total: R$ " + formatador.format(precoX));
            }
        });



        btMeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (valorUnitario >1) {
                    valorUnitario -= usuario.getUnidadeBebida();
                    precoX -= usuario.getPrecoBebida();
                    DecimalFormat formatador = new DecimalFormat("0.00");
                    unidades.setText("" + valorUnitario);
                    valorTotal.setText("  Valor Total: R$ " + formatador.format(precoX));
                }



            }
        });


        btn_vot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pedir_Bebida.this, Listar_Bebidas.class);
                intent.putExtra("Logado", usuario);
                startActivity(intent);
                finish();
            }
        });



    }


    public void MostrarDados(){

        int [] ImageBebidas = {R.drawable.tnt_original, R.drawable.monster_energetico, R.drawable.baly, R.drawable.red_bull,
                R.drawable.cachaca_51,R.drawable.velho_barreiro, R.drawable.ypioca_outo, R.drawable.ypioca_prata,
                R.drawable.bhrama,R.drawable.bhoemia, R.drawable.skol, R.drawable.heiniken,
                R.drawable.vinho_campos_marina,R.drawable.vinho_campos_reales, R.drawable.vinho_sinuelo,
                R.drawable.grey_goose,R.drawable.belvedere, R.drawable.ciroc, R.drawable.montilla,
                R.drawable.white_horse,R.drawable.red_label, R.drawable.old_parr};




                for (int i = 0; i<ImageBebidas.length; i++ ){

                    if (ImageBebidas[i]== usuario.getIdBebida()){


                        img[0] = ImageBebidas[i];

                    }

                }


                if (img[0]!= 0) {

                    imgBebiPedido.setImageResource(img[0]);



                    nomeBebida.setText(usuario.getNomeProd());
                    double precoV = usuario.getPrecoBebida();
                    DecimalFormat formatador = new DecimalFormat("0.00");
                    preco.setText("R$ "+formatador.format(precoV)+"    ");
                    int unidd = usuario.getUnidadeBebida();
                    unidades.setText(""+unidd);
                    valorTotal.setText("  Valor Total: R$ "+formatador.format(precoV));


                }

    }



    public void AddPedido(){


        Pedidos novoPedido = new Pedidos();

        novoPedido.setIdBebidas(usuario.getIdBebida());
        novoPedido.setImagem(img[0]);
        novoPedido.setNome(usuario.getNomeProd());
        novoPedido.setUnidades(valorUnitario);
        novoPedido.setValorApagar(precoX);
        novoPedido.setIdUser(usuario.getIdUsr());
        novoPedido.setStatus("Aguardando Confirmação");

        long in = meuBanco.NovoPedido(novoPedido);

        if (in!= -1){

            Toast.makeText(Pedir_Bebida.this, "Seu pedido foi adicionado ao carrinho", Toast.LENGTH_LONG ).show();


        }else   Toast.makeText(Pedir_Bebida.this, "Erro no cadastro!", Toast.LENGTH_LONG ).show();





    }

}