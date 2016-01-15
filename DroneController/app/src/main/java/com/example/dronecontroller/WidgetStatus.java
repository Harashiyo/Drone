package com.example.dronecontroller;

import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Shohei on 2016/01/14.
 */
public class WidgetStatus {
    TextView connection;
    Button takeoff;
    Button connect;
    public WidgetStatus(TextView textView, Button buttonCN, Button buttonTO){
        connection=textView;
        takeoff=buttonTO;
        connect=buttonCN;
    }
    public void setConnect(){
        connection.setText("接続中");
        connect.setText("接続終了");
    }

    public void setDisconnect(){
        connection.setText("未接続");
        connect.setText("接続開始");
    }
    public void setTakeoff(){
        takeoff.setText("Takeoff");
    }
    public void setLand(){
        takeoff.setText("Land");
    }
}
