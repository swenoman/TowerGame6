package towerREWORK.Managers.BattleManager;

import towerREWORK.Managers.BotManager;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Stages.BattleStage.wraps.HeroSlot;

public class BattleManager {
  private BotManager bm;
  private GameDataManager gdm;
  private Team team1;
  private Team team2;
  private boolean roundEnd = false;
  private HeroSlot team1Target = null;
  private HeroSlot team2Target = null;
  private HeroSlot basicAttackTarget;
  private boolean battleEnd = false;

  public BattleManager(GameDataManager gdm) {
    this.gdm = gdm;
    bm = new BotManager(gdm);
    team1 = new Team(gdm.hm().getActiveHero(0), gdm.hm().getActiveHero(1), gdm.hm().getActiveHero(2), gdm.hm().getActiveHero(3), 1);
    team2 = new Team(bm.getBot1(), bm.getBot2(), bm.getBot3(), bm.getBot4(), 2);
  }

  public void getSlotAnimation() {
    if (!battleEnd)
      updateActionSlot();
    team1.getAnimations();
    team2.getAnimations();
  }

  private Team basicAttack;
  private Team spellQueue;

  private void updateActionSlot() {
    if (team1.spellActionQueue().size != 0) {
      if (team2.spellActionQueue().size != 0) {
        setUpSpellQueue();
      } else spellQueue = team1;
    } else if (team2.spellActionQueue().size != 0) {
      spellQueue = team2;
    } else spellQueue = null;
    if (isActionsEnd()) {
      if (spellQueue != null) {
        if (spellQueue == team1) {
          if (team1Target == null || team1Target.isDead()) {
            team1Target = team2.getAliveHero();
          }
          spellQueue.setSpellAttack(team1Target);
        } else {
          if (team2Target != null && team2Target.isDead())
            team2Target = null;
          team2Target = team1.getAliveHero();
          spellQueue.setSpellAttack(team2Target);
        }
      } else {
        setUpBasicQueue();
        if (basicAttack.equals(team1)) {
          basicAttackTarget = team2.getAliveHero();
          basicAttack.setBasicAttack(basicAttackTarget);
        } else {
          basicAttackTarget = team1.getAliveHero();
          basicAttack.setBasicAttack(basicAttackTarget);
        }
      }
    }
    if (team1.isTargetAttacked()) {
      System.out.println("heroAttacked");
      team2.attackHero(team1.getActionSlot());
    }
    if (team2.isTargetAttacked()) {
      team1.attackHero(team2.getActionSlot());
    }
    if (team1.getHeroUnderComboState() != null) {
      switch (team1.getHeroUnderComboState().getCurrentState()) {
        case "highFloated":
          team2.findCombo("highFloated", team1.getHeroUnderComboState());
          break;
        case "lowFloated":
          team2.findCombo("lowFloated", team1.getHeroUnderComboState());
          break;
      }
    }
    if (team2.getHeroUnderComboState() != null) {
      switch (team2.getHeroUnderComboState().getCurrentState()) {
        case "highFloated":
          team1.findCombo("highFloated", team2.getHeroUnderComboState());
          break;
        case "lowFloated":
          team1.findCombo("lowFloated", team2.getHeroUnderComboState());
          break;
      }
    }
  }

  private void setUpSpellQueue() {
    spellQueue = team1;
    if (spellQueue.getHeroPosition(spellQueue.spellActionQueue().first().getAttacker()) >
            team2.getHeroPosition(team2.spellActionQueue().first().getAttacker())) {
      spellQueue = team2;
    } else if (spellQueue.getHeroPosition(spellQueue.spellActionQueue().first().getAttacker()) ==
            team2.getHeroPosition(team2.spellActionQueue().first().getAttacker())) {
      if (spellQueue.spellActionQueue().first().getAttacker().speed() <
              team2.spellActionQueue().first().getAttacker().speed()) {
        spellQueue = team2;
      }
    }
  }

  private void setUpBasicQueue() {
    basicAttack = team1;
    if (basicAttack.basicAttackQueue().first().isWasActionInThisRound() & team2.basicAttackQueue().first().isWasActionInThisRound()) {
      roundEnd = true;
    } else {
      if (basicAttack.getHeroPosition(basicAttack.basicAttackQueue().first()) >
              team2.getHeroPosition(team2.basicAttackQueue().first())) {
        basicAttack = team2;
      } else if (basicAttack.getHeroPosition(basicAttack.basicAttackQueue().first()) ==
              team2.getHeroPosition(team2.basicAttackQueue().first())) {
        if (basicAttack.basicAttackQueue().first().speed() <
                team2.basicAttackQueue().first().speed()) {
          basicAttack = team2;
        }
      }
      if (basicAttack.equals(team1)) {
        if (basicAttack.basicAttackQueue().first().isWasActionInThisRound()) {
          basicAttack = team2;
        }
      } else if (basicAttack.basicAttackQueue().first().isWasActionInThisRound()) {
        basicAttack = team1;
      }
    }
  }

  public boolean isRoundActionsEnd() {
    return roundEnd;
  }

  private boolean isActionsEnd() {
    return team1.isActionsEnd() & team2.isActionsEnd();
  }

  public void newRound() {
    roundEnd = false;
    team1.newRound();
    team2.newRound();
  }

  private String winner;
  private boolean rewardsUpdated = false;

  public boolean isBattleEnd() {
    if (team1.dead()) {
      winner = "bots";
      battleEnd = true;
      return true;
    }
    if (team2.dead() && !rewardsUpdated) {
      System.out.println("UPDATE REWARDS");
      battleEnd = true;
      rewardsUpdated = true;
      winner = "player";
      gdm.cm().updateCoins(bm.getCoinsReward());
      for (int i = 0; i < 4; i++) {
        gdm.hm().getHero(gdm.hm().getActiveHero(i).id()).updateLevel(bm.expReward());
      }
      if (gdm.database().currentStage() == gdm.database().maxStage()) {
        gdm.sm().updateMaxStage();
      }
      return true;
    }
    return false;
  }

  public String getRewards() {
    return "Coins: " + bm.getCoinsReward() + "Exp: " + bm.expReward();
  }

  public Integer getHp(int slot) {
    if (slot < 4) {
      return team1.getHero(slot).getHealth();
    } else return team2.getHero(slot - 4).getHealth();
  }

  public String getWinner() {
    return winner;
  }

  public Team getTeam(int num) {
    if (num == 1) {
      return team1;
    } else return team2;
  }

}
