package com.example.android.warranty2;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Entry extends AppCompatActivity implements Serializable {
    private static final int RESULT_LOAD_IMAGE=1;
    ImageView imageToUpload;
    TextView bToUpload;
    EditText eName,eBrand,eExpiryInMonth;
    final Calendar myCalendar=Calendar.getInstance();
    Button bSave;
    EditText editText;
    Products p=new Products();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        final ArrayList<Products> product=new ArrayList<Products>();



        eName=(EditText)findViewById(R.id.name);
        eExpiryInMonth=(EditText)findViewById(R.id.expiryInMonth) ;
        bToUpload=(TextView)findViewById(R.id.buttonGallery);
        editText=(EditText)findViewById(R.id.purchasedOn);
        imageToUpload=(ImageView) findViewById(R.id.imageGallery);
        bSave=(Button)findViewById(R.id.save);

         spinner = (Spinner) findViewById(R.id.brand);

        // Spinner click listener


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Apple");
        categories.add("Samsung");
        categories.add("Lenovo");
        categories.add("Philips");
        categories.add("Oneplus");
        categories.add("Others");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {



                    Object item = adapterView.getItemAtPosition(position);
                    if (item != null) {
                         p.setBrand(item.toString());
                    }


                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

        String text = spinner.getSelectedItem().toString();
        p.setBrand(text);



          eName.setMaxLines(1);


        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 p=new Products();



                if(imageToUpload.getDrawable()!=null) {
                    Bitmap bm = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
                   /* String FILENAME = "image.png";
                    String PATH = "/mnt/sdcard/"+ FILENAME;
                    File f = new File(PATH);
                    Uri yourUri = Uri.fromFile(f);
                    p.setImageUri(yourUri);
*/                }


                String text = spinner.getSelectedItem().toString();
                p.setBrand(text);

                p.setName(eName.getText().toString());

                if(!editText.getText().toString().matches("")){
                p.setDate(myCalendar.getTime());}

                if(!eExpiryInMonth.getText().toString().matches("")) {
                    p.setExpirery(Integer.parseInt(eExpiryInMonth.getText().toString()));
                }



                if(!editText.getText().toString().matches("") && !eExpiryInMonth.getText().toString().matches("")
                        && !eName.getText().toString().matches("") )
                {

                    new MainActivity().getArrayList().add(p);



                  /*  product.add(p);
                    FileOutputStream outputStream = null;
                    try {

                        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
                        if (!root.exists()) {
                            root.mkdirs();
                        }
                        File gpxfile = new File(root, "objects");






                        outputStream = openFileOutput(gpxfile, Context.MODE_PRIVATE);
                        ObjectOutputStream oos=new ObjectOutputStream(outputStream);

                        oos.writeObject(product);
                        oos.close();
                        }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

*/

                    Intent mainPage = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainPage);
                }
                else if(eName.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter The Product Name",Toast.LENGTH_LONG).show();
                }
                /*else if(eBrand.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Poduct Brand",Toast.LENGTH_LONG).show();
                }*/

                else if(editText.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter The Purchased Date",Toast.LENGTH_LONG).show();
                }
                else if(eExpiryInMonth.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Warranty Duration",Toast.LENGTH_LONG).show();
                }




            }
        });



        bToUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent galleryIntent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
           }
        });





        final DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);



                updateLabel();
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Entry.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });





    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null){
            Uri ImageSelected=data.getData();
            imageToUpload.setImageURI(ImageSelected);

        }
    }


    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date a = myCalendar.getTime();
       // Date today=new Date();
       // if(a.after(today)){ }

        Log.e("this is date",a.toString());
        editText.setText(sdf.format(myCalendar.getTime()));
    }



}
