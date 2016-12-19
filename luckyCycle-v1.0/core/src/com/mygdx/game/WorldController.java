package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.utils.Constants;

/**
 * Created by Mignet on 2016/12/18.
 */

public class WorldController {
    private static final String TAG = WorldController.class.getName();

    public Sprite[] gifts;
    public Texture background;
    public Texture startbutton;
    public int selectedGift;

    public WorldController() {
        init();
    }

    private void init() {
        initTestObjects();
    }

    private void initTestObjects() {
        // Create new array for 5 sprites
        gifts = new Sprite[7];
        // Create empty POT-sized Pixmap with 8 bit RGBA pixel data
        background = new Texture(Gdx.files.internal("bg.jpg"));
        startbutton = new Texture(Gdx.files.internal("start_btn.png"));
//        Pixmap pixmap = createProceduralPixmap(width, height);
        // Create new sprites using the just created texture
        for (int i = 0; i < gifts.length; i++) {
            // Create a new texture from pixmap data
            Texture texture = new Texture(Gdx.files.internal("t-"+(i+1)+".png"));
            Sprite spr = new Sprite(texture);
            // Set origin to sprite's center
            spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
            spr.setPosition(-Constants.VIEWPORT_WIDTH/2-3,-Constants.VIEWPORT_HEIGHT/2+206);
            // Put new sprite into array
            gifts[i] = spr;
        }
        // Set first sprite as selected one
        selectedGift = -1;
    }

    private Pixmap createProceduralPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        // Fill square with red color at 50% opacity
        pixmap.setColor(1, 0, 0, 0.5f);
        pixmap.fill();
        // Draw a yellow-colored X shape on square
        pixmap.setColor(1, 1, 0, 1);
        pixmap.drawLine(0, 0, width, height);
        pixmap.drawLine(width, 0, 0, height);
        // Draw a cyan-colored border around square
        pixmap.setColor(0, 1, 1, 1);
        pixmap.drawRectangle(0, 0, width, height);
        return pixmap;
    }

    public void update(float deltaTime) {
        updateTestObjects(deltaTime);
    }
    // 1s
    float time = 0;
    float speed = 2;
    private void updateTestObjects(float deltaTime) {
        // Get current rotation from selected sprite
//        float rotation = gifts[selectedGift].getRotation();
//        // Rotate sprite by 90 degrees per second
//        rotation += 90 * deltaTime;
//        // Wrap around at 360 degrees
//        rotation %= 360;
//        // Set new rotation value to selected sprite
//        gifts[selectedGift].setRotation(rotation);
        time += deltaTime*speed;
        selectedGift = MathUtils.floor(time%7);
    }

}
