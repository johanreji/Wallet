package com.jlabs.wallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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
    Bitmap bitmap;
String link=null;
private Button done=null;
    File imageFile;

    Uri uri;
//private  ImageView test=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_create_card);
       // test=(ImageView)findViewById(R.id.test);

        test = (ImageView)findViewById(R.id.test);
        myDb=new DatabaseHelper(this); bmp=null;

        done=(Button)findViewById(R.id.done);


        name=(EditText)findViewById(R.id.name);


        firstside=(Button)findViewById(R.id.firstside);
        firstside.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {

                Intent camera =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                file = new File(getFilesDir(), "newImage.jpg");
//                if(!file.exists())
//                {
//                    try {
//                        file.createNewFile();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                fileUri = Uri.fromFile(file);

//                dirName = Environment.getExternalStorageDirectory().getPath()
//                        + "/MyAppFolder/MyApp.png";
//
//                fileUri = Uri.fromFile(new File(dirName));

                //intent.putExtra( MediaStore.EXTRA_OUTPUT, uriImagem );
//                camera.putExtra(MediaStore.EXTRA_OUTPUT,"");
               // camera.putExtras(bundle);
                File imageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
                String imagename="cardpic.jpg";

                imageFile = new File(imageDirectory,imagename);

                uri = Uri.fromFile(imageFile);


                camera.putExtra(MediaStore.EXTRA_OUTPUT,uri);
               startActivityForResult(camera,CAMERA_REQUEST);
              //  test.setImageBitmap(bitmap);
                //test.setImageURI(uri);

                /////////////////////////////////////////////////////////////////////////////






                    // Create the File where the photo should go
//                    File photoFile = null;
//                    try {
//                        Toast.makeText(CreateCard.this, "flag1", Toast.LENGTH_SHORT).show();
//                        photoFile = createImageFile();
//                    } catch (IOException ex) {
//                        // Error occurred while creating the File
//
//                    }
//                    // Continue only if the File was successfully created
//                    if (photoFile != null) {
//                        Toast.makeText(CreateCard.this, "flag", Toast.LENGTH_SHORT).show();
//                        Uri photoURI = FileProvider.getUriForFile(CreateCard.this,
//                                "com.jlabs.wallet.fileprovider",
//                                photoFile);
//                        camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                        startActivityForResult(camera,CAMERA_REQUEST);
//                    }





                ////////////////////////////////////////////////////////



            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             // apple= getBytes(bmp);
//                String filename = "pippo.png";
//                File sd = Environment.getExternalStorageDirectory();
//                File dest = new File(sd, filename);
//
//                try {
//                    FileOutputStream out = new FileOutputStream(dest);
//                    bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
//
//                    out.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Uri uri=Uri.fromFile(dest);
//                link=uri.toString();

                // create a toast if there is a no photo taken
                if((flag==1)&&(name.length()!=0)) {

                    link=uri.toString();
                   // link="idk";

                    boolean isInserted = myDb.insertData(name.getText().toString(),link);
                    if (isInserted == true) {
                        Toast.makeText(CreateCard.this, "store success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CreateCard.this, "store not success", Toast.LENGTH_SHORT).show();
                    }
                    onBackPressed();
                }
                else
                {   if((flag==0)&&(name.length()!=0))
                {//Toast.makeText(CreateCard.this, "Provide photo", Toast.LENGTH_SHORT).show();
                    firstside.setError("Scan atleast one side");}
                    if((name.length()==0)&&(flag!=0)) {
                       // Toast.makeText(CreateCard.this, "Provide name", Toast.LENGTH_SHORT).show();


                        name.setError("Card name is empty");


                    }

                    if((flag==0)&&(name.length()==0)) {
                        //Toast.makeText(CreateCard.this, "Provide name and photo", Toast.LENGTH_SHORT).show();

                        firstside.setError("Scan atleast one side");
                        //firstside.setBackgroundColor(Color.RED);

                        name.setError("Cardname is empty");


                    }


                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        test.setImageBitmap(bitmap);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this,"wtf",Toast.LENGTH_SHORT).show();
        if((requestCode==CAMERA_REQUEST)&&(resultCode==CreateCard.RESULT_OK))
        {

            flag=1;
           // Bundle extras=data.getExtras();
 //fileUri=data.getData();

//
  //          link=fileUri.toString();
//link="idk";
            //fileUri=(Uri)extras.get("data");
     // bmp=(Bitmap)extras.get("data");
           // link=data.getData().toString();
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), bitmapOptions);
            Toast.makeText(this,"haa", Toast.LENGTH_SHORT).show();
            firstside.setError(null);
            



    }

}


}
