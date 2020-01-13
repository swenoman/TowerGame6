package towerREWORK.Stages.HomeStage.Groups.Inventory.EditFormation;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import towerREWORK.Managers.GameDataManager;

public class EditFormation extends Group {
  private GameDataManager gdm;
  private ActiveFormation activeFormation;
  private Heroes heroes;

  public EditFormation(GameDataManager gameDataManager) {
    setName("editFormation");
    this.gdm = gameDataManager;
    Skin imagesSkin = new Skin(gdm.assets().manager.get(gdm.assets().images, TextureAtlas.class));
    Image formationFrame = new Image();
    formationFrame.setDrawable(imagesSkin.getDrawable("EditSlots"));
    formationFrame.setSize(840, 480);
    formationFrame.setPosition(0, 0);
    formationFrame.setName("formationFrame");
    addActor(formationFrame);

    Skin buttonsSkin = new Skin(gdm.assets().manager.get(gdm.assets().buttons, TextureAtlas.class));
    ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
    style.up = buttonsSkin.getDrawable("CloseButton");
    style.down = buttonsSkin.getDrawable("CloseButton");
    style.over = buttonsSkin.getDrawable("CloseButton");

    ImageButton closeFormationGroup = new ImageButton(style);
    closeFormationGroup.setSize(36, 36);
    closeFormationGroup.setPosition(804, 444);
    closeFormationGroup.setName("closeFormationGroup");
    closeFormationGroup.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        setVisible(false);
      }
    });
    addActor(closeFormationGroup);

    activeFormation = new ActiveFormation(gdm);
    heroes = new Heroes(gdm);
    addActor(activeFormation);
    addActor(heroes);
  }
}
