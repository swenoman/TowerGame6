//package towerREWORK.Stages.HomeStage.Groups.Inventory;
//
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.scenes.scene2d.Group;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
//import com.badlogic.gdx.utils.Align;
//import com.badlogic.gdx.utils.Array;
//
//import towerREWORK.Assets;
//import towerREWORK.Managers.GameDataManager;
//import towerREWORK.Managers.HeroesManager;
//
//public class InventoryRootBackUp extends Group {
//  private GameDataManager gdm;
//  private Skin imagesSkin;
//  private Skin buttonsSkin;
//  private int focusedHeroNum = 0;
//  private BitmapFont font;
//  private ImageTextButton equip;
//  private ImageTextButton unequip;
//  private ImageButton deleteItem;
//  private Assets assets;
//  private HeroesManager hm;
//
//  private ArmedItemsGroup armedItems;
//  private BagGroup bag2;
//  private ActiveFormation editFormation;
//  private FormationGroup formation;
//
//
//  public InventoryRootBackUp(GameDataManager gdm) {
//    this.gdm = gdm;
//    this.assets = gdm.assets();
//    hm = gdm.hm();
//    imagesSkin = new Skin(assets.manager.get(assets.images, TextureAtlas.class));
//    buttonsSkin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
//    font = assets.manager.get(assets.font, BitmapFont.class);
//    playerInventoryInit();
//    formationGroupInit();
//
//    armedItems = new ArmedItemsGroup();
//    bag2 = new BagGroup(gdm);
//    editFormation = new ActiveFormation();
//    formation = new FormationGroup();
//  }
//
//  private void playerInventoryInit() {
//
//    Image frameImage = new Image(imagesSkin.getDrawable("PlayerMenu"));
//    frameImage.setSize(840, 480);
//    frameImage.setPosition(0, 0);
//    frameImage.setName("frameImage");
//    addActor(frameImage);
//
//    Image focusedHeroImage = new Image(hm.getActiveHero(0).getInventoryImage());
//    focusedHeroImage.setSize(353, 403);
//    focusedHeroImage.setPosition(15, 15);
//    focusedHeroImage.setName("focusedHeroImage");
//    focusedHeroImage.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        event.getTarget().setVisible(false);
//        ((Label) findActor("heroStats")).setText(gdm.hm().getActiveHero(focusedHeroNum).statsText());
//        findActor("heroStats").setVisible(true);
//        findActor("heroStats").toFront();
//      }
//    });
//    addActor(focusedHeroImage);
//
//    ImageButton closeFrameButton = new ImageButton(buttonsSkin.getDrawable("CloseButton"));
//    closeFrameButton.setSize(36, 36);
//    closeFrameButton.setPosition(804, 444);
//    closeFrameButton.setName("closeFrameButton");
//    closeFrameButton.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        event.getTarget().getParent().getParent().setVisible(false);
//      }
//    });
//    addActor(closeFrameButton);
//
//    Label.LabelStyle ls = new Label.LabelStyle(font, Color.BLACK);
//
//    Label heroStats = new Label("", ls);
//    heroStats.setAlignment(Align.center);
//    heroStats.setSize(353, 403);
//    heroStats.setText(gdm.hm().getActiveHero(focusedHeroNum).statsText());
//    heroStats.setVisible(false);
//    heroStats.setName("heroStats");
//    heroStats.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        event.getTarget().setVisible(false);
//        findActor("focusedHeroImage").setVisible(true);
//      }
//    });
//    addActor(heroStats);
//
//    Label itemStats = new Label("", ls);
//    itemStats.setVisible(false);
//    itemStats.setSize(353, 100);
//    itemStats.setPosition(15, 15);
//    itemStats.setAlignment(Align.center);
//    itemStats.setName("itemStats");
//    itemStats.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        event.getTarget().setVisible(false);
//      }
//    });
//    addActor(itemStats);
//
//    Image hero1 = new Image(hm.getActiveHero(0).getInventoryImage());
//    hero1.setPosition(15, 418);
//    hero1.setSize(68, 48);
//    hero1.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        equip.setVisible(false);
//        unequip.setVisible(false);
//        deleteItem.setVisible(false);
//        focusedHeroNum = 0;
//        setHeroActors();
//      }
//    });
//    Image hero2 = new Image(hm.getActiveHero(1).getInventoryImage());
//    hero2.setPosition(86, 418);
//    hero2.setSize(68, 48);
//    hero2.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        if (hm.getActiveHero(1).id() != -1) {
//          equip.setVisible(false);
//          unequip.setVisible(false);
//          deleteItem.setVisible(false);
//          focusedHeroNum = 1;
//          setHeroActors();
//        }
//      }
//    });
//    Image hero3 = new Image(hm.getActiveHero(2).getInventoryImage());
//    hero3.setPosition(157, 418);
//    hero3.setSize(68, 48);
//    hero3.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        if (hm.getActiveHero(2).id() != -1) {
//          equip.setVisible(false);
//          unequip.setVisible(false);
//          deleteItem.setVisible(false);
//          focusedHeroNum = 2;
//          setHeroActors();
//        }
//      }
//    });
//    Image hero4 = new Image(hm.getActiveHero(3).getInventoryImage());
//    hero4.setPosition(228, 418);
//    hero4.setSize(68, 48);
//    hero4.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        if (hm.getActiveHero(3).id() != -1) {
//          equip.setVisible(false);
//          unequip.setVisible(false);
//          deleteItem.setVisible(false);
//          focusedHeroNum = 3;
//          setHeroActors();
//        }
//      }
//    });
//    addActor(hero1);
//    addActor(hero2);
//    addActor(hero3);
//    addActor(hero4);
//
//    ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
//    style.up = buttonsSkin.getDrawable("NoTextButton");
//    style.down = buttonsSkin.getDrawable("NoTextButton");
//    style.over = buttonsSkin.getDrawable("NoTextButton");
//    style.font = font;
//
//    ImageTextButton formationGroupButton = new ImageTextButton("Edit Formation", style);
//    formationGroupButton.setSize(68, 48);
//    formationGroupButton.setPosition(299, 418);
//    formationGroupButton.setName("formationGroupButton");
//    formationGroupButton.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        updateFormationSlots();
//        formationGroup.setVisible(true);
//        formationGroup.toFront();
//
//      }
//    });
//    addActor(formationGroupButton);
//
//    heroSlotsInit();
//
//    equip = new ImageTextButton("equip", style);
//    equip.setSize(100, 20);
//    equip.setPosition(500, 40);
//    equip.setName("equip");
//    equip.setVisible(false);
//    equip.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        equip.setVisible(false);
//        deleteItem.setVisible(false);
//        final int freeBagSlot;
//        if (gdm.bm().freeSlot() < selectedBagSlotID)
//          if (gdm.bm().freeSlot() == -1)
//            freeBagSlot = selectedBagSlotID;
//          else
//            freeBagSlot = gdm.bm().freeSlot();
//        else
//          freeBagSlot = selectedBagSlotID;
//        equipItem(freeBagSlot);
//        unequip.setVisible(true);
//
//      }
//    });
//    addActor(equip);
//
//    unequip = new ImageTextButton("unequip", style);
//    unequip.setSize(100, 20);
//    unequip.setPosition(500, 40);
//    unequip.setName("unequip");
//    unequip.setVisible(false);
//    unequip.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        if (gdm.bm().freeSlot() >= 0) {
//          unequip.setVisible(false);
//          unequipItem(gdm.bm().freeSlot());
//          equip.setVisible(true);
//          deleteItem.setVisible(true);
//        } else
//          System.out.println("No free slot in bag");
//      }
//    });
//    addActor(unequip);
//
//    deleteItem = new ImageButton(buttonsSkin.getDrawable("DeleteButton"));
//    deleteItem.setSize(50, 50);
//    deleteItem.setPosition(equip.getRight() + 50, equip.getY());
//    deleteItem.setName("deleteItem");
//    deleteItem.setVisible(false);
//    deleteItem.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        deleteItem.setVisible(false);
//        equip.setVisible(false);
//        unequip.setVisible(false);
//        deleteItem();
//      }
//    });
//    addActor(deleteItem);
//  }
//
//  private Array<Image> bag;
//  private Array<Image> spells;
//  private Array<Image> items;
//
//  private void heroSlotsInit() {
//    bag = new Array<Image>();
//    spells = new Array<Image>();
//    items = new Array<Image>();
//    float x = 411;
//    float y = 402;
//
//    for (int j = 0; j < 8; j++) {
//      Image slot = new Image(gdm.hm().getActiveHero(0).getItem(j).getDrawable());
//      slot.setName(String.valueOf(j));
//      slot.setPosition(x, y);
//      slot.setSize(46, 46);
//      addEquippedItemListener(slot);
//      items.add(slot);
//      addActor(slot);
//      x += 48;
//    }
//
//    x = 378;
//    y = 210;
//    int bagNum = 0;
//    for (int j = 0; j < 4; j++) {
//      for (int i = 0; i < 8; i++) {
//        Image bagSlot = new Image(gdm.bm().getItem(bagNum).getDrawable());
//        bagSlot.setPosition(x, y);
//        bagSlot.setSize(46, 46);
//        bagSlot.setName(String.valueOf(bagNum));
//        addBagItemListener(bagSlot);
//        bagNum++;
//        x += 50;
//        bag.add(bagSlot);
//        addActor(bagSlot);
//      }
//      y -= 50;
//      x = 378;
//    }
//    x = 414;
//    y = 286;
//    for (int i = 0; i < 4; i++) {
//      for (int j = 0; j < 4; j++) {
//        Image spellSlot = new Image();
//        spellSlot.setPosition(x, y);
//        spellSlot.setVisible(false);
//        spells.add(spellSlot);
//        addActor(spellSlot);
//        x += 96;
//      }
//    }
//  }
//
//  private void setHeroActors() {
//    ((Image) findActor("focusedHeroImage")).setDrawable(gdm.hm().getActiveHero(focusedHeroNum).getInventoryImage());
//    ((Label) findActor("heroStats")).setText(gdm.hm().getActiveHero(focusedHeroNum).statsText());
//    for (int i = 0; i < items.size; i++) {
//      items.get(i).setDrawable(gdm.hm().getActiveHero(focusedHeroNum).getItem(i).getDrawable());
//    }
//  }
//
//  private int selectedEquippedSlotID;
//  private int selectedBagSlotID;
//
//  private void addEquippedItemListener(final Image image) {
//    if (gdm.hm().getActiveHero(focusedHeroNum).getItem(Integer.parseInt(image.getName())).id() != 0) {
//      image.addListener(new ClickListener() {
//        @Override
//        public void clicked(InputEvent event, float x, float y) {
//          super.clicked(event, x, y);
//          selectedEquippedSlotID = Integer.parseInt(event.getTarget().getName());
//          ((Label) findActor("itemStats")).setText(gdm.hm().getActiveHero(focusedHeroNum).getItem(Integer.parseInt(image.getName())).statsText());
//          findActor("itemStats").setVisible(true);
//          unequip.setVisible(true);
//          unequip.toFront();
//          equip.setVisible(false);
//          deleteItem.setVisible(false);
//        }
//      });
//    } else
//      image.addListener(new ClickListener() {
//        @Override
//        public void clicked(InputEvent event, float x, float y) {
//          super.clicked(event, x, y);
//          findActor("itemStats").setVisible(false);
//          unequip.setVisible(false);
//          equip.setVisible(false);
//          deleteItem.setVisible(false);
//        }
//      });
//  }
//
//  private void addBagItemListener(final Image image) {
//    if (gdm.bm().getItem(Integer.parseInt(image.getName())).id() != 0) {
//      image.addListener(new ClickListener() {
//        @Override
//        public void clicked(InputEvent event, float x, float y) {
//          super.clicked(event, x, y);
//          selectedBagSlotID = Integer.parseInt(event.getTarget().getName());
//          ((Label) findActor("itemStats")).setText(gdm.bm().getItem(Integer.parseInt(image.getName())).statsText());
//          findActor("itemStats").setVisible(true);
//          equip.setVisible(true);
//          unequip.setVisible(false);
//          deleteItem.setVisible(true);
//        }
//      });
//    } else
//      image.addListener(new ClickListener() {
//        @Override
//        public void clicked(InputEvent event, float x, float y) {
//          super.clicked(event, x, y);
//          findActor("itemStats").setVisible(false);
//          equip.setVisible(false);
//          unequip.setVisible(false);
//          deleteItem.setVisible(false);
//        }
//      });
//  }
//
//  public void updateBag() {
//    for (int i = 0; i < bag.size; i++) {
//      bag.get(i).setDrawable(gdm.bm().getItem(i).getDrawable());
//      addBagItemListener(bag.get(i));
//    }
//  }
//
//  private void equipItem(int freeBagSlot) {
//    switch (gdm.bm().getItem(selectedBagSlotID).type()) {
//      case "weapon":
//        selectedEquippedSlotID = 0;
//        break;
//      case "head":
//        selectedEquippedSlotID = 1;
//        break;
//      case "body":
//        selectedEquippedSlotID = 2;
//        break;
//      case "taz":
//        selectedEquippedSlotID = 3;
//        break;
//      case "hand":
//        selectedEquippedSlotID = 4;
//        break;
//      case "foot":
//        selectedEquippedSlotID = 5;
//        break;
//    }
//    int bagItemID = gdm.bm().getItem(selectedBagSlotID).id();
//    int armedItemID = gdm.hm().getActiveHero(focusedHeroNum).getItem(selectedEquippedSlotID).id();
//
//    gdm.bm().updateItem(bag.get(selectedBagSlotID), selectedBagSlotID, 0);
//    addBagItemListener(bag.get(selectedBagSlotID));
//
//    gdm.hm().updateArmedItem(items.get(selectedEquippedSlotID), focusedHeroNum, selectedEquippedSlotID, bagItemID);
//    gdm.bm().updateItem(bag.get(freeBagSlot), freeBagSlot, armedItemID);
//    addBagItemListener(bag.get(freeBagSlot));
//    addEquippedItemListener(items.get(selectedEquippedSlotID));
//
//    ((Label) findActor("heroStats")).setText(gdm.hm().getActiveHero(focusedHeroNum).statsText());
//  }
//
//  private void unequipItem(int freeBagSlot) {
//    int armedItemID = gdm.hm().getActiveHero(focusedHeroNum).getItem(selectedEquippedSlotID).id();
//
//    gdm.hm().updateArmedItem(items.get(selectedEquippedSlotID), focusedHeroNum, selectedEquippedSlotID, 0);
//    gdm.bm().updateItem(bag.get(freeBagSlot), freeBagSlot, armedItemID);
//    addBagItemListener(bag.get(freeBagSlot));
//    addEquippedItemListener(items.get(selectedEquippedSlotID));
//    selectedBagSlotID = freeBagSlot;
//    ((Label) findActor("heroStats")).setText(gdm.hm().getActiveHero(focusedHeroNum).statsText());
//  }
//
//  private void deleteItem() {
//    gdm.bm().updateItem(bag.get(selectedBagSlotID), selectedBagSlotID, 0);
//    addBagItemListener(bag.get(selectedBagSlotID));
//  }
//
//  private Group formationGroup;
//  private Array<Image> formationSlotsGroup;
//  private Array<Image> activeFormationGroup;
//
//  private void formationGroupInit() {
//    formationGroup = new Group();
//    formationGroup.setVisible(false);
//
//    activeFormationGroup = new Array<Image>();
//    formationSlotsGroup = new Array<Image>();
//
//    Image formationFrame = new Image();
//    formationFrame.setDrawable(imagesSkin.getDrawable("EditSlots"));
//    formationFrame.setSize(840, 480);
//    formationFrame.setPosition(0, 0);
//    formationFrame.setName("formationFrame");
//    formationGroup.addActor(formationFrame);
//
//    int x = 421;
//    for (int i = 0; i < 4; i++) {
//      Image activeSlot = new Image(gdm.hm().getActiveHero(i).getInventoryImage());
//      activeSlot.setSize(98, 98);
//      activeSlot.setPosition(x, 357);
//      activeSlot.setName(String.valueOf(i));
//      x -= 130;
//      activeFormationGroup.add(activeSlot);
//      formationGroup.addActor(activeSlot);
//    }
//
//    x = 18;
//    int y = 220;
//    int count = 0;
//    for (int j = 0; j < 3; j++) {
//      for (int i = 0; i < 7; i++) {
//        Image slot = new Image(gdm.hm().getHero(count).getInventoryImage());
//        slot.setSize(98, 98);
//        slot.setPosition(x, y);
//        slot.setName(String.valueOf(count));
//        count++;
//        addDragListeners(slot);
//        formationSlotsGroup.add(slot);
//        formationGroup.addActor(slot);
//        x += 101;
//      }
//      x = 18;
//      y -= 101;
//    }
//    ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
//    style.up = buttonsSkin.getDrawable("CloseButton");
//    style.down = buttonsSkin.getDrawable("CloseButton");
//    style.over = buttonsSkin.getDrawable("CloseButton");
//
//    ImageButton closeFormationGroup = new ImageButton(style);
//    closeFormationGroup.setSize(36, 36);
//    closeFormationGroup.setPosition(804, 444);
//    closeFormationGroup.setName("closeFormationGroup");
//    closeFormationGroup.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        formationGroup.setVisible(false);
//      }
//    });
//    formationGroup.addActor(closeFormationGroup);
//    addActor(formationGroup);
//  }
//
//  private float nativeX;
//  private float nativeY;
//
//  private void addDragListeners(Image image) {
//    if (gdm.hm().getHero(Integer.parseInt(image.getName())).isAvailable()) {
//      image.addListener(new ClickListener() {
//        @Override
//        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//          nativeX = event.getTarget().getX();
//          nativeY = event.getTarget().getY();
//          return super.touchDown(event, x, y, pointer, button);
//        }
//      });
//      image.addListener(new DragListener() {
//        @Override
//        public void drag(InputEvent event, float x, float y, int pointer) {
//          super.drag(event, x, y, pointer);
//          event.getTarget().toFront();
//          event.getAliveHero().moveBy(x - event.getAliveHero().getWidth() / 2, y - event.getAliveHero().getHeight() / 2);
//        }
//
//        @Override
//        public void dragStop(InputEvent event, float x, float y, int pointer) {
//          super.dragStop(event, x, y, pointer);
//          float yStop = event.getTarget().getTop();
//          float xStop = event.getTarget().getRight();
//          System.out.println(formationGroup.hit(xStop,yStop,false).getName());
//          if (yStop >= 407 & yStop <= 507) {
//            if (xStop >= 31 + 50 & xStop <= 31 + 150) {
//              gdm.hm().updateActiveHeroID(3, Integer.parseInt(formationSlotsGroup.get(Integer.parseInt(event.getTarget().getName())).getName()));
//              activeFormationGroup.get(3).setDrawable(gdm.hm().getActiveHero(3).getInventoryImage());
//              ((Image) findActor("hero4")).setDrawable(activeFormationGroup.get(3).getDrawable());
//            } else if (xStop >= 161 + 50 & xStop <= 161 + 150) {
//              gdm.hm().updateActiveHeroID(2, Integer.parseInt(formationSlotsGroup.get(Integer.parseInt(event.getTarget().getName())).getName()));
//              activeFormationGroup.get(3).setDrawable(gdm.hm().getActiveHero(2).getInventoryImage());
//              ((Image) findActor("hero3")).setDrawable(activeFormationGroup.get(2).getDrawable());
//            } else if (xStop >= 291 + 50 & xStop <= 291 + 150) {
//              gdm.hm().updateActiveHeroID(1, Integer.parseInt(formationSlotsGroup.get(Integer.parseInt(event.getTarget().getName())).getName()));
//              activeFormationGroup.get(3).setDrawable(gdm.hm().getActiveHero(1).getInventoryImage());
//              ((Image) findActor("hero2")).setDrawable(activeFormationGroup.get(1).getDrawable());
//            } else if (xStop >= 429 + 50 & xStop <= 429 + 150) {
//              gdm.hm().updateActiveHeroID(0, Integer.parseInt(formationSlotsGroup.get(Integer.parseInt(event.getTarget().getName())).getName()));
//              activeFormationGroup.get(3).setDrawable(gdm.hm().getActiveHero(0).getInventoryImage());
//              ((Image) findActor("hero1")).setDrawable(activeFormationGroup.get(3).getDrawable());
//            }
//          }
//          event.getTarget().setPosition(nativeX, nativeY);
//        }
//      });
//    }
//  }
//
//  private void updateFormationSlots() {
//    for (int i = 0; i < formationSlotsGroup.size; i++) {
//      addDragListeners(formationSlotsGroup.get(i));
//    }
//  }
//}
