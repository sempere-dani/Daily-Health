package com.example.daniel.tablerow;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class newData extends ActionBarActivity  {
     Context context;
     Spinner spinner;
    Button buttonNew;
    MyDbHelper dataHelper;

    SQLiteDatabase db;
      EditText EDdesayuno;
      EditText EDalmuerzo;
      EditText EDcomida;
      EditText EDmerienda;
     EditText EDcena;

    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);

         context = this;
        final EditText desayuno=(EditText)findViewById(R.id.editTextDesayuno);
        final EditText almuerzo=(EditText)findViewById(R.id.editTextAlmuerzo);
        final EditText comida=(EditText)findViewById(R.id.editTextComida);
        final  EditText merienda=(EditText)findViewById(R.id.editTextMerienda);
        final EditText cena=(EditText)findViewById(R.id.editTextCena);
        final EditText gone=(EditText)findViewById(R.id.editTextGone);
        TextView Desayuno=(TextView)findViewById(R.id.textView3);
        TextView titulo=(TextView)findViewById(R.id.textView10);
        TextView Almuerzo=(TextView)findViewById(R.id.textView4);
        TextView Comida=(TextView)findViewById(R.id.textView5);
        TextView Merienda=(TextView)findViewById(R.id.textView6);
        TextView Cena=(TextView)findViewById(R.id.textView8);
        buttonNew=(Button)findViewById(R.id.btGuardar);
        //creamos un campo fecha que no esta visible para poder hacer las busqueas
        gone.setVisibility(View.INVISIBLE);
        spinner =(Spinner)findViewById(R.id.spinner);

        //Cambiar tipo litra
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/TAKE ME HOME.ttf");
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/CFSchoolHandwriting-Regular.ttf");

        Desayuno.setTypeface(type);
        titulo.setTypeface(type2);
        Almuerzo.setTypeface(type);
        Comida.setTypeface(type);
        Merienda.setTypeface(type);
        Cena.setTypeface(type);


        //Configurar apariencia spinner
        ArrayAdapter<CharSequence> foodadapter = ArrayAdapter.createFromResource(
                this, R.array.tipos, R.layout.custom_spinner);
        foodadapter.setDropDownViewResource(R.layout.custom_spinner);
        spinner.setAdapter(foodadapter);


        desayuno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(TextUtils.isEmpty(desayuno.getText().toString())){
                    Toast.makeText(context, "introducir un valor", Toast.LENGTH_SHORT).show();
                    buttonNew.setEnabled(false);
                }else{
                    buttonNew.setEnabled(true);
                }
               }
        });

        almuerzo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(TextUtils.isEmpty(almuerzo.getText().toString())){
                    Toast.makeText(context, "introducir un valor", Toast.LENGTH_SHORT).show();
                    buttonNew.setEnabled(false);
                }else{
                    buttonNew.setEnabled(true);
                }
            }
        });
        comida.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(TextUtils.isEmpty(comida.getText().toString())){
                    Toast.makeText(context, "introducir un valor", Toast.LENGTH_SHORT).show();
                    buttonNew.setEnabled(false);
                }else{
                    buttonNew.setEnabled(true);
                }
            }
        });
        merienda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(TextUtils.isEmpty(merienda.getText().toString())){
                    Toast.makeText(context, "introducir un valor", Toast.LENGTH_SHORT).show();
                    buttonNew.setEnabled(false);
                }else{
                    buttonNew.setEnabled(true);
                }
            }
        });
        cena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(TextUtils.isEmpty(cena.getText().toString())){
                    Toast.makeText(context, "introducir un valor", Toast.LENGTH_SHORT).show();
                    buttonNew.setEnabled(false);
                }else{
                    buttonNew.setEnabled(true);
                }
            }
        });



         buttonNew=(Button)findViewById(R.id.btGuardar);
        spinnerSet();
        buttonNew.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Spinner spinner=(Spinner)findViewById(R.id.spinner);
                 //dataHelper=new MyDbHelper(context);
                 db=dataHelper.getWritableDatabase();
                ContentValues nuevo=new ContentValues();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT);
                String currentDateTimeString = sdf.format(new Date());



                    nuevo.put("fecha", currentDateTimeString);
                    nuevo.put("tipo", spinner.getSelectedItem().toString());
                    nuevo.put("desayuno", Integer.parseInt(desayuno.getText().toString()));
                    nuevo.put("almuerzo", Integer.parseInt(almuerzo.getText().toString()));
                    nuevo.put("comida", Integer.parseInt(comida.getText().toString()));
                    nuevo.put("merienda", Integer.parseInt(merienda.getText().toString()));
                    nuevo.put("cena", Integer.parseInt(cena.getText().toString()));

                    db.insert(MyDbHelper.TABLE_NAME, null, nuevo);

                    db.close();

                    Toast.makeText(context, "Grabado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, CalculoDiario.class));
                finish();

                }


        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_data, menu);
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
    @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, CalculoDiario.class));
        finish();
    }

    public void spinnerSet() {


        dataHelper = new MyDbHelper(context);
        //db = dataHelper1.getReadableDatabase();
        db = dataHelper.getReadableDatabase();
        // Start the transaction.
        //db.beginTransaction();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //MyDbHelper dataHelper = new MyDbHelper(this);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT);
                String currentDateTimeString = sdf.format(new Date());
                EditText EDdesayuno=(EditText) findViewById(R.id.editTextDesayuno);
                EDalmuerzo=(EditText)findViewById(R.id.editTextAlmuerzo);
                EDcomida=(EditText) findViewById(R.id.editTextComida);
                EDmerienda=(EditText) findViewById(R.id.editTextMerienda);
                EDcena=(EditText) findViewById(R.id.editTextCena);

                try {
                    String selectQuery = "SELECT * FROM " + MyDbHelper.TABLE_NAME +
                            " WHERE fecha='" + currentDateTimeString + "'" + "and tipo='" + spinner.getSelectedItem() + "'";

                    Cursor cursor = db.rawQuery(selectQuery, null);
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext()) {
                            Toast.makeText(getApplicationContext(), "tipo repetido",
                                    Toast.LENGTH_SHORT).show();
                            buttonNew.setEnabled(false);
                            EDdesayuno.setEnabled(false);
                            EDalmuerzo.setEnabled(false);
                            EDcomida.setEnabled(false);
                            EDmerienda.setEnabled(false);
                            EDcena.setEnabled(false);
                        }
                    } else {buttonNew.setEnabled(true);
                        EDdesayuno.setEnabled(true);
                        EDalmuerzo.setEnabled(true);
                        EDcomida.setEnabled(true);
                        EDmerienda.setEnabled(true);
                        EDcena.setEnabled(true);
                        //db.endTransaction();
                        //db.close();
                    }
                } catch (SQLiteException e) {
                    e.printStackTrace();

                } finally {
                    //db.endTransaction();
                    // End the transaction.
                  //  db.close();
                    // Close database

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }





}
