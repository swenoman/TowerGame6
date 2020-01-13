package towerREWORK.Managers;

import towerREWORK.Bots.BotStats;
import towerREWORK.HeroCore.HeroCore;
import towerREWORK.Heroes.Boy;
import towerREWORK.Managers.GameDataManager;

public class BotManager {
  private GameDataManager gdm;
  private HeroCore bot1;
  private HeroCore bot2;
  private HeroCore bot3;
  private HeroCore bot4;

  private BotStats bo1Stats;
  private BotStats bo2Stats;
  private BotStats bo3Stats;
  private BotStats bo4Stats;

  private int coinsReward;
  private int expReward;

  public BotManager(GameDataManager gdm) {
    this.gdm = gdm;
    setCurrentStage(gdm.database().currentStage());
  }

  private void setCurrentStage(int currentStage) {
    switch (currentStage) {
      case 1:
        bot1 = new Boy(gdm, true);
        bot2 = new Boy(gdm, true);
        bot3 = new Boy(gdm, true);
        bot4 = new Boy(gdm, true);
        coinsReward = 50;
        expReward = 10;
        break;
      case 2:
        bot1 = new Boy(gdm, true);
        bot2 = new Boy(gdm, true);
        bot3 = new Boy(gdm, true);
        bot4 = new Boy(gdm, true);
        coinsReward = 80;
        expReward = 20;
        break;
      case 3:
        bot1 = new Boy(gdm, true);
        bot2 = new Boy(gdm, true);
        bot3 = new Boy(gdm, true);
        bot4 = new Boy(gdm, true);
        coinsReward = 100;
        expReward = 30;
        break;
      case 4:
        bot1 = new Boy(gdm, true);
        bot2 = new Boy(gdm, true);
        bot3 = new Boy(gdm, true);
        bot4 = new Boy(gdm, true);
        coinsReward = 150;
        expReward = 40;
        break;
      case 5:
        bot1 = new Boy(gdm, true);
        bot2 = new Boy(gdm, true);
        bot3 = new Boy(gdm, true);
        bot4 = new Boy(gdm, true);
        coinsReward = 180;
        expReward = 50;
        break;
      default:
        bot1 = new Boy(gdm, true);
        bot2 = new Boy(gdm, true);
        bot3 = new Boy(gdm, true);
        bot4 = new Boy(gdm, true);
        coinsReward = 100;
        expReward = 60;
        break;
    }
    bo1Stats = new BotStats(currentStage, bot1.name());
    bo2Stats = new BotStats(currentStage, bot2.name());
    bo3Stats = new BotStats(currentStage, bot3.name());
    bo4Stats = new BotStats(currentStage, bot4.name());
  }

  public HeroCore getBot1() {
    return bot1;
  }

  public int getCoinsReward() {
    return coinsReward;
  }

  public int expReward() {
    return expReward;
  }

  public HeroCore getBot2() {
    return bot2;
  }

  public HeroCore getBot3() {
    return bot3;
  }

  public HeroCore getBot4() {
    return bot4;
  }

  public BotStats getBo1Stats() {
    return bo1Stats;
  }

  public BotStats getBo2Stats() {
    return bo2Stats;
  }

  public BotStats getBo3Stats() {
    return bo3Stats;
  }

  public BotStats getBo4Stats() {
    return bo4Stats;
  }
}

