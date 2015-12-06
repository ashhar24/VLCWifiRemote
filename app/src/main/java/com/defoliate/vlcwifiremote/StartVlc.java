package com.defoliate.vlcwifiremote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class StartVlc extends Activity implements View.OnClickListener
{
    Button bopenvlc, bopenvlc2, bexit, bRemote;
    Socket s = SocketHandler.getSocket();
    String cmd = "\"C:\\Program Files\\VideoLAN\\VLC\\vlc.exe\"";
    String cmd2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startvlc);
        bopenvlc = (Button) findViewById(R.id.bopenvlc);
        bopenvlc.setOnClickListener(this);
        bopenvlc2 = (Button) findViewById(R.id.bopenvlc2);
        bopenvlc2.setOnClickListener(this);
        bexit = (Button) findViewById(R.id.bexit);
        bexit.setOnClickListener(this);
        bRemote = (Button) findViewById(R.id.bRemote);
        bRemote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        DataOutputStream o = null;
        try
        {
            OutputStream out = s.getOutputStream();
            o = new DataOutputStream(out);
            switch (v.getId())
            {
                case R.id.bRemote:
                    Class c = Class.forName("com.defoliate.vlcwifiremote.Remote");
                    Intent i = new Intent(StartVlc.this, c);
                    startActivity(i);
                    break;

                case R.id.bopenvlc:
                    cmd2 = "taskkill /IM vlc.exe";
                    o.writeUTF(cmd2);
                    cmd2 = cmd + " \"F:\\TV Series\\Constantine S01E01 HDTV x264-LOL.mp4\"";
                    o.writeUTF(cmd2);
                    break;

                case R.id.bopenvlc2:
                    cmd2 = "taskkill /IM vlc.exe";
                    o.writeUTF(cmd2);
                    cmd2 = cmd + " \"F:\\TV Series\\Marvel One Shot - Item 47.mkv\"";
                    o.writeUTF(cmd2);
                    break;

                case R.id.bexit:
                    //cmd2 = "taskkill /IM vlc.exe";
                    //o.writeUTF(cmd2);
                    cmd2 = "exit";
                    o.writeUTF(cmd2);
                    o.close();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    //System.exit(1);
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
