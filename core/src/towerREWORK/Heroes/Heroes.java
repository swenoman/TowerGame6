package towerREWORK.Heroes;

import com.badlogic.gdx.utils.Array;

import towerREWORK.HeroCore.HeroCore;
import towerREWORK.Managers.GameDataManager;

public class Heroes {
  private Array<HeroCore> heroes;
  private GameDataManager gdm;

  public Heroes(GameDataManager gdm) {
    this.gdm = gdm;
    heroes = new Array<>();
    heroes.add(new Boy(gdm, gdm.database().isHeroAvailable(0)));
    heroes.add(new Mage(gdm, gdm.database().isHeroAvailable(1)));
    heroes.add(new Boy2(gdm, gdm.database().isHeroAvailable(2)));
    heroes.add(new Boy3(gdm, gdm.database().isHeroAvailable(3)));
  }

  public HeroCore getHero(int heroID) {
    if (heroID < 0) {
      return new Nullable(gdm, false);
    }
    return heroes.get(heroID);
  }
}