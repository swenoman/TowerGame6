package towerREWORK.Stages.HomeStage.Groups.Inventory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import towerREWORK.Assets;
import towerREWORK.Managers.BagManager;
import towerREWORK.Managers.GameDataManager;

public class BagGroup extends Group {
  private Skin imagesSkin;
  private ImageTextButton equip;
  private ImageButton deleteItem;
  private GameDataManager gdm;
  private Assets assets;
  private int selectedSlotID;
  private BagManager manager;
  private InventoryRoot root;

  public BagGroup(GameDataManager gdm, InventoryRoot root) {
    setName("bag");
    this.gdm = gdm;
    manager = gdm.bm();
    assets = gdm.assets();
    this.root = root;
    imagesSkin = new Skin(assets.manager.get(assets.images, TextureAtlas.class));

    ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
    Skin buttonsSkin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
    style.up = buttonsSkin.getDrawable("NoTextButton");
    style.down = buttonsSkin.getDrawable("NoTextButton");
    style.over = buttonsSkin.getDrawable("NoTextButton");
    style.font = assets.manager.get(assets.font);

    equip = new ImageTextButton("equip", style);
    equip.setSize(100, 20);
    equip.setPosition(500, 40);
    equip.setName("equip");
    equip.setVisible(false);
    equip.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        equip.setVisible(false);
        deleteItem.setVisible(false);
        equipItem();
      }
    });
    addActor(equip);
//
    deleteItem = new ImageButton(buttonsSkin.getDrawable("DeleteButton"));
    deleteItem.setSize(50, 50);
    deleteItem.setPosition(equip.getRight() + 50, equip.getY());
    deleteItem.setName("delete");
    deleteItem.setVisible(false);
    deleteItem.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        deleteItem.setVisible(false);
        equip.setVisible(false);
        deleteItem();
      }
    });
    addActor(deleteItem);

    float x = 0;
    float y = 0;
    int bagNum = 0;
    for (int j = 0; j < 4; j++) {
      for (int i = 0; i < 8; i++) {
        Image bagSlot = new Image(gdm.bm().getItem(bagNum).getDrawable());
        bagSlot.setPosition(x, y);
        bagSlot.setSize(46, 46);
        bagSlot.setName(String.valueOf(bagNum));
        addBagItemListener(bagSlot);
        bagNum++;
        x += 50;
        addActor(bagSlot);
      }
      y -= 50;
      x = 0;
    }
  }

  public void addItem(int itemId) {
    if (itemId != 0) {
      int freeSlot = manager.freeSlot();
      manager.updateSlot((Image) findActor(String.valueOf(freeSlot)), itemId);
      addBagItemListener((Image) findActor(String.valueOf(freeSlot)));
    }
  }

  private void deleteItem() {
    manager.updateSlot((Image) findActor(String.valueOf(selectedSlotID)), 0);
    addBagItemListener((Image) findActor(String.valueOf(selectedSlotID)));
  }

  private void equipItem() {
    int bagItemId = manager.getItem(selectedSlotID).id();
    int selectedEquippedSlotID = 0;
    switch (manager.getItem(selectedSlotID).type()) {
      case "weapon":
        selectedEquippedSlotID = 0;
        break;
      case "head":
        selectedEquippedSlotID = 1;
        break;
      case "body":
        selectedEquippedSlotID = 2;
        break;
      case "taz":
        selectedEquippedSlotID = 3;
        break;
      case "hand":
        selectedEquippedSlotID = 4;
        break;
      case "foot":
        selectedEquippedSlotID = 5;
        break;
    }
    manager.updateSlot((Image) findActor(String.valueOf(selectedSlotID)), 0);
    addBagItemListener((Image) findActor(String.valueOf(selectedSlotID)));
    ((ArmedItemsGroup) root.findActor("armedItems")).equipItem(selectedEquippedSlotID, bagItemId);
  }

  private void addBagItemListener(final Image image) {
    if (manager.getItem(Integer.parseInt(image.getName())).id() > 0) {
      image.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          selectedSlotID = Integer.parseInt(event.getTarget().getName());
          equip.setPosition(x, y - 50);
          deleteItem.setPosition(x, y - 100);
          equip.toFront();
          deleteItem.toFront();
          equip.setVisible(true);
          deleteItem.setVisible(true);
          ((Group) root.findActor("armedItems")).findActor("unequip").setVisible(false);
          Group formation = root.findActor("formation");
          ((Label) formation.findActor("itemStats")).setText(manager.getItem(Integer.parseInt(image.getName())).statsText());
          formation.findActor("itemStats").setVisible(true);
        }
      });
    } else
      image.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          Group formation = root.findActor("formation");
          formation.findActor("itemStats").setVisible(false);
          equip.setVisible(false);
          ((Group) root.findActor("armedItems")).findActor("unequip").setVisible(false);
          deleteItem.setVisible(false);
        }
      });
  }


}
