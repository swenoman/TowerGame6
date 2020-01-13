package towerREWORK.HeroCore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import towerREWORK.Assets;
import towerREWORK.Managers.GameDataManager;
import towerREWORK.Stages.BattleStage.wraps.HeroSlot;

public abstract class HeroData {
  protected GameDataManager gdm;
  private Assets assets;
  protected int id;
  protected String name;
  protected String type;
  protected int basicPhysicDamage;
  protected int basicMagicDamage;
  protected int basicHealth;
  protected int basicDefence;
  protected int basicSpeed;
  protected int physicDamageGrowth;
  protected int magicDamageGrowth;
  protected int healthGrowth;
  protected int defenceGrowth;
  protected boolean available;
  protected Drawable spellsImages;
  protected Drawable inventoryImage;
  protected Skin idleSkin;
  protected Skin deadSkin;
  protected Skin underAttackSkin;
  protected Skin underHighFloatSkin;
  protected Skin runSkin;
  protected Skin basicAttackSkin;
  protected Skin spellAttackSkin;
  protected Skin comboAttackSkin;
  protected Skin chaseHighFloatSkin;
  protected Skin chaseLowFloatSkin;
  protected String chaseAfterBasicAttack;
  protected String stateAfterBasicAttack;
  protected String chaseAfterSpellAttack;
  protected String stateAfterSpellAttack;
  protected String chaseAfterComboAttack;
  protected String stateAfterComboAttack;
  protected String chaseAfterHighFloat;
  protected String chaseAfterLowFloat;
  protected String stateAfterHighFloat;
  protected String stateAfterLowFloat;
  protected boolean chaseHighFloat;
  protected boolean chaseLowFloat;

  public HeroData(GameDataManager gdm, boolean isAvailable) {
    this.gdm = gdm;
    available = isAvailable;
    this.assets = gdm.assets();
    spellsImages = null;
  }

  protected void setHeroData(int id) {
    this.id = id;
    setStats();
    setDrawables();
    setStatesDataTextureAtlas();
    setActionsDataTextureAtlas();
    setChasesDataTextureAtlas();
    setNames();
    setStatesAfterAttacks();
  }

  private void setStats() {
    switch (id) {
      case 0:
        name = "Boy";
        type = "fighter";
        basicPhysicDamage = 10;
        basicHealth = 50;
        basicMagicDamage = 14;
        basicDefence = 2;
        basicSpeed = 100;
        physicDamageGrowth = 1;
        magicDamageGrowth = 1;
        healthGrowth = 10;
        defenceGrowth = 1;
        break;
      case 1:
        name = "Mage";
        type = "mage";
        basicPhysicDamage = 15;
        basicHealth = 30;
        basicMagicDamage = 14;
        basicDefence = 2;
        basicSpeed = 120;
        physicDamageGrowth = 1;
        magicDamageGrowth = 1;
        healthGrowth = 10;
        defenceGrowth = 1;
        break;
      case 2:
        name = "Boy2";
        type = "fighter";
        basicPhysicDamage = 10;
        basicHealth = 50;
        basicMagicDamage = 14;
        basicDefence = 2;
        basicSpeed = 110;
        physicDamageGrowth = 1;
        magicDamageGrowth = 1;
        healthGrowth = 10;
        defenceGrowth = 1;
        break;
      case 3:
        name = "Boy3";
        type = "fighter";
        basicPhysicDamage = 10;
        basicHealth = 50;
        basicMagicDamage = 14;
        basicDefence = 2;
        basicSpeed = 150;
        physicDamageGrowth = 1;
        magicDamageGrowth = 1;
        healthGrowth = 10;
        defenceGrowth = 1;
        break;
      default:
        name = "noName";
        type = "noType";
        basicPhysicDamage = 0;
        basicHealth = 0;
        basicMagicDamage = 0;
        basicDefence = 0;
        basicSpeed = 0;
        physicDamageGrowth = 0;
        magicDamageGrowth = 0;
        healthGrowth = 0;
        defenceGrowth = 0;
        break;
    }
  }

