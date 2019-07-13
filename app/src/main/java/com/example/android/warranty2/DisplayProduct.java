package com.example.android.warranty2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class DisplayProduct extends AppCompatActivity implements Serializable {
    String telNo;
    String others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);
        Products toDisplay=(Products)getIntent().getSerializableExtra("Class");

        HashMap<String,String> tel=new HashMap<String, String>();
        tel.put("Apple","18001001000");
        tel.put("Samsung","18001001000");

        tel.put("Oneplus","18001001000");
        tel.put("Philips","18001001000");
        tel.put("Xiomi","18001001000");
        tel.put("Motorola","18001001000");
        tel.put("Others","Not Available");

        TextView vBrand=(TextView)findViewById(R.id.dbrand);
        vBrand.setText(toDisplay.getBrand());

        TextView vName=(TextView)findViewById(R.id.dname);
        vName.setText(toDisplay.getName());

        Calendar cal=Calendar.getInstance();
        cal.setTime(toDisplay.getDate());
        cal.add(Calendar.MONTH,toDisplay.getExpirery());
        Date expiry=cal.getTime();

        SimpleDateFormat dateFormat=new SimpleDateFormat("MMM dd, yyyy");;
        String expiryDate=dateFormat.format(expiry);




        TextView vExpiry=(TextView)findViewById(R.id.dedate);
        vExpiry.setText(expiryDate);

        String pDate=dateFormat.format(toDisplay.getDate());

        TextView vDate=(TextView)findViewById(R.id.dpdate);
        vDate.setText(pDate);

        TextView vNo=(TextView)findViewById(R.id.dno);
        telNo=tel.get(toDisplay.getBrand());
        vNo.setText(telNo);
        others=toDisplay.getBrand();

        vNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!others.matches("Others")) {
                    String toPass = "tel:" + telNo;
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(toPass));
                    startActivity(intent);
                }
            }
        });

        /*ImageView iv=(ImageView)findViewById(R.id.dbill);

        iv.setImageURI(toDisplay.getImageUri());
*/

    }
}
