package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.logic.utils.Poolable;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class Monster extends GameCharacter implements Poolable {
    private float attackTime;
    private boolean ok = true;
    private boolean active;
    private Vector2 konechnaya = new Vector2(MathUtils.random(0, 1280), MathUtils.random(0, 720));

    public Monster(GameController gc) {
        super(gc, 20, 100.0f);
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.changePosition(800.0f, 300.0f);
        this.active = true;
    }

    @Override
    public void onDeath() {
        deactivate();
    }

    @Override
    public void render(SpriteBatch batch, BitmapFont font) {
        batch.setColor(0.5f, 0.5f, 0.5f, 0.7f);
        batch.draw(texture, position.x - 30, position.y - 30, 30, 30, 60, 60, 1, 1, 0);
        batch.setColor(1, 1, 1, 1);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / hpMax), 12);
    }

    public void update(float dt) {
        super.update(dt);

        //  Если до конечной меньше нпрм 50 px, то конечная позиция меняется
        //  хотел прописать, если позиция = конечной if (position == konechnaya), однако монстр почему-то останавливается на одной точке и не двигается

        if (position.dst(konechnaya) < 50){
            konechnaya.set(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        } else {
            dst.set(konechnaya);
        }

        //  Проверка рядом ли герой
        if (position.dst(gc.getHero().getPosition()) < 300){
            dst.set(gc.getHero().getPosition());
        }

        if (this.position.dst(gc.getHero().getPosition()) < 40) {
            attackTime += dt;
            if(attackTime > 0.3f) {
                attackTime = 0.0f;
                gc.getHero().takeDamage(1);
            }
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }
}
