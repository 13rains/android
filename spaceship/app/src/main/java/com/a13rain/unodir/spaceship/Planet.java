package com.a13rain.unodir.spaceship;

public class Planet {
    int x, y;
    int planetSpeed = 15;
    int dir;

    public Planet(int x, int y) {
        this.x = x;
        this.y = y;
      }

    public void move() {
        if ( dir == 0 )
            x -= planetSpeed;
        else
            x += planetSpeed;
    }
}
