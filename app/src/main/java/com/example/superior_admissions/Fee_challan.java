package com.example.superior_admissions;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fee_challan extends AppCompatActivity {
    String fName = String.valueOf(System.currentTimeMillis());

    TextView nameTxt, fname, degreeTxt, feesTxt;
    String cnic = "", degree = "";
    String program = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_challan);


        if (!checkPermission()){

            requestPermission();
        }

        nameTxt = findViewById(R.id.name);
        fname = findViewById(R.id.fname);
        degreeTxt = findViewById(R.id.degree);
        feesTxt = findViewById(R.id.amount);


        Intent intent = getIntent();

        cnic = intent.getStringExtra("cnic");
        degree = intent.getStringExtra("degree");

        degreeTxt.setText(degree);



        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Student20").child(cnic);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ChallanModel challan = snapshot.getValue(ChallanModel.class);
                program = challan.getProgram();
                nameTxt.setText(challan.getName());
                feesTxt.setText(challan.getFee());
                fname.setText(challan.getFname());

                System.out.println(challan.getFee());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        if (program.equals("BS Computer Science")){
//            amount = totalCredit * 9600;
//        }
//        if (program.equals("BS Information Technology")){
//            amount = totalCredit * 8300;
//        }
//        if (program.equals("BS Software Engineering")){
//            amount = totalCredit * 8500;
//        }
//        if (program.equals("BS Artificial Intelligence") || program.equals("BS Data Science") || program.equals("BS Cyber Security")){
//            amount = totalCredit * 8100;
//        }
//        if (program.equals("BS Gaming and Multimedia")){
//            amount = totalCredit * 8200;
//        }
//        if (program.equals("BS Internet of Things")){
//            amount = totalCredit * 7900;
//        }
//        if (program.equals("BS Robotics")){
//            amount = totalCredit * 7800;
//        }

//        amountTxt.setText(amount);


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        findViewById(R.id.createBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutTOimageConverter();
            }
        });


    }

    private void layoutTOimageConverter() {


        Dexter.withContext(this).withPermissions(WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            RelativeLayout layout  = findViewById(R.id.sllayout);


                            File file = saveBitMap(Fee_challan.this, layout);    //which view you want to pass that view as parameter
                            if (file != null) {
                                Log.i("TAG", "Drawing saved to the gallery!");
                                Toast.makeText(Fee_challan.this, "Processing", Toast.LENGTH_SHORT).show();


                                try {
                                    imageToPDF();
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }


                            } else {
                                Log.i("TAG", "Oops! Image could not be saved.");
                                Toast.makeText(Fee_challan.this, "Click Again !", Toast.LENGTH_SHORT).show();

                            }


                        } else {
                            Toast.makeText(Fee_challan.this, "Permissions are not granted!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();


    }


    public void imageToPDF() throws FileNotFoundException {
        try {
            Document document = new Document();
            String dirpath = android.os.Environment.getExternalStorageDirectory().toString();
            PdfWriter.getInstance(document, new FileOutputStream(dirpath + "/"+fName+".pdf")); //  Change pdf's name.
            document.open();
            Image img = Image.getInstance(Environment.getExternalStorageDirectory() + File.separator +"/Pictures/Download/"+ fName+".jpg");
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / img.getWidth()) * 100;
            img.scalePercent(scaler);
            img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
            document.add(img);
            document.close();
            Toast.makeText(this, "PDF Generated successfully!..", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }






    private File saveBitMap(Context context, View drawView) {
        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Download"); // enter folder name to save image
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if (!isDirectoryCreated)
                Log.i("ATG", "Can't create directory to save the image");
            return null;
        }
        String filename = pictureFileDir.getPath() + File.separator +fName + ".jpg";
        File pictureFile = new File(filename);
        Bitmap bitmap = getBitmapFromView(drawView);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        scanGallery(context, pictureFile.getAbsolutePath());
        return pictureFile;
    }










    //create bitmap from view and returns it
    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }










    private void scanGallery(Context cntx, String path) {
        try {
            MediaScannerConnection.scanFile(cntx, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean checkPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(Fee_challan.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(Fee_challan.this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }


    private void requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getPackageName())));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(Fee_challan.this, new String[]{WRITE_EXTERNAL_STORAGE}, 1100);
        }
    }

}