package com.a13rain.unodir.spaceship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    Bitmap spaceship;
    int spaceship_x, spaceship_y;
    Bitmap leftKey, rightKey;
    int leftKey_x, leftKey_y;
    int rightKey_x, rightKey_y;
    int Width, Height;
    int button_width;
    Bitmap screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyView(this));

        // get scrren size
        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Width = display.getWidth();
        Height = display.getHeight();

        // create scaled bitmap

        spaceship = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship);
        int x = Width/8;
        int y = Height/11;
        spaceship = Bitmap.createScaledBitmap(spaceship, x, y, true);
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

        screen = BitmapFactory.decodeResource(getResources(), R.drawable.screen);
        screen = Bitmap.createScaledBitmap(screen, Width, Height, true);
    }

    public class MyView extends View {
        public MyView(Context context) {
            super(context);
            setBackgroundColor(Color.BLUE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);

            Paint p1 = new Paint();

            p1.setColor(Color.RED);
            p1.setTextSize(50);
            canvas.drawBitmap(screen, 0, 0, p1);

            canvas.drawBitmap(spaceship, spaceship_x, spaceship_y, p1);
            canvas.drawBitmap(leftKey, leftKey_x, leftKey_y, p1);
            canvas.drawBitmap(rightKey, rightKey_x, rightKey_y, p1);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int x = 0, y = 0;

            if (event.getAction() == MotionEvent.ACTION_DOWN
                    || event.getAction() == MotionEvent.ACTION_MOVE) {
                x = (int)event.getX();
                y = (int)event.getY();
            }

            if ((x > leftKey_x) && (x < leftKey_x + button_width)
                    && (y > leftKey_y) && (y < leftKey_y + button_width)) {
                spaceship_x -= 20;
            }

            if ((x > rightKey_x) && (x < rightKey_y + button_width)
                    && (y > rightKey_y) && (y < rightKey_y + button_width)) {
                spaceship_x += 20;
            }
             invalidate();

            //return super.onTouchEvent(event);
            return true;
        }
    }
}
