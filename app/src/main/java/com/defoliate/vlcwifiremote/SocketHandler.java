package com.defoliate.vlcwifiremote;

import java.net.Socket;

public class SocketHandler
{
    private static Socket socket;
    private static String ip;

    public static synchronized Socket getSocket()
    {
        return socket;
    }

    public static synchronized String getIP()
    {
        return ip;
    }

    public static synchronized void setSocket(Socket socket)
    {
        SocketHandler.socket = socket;
    }

    public static synchronized void setIP(String ip)
    {
        SocketHandler.ip = ip;
    }
}
