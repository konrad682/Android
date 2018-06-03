package com.example.konrad.todolist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konrad.todolist.Task.AddTask;

import java.util.Calendar;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<AddTask> mCurrencies;
    private RecyclerView mRecyclerView;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mCode;
      //  public TextView mRate;
        public MyViewHolder(View pItem) {
            super(pItem);
            mName = (TextView) pItem.findViewById(R.id.currency_name);
            mCode = (TextView) pItem.findViewById(R.id.currency_code);
          //  mRate = (TextView) pItem.findViewById(R.id.currency_rate);
            //pItem.setOnCreateContextMenuListener(this);
        }
      /*  @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {

            menu.setHeaderTitle("Select The Action");
            menu.add(0, v.getId(), 0, "Delete");//groupId, itemId, order, title
            menu.add(0, v.getId(), 0, "Edit");


        }*/

    }


    public MyAdapter(List<AddTask> pCurrencies, RecyclerView pRecyclerView,Context context){
        mCurrencies = pCurrencies;
        mRecyclerView = pRecyclerView;
        this.context = context;
    }
    public MyAdapter(List<AddTask> pCurrencies, RecyclerView pRecyclerView){
        mCurrencies = pCurrencies;
        mRecyclerView = pRecyclerView;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.currency_layout, viewGroup, false);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
                mCurrencies.remove(positionToDelete);

                notifyItemRemoved(positionToDelete);

                return true;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
              //  mCurrencies.remove(positionToDelete);
                Toast.makeText(context, mCurrencies.get(positionToDelete).getDescription(), Toast.LENGTH_LONG).show();

               // notifyItemRemoved(positionToDelete);

            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        checkdate(i,viewHolder);
        AddTask article = mCurrencies.get(i);
        ((MyViewHolder) viewHolder).mName.setText(article.getName());
        if(mCurrencies.get(i).data.length() > 4){
        ((MyViewHolder) viewHolder).mCode.setText(article.getData());}
        //((MyViewHolder) viewHolder).mRate.setText(String.valueOf(article.getData()));
    }
    public void checkdate(final int i,RecyclerView.ViewHolder viewHolder){

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if(mCurrencies.get(i).data.length() > 3){
        String[] parts = mCurrencies.get(i).data.split("/");
        int month1 = Integer.parseInt(parts[0]);
        int day1 = Integer.parseInt(parts[1]);
        int year1 = Integer.parseInt(parts[2]);
        if(year > year1){
            ((MyViewHolder) viewHolder).mName.setTextColor(Color.parseColor("#b20000"));
        }
        else if(month > month1 && year == year1){
            ((MyViewHolder) viewHolder).mName.setTextColor(Color.parseColor("#b20000"));
        }
        else if(day > day1 && year == year1 && month == month1){
            ((MyViewHolder) viewHolder).mName.setTextColor(Color.parseColor("#b20000"));
        }}
    }
    @Override
    public int getItemCount() {
        return mCurrencies.size();
    }
}