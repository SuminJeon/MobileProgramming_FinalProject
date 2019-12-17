package com.sopt25.sunnni.mobileprogramming_finalproject.feature.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sopt25.sunnni.mobileprogramming_finalproject.R;
import com.sopt25.sunnni.mobileprogramming_finalproject.data.Record;

import java.util.ArrayList;

public class RecordsRvAdapter extends RecyclerView.Adapter<RecordsRvAdapter.RecordViewHolder>{

    public ArrayList<Record> mList;

    public class RecordViewHolder extends RecyclerView.ViewHolder{
        protected TextView mCount, mDate;

        public RecordViewHolder(View v){
            super(v);
            this.mCount = (TextView) v.findViewById(R.id.tv_mandarineCount);
            this.mDate = (TextView) v.findViewById(R.id.tv_date);
        }

        public void binding(Record record){
            mCount.setText(record.mandarineCount);
            mDate.setText(record.date);
        }
    }

    //constructor
    public RecordsRvAdapter(ArrayList<Record> list){
        this.mList = list;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_record, parent, false);
        RecordViewHolder recordViewHolder = new RecordViewHolder(view);

        return recordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        holder.binding(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
