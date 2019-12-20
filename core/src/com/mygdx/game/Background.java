package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.Vector;

public class Background {

    class DoublePicture {
        private Texture tx;
        private Vector2 pos;

        public DoublePicture(Vector2 pos) {
            tx = new Texture("fon.jpg");
            this.pos = pos;
        }
    }
    private int speed;
    private DoublePicture[] backs;

    public Background(){
        speed = 2;
        backs = new DoublePicture[2];
        backs[0] = new DoublePicture(new Vector2(0,0));
        backs[1] = new DoublePicture(new Vector2(1366,0));
    }

    public void render(SpriteBatch batch)
    {
        for(int i=0;i<backs.length;i++)
        {
            batch.draw(backs[i].tx,backs[i].pos.x,backs[i].pos.y);
        }
    }
    public void update(){
        for(int i=0;i<backs.length;i++) {
            backs[i].pos.x -= speed;
        }
        if(backs[0].pos.x<-1366){
            backs[0].pos.x=0;
            backs[1].pos.x=1366;
        }
    }
}
