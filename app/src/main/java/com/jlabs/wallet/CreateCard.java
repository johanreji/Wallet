package com.jlabs.wallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class CreateCard extends AppCompatActivity {

DatabaseHelper myDb;
    String mCurrentPhotoPath;
private Bitmap bmp=null;
    private int flag=0;
    ImageView test;
    private Button firstside=null;
private static final int CAMERA_REQUEST=1;
private byte[] apple=null;
    private EditText name=null;
    File file;
    Uri fileUri=null;
   private String string=null;
    long t=3000;
    String dirName;
    int n=0;
    Bitmap bitmap;
    Cursor cr=null;
    String id=null;
String link=null;
private Button done=null;
    File imageFile;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_create_card);

n=0;

        myDb=new DatabaseHelper(this);
        bmp=null;
cr=myDb.getAllData();
        if(cr.getCount()!=0) {
            cr.moveToLast();
            n = cr.getInt(0);
        }
            n++;
            id = Integer.toString(n);


        done=(Button)findViewById(R.id.done);


        name=(EditText)findViewById(R.id.name);


        firstside=(Button)findViewById(R.id.firstside);
        firstside.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {

                Intent camera =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File imageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
                String imagename="cardpic"+id+".jpg";

                imageFile = new File(imageDirectory,imagename);

                uri = Uri.fromFile(imageFile);


                camera.putExtra(MediaStore.EXTRA_OUTPUT,uri);
               startActivityForResult(camera,CAMERA_REQUEST);





            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((flag==1)&&(name.length()!=0)) {




                    boolean isInserted = myDb.insertData(name.getText().toString());
                    if (isInserted == true) {
                        Toast.makeText(CreateCard.this, "store success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CreateCard.this, "store not success", Toast.LENGTH_SHORT).show();
                    }
                    onBackPressed();
                }
                else
                {   if((flag==0)&&(name.length()!=0))
                {
                    firstside.setError("Scan atleast one side");}
                    if((name.length()==0)&&(flag!=0)) {


                        name.setError("Card name is empty");


                    }

                    if((flag==0)&&(name.length()==0)) {


                        firstside.setError("Scan atleast one side");


                        name.setError("Cardname is empty");


                    }


                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode==CAMERA_REQUEST)&&(resultCode==CreateCard.RESULT_OK))
        {

            flag=1;

            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), bitmapOptions);

            firstside.setError(null);
            



    }

}


}
