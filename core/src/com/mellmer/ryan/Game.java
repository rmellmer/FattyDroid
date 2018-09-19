package com.mellmer.ryan;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Game extends ApplicationAdapter{
	public static Preferences prefs;
	GameState gameState;
	SpriteBatch batch;
	ShapeRenderer sr;
	public static StretchViewport viewport;
	OrthographicCamera camera;
	Texture texture;
	Sprite background;
	float backgroundWidth;
	float x1 = 0, x2 = 0;
	
	
	@Override
	public void create () {
		prefs = Gdx.app.getPreferences("FattyBird");
		gameState = new GameState();
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		viewport = new StretchViewport(480, 800, camera);
		
		texture = new Texture(Gdx.files.internal("city_background1.png"));
		background = new Sprite(texture);
		backgroundWidth = background.getWidth();
		
		x2 = backgroundWidth;
		
		if(!prefs.contains("highScore")){
			prefs.putInteger("highScore", 0);
			prefs.flush();
		}
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		sr.setProjectionMatrix(camera.combined);
		batch.begin();
		sr.begin(ShapeType.Line);
		sr.setColor(1, 0, 0, 1);
		
		if(x1 < 0 && x2 < 0){
			if(x1 < x2) x1 = x2 + backgroundWidth;
			else x2 = x1 + backgroundWidth;
		}
		
		
		if(gameState.state == GameState.MENU){
			batch.draw(background, x1 -= 1, 0, 0, 0, backgroundWidth, 800, 1, 1, 0);
			batch.draw(background, x2 -= 1, 0, 0, 0, backgroundWidth, 800, 1, 1, 0);
		}	
		else{
			batch.draw(background, x1 -= 2.5, 0, 0, 0, backgroundWidth, 800, 1, 1, 0);
			batch.draw(background, x2 -= 2.5, 0, 0, 0, backgroundWidth, 800, 1, 1, 0);
		}
		
		gameState.render(batch, sr);
		batch.end();
		sr.end();		
	}


	@Override
	public void resize(int width, int height){
		viewport.update(width, height, true);		
		camera.update();
	}
	
	@Override
	public void dispose(){
		gameState.dispose();
	}
}
