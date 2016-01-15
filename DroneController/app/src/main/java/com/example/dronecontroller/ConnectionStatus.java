package com.example.dronecontroller;

import android.widget.TextView;

/**
 * Created by Shohei on 2016/01/14.
 */
public class ConnectionStatus {
    TextView connection;
    public ConnectionStatus(TextView textView){
        connection=textView;
    }
    public void setConnect(){
        connection.setText("接続");
    }
    public void setDisconnect(){
        connection.setText("切断");
    }
}
