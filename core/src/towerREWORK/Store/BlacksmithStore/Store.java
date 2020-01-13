package towerREWORK.Store.BlacksmithStore;

import com.badlogic.gdx.utils.Array;
import towerREWORK.ItemCore.ItemCore;
import towerREWORK.Managers.GameDataManager;

public class Store {
  private Array<ItemCore> items;
  private Blacksmith blacksmith;
  private GameDataManager gdm;

  public Store(GameDataManager gdm) {
    this.gdm = gdm;
    items = new Array<>();
    blacksmith = new Blacksmith(gdm, gdm.database().getCurrentBlacksmith());
    for (int i = 0; i < 9; i++) {
      items.add(new ItemCore(gdm, gdm.database().getBlacksmithStoreItemID(i)));
    }
  }

  public ItemCore getItem(int slotNum) {
    return items.get(slotNum);
  }

  public void refresh() {
    for (int i = 0; i < 9; i++) {
      items.get(i).setItemData(blacksmith.randomItemID());
    }
    new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 9; i++) {
          gdm.database().updateBlacksmithStoreItemID(i, items.get(i).id());
        }
      }
    }).start();
  }

  public void deleteItem(final int slotNum) {
    items.get(slotNum).setItemData(0);
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateBlacksmithStoreItemID(slotNum, 0);
      }
    }).start();

  }

  public Blacksmith blacksmith() {
    return blacksmith;
  }

  public void changeBlacksmith(int id) {
    blacksmith.setBlacksmithID(id);
  }
}
