package towerREWORK.Stages.BattleStage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import towerREWORK.Assets;
import towerREWORK.Managers.BattleManager.BattleManager;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Screens.HomeScreen;
import towerREWORK.Stages.BattleStage.wraps.HeroSlot;

public class BattleStage extends Stage {
  private GameDataManager gdm;
  private Assets assets;
  private final BitmapFont font;
  private final BattleManager manager;

  private Array<Image> team1;
  private Array<Image> team2;
  private Array<Label> hp;
  private Label current_round;
  private HeroSlot target;
  private int roundsCount = 1;

  public BattleStage(GameDataManager gdm) {
    super(new StretchViewport(840, 480));
    this.gdm = gdm;
    assets = gdm.assets();
    font = assets.manager.get(assets.font);
    manager = new BattleManager(gdm);
    slotsInit();
  }

  private void slotsInit() {
    Image battleScreenFrame = new Image(new Skin(assets.manager.get(assets.images, TextureAtlas.class)).getDrawable("BattleScreenGUI"));
    addActor(battleScreenFrame);

    team1 = new Array<>();
    team2 = new Array<>();

    for (int i = 0; i < 4; i++) {
      team1.add(manager.getTeam(1).getHero(i));
      addActor(manager.getTeam(1).getHero(i));
    }
    for (int i = 0; i < 4; i++) {
      addBotClickListeners(manager.getTeam(2).getHero(i));
      team2.add(manager.getTeam(2).getHero(i));
      addActor(manager.getTeam(2).getHero(i));
    }

	/*
		Adding leave button
	 */
    ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
    Skin buttonSkin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
    style.down = buttonSkin.getDrawable("NoTextButton");
    style.over = buttonSkin.getDrawable("NoTextButton");
    style.up = buttonSkin.getDrawable("NoTextButton");
    style.font = font;
    ImageTextButton leaveBattle = new ImageTextButton("Leave", style);
    leaveBattle.setSize(100, 50);
    leaveBattle.setPosition(730, 420);
    leaveBattle.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        dispose();
        gdm.game().setScreen(new HomeScreen(gdm));
      }
    });
    addActor(leaveBattle);

	/*
	Добавление спелов на экран
	 */
    float x = 400;
    float y = 40;
    for (int i = 0; i < 4; i++) {
      Image image = new Image(gdm.hm().getActiveHero(i).getSpellImage());
      image.setPosition(x, y);
      image.setSize(80, 80);
      image.setName(String.valueOf(i));
      addSpellsListener(image);
      addActor(image);
      x -= 120;
    }

    Label.LabelStyle labelStyle = new Label.LabelStyle();
    labelStyle.font = font;
    labelStyle.fontColor = Color.BLACK;

    current_round = new Label("Round 1", labelStyle);
    current_round.setAlignment(Align.center);
    current_round.setPosition(370, 405);
    current_round.setSize(100, 50);
    addActor(current_round);

    hp = new Array<>();
    for (int i = 0; i < 8; i++) {
      hp.add(new Label("", labelStyle));
      hp.get(i).setSize(80, 20);
      hp.get(i).setAlignment(Align.center);
      addActor(hp.get(i));
    }

	/*
	adding InfoLabel
	 */
//    infoLabel = new Label("Select target!", labelStyle);
//    infoLabel.setSize(200, 50);
//    infoLabel.setPosition(320, 340);
//    infoLabel.setAlignment(Align.center);
//    infoLabel.setVisible(false);
//    addActor(infoLabel);
  }

  public void rounds() {
    if (!manager.isBattleEnd()) {
      if (manager.isRoundActionsEnd()) {
        roundsCount++;
        manager.newRound();
        current_round.setText("Round " + roundsCount);
      }
      manager.getTeam(1).setCurrentRound(roundsCount);
      manager.getTeam(2).setCurrentRound(roundsCount);
      manager.getSlotAnimation();
      healthsRender();
    } else {
      manager.getSlotAnimation();
      healthsRender();
      // доделать анимацию окончания боя
      current_round.setText("Battle END" + "\n" + "Winner " + manager.getWinner() + "\n" + manager.getRewards());
    }
  }

  private void healthsRender() {
    for (int i = 0; i < 4; i++) {
      hp.get(i).setPosition(team1.get(i).getX(), team1.get(i).getTop());
      hp.get(i).setText(String.valueOf(manager.getHp(i)));
    }
    for (int i = 4; i < 8; i++) {
      hp.get(i).setPosition(team2.get(i - 4).getX(), team2.get(i - 4).getTop());
      hp.get(i).setText(String.valueOf(manager.getHp(i)));
    }
  }

  private void addSpellsListener(final Image spell) {
    spell.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (target != null && target.isDead()) {
          target = null;
        }
        manager.getTeam(1).addSpellAction(Integer.parseInt(spell.getName()), target);
      }
    });
  }

  private void addBotClickListeners(final Image bot) {
    bot.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        target = (HeroSlot) event.getTarget();
      }
    });
  }

  @Override
  public void dispose() {
    manager.newRound();
    super.dispose();
  }
}



