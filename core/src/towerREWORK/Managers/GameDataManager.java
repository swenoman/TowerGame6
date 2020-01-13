package towerREWORK.Managers;

import towerREWORK.Assets;
import towerREWORK.Database.ActionResolver;
import towerREWORK.Quests.QuestsManager;
import towerREWORK.TowerGame;

public class GameDataManager {
  private Assets assets;
  private TowerGame game;
  private ActionResolver database;
  private HeroesManager hm;
  private BagManager bm;
  private CoinsManager cm;
  private StageManager sm;
  private StoreManager store;
  private QuestsManager qm;
  private int isFirstTry;

  public GameDataManager(TowerGame game, ActionResolver database, Assets assets) {
    this.game = game;
    this.database = database;
    isFirstTry = database.isFirstTry();
    this.assets = assets;
    hm = new HeroesManager(this);
    bm = new BagManager(this);
    cm = new CoinsManager(this);
    sm = new StageManager(this);
    store = new StoreManager(this);
    qm = new QuestsManager();
  }

  public Assets assets() {
    return assets;
  }

  public TowerGame game() {
    return game;
  }

  public ActionResolver database() {
    return database;
  }

  public BagManager bm() {
    return bm;
  }

  public HeroesManager hm() {
    return hm;
  }

  public CoinsManager cm() {
    return cm;
  }

  public StageManager sm() {
    return sm;
  }

  public StoreManager store() {
    return store;
  }

  public QuestsManager qm() {
    return qm;
  }

  public boolean isFirstTry() {
    return isFirstTry == 0;
  }
}
