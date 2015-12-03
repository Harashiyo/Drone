package com.example.dronecontroller;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.shigeodayo.ardrone.processing.ARDroneForP5;
/**
 * Created by takumi on 11/18/15.
 */

public class AsyncDroneconnection extends AsyncTask<Integer, Void, Void> {

    @Override
    protected Void doInBackground(Integer... params) {

        Log.d("MyApp", "mode=" + params[0]);
        if(params[0]==1){
            ControllerActivity.ardrone.connect();
            ControllerActivity.ardrone.connectNav();
            //ardrone.connectVideo();
            ControllerActivity.ardrone.start();
            //Toast.makeText(this, "接続完了", Toast.LENGTH_LONG).show();
        }
        else if(params[0]==2)   ControllerActivity.ardrone.takeOff();//離陸
        else if(params[0]==3)   ControllerActivity.ardrone.landing();//着陸

        else if(params[0]==4)   ControllerActivity.ardrone.up();   //上昇
        else if(params[0]==5)   ControllerActivity.ardrone.down(); //下降

        else if(params[0]==6)   ControllerActivity.ardrone.forward();  //前
        else if(params[0]==7)   ControllerActivity.ardrone.backward(); //後
        else if(params[0]==8)   ControllerActivity.ardrone.goLeft();   //左
        else if(params[0]==9)   ControllerActivity.ardrone.goRight();  //右

        else if(params[0]==10)   ControllerActivity.ardrone.spinRight();//右回転
        else if(params[0]==11)   ControllerActivity.ardrone.spinLeft(); //左回転


        return null;
    }
}
