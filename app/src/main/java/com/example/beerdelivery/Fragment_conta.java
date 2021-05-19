package com.example.beerdelivery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.beerdelivery.dados.MeuBanco;
import com.example.beerdelivery.dados.Usuario;

public class Fragment_conta extends Fragment {

    View view;
    Usuario usuario;
    ImageView foto;

    Bitmap foto_img;

    MeuBanco meuBanco;
    TextView nomeBer, emailBer, senhaBer, editFoto;
    Button salvar, btn_fim;
    EditText edtNome, edtEmail, edtSenha;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_conta, container, false);


        meuBanco = new MeuBanco(getActivity());

        nomeBer = (TextView) view.findViewById(R.id.nome_atual);
        emailBer = (TextView) view.findViewById(R.id.email_atual);
        senhaBer = (TextView) view.findViewById(R.id.senha_atual);
        editFoto = (TextView) view.findViewById(R.id.editFoto);

        edtNome = (EditText) view.findViewById(R.id.edtar_nome);
        edtEmail = (EditText) view.findViewById(R.id.editar_email);
        edtSenha = (EditText) view.findViewById(R.id.editar_senha);

        salvar = (Button) view.findViewById(R.id.btnUp);
        btn_fim = (Button)view.findViewById(R.id.btnSessFim);

        foto = (ImageView) view.findViewById(R.id.imgVer);




        Bundle daddos = getActivity().getIntent().getExtras();
        if ((daddos!= null) && (daddos.containsKey("Logado"))) {
            usuario = (Usuario) daddos.getSerializable("Logado");

        }else {
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
            getActivity().finish();
        }

        VerDados();
        editFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagemPerfil();
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdadteDados();
            }
        });
        btn_fim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 0);

        }
        return view;
    }


    public void ImagemPerfil(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK){

            Bundle extras = data.getExtras();
            foto_img = (Bitmap) extras.get("data");
            foto.setImageBitmap(foto_img);
            upFoto();
        }

        super.onActivityResult(requestCode, resultCode , data);
    }


    public void VerDados(){

        Usuario user = meuBanco.MeusDados(usuario.getIdUsr());

        if (user!=null){

            nomeBer.setText("MEU NOME: "+user.getNome());
            emailBer.setText("MEU E-MAIL: "+user.getEmail());
            senhaBer.setText("MINHA SENHA: "+user.getSenha());
            foto.setImageBitmap(user.getFotoPerfil());


        }else Toast.makeText(getActivity(), "Usuário não encontrado", Toast.LENGTH_LONG).show();


    }

    public void upFoto() {

        if (foto_img != null) {
            Boolean result = meuBanco.FotoUpdate(foto_img, usuario.getIdUsr());

            if (result != false) {
                Toast.makeText(getActivity(), "Foto de Perfil Atualizada", Toast.LENGTH_LONG).show();
            } else Toast.makeText(getActivity(), "Foto não atualizda", Toast.LENGTH_LONG).show();

        }
    }

    public void UpdadteDados(){
        String nome, email, senha;
        nome = edtNome.getText().toString();
        email = edtEmail.getText().toString();
        senha = edtSenha.getText().toString();

        CampoValidos campoValidos = new CampoValidos();
        Usuario upUser= new Usuario();

        Boolean valid = false;
        Boolean validE = false;
        Boolean validS = false;


        if (valid = campoValidos.CamposText(nome)){
            nome = usuario.getNome();

        }
        if (validE = campoValidos.Email(email)) {
            email = usuario.getEmail();


        }if (validS = campoValidos.CamposText(senha)){
            senha = usuario.getSenha();

        }

        upUser.setNome(nome);
        upUser.setEmail(email);
        upUser.setSenha(senha);
        upUser.setIdUsr(usuario.getIdUsr());

        Boolean upDado = meuBanco.AtualizarDados(upUser);

        if (upDado!= false){

            Toast.makeText(getActivity(), "Dados Atulizados!", Toast.LENGTH_LONG).show();
            edtSenha.setText("");
            edtNome.setText("");
            edtEmail.setText("");
            nomeBer.setText("MEU NOME: "+upUser.getNome());
            emailBer.setText("MEU E-MAIL: "+upUser.getEmail());
            senhaBer.setText("MINHA SENHA: "+upUser.getSenha());

        }else Toast.makeText(getActivity(), "Erro ao Atualizar Dados", Toast.LENGTH_LONG).show();




    }


}
