package com.geekbrains.rpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.geekbrains.rpg.game.logic.GameController;
import com.geekbrains.rpg.game.logic.WorldRenderer;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class GameScreen extends AbstractScreen {
    private GameController gc;
    private WorldRenderer worldRenderer;
    private Stage stage;
    private boolean active = false;

    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        gc = new GameController();
        worldRenderer = new WorldRenderer(gc, batch);
        createGui();
    }

    @Override
    public void render(float delta) {
        if(active == true) {
            gc.update(delta);
        }
        worldRenderer.render();
        stage.draw();
    }

    public void createGui() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());

        BitmapFont font14 = Assets.getInstance().getAssetManager().get("fonts/font24.ttf");
        TextButton.TextButtonStyle menuBtnStyle = new TextButton.TextButtonStyle(
                skin.getDrawable("shortButton"), null, null, font14);

        TextButton btnMenuGame = new TextButton("Menu", menuBtnStyle);
        btnMenuGame.setPosition(1090, 620);
        TextButton btnPauseGame = new TextButton("Pause", menuBtnStyle);
        btnPauseGame.setPosition(1190, 620);

        btnMenuGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
            }
        });

        btnPauseGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (active == true) {
                    active = false;
                } else {
                    active = true;
                }
            }
        });

        stage.addActor(btnMenuGame);
        stage.addActor(btnPauseGame);
        skin.dispose();
    }
}