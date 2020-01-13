package towerREWORK.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import towerREWORK.Assets;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Screens.HomeScreen;

public class FirstHeroChooseStage extends Stage {

  private Assets assets;
  private int selectedHeroId;

  public FirstHeroChooseStage(final GameDataManager gdm) {
    super(new StretchViewport(840, 480));

    this.assets = gdm.assets();
    Skin buttonSkin = new Skin(gdm.assets().manager.get(gdm.assets().buttons, TextureAtlas.class));
    Skin imageSkin = new Skin(gdm.assets().manager.get(gdm.assets().images, TextureAtlas.class));
    ImageTextButton.ImageTextButtonStyle itbs = new ImageTextButton.ImageTextButtonStyle();
    itbs.up = buttonSkin.getDrawable("NoTextButton");
    itbs.down = buttonSkin.getDrawable("NoTextButton");
    itbs.over = buttonSkin.getDrawable("NoTextButton");
    itbs.font = assets.manager.get(assets.font);
    itbs.fontColor = Color.BLACK;

    Image chooseBlacksmithFrame = new Image();
    chooseBlacksmithFrame.setDrawable(imageSkin.getDrawable("ChooseBlackSmith"));
    chooseBlacksmithFrame.setSize(840, 480);
    chooseBlacksmithFrame.setPosition(0, 0);
    chooseBlacksmithFrame.setName("chooseBlacksmithFrame");
    addActor(chooseBlacksmithFrame);

    ImageTextButton chooseBlacksmithButton = new ImageTextButton("ChooseBlacksmith", itbs);
    chooseBlacksmithButton.setSize(368, 100);
    chooseBlacksmithButton.setPosition(236, 20);
    chooseBlacksmithButton.setName("chooseBlacksmithButton");
    chooseBlacksmithButton.setVisible(false);
    chooseBlacksmithButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        gdm.database().updateHeroAvailable(0, false);
        gdm.database().updateHeroAvailable(1, false);
        gdm.database().updateHeroAvailable(2, false);
        gdm.hm().updateActiveHeroID(0, selectedHeroId);
        gdm.database().updateHeroAvailable(selectedHeroId, true);
        gdm.game().setScreen(new HomeScreen(gdm));
        dispose();
      }
    });
    addActor(chooseBlacksmithButton);

    float x = 36;
    float y = 140;
    for (int i = 0; i < 3; i++) {
      Image firstHero = new Image();
      firstHero.setDrawable(gdm.hm().getHero(i).getInventoryImage());
      firstHero.setPosition(x, y);
      firstHero.setSize(200, 200);
      firstHero.setName(String.valueOf(i + 1));
      firstHero.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          selectedHeroId = Integer.parseInt(event.getTarget().getName());
          if (getRoot().findActor("chooseBlacksmithButton").isVisible())
            getRoot().findActor("chooseBlacksmithButton").setVisible(false);
          else
            getRoot().findActor("chooseBlacksmithButton").setVisible(true);
        }
      });
      x += 284;
      addActor(firstHero);
    }
  }
}
