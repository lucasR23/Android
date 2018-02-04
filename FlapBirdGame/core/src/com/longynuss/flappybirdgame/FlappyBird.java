package com.longynuss.flappybirdgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;


public class FlappyBird extends ApplicationAdapter {
	private Random random;

	private OrthographicCamera camera;
	private Viewport viewport;

	private SpriteBatch batch;
	private BitmapFont font;
	private Texture gameover;
	private Texture background;
	private Texture upObject;
	private Texture downObject;
	private Texture[] bird;

	//the module of jump must always be greater than gravity module
	private static final float jump = -20;
	private static final int VEL_FLYING = 5;
	private static final int VEL_OBJECTS=5;
	private static final float gravity=0.8f;
	private static final int initSpace = 300;
	//prefer numbers dividable by 2;
	private static final int noiseRange=400;
	private final int bird_posX = 50;

	private float flyingAnimation =0;
	private float bird_posY;
	private float heightScreen;
	private float widthScreen;
	private float velocity;
	private float objectPosX;
	private float upObjectPosY;
	private float downObjectPosY;
	private float heightBird;
	private int noise;

	private boolean isPlaying;
	private int point;
	private boolean madePoint;


	//meshs
	private Circle birdMesh;
	private Rectangle upObjectMesh;
	private Rectangle downObjectMesh;
	private boolean lost;

	private static final float VIEW_WIDTH = 768;
	private static final float VIEW_HEIGHT = 1024;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.position.set(VIEW_WIDTH/2,VIEW_HEIGHT/2,0);
		viewport = new StretchViewport(VIEW_WIDTH,VIEW_HEIGHT,camera);

		random = new Random();

		isPlaying = false;
		madePoint = false;
		lost = false;
		point =0;

		heightScreen = VIEW_HEIGHT;
		widthScreen = VIEW_WIDTH;

		birdMesh = new Circle();
		upObjectMesh = new Rectangle();
		downObjectMesh = new Rectangle();

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(5);

		batch = new SpriteBatch();
		background = new Texture("fundo.png");
		gameover = new Texture("game_over.png");

		downObject = new Texture("cano_baixo.png");
		upObject = new Texture("cano_topo.png");

		objectPosX = widthScreen;
		upObjectPosY = heightScreen/2 + (initSpace/2);
		downObjectPosY = heightScreen/2 - downObject.getHeight() -(initSpace/2);

		bird = new Texture[3];
		bird[0]= new Texture("passaro1.png");
		bird[1]= new Texture("passaro2.png");
		bird[2]= new Texture("passaro3.png");
		heightBird = bird[0].getHeight();

		bird_posY = heightScreen/2;
		noise = 0;
		velocity =0;
	}

	@Override
	public void render () {
		//optimizing
		camera.update();
		//clean old frames
		Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT|GL20.GL_COLOR_BUFFER_BIT);

		//Start Game
		if (!isPlaying && Gdx.input.justTouched()) {
			isPlaying = true;
		}

		if (isPlaying) {
			//flying
			flyingAnimation += Gdx.graphics.getDeltaTime()*VEL_FLYING;
			if(flyingAnimation>2) flyingAnimation = 0;

			//falling putting up and down limits to screen
			velocity += gravity;
			if(bird_posY>0){
				bird_posY -= velocity;
				if (bird_posY+heightBird>heightScreen){
					bird_posY = heightScreen - heightBird;
					velocity =0;
					lost = true;
				}
			}else{
				lost = true;
			}

			if (!lost) {
				//objects moving
				if(objectPosX > -100){
					objectPosX -= VEL_OBJECTS;
				}else{
					objectPosX = widthScreen;
					madePoint = false;
					noise = random.nextInt(noiseRange) - noiseRange/2;
				}

				//jumping
				if (Gdx.input.justTouched()) {
					velocity = jump;
				}

				//taking points
				if(objectPosX < 50 && !madePoint) {
					point++;
					madePoint = true;
				}
			}else{
				if (Gdx.input.justTouched()) {
					isPlaying = false;
					madePoint = false;
					lost = false;
					point =0;
					velocity = 0;
					noise = 0;
					objectPosX = widthScreen;
					bird_posY = heightScreen/2;
				}
			}

			//meshs config
			birdMesh.set(bird_posX + bird[0].getWidth()/2 , bird_posY + bird[0].getHeight()/2,bird[0].getHeight() / 2);
			upObjectMesh.set(objectPosX, upObjectPosY+noise ,upObject.getWidth(),upObject.getHeight());
			downObjectMesh.set(objectPosX, downObjectPosY+noise ,downObject.getWidth(),downObject.getHeight());

			//colliding
			if(Intersector.overlaps(birdMesh,upObjectMesh) || Intersector.overlaps(birdMesh,downObjectMesh)){
				lost=true;
			}
		}

		//config camera
		batch.setProjectionMatrix(camera.combined);

		//drawing on screen
		batch.begin();
		batch.draw(background, 0, 0,widthScreen,heightScreen);
		batch.draw(upObject, objectPosX, upObjectPosY+noise);
		batch.draw(downObject, objectPosX, downObjectPosY+noise);
		batch.draw(bird[(int)flyingAnimation], 50, bird_posY);
		font.draw(batch,String.valueOf(point),widthScreen/2,heightScreen - 100);
		if (lost) {
			batch.draw(gameover,(widthScreen - gameover.getWidth())/2,heightScreen/2);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width,height);
	}
}
