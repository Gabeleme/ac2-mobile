package com.example.atividadeac2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {
    
    //Nome do banco de dados
    private static final String DATABASE_NAME = "filmesbanco.db";
    private static final int DATABASE_VERSION = 1; //versão do banco de dados

    //definindo o nome da tabela e o nome das colunas 
    private static final String COLUMN_ID = "id";
    private static final String TABLE_NAME = "filmes";
    private static final String COLUMN_TITULO = "titulo";
    private static final String COLUMN_DIRETOR = "diretor";
    private static final String COLUMN_ANOLANCAMENTO = "ano";
    private static final String COLUMN_NOTA = "nota";
    private static final String COLUMN_GENERO = "genero";
    private static final String COLUMN_VIUCINEMA ="viucinema";
    
    //contrutor da classe que passa o nome da tabela e a versão para a super classe
    //inicializa o helper para gerenciar a criação e atualização do banco
    public BancoHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //criação da tabela e das colunas 
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITULO + " TEXT, "
                + COLUMN_DIRETOR + " TEXT, "
                + COLUMN_ANOLANCAMENTO + " INTEGER, "
                + COLUMN_NOTA + " INTEGER, "
                + COLUMN_VIUCINEMA+ " INTEGER, "
                + COLUMN_GENERO + " TEXT)";
        db.execSQL(CREATE_TABLE); //quando executada cria a tabela no banco 

    }

    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //apaga uma tabela e cria uma nova
        onCreate(db);
    }

    public long InserirFilmes (String titulo, String diretor, int ano, int nota, String genero, int viucinema) {
        SQLiteDatabase db = this.getWritableDatabase(); 
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, titulo);
        values.put(COLUMN_DIRETOR, diretor);
        values.put(COLUMN_ANOLANCAMENTO, ano);
        values.put(COLUMN_NOTA, nota);
        values.put(COLUMN_GENERO, genero);
        values.put(COLUMN_VIUCINEMA, viucinema);
        return db.insert(TABLE_NAME,null, values);
    }

    public int atualizarFilmes (int id, String titulo, String diretor, int ano, int nota, String genero, int viucinema){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, titulo);
        values.put(COLUMN_DIRETOR, diretor);
        values.put(COLUMN_ANOLANCAMENTO, ano);
        values.put(COLUMN_NOTA, nota);
        values.put(COLUMN_GENERO, genero);
        values.put(COLUMN_VIUCINEMA, viucinema);
        return db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)}); //atualiza o filme conforme o id passado
    }
    public Cursor listaFilmes(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null); //consulta todos os filmes 
    }

    public Cursor listarFilmesPorGenero(String genero){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE genero = ?", new String[]{genero}); //lista o filme conforme o genero informado
    }

     public int excluirFilme(int id) { 
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}); //exclui um filme pelo id passado 
    }
}


