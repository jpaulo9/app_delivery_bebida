package com.example.beerdelivery.dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MeuBanco extends SQLiteOpenHelper {

    private static final String TAB_USER ="USUARIO";
    private static final String TAB_PEDIDOS = "PEDIDOS";

    private static final String BANCO ="DADOSBEER";


    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[]imagem;


    public MeuBanco(Context context){
        super(context,BANCO, null, 2);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlInsertUse = "create table "+ TAB_USER+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT , nome varchar(20), email varchar(100), senha varchar(20), foto BLOB );";

        String sqlPedidos = "create table "+TAB_PEDIDOS+"(id_pedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idUser INTEGER, idBebidas INTEGER, unidades INTEGER, nomeProd varchar(40), valorpagar float," +
                " image INTEGER, status varchar(50));";
        //(int idUser, int idBebidas, int unidades, String status, double valorApagar)

//        (String nome, String categoria, double preco, int quantidade, int idBe, String medida, Bitmap bebida)

        db.execSQL(sqlInsertUse);
        db.execSQL(sqlPedidos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TAB_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_PEDIDOS);


        onCreate(db);
    }


    public long NovoUsuario(String nome, String email, String senha, Bitmap bitmap){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Bitmap bitmapStore = bitmap;
        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapStore.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imagem = byteArrayOutputStream.toByteArray();

        ContentValues values = new ContentValues();

        values.put("nome", nome);
        values.put("email", email);
        values.put("senha", senha);
        values.put("foto", imagem);


        return sqLiteDatabase.insert(TAB_USER, null, values);
    }


    public long NovoPedido(Pedidos novoP){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       //"idUser INTEGER, idBebidas INTEGER, unidades INTEGER, status varchar(40), valorpagar float)"


        ContentValues values = new ContentValues();

        values.put("idUser", novoP.getIdUser());
        values.put("idBebidas", novoP.getIdBebidas());
        values.put("unidades", novoP.getUnidades());
        values.put("nomeProd", novoP.getNome());
        values.put("valorpagar", novoP.getValorApagar());
        values.put("status", novoP.getStatus());




        return sqLiteDatabase.insert(TAB_PEDIDOS, null, values);
    }



    public Usuario DadosLogin(String emails, String senhas){

        SQLiteDatabase  liteDatabase = this.getReadableDatabase();

        String sqlString ="select id, nome, email, senha, foto from "+TAB_USER+" where email ='"+emails+
                "' and senha ='"+senhas+"'";

        Cursor cursor = liteDatabase.rawQuery(sqlString,null);


        Usuario usuario = new Usuario();
        int idUser = 0;

        if( cursor.moveToFirst()){

            String  nome, email, senha;
            idUser = cursor.getInt(0);
            nome = cursor.getString(1);
            email = cursor.getString(2);
            senha = cursor.getString(3);

//            byte[] imgNew = cursor.getBlob(4);
//            Bitmap imgBtmap = BitmapFactory.decodeByteArray(imgNew, 0, imgNew.length);

            usuario.setIdUsr(idUser);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
//            usuario.setFotoPerfil(imgBtmap);
//            usuario.setIdBebida(0);



        }else {

            usuario = null;
        }

        return usuario;
    }

    public Boolean FotoUpdate( Bitmap bitmap, int idUser){


        SQLiteDatabase  liteDatabase = this.getReadableDatabase();
        Bitmap bitmapStore = bitmap;


        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapStore.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imagem = byteArrayOutputStream.toByteArray();

        ContentValues value = new ContentValues();

        value.put("foto", imagem);




        long result = liteDatabase.update(TAB_USER,value, "id = ?", new String[]{String.valueOf(idUser)});

        if (result == 1){

            return  true;

        }
        else {
            return false;
        }

    }

    public Boolean AtualizarDados(Usuario usuario){


        SQLiteDatabase  liteDatabase = this.getReadableDatabase();

        Boolean upDados =  null;
        Usuario upUser = usuario;
        ContentValues values = new ContentValues();



       values.put("nome", upUser.getNome());
       values.put("email", upUser.getEmail());
        values.put("senha", upUser.getSenha());




        long result = liteDatabase.update(TAB_USER,values,
                "id = ?", new String[]{String.valueOf(upUser.getIdUsr())});

        if (result == 1){

            upDados = true;

        }else{
            upDados = false;
        }


        return upDados;
    }

    public List<Pedidos> MeusPedidos(int idUser){

        SQLiteDatabase database = this.getReadableDatabase();


        String sqlString ="select nomeProd, idUser, unidades, valorpagar, status, idBebidas from "+TAB_PEDIDOS+"" +
                " where idUser ="+idUser;
        ArrayList<Pedidos> listPedidos = new ArrayList<>();

        Cursor cursor = database.rawQuery(sqlString,null);



        if (cursor.getCount()!= 0){

            while (cursor.moveToNext()) {

                Pedidos mpedido = new Pedidos() ;

                String nomeProd, status;
                int idU, idB, unidade;
                double valorPag;
                nomeProd = cursor.getString(0);
                status = cursor.getString(4);
                idB = cursor.getInt(5);
                idU = cursor.getInt(1);
                unidade = cursor.getInt(2);
                 valorPag = cursor.getFloat(3);

               mpedido.setStatus(status);
               mpedido.setIdUser(idU);
               mpedido.setValorApagar(valorPag);
               mpedido.setUnidades(unidade);
               mpedido.setIdBebidas(idB);
               mpedido.setNome(nomeProd);



                listPedidos.add(mpedido);

            }

        }


        return listPedidos;
    }


    public Boolean UpStatus(String status, int idB, int idUser){

        SQLiteDatabase  liteDatabase = this.getReadableDatabase();

        Boolean upDados =  null;
        String upStats = status;
        ContentValues values = new ContentValues();


        values.put("status", upStats);



        long result = liteDatabase.update(TAB_PEDIDOS,values,
                "idUser = ? and idBebidas = ?", new String[]{String.valueOf(idUser), String.valueOf(idB)});

        if (result == 1){

            upDados = true;

        }else{
            upDados = false;
        }


        return upDados;
    }

    public boolean RemoverPedido(int idB, int idUser){


        SQLiteDatabase  mexclui = this.getReadableDatabase();


        long res = mexclui.delete(TAB_PEDIDOS, "idUser = ? and idBebidas = ?", new String[]{String.valueOf(idUser),
                String.valueOf(idB)});

        if (res == -1){

            return false;

        }else {
            return true;
        }



    }

    public Usuario MeusDados(int idUser){

        SQLiteDatabase database = this.getReadableDatabase();


        String sqlString ="select nome, email, senha, foto from "+TAB_USER+"" +
                " where id ="+idUser;

        Cursor cursor = database.rawQuery(sqlString,null);


        Usuario user = new Usuario() ;

        if (cursor.getCount()!= 0){

            if (cursor.moveToNext()) {


                String nome, email, senha;

                nome = cursor.getString(0);
                email = cursor.getString(1);
                senha = cursor.getString(2);

                byte[] perfilBytes = cursor.getBlob(3);
                Bitmap fotBmap = BitmapFactory.decodeByteArray(perfilBytes, 0, perfilBytes.length);


                user.setNome(nome);
                user.setEmail(email);
                user.setSenha(senha);
                user.setFotoPerfil(fotBmap);




            }

        }


        return user;
    }





}
