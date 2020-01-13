package towerREWORK.Heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import towerREWORK.HeroCore.HeroCore;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Stages.BattleStage.wraps.HeroSlot;

public class Mage extends HeroCore {

  public Mage(GameDataManager gdm, boolean isAvailable) {
    super(gdm, 1, isAvailable);
    comboBulletInit();
    spellBulletInit();
    highFloatBulletInit();
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
    if (player) {
      image.setDrawable(basicAttackSkin.getDrawable(basicAttackName[current_basic_attack_frame]));
    } else {
      Sprite sprite = new Sprite(basicAttackSkin.getSprite(basicAttackName[current_basic_attack_frame]));
      sprite.flip(true, false);
      TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
      image.setDrawable(trd);
    }
    tibf_basic_attack += Gdx.graphics.getDeltaTime();
    if (tibf_basic_attack > 0.05f) {
      tibf_basic_attack = 0f;
      current_basic_attack_frame++;
      targetAttacked = current_basic_attack_frame == 8;
      if (current_basic_attack_frame == basicAttackName.length) {
        tibf_basic_attack = 0;
        current_basic_attack_frame = 0;
        return true;
      }
    } else
      targetAttacked = false;
    return false;
  }

  @Override
  public boolean getSpellAttackAnimation(Image image, HeroSlot target, boolean player) {
    if (player) {
      image.setDrawable(spellAttackSkin.getDrawable(spellAttackName[current_spell_attack_frame]));
    } else {
      Sprite sprite = new Sprite(spellAttackSkin.getSprite(spellAttackName[current_spell_attack_frame]));
      sprite.flip(true, false);
      TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
      image.setDrawable(trd);
    }

    tibf_spell_attack += Gdx.graphics.getDeltaTime();
    if (tibf_spell_attack > 0.05f) {
      tibf_spell_attack = 0f;
      current_spell_attack_frame++;
      if (current_spell_attack_frame == spellAttackName.length) {
        current_spell_attack_frame -= 1;
        getIdleStateAnimation(image, player);
      }
    }
    if (current_spell_attack_frame >= 19) {
      image.getStage().addActor(spellBullet);
      if (spellBulletAnimation(target.getX())) {
        tibf_spell_attack = 0;
        current_spell_attack_frame = 0;
        return true;
      }
    }
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
    if (tibf_under_attack > 0.05f) {
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
    if (tibf_dead > 0.05f) {
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
    image.toFront();
    if (comboBullet.getX() == 0) {
      comboBullet.setPosition(image.getX() + 50, image.getY() + 50);
    }
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
      if (current_combo_attack_frame == comboAttackName.length) {
        current_combo_attack_frame -= 1;
        getIdleStateAnimation(image, player);
      }
    }
    if (current_combo_attack_frame >= 16) {
      image.getStage().addActor(comboBullet);
      if (comboBulletAnimation(image.getX(), target.getX())) {
        tibf_combo_attack = 0;
        current_combo_attack_frame = 0;
        return true;
      }
    }

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
    image.toFront();
    if (highFloatBullet.getX() == 0) {
      highFloatBullet.setX(image.getX() + 50);
    }
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
      if (current_chase_hight_float_frame == chaseHighFloatName.length) {
        current_chase_hight_float_frame -= 1;
        getIdleStateAnimation(image, player);
      }
    }
    if (current_chase_hight_float_frame >= 9) {
      image.getStage().addActor(highFloatBullet);
      if (highFloatBulletAnimation(target.getX())) {
        tibf_chase_high_float = 0;
        current_chase_hight_float_frame = 0;
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean getChaseLowFloatAnimation(Image image, HeroSlot target, boolean player) {
    return false;
  }

  private boolean comboBulletAnimation(float nativeX, float finishX) {
    comboBullet.setDrawable(comboBulletSkin.getDrawable(comboBulletName[current_combo_bullet_frame]));
    tibf_combo_bullet += Gdx.graphics.getDeltaTime();
    if (tibf_combo_bullet > 0.01) {
      float xesSec = (finishX - nativeX) / 5;
      if (comboBullet.getX() < finishX) {
        comboBullet.setX(comboBullet.getX() + xesSec);
      }
      tibf_combo_bullet = 0;
      current_combo_bullet_frame++;
      targetAttacked = current_combo_bullet_frame == 5;
      if (current_combo_bullet_frame == comboBulletName.length) {
        current_combo_bullet_frame = 0;
        tibf_combo_bullet = 0;
        comboBullet.setX(0);
        comboBullet.remove();
        return true;
      }
    }
    return false;
  }

  private boolean spellBulletAnimation(float finishX) {
    spellBullet.setDrawable(spellBulletSkin.getDrawable(spellBulletName[current_spell_bullet_frame]));
    tibf_spell_bullet += Gdx.graphics.getDeltaTime();
    if (tibf_spell_bullet > 0.01) {
      tibf_spell_bullet = 0;
      if (spellBullet.getY() > 200) {
        targetAttacked = false;
        spellBullet.setX(finishX);
        spellBullet.setY(spellBullet.getY() - 5);
      } else {
        current_spell_bullet_frame++;
      }
      targetAttacked = current_spell_bullet_frame == 2;
      if (current_spell_bullet_frame == spellBulletName.length) {
        current_spell_bullet_frame = 0;
        tibf_spell_bullet = 0;
        spellBullet.setY(900);
        spellBullet.remove();
        return true;
      }
    }
    return false;
  }

  private boolean highFloatBulletAnimation(float finishX) {
    tibf_high_float_bullet += Gdx.graphics.getDeltaTime();
    if (tibf_high_float_bullet > 0.01) {
      tibf_high_float_bullet = 0;
      if (highFloatBullet.getX() < finishX) {
        highFloatBullet.setX(highFloatBullet.getX() + 5);
      } else {
        tibf_high_float_bullet = 0;
        highFloatBullet.setX(0);
        highFloatBullet.remove();
        return true;
      }
    }
    targetAttacked = highFloatBullet.getX() == finishX - 10;
    return false;
  }

  private float tibf_combo_bullet = 0;
  private int current_combo_bullet_frame = 0;
  private String[] comboBulletName;
  private Skin comboBulletSkin;
  private Image comboBullet;

  private float tibf_spell_bullet = 0;
  private int current_spell_bullet_frame = 0;
  private String[] spellBulletName;
  private Skin spellBulletSkin;
  private Image spellBullet;

  private float tibf_high_float_bullet = 0;
  private Image highFloatBullet;

  private void highFloatBulletInit() {
    highFloatBullet = new Image(gdm.assets().manager.get(gdm.assets().mageBasicBullet, Texture.class));
    highFloatBullet.setSize(50, 50);
    highFloatBullet.setPosition(0, 200);
  }

  private void comboBulletInit() {
    comboBulletSkin = new Skin(gdm.assets().manager.get(gdm.assets().mageComboBullet, TextureAtlas.class));
    comboBullet = new Image();
    comboBullet.setSize(50, 50);
    comboBullet.setPosition(0, 0);
    comboBulletName = new String[comboBulletSkin.getAtlas().getRegions().size];
    for (int i = 0; i < comboBulletName.length; i++) {
      comboBulletName[i] = "ComboBullet_" + (i + 1);
    }
  }

  private void spellBulletInit() {
    spellBulletSkin = new Skin(gdm.assets().manager.get(gdm.assets().mageSpellBullet, TextureAtlas.class));
    spellBullet = new Image();
    spellBullet.setSize(50, 50);
    spellBullet.setPosition(0, 900);
    spellBulletName = new String[spellBulletSkin.getAtlas().getRegions().size];
    for (int i = 0; i < spellBulletName.length; i++) {
      spellBulletName[i] = "SpellBullet_" + (i + 1);
    }
  }


}