  private void setDrawables() {
    switch (id) {
      case 0:
        if (available) {
          inventoryImage = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boyInventoryImageUnlocked, Texture.class)));
        } else {
          inventoryImage = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boyInventoryImageLocked, Texture.class)));
        }
        spellsImages = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boySpellImage, Texture.class)));
        break;
      case 1:
        if (available) {
          inventoryImage = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.mageInventoryImageUnlocked, Texture.class)));
        } else {
          inventoryImage = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.mageInventoryImageLocked, Texture.class)));
        }
        spellsImages = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boySpellImage, Texture.class)));
        break;
      case 2:
        if (available) {
          inventoryImage = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boyInventoryImageUnlocked, Texture.class)));
        } else {
          inventoryImage = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boyInventoryImageLocked, Texture.class)));
        }
        spellsImages = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boySpellImage, Texture.class)));
        break;
      case 3:
        if (available) {
          inventoryImage = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boyInventoryImageUnlocked, Texture.class)));
        } else {
          inventoryImage = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boyInventoryImageLocked, Texture.class)));
        }
        spellsImages = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boySpellImage, Texture.class)));

        break;
      default:
        inventoryImage = new Skin(assets.manager.get(assets.items, TextureAtlas.class)).getDrawable("item_0");
        spellsImages = new TextureRegionDrawable(new TextureRegion(assets.manager.get(assets.boySpellImage, Texture.class)));

        break;
    }
  }

  private void setStatesDataTextureAtlas() {
    switch (id) {
      case 0:
        idleSkin = new Skin(assets.manager.get(assets.boyIdle, TextureAtlas.class));
        deadSkin = new Skin(assets.manager.get(assets.boyDead, TextureAtlas.class));
        underAttackSkin = new Skin(assets.manager.get(assets.boyUnderAttack, TextureAtlas.class));
        underHighFloatSkin = new Skin(assets.manager.get(assets.boyUnderHighFloat, TextureAtlas.class));
        break;
      case 1:
        idleSkin = new Skin(assets.manager.get(assets.mageIdle, TextureAtlas.class));
        deadSkin = new Skin(assets.manager.get(assets.mageDead, TextureAtlas.class));
        underAttackSkin = new Skin(assets.manager.get(assets.mageUnderAttack, TextureAtlas.class));
        underHighFloatSkin = new Skin(assets.manager.get(assets.mageUnderHighFloat, TextureAtlas.class));
        break;
      case 2:
        idleSkin = new Skin(assets.manager.get(assets.boyIdle, TextureAtlas.class));
        deadSkin = new Skin(assets.manager.get(assets.boyDead, TextureAtlas.class));
        underAttackSkin = new Skin(assets.manager.get(assets.boyUnderAttack, TextureAtlas.class));
        underHighFloatSkin = new Skin(assets.manager.get(assets.boyUnderHighFloat, TextureAtlas.class));
        break;
      case 3:
        idleSkin = new Skin(assets.manager.get(assets.boyIdle, TextureAtlas.class));
        deadSkin = new Skin(assets.manager.get(assets.boyDead, TextureAtlas.class));
        underAttackSkin = new Skin(assets.manager.get(assets.boyUnderAttack, TextureAtlas.class));
        underHighFloatSkin = new Skin(assets.manager.get(assets.boyUnderHighFloat, TextureAtlas.class));
        break;

    }
  }

  public void setActionsDataTextureAtlas() {
    switch (id) {
      case 0:
        runSkin = new Skin(assets.manager.get(assets.boyRun, TextureAtlas.class));
        basicAttackSkin = new Skin(assets.manager.get(assets.boyBasicAttack, TextureAtlas.class));
        spellAttackSkin = new Skin(assets.manager.get(assets.boySpellAttack, TextureAtlas.class));
        comboAttackSkin = new Skin(assets.manager.get(assets.boyComboAttack, TextureAtlas.class));
        break;
      case 1:
        runSkin = new Skin(assets.manager.get(assets.mageRun, TextureAtlas.class));
        basicAttackSkin = new Skin(assets.manager.get(assets.mageBasicAttack, TextureAtlas.class));
        spellAttackSkin = new Skin(assets.manager.get(assets.mageSpellAttack, TextureAtlas.class));
        comboAttackSkin = new Skin(assets.manager.get(assets.mageComboAttack, TextureAtlas.class));
        break;
      case 2:
        runSkin = new Skin(assets.manager.get(assets.boyRun, TextureAtlas.class));
        basicAttackSkin = new Skin(assets.manager.get(assets.boyBasicAttack, TextureAtlas.class));
        spellAttackSkin = new Skin(assets.manager.get(assets.boySpellAttack, TextureAtlas.class));
        comboAttackSkin = new Skin(assets.manager.get(assets.boyComboAttack, TextureAtlas.class));
        break;
      case 3:
        runSkin = new Skin(assets.manager.get(assets.boyRun, TextureAtlas.class));
        basicAttackSkin = new Skin(assets.manager.get(assets.boyBasicAttack, TextureAtlas.class));
        spellAttackSkin = new Skin(assets.manager.get(assets.boySpellAttack, TextureAtlas.class));
        comboAttackSkin = new Skin(assets.manager.get(assets.boyComboAttack, TextureAtlas.class));
        break;
    }
  }

  public void setChasesDataTextureAtlas() {
    switch (id) {
      case 0:
        chaseHighFloatSkin = new Skin(assets.manager.get(assets.boyChaseHighFloat, TextureAtlas.class));
        chaseLowFloatSkin = new Skin(assets.manager.get(assets.boyChaseLowFloat, TextureAtlas.class));
        break;
      case 1:
        chaseHighFloatSkin = new Skin(assets.manager.get(assets.mageChaseHighFloat, TextureAtlas.class));
        // no chaseFloat
        chaseLowFloatSkin = new Skin(assets.manager.get(assets.boyChaseLowFloat, TextureAtlas.class));
        break;
      case 2:
        chaseHighFloatSkin = new Skin(assets.manager.get(assets.boyChaseHighFloat, TextureAtlas.class));
        chaseLowFloatSkin = new Skin(assets.manager.get(assets.boyChaseLowFloat, TextureAtlas.class));
        break;
      case 3:
        chaseHighFloatSkin = new Skin(assets.manager.get(assets.boyChaseHighFloat, TextureAtlas.class));
        chaseLowFloatSkin = new Skin(assets.manager.get(assets.boyChaseLowFloat, TextureAtlas.class));
        break;
    }
  }

  public void setNames() {
    if (id != -1) {
      idleName = new String[idleSkin.getAtlas().getRegions().size];
      for (int i = 0; i < idleName.length; i++) {
        idleName[i] = "Idle_" + (i + 1);
      }
      deadName = new String[deadSkin.getAtlas().getRegions().size];
      for (int i = 0; i < deadName.length; i++) {
        deadName[i] = "Dead_" + (i + 1);
      }
      runName = new String[runSkin.getAtlas().getRegions().size];
      for (int i = 0; i < runName.length; i++) {
        runName[i] = "Run_" + (i + 1);
      }

      basicAttackName = new String[basicAttackSkin.getAtlas().getRegions().size];
      for (int i = 0; i < basicAttackName.length; i++) {
        basicAttackName[i] = "BasicAttack_" + (i + 1);
      }

      spellAttackName = new String[spellAttackSkin.getAtlas().getRegions().size];
      for (int i = 0; i < spellAttackName.length; i++) {
        spellAttackName[i] = "SpellAttack_" + (i + 1);
      }

      underAttackName = new String[underAttackSkin.getAtlas().getRegions().size];
      for (int i = 0; i < underAttackName.length; i++) {
        underAttackName[i] = "UnderAttack_" + (i + 1);
      }

      comboAttackName = new String[comboAttackSkin.getAtlas().getRegions().size];
      for (int i = 0; i < comboAttackName.length; i++) {
        comboAttackName[i] = "ComboAttack_" + (i + 1);
      }

      underHighFloatName = new String[underHighFloatSkin.getAtlas().getRegions().size];
      for (int i = 0; i < underHighFloatName.length; i++) {
        underHighFloatName[i] = "UnderHighFloat_" + (i + 1);
      }

      chaseHighFloatName = new String[chaseHighFloatSkin.getAtlas().getRegions().size];
      for (int i = 0; i < chaseHighFloatName.length; i++) {
        chaseHighFloatName[i] = "ChaseHighFloat_" + (i + 1);
      }

      chaseLowFloatName = new String[chaseLowFloatSkin.getAtlas().getRegions().size];
      for (int i = 0; i < chaseLowFloatName.length; i++) {
        chaseLowFloatName[i] = "ChaseLowFloat_" + (i + 1);
      }
    }

  }

  public void setStatesAfterAttacks() {
    switch (id) {
      case 0:
        chaseAfterBasicAttack = "no";
        stateAfterBasicAttack = "underAttack";
        chaseAfterSpellAttack = "chaseHighFloat";
        stateAfterSpellAttack = "highFloated";
        chaseAfterComboAttack = "chaseHighFloat";
        stateAfterComboAttack = "highFloated";
        chaseAfterHighFloat = "chaseLowFloat";
        chaseAfterLowFloat = "chaseHighFloat";
        stateAfterHighFloat = "highFloated";
        stateAfterLowFloat = "highFloated";
        chaseHighFloat = true;
        chaseLowFloat = true;
        break;
      case 1:
        chaseAfterBasicAttack = "no";
        stateAfterBasicAttack = "underAttack";
        chaseAfterSpellAttack = "chaseHighFloat";
        stateAfterSpellAttack = "highFloated";
        chaseAfterComboAttack = "chaseLowFloat";
        stateAfterComboAttack = "highFloated";
        chaseAfterHighFloat = "chaseHighFloat";
        chaseAfterLowFloat = "no";
        stateAfterHighFloat = "highFloated";
        stateAfterLowFloat = "highFloated";
        chaseHighFloat = true;
        chaseLowFloat = false;
        break;
      case 2:
        chaseAfterBasicAttack = "no";
        stateAfterBasicAttack = "underAttack";
        chaseAfterSpellAttack = "no";
        stateAfterSpellAttack = "underAttack";
        chaseAfterComboAttack = "chaseHighFloat";
        stateAfterComboAttack = "highFloated";
        chaseAfterHighFloat = "chaseLowFloat";
        chaseAfterLowFloat = "chaseHighFloat";
        stateAfterHighFloat = "highFloated";
        stateAfterLowFloat = "highFloated";
        chaseHighFloat = true;
        chaseLowFloat = true;
        break;
      case 3:
        chaseAfterBasicAttack = "no";
        stateAfterBasicAttack = "underAttack";
        chaseAfterSpellAttack = "no";
        stateAfterSpellAttack = "underAttack";
        chaseAfterComboAttack = "chaseHighFloat";
        stateAfterComboAttack = "highFloated";
        chaseAfterHighFloat = "chaseLowFloat";
        chaseAfterLowFloat = "chaseHighFloat";
        stateAfterHighFloat = "highFloated";
        stateAfterLowFloat = "highFloated";
        chaseHighFloat = true;
        chaseLowFloat = true;
        break;
    }
  }

  protected float tibf_idle = 0; // Time interval between frames
  protected float tibf_run = 0; // Time interval between frames
  protected float tibf_basic_attack = 0; // Time interval between frames
  protected float tibf_spell_attack = 0; // Time interval between frames
  protected float tibf_dead = 0; // Time interval between frames
  protected float tibf_under_attack = 0; // Time interval between frames
  protected float tibf_combo_attack = 0; // Time interval between frames
  protected float tibf_under_high_float = 0; // Time interval between frames
  protected float tibf_chase_high_float = 0; // Time interval between frames
  protected float tibf_chase_low_float = 0; // Time interval between frames
  protected int current_idle_frame = 0;
  protected String[] idleName;
  protected int current_run_frame = 0;
  protected String[] runName;
  protected int current_basic_attack_frame = 0;
  protected String[] basicAttackName;
  protected int current_spell_attack_frame = 0;
  protected String[] spellAttackName;
  protected int current_dead_frame = 0;
  protected String[] deadName;
  protected int current_under_attack_frame = 0;
  protected String[] underAttackName;
  protected int current_combo_attack_frame = 0;
  protected String[] comboAttackName;
  protected int current_under_hight_float_frame = 0;
  protected String[] underHighFloatName;
  protected int current_chase_hight_float_frame = 0;
  protected String[] chaseHighFloatName;
  protected int current_chase_low_float_frame = 0;
  protected String[] chaseLowFloatName;
  public boolean targetAttacked = false;

  public void getIdleStateAnimation(Image image, boolean player) {
    if (player) {
      image.setDrawable(idleSkin.getDrawable(idleName[current_idle_frame]));
    } else {
      Sprite sprite = new Sprite(idleSkin.getSprite(idleName[current_idle_frame]));
      sprite.flip(true, false);
      TextureRegionDrawable trd = new TextureRegionDrawable(sprite);
      image.setDrawable(trd);
    }
    tibf_idle += Gdx.graphics.getDeltaTime();
    if (tibf_idle > 0.1f) {
      tibf_idle = 0f;
      current_idle_frame++;
      if (current_idle_frame > idleName.length - 1) {
        current_idle_frame = 0;
      }
    }
  }

  public abstract boolean runToX(Image image, float runToX, boolean player);

  public abstract boolean getBasicAttackAnimation(Image image, HeroSlot target, boolean player);

  public abstract boolean getUnderAttackAnimation(Image image, boolean player);

  public abstract boolean getUnderHighFloatAnimation(Image image, boolean player, float nativeY);

  public abstract boolean getDeadAnimation(Image image, boolean player);

  public abstract boolean getSpellAttackAnimation(Image image, HeroSlot target, boolean player);

  public abstract boolean getComboAttackAnimation(Image image, HeroSlot target, boolean player);

  public abstract boolean getChaseHighFloatAnimation(Image image, HeroSlot target, boolean player);

  public abstract boolean getChaseLowFloatAnimation(Image image, HeroSlot target, boolean player);

  public int id() {
    return id;
  }

  public String name() {
    return name;
  }

  public String type() {
    return type;
  }

  public boolean isAvailable() {
    return available;
  }

  public int physicDamageGrowth() {
    return physicDamageGrowth;
  }

  public int magicDamageGrowth() {
    return magicDamageGrowth;
  }

  public int healthGrowth() {
    return healthGrowth;
  }

  public int defenceGrowth() {
    return defenceGrowth;
  }

  public String getStateAfterBasicAttack() {
    return stateAfterBasicAttack;
  }

  public String getChaseAfterBasicAttack() {
    return chaseAfterBasicAttack;
  }

  public String getStateAfterSpellAttack() {
    return stateAfterSpellAttack;
  }

  public String getChaseAfterSpellAttack() {
    return chaseAfterSpellAttack;
  }

  public String getStateAfterComboAttack() {
    return stateAfterComboAttack;
  }

  public String getChaseAfterComboAttack() {
    return chaseAfterComboAttack;
  }

  public String getStateAfterHighFloat() {
    return stateAfterHighFloat;
  }

  public String getChaseAfterHighFloat() {
    return chaseAfterHighFloat;
  }

  public String getStateAfterLowFloat() {
    return stateAfterLowFloat;
  }

  public String getChaseAfterLowFloat() {
    return chaseAfterLowFloat;
  }

  public boolean getChase(String chaseName) {
    switch (chaseName) {
      case "chaseHighFloat":
        if (!highFloatBlocked)
          return chaseHighFloat;
        else return false;
      case "chaseLowFloat":
        if (!lowFloatBlocked)
          return chaseLowFloat;
        else return false;
      default:
        return false;
    }
  }

  private boolean highFloatBlocked = false;
  private boolean lowFloatBlocked = false;

  public void blockChaseHighFloat(boolean block) {
    highFloatBlocked = block;
  }

  public void blockChaseLowFloat(boolean block) {
    lowFloatBlocked = block;
  }

  public boolean findChase(String chaseName) {
    switch (chaseName) {
      case "chaseHighFloat":
        return chaseHighFloat;
      case "chaseLowFloat":
        return chaseLowFloat;
    }
    return false;
  }
}
