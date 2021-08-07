package com.example.kebunku;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import com.example.kebunku.Adapter.Command;
import com.example.kebunku.Adapter.DataPosisi;
import com.example.kebunku.Custom.image;

import org.json.JSONException;
import org.json.JSONObject;

public class fitur extends AppCompatActivity{
    private static image imageView;
    private Command command = new Command();

    private static TextView txtInfo;
    private EditText EdtH;
    private EditText EdtV;
    private EditText EdtT;
    private EditText Point;
    private EditText Delay;

    public static DataPosisi dataPosisi;

    public static boolean isActive = false;
    private String Vacuum, Water;

    public static void updateInfo(JSONObject info){
        try {
            float posx = (float)((float) info.getInt("X_POSITION_CM") / dataPosisi.xDim * 1.0);
            float posy = (float)((float) info.getInt("Y_POSITION_CM") / dataPosisi.yDim * 1.0);
            int actx = (int) (posx * imageView.getWidth());
            int acty = (int) (posy * imageView.getHeight());
            Log.d("DEBUGSUKMA", info.getInt("X_POSITION_CM") + "," + dataPosisi.xDim + "," + posx + "," + actx);
            imageView.posActX = actx;
            imageView.posActY = acty;
            imageView.invalidate();
            int actPoint = dataPosisi.update(info.getInt("X_POSITION_CM"),info.getInt("Y_POSITION_CM"),info.getInt("Z_POSITION_CM"));
            txtInfo.setText(String.format("X: %d, Y: %d, Point: %d", info.getInt("X_POSITION_CM"), info.getInt("Y_POSITION_CM"), actPoint));
        } catch (JSONException e) {
            Log.d("DEBUGSUKMA",e.getMessage());
        }
    }
    @Override
    protected void onStart() {
        isActive = true;
        super.onStart();
    }
    @Override
    protected void onStop() {
        isActive = false;
        super.onStop();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent = getIntent();

        command.setId(intent.getStringExtra("id"));
        command.setKey(intent.getStringExtra("key"));
        dataPosisi = new DataPosisi();

        setContentView(R.layout.activity_fitur);

        txtInfo = findViewById(R.id.fiturTxtInformation);
        Button BtnXmax = findViewById(R.id.fiturBtnXmax);
        Button BtnXmin = findViewById(R.id.fiturBtnXmin);
        Button BtnYmax = findViewById(R.id.fiturBtnYmax);
        Button BtnYmin = findViewById(R.id.fiturBtnYmin);
        Button BtnZmax = findViewById(R.id.fiturBtnZmax);
        Button BtnZmin = findViewById(R.id.fiturBtnZmin);
        Button btnGo = findViewById(R.id.fiturBtnGO);
        EdtH = findViewById(R.id.fiturEdtH);
        EdtV = findViewById(R.id.fiturEdtV);
        EdtT = findViewById(R.id.fiturEdtT);
        Point = findViewById(R.id.fiturPoint);
        Delay = findViewById(R.id.fiturDelay);
        Button BtnSiramOn = findViewById(R.id.fiturBtnSiramOn);
        Button BtnSiramOff = findViewById(R.id.fiturBtnSiramOff);
        Button BtnTanamOn = findViewById(R.id.fiturBtnTanamOn);
        Button BtnTanamOff = findViewById(R.id.fiturBtnTanamOff);
        Button BtnGo2 = findViewById(R.id.fiturBtnGO2);

       
        BtnSiramOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand("W");
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
        BtnSiramOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand("w");
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
        BtnTanamOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand("V");
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
        BtnTanamOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand("v");
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EdtH.getText().toString().isEmpty()) EdtH.setText("0");
                if(EdtV.getText().toString().isEmpty()) EdtV.setText("0");
                if(EdtT.getText().toString().isEmpty()) EdtT.setText("0");

