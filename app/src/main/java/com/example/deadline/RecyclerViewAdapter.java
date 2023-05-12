package com.example.deadline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    ArrayList<ItemList_View> itemList_views;

    public RecyclerViewAdapter(ArrayList<ItemList_View> itemList_views){
        this.itemList_views = itemList_views;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.lint_item, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        holder.TextView_Title.setText(itemList_views.get(position).getTitle());
        holder.TextView_Memo.setText("test");
        holder.TextView_Data.setText(itemList_views.get(position).getY() + "년 " + itemList_views.get(position).getM() + "월 " + itemList_views.get(position).getD() + "일");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), itemList_views.get(holder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), itemList_views.get(holder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList_views.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView TextView_Title;
        TextView TextView_Memo;
        TextView TextView_Data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView_Title = itemView.findViewById(R.id.TextView_Title);
            TextView_Memo = itemView.findViewById(R.id.TextView_Memo);
            TextView_Data = itemView.findViewById(R.id.TextView_Data);


        }
    }
}
