package towerREWORK.Stages.HomeStage.Groups.Inventory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import towerREWORK.Assets;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Managers.HeroesManager;

public class ArmedItemsGroup extends Group {
  private HeroesManager manager;
  private ImageTextButton unequip;
  private Skin imagesSkin;
  private GameDataManager gdm;
  private Assets assets;
  private int selectedSlotID;
  private int selectedHeroID = 0;
  private InventoryRoot root;

  public ArmedItemsGroup(final GameDataManager gdm, final InventoryRoot root) {
    setName("armedItems");
    this.gdm = gdm;
    manager = gdm.hm();
    assets = gdm.assets();
    this.root = root;
    imagesSkin = new Skin(assets.manager.get(assets.images, TextureAtlas.class));


    ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
    Skin buttonsSkin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
    style.up = buttonsSkin.getDrawable("NoTextButton");
    style.down = buttonsSkin.getDrawable("NoTextButton");
    style.over = buttonsSkin.getDrawable("NoTextButton");
    style.font = assets.manager.get(assets.font);

    float x = 0;
    float y = 0;
    for (int j = 0; j < 8; j++) {
      Image slot = new Image(manager.getActiveHero(0).getItem(j).getDrawable());
      slot.setName(String.valueOf(j));
      slot.setPosition(x, y);
      slot.setSize(46, 46);
      addEquippedItemListener(slot);
      addActor(slot);
      x += 48;
    }

    unequip = new ImageTextButton("unequip", style);
    unequip.setSize(100, 20);
    unequip.setPosition(0, 0);
    unequip.setName("unequip");
    unequip.setVisible(false);
    unequip.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        unequipItem();
      }
    });
    addActor(unequip);
  }


  private void addEquippedItemListener(final Image image) {
    FormationGroup formation = root.findActor("formation");
    final int focusedHeroNum = formation.getSelectedHeroID();
    if (manager.getActiveHero(focusedHeroNum).getItem(Integer.parseInt(image.getName())).id() != 0) {
      image.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          selectedSlotID = Integer.parseInt(event.getTarget().getName());
          ((Label) ((Group) root.findActor("formation")).findActor("itemStats")).setText(manager.getActiveHero(((FormationGroup) root.findActor("formation")).getSelectedHeroID()).getItem(Integer.parseInt(event.getTarget().getName())).statsText());
          ((Group) root.findActor("formation")).findActor("itemStats").setVisible(true);
          unequip.setPosition(x, y - 50);
          unequip.setVisible(true);
          unequip.toFront();
          ((Group) root.findActor("bag")).findActor("equip").setVisible(false);
          ((Group) root.findActor("bag")).findActor("delete").setVisible(false);
        }
      });
    } else
      image.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          unequip.setVisible(false);
          ((Group) root.findActor("formation")).findActor("itemStats").setVisible(false);
          ((Group) root.findActor("bag")).findActor("equip").setVisible(false);
          ((Group) root.findActor("bag")).findActor("delete").setVisible(false);
        }
      });
  }

  public void setHeroItems(int selectedHeroID) {
    this.selectedHeroID = selectedHeroID;
    for (int i = 0; i < 8; i++) {
      ((Image) findActor(String.valueOf(i))).setDrawable(manager.getActiveHero(selectedHeroID).getItem(i).getDrawable());
      addEquippedItemListener((Image) findActor(String.valueOf(i)));
    }
  }

  private void unequipItem() {
    if (gdm.bm().freeSlot() >= 0) {
      unequip.setVisible(false);
      int armedItemId = manager.getActiveHero(selectedHeroID).getItem(selectedSlotID).id();
      manager.updateArmedItem((Image) findActor(String.valueOf(selectedSlotID)), selectedHeroID, selectedSlotID, 0);
      addEquippedItemListener((Image) findActor(String.valueOf(selectedSlotID)));
      ((BagGroup) root.findActor("bag")).addItem(armedItemId);
      Label label = ((Group) root.findActor("formation")).findActor("heroStats");
      label.setText(manager.getActiveHero(selectedHeroID).statsText());
    } else
      System.out.println("No free slot in bag");
  }

  public void equipItem(int slot, int itemId) {
    int armedItemId = manager.getActiveHero(selectedHeroID).getItem(slot).id();
    manager.updateArmedItem((Image) findActor(String.valueOf(slot)), selectedHeroID, slot, itemId);
    addEquippedItemListener((Image) findActor(String.valueOf(slot)));
    ((BagGroup) root.findActor("bag")).addItem(armedItemId);
    Label label = ((Group) root.findActor("formation")).findActor("heroStats");
    label.setText(manager.getActiveHero(selectedHeroID).statsText());
  }
}
