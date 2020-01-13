package towerREWORK.Managers;

public class StageManager {
  private GameDataManager gdm;
  private int currentStage;
  private int maxStage;

  public StageManager(GameDataManager gdm) {
    this.gdm = gdm;
    currentStage = gdm.database().currentStage();
    maxStage = gdm.database().maxStage();
  }

  public int currentStage() {
    return currentStage;
  }

  public int maxStage() {
    return maxStage;
  }

  public void updateCurrentStage(final int stageNum) {
    this.currentStage = stageNum;
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateCurrentStage(stageNum);
      }
    }).start();
  }

  public void updateMaxStage() {
    maxStage++;
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateMaxStage(maxStage);
      }
    }).start();
  }
}
