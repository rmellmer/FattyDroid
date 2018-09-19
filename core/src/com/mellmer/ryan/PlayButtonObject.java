package com.mellmer.ryan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class PlayButtonObject extends GameObject{
	float shapeScale;
	int highScore;
	Vector3 cameraPoint;
	Sprite logo;
	Texture texture;
	
	public PlayButtonObject(){
		shapeScale = 2.5f;
		cameraPoint = new Vector3();
		this.texture = new Texture("play.png");
		this.sprite = new Sprite(this.texture);
		
		this.shapex = new float[4];
		this.shapey = new float[4];
		
		this.width = 420/shapeScale; 
		this.height = 420/shapeScale;
		
		texture = new Texture("Logo.png");
		logo = new Sprite(texture);
		
		highScore = Game.prefs.getInteger("highScore");
		setShape();
	}
	
	
	private void setShape(){
		this.x = (480/2) - (this.width/2);
		this.y = (800/2) - (this.height/2);
		
		this.shapex[0] = x + 60/shapeScale;
		this.shapey[0] = y + 60/shapeScale;
		
		this.shapex[1] = x + 60/shapeScale;
		this.shapey[1] = y + 360/shapeScale;
		
		this.shapex[2] = x + 360/shapeScale;
		this.shapey[2] = y + 360/shapeScale;
		
		this.shapex[3] = x + 360/shapeScale;
		this.shapey[3] = y + 60/shapeScale;
	}
	
	public void render(SpriteBatch batch, ShapeRenderer sr, BitmapFont defaultNormal){
		setShape();
		defaultNormal.setScale(2f);
		defaultNormal.draw(batch, "High Score: " + highScore, (480/2) - (defaultNormal.getBounds("High Score: " + highScore).width/2), y + (height) + defaultNormal.getCapHeight());
		defaultNormal.setScale(1f);
		defaultNormal.setScale(2f);
		defaultNormal.draw(batch, "Press Play", (480/2) - (defaultNormal.getBounds("Press Play").width/2), y + 5 + (height) + (2 * defaultNormal.getCapHeight()));
		batch.draw(this.sprite, this.x, this.y, 1, 1, width, height, 1, 1, 0);
		batch.draw(logo, 480/2 - logo.getWidth()/4, 740 - logo.getHeight()/2, 1,1,logo.getWidth()/2, logo.getHeight()/2,1,1,0);
	}
	
	public boolean checkPressed(){
		if (Gdx.input.justTouched() == true){
			cameraPoint.x = (int)Gdx.input.getX();
			cameraPoint.y = (int)Gdx.input.getY();
			cameraPoint.z = 0;
			
			cameraPoint = Game.viewport.unproject(cameraPoint);
			
			if(this.contains((float)cameraPoint.x, (float)cameraPoint.y)){
				return true;	
			}
		}
		return false;
	}
	

}
