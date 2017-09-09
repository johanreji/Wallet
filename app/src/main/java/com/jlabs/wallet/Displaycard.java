package com.jlabs.wallet;


import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Displaycard extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int REQUEST_WRITE_PERMISSION = 1;
    private static final int REQUEST_READ_PERMISSION = 1;
    private int position;
    private Bundle b = null;
    Cursor cursor = null;
    DatabaseHelper Db;
    String id=null;

    Bitmap img = null;

    String ImgPath = null;
    BitmapFactory.Options options = null;



    ImageView card = null;

    public Bitmap resizeBitmap(String photoPath, int targetW, int targetH) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        }
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true; //Deprecated API 21
        return BitmapFactory.decodeFile(photoPath, bmOptions);
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_READ_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            display();

        }

    }

    private void requestPermission() {

        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) && (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        } else {


            display();

        }

    }


    public void display() {
        ImgPath = "/sdcard/Movies/cardpic"+id+".jpg";

        options = new BitmapFactory.Options();

        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        img = BitmapFactory.decodeFile(ImgPath);


        card.setImageBitmap(resizeBitmap(ImgPath, 1024, 768));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycard);

        Db = new DatabaseHelper(Displaycard.this);
        cursor = Db.getAllData();
        card = (ImageView) findViewById(R.id.card);

        b = getIntent().getExtras();
        if (b != null) {
            position = (int) b.getInt("chosen");
            cursor.moveToPosition(position);
            id = cursor.getString(0);


            requestPermission();


        }



    }
}
