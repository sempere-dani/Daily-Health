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
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Historial extends ActionBarActivity {
    private Context context;
    EditText desde;
    EditText hasta;
    TableLayout tableLayout;
    Button button;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        context = this;
        desde=(EditText)findViewById(R.id.desde);
        hasta=(EditText)findViewById(R.id.hasta);
        tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        button=(Button)findViewById(R.id.btSearch);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                DecimalFormat formatter = new DecimalFormat("00");
                String days = formatter.format(dayOfMonth);
                String months = formatter.format(month+1);



                if (desde.length()==0) {
                    desde.setText(days + "/" + months + "/" + String.valueOf(year));
                    tableLayout.removeAllViews();
                }else{
                    hasta.setText(days + "/" + months + "/" + String.valueOf(year));
                    System.out.println("this is the calendar day:  " + desde+"hasta: "+hasta);

                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=String.valueOf(desde.getText());
                String b=String.valueOf(hasta.getText());
                Search(a,b);
                desde.setText("");
                hasta.setText("");
            }
        });

    }

    public void Search(String dayDesde,String dayHasta) {


        MyDbHelper dataHelper = new MyDbHelper(context);
        SQLiteDatabase db1 = dataHelper.getWritableDatabase();
        TextView textView = (TextView) findViewById(R.id.date1);
        int objetivo = 0;
        // Start the transaction.
        db1.beginTransaction();

        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText = {"TIPO", "DESAYUNO", "ALMUERZO", "COMIDA", "MERIENDA", "CENA","TOTAL", "OBJETIVO"};
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

        try {
           /*  String selectQuery = "SELECT *"+
                    " FROM "+ MyDbHelper.TABLE_NAME+" WHERE fecha BETWEEN '16/05/2015' and " +
                    "'16/05/2015'";*/
           String selectQuery = "SELECT *" +
                    " FROM " + MyDbHelper.TABLE_NAME + " WHERE fecha BETWEEN '" +dayDesde+"'" + "AND" +
                    "'"+dayHasta +"'";

            Cursor cursor = db1.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    // Read columns data
                    final int ID = cursor.getInt(cursor.getColumnIndex("_id"));
                    final String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                    final int desayuno = cursor.getInt(cursor.getColumnIndex("desayuno"));
                    int almuerzo = cursor.getInt(cursor.getColumnIndex("almuerzo"));
                    int comida = cursor.getInt(cursor.getColumnIndex("comida"));
                    int merienda = cursor.getInt(cursor.getColumnIndex("merienda"));
                    int cena = cursor.getInt(cursor.getColumnIndex("cena"));
                    int total=desayuno+almuerzo+comida+cena;

                    System.out.println("VALOR TIPO;  " + tipo);

                    switch (tipo) {
                        case "Agua":
                            objetivo = 8;
                            break;
                        case "Pan,Arroz,Pasta":
                            objetivo = 2;
                            break;
                        case "Verduras":
                            objetivo = 2;
                            break;
                        case "Fruta":
                            objetivo = 2;
                            break;
                        case "Aceite Oliva":
                            objetivo = 2;
                            System.out.println("VALOR TIPO;  " + tipo + "objetivo: " + objetivo);
                            break;
                        case "Frutos Secos":
                            objetivo = 2;
                            break;
                        case "Lacteos":
                            objetivo = 2;
                            break;
                        case "Legumbres":
                            objetivo = 2;
                            break;
                        case "Carne":
                            objetivo = 2;
                            break;
                        case "Huevos":
                            objetivo = 4;
                            break;
                        case "Patatas":
                            objetivo = 3;
                            break;
                        case "Embutido":
                            objetivo = 1;
                            break;
                        default:
                            System.out.println("ERror");


                    }

                    // dara rows
                    TableRow row = new TableRow(context);

                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText = {tipo + "", String.valueOf(desayuno), String.valueOf(almuerzo),
                            String.valueOf(comida), String.valueOf(merienda), String.valueOf(cena),
                            String.valueOf(total),String.valueOf(objetivo)};

                    for (String text : colText) {

                        TextView tv = new TextView(this);
                        final EditText editText = new EditText(this);

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

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db1.endTransaction();
            // End the transaction.
            db1.close();
            // Close database
        }
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historial, menu);
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
