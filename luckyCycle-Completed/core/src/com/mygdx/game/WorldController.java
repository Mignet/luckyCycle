package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.utils.Constants;

/**
 * Created by Mignet on 2016/12/18.
 */

public class WorldController extends InputAdapter {
    private static final String TAG = WorldController.class.getName();
    public OrthographicCamera camera;
    public Sprite[] gifts;
    public Texture background;
    public Sprite startbutton;
    public int selectedGift;

    public WorldController() {
        init();
    }

    private void init() {
        initTestObjects();
        Gdx.input.setInputProcessor(this);
    }

    private void initTestObjects() {
        // Create new array for 7 sprites
        gifts = new Sprite[7];
        background = new Texture(Gdx.files.internal("bg.jpg"));
        startbutton = new Sprite(new Texture(Gdx.files.internal("start_btn.png")));
        startbutton.setOrigin(startbutton.getWidth() / 2.0f, startbutton.getHeight() / 2.0f);
        startbutton.setPosition(-startbutton.getWidth() / 2 + 6, -startbutton.getHeight() / 2 + 56);
        // Create new sprites using the just created texture
        for (int i = 0; i < gifts.length; i++) {
            // Create a new texture from pixmap data
            Texture texture = new Texture(Gdx.files.internal("t-" + (i + 1) + ".png"));
            Sprite spr = new Sprite(texture);
            // Set origin to sprite's center
            spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
            spr.setPosition(-Constants.VIEWPORT_WIDTH / 2 - 3, -Constants.VIEWPORT_HEIGHT / 2 + 206);
            // Put new sprite into array
            gifts[i] = spr;
        }
        // Set first sprite as selected one
        selectedGift = -1;
    }

    public void update(float deltaTime) {
        updateTestObjects(deltaTime);
    }

    // 1s
    float time = 0;
    float speed = 0;
    int selected = -1;
    boolean clicked = false;

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 input = new Vector3(screenX, screenY, 0);
        camera.unproject(input);
        if (startbutton.getBoundingRectangle().contains(input.x, input.y) && !clicked) {
            //start button
            Gdx.app.debug(TAG, "you click start button!");
            clicked = true;
            start();
        }
        return true;
    }

    private void start() {
        speed = 6;
        time = 0;
        int r = MathUtils.random(100);
        if (r < 95) {
            selected = 4;
        } else if (r >= 95 && r < 97) {
            selected = 6;
        } else if (r >= 97 && r < 98) {
            selected = 5;
        } else if (r >= 98 && r < 99) {
            selected = 3;
        } else if (r >= 99 && r < 100) {
            selected = 1;
        }
    }

    private void updateTestObjects(float deltaTime) {
        if (time > 6 * speed) {
            speed = 2;
        }
        if (speed != 0 && clicked) {
            time += deltaTime * speed;
            selectedGift = MathUtils.floor(time % 7);
        }
        if (speed == 2 && selected != -1 && selectedGift == selected) {
            speed = 0;
            selected = -1;
            clicked = false;
        }
    }

}
