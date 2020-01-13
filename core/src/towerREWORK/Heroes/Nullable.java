package towerREWORK.Heroes;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import towerREWORK.HeroCore.HeroCore;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Stages.BattleStage.wraps.HeroSlot;

public class Nullable extends HeroCore {

  public Nullable(GameDataManager gdm, boolean isAvailable) {
    super(gdm, -1, isAvailable);
  }

  @Override
  public boolean runToX(Image image, float runToX, boolean player) {
    return false;
  }

  @Override
  public boolean getBasicAttackAnimation(Image image, HeroSlot target, boolean player) {
    return false;
  }

  @Override
  public boolean getUnderAttackAnimation(Image image, boolean player) {
    return false;
  }

  @Override
  public boolean getUnderHighFloatAnimation(Image image, boolean player, float nativeY) {
    return false;
  }

  @Override
  public boolean getDeadAnimation(Image image, boolean player) {
    return false;
  }

  @Override
  public boolean getSpellAttackAnimation(Image image, HeroSlot target, boolean player) {
    return false;
  }

  @Override
  public boolean getComboAttackAnimation(Image image, HeroSlot target, boolean player) {
    return false;
  }

  @Override
  public boolean getChaseHighFloatAnimation(Image image, HeroSlot target, boolean player) {
    return false;
  }

  @Override
  public boolean getChaseLowFloatAnimation(Image image, HeroSlot target, boolean player) {
    return false;
  }

}