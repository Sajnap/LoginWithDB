package com.example.loginpage;

import java.util.ArrayList;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SecMainActivity extends ActionBarActivity {

	EditText name,email;
	String name1,email1;
	ListView lv;
	NewActivity dbh;
	ArrayList<String> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		name=(EditText)findViewById(R.id.editText1);
		email=(EditText)findViewById(R.id.editText3);
		lv=(ListView)findViewById(R.id.listView1);
	}
	public void submitButton(View v)
	{
		name1=name.getText().toString();
		email1=email.getText().toString();
		dbh=new NewActivity(getApplicationContext());
		long g=dbh.insertdata(name1,email1);
		if(g>0){
			Toast.makeText(SecMainActivity.this,"Inserted",Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(SecMainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
		}

	}
	public void viewButton(View v){
		dbh=new NewActivity(getApplicationContext());
		Cursor c=null;
		data=new ArrayList<String>();
		c=dbh.viewdata();
		if(c!=null){
			if(c.moveToFirst())
			{
				do
				{
					int i=c.getInt(c.getColumnIndex("id"));
					String n=c.getString(c.getColumnIndex("name"));
					String e=c.getString(c.getColumnIndex("email"));
					data.add("Id :"+i+"\nName :"+n+"\nEmail :"+e);
				}while(c.moveToNext());
			}
			else
			{
				Toast.makeText(getApplicationContext(),"Empty database",Toast.LENGTH_SHORT).show();
			}
		}
		else{
			Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_SHORT).show();
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
		lv.setAdapter(adapter);
	}



}
