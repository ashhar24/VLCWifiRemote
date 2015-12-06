package com.defoliate.vlcwifiremote;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Socket;


public class Connect extends Activity implements View.OnClickListener
{
    EditText etip;
    Button bconnect;
    Socket s;
    String ip;
    Boolean x;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);
        etip=(EditText)findViewById(R.id.etIP);
        bconnect=(Button)findViewById(R.id.bConnect);
        bconnect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bConnect  :   ip = etip.getText().toString();
                                    try
                                    {
                                        // needed to create a new class connecttoserver so as to handle
                                        // android.os.NetworkOnMainThreadException
                                        // this exception is handled using doinbackground method of asynctask as shown below
                                        Toast t1 = Toast.makeText(Connect.this, "Waiting for connection", Toast.LENGTH_SHORT);
                                        t1.show();
                                        Connecttoserver con =new Connecttoserver();
                                        con.execute();
                                        Thread.sleep(2000);
                                        System.out.println(x);
                                        if(x)
                                        {
                                            Toast t = Toast.makeText(Connect.this, "Connected to" + ip, Toast.LENGTH_SHORT);
                                            t.show();
                                            Class c = Class.forName("com.defoliate.vlcwifiremote.StartVlc");
                                            Intent menuintent = new Intent(Connect.this, c);
                                            startActivity(menuintent);
                                        }
                                    }
                                    catch (Exception e)
                                    {
                                        Toast t = Toast.makeText(Connect.this, "Unable to connect",Toast.LENGTH_SHORT);
                                        t.show();
                                        e.printStackTrace();
                                    }
        }
    }

    public class Connecttoserver extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] params)
        {
            try
            {
                s = new Socket(ip,8000);
                SocketHandler.setSocket(s);
                SocketHandler.setIP(ip);
                x=true;
            }
            catch (Exception e)
            {
                x=false;
                e.printStackTrace();
            }
            return null;
        }
    }
}

