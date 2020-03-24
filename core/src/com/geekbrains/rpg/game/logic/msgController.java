package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.geekbrains.rpg.game.logic.utils.ObjectPool;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class msgController extends ObjectPool<msg> {
    private GameController gc;


    public msgController(GameController gc) {
        this.gc = gc;
    }

    public void setup(float x, float y, float damage) {
        getActiveElement().setup(x,y,damage);
    }

    public void update(float dt) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).update(dt);
        }
        checkPool();
    }

    @Override
    protected msg newObject() {
        return new msg(gc);
    }
}