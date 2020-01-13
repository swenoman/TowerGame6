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
import towerREWORK.Stages.HomeStage.Groups.Inventory.InventoryRoot;

public class Heroes extends Group {
  private Array<Image> formationSlotsGroup;
  private GameDataManager gdm;
  private HeroesManager manager;
  private float nativeX;
  private float nativeY;
  private InventoryRoot root;
  private Label heroData;

  public Heroes(GameDataManager gameDataManager) {

    gdm = gameDataManager;
    manager = gdm.hm();
    Label.LabelStyle labelStyle = new Label.LabelStyle();
    labelStyle.font = gdm.assets().manager.get(gdm.assets().font);
    heroData = new Label("", labelStyle);
    heroData.setPosition(730, 220);
    addActor(heroData);
    formationSlotsGroup = new Array<Image>();
    float x = 18;
    float y = 220;
    int count = 0;
    for (int j = 0; j < 3; j++) {
      for (int i = 0; i < 7; i++) {
        Image slot = new Image(manager.getHero(count).getInventoryImage());
        slot.setSize(98, 98);
        slot.setPosition(x, y);
        slot.setName(String.valueOf(count));
        count++;
        addDragListeners(slot);
        formationSlotsGroup.add(slot);
        addActor(slot);
        x += 101;
      }
      x = 18;
      y -= 101;
    }
  }

  private void addDragListeners(Image image) {
    if (manager.getHero(Integer.parseInt(image.getName())).isAvailable()) {
      image.addListener(new ClickListener() {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          nativeX = event.getTarget().getX();
          nativeY = event.getTarget().getY();
          heroData.setText(manager.getHero(Integer.parseInt(event.getTarget().getName())).statsText());
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
          Group group = getParent().findActor("activeFormation");
          Actor actor = group.hit(xStop, yStop, true);
          root = getStage().getRoot().findActor("inventoryRoot");
          if (actor != null && actor.getName().equals("0")) {
            if (manager.checkRepeat(Integer.parseInt(event.getTarget().getName()))) {
              System.out.println("REPEAT");
            } else {
              manager.updateActiveHeroID(0, Integer.parseInt(event.getTarget().getName()));
              ((Image) ((Group) root.findActor("formation")).findActor("focusedHeroImage")).setDrawable(manager.getActiveHero(0).getInventoryImage());
              ((Image) ((Group) root.findActor("formation")).findActor("hero1")).setDrawable(manager.getActiveHero(0).getInventoryImage());
            }
          }
          if (actor != null && actor.getName().equals("1")) {
            if (manager.checkRepeat(Integer.parseInt(event.getTarget().getName()))) {
              System.out.println("REPEAT");
            } else {
              manager.updateActiveHeroID(1, Integer.parseInt(event.getTarget().getName()));
              ((Image) ((Group) root.findActor("formation")).findActor("focusedHeroImage")).setDrawable(manager.getActiveHero(1).getInventoryImage());
              ((Image) ((Group) root.findActor("formation")).findActor("hero2")).setDrawable(manager.getActiveHero(1).getInventoryImage());
            }
          }
          if (actor != null && actor.getName().equals("2")) {
            if (manager.checkRepeat(Integer.parseInt(event.getTarget().getName()))) {
              System.out.println("REPEAT");
            } else {
              manager.updateActiveHeroID(2, Integer.parseInt(event.getTarget().getName()));
              ((Image) ((Group) root.findActor("formation")).findActor("focusedHeroImage")).setDrawable(manager.getActiveHero(2).getInventoryImage());
              ((Image) ((Group) root.findActor("formation")).findActor("hero3")).setDrawable(manager.getActiveHero(2).getInventoryImage());
            }
          }
          if (actor != null && actor.getName().equals("3")) {
            if (manager.checkRepeat(Integer.parseInt(event.getTarget().getName()))) {
              System.out.println("REPEAT");
            } else {
              manager.updateActiveHeroID(3, Integer.parseInt(event.getTarget().getName()));
              ((Image) ((Group) root.findActor("formation")).findActor("focusedHeroImage")).setDrawable(manager.getActiveHero(3).getInventoryImage());
              ((Image) ((Group) root.findActor("formation")).findActor("hero4")).setDrawable(manager.getActiveHero(3).getInventoryImage());
            }
          }
//
//          if (yStop >= 407 & yStop <= 507) {
//            if (xStop >= 31 + 50 & xStop <= 31 + 150) {
//              gdm.hm().updateActiveHeroID(3, Integer.parseInt(formationSlotsGroup.get(Integer.parseInt(event.getAliveHero().getName())).getName()));
//              activeFormationGroup.get(3).setDrawable(gdm.hm().getActiveHero(3).getInventoryImage());
//              ((Image) findActor("hero4")).setDrawable(activeFormationGroup.get(3).getDrawable());
//            } else if (xStop >= 161 + 50 & xStop <= 161 + 150) {
//              gdm.hm().updateActiveHeroID(2, Integer.parseInt(formationSlotsGroup.get(Integer.parseInt(event.getAliveHero().getName())).getName()));
//              activeFormationGroup.get(3).setDrawable(gdm.hm().getActiveHero(2).getInventoryImage());
//              ((Image) findActor("hero3")).setDrawable(activeFormationGroup.get(2).getDrawable());
//            } else if (xStop >= 291 + 50 & xStop <= 291 + 150) {
//              gdm.hm().updateActiveHeroID(1, Integer.parseInt(formationSlotsGroup.get(Integer.parseInt(event.getAliveHero().getName())).getName()));
//              activeFormationGroup.get(3).setDrawable(gdm.hm().getActiveHero(1).getInventoryImage());
//              ((Image) findActor("hero2")).setDrawable(activeFormationGroup.get(1).getDrawable());
//            } else if (xStop >= 429 + 50 & xStop <= 429 + 150) {
//              gdm.hm().updateActiveHeroID(0, Integer.parseInt(formationSlotsGroup.get(Integer.parseInt(event.getAliveHero().getName())).getName()));
//              activeFormationGroup.get(3).setDrawable(gdm.hm().getActiveHero(0).getInventoryImage());
//              ((Image) findActor("hero1")).setDrawable(activeFormationGroup.get(3).getDrawable());
//            }
//          }
          event.getTarget().setPosition(nativeX, nativeY);
        }
      });
    }
  }

  private void updateFormationSlots() {
    for (int i = 0; i < formationSlotsGroup.size; i++) {
      addDragListeners(formationSlotsGroup.get(i));
    }
  }
}
