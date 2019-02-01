package com.a13rain.unodir.spaceship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {

    Bitmap spaceship;
    int spaceship_x, spaceship_y;
    int spaceshipWidth;
    Bitmap leftKey, rightKey;
    int leftKey_x, leftKey_y;
    int rightKey_x, rightKey_y;
    int Width, Height;

    int button_width;

    Bitmap missileButton;
    int missileButton_x, missileButton_y;
    int missileWidth;
    int missile_middle;
    Bitmap missile;
    Bitmap planetimg;

    int count;
    int score;
    ArrayList<MyMissile> myM;
    ArrayList<Planet> planet;

    Bitmap screen;

    MyThread mThread;
    SurfaceHolder mHolder;
    Context mContext;

    public MySurface(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        mThread = new MyThread(holder, context);
        InitApp();
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = 0, y = 0;

        if (event.getAction() == MotionEvent.ACTION_DOWN
                || event.getAction() == MotionEvent.ACTION_MOVE) {
            x = (int)event.getX();
            y = (int)event.getY();
        }


        if ((x > missileButton_x) && (x < missileButton_x + button_width)
                && (y > missileButton_y) && (y < missileButton_y + button_width)
        ) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //if(myM.size()<1){
                myM.add(new MyMissile(spaceship_x + spaceshipWidth / 2 - missileWidth / 2, spaceship_y));
                //}
            }
            return true;
        } else {
            if (x < spaceship_x + spaceshipWidth / 2) {
                spaceship_x -= 20;
            }

            if (x > spaceship_x + spaceshipWidth / 2) {
                spaceship_x += 20;
            }
        }
            /*
            if ((x > leftKey_x) && (x < leftKey_x + button_width)
                    && (y > leftKey_y) && (y < leftKey_y + button_width)) {
                spaceship_x -= 20;
            }

            if ((x > rightKey_x) && (x < rightKey_y + button_width)
                    && (y > rightKey_y) && (y < rightKey_y + button_width)) {
                spaceship_x += 20;
            }
            */

        //return super.onTouchEvent(event);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    class MyThread extends Thread {
        public MyThread(SurfaceHolder holder, Context context) {
            mHolder = holder;
            mContext = context;
        }

        @Override
        public void run() {
            Canvas canvas = null;

            while (true) {
                canvas = mHolder.lockCanvas();
                try {
                    synchronized (mHolder) {
                        drawScreen(canvas);
                    }
                }finally {
                    if (canvas != null) {
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }


        public void drawScreen(Canvas canvas) {
            Random r1 = new Random();
            int x = r1.nextInt(Width);

            if (planet.size() < 5) {
                planet.add(new Planet(x, 400));
            }

            Paint p1 = new Paint();

            p1.setColor(Color.RED);
            p1.setTextSize(50);
            canvas.drawBitmap(screen, 0, 0, p1);

            canvas.drawText(Integer.toString(count), 0, 300, p1);
            canvas.drawText("점수: " + Integer.toString(score), 0, 200, p1);

            canvas.drawBitmap(spaceship, spaceship_x, spaceship_y, p1);
            canvas.drawBitmap(leftKey, leftKey_x, leftKey_y, p1);
            canvas.drawBitmap(rightKey, rightKey_x, rightKey_y, p1);

            canvas.drawBitmap(missileButton, missileButton_x, missileButton_y, p1);

            for (MyMissile tmp : myM) {
                canvas.drawBitmap(missile, tmp.x, tmp.y, p1);
            }
            for (Planet tmp : planet) {
                canvas.drawBitmap(planetimg, tmp.x, tmp.y, p1);
            }

            moveMissile();
            movePlanet();
            checkCollision();

            count++;

        }

        public void moveMissile() {
            for (int i = myM.size() - 1; i >= 0; i--) {
                myM.get(i).move();
            }

            for (int i = myM.size() - 1; i >= 0; i--) {
                if( myM.get(i).y < 0 ) myM.remove(i);
            }
        }

        public void movePlanet() {
            for (int i = planet.size() - 1; i >= 0; i--) {
                planet.get(i).move();
            }

            for (int i = planet.size() - 1; i >= 0; i--) {
                if( planet.get(i).x < 0 ) planet.remove(i);
            }
        }

        public void checkCollision() {
            for (int i = planet.size() - 1; i >= 0; i--) {
                for (int j = myM.size() - 1; j >= 0; j--) {
                    if ( myM.get(j).x+missile_middle > planet.get(i).x
                            && myM.get(j).x+missile_middle < planet.get(i).x+button_width
                            && myM.get(j).y > planet.get(i).y
                            && myM.get(j).y < planet.get(i).y+button_width)
                    {
                        planet.remove(i);
                        myM.get(j).y=-430;
                        score += 10;
                    }
                }
            }
        }
    }

    private void InitApp() {
        // get scrren size
        Display display = ((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Width = display.getWidth();
        Height = display.getHeight();

        myM = new ArrayList<MyMissile>();
        planet = new ArrayList<Planet>();

        // create scaled bitmap
        spaceship = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship);
        int x = Width/8;
        int y = Height/11;
        spaceship = Bitmap.createScaledBitmap(spaceship, x, y, true);
        spaceshipWidth = spaceship.getWidth();

        spaceship_x = Width/9;
        spaceship_y = Height*6/9;

        leftKey = BitmapFactory.decodeResource(getResources(), R.drawable.leftkey);
        leftKey_x = Width*5/9;
        leftKey_y = Height*7/9;
        button_width = Width/6;
        leftKey = Bitmap.createScaledBitmap(leftKey, button_width, button_width, true);

        rightKey = BitmapFactory.decodeResource(getResources(), R.drawable.rightkey);
        rightKey_x = Width*7/9;
        rightKey_y = Height*7/9;

        rightKey = Bitmap.createScaledBitmap(rightKey, button_width, button_width, true);

        missileButton = BitmapFactory.decodeResource(getResources(), R.drawable.missilebutton);
        missileButton = Bitmap.createScaledBitmap(missileButton, button_width, button_width, true);
        missileButton_x = Width / 11;
        missileButton_y = Height*7/9;

        missile = BitmapFactory.decodeResource(getResources(), R.drawable.missile0);
        missile = Bitmap.createScaledBitmap(missile, button_width/4, button_width/4, true);
        missileWidth = missile.getWidth();
        missile_middle = missileWidth/2;

        planetimg = BitmapFactory.decodeResource(getResources(), R.drawable.planet);
        planetimg = Bitmap.createScaledBitmap(planetimg, button_width, button_width, true);

        screen = BitmapFactory.decodeResource(getResources(), R.drawable.screen);
        screen = Bitmap.createScaledBitmap(screen, Width, Height, true);
    }

}
