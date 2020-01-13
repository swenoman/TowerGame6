package towerREWORK.Managers;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import towerREWORK.ItemCore.ItemCore;

public class BagManager {
  private Array<ItemCore> bag;
  private GameDataManager gdm;

  public BagManager(GameDataManager gdm) {
    this.gdm = gdm;
    bag = new Array<>();
    for (int i = 0; i < 32; i++) {
      bag.add(new ItemCore(gdm, gdm.database().getBagItemID(i)));
    }
  }

  public ItemCore getItem(int slotNum) {
    return bag.get(slotNum);
  }

  public int freeSlot() {
    for (int i = 0; i < bag.size; i++) {
      if (bag.get(i).id() == 0) {
        System.out.println(i + " freeBagSlot");
        return i;
      }
    }
    return -1;
  }

  public void updateSlot(Image image, final int itemID) {
    final int slotNum = Integer.parseInt(image.getName());
    bag.get(slotNum).setItemData(itemID);
    image.setDrawable(bag.get(slotNum).getDrawable());
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateBagItemID(slotNum, itemID);
      }
    }).start();
  }

  public void updateSlot(final int slotNum, final int itemID) {
    bag.get(slotNum).setItemData(itemID);
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateBagItemID(slotNum, itemID);
      }
    }).start();
  }
}
