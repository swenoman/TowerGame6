package towerREWORK.Store.BlacksmithStore;

import com.badlogic.gdx.utils.Array;
import towerREWORK.Managers.GameDataManager;

public class Blacksmith {
  private GameDataManager gdm;
  private int id;
  private int level;
  private int levelProgress;
  private Array<Integer> itemID;

  public Blacksmith(GameDataManager gdm, int id) {
    this.gdm = gdm;
    itemID = new Array<>();
    setBlacksmithID(id);
  }

  public void setBlacksmithID(final int id) {
    this.id = id;
    level = gdm.database().getBlacksmithLevel(id);
    levelProgress = gdm.database().getBlacksmithLevelProgress(id);
    switch (id) {
      case 1:
        itemID.clear();
        itemID.add(1, 2, 3, 4);
        itemID.add(5, 11, 12, 13);
        itemID.add(14, 15, 16, 17);
        break;
      case 2:
        itemID.clear();
        itemID.add(6, 7, 8, 9);
        itemID.add(10, 18, 19, 20);
        itemID.add(21, 22, 23, 24);
        break;
      case 3:
        itemID.clear();
        itemID.add(25, 26, 27);
        break;
    }
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateCurrentBlacksmith(id);
      }
    }).start();
  }

  public int randomItemID() {
    return itemID.random();
  }

  public int level() {
    return level;
  }

  public int levelProgress() {
    return levelProgress;
  }

  public int id() {
    return id;
  }

  public void updateLevel(int exp) {
    levelProgress += exp;
    if (levelProgress > 99) {
      level++;
      levelProgress -= 100;
      if (levelProgress > 99) {
        updateLevel(0);
      }
    }
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateBlacksmithLevel(id, level);
        gdm.database().updateBlacksmithLevelProgress(id, levelProgress);
      }
    }).start();
  }

  public String levelText() {
    return "Level: " + level + "(" + levelProgress + "%)";
  }
}
