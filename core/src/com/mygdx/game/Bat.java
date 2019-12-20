package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.w3c.dom.Text;

import java.util.Random;

import static java.lang.Math.abs;

public class Bat {
    class BatLot {
        Vector2 position;
        float speed;
        int offset;
        Rectangle Space;

        public BatLot(Vector2 pos) {
            speed = 10;
            position = pos;
            offset = new Random().nextInt(450);
            Space = new Rectangle(position.x, position.y, position.x + 204, position.y + 91);
        }

        public void update() {
            position.x -= speed;
            if (position.x < -204) {
                position.x = 1366;
                offset = new Random().nextInt(500);
            }
            Space.x = position.x;
        }
    }

    int n = 3;
    static int[] rad;
    static BatLot[] obs;
    Texture front;
    static Texture[] colo;
    int betweenDistance;

    public Bat() {
        rad  = new int[n];
        for(int k=0;k<n;k++)
        {
            rad[k] = new Random().nextInt(3) ;
        }

        front = new Texture("batfront.png");
        colo  = new Texture[4];
        colo[0] = new Texture("batbackred.png");
        colo[1] = new Texture("batbackgreen.png");
        colo[2] = new Texture("batbackblue.png");
        colo[3] = new Texture("batback.png");
        obs = new BatLot[n];
        int startPosX = 1000;
        for (int i = 0; i < obs.length; i++) {
            obs[i] = new BatLot(new Vector2(startPosX, 30));
            startPosX += 400;
        }
    }

    public void render(SpriteBatch batch) {
        Random random = new Random();
        for (int i = 0; i < obs.length; i++) {
            batch.draw(front, obs[i].position.x, obs[i].position.y + obs[i].offset%200);
            if (i==n-1) {
                batch.draw(colo[3], obs[i].position.x, obs[i].position.y + obs[i].offset%200);
            }
            else {
                batch.draw(colo[rad[i]], obs[i].position.x, obs[i].position.y + obs[i].offset%200);
            }
        }
    }

    public void update(){
        for (int i = 0; i < obs.length; i++) {
            obs[i].update();
        }
    }

    public void recreate(){
        int startPosX = 400;
        for (int i = 0; i < obs.length; i++) {
            obs[i] = new BatLot(new Vector2(startPosX,0));
            startPosX += 220;
        }
    }
}
