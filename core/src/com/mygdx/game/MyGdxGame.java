package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static java.lang.Math.abs;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Cat cat;
	Bat bat;
	boolean gameOver;
	Texture restartTexture;


	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		cat = new Cat();
		bat = new Bat();
		gameOver = false;
		restartTexture = new Texture("gameover.png");
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg.render(batch);
		cat.render(batch);
		bat.render(batch);
		if(!gameOver) {
			cat.render(batch);
		}else{
			batch.draw(restartTexture, 100, 380);
		}
		batch.end();
	}

	public void update(){
		bg.update();
		cat.update();
		bat.update();
		for (int i = 0; i < Bat.obs.length; i++) {
			if((abs(cat.position.x- Bat.obs[i].position.x)<200) &&(abs(cat.position.y- Bat.obs[i].position.y+91)<700)) {
				if(Bat.rad[i]==1) {
					cat.img = new Texture("catgreen0.png");
				}
				if(Bat.rad[i]==2)
				{
					cat.img = new Texture("catblue0.png");}
				if(Bat.rad[i]==0){
					cat.img = new Texture("catred0.png");}
				else{
					cat.img = new Texture("cat.png");}
			}
		}

		if(cat.position.y <0 || cat.position.y > 800){
			gameOver = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver){
			recreate();
		}
	}

	public void recreate(){
		cat.recreate();
		bat.recreate();
		gameOver = false;
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
