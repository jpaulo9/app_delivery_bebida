package com.example.beerdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beerdelivery.dados.MeuBanco;
import com.example.beerdelivery.dados.Usuario;

public class Login extends AppCompatActivity {

    Button btn;
    TextView conta;
    EditText editTextemail, editTextsenha;
    MeuBanco banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        banco = new MeuBanco(Login.this);
        editTextemail = (EditText) findViewById(R.id.edtemailLogin);
        editTextsenha = (EditText) findViewById(R.id.edtsenhaLogin);

        btn = (Button) findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logar();
            }
        });

        conta = (TextView) findViewById(R.id.cadastroUser);

        conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Usuario_Beer.class);
                startActivity(intent);

            }
        });
    }

    public void Logar(){

        String email, senha, emailAdm = "admin@gmail.com", senhaAdm = "0123";
        email = editTextemail.getText().toString();
        senha = editTextsenha.getText().toString();

        Boolean campL = false;
        CampoValidos validos = new CampoValidos();


        if (campL = validos.Email(email)){
            editTextemail.setError("E-mail inválido");
        }else if (campL = validos.CamposText(senha)){
            editTextsenha.setError("Campo vazio");
        }

        if (!campL){

            Usuario userLogado = banco.DadosLogin(email, senha);


            if (userLogado != null) {

                Toast.makeText(Login.this, "ID "+ userLogado.getIdUsr(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Login.this, LayoutHome.class);
                intent.putExtra("Logado", userLogado);
                startActivity(intent);

            }else if(email.equals(emailAdm) && senha.equals(senhaAdm)) {
//                Intent intent
//                         = new Intent(Login.this, Admin.class);
//                startActivity(intent);

            }else Toast.makeText(Login.this, "Verifique se seus dados estão corretos\n" +
                    "Ou se dirija até a página de cadastro para criar uma conta.", Toast.LENGTH_LONG).show();

        }



    }
}