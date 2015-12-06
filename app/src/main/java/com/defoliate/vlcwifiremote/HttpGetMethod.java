package com.defoliate.vlcwifiremote;

import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class HttpGetMethod
{
    String data = null;
    BufferedReader br = null;
    Boolean x = false;
    HttpClient s;
    String ip = SocketHandler.getIP();
    HttpURLConnection con;
    URL url;

    public class Httpconnect extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] params)
        {
            try
            {
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(1000);
                con.setDoOutput(true);
                con.connect();
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String receive = "";
                while ((receive = br.readLine()) != null)
                    data = data + receive + "\n";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    public String getInternetData(String command) throws Exception
    {
        try
        {
            Authenticator.setDefault(new Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication("", "mobashshir".toCharArray());
                }
            });
            s = new DefaultHttpClient();
            if(command==null)
                url=new URL("http://" + ip + ":8080/requests/status.xml");
            else
                url = new URL("http://" + ip + ":8080/requests/status.xml?command="+command);
            new Httpconnect().execute();
            Thread.sleep(500);
            return data;

        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return data;
        }
    }
}
