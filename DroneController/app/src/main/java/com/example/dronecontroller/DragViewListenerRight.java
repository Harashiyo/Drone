package com.example.dronecontroller;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.shigeodayo.ardrone.processing.*;
/**
 * Created by Shohei on 2015/10/30.
 */

public class DragViewListenerRight implements View.OnTouchListener {
    // ドラッグ対象のView
    private ImageView dragView;
    // ドラッグ中に移動量を取得するための変数
    private int mOldX; //前回のX軸
    private int mOldY; //前回のY軸
    private int mInitialX; //初期のx座標
    private int mInitialY; //初期のy座標
    private int mWidth; //ビューの横幅
    private int mHeight; //ビューの縦幅

    private int count=0;

    public DragViewListenerRight(ImageView dragView) {
        this.dragView = dragView;
        this.mInitialX = dragView.getLeft();
        this.mInitialY = dragView.getTop();
        this.mWidth= dragView.getWidth();
        this.mHeight=dragView.getHeight();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

            // タッチしている位置取得
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    // 今回イベントでのView移動先の位置
                    int[] coordinate = new int[2];// [0]:X座標 [1]:Y座標
                    coordinate[0] = dragView.getLeft() + (x - mOldX);
                    coordinate[1] = dragView.getTop() + (y - mOldY);
                    //ドラッグ可能範囲か判定
                    checkCoordinate(coordinate);
                    count++;
                    if(coordinate[1]-mInitialY<-45){
                        if(count%5==0) {
                        Log.d("MyApp", "上昇");
                        AsyncDroneconnection up = new AsyncDroneconnection();
                        up.execute(4);
                        }

                    }else if(coordinate[1]-mInitialY>45){
                        if(count%5==0) {
                            Log.d("MyApp", "下降");
                            AsyncDroneconnection down = new AsyncDroneconnection();
                            down.execute(5);
                        }
                    }
                    if(coordinate[0]-mInitialX>45){
                        if(count%5==0) {
                            Log.d("MyApp", "右回転");
                            AsyncDroneconnection spinright = new AsyncDroneconnection();
                            spinright.execute(10);
                        }
                    }else if(coordinate[0]-mInitialX<-45){
                        if(count%5==0) {
                            Log.d("MyApp", "左回転");
                            AsyncDroneconnection spinleft = new AsyncDroneconnection();
                            spinleft.execute(11);
                        }
                    }
                    // Viewを移動する
                    dragView.layout(coordinate[0], coordinate[1], coordinate[0] + mWidth, coordinate[1] + mHeight);
                    break;
                case MotionEvent.ACTION_UP: //指を離したとき
                    //Viewを元の位置に移動する
                    dragView.layout(mInitialX, mInitialY, mInitialX + mWidth, mInitialY + mHeight);
                    break;
            }

            // 今回のタッチ位置を保持
            mOldX = x;
            mOldY = y;
            //Log.d("MyApp", "x:" + String.valueOf(dragView.getLeft() - mInitialX) + " y:" + String.valueOf(mInitialY - dragView.getTop()));
            // イベント処理完了

        return true;
    }

    void checkCoordinate(int[] coordinate){// [0]:X座標 [1]:Y座標
        //移動できる範囲を制限
        if(coordinate[0] - mInitialX>90){
            coordinate[0]=mInitialX+90;
        }else if(coordinate[0] - mInitialX<-90){
            coordinate[0]=mInitialX-90;
        }
        if(mInitialY - coordinate[1]>90){
            coordinate[1]=mInitialY-90;
        }else if(mInitialY - coordinate[1] <-90){
            coordinate[1]=mInitialY+90;
        }
    }
}