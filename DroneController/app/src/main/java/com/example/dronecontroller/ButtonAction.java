package com.example.dronecontroller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by takumi on 11/19/15.
 */
public class ButtonAction implements View.OnClickListener {
    private Context context;
    BatteryGetter batteryGetter;
    ConnectionStatus cStatus;
    private int count;

    ButtonAction(Context c,  BatteryGetter getter, ConnectionStatus connectionStatus){
        context = c;
        batteryGetter=getter;
        cStatus=connectionStatus;
        count=0;
    }
    int i=0;
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button_connect:
                if(count==0) {
                    Log.d("MyApp", "接続開始");
                    AsyncDroneconnection connect = new AsyncDroneconnection();
                    connect.execute(1);
                    batteryGetter.execute();
                    cStatus.setConnect();
                    Toast.makeText(context.getApplicationContext(), "接続完了", Toast.LENGTH_SHORT).show();
                    count=1;
                }
                break;
            case R.id.button_takeoff:
                if (i == 0) {
                    Log.d("MyApp", "離陸しますわ");
                    Toast.makeText(context.getApplicationContext(), "離陸します！", Toast.LENGTH_SHORT).show();
                    AsyncDroneconnection takeoff=new AsyncDroneconnection();
                    takeoff.execute(2);
                    i = 1;
                } else if (i == 1) {
                    Log.d("MyApp", "着陸しますわね");
                    Toast.makeText(context.getApplicationContext(), "着陸します", Toast.LENGTH_SHORT).show();
                    AsyncDroneconnection land=new AsyncDroneconnection();
                    land.execute(3);
                    i = 0;
                }
                break;
            case R.id.button_stop:
                if(count==1) {
                    Log.d("MyApp", "停止");
                    AsyncDroneconnection stop = new AsyncDroneconnection();
                    stop.execute(12);
                    batteryGetter.cancel();
                    cStatus.setDisconnect();
                    Toast.makeText(context.getApplicationContext(), "停止", Toast.LENGTH_SHORT).show();
                    count = 0;
                }
                break;
        }
    }
}
