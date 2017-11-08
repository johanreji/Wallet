package com.jlabs.wallet;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
 public int pos=0;
    public DatabaseHelper myDb;

private FloatingActionButton fab=null;
   private ListView listView=null;

private ArrayList<String> thelist=null;
    private ListAdapter listAdapter=null;
    Toolbar toolbar=null;
    ActionMode actionMode;
    CheckBox checkBox=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        thelist=new ArrayList<>();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        intent= new Intent(MainActivity.this,CreateCard.class);
        i=new Intent(MainActivity.this,Displaycard.class);
checkBox=(CheckBox)findViewById(R.id.checkbox);
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
           // thelist.add(res);
         //   thelist.add(res.getString(0));

            thelist.add(res.getString(1));







        }
        listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);

        listView.setAdapter(listAdapter);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    pos=i+1;

                    Toast.makeText(MainActivity.this,"", Toast.LENGTH_SHORT).show();

                  //  AlertDialog.Builder builder;
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
//                    } else {
                  //      builder = new AlertDialog.Builder(MainActivity.this);
//                    }
                //    builder
//                            .setMessage("Are you sure you want to delete this entry?")
//                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // continue with delete
//
//                                    myDb.removeData(pos);
//
//                                }
//                            })
//                            .setNegativeButton("no", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // do nothing
//                                }
//                            })
//                            .setCancelable(true)
//                            .show();




                    return  true;
                };
            });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//myDb.removeData(position);
                 i.putExtra("chosen",position);
                startActivity(i);

            }
        });

    }


}
