package towerREWORK.Quests.novice;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import towerREWORK.Quests.Quest;
import towerREWORK.Stages.HomeStage.HomeStageRework;

public class Quest1 implements Quest {
  private boolean isDone = false;
  private HomeStageRework stage;
  private Actor inventory;

  public Quest1() {
    // определить награду для поднятия ранга
  }

  public boolean ongoing() {
    if (inventory.isVisible() & !isDone) {
      ((Group) stage.getRoot().findActor("questPage")).findActor("endQuestButton").setVisible(true);
      isDone = true;
    }
    return isDone;
  }

  public String getText() {
    return "Open your inventory";
  }

  public void start(Stage stage) {
    this.stage = (HomeStageRework) stage;
    inventory = stage.getRoot().findActor("inventoryRoot");
  }

  public void end() {
    stage.addCoins(1000);
    isDone = false;
  }
}
