package towerREWORK.Heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import towerREWORK.HeroCore.HeroCore;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Stages.BattleStage.wraps.HeroSlot;

public class Boy2 extends HeroCore {

  public Boy2(GameDataManager gdm, boolean isAvailable) {
    super(gdm, 2, isAvailable);
  }

  @Override
  public boolean runToX(Image image, float runToX, boolean player) {
    image.toFront();
    if (player) {
      image.setDrawable(runSkin.getDrawable(runName[current_run_frame]));
    } else {
      Sprite sprite = new Sprite(runSkin.getSprite(runName[current_run_frame]));
      sprite.flip(true, false);
      TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
      image.setDrawable(trd);
    }
    if (player) {
      if (!(image.getX() >= runToX - 85)) {
        image.setPosition(image.getX() + 5, image.getY());
      } else
        return true;
    } else {
      if (!(image.getX() <= runToX + 85)) {
        image.setPosition(image.getX() - 5, image.getY());
      } else
        return true;
    }
    tibf_run += Gdx.graphics.getDeltaTime();
    if (tibf_run > 0.01f) {
      tibf_run = 0f;
      current_run_frame++;
      if (current_run_frame == runName.length) {
        current_run_frame = 0;
      }
    }
    return false;
  }

  @Override
  public boolean getBasicAttackAnimation(Image image, HeroSlot target, boolean player) {
    if (runToX(image, target.getX(), player)) {
      if (player) {
        image.setDrawable(basicAttackSkin.getDrawable(basicAttackName[current_basic_attack_frame]));
      } else {
        Sprite sprite = new Sprite(basicAttackSkin.getSprite(basicAttackName[current_basic_attack_frame]));
        sprite.flip(true, false);
        TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
        image.setDrawable(trd);
      }
      tibf_basic_attack += Gdx.graphics.getDeltaTime();
      if (tibf_basic_attack > 0.01f) {
        tibf_basic_attack = 0f;
        current_basic_attack_frame++;
        targetAttacked = current_basic_attack_frame == 23;
        if (current_basic_attack_frame == basicAttackName.length) {
          tibf_basic_attack = 0;
          current_basic_attack_frame = 0;
          return true;
        }
      } else
        targetAttacked = false;
    }
    return false;
  }

  @Override
  public boolean getSpellAttackAnimation(Image image, HeroSlot target, boolean player) {
    if (runToX(image, target.getX(), player)) {
      if (player) {
        image.setDrawable(spellAttackSkin.getDrawable(spellAttackName[current_spell_attack_frame]));
      } else {
        Sprite sprite = new Sprite(spellAttackSkin.getSprite(spellAttackName[current_spell_attack_frame]));
        sprite.flip(true, false);
        TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
        image.setDrawable(trd);
      }
      tibf_spell_attack += Gdx.graphics.getDeltaTime();
      if (tibf_spell_attack > 0.01f) {
        tibf_spell_attack = 0f;
        current_spell_attack_frame++;
        targetAttacked = current_spell_attack_frame == 9;
        if (current_spell_attack_frame == spellAttackName.length) {
          tibf_spell_attack = 0;
          current_spell_attack_frame = 0;

          return true;
        }
      } else
        targetAttacked = false;
      return false;
    } else
      return false;
  }

