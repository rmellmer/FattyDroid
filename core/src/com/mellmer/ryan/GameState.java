package com.mellmer.ryan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameState {
	int state;
	PlayObject play;
	MenuObject menu;
	static int MENU = 0, PLAY = 1234;
	public BitmapFont defaultNormal;
	
	public GameState(){
		defaultNormal = new BitmapFont(Gdx.files.internal("arial.fnt"), Gdx.files.internal("arial-15.png"), false);
		defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		defaultNormal.setColor(0,0,0,1);
		defaultNormal.setScale(6f);		
		state = MENU;
		menu = new MenuObject();
	}
	
	public void update(){
		int returnedState = 0;
		if(state == MENU){
			returnedState = menu.update();
			if(returnedState != state){
				state = returnedState;
				play = new PlayObject();
			}
		}
		else if(state == PLAY){
			returnedState = play.update();
			if(returnedState != state){
				state = returnedState;
				menu = new MenuObject();
			}
		}
	}
	
	public void render(SpriteBatch batch, ShapeRenderer sr){
		update();
		if(state == MENU){
			play = null;
			menu.render(batch, sr, defaultNormal);
		}
		else{
			play.render(batch, sr, defaultNormal);
			menu = null;
		}
	}
	
	public void dispose(){
		defaultNormal.dispose();
	}
}
