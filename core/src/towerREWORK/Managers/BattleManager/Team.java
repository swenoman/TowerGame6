package towerREWORK.Managers.BattleManager;

import com.badlogic.gdx.utils.Array;

import towerREWORK.HeroCore.HeroCore;
import towerREWORK.Stages.BattleStage.wraps.HeroSlot;

public class Team {
  private HeroSlot hero1;
  private HeroSlot hero2;
  private HeroSlot hero3;
  private HeroSlot hero4;
  private Array<HeroSlot> team;
  private Array<HeroSlot> basicActionQueue;
  private Array<TargetsContainer> spellActionQueue;
  private Array<HeroSlot> extraActionQueue;
  private boolean reSetUpSpellStackQueue = false;
  private HeroSlot heroUnderComboState;
  private HeroSlot actionSlot;
  private int currentRound;

  public Team(HeroCore first, HeroCore second, HeroCore third, HeroCore fourth, int teamNum) {
    hero1 = new HeroSlot(first);
    hero2 = new HeroSlot(second);
    hero3 = new HeroSlot(third);
    hero4 = new HeroSlot(fourth);
    team = new Array<>();
    team.add(hero1, hero2, hero3, hero4);
    basicActionQueue = new Array<>();
    spellActionQueue = new Array<>();
    extraActionQueue = new Array<>();
    basicActionQueue.add(hero1, hero2, hero3, hero4);

    if (teamNum == 1) {
      hero1.setStartData(280, true);
      hero2.setStartData(200, true);
      hero3.setStartData(120, true);
      hero4.setStartData(40, true);
    } else {
      hero1.setStartData(480, false);
      hero2.setStartData(560, false);
      hero3.setStartData(640, false);
      hero4.setStartData(720, false);
    }
  }

  public void setCurrentRound(int currentRound) {
    this.currentRound = currentRound;
  }

  public HeroSlot getHero(int i) {
    switch (i) {
      case 0:
        return hero1;
      case 1:
        return hero2;
      case 2:
        return hero3;
      case 3:
        return hero4;
      default:
        return null;
    }
  }

  public void getAnimations() {
    hero1.getAnimation();
    hero2.getAnimation();
    hero3.getAnimation();
    hero4.getAnimation();
  }


  public boolean isActionsEnd() {
    return hero1.actionEnd() & hero2.actionEnd() & hero3.actionEnd() & hero4.actionEnd();
  }

  public void newRound() {
    hero1.newRound();
    hero2.newRound();
    hero3.newRound();
    hero4.newRound();
  }

  public Array<HeroSlot> basicAttackQueue() {
    if (basicActionQueue.first().isDead()) {
      basicActionQueue.add(basicActionQueue.first());
      basicActionQueue.removeIndex(0);
      if (basicActionQueue.first().isDead()) {
        basicAttackQueue();
      }
    }
    return basicActionQueue;
  }


  public Array<TargetsContainer> spellActionQueue() {
    for (int i = 0; i < spellActionQueue.size; i++) {
      if (spellActionQueue.get(i).getAttacker().isDead())
        spellActionQueue.removeIndex(i);
    }
    return spellActionQueue;
  }

  public Array<HeroSlot> extraActionQueue() {
    return extraActionQueue;
  }

  public boolean dead() {
    for (int i = 0; i < team.size; i++) {
      if (!team.get(i).isDead()) {
        return false;
      }
    }
    return true;
  }

  public void addSpellAction(int heroNum, HeroSlot target) {
    TargetsContainer container = new TargetsContainer();
    container.put(team.get(heroNum), target);
    spellActionQueue.add(container);
    setUpSpellStackQueue();
  }

  private void setUpSpellStackQueue() {
    for (int i = 0; i < spellActionQueue.size; i++) {
      if (i != spellActionQueue.size - 1 && getHeroPosition(spellActionQueue.get(i).getAttacker()) > getHeroPosition(spellActionQueue.get(i + 1).getAttacker())) {
        spellActionQueue.swap(i, i + 1);
        reSetUpSpellStackQueue = true;
      } else reSetUpSpellStackQueue = false;
    }
    if (reSetUpSpellStackQueue) {
      setUpSpellStackQueue();
    }
  }

