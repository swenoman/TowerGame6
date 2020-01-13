package towerREWORK.Stages.HomeStage.Groups.Inventory;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import towerREWORK.Assets;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Managers.HeroesManager;
import towerREWORK.Stages.HomeStage.Groups.Inventory.EditFormation.ActiveFormation;
import towerREWORK.Stages.HomeStage.Groups.Inventory.EditFormation.EditFormation;

public class InventoryRoot extends Group {
  private GameDataManager gdm;
  private Skin imagesSkin;
  private Skin buttonsSkin;
  private BitmapFont font;
  private Assets assets;
  private HeroesManager hm;
  private ArmedItemsGroup armedItems;
  private BagGroup bag2;
  private EditFormation editFormation;
  private FormationGroup formation;


  public InventoryRoot(GameDataManager gdm) {
    setName("inventoryRoot");
    this.gdm = gdm;
    this.assets = gdm.assets();
    hm = gdm.hm();
    imagesSkin = new Skin(assets.manager.get(assets.images, TextureAtlas.class));
    buttonsSkin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
    font = assets.manager.get(assets.font, BitmapFont.class);

    playerInventoryInit();

    formation = new FormationGroup(gdm, this);
    addActor(formation);
    bag2 = new BagGroup(gdm, this);
    bag2.setPosition(378, 210);
    addActor(bag2);
    armedItems = new ArmedItemsGroup(gdm, this);
    armedItems.setPosition(411, 402);
    addActor(armedItems);
    editFormation = new EditFormation(gdm);
    editFormation.setVisible(false);
    addActor(editFormation);
  }

  private void playerInventoryInit() {

    Image frameImage = new Image(imagesSkin.getDrawable("PlayerMenu"));
    frameImage.setSize(840, 480);
    frameImage.setPosition(0, 0);
    frameImage.setName("frameImage");
    addActor(frameImage);


    ImageButton closeFrameButton = new ImageButton(buttonsSkin.getDrawable("CloseButton"));
    closeFrameButton.setSize(36, 36);
    closeFrameButton.setPosition(804, 444);
    closeFrameButton.setName("closeFrameButton");
    closeFrameButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        event.getTarget().getParent().getParent().setVisible(false);
      }
    });
    addActor(closeFrameButton);

    ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
    Skin buttonsSkin = new Skin(assets.manager.get(assets.buttons, TextureAtlas.class));
    style.up = buttonsSkin.getDrawable("NoTextButton");
    style.down = buttonsSkin.getDrawable("NoTextButton");
    style.over = buttonsSkin.getDrawable("NoTextButton");
    style.font = assets.manager.get(assets.font);

    ImageTextButton formationGroupButton = new ImageTextButton("Edit Formation", style);
    formationGroupButton.setSize(68, 48);
    formationGroupButton.setPosition(299, 418);
    formationGroupButton.setName("formationGroupButton");
    formationGroupButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
//        updateFormationSlots();
//        formationGroup.setVisible(true);
//        formationGroup.toFront();
        editFormation.setVisible(true);
        editFormation.toFront();
      }
    });
    addActor(formationGroupButton);
  }
}
