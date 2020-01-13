package towerREWORK.Stages.HomeStage.Groups.Inventory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import towerREWORK.Assets;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Managers.HeroesManager;

public class FormationGroup extends Group {

  private HeroesManager manager;
  private Skin imagesSkin;
  private GameDataManager gdm;
  private Assets assets;
  private int selectedHeroId = 0;
  private InventoryRoot root;

  public FormationGroup(GameDataManager gdm, final InventoryRoot root) {
    setName("formation");
    this.gdm = gdm;
    manager = gdm.hm();
    assets = gdm.assets();
    this.root = root;
    imagesSkin = new Skin(assets.manager.get(assets.images, TextureAtlas.class));

    Image focusedHeroImage = new Image(manager.getActiveHero(selectedHeroId).getInventoryImage());
    focusedHeroImage.setSize(353, 403);
    focusedHeroImage.setPosition(15, 15);
    focusedHeroImage.setName("focusedHeroImage");
    focusedHeroImage.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        event.getTarget().setVisible(false);
        ((Label) findActor("heroStats")).setText(manager.getActiveHero(selectedHeroId).statsText());
        findActor("heroStats").setVisible(true);
        findActor("heroStats").toFront();
      }
    });
    addActor(focusedHeroImage);

    Label.LabelStyle ls = new Label.LabelStyle(assets.manager.get(assets.font, BitmapFont.class), Color.BLACK);

    Label heroStats = new Label("", ls);
    heroStats.setAlignment(Align.center);
    heroStats.setSize(353, 403);
    heroStats.setText(gdm.hm().getActiveHero(selectedHeroId).statsText());
    heroStats.setVisible(false);
    heroStats.setName("heroStats");
    heroStats.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        event.getTarget().setVisible(false);
        findActor("focusedHeroImage").setVisible(true);
      }
    });
    addActor(heroStats);

    Label itemStats = new Label("", ls);
    itemStats.setVisible(false);
    itemStats.setSize(353, 100);
    itemStats.setPosition(15, 15);
    itemStats.setAlignment(Align.center);
    itemStats.setName("itemStats");
    itemStats.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        event.getTarget().setVisible(false);
      }
    });
    addActor(itemStats);

    Image hero4 = new Image(manager.getActiveHero(3).getInventoryImage());
    hero4.setPosition(15, 418);
    hero4.setSize(68, 48);
    hero4.setName("hero4");
    hero4.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (manager.getActiveHero(3).id() != -1) {
          ((Group) root.findActor("bag")).findActor("equip").setVisible(false);
          ((Group) root.findActor("bag")).findActor("delete").setVisible(false);
          ((Group) root.findActor("armedItems")).findActor("unequip").setVisible(false);
          selectedHeroId = 3;
          setHeroActors();
        }
      }
    });
    Image hero3 = new Image(manager.getActiveHero(2).getInventoryImage());
    hero3.setPosition(86, 418);
    hero3.setName("hero3");
    hero3.setSize(68, 48);
    hero3.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (manager.getActiveHero(2).id() != -1) {
          ((Group) root.findActor("bag")).findActor("equip").setVisible(false);
          ((Group) root.findActor("bag")).findActor("delete").setVisible(false);
          ((Group) root.findActor("armedItems")).findActor("unequip").setVisible(false);
          selectedHeroId = 2;
          setHeroActors();
        }
      }
    });
    Image hero2 = new Image(manager.getActiveHero(1).getInventoryImage());
    hero2.setPosition(157, 418);
    hero2.setName("hero2");
    hero2.setSize(68, 48);
    hero2.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (manager.getActiveHero(1).id() != -1) {
          ((Group) root.findActor("bag")).findActor("equip").setVisible(false);
          ((Group) root.findActor("bag")).findActor("delete").setVisible(false);
          ((Group) root.findActor("armedItems")).findActor("unequip").setVisible(false);
          selectedHeroId = 1;
          setHeroActors();
        }
      }
    });
    Image hero1 = new Image(manager.getActiveHero(0).getInventoryImage());
    hero1.setPosition(228, 418);
    hero1.setName("hero1");
    hero1.setSize(68, 48);
    hero1.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (manager.getActiveHero(0).id() != -1) {
          ((Group) root.findActor("bag")).findActor("equip").setVisible(false);
          ((Group) root.findActor("bag")).findActor("delete").setVisible(false);
          ((Group) root.findActor("armedItems")).findActor("unequip").setVisible(false);
          selectedHeroId = 0;
          setHeroActors();
        }
      }
    });
    addActor(hero1);
    addActor(hero2);
    addActor(hero3);
    addActor(hero4);

    ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
    Skin buttonsSkin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
    style.up = buttonsSkin.getDrawable("NoTextButton");
    style.down = buttonsSkin.getDrawable("NoTextButton");
    style.over = buttonsSkin.getDrawable("NoTextButton");
    style.font = assets.manager.get(assets.font);
  }

  private void setHeroActors() {
    ((Image) findActor("focusedHeroImage")).setDrawable(manager.getActiveHero(selectedHeroId).getInventoryImage());
    ((Label) findActor("heroStats")).setText(manager.getActiveHero(selectedHeroId).statsText());
    ((ArmedItemsGroup) root.findActor("armedItems")).setHeroItems(selectedHeroId);
  }

  public int getSelectedHeroID() {
    return selectedHeroId;
  }
}
