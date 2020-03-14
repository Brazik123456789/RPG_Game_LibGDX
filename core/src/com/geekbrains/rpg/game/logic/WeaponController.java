package com.geekbrains.rpg.game.logic;

import com.geekbrains.rpg.game.logic.utils.ObjectPool;

public class WeaponController extends ObjectPool<Weapon> {
    private GameController gc;

    @Override
    protected Weapon newObject() {
        return new Weapon(gc);
    }

    public WeaponController(GameController gct) {
        this.gc = gc;
    }

    public void update(float dt) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).update(dt);
        }
        checkPool();
    }
}
