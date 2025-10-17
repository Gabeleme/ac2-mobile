package com.example.atividadeac2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextTitulo, editTextano, editTextdiretor, editTextnota, editTextgenero;

    Button buttonsalvar;

    CheckBox checkBoxcinema;

    ListView listar;

    ArrayAdapter<String> adapter;
    ArrayList<String> listaFilmes;

    ArrayList<Integer> listaIds;

    BancoHelper databaseHelper;

    ListView listViewFilmes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

            editTextTitulo = findViewById(R.id.editTextTitulo);
            editTextano = findViewById(R.id.editTextano);
            editTextdiretor = findViewById(R.id.editTextdiretor);
            editTextnota = findViewById(R.id.editTextnota);
            editTextgenero = findViewById(R.id.editTextgenero);
            listar = findViewById(R.id.listar);
            checkBoxcinema = findViewById(R.id.checkBoxcinema);
            buttonsalvar = findViewById(R.id.buttonsalvar);

            buttonsalvar.setOnClickListener(v -> {
            String titulo = editTextTitulo.getText().toString();
            String diretor = editTextdiretor.getText().toString();
            String ano = editTextano.getText().toString();
            String nota = editTextnota.getText().toString();
            String genero = editTextgenero.getText().toString();
            int viuCinema = checkBoxcinema.isChecked() ? 1 : 0;

            databaseHelper.inserirFilme(titulo, diretor, ano, nota, genero, viuCinema);
            carregarFilmes();
        });
 }
       

        private void carregarFilme() {
            Cursor cursor = databaseHelper.listaFilmes();
            listaFilmes = new ArrayList<>();
            listaIds = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do{
                    int id = cursor.getInt(0);
                    String titulo = cursor.getString(1);
                    String diretor = cursor.getString(2);
                    String ano = cursor.getString(3);
                    String nota = cursor.getString(4);
                    String genero = cursor.getString(5);

                    listaIds.add(id);
                    listaFilmes.add(id + " - " + titulo + " - " + diretor + " - " + ano + " - " + nota + " - " + genero);
                }while (cursor.moveToNext());
            }  
     adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaFilmes);
    listar.setAdapter(adapter);
    }

}
