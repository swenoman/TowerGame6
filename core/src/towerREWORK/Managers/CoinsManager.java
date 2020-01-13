package towerREWORK.Managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class CoinsManager {
  private Label coins;
  private int coinsInt;
  private GameDataManager gdm;

  public CoinsManager(GameDataManager gdm) {
    this.gdm = gdm;
    coinsInt = gdm.database().getCoins();
    Label.LabelStyle style = new Label.LabelStyle();
    style.font = gdm.assets().manager.get(gdm.assets().font, BitmapFont.class);
    style.fontColor = Color.BLUE;
    coins = new Label("COINS: ", style);
    coins.setText("COINS: " + coinsInt);
  }

  public void updateCoins(int coins) {
    coinsInt += coins;
    this.coins.setText("COINS: " + coinsInt);
    new Thread(new Runnable() {
      @Override
      public void run() {
        gdm.database().updateCoins(coinsInt);
      }
    }).start();
  }

  public Label getLabel() {
    return coins;
  }

  public int coins() {
    return coinsInt;
  }
}
