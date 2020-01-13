package towerREWORK.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Stages.BattleStage.BattleStage;

public class BattleScreen implements Screen {

  private BattleStage stage;
  private GameDataManager gdm;

  public BattleScreen(GameDataManager gdm) {
    this.gdm = gdm;
  }

  @Override
  public void show() {
    stage = new BattleStage(gdm);
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render(float delta) {
    Gdx.gl20.glClearColor(1, 1, 1, 1);
    Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw();
    stage.act();
    stage.rounds();
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
