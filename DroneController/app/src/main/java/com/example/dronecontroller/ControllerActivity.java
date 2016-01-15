package com.example.dronecontroller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shigeodayo.ardrone.processing.*;



public class ControllerActivity extends Activity {
    ImageView dragViewRight;
    ImageView dragViewLeft;
    Button connect;
    Button takeoff;
    Button stop;
    TextView battery;
    TextView connection;
    BatteryGetter batteryGetter;
    ImageView video;
    ConnectionStatus cStatus;

    public static final ARDroneForP5 ardrone = new ARDroneForP5("192.168.1.1");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        dragViewLeft = (ImageView) findViewById(R.id.imageView_pad_left);
        dragViewRight = (ImageView) findViewById(R.id.imageView_pad_right);
        connect = (Button) findViewById(R.id.button_connect);
        takeoff = (Button) findViewById(R.id.button_takeoff);
        stop = (Button) findViewById(R.id.button_stop);
        battery = (TextView)findViewById(R.id.textView_battery);
        video=(ImageView)findViewById(R.id.imgVideo);
        batteryGetter=new BatteryGetter(3000,true,battery);
        connection=(TextView)findViewById(R.id.textView_connect);
        cStatus= new ConnectionStatus(connection);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) { //onResumeの後に呼ばれる
        DragViewListenerLeft listenerLeft = new DragViewListenerLeft(dragViewLeft);
        dragViewLeft.setOnTouchListener(listenerLeft);
        DragViewListenerRight listenerRight = new DragViewListenerRight(dragViewRight);
        dragViewRight.setOnTouchListener(listenerRight);
        ButtonAction button = new ButtonAction(this,batteryGetter,cStatus);
        takeoff.setOnClickListener(button);
        connect.setOnClickListener(button);
        stop.setOnClickListener(button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_controller, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        if(batteryGetter!=null){
            batteryGetter.cancel();
        }
        AsyncDroneconnection disconnect = new AsyncDroneconnection();
        disconnect.execute(12);
        cStatus.setDisconnect();
        super.onPause();
    }


    /*
    void draw() {
        ARDroneForP5 ardrone = new ARDroneForP5();
        //background(204);

        // getting image from AR.Drone
        // true: resizeing image automatically
        // false: not resizing

        PImage img = ardrone.getVideoImage(false);
        if (img == null)    return;
        image(img, 0, 0);

        // print out AR.Drone information
        ardrone.printARDroneInfo();

        // getting sensor information of AR.Drone
        float pitch = ardrone.getPitch();
        float roll = ardrone.getRoll();
        float yaw = ardrone.getYaw();
        float altitude = ardrone.getAltitude();
        float[] velocity = ardrone.getVelocity();
        int battery = ardrone.getBatteryPercentage();
        /*
        String attitude = "pitch:" + pitch + "\nroll:" + roll + "\nyaw:" + yaw + "\naltitude:" + altitude;
        text(attitude, 20, 85);
        String vel = "vx:" + velocity[0] + "\nvy:" + velocity[1];
        text(vel, 20, 140);
        String bat = "battery:" + battery + " %";
        text(bat, 20, 170);

    } */


}
