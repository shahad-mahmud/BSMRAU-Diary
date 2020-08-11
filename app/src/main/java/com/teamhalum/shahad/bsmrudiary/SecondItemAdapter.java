package com.teamhalum.shahad.bsmrudiary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SecondItemAdapter extends RecyclerView.Adapter<SecondItemAdapter.ItemViewHolder> {

    Context context;
    List<Items> itemList;

    public SecondItemAdapter(Context context, List<Items> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_layout, viewGroup, false);

        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int i) {
        final Items items = itemList.get(i);

        itemViewHolder.titel.setText(items.getItem_name());

        itemViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, itemList.get(i).getItem_name(), Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("name", itemList.get(i).getItem_name());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView titel;
        RelativeLayout layout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            titel = itemView.findViewById(R.id.titleId);
            layout = itemView.findViewById(R.id.item_layout);
        }

    }

}
