package towerREWORK.ItemCore;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import towerREWORK.Managers.GameDataManager;

public class ItemCore {
    private int attackDamage;
    private int health;
    private String type;
    private String name;
    private int id;
    private Skin itemsSkin;
    private Drawable drawable;

    public ItemCore(GameDataManager gdm, int itemID) {
        itemsSkin = new Skin(gdm.assets().manager.get(gdm.assets().items, TextureAtlas.class));
        setItemData(itemID);
    }

    public void setItemData(int itemID) {
        id = itemID;
        switch (id) {
            case 0:
                attackDamage = 0;
                type = "noType";
                name = "notName";
                id = 0;
                health = 0;
                break;
            case 1:
                attackDamage = 1;
                type = "head";
                name = "Helmet";
                health = 10;
                break;
            case 2:
                attackDamage = 1;
                type = "body";
                name = "Armor";
                health = 40;
                break;
            case 3:
                attackDamage = 1;
                type = "taz";
                name = "Taz";
                health = 30;
                break;
            case 4:
                attackDamage = 2;
                type = "hand";
                name = "Hand";
                health = 15;
                break;
            case 5:
                attackDamage = 1;
                type = "foot";
                name = "Foot";
                health = 20;
                break;

            case 6:
                attackDamage = 1;
                type = "head";
                name = "Helmet";
                health = 10;
                break;
            case 7:
                attackDamage = 1;
                type = "body";
                name = "Armor";
                health = 40;
                break;
            case 8:
                attackDamage = 1;
                type = "taz";
                name = "Taz";
                health = 30;
                break;
            case 9:
                attackDamage = 2;
                type = "hand";
                name = "Hand";
                health = 15;
                break;
            case 10:
                attackDamage = 1;
                type = "foot";
                name = "Foot";
                health = 20;
                break;

            case 11:
                attackDamage = 6;
                type = "weapon";
                name = "Basic Double Axe";
                health = 20;
                break;

            case 12:
                attackDamage = 5;
                type = "weapon";
                name = "Basic Hammer";
                health = 20;
                break;

            case 13:
                attackDamage = 3;
                type = "weapon";
                name = "Basic Dagger";
                health = 20;
                break;

            case 14:
                attackDamage = 4;
                type = "weapon";
                name = "Basic Bow";
                health = 20;
                break;

            case 15:
                attackDamage = 4;
                type = "weapon";
                name = "Basic Sword";
                health = 20;
                break;

            case 16:
                attackDamage = 1;
                type = "weapon";
                name = "Basic Wand";
                health = 20;
                break;

            case 17:
                attackDamage = 4;
                type = "weapon";
                name = "Basic Axe";
                health = 20;
                break;

            case 18:
                attackDamage = 10;
                type = "weapon";
                name = "Double Axe";
                health = 20;
                break;

            case 19:
                attackDamage = 9;
                type = "weapon";
                name = "Hammer";
                health = 20;
                break;

            case 20:
                attackDamage = 5;
                type = "weapon";
                name = "Dagger";
                health = 20;
                break;

            case 21:
                attackDamage = 6;
                type = "weapon";
                name = "Bow";
                health = 20;
                break;

            case 22:
                attackDamage = 6;
                type = "weapon";
                name = "Sword";
                health = 20;
                break;

            case 23:
                attackDamage = 1;
                type = "weapon";
                name = "Wand";
                health = 20;
                break;

            case 24:
                attackDamage = 7;
                type = "weapon";
                name = "Axe";
                health = 20;
                break;

            case 25:
                attackDamage = 20;
                type = "weapon";
                name = "Elite Double Axe";
                health = 20;
                break;

            case 26:
                attackDamage = 15;
                type = "weapon";
                name = "Elite Sword";
                health = 20;
                break;

            case 27:
                attackDamage = 17;
                type = "weapon";
                name = "Elite Axe";
                health = 20;
                break;
        }
        drawable = itemsSkin.getDrawable("item_" + id);
    }

    public int attackDamage() {
        return attackDamage;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public int health() {
        return health;
    }

    public int id() {
        return id;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String statsText() {
        return "[ORANGE]Name: [BLACK]" + name + "\n" + "[ORANGE]Type: [BLACK]" + type + "\n" + "[ORANGE]Attack: [BLACK]" + attackDamage + "\n" + "[ORANGE]Health: [BLACK]" + health;
    }
}
