package towerREWORK.HeroCore;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

import towerREWORK.ItemCore.ItemCore;
import towerREWORK.Managers.GameDataManager;

public abstract class HeroCore extends HeroData {
  private String rank;
  private int subRank;
  private int levelProgress;
  private Array<ItemCore> items;
  private int physicDamage;
  private int magicDamage;
  private int health;
  private int defence;
  private int speed ;
  protected GameDataManager gdm;

  public HeroCore(GameDataManager gdm, int heroID, boolean isAvailable) {
    super(gdm, isAvailable);
    this.gdm = gdm;
    setHeroData(heroID);
    basicPhysicDamage = 10;
    physicDamage = basicPhysicDamage;
    magicDamage = basicMagicDamage;
    health = basicHealth;
    defence = basicDefence;
    speed = basicSpeed;

    if (heroID != -1) {
      subRank = gdm.database().getHeroLevel(heroID);
      levelProgress = gdm.database().getHeroLevelProgress(heroID);
    } else {
      subRank = 0;
      levelProgress = 0;
    }
    items = new Array<>();
    items.setSize(8);
    for (int i = 0; i < 8; i++) {
      items.set(i, new ItemCore(gdm, 0));
    }
  }

  public void initArmedItems(Array<ItemCore> items) {
    for (int i = 0; i < items.size; i++) {
      this.items.set(i, items.get(i));
      physicDamage += items.get(i).attackDamage();
      health += items.get(i).health();
      System.out.println("Добавляю итем: id = " + this.items.get(i).id());
    }
  }

  public void updateItem(Image image, int slotNum, int itemID) {
    items.get(slotNum).setItemData(itemID);
    image.setDrawable(items.get(slotNum).getDrawable());
  }

  public ItemCore getItem(int slotNum) {
    return items.get(slotNum);
  }

  public Array<ItemCore> getItems() {

    return items;
  }

  public void removeItems() {
    for (int i = 0; i < items.size; i++) {
      items.get(i).setItemData(0);
      System.out.println("Удаляю итем: проход " + i);
    }
  }

  public void unlockHero() {
    super.available = true;
  }

  public String statsText() {
    physicDamage = basicPhysicDamage + (subRank * physicDamageGrowth);
    magicDamage = basicMagicDamage + (subRank * magicDamageGrowth);
    health = basicHealth + (subRank * healthGrowth);
    defence = basicDefence + (subRank * defenceGrowth);

    for (int i = 0; i < items.size; i++) {
      physicDamage += items.get(i).attackDamage();
      health += items.get(i).health();
    }
    return "Name: " + name + "\n" + "Rank: " + getRank() + "(" + levelProgress + ")" + "\n" + "Type: " + type + "\n" + "Attack: " + physicDamage + "\n" + "Health: " + health;
  }

  public void updateLevel(int exp) {
    levelProgress += exp;
    if (levelProgress > 99) {
      subRank++;
      levelProgress -= 100;
      if (levelProgress > 99) {
        updateLevel(0);
      }
    }
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateHeroLevel(id, subRank);
        gdm.database().updateHeroLevelProgress(id, levelProgress);
      }
    }).start();
  }

  public int getSubRank() {
    return subRank;
  }

  public int getLevelProgress() {
    return levelProgress;
  }

  public int physicDamage() {
    return physicDamage;
  }

  public int speed() {
    return speed;
  }

  public int magicDamage() {
    return magicDamage;
  }

  public int health() {
    return health;
  }

  public int defence() {
    return defence;
  }

  public Drawable getSpellImage() {
    return super.spellsImages;
  }

  public Drawable getInventoryImage() {
    return inventoryImage;
  }

  public String getRank() {
    if (subRank < 5) {
      return "novice";
    } else
      return "player";
  }
}
