package com.mellmer.ryan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player extends GameObject{
	float stateTime;
	Texture explosionTexture;
	TextureRegion currentFrame;
	TextureRegion [] explosionFrames;
	Animation explosion;
	int gravity;
	float gravityIntensity;
	int state;
	final static int DEAD = 4, LARGE = 3, MEDIUM = 2, SMALL = 1, SMALLEST = 0;
	final static float LARGE_SCALE = 1.5f, MEDIUM_SCALE = 2f, SMALL_SCALE = 3f, SMALLEST_SCALE = 4f;
	float shapeScale;
	boolean animate;
	int totalLives;
	
	public Player(){
		texture = new Texture("ic_launcher-web.png");
		sprite = new Sprite(texture);
		
		x = 0;
		y = 0;
		
		shapex = new float[9];
		shapey = new float[9];
		
		setShape();
		
		state = SMALLEST;

		width = sprite.getWidth();
		height = sprite.getHeight();
		gravityIntensity = 1;
		
		explosionTexture = new Texture(Gdx.files.internal("explosion-sprite-sheet-i0.png"));
	    TextureRegion[][] tmp = TextureRegion.split(explosionTexture, explosionTexture.getWidth()/5, explosionTexture.getHeight()/5);
	    explosionFrames = new TextureRegion[25];
	    int index = 0;
	        for (int i = 0; i < 5; i++) {
	            for (int j = 0; j < 5; j++) {
	                explosionFrames[index++] = tmp[i][j];
	            }
	        }
	    explosion = new Animation(0.025f, explosionFrames);
	    stateTime = 0f;
	    explosion.setPlayMode(Animation.PlayMode.NORMAL);
	    animate = true;
	    totalLives = 4;
	}
	
	public void setShape(){
		if(state == DEAD){
			shapex = null;
			shapey = null;
			return;
		}
		
		else if(state == LARGE) shapeScale = LARGE_SCALE;
		else if(state == MEDIUM) shapeScale = MEDIUM_SCALE;
		else if(state == SMALL) shapeScale = SMALL_SCALE;
		else if(state == SMALLEST) shapeScale = SMALLEST_SCALE;
		
			shapeScale = shapeScale * 2f;
		
			shapex[0] = x + 170/shapeScale;
			shapey[0] = y + 44/shapeScale;
			
			shapex[1] = x + 77/shapeScale;
			shapey[1] = y + 165/shapeScale;
			
			shapex[2] = x + 77/shapeScale;
			shapey[2] = y + 329/shapeScale;
			
			shapex[3] = x + 194/shapeScale;
			shapey[3] = y + 443/shapeScale;
			
			shapex[4] = x + 256/shapeScale;
			shapey[4] = y + 467/shapeScale;
			
			shapex[5] = x + 320/shapeScale;
			shapey[5] = y + 443/shapeScale;
			
			shapex[6] = x + 443/shapeScale;
			shapey[6] = y + 329/shapeScale;
			
			shapex[7] = x + 437/shapeScale;
			shapey[7] = y + 165/shapeScale;
			
			shapex[8] = x + 331/shapeScale;
			shapey[8] = y + 44/shapeScale;
		}
	
	public void increaseState(){
		if(state == LARGE){
			state = DEAD;
		}
		else if(state == SMALLEST){
			state = SMALL;
		}
		else if(state == SMALL){
			state = MEDIUM;
		}
		else if(state == MEDIUM){
			state = LARGE;
		}
		--totalLives;
	}
	
	public void gravity(){
		if(state != DEAD){
			if(shapey[0] <= 0 - (height/shapeScale)/3){
				gravity = -20;
			}
			else if(shapey[5] <= Gdx.graphics.getHeight() + (height/shapeScale)/3){
				if(state == LARGE) gravityIntensity = 3;
				else if(state == MEDIUM) gravityIntensity = 2.5f;
				else if(state == SMALL) gravityIntensity = 2;
				else if(state == SMALLEST) gravityIntensity = 1;
			}
			else{
				gravityIntensity = 2;
			}
			y -= gravity;
		}		
	}
	
	public void jump(){
		if(state != DEAD){
			if((int)shapey[5] <= 800 + (height/shapeScale)/3){
				gravity = -30;	
			}
		}
	}
	
	public int checkLives(){
		return totalLives;
	}
	
	public boolean animationOver(){
		return animate;
	}
	
	public void render(SpriteBatch batch){
		gravity();
		gravity += gravityIntensity;
		
		setShape();
		
		if(this.state == DEAD && this.animate == true){	
			currentFrame = explosion.getKeyFrame(stateTime, false);
			stateTime += Gdx.graphics.getDeltaTime();
			gravityIntensity = 0;             
			batch.draw(currentFrame, x - (width/2), y - (width/2), width/2f, height/2f,  width, height, 1, 1, 0);
			if(explosion.isAnimationFinished(stateTime)) animate = false;
		}
		else batch.draw(sprite, x, y, width/2f, height/2f, width/shapeScale, height/shapeScale, 1, 1, 0);
	}
	
	public void draw(ShapeRenderer sr){
		for(int i = 0, j = shapex.length - 1; i < shapex.length; j = i, ++i){
			sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
		}
	}
}
