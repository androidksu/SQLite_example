package com.example.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText name, number;
	TextView tv;

	SQLiteDatabase db;
	SQLite dbHelper;
	String DATABASE_TABLE = "table1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name = (EditText) findViewById(R.id.editText1);
		number = (EditText) findViewById(R.id.editText2);
		tv = (TextView) findViewById(R.id.textView3);
	}

	// 新增
	public void bt1(View v) {
		String aa = name.getText().toString();
		String bb = number.getText().toString();
		// 開啟資料庫
		dbHelper = new SQLite(getBaseContext());
		db = dbHelper.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put("Name", aa);
		cv.put("Number", bb);
		db.insert(DATABASE_TABLE, null, cv);

		dbHelper.close();
		db.close();
		tv.setText("新增一筆資料");
	}

	// 修改
	public void bt2(View v) {
		String aa = name.getText().toString();
		String bb = number.getText().toString();
		// 開啟資料庫
		dbHelper = new SQLite(getBaseContext());
		db = dbHelper.getWritableDatabase();
		// 寫入SQLite
		ContentValues cv = new ContentValues();
		cv.put("Number", bb);
		//
		db.update(DATABASE_TABLE, cv, "Name='" + aa + "'", null);
		// 關閉資料庫
		dbHelper.close();
		db.close();
		tv.setText("修改一筆資料");
	}

	// 移除
	public void bt3(View v) {
		String aa = name.getText().toString();
		// 開啟資料庫
		dbHelper = new SQLite(getBaseContext());
		db = dbHelper.getWritableDatabase();
		//
		db.delete(DATABASE_TABLE, "Name='" + aa + "'", null);
		// 關閉資料庫
		dbHelper.close();
		db.close();
		tv.setText("移除一筆資料");

	}

	// 查詢
	public void bt4(View v) {
		String[] item = { "_id", "Name", "Number" };
		StringBuffer sb = new StringBuffer();
		sb.append("ID");
		sb.append("\t");
		sb.append("姓名");
		sb.append("\t\t\t");
		sb.append("學號");
		sb.append("\n");

		// 開起資料庫
		dbHelper = new SQLite(getBaseContext());
		db = dbHelper.getWritableDatabase();

		Cursor c = db.query(DATABASE_TABLE, item, null, null, null, null, null);
		c.moveToFirst();

		for (int i = 0; i < c.getCount(); i++) {
			sb.append(c.getString(0));
			sb.append("\t");
			sb.append(c.getString(1));
			sb.append("\t");
			sb.append(c.getString(2));
			sb.append("\n");
			c.moveToNext();

		}
		tv.setText(sb);

		dbHelper.close();
		db.close();

	}

}
