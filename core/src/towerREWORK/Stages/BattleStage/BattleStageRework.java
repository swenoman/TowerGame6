//package towerREWORK.Stages.BattleStage;
//
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.utils.Align;
//import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.utils.viewport.StretchViewport;
//
//import towerREWORK.Assets;
//import towerREWORK.Managers.BattleStageManagerRework;
//import towerREWORK.Managers.GameDataManager;
//import towerREWORK.Screens.HomeScreen;
//
//public class BattleStageRework extends Stage {
//  private GameDataManager gdm;
//  private Assets assets;
//  private final BitmapFont font;
//  private final BattleStageManagerRework bsm;
//
//  private Array<Image> heroes;
//  private Array<Image> bots;
//  private Array<Label> hp;
//  private Array<Image> spellsGroup;
//  private Label current_round;
//  private Label infoLabel;
//  private int slotOnAction = 1;
//  private int target;
//  private int roundsCount = 1;
//
//  public BattleStageRework(GameDataManager gdm) {
//    super(new StretchViewport(840, 480));
//    this.gdm = gdm;
//    assets = gdm.assets();
//    font = assets.manager.get(assets.font);
//    slotsInit();
//    bsm = new BattleStageManagerRework(gdm, heroes, bots);
//  }
//
//  private void slotsInit() {
//    Image battleScreenFrame = new Image(new Skin(assets.manager.get(assets.images, TextureAtlas.class)).getDrawable("BattleScreenGUI"));
//    addActor(battleScreenFrame);
//
//    heroes = new Array<>();
//    bots = new Array<>();
//
//    float x = 280;
//    float y = 200;
//    for (int i = 0; i < 4; i++) {
//      Image image = new Image();
//      image.setPosition(x, y);
//      image.setSize(80, 120);
//      x -= 80;
//      heroes.add(image);
//      addActor(image);
//    }
//    x = 480;
//    for (int i = 0; i < 4; i++) {
//      Image image = new Image();
//      image.setPosition(x, y);
//      image.setName(String.valueOf(i + 5));
//      image.setSize(80, 120);
//      x += 80;
//      addBotClickListeners(image);
//      bots.add(image);
//      addActor(image);
//    }
//
//	/*
//		Adding leave button
//	 */
//    ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
//    Skin buttonSkin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
//    style.down = buttonSkin.getDrawable("NoTextButton");
//    style.over = buttonSkin.getDrawable("NoTextButton");
//    style.up = buttonSkin.getDrawable("NoTextButton");
//    style.font = font;
//    ImageTextButton leaveBattle = new ImageTextButton("Leave", style);
//    leaveBattle.setSize(100, 50);
//    leaveBattle.setPosition(730, 420);
//    leaveBattle.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        dispose();
//        gdm.game().setScreen(new HomeScreen(gdm));
//      }
//    });
//    addActor(leaveBattle);
//
//	/*
//	Добавление спелов на экран
//	 */
//    spellsGroup = new Array<>();
//    x = 40;
//    y = 40;
//    for (int i = 0; i < 4; i++) {
//      Image image = new Image(gdm.hm().getActiveHero(0).getSpellImage().get(0));
//      image.setPosition(x, y);
//      image.setSize(80, 80);
//      addSpellsListener(image);
//      spellsGroup.add(image);
//      addActor(image);
//      x += 120;
//    }
//    spellsGroup.get(0).setName("basicAttack");
//    spellsGroup.get(1).setName("spellAttack");
//    spellsGroup.get(2).setName("comboAttack");
//    spellsGroup.get(3).setName("basicAttack");
//
//    Label.LabelStyle labelStyle = new Label.LabelStyle();
//    labelStyle.font = font;
//    labelStyle.fontColor = Color.BLACK;
//
//    current_round = new Label("Round 1", labelStyle);
//    current_round.setAlignment(Align.center);
//    current_round.setPosition(370, 405);
//    current_round.setSize(100, 50);
//    addActor(current_round);
//
//    hp = new Array<>();
//    for (int i = 0; i < 8; i++) {
//      hp.add(new Label("", labelStyle));
//      hp.get(i).setSize(80, 20);
//      hp.get(i).setAlignment(Align.center);
//      addActor(hp.get(i));
//    }
//
//	/*
//	adding InfoLabel
//	 */
//    infoLabel = new Label("Select target!", labelStyle);
//    infoLabel.setSize(200, 50);
//    infoLabel.setPosition(320, 340);
//    infoLabel.setAlignment(Align.center);
//    infoLabel.setVisible(false);
//    addActor(infoLabel);
//
//  }
//
//  public void rounds() {
//    if (!bsm.isBattleEnd()) {
//      if (bsm.isRoundActionsEnd()) {
//        roundsCount++;
//        bsm.newRound();
//        current_round.setText("Round " + roundsCount);
//      }
//      setCurrentSlotAction(bsm.getSlotOnAction());
//      bsm.getSlotAnimation();
//      healthsRender();
//    } else {
//      bsm.getSlotAnimation();
//      healthsRender();
//      // do this later
//      current_round.setText("Battle END" + "\n" + "Winner " + bsm.getWinner() + "\n" + bsm.getRewards());
//    }
//  }
//
//  private void setCurrentSlotAction(int slot) {
//    if (slotOnAction != slot) {
//      slotOnAction = slot;
//      setSpells();
//      setBotAction();
//    }
//  }
//
//  private void setBotAction() {
//    if (slotOnAction > 4) {
//      bsm.setBotAction("comboAttack", bsm.getAlivePlayer());
//    }
//  }
//
//  private void setSpells() {
//    if (slotOnAction < 5)
//      for (int i = 0; i < spellsGroup.size; i++) {
//        spellsGroup.get(i).setDrawable(gdm.hm().getActiveHero(slotOnAction - 1).getSpellImage().get(0));
//        spellsGroup.get(i).setVisible(true);
//      }
//    else {
//      for (int i = 0; i < spellsGroup.size; i++) {
//        spellsGroup.get(i).setVisible(false);
//      }
//    }
//  }
//
//  private void healthsRender() {
//    for (int i = 0; i < 4; i++) {
//      hp.get(i).setPosition(heroes.get(i).getX(), heroes.get(i).getTop());
//      hp.get(i).setText(String.valueOf(bsm.getHp(i)));
//    }
//    for (int i = 4; i < 8; i++) {
//      hp.get(i).setPosition(bots.get(i - 4).getX(), bots.get(i - 4).getTop());
//      hp.get(i).setText(String.valueOf(bsm.getHp(i)));
//    }
//  }
//
//  private void addSpellsListener(final Image spell) {
//    spell.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        if (target == 0 | bsm.getHp(target-1) <= 0) {
//          target = 0;
//          infoLabel.setVisible(true);
//        } else {
//          bsm.setHeroAction(spell.getName(), target);
//        }
//      }
//    });
//  }
//
//  private void addBotClickListeners(final Image bot) {
//    bot.addListener(new ClickListener() {
//      @Override
//      public void clicked(InputEvent event, float x, float y) {
//        super.clicked(event, x, y);
//        target = Integer.parseInt(bot.getName());
//      }
//    });
//  }
//
//  @Override
//  public void dispose() {
//    bsm.newRound();
//    super.dispose();
//  }
//}
//
//
//
