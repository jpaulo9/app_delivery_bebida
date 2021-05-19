package com.example.beerdelivery;

import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beerdelivery.dados.Bebidas;
import com.example.beerdelivery.dados.MeuBanco;
import com.example.beerdelivery.dados.Pedidos;
import com.example.beerdelivery.dados.Usuario;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class BebidasListasPedidos extends RecyclerView.Adapter<BebidasListasPedidos.MyViewHolder> {

    Context context;
    private static List<Pedidos> bebidaslista;

    public static Usuario usuario;

    public BebidasListasPedidos(Context context, List<Pedidos> bebidas, Usuario user){

        this.context = context;
        this.bebidaslista = bebidas;
        this.usuario = user;




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

            view = LayoutInflater.from(context).inflate(R.layout.dados_pedidos, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view, parent.getContext());
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

                    holder.nomeBebida.setText("  "+bebidaslista.get(position).getNome());

                         DecimalFormat formatador = new DecimalFormat("0.00");
                    holder.precoBebida.setText("  Valor à Pagar: R$ "+String.valueOf(
                            formatador.format(bebidaslista.get(position).getValorApagar())));

                    holder.imgBebida.setImageResource(bebidaslista.get(position).getIdBebidas());
                    holder.quantidade.setText("  Unidade(s): "+bebidaslista.get(position).getUnidades());

                    holder.status.setText("  Status: "+bebidaslista.get(position).getStatus());

                    if (bebidaslista.get(position).getStatus().equals("Confirmado")){

                        holder.addCompra.setEnabled(false);
                        holder.addCompra.setText("Enviado");


                    }

    }


    @Override
    public int getItemCount() {
        return bebidaslista.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgBebida;
        private TextView nomeBebida, precoBebida, quantidade, status;

        private Button addCompra, removeItem;




      public MyViewHolder (@NonNull final View itemView, final Context context){
          super(itemView);


                imgBebida = (ImageView) itemView.findViewById(R.id.imgVerBebida);

                nomeBebida = (TextView) itemView.findViewById(R.id.txtNomeBebida);
                precoBebida = (TextView) itemView.findViewById(R.id.txtPreco);
                quantidade = (TextView) itemView.findViewById(R.id.txtQuantidade);
                addCompra = (Button) itemView.findViewById(R.id.btn_enviar_pedido);
                status = (TextView) itemView.findViewById(R.id.txtStatus);
                removeItem = (Button) itemView.findViewById(R.id.btn_remove_pedido);


                addCompra.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (bebidaslista.size() > 0) {

                        Pedidos bebida = bebidaslista.get(getLayoutPosition());

                            MeuBanco  meuBanco = new MeuBanco(context);

                            Boolean rest = meuBanco.UpStatus("Confirmado", bebida.getIdBebidas(), bebida.getIdUser());

                            if (rest == true){

                                Notificar_Pedido novaNotificao = new
                                        Notificar_Pedido(context);

                                NotificationCompat.Builder builder =
                                        novaNotificao.builder
                                                (usuario.getNome()+" Obrigado pela preferência"
                                                        ,"Seu pedido está há caminho!" );

                                novaNotificao.getManager().notify(new Random().nextInt(), builder.build());
                                addCompra.setEnabled(false);

                            }else Toast.makeText(context, "Erro na confirmação", Toast.LENGTH_LONG).show();


                        }
                    }
                });


                removeItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Pedidos bebida = bebidaslista.get(getLayoutPosition());
                        removeItem.setEnabled(false);
                        addCompra.setEnabled(false);
                        MeuBanco  meuBanco = new MeuBanco(context);
                        Boolean rest = meuBanco.RemoverPedido(bebida.getIdBebidas(), bebida.getIdUser());

                        if (rest == true){
                            Toast.makeText(context, "Pedido Removido", Toast.LENGTH_LONG).show();


                        }else Toast.makeText(context, "Erro na confirmação", Toast.LENGTH_LONG).show();


                    }
                });






      }




    }



}