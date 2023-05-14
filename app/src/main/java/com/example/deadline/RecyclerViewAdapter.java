package com.example.deadline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deadline.DataBase.DataBaseManager;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<ItemList_View> itemList_views;

    DataBaseManager dataBaseManager;

    public RecyclerViewAdapter(ArrayList<ItemList_View> itemList_views, Context context){
        this.itemList_views = itemList_views;
        this.context = context;
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
        holder.TextView_Memo.setText(itemList_views.get(position).getMemo());
        holder.TextView_Data.setText(itemList_views.get(position).getY() + "년 " + itemList_views.get(position).getM() + "월 " + itemList_views.get(position).getD() + "일");
        holder.TextView_Time.setText(itemList_views.get(position).getTime_h() + " : " + itemList_views.get(position).getTime_m());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), itemList_views.get(holder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dataBaseManager = new DataBaseManager(context);

                Toast.makeText(v.getContext(), "num: " + dataBaseManager.getDate().get(holder.getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
                dataBaseManager.setDelete(dataBaseManager.getDate().get(holder.getAdapterPosition()).getId());
                itemList_views.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
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
        TextView TextView_Time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView_Title = itemView.findViewById(R.id.TextView_Title);
            TextView_Memo = itemView.findViewById(R.id.TextView_Memo);
            TextView_Data = itemView.findViewById(R.id.TextView_Data);
            TextView_Time = itemView.findViewById(R.id.TextView_Time);

        }
    }
}
