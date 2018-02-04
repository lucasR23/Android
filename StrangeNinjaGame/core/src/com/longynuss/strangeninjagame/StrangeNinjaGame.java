package com.longynuss.strangeninjagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class StrangeNinjaGame extends ApplicationAdapter {

	//todo: delete that
	private ShapeRenderer shapeRenderer;

	//generate noise to variate height
	private Random random;
	private int noise;
	//prefer numbers dividable by 2;
	private static final int noiseRange=400;

	//Camera of the game used to work with different resolutions
	private OrthographicCamera camera;
	private Viewport viewport;
	private float heightScreen;
	private float widthScreen;

	//
	private SpriteBatch batch;
	private Texture gameOver;
	private Texture background;

	private Texture upBuilding;
	private Texture downBuilding;
	private float buildingPosX;
	private float upBuildingPosY;
	private float downBuildingPosY;

	private Texture[] ninja;
	private final int ninja_posX = 50;
	private float ninja_posY;
	private float heightNinja;

	private BitmapFont pointShow;
	private int point;

	//the module of jump must always be greater than gravity module
	private static final float jump = -20;
	private static final int VEL_FLYING = 5;
	private static final int VEL_BUILDINGS =5;
	private static final float gravity = 0.8f;
	private static final int initSpace = 300;

	private float jumpingAnimation =0;
	private float runningAnimation =0;
	private float fallingAnimation =0;

	private float velocity;

	private boolean isPlaying;
	private boolean lost;
	private boolean madePoint;

	//meshs
	private Rectangle ninjaMesh;
	private Rectangle upBuildingMesh;
	private Rectangle downBuildingMesh;

	private static final float VIEW_WIDTH = 1024;
	private static final float VIEW_HEIGHT = 768;

	@Override
	public void create () {
		//todo: delete that
		shapeRenderer = new ShapeRenderer();

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

		ninjaMesh = new Rectangle();
		upBuildingMesh = new Rectangle();
		downBuildingMesh = new Rectangle();

//		pointShow = new BitmapFont();
//		pointShow.setColor(Color.WHITE);
//		pointShow.getData().setScale(5);
//
		batch = new SpriteBatch();
		//todo: make all the textures
//		background = new Texture("fundo.png");
//		gameOver = new Texture("game_over.png");
//
//		downBuilding = new Texture("cano_baixo.png");
//		upBuilding = new Texture("cano_topo.png");

//		buildingPosX = widthScreen;
//		upBuildingPosY = heightScreen/2 + (initSpace/2);
//		downBuildingPosY = heightScreen/2 - downBuilding.getHeight() -(initSpace/2);

		//todo: make all the textures
//		ninja = new Texture[3];
//		ninja[0]= new Texture("passaro1.png");
//		ninja[1]= new Texture("passaro2.png");
//		ninja[2]= new Texture("passaro3.png");
//		heightNinja = ninja[0].getHeight();

		ninja_posY = heightScreen/2;
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
			//meshs config
//			ninjaMesh.set(ninja_posX + ninja[0].getWidth()/2 , ninja_posY + ninja[0].getHeight()/2, ninja[0].getHeight() / 2);
//			upBuildingMesh.set(buildingPosX, upBuildingPosY +noise , upBuilding.getWidth(), upBuilding.getHeight());
//			downBuildingMesh.set(buildingPosX, downBuildingPosY +noise , downBuilding.getWidth(), downBuilding.getHeight());

			//colliding
			if(Intersector.overlaps(ninjaMesh, upBuildingMesh) || Intersector.overlaps(ninjaMesh, downBuildingMesh)){
				lost=true;
			}
		}

		//config camera
//		batch.setProjectionMatrix(camera.combined);

		//drawing on screen
		//todo: draw textures
//		batch.begin();
//		batch.draw(background, 0, 0,widthScreen,heightScreen);
//		batch.draw(upBuilding, buildingPosX, upBuildingPosY +noise);
//		batch.draw(downBuilding, buildingPosX, downBuildingPosY +noise);
//		batch.draw(ninja[(int) jumpingAnimation], 50, ninja_posY);
//		pointShow.draw(batch,String.valueOf(point),widthScreen/2,heightScreen - 100);
//		if (lost) {
//			batch.draw(gameOver,(widthScreen - gameOver.getWidth())/2,heightScreen/2);
//		}
//		batch.end();

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(300,100,30,90);
		shapeRenderer.rect(0,0,350,100,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE);
		shapeRenderer.rect(500,-300,200,500,Color.RED,Color.RED,Color.RED,Color.RED);
		shapeRenderer.end();
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
