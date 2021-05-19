package com.example.beerdelivery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beerdelivery.dados.MeuBanco;
import com.example.beerdelivery.dados.Usuario;

public class Usuario_Beer extends AppCompatActivity {

    ImageView foto;

    Bitmap foto_img;

    MeuBanco meuBanco;
    EditText nomeEdtit, emailEdit, senhaEdit;
    Button btaddUser;
        TextView btnVotLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario__beer);

        foto = (ImageView) findViewById(R.id.imgUserNew);
        nomeEdtit = (EditText) findViewById(R.id.edt_Unome);
        emailEdit = (EditText) findViewById(R.id.edt_Uemail);
        senhaEdit = (EditText) findViewById(R.id.edt_Usenha);

        meuBanco = new MeuBanco(Usuario_Beer.this);
        btaddUser = (Button) findViewById(R.id.btn_cad);
        btnVotLog = (TextView) findViewById(R.id.btnVcad);

        btnVotLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Usuario_Beer.this, Login.class);
                startActivity(intent);
            }
        });

        btaddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NovoUsuario();
            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagemPerfil();
            }
        });

        if (ActivityCompat.checkSelfPermission(Usuario_Beer.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Usuario_Beer.this, new String[] {Manifest.permission.CAMERA}, 0);

        }

    }

    public void ImagemPerfil(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == Usuario_Beer.RESULT_OK){

            Bundle extras = data.getExtras();
            foto_img = (Bitmap) extras.get("data");
            foto.setImageBitmap(foto_img);

        }

        super.onActivityResult(requestCode, resultCode , data);
    }


    public void NovoUsuario(){

        Boolean dados = false;

        CampoValidos campoValidos = new CampoValidos();

        String nome, email, senha;
        nome = nomeEdtit.getText().toString();
        email = emailEdit.getText().toString();
        senha = senhaEdit.getText().toString();

        if (dados = campoValidos.CamposText(nome)){
            nomeEdtit.setError("Campo Vazio");

        }else if (dados= campoValidos.Email(email)){
            emailEdit.setError("Email inválido");

        }else if (dados = campoValidos.CamposText(senha)){
            senhaEdit.setError("Campo Vazio");
        }

        if (!dados){

            if (foto_img != null && foto.getDrawable()!= null) {

                long n = meuBanco.NovoUsuario(nome, email, senha, foto_img);

                if (n!= -1) {
                    Toast.makeText(Usuario_Beer.this, "Cadastro finalizado", Toast.LENGTH_LONG).show();
                    emailEdit.setText("");
                    nomeEdtit.setText("");
                    senhaEdit.setText("");
                    foto.setImageResource(R.drawable.ic_baseline_add_a_photo_24);

                }else Toast.makeText(Usuario_Beer.this, "Erro no banco de dados", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(Usuario_Beer.this, "Por favor Insira uma foto", Toast.LENGTH_LONG ).show();



        }


    }






}