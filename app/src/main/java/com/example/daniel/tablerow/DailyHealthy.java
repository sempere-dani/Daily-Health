package com.example.daniel.tablerow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;


public class DailyHealthy extends Activity {

    private static final long DELAY=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        //Creamos el timer para que quite el logo y pase a la ventana principal

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
// Start the next activity
                Intent mainIntent = new Intent().setClass(
                        DailyHealthy.this, Main.class);
                startActivity(mainIntent);

                finish();
            }
        };
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();

        timer.schedule(task, DELAY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_healthy, menu);
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
