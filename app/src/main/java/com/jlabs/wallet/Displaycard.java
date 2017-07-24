package com.jlabs.wallet;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Displaycard extends AppCompatActivity {
private int position;
   private Bundle b=null;
    Cursor cursor=null;
    DatabaseHelper Db;
    String link;
    Uri fileUri;

//    TextView textView;
    ImageView card=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycard);
        Db=new DatabaseHelper(Displaycard.this);
        card=(ImageView)findViewById(R.id.card);
//        textView=(TextView)findViewById(R.id.test);
        cursor=Db.getAllData();
        File imageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        String imagename="cardpic.jpg";

        File imageFile = new File(imageDirectory,imagename);

        Uri uri = Uri.fromFile(imageFile);
        
        //Bitmap bmp=BitmapFactory.DecodeStream(ContentResolver.OpenInputStream(uri));


        b=getIntent().getExtras();
        if(b!=null){
            position=(int)b.getInt("chosen");
            cursor.moveToPosition(position);
         //   byte[] image = cursor.getBlob(2);
           // Bitmap bmp=getImage(image);




//
//
         //  card.setImageBitmap(bmp);
           // Toast.makeText(Displaycard.this,cursor.getString(1)+"hello",Toast.LENGTH_SHORT).show();

            //Toast.makeText(Displaycard.this,position+"hello",Toast.LENGTH_SHORT).show();

         //  link=cursor.getString(3);
           // Toast.makeText(this, link, Toast.LENGTH_SHORT).show();
         //fileUri=Uri.parse(link);
         //   card.setImageURI(null);
       //    card.setImageURI(fileUri);

//            try {
//                File f=new File(Environment.getExternalStorageDirectory(),"pippo.png");
//                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//                card.setImageBitmap(b);
//            }
//            catch (FileNotFoundException e)
//            {
//                e.printStackTrace();
//            }



        }

    }
        public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }



}
