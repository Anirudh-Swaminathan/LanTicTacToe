package com.anirudh.anirudhswami.ticgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Tic extends AppCompatActivity {

    static final int SocketServerPort  = 9089;
    String IPAdd;

    static String TAG = "TEST";

    ImageButton r0c0,r0c1,r0c2,r1c0,r1c1,r1c2,r2c0,r2c1,r2c2;
    TextView win;
    boolean sor = false,end = false;
    int nuTap = 0;

    TicClientThread cThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic);
        Intent a = getIntent();
        IPAdd = a.getStringExtra("IP");

        r0c0 = (ImageButton) findViewById(R.id.im00);
        r0c0.setTag(R.mipmap.yellow);
        r0c1 = (ImageButton) findViewById(R.id.im01);
        r0c1.setTag(R.mipmap.yellow);
        r0c2 = (ImageButton) findViewById(R.id.im02);
        r0c2.setTag(R.mipmap.yellow);
        r1c0 = (ImageButton) findViewById(R.id.im10);
        r1c0.setTag(R.mipmap.yellow);
        r1c1 = (ImageButton) findViewById(R.id.im11);
        r1c1.setTag(R.mipmap.yellow);
        r1c2 = (ImageButton) findViewById(R.id.im12);
        r1c2.setTag(R.mipmap.yellow);
        r2c0 = (ImageButton) findViewById(R.id.im20);
        r2c0.setTag(R.mipmap.yellow);
        r2c1 = (ImageButton) findViewById(R.id.im21);
        r2c1.setTag(R.mipmap.yellow);
        r2c2 = (ImageButton) findViewById(R.id.im22);
        r2c2.setTag(R.mipmap.yellow);

        win = (TextView) findViewById(R.id.winner);

        //Connect to serrver
        cThread = new TicClientThread(IPAdd,SocketServerPort);
        cThread.start();

        //The onclick for each imageButton
        r0c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int)r0c0.getTag() == (int) R.mipmap.yellow) {
                    if (sor) {
                        r0c0.setImageResource(R.mipmap.nought);
                        r0c0.setTag(R.mipmap.nought);
                        sor = false;
                    } else {
                        r0c0.setImageResource(R.mipmap.cross);
                        r0c0.setTag(R.mipmap.cross);
                        sor = true;
                    }
                    //r0c0.setEnabled(false);
                    nuTap++;
                    if (nuTap == 9 && !end) {
                        Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                        win.setText("Game is a draw");
                    }

                    //Check row
                    int no = (int) r0c0.getTag();
                    int ra = (int) r0c1.getTag();
                    int rb = (int) r0c2.getTag();
                    if (no == ra && no == rb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check column
                    int ca = (int) r1c0.getTag();
                    int cb = (int) r2c0.getTag();
                    if (no == ca && no == cb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check diagonal
                    int da = (int) r1c1.getTag();
                    int db = (int) r2c2.getTag();
                    if (no == da && no == db) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    disableAll();
                    cThread.sendMessage("1");
                }
            }
        });

        r0c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int)r0c1.getTag() == (int) R.mipmap.yellow) {
                    if (sor) {
                        r0c1.setImageResource(R.mipmap.nought);
                        r0c1.setTag(R.mipmap.nought);
                        sor = false;
                    } else {
                        r0c1.setImageResource(R.mipmap.cross);
                        r0c1.setTag(R.mipmap.cross);
                        sor = true;
                    }
                    //r0c1.setEnabled(false);
                    nuTap++;
                    if (nuTap == 9 && !end) {
                        Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                        win.setText("Game is a draw");
                    }

                    //Check row
                    int no = (int) r0c1.getTag();
                    int ra = (int) r0c0.getTag();
                    int rb = (int) r0c2.getTag();
                    if (no == ra && no == rb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check column
                    int ca = (int) r1c1.getTag();
                    int cb = (int) r2c1.getTag();
                    if (no == ca && no == cb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    disableAll();
                    cThread.sendMessage("2");
                }
            }
        });

        r0c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int)r0c2.getTag() == (int) R.mipmap.yellow) {
                    if (sor) {
                        r0c2.setImageResource(R.mipmap.nought);
                        r0c2.setTag(R.mipmap.nought);
                        sor = false;
                    } else {
                        r0c2.setImageResource(R.mipmap.cross);
                        r0c2.setTag(R.mipmap.cross);
                        sor = true;
                    }
                    //r0c2.setEnabled(false);
                    nuTap++;
                    if (nuTap == 9 && !end) {
                        Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                        win.setText("Game is a draw");
                    }

                    //Check row
                    int no = (int) r0c2.getTag();
                    int ra = (int) r0c1.getTag();
                    int rb = (int) r0c0.getTag();
                    if (no == ra && no == rb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check column
                    int ca = (int) r1c2.getTag();
                    int cb = (int) r2c2.getTag();
                    if (no == ca && no == cb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check diagonal
                    int da = (int) r1c1.getTag();
                    int db = (int) r2c0.getTag();
                    if (no == da && no == db) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                        }
                        disableAll();
                    }
                    disableAll();
                    cThread.sendMessage("3");

                }
            }
        });

        r1c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int)r1c0.getTag() == (int) R.mipmap.yellow) {
                    if (sor) {
                        r1c0.setImageResource(R.mipmap.nought);
                        r1c0.setTag(R.mipmap.nought);
                        sor = false;
                    } else {
                        r1c0.setImageResource(R.mipmap.cross);
                        r1c0.setTag(R.mipmap.cross);
                        sor = true;
                    }
                    //r1c0.setEnabled(false);
                    nuTap++;
                    if (nuTap == 9 && !end) {
                        Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                        win.setText("Game is a draw");
                    }

                    //Check row
                    int no = (int) r1c0.getTag();
                    int ra = (int) r1c1.getTag();
                    int rb = (int) r1c2.getTag();
                    if (no == ra && no == rb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check column
                    int ca = (int) r0c0.getTag();
                    int cb = (int) r2c0.getTag();
                    if (no == ca && no == cb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    disableAll();
                    cThread.sendMessage("4");
                }
            }
        });

        r1c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int)r1c1.getTag() == (int) R.mipmap.yellow) {
                    if (sor) {
                        r1c1.setImageResource(R.mipmap.nought);
                        r1c1.setTag(R.mipmap.nought);
                        sor = false;
                    } else {
                        r1c1.setImageResource(R.mipmap.cross);
                        r1c1.setTag(R.mipmap.cross);
                        sor = true;
                    }
                    //r1c1.setEnabled(false);
                    nuTap++;
                    if (nuTap == 9 && !end) {
                        Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                        win.setText("Game is a draw");
                    }

                    //Check row
                    int no = (int) r1c1.getTag();
                    int ra = (int) r1c0.getTag();
                    int rb = (int) r1c2.getTag();
                    if (no == ra && no == rb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check column
                    int ca = (int) r0c1.getTag();
                    int cb = (int) r2c1.getTag();
                    if (no == ca && no == cb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check diagonal
                    int da = (int) r0c0.getTag();
                    int db = (int) r2c2.getTag();
                    if (no == da && no == db) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }

                    //Check other dia
                    int ea = (int) r0c2.getTag();
                    int eb = (int) r2c0.getTag();
                    if (no == ea && no == eb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    disableAll();
                    cThread.sendMessage("5");
                }
            }
        });

        r1c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int)r1c2.getTag() == (int) R.mipmap.yellow) {
                    if (sor) {
                        r1c2.setImageResource(R.mipmap.nought);
                        r1c2.setTag(R.mipmap.nought);
                        sor = false;
                    } else {
                        r1c2.setImageResource(R.mipmap.cross);
                        r1c2.setTag(R.mipmap.cross);
                        sor = true;
                    }
                    //r1c2.setEnabled(false);
                    nuTap++;
                    if (nuTap == 9 && !end) {
                        Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                        win.setText("Game is a draw");
                    }
                    //Check row
                    int no = (int) r1c2.getTag();
                    int ra = (int) r1c0.getTag();
                    int rb = (int) r1c1.getTag();
                    if (no == ra && no == rb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check column
                    int ca = (int) r0c2.getTag();
                    int cb = (int) r2c2.getTag();
                    if (no == ca && no == cb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    disableAll();
                    cThread.sendMessage("6");
                }
            }
        });

        r2c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int)r2c0.getTag() == (int) R.mipmap.yellow) {
                    if (sor) {
                        r2c0.setImageResource(R.mipmap.nought);
                        r2c0.setTag(R.mipmap.nought);
                        sor = false;
                    } else {
                        r2c0.setImageResource(R.mipmap.cross);
                        r2c0.setTag(R.mipmap.cross);
                        sor = true;
                    }
                    //r2c0.setEnabled(false);
                    nuTap++;
                    if (nuTap == 9 && !end) {
                        Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                        win.setText("Game is a draw");
                    }

                    //Check row
                    int no = (int) r2c0.getTag();
                    int ra = (int) r2c1.getTag();
                    int rb = (int) r2c2.getTag();
                    if (no == ra && no == rb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check column
                    int ca = (int) r0c0.getTag();
                    int cb = (int) r1c0.getTag();
                    if (no == ca && no == cb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check diagonal
                    int da = (int) r0c2.getTag();
                    int db = (int) r1c1.getTag();
                    if (no == da && no == db) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    disableAll();
                    cThread.sendMessage("7");
                }
            }
        });
        r2c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int)r2c1.getTag() == (int) R.mipmap.yellow) {
                    if (sor) {
                        r2c1.setImageResource(R.mipmap.nought);
                        r2c1.setTag(R.mipmap.nought);
                        sor = false;
                    } else {
                        r2c1.setImageResource(R.mipmap.cross);
                        r2c1.setTag(R.mipmap.cross);
                        sor = true;
                    }
                    //r2c1.setEnabled(false);
                    nuTap++;
                    if (nuTap == 9 && !end) {
                        Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                        win.setText("Game is a draw");
                    }

                    //Check row
                    int no = (int) r2c1.getTag();
                    int ra = (int) r2c0.getTag();
                    int rb = (int) r2c2.getTag();
                    if (no == ra && no == rb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check column
                    int ca = (int) r0c1.getTag();
                    int cb = (int) r1c1.getTag();
                    if (no == ca && no == cb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    disableAll();
                    cThread.sendMessage("8");
                }
            }
        });

        r2c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int)r2c2.getTag() == (int) R.mipmap.yellow) {
                    if (sor) {
                        r2c2.setImageResource(R.mipmap.nought);
                        r2c2.setTag(R.mipmap.nought);
                        sor = false;
                    } else {
                        r2c2.setImageResource(R.mipmap.cross);
                        r2c2.setTag(R.mipmap.cross);
                        sor = true;
                    }
                    //r2c2.setEnabled(false);
                    nuTap++;
                    if (nuTap == 9 && !end) {
                        Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                        win.setText("Game is a draw");
                    }

                    //Check row
                    int no = (int) r2c2.getTag();
                    int ra = (int) r2c0.getTag();
                    int rb = (int) r2c1.getTag();
                    if (no == ra && no == rb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check column
                    int ca = (int) r0c2.getTag();
                    int cb = (int) r1c0.getTag();
                    if (no == ca && no == cb) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    //Check diagonal
                    int da = (int) r0c0.getTag();
                    int db = (int) r1c1.getTag();
                    if (no == da && no == db) {
                        end = true;
                        if (no == (int) R.mipmap.cross) {
                            Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 1 won");
                        } else {
                            Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                            win.setText("Player 2 won");
                        }
                        disableAll();
                    }
                    disableAll();
                    cThread.sendMessage("9");
                }
            }
        });
        //Ends here

    }

    public void disableAll(){
        r0c0.setEnabled(false);r0c1.setEnabled(false);r0c2.setEnabled(false);
        r1c0.setEnabled(false);r1c1.setEnabled(false);r1c2.setEnabled(false);
        r2c0.setEnabled(false);r2c1.setEnabled(false);r2c2.setEnabled(false);
    }

    public void enableAll(){
        r0c0.setEnabled(true);r0c1.setEnabled(true);r0c2.setEnabled(true);
        r1c0.setEnabled(true);r1c1.setEnabled(true);r1c2.setEnabled(true);
        r2c0.setEnabled(true);r2c1.setEnabled(true);r2c2.setEnabled(true);
    }

    private class TicClientThread extends Thread{
        String dstAddress;
        int dstPort;

        String msgToSend = "";

        TicClientThread(String address, int port){
            dstAddress = address;
            dstPort = port;
        }

        @Override
        public void run() {
            Socket socket = null;
            DataOutputStream dataOutputStream = null;
            DataInputStream dataInputStream = null;
            //super.run();
            try{
                socket = new Socket(dstAddress, dstPort);
                dataOutputStream = new DataOutputStream(
                        socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                while (true){
                    if(dataInputStream.available()>0){
                        final String id = dataInputStream.readUTF();
                        Tic.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(Tic.this,"Input data is "+id,Toast.LENGTH_SHORT).show();
                                switch (id){
                                    case "1":
                                        if ((int)r0c0.getTag() == (int) R.mipmap.yellow) {
                                            if (sor) {
                                                r0c0.setImageResource(R.mipmap.nought);
                                                r0c0.setTag(R.mipmap.nought);
                                                sor = false;
                                            } else {
                                                r0c0.setImageResource(R.mipmap.cross);
                                                r0c0.setTag(R.mipmap.cross);
                                                sor = true;
                                            }
                                            //r0c0.setEnabled(false);
                                            nuTap++;
                                            if (nuTap == 9 && !end) {
                                                Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                                                win.setText("Game is a draw");
                                            }

                                            //Check row
                                            int no = (int) r0c0.getTag();
                                            int ra = (int) r0c1.getTag();
                                            int rb = (int) r0c2.getTag();
                                            if (no == ra && no == rb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check column
                                            int ca = (int) r1c0.getTag();
                                            int cb = (int) r2c0.getTag();
                                            if (no == ca && no == cb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check diagonal
                                            int da = (int) r1c1.getTag();
                                            int db = (int) r2c2.getTag();
                                            if (no == da && no == db) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            if(!end) enableAll();
                                        }
                                        break;
                                    case "2":

                                        if ((int)r0c1.getTag() == (int) R.mipmap.yellow) {
                                            if (sor) {
                                                r0c1.setImageResource(R.mipmap.nought);
                                                r0c1.setTag(R.mipmap.nought);
                                                sor = false;
                                            } else {
                                                r0c1.setImageResource(R.mipmap.cross);
                                                r0c1.setTag(R.mipmap.cross);
                                                sor = true;
                                            }
                                            //r0c1.setEnabled(false);
                                            nuTap++;
                                            if (nuTap == 9 && !end) {
                                                Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                                                win.setText("Game is a draw");
                                            }

                                            //Check row
                                            int no = (int) r0c1.getTag();
                                            int ra = (int) r0c0.getTag();
                                            int rb = (int) r0c2.getTag();
                                            if (no == ra && no == rb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check column
                                            int ca = (int) r1c1.getTag();
                                            int cb = (int) r2c1.getTag();
                                            if (no == ca && no == cb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            if(!end) enableAll();
                                        }
                                        break;
                                    case "3":
                                        if ((int)r0c2.getTag() == (int) R.mipmap.yellow) {
                                            if (sor) {
                                                r0c2.setImageResource(R.mipmap.nought);
                                                r0c2.setTag(R.mipmap.nought);
                                                sor = false;
                                            } else {
                                                r0c2.setImageResource(R.mipmap.cross);
                                                r0c2.setTag(R.mipmap.cross);
                                                sor = true;
                                            }
                                            //r0c2.setEnabled(false);
                                            nuTap++;
                                            if (nuTap == 9 && !end) {
                                                Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                                                win.setText("Game is a draw");
                                            }

                                            //Check row
                                            int no = (int) r0c2.getTag();
                                            int ra = (int) r0c1.getTag();
                                            int rb = (int) r0c0.getTag();
                                            if (no == ra && no == rb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check column
                                            int ca = (int) r1c2.getTag();
                                            int cb = (int) r2c2.getTag();
                                            if (no == ca && no == cb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check diagonal
                                            int da = (int) r1c1.getTag();
                                            int db = (int) r2c0.getTag();
                                            if (no == da && no == db) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                }
                                                disableAll();
                                            }
                                            if(!end) enableAll();
                                        }
                                        break;
                                    case "4":
                                        if ((int)r1c0.getTag() == (int) R.mipmap.yellow) {
                                            if (sor) {
                                                r1c0.setImageResource(R.mipmap.nought);
                                                r1c0.setTag(R.mipmap.nought);
                                                sor = false;
                                            } else {
                                                r1c0.setImageResource(R.mipmap.cross);
                                                r1c0.setTag(R.mipmap.cross);
                                                sor = true;
                                            }
                                            //r1c0.setEnabled(false);
                                            nuTap++;
                                            if (nuTap == 9 && !end) {
                                                Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                                                win.setText("Game is a draw");
                                            }

                                            //Check row
                                            int no = (int) r1c0.getTag();
                                            int ra = (int) r1c1.getTag();
                                            int rb = (int) r1c2.getTag();
                                            if (no == ra && no == rb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check column
                                            int ca = (int) r0c0.getTag();
                                            int cb = (int) r2c0.getTag();
                                            if (no == ca && no == cb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            if(!end) enableAll();
                                        }
                                        break;
                                    case "5":
                                        if ((int)r1c1.getTag() == (int) R.mipmap.yellow) {
                                            if (sor) {
                                                r1c1.setImageResource(R.mipmap.nought);
                                                r1c1.setTag(R.mipmap.nought);
                                                sor = false;
                                            } else {
                                                r1c1.setImageResource(R.mipmap.cross);
                                                r1c1.setTag(R.mipmap.cross);
                                                sor = true;
                                            }
                                            //r1c1.setEnabled(false);
                                            nuTap++;
                                            if (nuTap == 9 && !end) {
                                                Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                                                win.setText("Game is a draw");
                                            }

                                            //Check row
                                            int no = (int) r1c1.getTag();
                                            int ra = (int) r1c0.getTag();
                                            int rb = (int) r1c2.getTag();
                                            if (no == ra && no == rb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check column
                                            int ca = (int) r0c1.getTag();
                                            int cb = (int) r2c1.getTag();
                                            if (no == ca && no == cb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check diagonal
                                            int da = (int) r0c0.getTag();
                                            int db = (int) r2c2.getTag();
                                            if (no == da && no == db) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }

                                            //Check other dia
                                            int ea = (int) r0c2.getTag();
                                            int eb = (int) r2c0.getTag();
                                            if (no == ea && no == eb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            if(!end) enableAll();
                                        }
                                        break;
                                    case "6":
                                        if ((int)r1c2.getTag() == (int) R.mipmap.yellow) {
                                            if (sor) {
                                                r1c2.setImageResource(R.mipmap.nought);
                                                r1c2.setTag(R.mipmap.nought);
                                                sor = false;
                                            } else {
                                                r1c2.setImageResource(R.mipmap.cross);
                                                r1c2.setTag(R.mipmap.cross);
                                                sor = true;
                                            }
                                            //r1c2.setEnabled(false);
                                            nuTap++;
                                            if (nuTap == 9 && !end) {
                                                Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                                                win.setText("Game is a draw");
                                            }
                                            //Check row
                                            int no = (int) r1c2.getTag();
                                            int ra = (int) r1c0.getTag();
                                            int rb = (int) r1c1.getTag();
                                            if (no == ra && no == rb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check column
                                            int ca = (int) r0c2.getTag();
                                            int cb = (int) r2c2.getTag();
                                            if (no == ca && no == cb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            if(!end) enableAll();
                                        }
                                        break;
                                    case "7":
                                        if ((int)r2c0.getTag() == (int) R.mipmap.yellow) {
                                            if (sor) {
                                                r2c0.setImageResource(R.mipmap.nought);
                                                r2c0.setTag(R.mipmap.nought);
                                                sor = false;
                                            } else {
                                                r2c0.setImageResource(R.mipmap.cross);
                                                r2c0.setTag(R.mipmap.cross);
                                                sor = true;
                                            }
                                            //r2c0.setEnabled(false);
                                            nuTap++;
                                            if (nuTap == 9 && !end) {
                                                Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                                                win.setText("Game is a draw");
                                            }

                                            //Check row
                                            int no = (int) r2c0.getTag();
                                            int ra = (int) r2c1.getTag();
                                            int rb = (int) r2c2.getTag();
                                            if (no == ra && no == rb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check column
                                            int ca = (int) r0c0.getTag();
                                            int cb = (int) r1c0.getTag();
                                            if (no == ca && no == cb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check diagonal
                                            int da = (int) r0c2.getTag();
                                            int db = (int) r1c1.getTag();
                                            if (no == da && no == db) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            if(!end) enableAll();
                                        }
                                        break;
                                    case "8":
                                        if ((int)r2c1.getTag() == (int) R.mipmap.yellow) {
                                            if (sor) {
                                                r2c1.setImageResource(R.mipmap.nought);
                                                r2c1.setTag(R.mipmap.nought);
                                                sor = false;
                                            } else {
                                                r2c1.setImageResource(R.mipmap.cross);
                                                r2c1.setTag(R.mipmap.cross);
                                                sor = true;
                                            }
                                            //r2c1.setEnabled(false);
                                            nuTap++;
                                            if (nuTap == 9 && !end) {
                                                Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                                                win.setText("Game is a draw");
                                            }

                                            //Check row
                                            int no = (int) r2c1.getTag();
                                            int ra = (int) r2c0.getTag();
                                            int rb = (int) r2c2.getTag();
                                            if (no == ra && no == rb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check column
                                            int ca = (int) r0c1.getTag();
                                            int cb = (int) r1c1.getTag();
                                            if (no == ca && no == cb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            if(!end) enableAll();
                                        }
                                        break;
                                    case "9":
                                        if ((int)r2c2.getTag() == (int) R.mipmap.yellow) {
                                            if (sor) {
                                                r2c2.setImageResource(R.mipmap.nought);
                                                r2c2.setTag(R.mipmap.nought);
                                                sor = false;
                                            } else {
                                                r2c2.setImageResource(R.mipmap.cross);
                                                r2c2.setTag(R.mipmap.cross);
                                                sor = true;
                                            }
                                            //r2c2.setEnabled(false);
                                            nuTap++;
                                            if (nuTap == 9 && !end) {
                                                Toast.makeText(Tic.this, "Game is a draw", Toast.LENGTH_SHORT).show();
                                                win.setText("Game is a draw");
                                            }

                                            //Check row
                                            int no = (int) r2c2.getTag();
                                            int ra = (int) r2c0.getTag();
                                            int rb = (int) r2c1.getTag();
                                            if (no == ra && no == rb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check column
                                            int ca = (int) r0c2.getTag();
                                            int cb = (int) r1c0.getTag();
                                            if (no == ca && no == cb) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            //Check diagonal
                                            int da = (int) r0c0.getTag();
                                            int db = (int) r1c1.getTag();
                                            if (no == da && no == db) {
                                                end = true;
                                                if (no == (int) R.mipmap.cross) {
                                                    Toast.makeText(Tic.this, "Player 1 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 1 won");
                                                } else {
                                                    Toast.makeText(Tic.this, "Player 2 won ", Toast.LENGTH_SHORT).show();
                                                    win.setText("Player 2 won");
                                                }
                                                disableAll();
                                            }
                                            if(!end) enableAll();
                                        }
                                        break;
                                    default:

                                }
                            }
                        });
                    }
                    if (!msgToSend.equals("")) {
                        dataOutputStream.writeUTF(msgToSend);
                        dataOutputStream.flush();
                        msgToSend = "";
                    }
                }
            } catch (UnknownHostException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        private void sendMessage(String msg){
            msgToSend = msg;
        }
    }

}
