package com.example.dronecontroller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by takumi on 11/19/15.
 */
public class ButtonAction implements View.OnClickListener {
    private Context context;
    BatteryGetter batteryGetter;
    WidgetStatus wStatus;
    private int flagConnect, flagTakeoff;

    ButtonAction(Context c,  BatteryGetter g, WidgetStatus w){
        context = c;
        batteryGetter=g;
        wStatus =w;
        flagConnect =0;
        flagTakeoff =0;
    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button_connect:
                if(flagConnect ==0 && flagTakeoff==0) {
                    Log.d("MyApp", "接続開始");
                    AsyncDroneconnection connect = new AsyncDroneconnection();
                    connect.execute(1);
                    batteryGetter.execute();//バッテリー取得開始
                    wStatus.setConnect();//ボタンの表示変更
                    Toast.makeText(context.getApplicationContext(), "接続完了", Toast.LENGTH_SHORT).show();
                    flagConnect =1;
                }else if(flagConnect ==1 && flagTakeoff==0) {
                    Log.d("MyApp", "停止");
                    AsyncDroneconnection stop = new AsyncDroneconnection();
                    stop.execute(12);
                    batteryGetter.cancel();//バッテリー取得終了
                    wStatus.setDisconnect();//ボタンの表示変更
                    Toast.makeText(context.getApplicationContext(), "停止", Toast.LENGTH_SHORT).show();
                    flagConnect = 0;
                }
                break;
            case R.id.button_takeoff:
                if (flagTakeoff == 0&& flagConnect ==1) {
                    Log.d("MyApp", "離陸しますわ");
                    Toast.makeText(context.getApplicationContext(), "離陸します！", Toast.LENGTH_SHORT).show();
                    wStatus.setLand();//ボタンの表示変更
                    AsyncDroneconnection takeoff=new AsyncDroneconnection();
                    takeoff.execute(2);
                    flagTakeoff = 1;
                } else if (flagTakeoff == 1 && flagConnect ==1) {
                    Log.d("MyApp", "着陸しますわね");
                    Toast.makeText(context.getApplicationContext(), "着陸します", Toast.LENGTH_SHORT).show();
                    wStatus.setTakeoff();//ボタンの表示変更
                    AsyncDroneconnection land=new AsyncDroneconnection();
                    land.execute(3);
                    flagTakeoff = 0;
                }
                break;
        }
    }
}
