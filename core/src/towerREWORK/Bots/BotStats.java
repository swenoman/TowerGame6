package towerREWORK.Bots;

public class BotStats {

  private String heroName;
  private int attackDamage;
  private int spellDamage;
  private int hp;
  private int defence;

  public BotStats(int stage, String heroName) {
    this.heroName = heroName;
    setStats(stage);
  }

  private void setStats(int stage) {
    switch (stage) {
      case 1:
        attackDamage = 50;
        spellDamage = 50;
        hp = 10;
        defence = 5;
        break;
      case 2:
        attackDamage = 60;
        spellDamage = 60;
        hp = 100;
        defence = 6;
        break;
      case 3:
        attackDamage = 30;
        spellDamage = 70;
        hp = 10;
        defence = 7;
        break;
      case 4:
        attackDamage = 30;
        spellDamage = 80;
        hp = 10;
        defence = 8;
        break;
      case 5:
        attackDamage = 30;
        spellDamage = 90;
        hp = 10;
        defence = 9;
        break;
      default:
        attackDamage = 90;
        spellDamage = 90;
        hp = 10;
        defence = 9;
        break;
    }
  }

  public String getHeroName() {
    return heroName;
  }

  public int getAttackDamage() {
    return attackDamage;
  }

  public int getSpellDamage() {
    return spellDamage;
  }

  public int getHp() {
    return hp;
  }

  public int getDefence() {
    return defence;
  }

}
