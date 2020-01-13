package towerREWORK.Managers;

import towerREWORK.Store.BlacksmithStore.Store;

public class StoreManager {
  private Store store;

  public Store bss() {
    return store;
  }

  public StoreManager(GameDataManager gdm) {
    store = new Store(gdm);
  }
}
