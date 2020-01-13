package towerREWORK;

import com.badlogic.gdx.Game;

import towerREWORK.Database.ActionResolver;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Screens.MainMenuScreen;


public class TowerGame extends Game {
  private ActionResolver database;
  private Assets assets;
  private boolean assetsFirstLoadFinished = false;

  public TowerGame(ActionResolver db) {
    assets = new Assets();
    database = db;
  }

  @Override
  public void create() {
    assets.load();
  }

  @Override
  public void render() {
    super.render();
    if (assets.manager.update()) {
      if (!assetsFirstLoadFinished) {
        System.out.println("LoadFinished");
        setScreen(new MainMenuScreen(new GameDataManager(this, database, assets)));
        assetsFirstLoadFinished = true;
      }
    } else
      System.out.println("PROGRESS: " + assets.manager.getProgress() * 100 + "%");
  }

  @Override
  public void dispose() {
    assets.dispose();
  }

  public void restart() {
    assetsFirstLoadFinished = false;
  }
}

// battleStage work need TODO: ranks, clans