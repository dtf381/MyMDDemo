package com.sunjiajia.alldemo.snowflake;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by mk on 2016/11/30.
 */

public class Flake {

    float x,y;
    float rotation;
    float speed;
    float rotationSpeed;
    int width,height;
    Bitmap bitmap;

    static HashMap<Integer , Bitmap> bitmapMap = new HashMap<>();

    static Flake createFlake(float xRange,Bitmap originalBitmap){
        Flake flake = new Flake();
        flake.width = (int)((float) Math.random() *50) +5;
        Log.e("Flake","width1 = "+originalBitmap.getWidth()+"  height1 = "+originalBitmap.getHeight());
        float hwRatio = originalBitmap.getHeight()/originalBitmap.getWidth();
        flake.height = (int) (flake.width * hwRatio);

        flake.x = (float) (Math.random() * (xRange - flake.width));
        flake.y = 0 - (flake.height + (float) Math.random() * flake.height);

        flake.speed = (float) (50 + Math.random()*150);


        flake.rotation = (float) (Math.random() *180-90);
        flake.rotationSpeed = (float) (Math.random()*90-45);


        flake.bitmap = bitmapMap.get(flake.width);
        if (flake.bitmap == null){
            Log.e("Flake","width = "+flake.width+"  height = "+flake.height);
            flake.bitmap = Bitmap.createScaledBitmap(originalBitmap,flake.width,flake.height,true);
        }
        return  flake;
    }

}
