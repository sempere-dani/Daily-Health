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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Update extends ActionBarActivity {

    Context context;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

         button=(Button)findViewById(R.id.btUpdate);
        context = this;
        final EditText desayuno=(EditText)findViewById(R.id.editTextDesayuno);
        final EditText almuerzo=(EditText)findViewById(R.id.editTextAlmuerzo);
        final EditText comida=(EditText)findViewById(R.id.editTextComida);
        final  EditText merienda=(EditText)findViewById(R.id.editTextMerienda);
        final EditText cena=(EditText)findViewById(R.id.editTextCena);
        final EditText gone=(EditText)findViewById(R.id.editTextGone);
        gone.setVisibility(View.INVISIBLE);
        TextView Desayuno=(TextView)findViewById(R.id.textView3);

        TextView Almuerzo=(TextView)findViewById(R.id.textView4);
        TextView Comida=(TextView)findViewById(R.id.textView5);
        TextView Merienda=(TextView)findViewById(R.id.textView6);
        TextView Cena=(TextView)findViewById(R.id.textView8);
//Cambiar tipo litra
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/TAKE ME HOME.ttf");
        Desayuno.setTypeface(type);

        Almuerzo.setTypeface(type);
        Comida.setTypeface(type);
        Merienda.setTypeface(type);
        Cena.setTypeface(type);

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
                    button.setEnabled(false);
                }else{
                    button.setEnabled(true);
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
                    button.setEnabled(false);
                }else{
                    button.setEnabled(true);
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
                    button.setEnabled(false);
                }else{
                    button.setEnabled(true);
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
                    button.setEnabled(false);
                }else{
                    button.setEnabled(true);
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
                    button.setEnabled(false);
                }else{
                    button.setEnabled(true);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {



                MyDbHelper db1=new MyDbHelper(context);
                SQLiteDatabase db=db1.getWritableDatabase();
                ContentValues update=new ContentValues();


                update.put("desayuno",Integer.parseInt(desayuno.getText().toString()));
                update.put("almuerzo",Integer.parseInt(almuerzo.getText().toString()));
                update.put("comida",Integer.parseInt(comida.getText().toString()));
                update.put("merienda",Integer.parseInt(merienda.getText().toString()));
                update.put("cena",Integer.parseInt(cena.getText().toString()));

                db.update(MyDbHelper.TABLE_NAME,update,"_id='"+Integer.parseInt(gone.getText().toString())+"'",null);


                db.close();

                Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, CalculoDiario.class));
                finish();
            }

        });



        loadData();
    }
    public void loadData() {
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        final  EditText EDdesayuno=(EditText) findViewById(R.id.editTextDesayuno);
        final  EditText EDalmuerzo=(EditText)findViewById(R.id.editTextAlmuerzo);
        final  EditText EDcomida=(EditText) findViewById(R.id.editTextComida);
        final  EditText EDmerienda=(EditText) findViewById(R.id.editTextMerienda);
        final EditText EDcena=(EditText) findViewById(R.id.editTextCena);
        final TextView tvTipo=(TextView)findViewById(R.id.tvTipo);

        Intent intent=getIntent();
        tvTipo.setText(intent.getStringExtra("text"));

        final EditText gone=(EditText)findViewById(R.id.editTextGone);
        gone.setVisibility(View.INVISIBLE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT);
        String currentDateTimeString = sdf.format(new Date());

        final MyDbHelper dataHelper = new MyDbHelper(this);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();


        try {
            String selectQuery = "SELECT * FROM " + MyDbHelper.TABLE_NAME +
                    " WHERE fecha='"+currentDateTimeString+"'"+" and tipo='"+tvTipo.getText().toString()+"'";

            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    // Read columns data
                    final int ID = cursor.getInt(cursor.getColumnIndex("_id"));
                    String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                    final int desayuno = cursor.getInt(cursor.getColumnIndex("desayuno"));
                    int almuerzo = cursor.getInt(cursor.getColumnIndex("almuerzo"));
                    int comida = cursor.getInt(cursor.getColumnIndex("comida"));
                    int merienda = cursor.getInt(cursor.getColumnIndex("merienda"));
                    int cena = cursor.getInt(cursor.getColumnIndex("cena"));
                    int suma = desayuno + almuerzo + comida + merienda + cena;

                    // dara rows
                    tvTipo.setText(tipo);
                    gone.setText(String.valueOf(ID));
                    EDdesayuno.setText(String.valueOf(desayuno));
                    EDalmuerzo.setText(String.valueOf(almuerzo));
                    EDcomida.setText(String.valueOf(comida));
                    EDmerienda.setText(String.valueOf(merienda));
                    EDcena.setText(String.valueOf(cena));


                }


            }
        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database

        }


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
   /* @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
    }*/
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, CalculoDiario.class));
        finish();
    }
}
