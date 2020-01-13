package towerREWORK;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Assets {
  public final AssetManager manager = new AssetManager();
  public final String font = "font.TTF";
  public final String images = "atlases/images.atlas";
  public final String buttons = "atlases/buttons.atlas";
  public final String items = "atlases/item.atlas";

  public final String boyRun = "atlases/boy/Run.atlas";
  public final String boyDead = "atlases/boy/Dead.atlas";
  public final String boyIdle = "atlases/boy/Idle.atlas";
  public final String boyBasicAttack = "atlases/boy/BasicAttack.atlas";
  public final String boyUnderAttack = "atlases/boy/UnderAttack.atlas";
  public final String boyUnderHighFloat = "atlases/boy/UnderHighFloat.atlas";
  public final String boyChaseHighFloat = "atlases/boy/ChaseHighFloat.atlas";
  public final String boyChaseLowFloat = "atlases/boy/ChaseLowFloat.atlas";
  public final String boySpellAttack = "atlases/boy/SpellAttack.atlas";
  public final String boyComboAttack = "atlases/boy/ComboAttack.atlas";
  public final String boySpellImage = "atlases/boy/spellImage.png";
  public final String boyInventoryImageUnlocked = "atlases/boy/inventoryImageUnlocked.png";
  public final String boyInventoryImageLocked = "atlases/boy/inventoryImageLocked.png";

  public final String mageRun = "atlases/mage/Run.atlas";
  public final String mageDead = "atlases/mage/Dead.atlas";
  public final String mageIdle = "atlases/mage/Idle.atlas";
  public final String mageBasicAttack = "atlases/mage/BasicAttack.atlas";
  public final String mageUnderAttack = "atlases/mage/UnderAttack.atlas";
  public final String mageUnderHighFloat = "atlases/mage/UnderHighFloat.atlas";
  public final String mageChaseHighFloat = "atlases/mage/ChaseHighFloat.atlas";
  public final String mageSpellAttack = "atlases/mage/SpellAttack.atlas";
  public final String mageComboAttack = "atlases/mage/ComboAttack.atlas";
  public final String mageSpellImage = "atlases/mage/spellImage.png";
  public final String mageInventoryImageUnlocked = "atlases/mage/inventoryImageUnlocked.png";
  public final String mageInventoryImageLocked = "atlases/mage/inventoryImageLocked.png";
  public final String mageSpellBullet = "atlases/mage/SpellBullet.atlas";
  public final String mageComboBullet = "atlases/mage/ComboBullet.atlas";
  public final String mageBasicBullet = "atlases/mage/BasicBullet.png";


  public void load() {
    FileHandleResolver resolver = new InternalFileHandleResolver();
    manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
    manager.setLoader(BitmapFont.class, ".TTF", new FreetypeFontLoader(resolver));
    FreetypeFontLoader.FreeTypeFontLoaderParameter parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
    parms.fontFileName = font;    // path of .ttf file where that exist
    parms.fontParameters.size = 15;
    parms.fontParameters.magFilter = Texture.TextureFilter.Linear;
    parms.fontParameters.minFilter = Texture.TextureFilter.Linear;


    manager.load(font, BitmapFont.class, parms);   // font with extension, sameName will use to get from manager

    manager.load(images, TextureAtlas.class);
    manager.load(buttons, TextureAtlas.class);
    manager.load(items, TextureAtlas.class);

    manager.load(boyRun, TextureAtlas.class);
    manager.load(boyDead, TextureAtlas.class);
    manager.load(boyIdle, TextureAtlas.class);
    manager.load(boyBasicAttack, TextureAtlas.class);
    manager.load(boyUnderAttack, TextureAtlas.class);
    manager.load(boySpellAttack, TextureAtlas.class);
    manager.load(boyComboAttack, TextureAtlas.class);
    manager.load(boyUnderHighFloat, TextureAtlas.class);
    manager.load(boyChaseHighFloat, TextureAtlas.class);
    manager.load(boyChaseLowFloat, TextureAtlas.class);
    manager.load(boySpellImage, Texture.class);
    manager.load(boyInventoryImageUnlocked, Texture.class);
    manager.load(boyInventoryImageLocked, Texture.class);

    manager.load(mageRun, TextureAtlas.class);
    manager.load(mageDead, TextureAtlas.class);
    manager.load(mageIdle, TextureAtlas.class);
    manager.load(mageBasicAttack, TextureAtlas.class);
    manager.load(mageUnderAttack, TextureAtlas.class);
    manager.load(mageSpellAttack, TextureAtlas.class);
    manager.load(mageComboAttack, TextureAtlas.class);
    manager.load(mageUnderHighFloat, TextureAtlas.class);
    manager.load(mageChaseHighFloat, TextureAtlas.class);
    manager.load(mageSpellImage, Texture.class);
    manager.load(mageInventoryImageUnlocked, Texture.class);
    manager.load(mageInventoryImageLocked, Texture.class);
    manager.load(mageSpellBullet, TextureAtlas.class);
    manager.load(mageComboBullet, TextureAtlas.class);
    manager.load(mageBasicBullet, Texture.class);
  }

  public void dispose() {
    manager.dispose();
  }

}
