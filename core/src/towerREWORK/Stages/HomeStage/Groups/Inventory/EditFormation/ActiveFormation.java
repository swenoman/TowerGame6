package towerREWORK.Stages.HomeStage.Groups.Inventory.EditFormation;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;

import towerREWORK.Managers.GameDataManager;
import towerREWORK.Managers.HeroesManager;

public class ActiveFormation extends Group {
  private GameDataManager gdm;
  private Array<Image> activeFormationGroup;
  private Label heroData;
  private HeroesManager manager;

  public ActiveFormation(GameDataManager gameDataManager) {
    gdm = gameDataManager;
    manager = gdm.hm();
    setName("activeFormation");
    formationGroupInit();
  }

  private void formationGroupInit() {
    Label.LabelStyle labelStyle = new Label.LabelStyle();
    labelStyle.font = gdm.assets().manager.get(gdm.assets().font);
    heroData = new Label("", labelStyle);
    heroData.setPosition(730, 400);
    addActor(heroData);
    activeFormationGroup = new Array<Image>();
    int x = 421;
    for (int i = 0; i < 4; i++) {
      Image activeSlot = new Image(gdm.hm().getActiveHero(i).getInventoryImage());
      activeSlot.setSize(98, 98);
      activeSlot.setPosition(x, 357);
      activeSlot.setName(String.valueOf(i));
      addDragListeners(activeSlot);
      x -= 130;
      activeFormationGroup.add(activeSlot);
      addActor(activeSlot);
    }
  }

  private float nativeX;
  private float nativeY;

  private void addDragListeners(Image image) {
    if (gdm.hm().getHero(Integer.parseInt(image.getName())).isAvailable()) {
      image.addListener(new ClickListener() {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          nativeX = event.getTarget().getX();
          nativeY = event.getTarget().getY();
          heroData.setText(manager.getActiveHero(Integer.parseInt(event.getTarget().getName())).statsText());
          return super.touchDown(event, x, y, pointer, button);
        }
      });
      image.addListener(new DragListener() {
        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
          super.drag(event, x, y, pointer);
          event.getTarget().toFront();
          event.getTarget().moveBy(x - event.getTarget().getWidth() / 2, y - event.getTarget().getHeight() / 2);
        }

        @Override
        public void dragStop(InputEvent event, float x, float y, int pointer) {
          super.dragStop(event, x, y, pointer);
          float xStop = event.getTarget().getX() + (event.getTarget().getRight() - event.getTarget().getX()) / 2;
          float yStop = event.getTarget().getY() + (event.getTarget().getTop() - event.getTarget().getY()) / 2;
          event.getTarget().setPosition(nativeX, nativeY);
          Actor actor = getParent().findActor("activeFormation").hit(xStop, yStop, true);
          Group root = getStage().getRoot().findActor("inventoryRoot");
          if (actor != null) {
            if (actor.getName().equals(event.getTarget().getName())) {
              System.out.println("as");
            } else {
              if (actor.getName().equals("0")) {
                manager.swapActiveHeroes(Integer.parseInt(event.getTarget().getName()), 0);
                ((Image) ((Group) root.findActor("formation")).findActor("focusedHeroImage")).setDrawable(manager.getActiveHero(0).getInventoryImage());
                ((Image) ((Group) root.findActor("formation")).findActor("hero1")).setDrawable(manager.getActiveHero(0).getInventoryImage());
                ((Image) findActor("0")).setDrawable(manager.getActiveHero(0).getInventoryImage());
              }
              if (actor.getName().equals("1")) {
                manager.swapActiveHeroes(Integer.parseInt(event.getTarget().getName()), 1);
                ((Image) ((Group) root.findActor("formation")).findActor("focusedHeroImage")).setDrawable(manager.getActiveHero(1).getInventoryImage());
                ((Image) ((Group) root.findActor("formation")).findActor("hero2")).setDrawable(manager.getActiveHero(1).getInventoryImage());
                ((Image) findActor("1")).setDrawable(manager.getActiveHero(1).getInventoryImage());

              }
              if (actor.getName().equals("2")) {
                manager.swapActiveHeroes(Integer.parseInt(event.getTarget().getName()), 2);
                ((Image) ((Group) root.findActor("formation")).findActor("focusedHeroImage")).setDrawable(manager.getActiveHero(2).getInventoryImage());
                ((Image) ((Group) root.findActor("formation")).findActor("hero3")).setDrawable(manager.getActiveHero(2).getInventoryImage());
                ((Image) findActor("2")).setDrawable(manager.getActiveHero(2).getInventoryImage());

              }
              if (actor.getName().equals("3")) {
                manager.swapActiveHeroes(Integer.parseInt(event.getTarget().getName()), 3);
                ((Image) ((Group) root.findActor("formation")).findActor("focusedHeroImage")).setDrawable(manager.getActiveHero(3).getInventoryImage());
                ((Image) ((Group) root.findActor("formation")).findActor("hero4")).setDrawable(manager.getActiveHero(3).getInventoryImage());
                ((Image) findActor("3")).setDrawable(manager.getActiveHero(3).getInventoryImage());
              }
              int heroNum = Integer.parseInt(event.getTarget().getName()) + 1;
              ((Image) ((Group) root.findActor("formation")).findActor("hero" + heroNum)).setDrawable(manager.getActiveHero(Integer.parseInt(event.getTarget().getName())).getInventoryImage());
              ((Image) event.getTarget()).setDrawable(manager.getActiveHero(Integer.parseInt(event.getTarget().getName())).getInventoryImage());
            }
          }
        }
      });
    }
  }


//  private void updateFormationSlots() {
//    for (int i = 0; i < formationSlotsGroup.size; i++) {
//      addDragListeners(formationSlotsGroup.get(i));
//    }
//  }
}
