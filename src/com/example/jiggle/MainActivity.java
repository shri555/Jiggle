package com.example.jiggle;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity 
{
	ServerSocket ss=null;
	Socket client=null;
	String serverip=null;
	static TextView status; 
	Handler h=new Handler();
	public static PrintWriter out;

	public static void dis(String s)
	{
		try
		{
		out.println(s);
		}catch (Exception e){
			//status.setText("disconnected");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		serverip=getLocalIpAddress();
		status=(TextView) findViewById(R.id.textView1);
		ImageButton b1=(ImageButton) findViewById(R.id.imageButton1);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,Vlcact.class);	
				startActivity(i);	
			}
		});
		ImageButton b2=(ImageButton) findViewById(R.id.imageButton2);
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,Motion.class);	
				startActivity(i);	
			}
		});
		ImageButton b3=(ImageButton) findViewById(R.id.imageButton3);
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,Ppt.class);	
				startActivity(i);	
			}
		});
		ImageButton b4=(ImageButton) findViewById(R.id.imageButton4);
		b4.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,Mouse.class);	
				startActivity(i);	
			}
		});
		Button t=(Button) findViewById(R.id.button1);
		t.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,Tilt.class);	
				startActivity(i);	
			}
		});
		ImageButton b5=(ImageButton) findViewById(R.id.imageButton5);
		b5.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent i=new Intent(MainActivity.this,Gamec.class);	
			startActivity(i);	
			}
		});
		serverip=getLocalIpAddress();
		status.setText(serverip);
		Thread st=new Thread(new serverthread());
		st.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
    protected void onStop() {
        super.onStop();
        try {
             // make sure you close the socket upon exiting
             ss.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
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
	class serverthread implements Runnable
	{

		@Override
		public void run() 
		{
			if(serverip!=null)
			{
				//status.setText("Enter "+serverip );				
			/*	try
				{
				h.post(new Runnable() {
                      @Override
                      public void run() {
                          status.setText("Ip :" + serverip);
                      }
                  });
				}
				catch(Exception e){}*/
			}
			else
			{
				//status.setText("turn on hotspot");
			}			
			try 
			{
				ss=new ServerSocket(8080);
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				//status.setText("error socket");
			}
			while(true)
			{
				try {
					client=ss.accept();	
				/*	h.post(new Runnable() {
                        @Override
                        public void run() {
                            status.setText("Connected.");                            
                        }
                    });*/
					//	out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream()),true);
				 try 
				 {
					out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);				
				 }
				 catch (Exception e) 
				 {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				 }
				
			}
				catch(Exception e){
			/*		h.post(new Runnable() {
                        @Override
                        public void run() {
                        //    status.setText("DisConnected.");                            
                        }
                    });*/

				}
			}
		
	}
	}
	private String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) { return inetAddress.getHostAddress().toString(); }
                }
            }
        } catch (SocketException ex) {
           
        }
        return null;
    }
	
}
 