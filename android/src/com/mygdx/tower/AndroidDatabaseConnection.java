package com.mygdx.tower;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.badlogic.gdx.Gdx;
import com.mygdx.tower.Database.ActionResolver;
import com.mygdx.tower.Database.LocalSlotsDatabase;

import java.sql.Connection;

public class AndroidDatabaseConnection implements ActionResolver {

private DatabaseHelper dbhelper;
private LocalSlotsDatabase localSlotsDatabase;

public AndroidDatabaseConnection(Context appContext) {
	dbhelper = new DatabaseHelper(appContext);
	localSlotsDatabase = new LocalSlotsDatabase();
}

@Override
public Connection getConnection() {
	return null;
}

@Override
public LocalSlotsDatabase getLocalSlotsDatabase() {
	Cursor cursor = dbhelper.getAllFromDatabase(dbhelper.getReadableDatabase());
	if (cursor.moveToFirst()) {
		do {
			localSlotsDatabase.setDatabase(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
		} while (cursor.moveToNext());
	}
	cursor.close();
	return localSlotsDatabase;
}

@Override
public void putItem(String bagOrEquip, int slot_id, int item_id) {
	ContentValues cv = new ContentValues();
	cv.put("ITEM_ID", item_id);
	String slot = "slot_" + (slot_id + 1);
	if (bagOrEquip.equals("bag")) {
		slot = "bag_slot_" + (slot_id + 1);
	}
	if (bagOrEquip.equals("hero")) {
		slot = "hero_slot_" + (slot_id + 1);
	}
	if (bagOrEquip.equals("store")) {
		slot = "store_slot_" + (slot_id + 1);
	}
	if (bagOrEquip.equals("stage")) {
		slot = "current_stage";
	}
	if (bagOrEquip.equals("coins")) {
		slot = "coins";
	}
	Gdx.app.log("ADBC: putItem: ", "trying to update: db name: " + dbhelper.getWritableDatabase() + " container: " + String.valueOf(cv) + " column: SLOT_ID " + " row: " + slot);
	dbhelper.update(dbhelper.getWritableDatabase(), cv, "SLOT_ID", slot);
}

@Override
public void saveData(LocalSlotsDatabase localSlotsDatabase) {

}

@Override
public void saveStoreData(LocalSlotsDatabase localSlotsDatabase) {
	String[][] database = localSlotsDatabase.getDatabase();
	for (int i = 0; i < 24; i++) {
		ContentValues cv = new ContentValues();
		cv.put("ITEM_ID", Integer.valueOf(database[i][2]));
		String slot = "store_slot" + (i + 1);
		dbhelper.update(dbhelper.getWritableDatabase(), cv, "SLOT_ID", slot);
	}
}
}