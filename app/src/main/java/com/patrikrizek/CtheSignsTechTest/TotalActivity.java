package com.patrikrizek.CtheSignsTechTest;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

public class TotalActivity extends AppCompatActivity {

    // Debug
    private static final String TAG = "TotalActivity";

    // Vars
    String display;
    TextView total;
    int sum;
    int[] values;
    String[] resultArr;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_total );

        // Debug
        Log.d( TAG, "onCreated" );

        total = (TextView) findViewById( R.id.total );

        calculateTotal();
    }

    /*
     * This method calculates total sum of selected numbers.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculateTotal(){

        // Debug
        Log.d( TAG, "calculateTotal: called" );

        // The values from MainActivity are uploaded and used in following array.
        Bundle b = getIntent().getExtras();
        resultArr = b.getStringArray( "selectedItems" );
        values = Arrays.stream( resultArr ).mapToInt( Integer::parseInt ).toArray();

        // Calculating total sum.
        for (int counter = 0; counter < values.length; counter++) {
            sum += values[counter];
        }

        // Transfer integer to string.
        display = Integer.toString( sum );
        total.setText( display );
    }

    /*
     * This method clears saved data activity.
     */
    @Override
    public void onBackPressed() {

        // Debug
        Log.d(TAG, "onBackPressed: clicked");

        Intent intent = new Intent( this, MainActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity( intent );
    }
}