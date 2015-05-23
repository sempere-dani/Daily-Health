package com.example.daniel.tablerow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class Main extends ActionBarActivity {
    //Declaramos los botones

    ImageButton piramide;
    ImageButton Calc;
    ImageButton Recetas;
    ImageButton Equivalencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Ocultat action bar

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Configurar los botones
        piramide=(ImageButton)findViewById(R.id.ibPiramide);
         Calc=(ImageButton)findViewById(R.id.ibCalculo);
         Recetas=(ImageButton)findViewById(R.id.ibRecetas);
         Equivalencias=(ImageButton)findViewById(R.id.ibEquivalencias);

        //Asignar los listeners

       piramide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this,Piramide.class);
                startActivity(intent);
            }
        });


        Recetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent=new Intent(Main.this,Recetas.class);
                startActivity(intent);
            }
        });
        Equivalencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(Main.this,Equivalencias.class);
                startActivity(intent);
            }
        });
        Calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this,CalculoDiario.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
