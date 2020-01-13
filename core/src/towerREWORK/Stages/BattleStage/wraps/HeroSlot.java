package towerREWORK.Stages.BattleStage.wraps;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import towerREWORK.HeroCore.HeroCore;

public class HeroSlot extends Image {

  private String currentAction = "idle";
  private String nextAction = null;
  private HeroCore hero;
  private int physicDamage;
  private int spellDamage;
  private int defence;
  private int health;
  private int speed;
  private float nativeX;
  private float nativeY = 200;
  private boolean player;
  private boolean actionsEnd = true;
  private HeroSlot basicAttackTarget;
  private HeroSlot spellTarget;
  private HeroSlot comboTarget;
  private boolean isDead = false;
  private boolean wasActionInThisRound = false;
  private boolean prepareSpellAttack = false;
  private String stateAfterAttack;
  private boolean targetAttacked = false;
  private HeroSlot globalTarget;

  public HeroSlot(HeroCore heroCore) {
    this.hero = heroCore;
    this.physicDamage = heroCore.physicDamage();
    this.spellDamage = heroCore.magicDamage();
    this.health = heroCore.health();
    this.defence = heroCore.defence();
    this.speed = heroCore.speed();
    basicAttackTarget = null;
    spellTarget = null;
    comboTarget = null;
  }

  public void setStartData(float nativeX, boolean player) {
    setSize(80, 120);
    setPosition(nativeX, 200);
    this.player = player;
    this.nativeX = nativeX;
  }

  public void getAnimation() {
    switch (currentAction) {
      case "idle":
        hero.getIdleStateAnimation(this, player);
        break;
      case "basicAttack":
        getBasicAttack();
        break;
      case "spellAttack":
        getSpellAttack();
        break;
      case "comboAttack":
        getComboAttack();
        break;
      case "underAttack":
        getUnderAttack();
        break;
      case "highFloated":
        getHighFloated();
        break;
      case "chaseHighFloat":
        getChaseHighFloat();
        break;
      case "chaseLowFloat":
        getChaseLowFloat();
        break;
      case "dead":
        getDead();
        break;
    }
  }

  private void getBasicAttack() {
    stateAfterAttack = hero.getStateAfterBasicAttack();
    if (hero.getBasicAttackAnimation(this, basicAttackTarget, player)) {
      if (nextAction != null) {
        currentAction = nextAction;
        nextAction = null;
      } else {
        actionsEnd = true;
        this.setPosition(nativeX, nativeY);
        setAction("idle");
      }
      wasActionInThisRound = true;
      basicAttackTarget = null;
    } else {
      actionsEnd = false;
      targetAttacked = hero.targetAttacked;
    }
  }

  private void getSpellAttack() {
    stateAfterAttack = hero.getStateAfterSpellAttack();
    if (hero.getSpellAttackAnimation(this, spellTarget, player)) {
      if (nextAction != null) {
        currentAction = nextAction;
        nextAction = null;
      } else {
        actionsEnd = true;
        this.setPosition(nativeX, nativeY);
        setAction("idle");
      }
      spellTarget = null;
    } else {
      actionsEnd = false;
      targetAttacked = hero.targetAttacked;
    }
  }

  private void getComboAttack() {
//    if (hero.getComboAttackAnimation(image, basicAttackTarget, player)) {
//      if (nextComboHero == null) {
//        actionsEnd = true;
//        image.setPosition(nativeX, nativeY);
//        setAction("idle");
//      } else {
//        if (nextComboHero.equals(this)) {
//          actionsEnd = true;
//        } else {
//          actionsEnd = true;
//          image.setPosition(nativeX, nativeY);
//          setAction("idle");
//        }
//      }
//      wasActionInThisRound = true;
//    } else {
//      actionsEnd = false;
//      if (hero.targetAttacked) {
//        basicAttackTarget.setHealth(physicDamage);
//        if (basicAttackTarget.getHealth() <= 0) {
//          basicAttackTarget.setAction("dead", null);
//          nextComboHero = null;
//        } else {
//          basicAttackTarget.setAction(hero.getStateAfterComboAttack());
//          if (checkCombos(hero.getChaseAfterComboAttack())) {
//            nextComboHero.setAction(hero.getChaseAfterComboAttack(), basicAttackTarget);
//          }
//        }
//      }
//    }
  }

