package towerREWORK.Managers;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import towerREWORK.HeroCore.HeroCore;
import towerREWORK.Heroes.Heroes;
import towerREWORK.Heroes.Nullable;
import towerREWORK.ItemCore.ItemCore;

public class HeroesManager {
  private GameDataManager gdm;
  private Array<HeroCore> activeHeroes;
  private Heroes heroes;

  public HeroesManager(GameDataManager gdm) {
    this.gdm = gdm;
    heroes = new Heroes(gdm);
    activeHeroes = new Array<>();
    for (int i = 0; i < 4; i++) {
      activeHeroes.add(heroes.getHero(gdm.database().getActiveHeroID(i)));
      if (activeHeroes.get(i) != null) {
        Array<ItemCore> items = new Array<>();
        for (int j = 0; j < 8; j++) {
          items.add(new ItemCore(gdm, gdm.database().getArmedItemID(i, j)));
        }
        activeHeroes.get(i).initArmedItems(items);
      }
    }
  }

  public HeroCore getActiveHero(int heroNum) {
    return activeHeroes.get(heroNum);
  }

  public HeroCore getHero(int heroID) {
    if (heroID > 3) {
      return new Nullable(gdm, false);
    }
    return heroes.getHero(heroID);
  }

  public void unlockHero(int heroID) {
    // сделать в базе метод
    heroes.getHero(heroID).unlockHero();
  }

  public void updateActiveHeroID(final int slot, final int heroID) {
    Array<ItemCore> items = new Array<>();
    for (int i = 0; i < 8; i++) {
      items.add(new ItemCore(gdm, activeHeroes.get(slot).getItem(i).id()));
    }
    activeHeroes.get(slot).removeItems();
    activeHeroes.set(slot, heroes.getHero(heroID));
    activeHeroes.get(slot).initArmedItems(items);
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateActiveHeroID(slot, heroID);
      }
    }).start();

  }

  public void updateArmedItem(Image image, final int heroNum, final int slotNum, final int itemID) {
    activeHeroes.get(heroNum).updateItem(image, slotNum, itemID);
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateArmedItemID(heroNum, slotNum, itemID);
      }
    }).start();
  }

  public boolean checkRepeat(int heroId) {
    for (int i = 0; i < activeHeroes.size; i++) {
      if (activeHeroes.get(i).id() == heroId) {
        return true;
      }
    }
    return false;
  }

  public void swapActiveHeroes(final int hero1, final int hero2) {
    activeHeroes.swap(hero1, hero2);
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateActiveHeroID(hero1, activeHeroes.get(hero1).id());
        gdm.database().updateActiveHeroID(hero2, activeHeroes.get(hero2).id());
      }
    }).start();
  }
}
