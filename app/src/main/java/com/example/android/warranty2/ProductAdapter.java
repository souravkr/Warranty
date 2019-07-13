package com.example.android.warranty2;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProductAdapter extends ArrayAdapter<Products> {
    public ProductAdapter(@NonNull Context context, ArrayList<Products> products) {
        super(context,0,products);
    }

    private int getDaysColor(double magnitude) {
        int magnitudeColorResourceId= R.color.m1;
        if(magnitude<60){
            magnitudeColorResourceId= R.color.m1;
            return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
        }
        else if(magnitude>=60 && magnitude<=120){
            magnitudeColorResourceId=R.color.m2;
            return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
        }
        else if(magnitude>120){
            magnitudeColorResourceId=R.color.m3;
            return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View wordView=convertView;
        if(convertView==null){
            wordView=LayoutInflater.from(getContext()).inflate(R.layout.simple_product,parent,false);
        }

        Products p=getItem(position);

        TextView names=(TextView)wordView.findViewById(R.id.displayName);
        names.setText(p.getName());

        TextView BRANDS=(TextView)wordView.findViewById(R.id.displayBrand);
        BRANDS.setText(p.getBrand());

        TextView pO=(TextView)wordView.findViewById(R.id.tPurchasedOn);

        SimpleDateFormat dateFormater=new SimpleDateFormat("MMM dd, yyyy");
        String dates=dateFormater.format(p.getDate());

        String pD="Purchased On:- "+dates;
        pO.setText(pD);
        /*Calendar cal=Calendar.getInstance();
        cal.setTime(p.getDate());
        cal.add(Calendar.MONTH,p.getExpirery());

        TextView daysLeft=(TextView)wordView.findViewById(R.id.magnitude);
      int daysBetween = ChronoUnit.DAYS.between(Calendar.getInstance(),cal);

        int diff=cal.compareTo(Calendar.getInstance());*/
        int diffr=0;

        TextView daysLeft=(TextView)wordView.findViewById(R.id.magnitude);
        try{
            Calendar cal=Calendar.getInstance();
            cal.setTime(p.getDate());
            cal.add(Calendar.MONTH,p.getExpirery());
            Date expiry=cal.getTime();

            long diff=expiry.getTime()-new Date().getTime();


            float daysBetween = (diff / (1000*60*60*24));
             if(daysBetween<0)
             {
                 daysBetween=0;
             }
            diffr=(int)daysBetween;
            String str=diffr+" "+"days Left";
             daysLeft.setText(str);


            GradientDrawable gradientDrawable=(GradientDrawable)daysLeft.getBackground();
            int daysColour=getDaysColor(diffr);
            gradientDrawable.setColor(daysColour);
        }
        catch (Exception e){
            e.printStackTrace();
        }




        return wordView;
    }
}
