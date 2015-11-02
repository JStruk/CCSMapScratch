package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

//http://www.gamefromscratch.com/post/2014/04/16/LibGDX-Tutorial-11-Tiled-Maps-Part-1-Simple-Orthogonal-Maps.aspx
//^^shows how to render a map, very basic but good example
//https://gist.github.com/spilth/5457184
//^^shows a few lines of code for getting the map height and width the easiest way

public class CCSMap extends ApplicationAdapter {
	TiledMap tiledMap;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	int nTile = 32;
	TiledMapTileLayer tiledMapTileLayer;
	int nMapWidth, nMapHeight, nTileSize;

	@Override
	public void create () {
		tiledMap = new TmxMapLoader().load("bombermap.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		tiledMapTileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		nTileSize = (int) tiledMapTileLayer.getTileWidth();
		nMapHeight = tiledMapTileLayer.getHeight() * nTile;
		nMapWidth = tiledMapTileLayer.getWidth() * nTile;
		camera = new OrthographicCamera();
		camera.setToOrtho(true, nMapWidth, nMapHeight);
		//LibGdx coordinate systems origin is bottom left, whereas norm is upper left
		//Set cam ortho to true and flip all textureregions so origin is upper left.
		camera.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		camera.update();
	}
}
