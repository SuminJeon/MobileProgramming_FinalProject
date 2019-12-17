package com.sopt25.sunnni.mobileprogramming_finalproject.feature.record;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sopt25.sunnni.mobileprogramming_finalproject.R;
import com.sopt25.sunnni.mobileprogramming_finalproject.data.DBHelper;
import com.sopt25.sunnni.mobileprogramming_finalproject.data.Record;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {

    Context context;
    SQLiteDatabase recordsDB;
    DBHelper helper;

    RecyclerView recyclerView;
    RecordsRvAdapter rvAdapter;
    ArrayList<Record> mList;

    public RecordFragment(Context ctx, SQLiteDatabase db) {
        this.context = ctx;
        this.recordsDB = db;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);

        recyclerView = view.findViewById(R.id.rv_records);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        mList = new ArrayList<Record>();
        rvAdapter = new RecordsRvAdapter(mList);
        recyclerView.setAdapter(rvAdapter);

        helper = new DBHelper(context, "recordsDB", null,1);
        recordsDB = helper.getReadableDatabase();

        dbToList();

        return view;
    }

    private void dbToList() {
        String sql = "select mandarineCount,date from DBTABLE";
        Cursor cursor = recordsDB.rawQuery(sql, null);
        int numData;
        if (cursor != null) {
            numData = cursor.getCount();
            for (int i = 0; i < numData; i++) {
                cursor.moveToNext();
                mList.add(new Record(cursor.getString(0), cursor.getString(1)));
            }
            cursor.close();
        }

        rvAdapter.notifyDataSetChanged();
    }

}
