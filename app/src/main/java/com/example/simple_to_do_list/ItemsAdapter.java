package com.example.simple_to_do_list;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    List<String> items;
    OnLongClickListener onLongClickListener;
    //OnClickListener onClickListener;

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

   // public interface OnClickListener{
     //   void onClickListener(int position);
    //}

    public ItemsAdapter(List<String> items, OnLongClickListener onLongClickListener){
        this.items = items;
        this.onLongClickListener = onLongClickListener;
       // this.onClickListener = onClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout to inflate a view

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent, false);
        //wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    // responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab the item at the position
        String item = items.get(position);

        // Bind the item into the specified view holder
        holder.bind(item);
    }

    // Tells the RV how many items are in the list
    @Override
    public int getItemCount() {
        if(!items.isEmpty()) {
            return items.size();
        } else
            return 0;

    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    //Notifying the listener which position was long pressed
                    onLongClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });


        }
    }
}
