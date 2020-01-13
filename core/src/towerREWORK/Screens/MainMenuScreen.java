package towerREWORK.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import towerREWORK.Assets;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Stages.MainMenuStage;


public class MainMenuScreen implements Screen {
    private Stage stage;
    private GameDataManager gdm;
    private Assets assets;

    public MainMenuScreen(GameDataManager gdm) {
        this.assets = gdm.assets();
        this.gdm = gdm;
    }

    @Override
    public void show() {
        stage = new MainMenuStage(gdm);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
