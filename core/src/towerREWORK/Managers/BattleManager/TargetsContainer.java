package towerREWORK.Managers.BattleManager;

import towerREWORK.Stages.BattleStage.wraps.HeroSlot;

public class TargetsContainer {
  private HeroSlot attacker;
  private HeroSlot target;

  public TargetsContainer() {
  }

  public void put(HeroSlot attacker, HeroSlot target) {
    this.attacker = attacker;
    this.target = target;
  }

  public HeroSlot getAttacker() {
    return attacker;
  }

  public HeroSlot getTarget() {
    return target;
  }
}
