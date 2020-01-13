package com.mygdx.tower;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.badlogic.gdx.Gdx;

public class DatabaseHelper extends SQLiteOpenHelper {

private static final String DATABASE_NAME = "SlotsDatabase.db";
private static final int DATABASE_VERSION = 1;
private static final String TABLE_NAME = "SLOTS";
private static final String COLUMN_ID = "ID";
private static final String COLUMN_SLOT_ID = "SLOT_ID";
private static final String COLUMN_ITEM_ID = "ITEM_ID";
private static final String COLUMN_STATE_ID = "STATE_ID";
private static final String COLUMN_RESERVED_1 = "RESERVED_1";
private static final String COLUMN_RESERVED_2 = "RESERVED_2";

public DatabaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) {
	db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_SLOT_ID + " text not null," + COLUMN_ITEM_ID + " INT," + COLUMN_STATE_ID + " INT," + COLUMN_RESERVED_1 + " INT," + COLUMN_RESERVED_2 + " INT);");
	for (int i = 1; i <= 32; i++) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_SLOT_ID, "slot_" + i);
		cv.put(COLUMN_ITEM_ID, 0);
		cv.put(COLUMN_STATE_ID, 0);
		cv.put(COLUMN_RESERVED_1, 0);
		cv.put(COLUMN_RESERVED_2, 0);
		db.insert(TABLE_NAME, null, cv);
	}
	for (int i = 1; i <= 32; i++) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_SLOT_ID, "bag_slot_" + i);
		cv.put(COLUMN_ITEM_ID, 0);
		cv.put(COLUMN_STATE_ID, 0);
		cv.put(COLUMN_RESERVED_1, 0);
		cv.put(COLUMN_RESERVED_2, 0);
		db.insert(TABLE_NAME, null, cv);
	}
	for (int i = 1; i <= 25; i++) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_SLOT_ID, "hero_slot" + i);
		cv.put(COLUMN_ITEM_ID, 1);
		cv.put(COLUMN_STATE_ID, 0);
		cv.put(COLUMN_RESERVED_1, 0);
		cv.put(COLUMN_RESERVED_2, 0);
		db.insert(TABLE_NAME, null, cv);
	}
	
	for (int i = 1; i <= 24; i++) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_SLOT_ID, "store_slot" + i);
		cv.put(COLUMN_ITEM_ID, 1);
		cv.put(COLUMN_STATE_ID, 0);
		cv.put(COLUMN_RESERVED_1, 0);
		cv.put(COLUMN_RESERVED_2, 0);
		db.insert(TABLE_NAME, null, cv);
	}
	
	ContentValues stage = new ContentValues();
	stage.put(COLUMN_SLOT_ID, "current_stage");
	stage.put(COLUMN_ITEM_ID, 1);
	stage.put(COLUMN_STATE_ID, 0);
	stage.put(COLUMN_RESERVED_1, 0);
	stage.put(COLUMN_RESERVED_2, 0);
	db.insert(TABLE_NAME, null, stage);
	
	ContentValues coins = new ContentValues();
	coins.put(COLUMN_SLOT_ID, "coins");
	coins.put(COLUMN_ITEM_ID, 0);
	coins.put(COLUMN_STATE_ID, 0);
	coins.put(COLUMN_RESERVED_1, 0);
	coins.put(COLUMN_RESERVED_2, 0);
	db.insert(TABLE_NAME, null, coins);
	
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
	db.execSQL(sql);
	onCreate(db);
}

public void insert(SQLiteDatabase db, ContentValues cv) {
	db.insert(TABLE_NAME, null, cv);
}

public void update(SQLiteDatabase db, ContentValues cv, String column, String row) {
	int x = db.update(TABLE_NAME, cv, column + " = ?", new String[]{row});
	Gdx.app.log("Helper: update: updated count ", String.valueOf(x));
}

public int getItemId(SQLiteDatabase db, int slot_id) {
	Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ITEM_ID}, "id = ?", new String[]{Integer.toString(slot_id)}, null, null, null);
	if (cursor != null) {
		if (cursor.moveToFirst()) {
			int x = cursor.getInt(0);
			cursor.close();
			return x;
		}
	}
	return -1;
}

public Cursor getAllFromDatabase(SQLiteDatabase db) {
	return db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_SLOT_ID, COLUMN_ITEM_ID}, null, null, null, null, null);
}
}