package com.example.beerdelivery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beerdelivery.dados.Bebidas;
import com.example.beerdelivery.dados.Usuario;


import java.text.DecimalFormat;
import java.util.List;

public class BebidasListasView extends RecyclerView.Adapter<BebidasListasView.MyViewHolder> {

    Context context;
    private static List<Bebidas> bebidaslista;

    public static Usuario usuario;

    public BebidasListasView(Context context, List<Bebidas> bebidas, Usuario user){

        this.context = context;
        this.bebidaslista = bebidas;
        this.usuario = user;




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

            view = LayoutInflater.from(context).inflate(R.layout.dados_bebidas, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view, parent.getContext());
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

                    holder.nomeBebida.setText(bebidaslista.get(position).getNome()+" "+bebidaslista.get(position).getMedida()
                    +" "+bebidaslista.get(position).getTipomedida());
//                    double valo = bebidaslista.get(position).getPreco();
        DecimalFormat formatador = new DecimalFormat("0.00");
                    holder.precoBebida.setText("R$ "+String.valueOf(formatador.format(bebidaslista.get(position).getPreco())));

                    holder.imgBebida.setImageResource(bebidaslista.get(position).getImagem());


    }


    @Override
    public int getItemCount() {
        return bebidaslista.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgBebida;
        private TextView nomeBebida, precoBebida, irBt;




      public MyViewHolder (@NonNull final View itemView, final Context context){
          super(itemView);


                imgBebida = (ImageView) itemView.findViewById(R.id.imgBebList);

                nomeBebida = (TextView) itemView.findViewById(R.id.bebida_nome);
                precoBebida = (TextView) itemView.findViewById(R.id.bebida_preco);
                irBt = (TextView) itemView.findViewById(R.id.seguirB);




                irBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (bebidaslista.size()>0){

                        Bebidas bebida = bebidaslista.get(getLayoutPosition());
                            Intent bIntent = new Intent(context, Pedir_Bebida.class );

                            usuario.setIdBebida(bebida.getImagem());
                            usuario.setNomeProd(bebida.getNome()+" "+bebida.getMedida()+" "+bebida.getTipomedida());
                            usuario.setPrecoBebida(bebida.getPreco());
                            usuario.setUnidadeBebida(bebida.getQuantidade());

                            bIntent.putExtra("Logado", usuario);
                            ((AppCompatActivity) context).startActivityForResult(bIntent, 0) ;


                           }else Toast.makeText(context,"Lista Vazia", Toast.LENGTH_SHORT).show();
                      }
                });






      }




    }



}