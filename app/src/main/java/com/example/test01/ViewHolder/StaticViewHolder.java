package com.example.test01.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test01.Common.Common;
import com.example.test01.Interface.ItemClickListener;
import com.example.test01.R;

public class StaticViewHolder  extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnCreateContextMenuListener
{

    public TextView statisticFood_name;
    public TextView statistic;

    private ItemClickListener itemClickListener;

    public StaticViewHolder(@NonNull View itemView) {
        super(itemView);

        statisticFood_name=(TextView)itemView.findViewById(R.id.statistic_name);
        statistic=(TextView)itemView.findViewById(R.id.statistic);
        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener (ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

    }
}