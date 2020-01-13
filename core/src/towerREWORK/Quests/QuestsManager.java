package towerREWORK.Quests;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import towerREWORK.Quests.novice.Quest1;
import towerREWORK.Quests.novice.Quest2;
import towerREWORK.Quests.novice.Quest3;

public class QuestsManager {

  private Array<Quest> availableQuests;
  private Array<Quest> questsQueue;

  public QuestsManager() {
    availableQuests = new Array<>();
    questsQueue = new Array<>();
  }
// когда будешь больше квестов, сделать выдачу квеста рандомной
  public Array<Quest> getAvailableQuest(String rank) {
    availableQuests.clear();
    switch (rank) {
      case "novice":
        availableQuests.add(new Quest1());
        availableQuests.add(new Quest2());
        availableQuests.add(new Quest3());
        break;
    }
    return availableQuests;
  }

  public void startQuest(int num, Stage stage) {
    questsQueue.add(availableQuests.get(num));
    questsQueue.get(questsQueue.indexOf(availableQuests.get(num), true)).start(stage);
  }

  public Array<Quest> getQuestQueue() {
    return questsQueue;
  }

  public void endQuest(int selectedQuest) {
    questsQueue.get(questsQueue.indexOf(availableQuests.get(selectedQuest), true)).end();
    questsQueue.removeIndex(questsQueue.indexOf(availableQuests.get(selectedQuest), true));
  }
}
