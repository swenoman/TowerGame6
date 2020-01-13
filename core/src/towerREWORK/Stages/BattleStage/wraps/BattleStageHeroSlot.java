//package towerREWORK.Stages.BattleStage.wraps;
//
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
//import com.badlogic.gdx.utils.Array;
//
//import towerREWORK.Bots.BotStats;
//import towerREWORK.HeroCore.HeroCore;
//
//public class BattleStageHeroSlot {
//
//  private String currentAction = "idle";
//  private String nextAction = null;
//  private Image image;
//  private HeroCore hero;
//  private int physicDamage;
//  private int spellDamage;
//  private int defence;
//  private int health;
//  private float nativeX;
//  private float nativeY = 200;
//  private boolean player;
//  private BattleStageHeroSlot target;
//  private boolean actionsEnd = true;
//  private BattleStageHeroSlot nextTarget = null;
//  private boolean isDead = false;
//  private boolean wasActionInThisRound = false;
//  private Array<BattleStageHeroSlot> heroes = null;
//  private BattleStageHeroSlot nextComboHero = null;
//
//  public BattleStageHeroSlot(HeroCore heroCore, Image image, float nativeX, boolean player) {
//    this.hero = heroCore;
//    this.player = player;
//    this.image = image;
//    this.nativeX = nativeX;
//
//    this.physicDamage = heroCore.physicDamage();
//    this.spellDamage = heroCore.magicDamage();
//    this.health = heroCore.health();
//    this.defence = heroCore.defence();
//    target = null;
//  }
//
//  public void setTeam(Array<BattleStageHeroSlot> team) {
//    this.heroes = new Array<>();
//    heroes.add(team.get(0));
//    heroes.add(team.get(1));
//    heroes.add(team.get(2));
//    heroes.add(team.get(3));
//  }
//
//  public void setBotStats(BotStats bs) {
//    this.physicDamage = bs.getAttackDamage();
//    this.spellDamage = bs.getSpellDamage();
//    this.health = bs.getHp();
//    this.defence = bs.getDefence();
//  }
//
//  public void getAnimation() {
//    switch (currentAction) {
//      case "idle":
//        hero.getIdleStateAnimation(image, player);
//        break;
//      case "basicAttack":
//        getBasicAttack();
//        break;
//      case "spellAttack":
//        getSpellAttack();
//        break;
//      case "comboAttack":
//        getComboAttack();
//        break;
//      case "underAttack":
//        getUnderAttack();
//        break;
//      case "highFloated":
//        getHighFloated();
//        break;
//      case "chaseHighFloat":
//        getChaseHighFloat();
//        break;
//      case "chaseLowFloat":
//        getChaseLowFloat();
//        break;
//      case "dead":
//        getDead();
//        break;
//    }
//    if (nextAction != null) {
//      if (actionsEnd) {
//        if (nextAction.equals("idle"))
//          actionsEnd = true;
//        else
//          actionsEnd = false;
//        currentAction = nextAction;
//        target = nextTarget;
//        nextAction = null;
//        nextTarget = null;
//      }
//    }
//  }
//
//  private void getBasicAttack() {
//    if (hero.getBasicAttackAnimation(image, target, player)) {
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
//        target.setHealth(physicDamage);
//        if (target.getHealth() <= 0) {
//          target.setAction("dead", null);
//          nextComboHero = null;
//        } else {
//          target.setAction(hero.getStateAfterBasicAttack());
//          if (checkCombos(hero.getChaseAfterBasicAttack())) {
//            nextComboHero.setAction(hero.getChaseAfterBasicAttack(), target);
//          }
//        }
//      }
//    }
//  }
//
//  private void getSpellAttack() {
//    if (hero.getSpellAttackAnimation(image, target, player)) {
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
//        target.setHealth(physicDamage);
//        if (target.getHealth() <= 0) {
//          target.setAction("dead", null);
//          nextComboHero = null;
//        } else {
//          target.setAction(hero.getStateAfterSpellAttack());
//          if (checkCombos(hero.getChaseAfterSpellAttack())) {
//            nextComboHero.setAction(hero.getChaseAfterSpellAttack(), target);
//          }
//        }
//      }
//    }
//  }
//
//  private void getComboAttack() {
//    if (hero.getComboAttackAnimation(image, target, player)) {
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
//        target.setHealth(physicDamage);
//        if (target.getHealth() <= 0) {
//          target.setAction("dead", null);
//          nextComboHero = null;
//        } else {
//          target.setAction(hero.getStateAfterComboAttack());
//          if (checkCombos(hero.getChaseAfterComboAttack())) {
//            nextComboHero.setAction(hero.getChaseAfterComboAttack(), target);
//          }
//        }
//      }
//    }
//  }
//
//  private void getChaseHighFloat() {
//    if (hero.getChaseHighFloatAnimation(image, target, player)) {
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
//    } else {
//      actionsEnd = false;
//      if (hero.targetAttacked) {
//        target.setHealth(physicDamage);
//        if (target.getHealth() <= 0) {
//          target.setAction("dead", null);
//          nextComboHero = null;
//        } else {
//          target.setAction(hero.getStateAfterHighFloat(), null);
//          if (checkCombos(hero.getChaseAfterHighFloat())) {
//            nextComboHero.setAction(hero.getChaseAfterHighFloat(), target);
//          }
//        }
//      }
//    }
//  }
//
//  private void getChaseLowFloat() {
//    if (hero.getChaseLowFloatAnimation(image, target, player)) {
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
//    } else {
//      actionsEnd = false;
//      if (hero.targetAttacked) {
//        target.setHealth(physicDamage);
//        if (target.getHealth() <= 0) {
//          target.setAction("dead");
//          nextComboHero = null;
//        } else {
//          target.setAction(hero.getStateAfterLowFloat());
//          if (checkCombos(hero.getChaseAfterLowFloat())) {
//            nextComboHero.setAction(hero.getChaseAfterLowFloat(), target);
//          }
//        }
//      }
//    }
//  }
//
//  private void getUnderAttack() {
//    if (hero.getUnderAttackAnimation(image, player)) {
//      setAction("idle");
//      actionsEnd = true;
//    } else
//      actionsEnd = false;
//  }
//
//  private void getHighFloated() {
//    if (hero.getUnderHighFloatAnimation(image, player, nativeY)) {
//      actionsEnd = true;
//      setAction("idle", null);
//    } else
//      actionsEnd = false;
//  }
//
//  private void getDead() {
//    isDead = true;
//    actionsEnd = true;
//    wasActionInThisRound = true;
//    hero.getDeadAnimation(image, player);
//  }
//
//  private boolean checkCombos(String chaseName) {
//    for (int i = 0; i < heroes.size; i++) {
//      if (heroes.get(i).getHealth() > 0) {
//        if (heroes.get(i).hero.getChase(chaseName)) {
//          nextComboHero = heroes.get(i);
//          return true;
//        } else nextComboHero = null;
//      } else
//        nextComboHero = null;
//    }
//    return false;
//  }
//
//  public void setAction(String finalAction, BattleStageHeroSlot target) {
//    if (finalAction.equals("dead")) {
//      isDead = true;
//    }
//    if (finalAction.equals("chaseHighFloat"))
//      hero.blockChaseHighFloat(true);
//    if (finalAction.equals("chaseLowFloat"))
//      hero.blockChaseLowFloat(true);
//    if (nextAction == null) {
//      nextAction = finalAction;
//      nextTarget = target;
//    }
//  }
//
//  public void setAction(String finalAction) {
//    if (finalAction.equals("dead")) {
//      isDead = true;
//    }
//    if (nextAction == null) {
//      currentAction = finalAction;
//    }
//  }
//
//  public void setHealth(int damageDeal) {
//    this.health -= damageDeal;
//    if (health <= 0) {
//      health = 0;
//    }
//  }
//
//  public void newRound() {
//    wasActionInThisRound = false;
//    hero.blockChaseHighFloat(false);
//    hero.blockChaseLowFloat(false);
//  }
//
//  public boolean isWasActionInThisRound() {
//    return wasActionInThisRound;
//  }
//
//  public boolean isDead() {
//    return isDead;
//  }
//
//  public boolean actionEnd() {
//    return actionsEnd;
//  }
//
//  public Drawable spellImage() {
//    return hero.getSpellImage().get(0);
//  }
//
//  public float getX() {
//    return nativeX;
//  }
//
//  public int getPhysicDamage() {
//    return physicDamage;
//  }
//
//  public int getSpellDamage() {
//    return spellDamage;
//  }
//
//  public int getDefence() {
//    return defence;
//  }
//
//  public int getHealth() {
//    return health;
//  }
//}
