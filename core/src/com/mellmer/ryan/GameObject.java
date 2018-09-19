package com.mellmer.ryan;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameObject {
	float x;
	float y;
	
	protected float[] shapex;
	protected float[] shapey;
	
	protected float width;
	protected float height;
	
	Texture texture;
	Sprite sprite;	
	
	
	public boolean intersects(GameObject other){
		float[] sx = other.shapex;
		float[] sy = other.shapey;
		
		for(int i = 0; i < sx.length; ++i){
			if(contains(sx[i], sy[i])) return true;
		}
		return false;
	}
	
	public boolean contains(float x, float y){
		boolean b = false;
		for(int i = 0, j = shapex.length - 1; i < shapex.length; j = i, ++i){
			if((shapey[i] > y) != (shapey[j] > y) && (x < (shapex[j] - shapex[i]) * (y - shapey[i]) / (shapey[j] - shapey[i]) + shapex[i])){
				b = !b;
			}
		}
		return b;		
	}
	
}
