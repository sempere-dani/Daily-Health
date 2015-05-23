package com.example.daniel.tablerow;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Recetas extends Activity implements AdapterView.OnItemClickListener {

    // variables declaration
    ArrayList<Data> arrayData;
    ListView listViewData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);


        arrayData = new ArrayList<Data>();

        //fill cars data
        Data data1=new Data("Arguiñano","http://www.hogarutil.com/cocina/",
                "web de gran utilidad para la elaboración de recetas",R.drawable.hogar_util);
        Data data2=new Data("Recetas.net","http://www.recetas.net/",
                "Agrupacion de recetas variadas",R.drawable.recetas_net);
        Data data3=new Data("Gastronomia&Cia","http://www.gastronomiaycia.com/category/recetas/",
                "Diversidad de recetas",R.drawable.gastronomia);
        Data data4=new Data("Recetas de Rechupete","http://recetasderechupete.hola.com/",
                "Su nombre invia a probar, a que esperas",R.drawable.rechupete);
        Data data5=new Data("Javier Recetas","http://javirecetas.hola.com/",
                "Una nueva propuesta de recetas",R.drawable.javier);
        Data data6=new Data("Thermomix","http://thermomix.vorwerk.es/home/",
                "Otra forma de cocinar",R.drawable.thermomix);

        arrayData.add(data1);
        arrayData.add(data2);
        arrayData.add(data3);
        arrayData.add(data4);
        arrayData.add(data5);
        arrayData.add(data6);

        // Get the ListView by Id and instantiate the adapter with
        // cars data and then set it the ListView
        listViewData = (ListView) findViewById(R.id.list_data);

        MyAdapter myAdapter = new MyAdapter(this,arrayData);


        listViewData.setAdapter(myAdapter);
        // Set the onItemClickListener on the ListView to listen for items clicks
        listViewData.setOnItemClickListener(this);


    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Data selected = arrayData.get(position);

        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(selected.getUrl())));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recetas, menu);
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