  public HeroSlot getAliveHero() {
    for (int i = 0; i < team.size; i++) {
      if (!team.get(i).isDead())
        return team.get(i);
    }
    return null;
  }


  public int getHeroPosition(HeroSlot hero) {
    return team.indexOf(hero, true);
  }

  public void setSpellAttack(HeroSlot target) {
    if (spellActionQueue.first().getAttacker().isSpellAvailable(currentRound))
      if (spellActionQueue.first().getTarget() != null && !spellActionQueue.first().getTarget().isDead()) {
        spellActionQueue.first().getAttacker().setAction("spellAttack", spellActionQueue.first().getTarget());
        spellActionQueue.first().getAttacker().startSpellCooldown(currentRound);
      } else {
        spellActionQueue.first().getAttacker().setAction("spellAttack", target);
        spellActionQueue.first().getAttacker().startSpellCooldown(currentRound);
      }
    spellActionQueue.removeIndex(0);
  }

  public HeroSlot getHeroUnderComboState() {
    for (int i = 0; i < 4; i++) {
      if (team.get(i).getCurrentState().equals("highFloated") || team.get(i).getCurrentState().equals("lowFloated")) {
        heroUnderComboState = team.get(i);
        return heroUnderComboState;
      } else heroUnderComboState = null;
    }
    return null;
  }

  public void findCombo(String state, HeroSlot target) {
    if (isChaseEnd()) {
      switch (state) {
        case "highFloated":
          for (int i = 0; i < team.size; i++) {
            if (team.get(i).haveChase("chaseHighFloat")) {
              team.get(i).setAction("chaseHighFloat", target);
              return;
            }
          }
          break;
        case "lowFloated":
          for (int i = 0; i < team.size; i++) {
            if (team.get(i).haveChase("chaseLowFloat")) {
              team.get(i).setAction("chaseLowFloat", target);
              return;
            }
          }
          break;
      }
    }
  }

  public boolean isTargetAttacked() {
    getActionSlot();
    return actionSlot != null && actionSlot.isTargetAttacked();
  }

  public void attackHero(HeroSlot attacker) {
    HeroSlot target = attacker.getGlobalTarget();
    target.setAction(attacker.getStateAfterAttack());
    target.setHealth(attacker.getPhysicDamage());
  }

  private boolean isChaseEnd() {
    return actionSlot == null || actionSlot.chaseEnd();
  }

  public HeroSlot getActionSlot() {
    if (team.get(0).getCurrentState().equals("basicAttack") ||
            team.get(0).getCurrentState().equals("spellAttack") ||
            team.get(0).getCurrentState().equals("chaseHighFloat") ||
            team.get(0).getCurrentState().equals("chaseLowFloat")) {
      actionSlot = team.get(0);
    } else if (team.get(1).getCurrentState().equals("basicAttack") ||
            team.get(1).getCurrentState().equals("spellAttack") ||
            team.get(1).getCurrentState().equals("chaseHighFloat") ||
            team.get(1).getCurrentState().equals("chaseLowFloat")) {
      actionSlot = team.get(1);
    } else if (team.get(2).getCurrentState().equals("basicAttack") ||
            team.get(2).getCurrentState().equals("spellAttack") ||
            team.get(2).getCurrentState().equals("chaseHighFloat") ||
            team.get(2).getCurrentState().equals("chaseLowFloat")) {
      actionSlot = team.get(2);
    } else if (team.get(3).getCurrentState().equals("basicAttack") ||
            team.get(3).getCurrentState().equals("spellAttack") ||
            team.get(3).getCurrentState().equals("chaseHighFloat") ||
            team.get(3).getCurrentState().equals("chaseLowFloat")) {
      actionSlot = team.get(3);
    } else actionSlot = null;
    return actionSlot;
  }

  public void setBasicAttack(HeroSlot target) {
    basicActionQueue.first().setAction("basicAttack", target);
    basicActionQueue.add(basicActionQueue.first());
    basicActionQueue.removeIndex(0);
  }

  public boolean isRoundsActionEnd() {
    return team.get(0).isWasActionInThisRound()
            & team.get(1).isWasActionInThisRound()
            & team.get(2).isWasActionInThisRound()
            & team.get(3).isWasActionInThisRound();
  }
}
