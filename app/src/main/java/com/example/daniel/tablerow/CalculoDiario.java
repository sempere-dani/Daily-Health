package com.example.daniel.tablerow;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//http://www.worldbestlearningcenter.com/tips/Android-sqlite-table-layout-example.htm
//http://olidroide.blogspot.com.es/2010/02/tabla-dinamica-con-bordes.html
public class CalculoDiario extends ActionBarActivity {

    private Context context;
    private static final String TAG = "MyTag";
    Button tbExit;
    Button tbBusqueda;
    Button btHistorial;

    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculo_diario);

        //campo que muestra la fecha
        textView1=(TextView)findViewById(R.id.date1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT);
        String currentDateTimeString = sdf.format(new Date());


      textView1.setText(currentDateTimeString);

        context = this;

        // Create DatabaseHelper instance
        MyDbHelper dataHelper = new MyDbHelper(context);
        // Insert sample data

        // Reference to TableLayout
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#A9A9A9"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText = {"TIPO", "DESAYUNO", "ALMUERZO", "COMIDA", "MERIENDA", "CENA", "OBJETIVO"};
        for (String c : headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(10);
            tv.setPadding(3, 3, 3, 3);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);

        // Get data from sqlite database and add them to the table
        // Open the database for reading
        Button buttonAdd=(Button)findViewById(R.id.btAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CalculoDiario.this,newData.class);
                startActivity(intent);
            }
        });
        //Definicion botones
        tbExit=(Button)findViewById(R.id.btExit);
        tbExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);


            }
        });

        btHistorial=(Button)findViewById(R.id.btHistorial);
        btHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CalculoDiario.this,Historial.class);
                startActivity(intent);
            }
        });
        load();//cargamos los datos

        tbBusqueda=(Button)findViewById(R.id.button2);
        tbBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CalculoDiario.this,Busqueda.class);
                startActivity(intent);
            }
        });
    }

    public void load(){
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        final MyDbHelper dataHelper = new MyDbHelper(context);
        SQLiteDatabase db1 = dataHelper.getWritableDatabase();
        TextView textView=(TextView)findViewById(R.id.date1);
        // Start the transaction.
        db1.beginTransaction();
        int objetivo=0;

        try
        {
            String selectQuery = "SELECT * FROM "+ MyDbHelper.TABLE_NAME+" WHERE fecha='"+textView1.getText()+"'";
            Cursor cursor = db1.rawQuery(selectQuery,null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Read columns data
                    final int ID=cursor.getInt(cursor.getColumnIndex("_id"));
                    final String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                    final int desayuno = cursor.getInt(cursor.getColumnIndex("desayuno"));
                    int almuerzo = cursor.getInt(cursor.getColumnIndex("almuerzo"));
                    int comida = cursor.getInt(cursor.getColumnIndex("comida"));
                    int merienda = cursor.getInt(cursor.getColumnIndex("merienda"));
                    int cena = cursor.getInt(cursor.getColumnIndex("cena"));



                  switch (tipo){
                      case "Agua":
                          objetivo=8;break;
                      case "Pan,Arroz,Pasta":
                          objetivo=2;break;
                      case "Verduras":
                          objetivo=2;break;
                      case "Fruta":
                          objetivo=2;break;
                      case "Aceite Oliva":
                          objetivo=2;
                          System.out.println("VALOR TIPO;  "+tipo+"objetivo: "+objetivo);
                          break;
                      case "Frutos Secos":
                          objetivo=2;break;
                      case "Lacteos":
                          objetivo=2;break;
                      case "Legumbres":
                          objetivo=2;break;
                      case "Carne":
                          objetivo=2;break;
                      case "Huevos":
                          objetivo=4;break;
                      case "Patatas":
                          objetivo=3;break;
                      case "Embutido":
                          objetivo=1;


                      default:
                          System.out.println("Error");

                        }

                    // data rows
                   TableRow row = new TableRow(context);


                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText={tipo + "", String.valueOf(desayuno), String.valueOf(almuerzo),
                            String.valueOf(comida), String.valueOf(merienda), String.valueOf(cena),
                            String.valueOf(objetivo)};

                   for(String text:colText) {


                        TextView tv = new TextView(this);
                        final EditText editText=new EditText(this);

                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                       tv.setGravity(Gravity.CENTER);
                       tv.hasOnClickListeners();
                       tv.setTextSize(12);
                       tv.setPadding(3, 3, 3, 3);
                       tv.setText(text);
                       tv.setLines(1);
                       // editText.setId(i);
                        row.addView(tv);

                        tv.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                               Intent intent=new Intent(CalculoDiario.this,Update.class);

                                     intent.putExtra("text",tipo);
                                     startActivity(intent);
                                       }
                                                                              }
                        );
                    }

                    tableLayout.addView(row);
                    }
                }


            db1.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db1.endTransaction();
            // End the transaction.
            db1.close();
            // Close database
        }


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
