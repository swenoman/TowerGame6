package com.mygdx.tower.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import towerREWORK.Database.DatabaseConnection;
import towerREWORK.TowerGame;

public class DesktopLauncher {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 840;
        config.height = 480;
        config.title = "DatabaseTest";
        config.forceExit = false;
        config.vSyncEnabled = true;
        new LwjglApplication(new TowerGame(new DatabaseConnection()), config);
    }
}