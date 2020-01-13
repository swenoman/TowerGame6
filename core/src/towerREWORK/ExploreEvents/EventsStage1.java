//package com.mygdx.tower.ExploreEvents;
//
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.math.MathUtils;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.utils.Align;
//import com.badlogic.gdx.utils.Timer;
//import com.mygdx.tower.Assets;
//import com.mygdx.tower.PlayerManager.GameDataManager;
//import com.mygdx.tower.Screens.BattleScreen;
//import towerREWORK.Stages.HomeStage.HomeStageRework;
//
//public class EventsStage1 {
//private HomeStageRework stage;
//private Assets assets;
//private Label.LabelStyle labelStyle;
//private GameDataManager psm;
//private boolean eventsEnd = true;
//
//public EventsStage1(Assets assets, GameDataManager psm) {
//	this.psm = psm;
//	this.assets = assets;
//	labelStyle = new Label.LabelStyle(assets.manager.get(assets.font, BitmapFont.class), Color.BLUE);
//	labelStyle.background = new Skin(assets.manager.get(assets.images, TextureAtlas.class)).getDrawable("item_0");
//}
//
//private void event1() {
//	final Label label = new Label("You got coins!" + "\n" + "50", labelStyle);
//	label.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);
//	label.setWrap(false);
//	label.toFront();
//	label.setAlignment(Align.center);
//	stage.addActor(label);
//	new Timer().scheduleTask(new Timer.Task() {
//		@Override
//		public void run() {
//			label.remove();
//			eventsEnd = true;
//		}
//	}, 3);
//	psm.updateCoins(50);
//}
//
//private void event2() {
//	final Label label = new Label("You got coins!" + "\n" + "100", labelStyle);
//	label.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);
//	label.setWrap(false);
//	label.toFront();
//	label.setAlignment(Align.center);
//	stage.addActor(label);
//	new Timer().scheduleTask(new Timer.Task() {
//		@Override
//		public void run() {
//			label.remove();
//			eventsEnd = true;
//		}
//	}, 3);
//	psm.updateCoins(100);
//}
//
//private void event3() {
//}
//
//private void event4() {
//	psm.game.setScreen(new BattleScreen(assets, psm));
//}
//
//public void getEvent(HomeStageRework stage) {
//	this.stage = stage;
//	if (eventsEnd) {
//		eventsEnd = false;
//		switch (MathUtils.random(3, 4)) {
//			case 1: event1();
//				break;
//			case 2: event2();
//				break;
//			case 3: event3();
//				break;
//			case 4: event4();
//				break;
//		}
//	}
//}
//}