                command.setCommand(dataPosisi.go(Integer.valueOf(EdtH.getText().toString()), Integer.valueOf(EdtV.getText().toString()), Integer.valueOf(EdtT.getText().toString())));
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
        BtnXmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand(dataPosisi.Xmax());
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
        BtnXmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand(dataPosisi.Xmin());
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
       BtnYmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand(dataPosisi.Ymax());
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
        BtnYmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand(dataPosisi.Ymin());
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
        BtnZmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand(dataPosisi.Zmax());
                new Thread(new login.SendThread(command.toString())).start();
            }
        });
        BtnZmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.setCommand(dataPosisi.Zmin());
                new Thread(new login.SendThread(command.toString())).start();
            }
        });

        imageView = findViewById(R.id.fiturCanvas);
        imageView.setOnTouchListener((v, event) -> {
            int[] position = imageView.getPosition(event.getX(), event.getY());
            Log.d("DEBUG", position[0] + " ," + position[1]);

            // update gambar lingkaran
            imageView.posRefX = position[0];
            imageView.posRefY = position[1];
            imageView.invalidate();

            command.setCommand(dataPosisi.goByGrid(position[0], position[1], imageView.getSize()[0], imageView.getSize()[1]));
            new Thread(new login.SendThread(command.toString())).start();
            return false;
        });
        BtnGo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Point.getText().toString().isEmpty())  Point.setText("1");
                int pointToGo = Integer.parseInt(Point.getText().toString());
                pointToGo = MathUtils.clamp(pointToGo, 1, dataPosisi.maxPoint);

                int[] position = dataPosisi.getPosByPoint(pointToGo);
                imageView.posRefX = position[0];
                imageView.posRefY = position[1];
                imageView.invalidate();

                //int delayDiam = 1000;  // millliseconds
                int delayDiam = Integer.parseInt(Delay.getText().toString());

                int finalPointToGo = pointToGo;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(finalPointToGo > dataPosisi.point){
                            for (int now = dataPosisi.point; now <= finalPointToGo; now++){
                                int[] newPosition = dataPosisi.getPosByPoint(now);
                                Log.d("DEBUGSUKMA","now: " + now +",togo: "+finalPointToGo+"newPosition: "+newPosition[0]+","+newPosition[1]);

                                command.setCommand(dataPosisi.goByGrid(newPosition[0], newPosition[1], imageView.getSize()[0], imageView.getSize()[1]));
                                new Thread(new login.SendThread(command.toString())).start();

                                while (dataPosisi.x != dataPosisi.getCmByPos(newPosition[0], newPosition[1], imageView.getSize()[0], imageView.getSize()[1])[0]
                                        || dataPosisi.y != dataPosisi.getCmByPos(newPosition[0], newPosition[1], imageView.getSize()[0], imageView.getSize()[1])[1]){}
                                try {
                                    Thread.sleep(delayDiam);
                                } catch (InterruptedException e) {
                                    Log.d("DEBUGSUKMA", e.getMessage());
                                }
                            }
                        } else {
                            for (int now = dataPosisi.point; now >= finalPointToGo; now--){
                                int[] newPosition = dataPosisi.getPosByPoint(now);

                                command.setCommand(dataPosisi.goByGrid(newPosition[0], newPosition[1], imageView.getSize()[0], imageView.getSize()[1]));
                                new Thread(new login.SendThread(command.toString())).start();

                                while (dataPosisi.x != dataPosisi.getCmByPos(newPosition[0], newPosition[1], imageView.getSize()[0], imageView.getSize()[1])[0]
                                        || dataPosisi.y != dataPosisi.getCmByPos(newPosition[0], newPosition[1], imageView.getSize()[0], imageView.getSize()[1])[1]){}
                                try {
                                    Thread.sleep(delayDiam);
                                } catch (InterruptedException e) {
                                    Log.d("DEBUGSUKMA", e.getMessage());
                                }
                            }
                        }
                    }
                }).start();
            }
        });

        command.setCommand(dataPosisi.go(0,0, 0));
        new Thread(new login.SendThread(command.toString())).start();
    }
}
