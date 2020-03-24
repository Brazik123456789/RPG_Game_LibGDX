package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.logic.utils.Consumable;
import com.geekbrains.rpg.game.logic.utils.MapElement;
import com.geekbrains.rpg.game.logic.utils.Poolable;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class msg implements Consumable, Poolable, MapElement {

    private GameController gc;
    private  BitmapFont font12;
    private Vector2 position;
    private Vector2 velocity;
    private float time;
    private boolean active;
    private String msg;

    public Vector2 getPosition() {
        return position;
    }

    public msg(GameController gc) {
        this.gc = gc;
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.font12 = Assets.getInstance().getAssetManager().get("fonts/font32.ttf");
    }

    @Override
    public void consume(GameCharacter gameCharacter) {
        active = false;
    }

    public void setup(float x, float y, float damage) {
        position.set(x, y);
        velocity.set(MathUtils.random(-60.0f, 60.0f), MathUtils.random(-60.0f, 60.0f));
        time = 0.0f;
        msg = "-" + damage;
        active = true;
    }

    public void update(float dt) {
        time += dt;
        position.mulAdd(velocity, dt);
        if (time > 4.0f) {
            active = false;
        }
    }

    @Override
    public void render(SpriteBatch batch, BitmapFont font) {
        font12.draw(batch, msg, position.x, position.y);
    }

    @Override
    public int getCellX() {
        return (int) (position.x / Map.CELL_WIDTH);
    }

    @Override
    public int getCellY() {
        return (int) (position.y / Map.CELL_HEIGHT);
    }

    @Override
    public float getY() {
        return position.y;
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
