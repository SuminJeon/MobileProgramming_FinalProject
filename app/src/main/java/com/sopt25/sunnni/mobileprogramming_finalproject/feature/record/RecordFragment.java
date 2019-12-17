package com.sopt25.sunnni.mobileprogramming_finalproject.feature.record;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sopt25.sunnni.mobileprogramming_finalproject.R;
import com.sopt25.sunnni.mobileprogramming_finalproject.data.Record;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {

    Context context;

    RecyclerView recyclerView;
    RecordsRvAdapter rvAdapter;
    ArrayList<Record> mList;

    public RecordFragment(Context ctx) {
        this.context = ctx;
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

        makeDummy();

        return view;
    }

    private void makeDummy(){
        mList.add(new Record("1000", "2019/12/17"));
        mList.add(new Record("1000", "2019/12/17"));
        mList.add(new Record("1000", "2019/12/17"));
        mList.add(new Record("1000", "2019/12/17"));
        mList.add(new Record("1000", "2019/12/17"));
        mList.add(new Record("1000", "2019/12/17"));
        mList.add(new Record("1000", "2019/12/17"));
        mList.add(new Record("1000", "2019/12/17"));
        mList.add(new Record("1000", "2019/12/17"));
        mList.add(new Record("1000", "2019/12/17"));

        rvAdapter.notifyDataSetChanged();
    }

}
