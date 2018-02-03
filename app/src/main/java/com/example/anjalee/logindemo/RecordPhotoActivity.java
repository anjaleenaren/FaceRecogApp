package com.example.anjalee.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class RecordPhotoActivity extends AppCompatActivity {
    private ImageView userView;
    private Button userTakePic;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_photo);

        userView = (ImageView) findViewById(R.id.ivStorePhoto);
        userTakePic = (Button) findViewById(R.id.btnTakePic);

        //Disable if the user doesn't have a camera
        if ( !hasCamera() )
        {
            userTakePic.setEnabled(false);
        }

        userTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });
    }

    private boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

   /* private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

   public void launchCamera (View view)
   {
       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

   } */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            userView.setImageBitmap(imageBitmap);
           //Toast.makeText(RecordPhotoActivity.this, "death", Toast.LENGTH_SHORT).show();
        }
    }
}
