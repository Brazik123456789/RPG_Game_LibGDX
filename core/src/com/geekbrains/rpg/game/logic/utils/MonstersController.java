package com.geekbrains.rpg.game.logic.utils;

import com.geekbrains.rpg.game.logic.GameController;
import com.geekbrains.rpg.game.logic.Monster;

import java.util.List;

public class MonstersController extends ObjectPool<Monster> {
    GameController gc;

    @Override
    public List<Monster> getActiveList() {
        return super.getActiveList();
    }

    @Override
    protected Monster newObject() {
        return new Monster(gc);
    }

    @Override
    public void free(int index) {
        super.free(index);
    }

    @Override
    public Monster getActiveElement() {
        return super.getActiveElement();
    }

    @Override
    public void checkPool() {
        super.checkPool();
    }

    public void update(float dt) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).update(dt);
        }
        checkPool();
    }

}
