package towerREWORK.Stages;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import towerREWORK.Assets;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Screens.FirstHeroChooseScreen;
import towerREWORK.Screens.HomeScreen;

public class MainMenuStage extends Stage {

  private Assets assets;

  public MainMenuStage(final GameDataManager gdm) {
    super(new StretchViewport(840, 480));
    this.assets = gdm.assets();
    ImageTextButton newGame = buttonFactory(assets.manager.get(assets.font, BitmapFont.class), "NoTextButton", "New Game", 100, 50, 50, 150);
    newGame.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (gdm.isFirstTry()) {
          gdm.database().setFirstTry(1);
          gdm.game().setScreen(new FirstHeroChooseScreen(gdm));
        }
        else gdm.game().setScreen(new HomeScreen(gdm));
      }
    });
    ImageTextButton exit = buttonFactory(assets.manager.get(assets.font, BitmapFont.class), "NoTextButton", "Exit", 100, 50, 50, 50);
    exit.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        gdm.game().dispose();
      }
    });
    this.addActor(newGame);
    this.addActor(exit);
  }

  private ImageTextButton buttonFactory(BitmapFont font, String name, String text, float width, float height, float x, float y) {

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
}
