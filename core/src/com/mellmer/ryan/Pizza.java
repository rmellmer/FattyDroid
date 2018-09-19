package com.mellmer.ryan;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

	public class Pizza extends GameObject{
	
		public Pizza(){
				texture = new Texture("Pizza.png");
				sprite = new Sprite(texture);
				resetPizza();
				shapex = new float[3];
				shapey = new float[3];
				setShape();
				width = sprite.getWidth();
				height = sprite.getHeight();
		}

		public void setShape(){
			shapex[0] = x;
			shapey[0] = y;
			
			shapex[1] = x + width/2;
			shapey[1] = y + 63/2;
			
			shapex[2] = x + 116/2;
			shapey[2] = y + height/2;
		}
		
		public void render(SpriteBatch batch, ArrayList<Pizza> pizzas){
			if(x > (0 - sprite.getWidth()))
				batch.draw(sprite, (x-= 4), y, x, y, width/2, height/2, 1, 1, 0);
			else{
				resetPizza();
			}
			setShape();
		}
		
		public void draw(ShapeRenderer sr){
			sr.triangle(shapex[0], shapey[0], shapex[1], shapey[1], shapex[2], shapey[2]);
		}
		
		public void resetPizza(){	
			y = MathUtils.random(0, 800);
			x = (int)(480 + sprite.getWidth() + 20);
		}
	}

