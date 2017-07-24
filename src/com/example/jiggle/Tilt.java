package com.example.jiggle;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Tilt extends ActionBarActivity implements SensorEventListener{
	SensorManager sm;
	Sensor sen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tilt);
		sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sen=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, sen, 3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tilt, menu);
		return true;
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
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float x=event.values[0];
		float y=event.values[1];
		float z=event.values[2];
		if(Math.abs(x)>Math.abs(y))
		{
			if(x<0)
				MainActivity.dis("tright");
			if(x>0)
				MainActivity.dis("tleft");
		}
		else
		{
			if(x<0)
				MainActivity.dis("ttop");
			if(x>0)
				MainActivity.dis("tbot");			
		}
		if(x>-2&&x<2&&y>-2&&y<2)
		{}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
}
