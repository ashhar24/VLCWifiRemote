package com.defoliate.vlcwifiremote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Remote extends Activity implements View.OnClickListener
{
    //String ip = SocketHandler.getIP();
    //TextView tvhttp;
    Button bplay, bpause, btogfull, brev10, brev3, brev60, bfwd10, bfwd3, bfwd60;
    String data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote);

        bplay = (Button) findViewById(R.id.bplay);
        bpause = (Button) findViewById(R.id.bpause);
        btogfull = (Button) findViewById(R.id.btogfull);
        brev10 = (Button) findViewById(R.id.brev10);
        brev3 = (Button) findViewById(R.id.brev3);
        brev60 = (Button) findViewById(R.id.brev60);
        bfwd10 = (Button) findViewById(R.id.bfwd10);
        bfwd3 = (Button) findViewById(R.id.bfwd3);
        bfwd60 = (Button) findViewById(R.id.bfwd60);

        bplay.setOnClickListener(this);
        bpause.setOnClickListener(this);
        btogfull.setOnClickListener(this);
        brev10.setOnClickListener(this);
        brev3.setOnClickListener(this);
        brev60.setOnClickListener(this);
        bfwd10.setOnClickListener(this);
        bfwd3.setOnClickListener(this);
        bfwd60.setOnClickListener(this);

        //tvhttp = (TextView)findViewById(R.id.tvhttp);
        HttpGetMethod get = new HttpGetMethod();
        try
        {
            data = get.getInternetData(null);
            if (data == null)
            {
                data = "ERROR";
                Toast t = Toast.makeText(Remote.this, "ERROR Unable to retreive", Toast.LENGTH_SHORT);
                t.show();
            }
            //tvhttp.setText(data);
            else
            {
                Toast t = Toast.makeText(Remote.this, "Data Retreived", Toast.LENGTH_SHORT);
                t.show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v)
    {
        HttpGetMethod set = new HttpGetMethod();
        if (data != "ERROR")
        {
            try
            {
                switch (v.getId())
                {
                    case R.id.bplay:
                        set.getInternetData("pl_play");
                        break;
                    case R.id.bpause:
                        set.getInternetData("pl_pause");
                        break;
                    case R.id.btogfull:
                        set.getInternetData("fullscreen");
                        break;
                    case R.id.brev10:
                        set.getInternetData("seek&val=-10");
                        break;
                    case R.id.brev3:
                        set.getInternetData("seek&val=-3");
                        break;
                    case R.id.brev60:
                        set.getInternetData("seek&val=-60");
                        break;
                    case R.id.bfwd10:
                        set.getInternetData("seek&val=+10");
                        break;
                    case R.id.bfwd3:
                        set.getInternetData("seek&val=+3");
                        break;
                    case R.id.bfwd60:
                        set.getInternetData("seek&val=+60");
                        break;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
