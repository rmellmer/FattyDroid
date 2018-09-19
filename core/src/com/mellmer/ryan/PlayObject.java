package com.mellmer.ryan;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayObject {
	int pizzaCounter;
	ArrayList<Pizza> pizzas;
	Player player;
	float score;
	static int MENU = 0, PLAY = 1234;
	final static int DEAD = 4, LARGE = 3, MEDIUM = 2, SMALL = 1, SMALLEST = 0;
	
	
	public PlayObject(){
		pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza());
		player = new Player();
		pizzaCounter = 0;
		score = 0f;		
	}
	
	
	public void checkTouched(){
		if (Gdx.input.justTouched() == true){
			player.jump();
		}
	}


	public int update() {
		if(player.state == DEAD && player.animate == false){
			if(Game.prefs.getInteger("highScore") < score){
				Game.prefs.putInteger("highScore", (int)score);
				Game.prefs.flush();
			}
			return MENU;
		}
		else return PLAY;
	}

	public void computeScore(){
		if(player.state != DEAD){
			score += Gdx.graphics.getDeltaTime();		
		}
	}
	
	public void checkCollision(){
		if(player.state != DEAD){
			for(int i = 0; i < pizzas.size(); ++i){
				Pizza a = pizzas.get(i);
				if(a.intersects(player)){
					a.resetPizza();
					player.increaseState();
				}
			}
		}
	}
	
	public void addPizzas(){
		if(pizzaCounter > 1000 && pizzas.size() < 4){
			pizzas.add(new Pizza());
			pizzaCounter = 0;
		}
		else if(pizzaCounter > 1000) pizzaCounter = 0;
	}
	
	public void render(SpriteBatch batch, ShapeRenderer sr, BitmapFont defaultNormal) {
		++pizzaCounter;
		checkCollision();
		checkTouched();
		computeScore();
		addPizzas();
			
		for(int i = 0; i < pizzas.size(); ++i){
			pizzas.get(i).render(batch, pizzas);
		}
		defaultNormal.setScale(2f);
		defaultNormal.draw(batch, "Lives Left: " + player.checkLives(), 480 - defaultNormal.getBounds("Lives Left:  " + player.checkLives()).width, 800 - defaultNormal.getCapHeight());
		defaultNormal.draw(batch, "Score: " + (int)score, 20, 800 - defaultNormal.getCapHeight());
		player.render(batch);		
	}
}