  private void getChaseHighFloat() {
    stateAfterAttack = hero.getStateAfterHighFloat();
    if (hero.getChaseHighFloatAnimation(this, comboTarget, player)) {
      if (nextAction != null) {
        currentAction = nextAction;
        nextAction = null;
      } else {
        actionsEnd = true;
        this.setPosition(nativeX, nativeY);
        setAction("idle");
      }
      chaseEnd = true;
      comboTarget = null;
    } else {
      chaseEnd = false;
      actionsEnd = false;
      targetAttacked = hero.targetAttacked;
    }
  }

  private void getChaseLowFloat() {
    stateAfterAttack = hero.getStateAfterLowFloat();
    if (hero.getChaseLowFloatAnimation(this, comboTarget, player)) {
      if (nextAction != null) {
        currentAction = nextAction;
        nextAction = null;
      } else {
        actionsEnd = true;
        this.setPosition(nativeX, nativeY);
        setAction("idle");
      }
      chaseEnd = true;
      comboTarget = null;
    } else {
      chaseEnd = false;
      actionsEnd = false;
      targetAttacked = hero.targetAttacked;
    }
  }

  private void getUnderAttack() {
    if (hero.getUnderAttackAnimation(this, player)) {
      setAction("idle");
      actionsEnd = true;
    } else
      actionsEnd = false;
  }

  private void getHighFloated() {
    if (hero.getUnderHighFloatAnimation(this, player, nativeY)) {
      actionsEnd = true;
      setAction("idle");
    } else
      actionsEnd = false;
  }

  private void getDead() {
    isDead = true;
    actionsEnd = true;
    wasActionInThisRound = true;
    hero.getDeadAnimation(this, player);
  }

  public void setAction(String action, HeroSlot target) {
    if (action.equals("chaseHighFloat")) {
      hero.blockChaseHighFloat(true);
      comboTarget = target;
    }
    if (action.equals("chaseLowFloat")) {
      hero.blockChaseLowFloat(true);
      comboTarget = target;
    }
    if (action.equals("basicAttack"))
      basicAttackTarget = target;
    if (action.equals("spellAttack"))
      spellTarget = target;
    if (currentAction.equals("idle"))
      currentAction = action;
    else nextAction = action;
  }

  public void setAction(String action) {
    if (action.equals("dead")) {
      isDead = true;
    }
    currentAction = action;
  }

  public void setHealth(int damageDeal) {
    this.health -= damageDeal;
    if (health <= 0) {
      health = 0;
      currentAction = "dead";
    }
  }

  public void newRound() {
    wasActionInThisRound = false;
    hero.blockChaseHighFloat(false);
    hero.blockChaseLowFloat(false);
  }

  public boolean isWasActionInThisRound() {
    return wasActionInThisRound;
  }

  public boolean isDead() {
    return isDead;
  }

  public boolean actionEnd() {
    return actionsEnd;
  }

  public Drawable spellImage() {
    return hero.getSpellImage();
  }

  public float heroGetX() {
    return nativeX;
  }

  public int getPhysicDamage() {
    return physicDamage;
  }

  public int getSpellDamage() {
    return spellDamage;
  }

  public int getDefence() {
    return defence;
  }

  public int getHealth() {
    return health;
  }

  public int speed() {
    return speed;
  }

  public boolean haveChase(String chaseName) {
    return !isDead && hero.getChase(chaseName);
  }

  public String getCurrentState() {
    return currentAction;
  }

  public boolean isTargetAttacked() {
    return targetAttacked;
  }

  public String getStateAfterAttack() {
    return stateAfterAttack;
  }

  public boolean isPlayer() {
    return player;
  }

  public HeroSlot getGlobalTarget() {
    if (basicAttackTarget != null) {
      globalTarget = basicAttackTarget;
    } else if (spellTarget != null) {
      globalTarget = spellTarget;
    } else if (comboTarget != null) {
      globalTarget = comboTarget;
    }
    return globalTarget;
  }

  private boolean chaseEnd = false;

  public boolean chaseEnd() {
    return chaseEnd;
  }

  // Делаю кулдауны спелов пока тут, но нужно будет значения брать из heroCore
  private int end = 1;

  public boolean isSpellAvailable(int currentRound) {
    return end == currentRound;
  }

  public void startSpellCooldown(int currentRound) {
    end = currentRound + 2;
  }
}
