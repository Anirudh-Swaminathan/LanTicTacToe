package com.anirudh.anirudhswami.ticserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int SocketServerPort  = 9089;
    ServerSocket serverSocket;
    List<Player> users;
    TextView IPtxt;

    final String TAG = "TIC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IPtxt = (TextView) findViewById(R.id.ipText);

        users = new ArrayList<Player>();

        TicServerThread ticServerThread = new TicServerThread();
        ticServerThread.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(serverSocket!=null){
            try {
                serverSocket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    private class TicServerThread extends Thread{
        int count = 0;
        @Override
        public void run() {
            //super.run();
            Socket socket = null;
            try {
                serverSocket = new ServerSocket(SocketServerPort);

                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"Running on "+serverSocket.getLocalPort(),Toast.LENGTH_SHORT).show();
                        IPtxt.setText("Running on " + serverSocket.getLocalPort());
                        Log.d("MainActivity","Port "+serverSocket.getLocalPort());
                    }
                });

                while (true){
                    socket = serverSocket.accept();
                    Player player = new Player();
                    users.add(player);
                    count++;
                    if(count<=2) {
                        ConnectThread connectThread = new ConnectThread(player, socket);
                        connectThread.start();
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }




    private class ConnectThread extends Thread{
        Socket socket;
        Player player;
        String data = "";

        ConnectThread(Player play,Socket sock){
            player = play;
            socket = sock;
            play.socket = sock;
            play.play = this;
        }

        @Override
        public void run() {
            //super.run();
            DataInputStream ins = null;
            DataOutputStream out = null;

            try{
                ins = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                while (true){
                    if(ins.available()>0){
                        String msg = ins.readUTF();
                        broadcastMsg(msg);
                    }
                    if(!data.equals("")){
                        out.writeUTF(data);
                        out.flush();
                        data = "";
                    }
                }

            } catch (IOException e){
                e.printStackTrace();
                Log.d(TAG,"Error is "+e.getMessage());
            } finally {
                if(ins != null){
                    try{
                        ins.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if(out!= null){
                    try {
                        out.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                users.remove(player);
            }
        }
        private void sendMsg(String msg){
            data = msg;
        }
    }

    private void broadcastMsg(String msg) {
        for (int i = 0; i < users.size(); i++) {
            users.get(i).play.sendMsg(msg);
        }
    }

    class Player{
        Socket socket;
        ConnectThread play;
    }





}
