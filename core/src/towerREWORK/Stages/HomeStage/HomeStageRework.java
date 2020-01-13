package towerREWORK.Stages.HomeStage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import towerREWORK.Assets;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Screens.BattleScreen;
import towerREWORK.Stages.HomeStage.Groups.BlacksmithStore;
import towerREWORK.Stages.HomeStage.Groups.Inventory.InventoryRoot;
import towerREWORK.Stages.HomeStage.Groups.QuestGroup;

public class HomeStageRework extends Stage {

  private GameDataManager gdm;
  private Assets assets;
  private BitmapFont font;
  public Label coins;
  private final Table table = new Table();
  private InventoryRoot inventoryRoot;
  private Group blacksmithStoreManager;
  private QuestGroup quests;

  public HomeStageRework(GameDataManager gdm) {
    super(new StretchViewport(840, 480));
    this.assets = gdm.assets();
    this.gdm = gdm;
    this.font = assets.manager.get(assets.font, BitmapFont.class);
    homeStageInit();
  }

  private void homeStageInit() {
	/*
	Инвентарь
	 */
    inventoryRoot = new InventoryRoot(gdm);
    System.out.println(inventoryRoot.getName());
    inventoryRoot.setVisible(false);
    addActor(inventoryRoot);
	/*
	Магазин
	 */
    blacksmithStoreManager = new BlacksmithStore(gdm);
    blacksmithStoreManager.setVisible(false);
    addActor(blacksmithStoreManager);
    /*
 Задания
     */
    quests = new QuestGroup(gdm);
    quests.setVisible(false);
    addActor(quests);
    /*
    Кнопки
     */
    ImageTextButton inventoryButton = imageTBF("NoTextButton", "Inventory", 100, 50, 730, 420);
    inventoryButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
//        inventoryRoot.updateBag();
        inventoryRoot.setVisible(true);
        inventoryRoot.toFront();
      }
    });
    this.addActor(inventoryButton);

    ImageTextButton store = imageTBF("NoTextButton", "BlacksmithStoreManager", 100, 50, 10, 320);
    store.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        blacksmithStoreManager.setVisible(true);
        blacksmithStoreManager.toFront();
      }
    });
    addActor(store);

    ImageTextButton battle = imageTBF("NoTextButton", "ChoseStage", 100, 50, 730, 310);
    battle.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (!table.isVisible())
          table.setVisible(true);
        else
          table.setVisible(false);
      }
    });
    addActor(battle);

    ImageTextButton questsButton = imageTBF("NoTextButton", "Quests", 100, 50, 10, 250);
    questsButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        quests.setVisible(true);
        quests.toFront();
      }
    });
    addActor(questsButton);

    coins = gdm.cm().getLabel();
    coins.setAlignment(Align.center);
    coins.setSize(50, 20);
    coins.setPosition(600, 420);
    coins.toFront();
    addActor(coins);

    ImageTextButton explore = imageTBF("NoTextButton", "Explore", 100, 50, 730, 250);
    explore.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
//                getExploreEvents((HomeStageRework) event.getStage());
      }
    });
    addActor(explore);

    // Scroll Table

    ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
    style.font = font;
    style.over = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class)).getDrawable("NoTextButton");
    style.down = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class)).getDrawable("NoTextButton");
    style.up = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class)).getDrawable("NoTextButton");

    final Table scrollTable = new Table();

    for (int i = 0; i < gdm.sm().maxStage(); i++) {
      System.out.println("переделываем таблицу" + gdm.sm().maxStage());
      ImageTextButton button = new ImageTextButton("Stage " + (i + 1), style);
      button.setSize(100, 100);
      button.setPosition(0, 0);
      final int finalI = i;
      button.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          gdm.sm().updateCurrentStage(finalI + 1);
          dispose();
          gdm.game().setScreen(new BattleScreen(gdm));
        }
      });
      scrollTable.add(button);
      scrollTable.row();
    }
    final ScrollPane scroller = new ScrollPane(scrollTable);
    table.setSize(400, 480);
    table.add(scroller).fill().expand();
    table.setVisible(false);
    addActor(table);

    ImageTextButton reset = new ImageTextButton("reset ", style);
    reset.setSize(100, 100);
    reset.setPosition(0, 0);
    reset.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        addActor(new Label("reseting", new Label.LabelStyle(font, Color.BLACK)));
        new Thread(new Runnable() {
          @Override
          public void run() {
            gdm.database().setFirstTry(0);
            gdm.game().restart();
          }
        }).start();
      }
    });
    addActor(reset);
  }

  private ImageTextButton imageTBF(String name, String text, float width, float height, float x, float y) {
    Skin skin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
    ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
    style.down = skin.getDrawable(name);
    style.up = skin.getDrawable(name);
    style.over = skin.getDrawable(name);
    style.font = font;

    ImageTextButton button = new ImageTextButton(text, style);
    button.setSize(width, height);
    button.setPosition(x, y);
    return button;
  }

  public void checkQuestsDone() {
    quests.checkQuestsDone();
  }

  public void addCoins(int coins) {
    gdm.cm().updateCoins(coins);
  }

  @Override
  public void dispose() {
    super.dispose();
  }
}
