package com.example.android.warranty2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {




   static ArrayList<Products> product=new ArrayList<Products>();

    ArrayList<Products> getArrayList() {
        return product;
    }

    ProductAdapter pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


       /* try {

            FileInputStream fileIn = new FileInputStream("objects");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

             product = (ArrayList<Products>)objectIn.readObject();
        }
        catch (Exception ex) {
            ex.printStackTrace();

        }

*/



            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView fT=(TextView)findViewById(R.id.displayText);
        if(!product.isEmpty()){
            fT.setVisibility(View.INVISIBLE);
        }
        else{
            findViewById(R.id.delete).setVisibility(View.INVISIBLE);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //         .setAction("Action", null).show();
                Intent takeUserEntry = new Intent(getApplicationContext(), Entry.class);
                startActivity(takeUserEntry);
            }
        });

        pro=new ProductAdapter(this,product);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(pro );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Products difsp=pro.getItem(position);


            Intent display=new Intent(getApplicationContext(),DisplayProduct.class);
           display.putExtra("Class", difsp);

                startActivity(display);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int arg2, long arg3) {

                product.remove(arg2);
                pro.notifyDataSetChanged();
                return false;
            }
        });



      /*  if(!product.isEmpty()){
            EditText er=(EditText)findViewById(R.id.fake);
            er.setText(product.get(0).getBrand());

        }*/
    }

   /* public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }  */


}
