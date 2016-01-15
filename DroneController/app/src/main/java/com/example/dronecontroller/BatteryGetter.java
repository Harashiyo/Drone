package com.example.dronecontroller;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by Shohei on 2015/12/18.
 */
public class BatteryGetter extends AbstractPeriodicTask {

    TextView batteryView;

    public BatteryGetter(long period, boolean isDaemon, TextView textView) {
        super(period, isDaemon);
        batteryView=textView;
    }

    @Override
    protected void invokersMethod() {
        //周期的に繰り返される部分
        AsyncDroneconnection battery=new AsyncDroneconnection();
        battery.execute(13);//バッテリー取得
        batteryView.setText("バッテリー: "+ Integer.toString(AsyncDroneconnection.battery) + "%");//テキスト更新
    }

}