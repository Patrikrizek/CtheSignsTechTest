package com.patrikrizek.CtheSignsTechTest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Debug
    private static final String TAG = "MainActivity";

    // Vars
    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // Debug
        Log.d( TAG, "calculateTotal: called" );

        listView = (ListView) findViewById( R.id.list );
        button = (Button) findViewById( R.id.total_btn );

        // This creates array of number items.
        String[] numbers = getResources().getStringArray( R.array.number_array );

        // Adapter for List of Items. This recycling the items in the list.
        adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_multiple_choice, numbers );

        // This allow multi selection by checkboxes.
        listView.setChoiceMode( ListView.CHOICE_MODE_MULTIPLE );
        listView.setAdapter( adapter );
        button.setOnClickListener( this );

    }

    /*
     * This method is responsible for control which checkboxes in each item of the list are checked.
     */
    @Override
    public void onClick(View view) {

        // Debug
        Log.d( TAG, "onClick" );

        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selected = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {

            // Position in Adapter.
            int position = checked.keyAt( i );

            // Is position checked.
            if (checked.valueAt( i )) ;
            selected.add( adapter.getItem( position ) );
        }

        String[] results = new String[selected.size()];
        for (int i = 0; i < selected.size(); i++) {
            results[i] = selected.get( i );
        }

        Intent intent = new Intent( getApplicationContext(), TotalActivity.class );

        // Sending selected values to TotalActivity.
        Bundle b =  new Bundle();
        b.putStringArray( "selectedItems", results );
        intent.putExtras( b );
        startActivity( intent );
    }
}