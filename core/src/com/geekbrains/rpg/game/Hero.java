package com.geekbrains.rpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private float dX = 0;
    private float dY = 0;


    public Hero() {
        this.texture = new Texture("hero.png");
        this.position = new Vector2(100, 100);
        this.speed = 100.0f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 32, position.y - 32,32,32,64,64,1,1,0,0,0,64,64,false,false);
    }

    public void update(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * dt;
        }


        //  сравниваем координаты мышки с центром картинки
        if (Gdx.input.justTouched()){
            if (Gdx.input.getX() != position.x - 32) dX = Gdx.input.getX();
            if (720.0f - Gdx.input.getY() != position.y - 32) dY = 720.0f - Gdx.input.getY();
        }

        if ((dX != 0) || (dY !=0)) goToMouse(dt);

    }

    //  герой доходит до точки, но продолжает дергаться
    //  это из-за условий, в конце герой начнет просто менять свои координаты вперед-назад, и, соответственно, будет дергаться.
    //  Я не знаю, как прописать нормальное условие
    //  Одним из вариантов было, что если расстояние между координатой героя и координатой мышки меньше скорости speed, то автоматически присвоить координате героя эту координату мышки
    //  но получается непонятное мне дергание по всему полю


    public void goToMouse(float dt){

        if (dX - position.x > 0) {
                position.x += speed * dt;
        } else if (dX - position.x < 0) {
            position.x -= speed * dt;
        } else dX = 0;


        if (dY - position.y > 0) {
            position.y += speed * dt;
        } else if (dY - position.y < 0) {
            position.y -= speed * dt;
        } else dY = 0;
    }
}