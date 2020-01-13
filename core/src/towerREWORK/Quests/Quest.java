package towerREWORK.Quests;

import com.badlogic.gdx.scenes.scene2d.Stage;

public interface Quest {
  boolean ongoing();
  String getText();
  void start(Stage stage);
  void end();
}
