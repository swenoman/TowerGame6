package towerREWORK.Database;

import java.sql.Connection;

public interface ActionResolver {
  Connection getConnection();

  int isFirstTry();

  int getHeroLevel(int heroID);

  int getHeroLevelProgress(int heroID);

  boolean isHeroAvailable(int heroID);

  int getActiveHeroID(int slotNum);

  int getArmedItemID(int heroNum, int slotNum);

  int getBagItemID(int slotNum);

  void updateHeroLevel(int heroID, int level);

  void updateHeroLevelProgress(int heroID, int levelProgress);

  void updateHeroAvailable(int heroID, boolean available);

  void updateActiveHeroID(int slotNum, int heroID);

  void updateArmedItemID(int heroNum, int slotNum, int itemID);

  void updateBagItemID(int slotNum, int itemID);

  int getCoins();

  void updateCoins(int newCoins);

  int currentStage();

  int maxStage();

  void updateCurrentStage(int stageNum);

  void updateMaxStage(int newMaxStage);

  int getCurrentBlacksmith();

  void updateCurrentBlacksmith(int id);

  int getBlacksmithLevel(int id);

  int getBlacksmithLevelProgress(int id);

  void updateBlacksmithLevel(int id, int level);

  void updateBlacksmithLevelProgress(int id, int levelProgress);

  int getBlacksmithStoreItemID(int slot);

  void updateBlacksmithStoreItemID(int slot, int itemID);

  void setFirstTry(int x);

  void resetData();

  boolean isResetDone();
}