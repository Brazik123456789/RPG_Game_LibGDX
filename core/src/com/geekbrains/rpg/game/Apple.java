package com.geekbrains.rpg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Apple {

    private TextureRegion textureApple;
    private Vector2 position;

    public Apple(TextureAtlas atlas) {
        this.textureApple = atlas.findRegion("apple");
        this.position = new Vector2((int) (Math.random()*1130), (int) (720 - Math.random()*720));
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureApple, position.x - 150, position.y - 150, 150, 150, 300, 300, 0.2f, 0.2f, 0);

    }

    public Vector2 getPosition() {
        return position;
    }

    public void paintApple(){
        this.position = new Vector2((int) (Math.random()*1130), (int) (720 - Math.random()*570));
    }
}
