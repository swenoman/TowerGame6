package towerREWORK.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection implements ActionResolver {
  private Connection connection;
  private boolean resetDone = false;

  public DatabaseConnection() {
    connection = getConnection();
  }

  @Override
  public Connection getConnection() {
    String url = "jdbc:sqlite:database.db";
    try {
      Class.forName("org.sqlite.JDBC");
      return DriverManager.getConnection(url);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public synchronized int isFirstTry() {
    int state = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT state FROM firstTry WHERE id LIKE " + "0");
      state = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (state == -1)
      System.out.println("Ошбика в базе firstTry id = -1");
    return state;
  }

  @Override
  public synchronized int getHeroLevel(int heroID) {
    int level = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT LVL FROM heroes WHERE id LIKE " + heroID);
      level = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (level == -1)
      System.out.println("Ошбика в базе(getHeroLevel): heroID = " + heroID + " максимальный ID не должен превышать 24");
    return level;
  }

  @Override
  public synchronized int getHeroLevelProgress(int heroID) {
    int levelProgress = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT LVL_PROGRESS FROM heroes WHERE id LIKE " + heroID);
      levelProgress = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (levelProgress != -1)
      return levelProgress;
    else {
      System.out.println("Ошбика в базе (getHeroLevelProgress): heroID = " + heroID + " максимальный ID не должен превышать 24");
      return levelProgress;
    }
  }

  @Override
  public synchronized boolean isHeroAvailable(int heroID) {
    boolean available = false;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT AVAILABLE FROM heroes WHERE id LIKE " + heroID);
      available = resultSet.getInt(1) == 1;
      System.out.println(available);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return available;
  }

  @Override
  public synchronized int getActiveHeroID(int slotNum) {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT heroID FROM activeHeroes WHERE id LIKE " + slotNum);
      itemID = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (itemID == -1) {
      System.out.println("Ошбика в базе (getActiveHero): slotNum = " + slotNum + " максимальный slotNum не должен превышать 4");
    }
    return itemID;
  }

  @Override
  public synchronized int getArmedItemID(int heroNum, int slotNum) {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT slot" + slotNum + " FROM activeHeroes WHERE id LIKE " + heroNum);
      itemID = resultSet.getInt(1);
      System.out.println("DB: getARMITEM: heroNum = " + heroNum + " slotNum = " + slotNum + " itemID = " + itemID);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (itemID == -1) {
      System.out.println("Ошбика в базе (getArmedItemID): heroNum = " + heroNum + " максимальный heroNum не должен превышать 4");
      System.out.println("-------------- slotNum = " + slotNum + " максимальный slotNum не должен превышать 8");
    }
    return itemID;
  }

  @Override
  public synchronized int getBagItemID(int slotNum) {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT ITEM_ID FROM bagItems WHERE id LIKE " + slotNum);
      itemID = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (itemID == -1) {
      System.out.println("Ошбика в базе (getBagItemID): slotNum = " + slotNum + " максимальный slotNum не должен превышать 31");
    }
    return itemID;
  }

  @Override
  public synchronized void updateHeroLevel(int heroID, int level) {
    String SQL = "UPDATE heroes SET LVL  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, level);
      preparedStatement.setInt(2, heroID);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: LVL updated: " + level + " heroID: " + heroID);
    }
  }

  @Override
  public synchronized void updateHeroLevelProgress(int heroID, int levelProgress) {
    String SQL = "UPDATE heroes SET LVL_PROGRESS  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, levelProgress);
      preparedStatement.setInt(2, heroID);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: LVL_PROGRESS updated: " + levelProgress + " heroID: " + heroID);
    }
  }

  @Override
  public synchronized void updateHeroAvailable(int heroID, boolean available) {
    int tmp;
    if (available) {
      tmp = 1;
    } else tmp = 0;

    String SQL = "UPDATE heroes SET AVAILABLE  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, tmp);
      preparedStatement.setInt(2, heroID);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: heroID " + heroID + ", AVAILABLE updated: " + available);
    }
  }

  @Override
  public synchronized void updateActiveHeroID(int slotNum, int heroID) {

    String SQL = "UPDATE activeHeroes SET heroID  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, heroID);
      preparedStatement.setInt(2, slotNum);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: activeHeroes " + slotNum + ", updated: " + heroID);
    }
  }

  @Override
  public synchronized void updateArmedItemID(int heroNum, int slotNum, int itemID) {
    String SQL = "UPDATE activeHeroes SET slot" + slotNum + "  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, itemID);
      preparedStatement.setInt(2, heroNum);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: heroNum " + heroNum + ", slot" + slotNum + ", updated: ITEM_ID" + itemID);
    }
  }

  @Override
  public synchronized void updateBagItemID(int slotNum, int itemID) {
    String SQL = "UPDATE bagItems SET ITEM_ID  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, itemID);
      preparedStatement.setInt(2, slotNum);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: bag ITEM_ID updated: " + itemID + ", slotNum: " + slotNum);
    }
  }

  @Override
  public synchronized int getCoins() {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT COINS FROM coins WHERE id LIKE 0");
      itemID = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemID;
  }

  @Override
  public synchronized void updateCoins(int newCoins) {
    String SQL = "UPDATE coins SET COINS  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, newCoins);
      preparedStatement.setInt(2, 0);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: COINS updated: " + newCoins);
    }
  }

  @Override
  public synchronized int currentStage() {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT CURRENT_STAGE FROM stages WHERE id LIKE 0");
      itemID = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemID;
  }

  @Override
  public synchronized int maxStage() {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT MAX_STAGE FROM stages WHERE id LIKE 0");
      itemID = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemID;
  }

  @Override
  public synchronized void updateCurrentStage(int stageNum) {
    String SQL = "UPDATE stages SET CURRENT_STAGE  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, stageNum);
      preparedStatement.setInt(2, 0);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: currentStage updated: " + stageNum);
    }
  }

  @Override
  public synchronized void updateMaxStage(int newMaxStage) {
    String SQL = "UPDATE stages SET MAX_STAGE  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, newMaxStage);
      preparedStatement.setInt(2, 0);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: max_Stage updated: " + newMaxStage);
    }
  }

  @Override
  public synchronized int getCurrentBlacksmith() {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT ITEM_ID FROM blacksmithStore WHERE id LIKE " + 9);
      itemID = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemID;
  }

  @Override
  public synchronized void updateCurrentBlacksmith(int id) {
    String SQL = "UPDATE blacksmithStore SET ITEM_ID  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, id);
      preparedStatement.setInt(2, 9);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: updateCurrentBlacksmith updated: " + id);
    }
  }

  @Override
  public synchronized int getBlacksmithLevel(int id) {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT LVL FROM blacksmith WHERE id LIKE " + id);
      itemID = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemID;
  }

  @Override
  public synchronized int getBlacksmithLevelProgress(int id) {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT LVL_PROGRESS FROM blacksmith WHERE id LIKE " + id);
      itemID = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemID;
  }

  @Override
  public synchronized void updateBlacksmithLevel(int id, int level) {
    String SQL = "UPDATE blacksmith SET LVL  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, level);
      preparedStatement.setInt(2, id);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: blacksmith(id = " + id + ") LVL updated: " + level);
    }
  }

  @Override
  public synchronized void updateBlacksmithLevelProgress(int id, int levelProgress) {
    String SQL = "UPDATE blacksmith SET LVL_PROGRESS  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, levelProgress);
      preparedStatement.setInt(2, id);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: blacksmith(id = " + id + ") LVL_PROGRESS updated: " + levelProgress);
    }
  }

  @Override
  public synchronized int getBlacksmithStoreItemID(int slot) {
    int itemID = -1;
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT ITEM_ID FROM blacksmithStore WHERE id LIKE " + slot);
      itemID = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemID;
  }

  @Override
  public synchronized void updateBlacksmithStoreItemID(int slot, int itemID) {
    String SQL = "UPDATE blacksmithStore SET ITEM_ID  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, itemID);
      preparedStatement.setInt(2, slot);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Database: blacksmithStore(slot = " + slot + ") ITEM_ID updated: " + itemID);
    }
  }

  @Override
  public synchronized void setFirstTry(int x) {
    String SQL = "UPDATE firstTry SET state  = ? WHERE ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, x);
      preparedStatement.setInt(2, 0);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      if (x == 0) {
        resetData();
      }
      System.out.println("Database: firstTry set to " + x);
    }
  }

  @Override
  public synchronized void resetData() {
    resetDone = false;
    for (int i = 0; i < 4; i++) {
      updateActiveHeroID(i, -1);
    }
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 8; j++) {
        updateArmedItemID(i, j, 0);
      }
    }
    for (int i = 0; i < 32; i++) {
      updateBagItemID(i, 0);
    }
    // Сделать сброс кузнеца когда доведу до ума эту функцию
    updateCoins(0);
    for (int i = 0; i < 25; i++) {
      updateHeroLevel(i, 1);
      updateHeroLevelProgress(i, 1);
      updateHeroAvailable(i, false);
    }
    updateHeroAvailable(0, true);
    updateHeroAvailable(1, true);
    updateHeroAvailable(2, true);
    updateCurrentStage(1);
    updateMaxStage(1);
    resetDone = true;
    System.out.println("data reset done");
  }

  @Override
  public boolean isResetDone() {
    return resetDone;
  }
}