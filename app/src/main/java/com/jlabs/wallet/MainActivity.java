package com.jlabs.wallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Intent intent=null;
    private Intent i=null;

    DatabaseHelper myDb;

private FloatingActionButton fab=null;
   private ListView listView=null;

private ArrayList<String> thelist=null;
    private ListAdapter listAdapter=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        thelist=new ArrayList<>();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        intent= new Intent(MainActivity.this,CreateCard.class);
        i=new Intent(MainActivity.this,Displaycard.class);

        listView=(ListView)findViewById(R.id.listview);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });






    }
    public void onResume(){
        super.onResume();

        viewAll();
    }


    public void viewAll()
    {
        Cursor res=myDb.getAllData();
        if(res.getCount()==0)
        {
             Toast.makeText(this,"Nothing to show",Toast.LENGTH_SHORT).show();

            return ;
        }
        thelist.clear();

        while(res.moveToNext()){

            thelist.add(res.getString(1));





        }
        listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);

        listView.setAdapter(listAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                 i.putExtra("chosen",position);
                startActivity(i);

            }
        });

    }



}
