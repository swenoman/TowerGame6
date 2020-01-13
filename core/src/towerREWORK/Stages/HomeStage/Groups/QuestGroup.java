package towerREWORK.Stages.HomeStage.Groups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import towerREWORK.Managers.GameDataManager;
import towerREWORK.Quests.QuestsManager;

public class QuestGroup extends Group {
  private GameDataManager gdm;
  private QuestsManager manager;
  private int selectedQuest;
  private Label ongoingQuestText;
  private Label quest1;
  private Label quest2;
  private Label quest3;

  public QuestGroup(GameDataManager gameDataManager) {
    gdm = gameDataManager;
    manager = gdm.qm();
    setName("questPage");
    Skin imageSkin = new Skin(gdm.assets().manager.get(gdm.assets().images, TextureAtlas.class));
    Skin buttonSkin = new Skin(gdm.assets().manager.get(gdm.assets().buttons, TextureAtlas.class));
    Image questFrame = new Image();
    questFrame.setDrawable(imageSkin.getDrawable("ChooseBlackSmith"));
    questFrame.setSize(840, 480);
    questFrame.setPosition(0, 0);
    questFrame.setName("questFrame");
    addActor(questFrame);

    ImageTextButton.ImageTextButtonStyle buttonStyle = new ImageTextButton.ImageTextButtonStyle();
    buttonStyle.up = buttonSkin.getDrawable("NoTextButton");
    buttonStyle.down = buttonSkin.getDrawable("NoTextButton");
    buttonStyle.over = buttonSkin.getDrawable("NoTextButton");
    buttonStyle.font = gdm.assets().manager.get(gdm.assets().font);
    buttonStyle.fontColor = Color.BLACK;

    ImageTextButton chooseQuestButton = new ImageTextButton("Accept", buttonStyle);
    chooseQuestButton.setSize(368, 100);
    chooseQuestButton.setPosition(236, 20);
    chooseQuestButton.setName("chooseQuestButton");
    chooseQuestButton.setVisible(false);
    chooseQuestButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        manager.startQuest(selectedQuest, getStage());
        findActor("chooseQuestButton").setVisible(false);
        findActor("ongoingQuest").setVisible(true);
        findActor("ongoingQuest").toFront();
        switch (selectedQuest) {
          case 0:
            ongoingQuestText.setText(quest1.getText());
            break;
          case 1:
            ongoingQuestText.setText(quest2.getText());
            break;
          case 2:
            ongoingQuestText.setText(quest3.getText());
            break;
        }
        ongoingQuestText.setVisible(true);
        ongoingQuestText.toFront();
        findActor("quest0").setVisible(false);
        findActor("quest1").setVisible(false);
        findActor("quest2").setVisible(false);
        quest1.setVisible(false);
        quest2.setVisible(false);
        quest3.setVisible(false);
      }
    });
    addActor(chooseQuestButton);

    ImageTextButton endQuestButton = new ImageTextButton("End", buttonStyle);
    endQuestButton.setSize(368, 100);
    endQuestButton.setPosition(236, 20);
    endQuestButton.setName("endQuestButton");
    endQuestButton.setVisible(false);
    endQuestButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        manager.endQuest(selectedQuest);
        manager.getAvailableQuest(gdm.hm().getActiveHero(0).getRank());
        findActor("endQuestButton").setVisible(false);
        findActor("ongoingQuest").setVisible(false);
        ongoingQuestText.setVisible(false);
        findActor("quest0").setVisible(true);
        findActor("quest1").setVisible(true);
        findActor("quest2").setVisible(true);
        quest1.setVisible(true);
        quest2.setVisible(true);
        quest3.setVisible(true);
      }
    });
    addActor(endQuestButton);

    float x = 36;
    float y = 140;
    for (int i = 0; i < 3; i++) {
      Image quest = new Image();
      quest.setPosition(x, y);
      quest.setSize(200, 200);
      quest.setName("quest" + i);
      final int finalI = i;
      quest.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          super.clicked(event, x, y);
          findActor("chooseQuestButton").setVisible(true);
          selectedQuest = finalI;
        }
      });
      x += 284;
      addActor(quest);
    }

    Image ongoingQuest = new Image();
    ongoingQuest.setVisible(false);
    ongoingQuest.setPosition(320, 140);
    ongoingQuest.setSize(200, 200);
    ongoingQuest.setName("ongoingQuest");
    addActor(ongoingQuest);

    Label.LabelStyle labelStyle = new Label.LabelStyle(gdm.assets().manager.get(gdm.assets().font, BitmapFont.class), Color.BLACK);

    ongoingQuestText = new Label("", labelStyle);
    ongoingQuestText.setVisible(false);
    ongoingQuestText.setTouchable(Touchable.disabled);
    ongoingQuestText.setPosition(320, 140);
    ongoingQuestText.setSize(200, 200);
    addActor(ongoingQuestText);

    quest1 = new Label(manager.getAvailableQuest(gdm.hm().getActiveHero(0).getRank()).get(0).getText(), labelStyle);
    quest1.setTouchable(Touchable.disabled);
    quest1.setPosition(findActor("quest0").getX(), findActor("quest0").getY());
    quest1.setSize(200, 200);

    quest2 = new Label(manager.getAvailableQuest(gdm.hm().getActiveHero(0).getRank()).get(1).getText(), labelStyle);
    quest2.setTouchable(Touchable.disabled);
    quest2.setPosition(findActor("quest1").getX(), findActor("quest1").getY());
    quest2.setSize(200, 200);

    quest3 = new Label(manager.getAvailableQuest(gdm.hm().getActiveHero(0).getRank()).get(2).getText(), labelStyle);
    quest3.setTouchable(Touchable.disabled);
    quest3.setPosition(findActor("quest2").getX(), findActor("quest2").getY());
    quest3.setSize(200, 200);

    addActor(quest1);
    addActor(quest2);
    addActor(quest3);

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
      }
    });
    addActor(closeStoreFrame);
  }

  public void checkQuestsDone() {
    for (int i = 0; i < manager.getQuestQueue().size; i++) {
      manager.getQuestQueue().get(i).ongoing();
    }
  }
}