  @Override
  public boolean getUnderAttackAnimation(Image image, boolean player) {
    if (player)
      image.setDrawable(underAttackSkin.getDrawable(underAttackName[current_under_attack_frame]));
    else {
      Sprite sprite = new Sprite(underAttackSkin.getSprite(underAttackName[current_under_attack_frame]));
      sprite.flip(true, false);
      TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
      image.setDrawable(trd);
    }
    tibf_under_attack += Gdx.graphics.getDeltaTime();
    if (tibf_under_attack > 0.01f) {
      tibf_under_attack = 0f;
      current_under_attack_frame++;
      if (current_under_attack_frame == underAttackName.length) {
        tibf_under_attack = 0;
        current_under_attack_frame = 0;
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean getDeadAnimation(Image image, boolean player) {
    if (player)
      image.setDrawable(deadSkin.getDrawable(deadName[current_dead_frame]));
    else {
      Sprite sprite = new Sprite(deadSkin.getSprite(deadName[current_dead_frame]));
      sprite.flip(true, false);
      TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
      image.setDrawable(trd);
    }
    tibf_dead += Gdx.graphics.getDeltaTime();
    if (tibf_dead > 0.01f) {
      tibf_dead = 0f;
      current_dead_frame++;
      if (current_dead_frame == deadName.length) {
        current_dead_frame -= 1;
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean getComboAttackAnimation(Image image, HeroSlot target, boolean player) {
    if (runToX(image, target.getX(), player)) {
      image.toFront();
      if (player) {
        image.setDrawable(comboAttackSkin.getDrawable(comboAttackName[current_combo_attack_frame]));
      } else {
        Sprite sprite = new Sprite(comboAttackSkin.getSprite(comboAttackName[current_combo_attack_frame]));
        sprite.flip(true, false);
        TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
        image.setDrawable(trd);
      }
      tibf_combo_attack += Gdx.graphics.getDeltaTime();
      if (tibf_combo_attack > 0.01f) {
        tibf_combo_attack = 0f;
        current_combo_attack_frame++;
        targetAttacked = current_combo_attack_frame == 6;
        if (current_combo_attack_frame == comboAttackName.length) {
          tibf_combo_attack = 0;
          current_combo_attack_frame = 0;
          return true;
        }
      } else
        targetAttacked = false;
      return false;
    } else
      return false;
  }

  @Override
  public boolean getUnderHighFloatAnimation(Image image, boolean player, float nativeY) {
    if (player)
      image.setDrawable(underHighFloatSkin.getDrawable(underHighFloatName[current_under_hight_float_frame]));
    else {
      Sprite sprite = new Sprite(underHighFloatSkin.getSprite(underHighFloatName[current_under_hight_float_frame]));
      sprite.flip(true, false);
      TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
      image.setDrawable(trd);
    }
    if (current_under_hight_float_frame < 20) {
      image.setPosition(image.getX(), image.getY() + 3);
    }
    if (current_under_hight_float_frame >= 20) {
      image.setPosition(image.getX(), image.getY() - 6);
      if (image.getY() <= 200)
        image.setPosition(image.getX(), 200);
    }
    tibf_under_high_float += Gdx.graphics.getDeltaTime();
    if (tibf_under_high_float > 0.01f) {
      tibf_under_high_float = 0f;
      current_under_hight_float_frame++;
      if (current_under_hight_float_frame == underHighFloatName.length) {
        image.setPosition(image.getX(), 200);
        current_under_hight_float_frame = 0;
        tibf_under_high_float = 0;
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean getChaseHighFloatAnimation(Image image, HeroSlot target, boolean player) {
    if (runToX(image, target.getX(), player)) {
      image.toFront();
      if (player) {
        image.setDrawable(chaseHighFloatSkin.getDrawable(chaseHighFloatName[current_chase_hight_float_frame]));
      } else {
        Sprite sprite = new Sprite(chaseHighFloatSkin.getSprite(chaseHighFloatName[current_chase_hight_float_frame]));
        sprite.flip(true, false);
        TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
        image.setDrawable(trd);
      }
      tibf_chase_high_float += Gdx.graphics.getDeltaTime();
      if (tibf_chase_high_float > 0.03) {
        tibf_chase_high_float = 0;
        current_chase_hight_float_frame++;
        targetAttacked = current_chase_hight_float_frame == 36;
        if (current_chase_hight_float_frame == chaseHighFloatName.length) {
          current_chase_hight_float_frame = 0;
          tibf_chase_high_float = 0;
          return true;
        }
      } else {
        targetAttacked = false;
      }
    }
    return false;
  }

  @Override
  public boolean getChaseLowFloatAnimation(Image image, HeroSlot target, boolean player) {
    if (runToX(image, target.getX(), player)) {
      image.toFront();
      if (player) {
        image.setDrawable(chaseLowFloatSkin.getDrawable(chaseLowFloatName[current_chase_low_float_frame]));
      } else {
        Sprite sprite = new Sprite(chaseLowFloatSkin.getSprite(chaseLowFloatName[current_chase_low_float_frame]));
        sprite.flip(true, false);
        TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
        image.setDrawable(trd);
      }
      tibf_chase_low_float += Gdx.graphics.getDeltaTime();
      if (tibf_chase_low_float > 0.03) {
        tibf_chase_low_float = 0;
        current_chase_low_float_frame++;
        targetAttacked = current_chase_low_float_frame == 16;
        if (current_chase_low_float_frame == chaseLowFloatName.length) {
          current_chase_low_float_frame = 0;
          tibf_chase_low_float = 0;
          return true;
        }
      } else
        targetAttacked = false;
    }
    return false;
  }
}