package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.utils.Constants;

/**
 * Created by Minget on 2016/12/18.
 */

public class WorldRenderer implements Disposable {
    
    private SpriteBatch batch;
    private WorldController worldController;

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        worldController.camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        worldController.camera.position.set(0, 0, 0);
        worldController.camera.update();
    }

    public void render() {
        renderTestObjects();
    }

    private void renderTestObjects() {
        batch.setProjectionMatrix(worldController.camera.combined);
        batch.begin();
        batch.draw(worldController.background,-worldController.background.getWidth()/2,-worldController.background.getHeight()/2);
        //draw selected
        if(worldController.selectedGift!=-1){
            worldController.gifts[worldController.selectedGift].draw(batch);
        }
        worldController.startbutton.draw(batch);
        batch.end();
    }

    public void resize(int width, int height) {
        worldController.camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        worldController.camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
