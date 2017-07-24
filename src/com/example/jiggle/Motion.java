package com.example.jiggle;


import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Motion extends ActionBarActivity implements SensorEventListener {
ImageButton left,right;
SensorManager sm;
Sensor sen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_motion);
		right=(ImageButton) findViewById(R.id.imageButton1);
		left=(ImageButton) findViewById(R.id.imageButton2);
		sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sen=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, sen, 3);

	}
	@Override
	protected void onPause()
	{
		super.onPause();
		sm.unregisterListener(this);
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		sm.registerListener(this,sen,3);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.motion, menu);
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

	@Override
	public void onSensorChanged(SensorEvent e) {
		// TODO Auto-generated method stub
		float x=e.values[0];
		float y=e.values[1];
		float z=e.values[2];
		float norm_of_g=(float) Math.sqrt(x*x+y*y+z*z); 
		float lr=x/norm_of_g;
		float ly=y/norm_of_g;
		float lz=z/norm_of_g;

		float speed=(float) Math.sqrt(x*x+y*y+z*z);
		speed=speed-(SensorManager.GRAVITY_EARTH);		
		if(speed>(float)12)
		{
			//MainActivity.dis("space");	
	//		t.setText(""+lr+" "+ly+" "+lz);
			//MainActivity.dis("gspace");
			if(lr<-0.4)
			{
				MainActivity.dis("left");
				
				//MainActivity.dis(""+lr+" "+ly+" "+lz);
				right.setVisibility(View.INVISIBLE);
				left.setVisibility(View.VISIBLE);
			}
			else if(lr>0.4)
			{
				MainActivity.dis("right");
		//		MainActivity.dis("gspace");
				//MainActivity.dis(""+lr+" "+ly+" "+lz);
				left.setVisibility(View.INVISIBLE);
				right.setVisibility(View.VISIBLE);
			}
		}
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
}
  