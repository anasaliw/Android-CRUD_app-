package com.it18.crud_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btninsert,btnupdate,btndelete,btnread;
EditText txtname,txtcontact,txtDOB;
    DBHelper db=new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        //buttons
        btninsert=findViewById(R.id.btninsert);
        btnupdate=findViewById(R.id.btnupdate);
        btnread=findViewById(R.id.btnread);
        btndelete=findViewById(R.id.btndelete);
        //Texts
        txtname=findViewById(R.id.txtname);
        txtcontact=findViewById(R.id.txtcontact);
        txtDOB=findViewById(R.id.txtDOB);
        //Insert
        btninsert.setOnClickListener(v->{
            String name=txtname.getText().toString();
            String contact=txtcontact.getText().toString();
            String dob=txtDOB.getText().toString();
            boolean insert=db.insertData(name,contact,dob);
            if(insert==true){
                Toast.makeText(MainActivity.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this,"Data insert Failed",Toast.LENGTH_SHORT);
            }
        });//End Insert Button
        btnupdate.setOnClickListener(v->{
            String name=txtname.getText().toString();
            String contact=txtcontact.getText().toString();
            String dob=txtDOB.getText().toString();
            boolean update=db.updateData(name,contact,dob);
            if(update==true){
                Toast.makeText(MainActivity.this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this,"Data update Failed",Toast.LENGTH_SHORT);
            }
        });//End Update Button
        btnread.setOnClickListener(v->{
            Cursor read=db.ReadData();
            if(read.getCount()==0){
                Toast.makeText(MainActivity.this,"No Data Found",Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer allrecord=new StringBuffer();
            while(read.moveToNext()){
                allrecord.append("Name: "+read.getString(0)+"\n");
                allrecord.append("Contact: "+read.getString(1)+"\n");
                allrecord.append("DOB: "+read.getString(2)+"\n");
            }
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("View All Records");
            builder.setMessage(allrecord.toString());
            builder.show();

        });

        btndelete.setOnClickListener(v->{
            String name=txtname.getText().toString();
            boolean delete=db.delete(name);
            if(delete==true){
                Toast.makeText(MainActivity.this,"Data Deleted Successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this,"Data delete Failed",Toast.LENGTH_SHORT);
            }
        });



    }
}