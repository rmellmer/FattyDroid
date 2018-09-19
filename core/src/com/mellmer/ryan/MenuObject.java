package com.mellmer.ryan;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MenuObject {
	static int MENU = 0, PLAY = 1234;
	PlayButtonObject playButton;
	
	public MenuObject(){
		playButton = new PlayButtonObject();
	}

	public void render(SpriteBatch batch, ShapeRenderer sr, BitmapFont defaultNormal) {
		playButton.render(batch, sr, defaultNormal);
	}

	public int update() {
		if(playButton.checkPressed() == true){
			return PLAY;
		}
		return MENU;
	}
}
