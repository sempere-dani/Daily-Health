package com.example.daniel.tablerow;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Busqueda extends ActionBarActivity {
    TableLayout tableLayout;
    private Context context;
    CalendarView calendarView;
    EditText day;
    int objetivo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);


        tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        day = (EditText) findViewById(R.id.day);
        context = this;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                DecimalFormat formatter = new DecimalFormat("00");
                String days = formatter.format(dayOfMonth);
                String months = formatter.format(month+1);
                day.setText(days + "/" + months + "/" + String.valueOf(year));
                System.out.println("this is the calendar day:  " + day);

                load(String.valueOf(day.getText()));

            }
        });




    }
    public void load(String day){
        tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        MyDbHelper dataHelper = new MyDbHelper(context);
        SQLiteDatabase db1 = dataHelper.getWritableDatabase();
        tableLayout.removeAllViews();
        db1.beginTransaction();


        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
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



        try
        {

            System.out.println("Weed day:   "+day);
            String selectQuery = "SELECT * FROM "+ MyDbHelper.TABLE_NAME+" WHERE fecha='"+day+"'";
           // String selectQuery = "SELECT * FROM "+ MyDbHelper.TABLE_NAME+" WHERE fecha='13/05/2015'";
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

                    System.out.println("VALOR TIPO;  "+tipo);

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
                            objetivo=1;break;
                        default:
                            System.out.println("ERror");


                    }

                    // dara rows
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
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);
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
