package com.example.dronecontroller;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by takumi on 11/19/15.
 */
public class ButtonAction implements View.OnClickListener {
    int i=0;
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button_connect:
                Log.d("MyApp", "接続開始");
                AsyncDroneconnection connect=new AsyncDroneconnection();
                connect.execute(1);
                break;

            case R.id.button_takeoff:
                if (i == 0) {
                    Log.d("MyApp", "離陸しますわ");
                    //Toast.makeText(this, "離陸します！", Toast.LENGTH_LONG).show();
                    AsyncDroneconnection takeoff=new AsyncDroneconnection();
                    takeoff.execute(2);
                    i = 1;
                } else if (i == 1) {
                    Log.d("MyApp", "着陸しますわね");
                    AsyncDroneconnection land=new AsyncDroneconnection();
                    land.execute(3);
                    i = 0;
                }
                break;
        }
    }
}
