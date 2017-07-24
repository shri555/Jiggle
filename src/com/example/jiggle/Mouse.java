package com.example.jiggle;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Mouse extends ActionBarActivity {
	TextView mousepad;
	Button b1;
	EditText e1;
	ImageButton i1;
	boolean moved=false;
		void display(String s)
		{
			MainActivity.dis(s);
		}
		 int sx;
		 int sy;
		 int disX;
		 int disY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);

		mousepad=(TextView) findViewById(R.id.textView1);
		mousepad.setOnTouchListener(new View.OnTouchListener() {
	@Override
	public boolean onTouch(View v, MotionEvent event) {
	
	switch(event.getAction())
	{
	case MotionEvent.ACTION_DOWN:
	sx =(int) event.getX();
	sy =(int) event.getY();
	moved=false;
	break;
	case MotionEvent.ACTION_MOVE:
	disX = (int) (event.getX()- sx); 
	disY = (int) (event.getY()- sy); 
	sx = (int) event.getX();
	sy = (int) event.getY();
	
	if(disX !=0|| disY !=0)
	{
	display(disX +","+ disY); 
	}
	moved=true;
	break;
	case MotionEvent.ACTION_UP:
	if(!moved){
	display("click");
	}
	break;
	}			
	return true;
	}
	});

	b1=(Button) findViewById(R.id.button1);
	b1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			display("click");
		}
	});
	e1=(EditText) findViewById(R.id.editText1);
	i1=(ImageButton) findViewById(R.id.imageButton1);
	i1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(e1.getText()!=null)
			{
				display("@"+e1.getText());
				//e1.setText("");
			}
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mouse, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
