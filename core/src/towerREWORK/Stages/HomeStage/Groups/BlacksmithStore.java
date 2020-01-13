package towerREWORK.Stages.HomeStage.Groups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import towerREWORK.Assets;
import towerREWORK.ItemCore.ItemCore;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Stages.HomeStage.Groups.Inventory.BagGroup;

public class BlacksmithStore extends Group {
  private GameDataManager gdm;
  private Skin imageSkin;
  private Skin buttonSkin;
  private BitmapFont font;
  private Array<Label> itemStats;
  private Array<Image> items;
  private ImageTextButton buy;
  private Group chooseBlacksmithGroup;
  private int targetedBlackSmith;
  private Assets assets;

  public BlacksmithStore(GameDataManager gdm) {
    this.gdm = gdm;
    assets = gdm.assets();
    imageSkin = new Skin(assets.manager.get(assets.images, TextureAtlas.class));
    buttonSkin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
    font = assets.manager.get(assets.font);
    font.getData().markupEnabled = true;
    storeSlotsInit();
    chooseBlacksmithGroupInit();
  }

  private void storeSlotsInit() {
    Image storeImage = new Image();
    storeImage.setDrawable(imageSkin.getDrawable("StoreFrame"));
    storeImage.setSize(840, 480);
    storeImage.setPosition(0, 0);
    storeImage.setName("StoreFrame");
    addActor(storeImage);

    items = new Array<>();
    itemStats = new Array<>();
    Image chooseBlackSmithFrame = new Image();
    chooseBlackSmithFrame.setSize(67, 65);
    chooseBlackSmithFrame.setPosition(13, 402);
    addActor(chooseBlackSmithFrame);

    Label.LabelStyle labelStyle = new Label.LabelStyle(font, null);

    Label currentBlacksmithLevel = new Label(gdm.store().bss().blacksmith().levelText(), labelStyle);
    currentBlacksmithLevel.setSize(67, 65);
    currentBlacksmithLevel.setPosition(13, 402);
    currentBlacksmithLevel.setName("currentBlacksmithLevel");
    currentBlacksmithLevel.setAlignment(Align.center);
    addActor(currentBlacksmithLevel);

    float x = 17;
    float y = 290;
    int itemNum = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Image image = new Image(gdm.store().bss().getItem(itemNum).getDrawable());
        image.setSize(87, 81);
        image.setPosition(x, y);
        image.setName(String.valueOf(itemNum));
        addStoreItemListener(image);
        items.add(image);
        addActor(image);
        itemNum++;
        x += 90;
      }
      x = 17;
      y -= 101;
    }

    Label storeClickedItem = new Label("", labelStyle);
    storeClickedItem.setSize(150, 150);
    storeClickedItem.setPosition(307, 154);
    storeClickedItem.setName("storeClickedItem");
    storeClickedItem.setAlignment(Align.center);
    itemStats.add(storeClickedItem);
    addActor(storeClickedItem);

    Label storeEquippedSlot1Item = new Label("", labelStyle);
    storeEquippedSlot1Item.setSize(150, 150);
    storeEquippedSlot1Item.setPosition(469, 231);
    storeEquippedSlot1Item.setName("storeEquippedSlot1Item");
    storeEquippedSlot1Item.setAlignment(Align.center);
    itemStats.add(storeEquippedSlot1Item);
    addActor(storeEquippedSlot1Item);

    Label storeEquippedSlot2Item = new Label("", labelStyle);
    storeEquippedSlot2Item.setSize(150, 150);
    storeEquippedSlot2Item.setPosition(626, 231);
    storeEquippedSlot2Item.setName("storeEquippedSlot2Item");
    storeEquippedSlot2Item.setAlignment(Align.center);
    itemStats.add(storeEquippedSlot2Item);
    addActor(storeEquippedSlot2Item);

    Label storeEquippedSlot3Item = new Label("", labelStyle);
    storeEquippedSlot3Item.setSize(150, 150);
    storeEquippedSlot3Item.setPosition(469, 67);
    storeEquippedSlot3Item.setName("storeEquippedSlot3Item");
    storeEquippedSlot3Item.setAlignment(Align.center);
    itemStats.add(storeEquippedSlot3Item);
    addActor(storeEquippedSlot3Item);

    Label storeEquippedSlot4Item = new Label("", labelStyle);
    storeEquippedSlot4Item.setSize(150, 150);
    storeEquippedSlot4Item.setPosition(626, 67);
    storeEquippedSlot4Item.setName("storeEquippedSlot4Item");
    storeEquippedSlot4Item.setAlignment(Align.center);
    itemStats.add(storeEquippedSlot4Item);
    addActor(storeEquippedSlot4Item);

    ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
    imageButtonStyle.up = buttonSkin.getDrawable("CloseButton");
    imageButtonStyle.down = buttonSkin.getDrawable("CloseButton");
    imageButtonStyle.over = buttonSkin.getDrawable("CloseButton");

    ImageButton closeStoreFrame = new ImageButton(imageButtonStyle);
    closeStoreFrame.setSize(36, 36);
    closeStoreFrame.setPosition(804, 444);
    closeStoreFrame.setName("closeStoreFrame");
    closeStoreFrame.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        event.getTarget().getParent().setVisible(false);
        buy.setVisible(false);
      }
    });
    addActor(closeStoreFrame);

    ImageTextButton.ImageTextButtonStyle itbs = new ImageTextButton.ImageTextButtonStyle();
    itbs.up = buttonSkin.getDrawable("NoTextButton");
    itbs.down = buttonSkin.getDrawable("NoTextButton");
    itbs.over = buttonSkin.getDrawable("NoTextButton");
    itbs.font = font;

    buy = new ImageTextButton("Buy", itbs);
    buy.setSize(150, 65);
    buy.setPosition(307, 67);
    buy.setName("buy");
    buy.setVisible(false);
    buy.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (gdm.cm().coins() >= 0 & gdm.bm().freeSlot() != -1) {
          buy.setVisible(false);
          ((BagGroup) ((Group) getStage().getRoot().findActor("inventoryRoot")).findActor("bag")).addItem(gdm.store().bss().getItem(selectedItemInStore).id());
          gdm.store().bss().deleteItem(selectedItemInStore);
          items.get(selectedItemInStore).setDrawable(gdm.store().bss().getItem(selectedItemInStore).getDrawable());
          addStoreItemListener(items.get(selectedItemInStore));
          gdm.cm().updateCoins(200);
        } else
          System.out.println("No free slots in bag");
      }
    });
    addActor(buy);

    ImageTextButton refresh = new ImageTextButton("Pay for new ITEMS", itbs);
    refresh.setSize(150, 65);
    refresh.setPosition(307, 316);
    refresh.setName("refresh");
    refresh.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (gdm.cm().coins() >= 200) {
          gdm.store().bss().refresh();
          buy.setVisible(false);
          for (int i = 0; i < items.size; i++) {
            items.get(i).setDrawable(gdm.store().bss().getItem(i).getDrawable());
            addStoreItemListener(items.get(i));
          }
          ((Label) findActor("currentBlacksmithLevel")).setText("Level: " + gdm.store().bss().blacksmith().level() + "(" + gdm.store().bss().blacksmith().levelProgress() + "%)");
          gdm.cm().updateCoins(-200);
        } else
          System.out.println("NotEnoughCOINS");
      }
    });
    addActor(refresh);


    ImageTextButton blacksmithChooseButton = new ImageTextButton("Choose Your Blacksmith", itbs);
    blacksmithChooseButton.setSize(200, 50);
    blacksmithChooseButton.setPosition(100, 402);
    blacksmithChooseButton.setName("blacksmithChooseButton");
    blacksmithChooseButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        chooseBlacksmithGroup.setVisible(true);
      }
    });
    addActor(blacksmithChooseButton);

  }

  private void chooseBlacksmithGroupInit() {
    ImageTextButton.ImageTextButtonStyle itbs = new ImageTextButton.ImageTextButtonStyle();
    itbs.up = buttonSkin.getDrawable("NoTextButton");
    itbs.down = buttonSkin.getDrawable("NoTextButton");
    itbs.over = buttonSkin.getDrawable("NoTextButton");
    itbs.font = font;
    itbs.fontColor = Color.BLACK;

    chooseBlacksmithGroup = new Group();
    chooseBlacksmithGroup.setVisible(false);

    Image chooseBlacksmithFrame = new Image();
    chooseBlacksmithFrame.setDrawable(imageSkin.getDrawable("ChooseBlackSmith"));
    chooseBlacksmithFrame.setSize(840, 480);
    chooseBlacksmithFrame.setPosition(0, 0);
    chooseBlacksmithFrame.setName("chooseBlacksmithFrame");
    chooseBlacksmithGroup.addActor(chooseBlacksmithFrame);

    ImageTextButton chooseBlacksmithButton = new ImageTextButton("ChooseBlacksmith", itbs);
    chooseBlacksmithButton.setSize(368, 100);
    chooseBlacksmithButton.setPosition(236, 20);
    chooseBlacksmithButton.setName("chooseBlacksmithButton");
    chooseBlacksmithButton.setVisible(false);
    chooseBlacksmithButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        gdm.store().bss().changeBlacksmith(targetedBlackSmith);
        ((Label) findActor("currentBlacksmithLevel")).setText(gdm.store().bss().blacksmith().levelText());
        findActor("chooseBlacksmithButton").setVisible(false);
        chooseBlacksmithGroup.setVisible(false);
      }
    });
    chooseBlacksmithGroup.addActor(chooseBlacksmithButton);

    float x = 36;
    float y = 140;
    for (int i = 0; i < 3; i++) {
      Image blacksmith = new Image();
      blacksmith.setPosition(x, y);
      blacksmith.setSize(200, 200);
      blacksmith.setName(String.valueOf(i + 1));
      blacksmith.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          targetedBlackSmith = Integer.parseInt(event.getTarget().getName());
          if (findActor("chooseBlacksmithButton").isVisible())
            findActor("chooseBlacksmithButton").setVisible(false);
          else
            findActor("chooseBlacksmithButton").setVisible(true);
        }
      });
      x += 284;
      chooseBlacksmithGroup.addActor(blacksmith);
    }
    addActor(chooseBlacksmithGroup);
  }

  private int selectedItemInStore;

  private void addStoreItemListener(Image image) {
    if (gdm.store().bss().getItem(Integer.parseInt(image.getName())).id() != 0) {
      image.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          selectedItemInStore = Integer.parseInt(event.getTarget().getName());
          itemStats.get(0).setText(gdm.store().bss().getItem(selectedItemInStore).statsText());
          int itemType = -1;
          switch (gdm.store().bss().getItem(selectedItemInStore).type()) {
            case "weapon":
              itemType = 0;
              break;
            case "head":
              itemType = 1;
              break;
            case "body":
              itemType = 2;
              break;
            case "taz":
              itemType = 3;
              break;
            case "hand":
              itemType = 4;
              break;
            case "foot":
              itemType = 5;
              break;
          }
          itemStats.get(1).setText(itemTextEditor(gdm.store().bss().getItem(selectedItemInStore), gdm.hm().getActiveHero(0).getItem(itemType)));
          itemStats.get(2).setText(itemTextEditor(gdm.store().bss().getItem(selectedItemInStore), gdm.hm().getActiveHero(1).getItem(itemType)));
          itemStats.get(3).setText(itemTextEditor(gdm.store().bss().getItem(selectedItemInStore), gdm.hm().getActiveHero(2).getItem(itemType)));
          itemStats.get(4).setText(itemTextEditor(gdm.store().bss().getItem(selectedItemInStore), gdm.hm().getActiveHero(3).getItem(itemType)));

          buy.setVisible(true);
        }
      });
    } else
      image.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          buy.setVisible(false);
        }
      });
  }

  private String itemTextEditor(ItemCore item1, ItemCore item2) {
    String text = "[ORANGE]Name: [BLACK]" + item2.name() + "\n" + "[ORANGE]Type: [BLACK]" + item2.type() + "\n";
    if (item1.attackDamage() > item2.attackDamage()) {
      text += "[ORANGE]Attack: [RED]" + item2.attackDamage() + "\n";
    } else if (item1.attackDamage() < item2.attackDamage()) {
      text += "[ORANGE]Attack: [GREEN]" + item2.attackDamage() + "\n";
    } else text += "[ORANGE]Attack: [BLACK]" + item2.attackDamage() + "\n";
    if (item1.health() > item2.health()) {
      text += "[ORANGE]Health: [RED]" + item2.health() + "\n";
    } else if (item1.health() < item2.health()) {
      text += "[ORANGE]Heath: [GREEN]" + item2.health() + "\n";
    } else text += "[ORANGE]Health: [BLACK]" + item2.health() + "\n";
    return text;
  }
}
