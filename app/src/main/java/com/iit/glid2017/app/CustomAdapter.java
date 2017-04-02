package com.iit.glid2017.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by slim on 07/03/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ItemViewHolder> {


    private ArrayList<DataModel> mDataList;
    private LayoutInflater mLayoutInflater;
    //private int position=0;
    // Context context;

    public CustomAdapter(Context context, ArrayList<DataModel> dataList) {
        mDataList = dataList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.data_item_layout, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Log.v("morsi", "onBindViewHolder");
        final DataModel dataModel = mDataList.get(position);

        holder.mTitle.setText(dataModel.getTitle());
        holder.mDescription.setText(dataModel.getDescription());
        holder.mImage.setImageResource(dataModel.getImageRes());
        holder.mCheck.setChecked(dataModel.isCheck());


        //final int x=holder.getAdapterPosition();
        holder.mCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // View view = v;
                //View parent = (View) v.getParent();
                //while (!(parent instanceof RecyclerView)){
                //view=parent;
                //parent = (View) parent.getParent();
                //}
                //String position = ""+holder.getAdapterPosition();
                //Log.v("position :", position);
                //DataModel d = mDataList.get(x);

                dataModel.reverseCheck();
            }
        });


    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mDescription;
        ImageView mImage;
        CheckBox mCheck;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDescription = (TextView) itemView.findViewById(R.id.description);
            mImage = (ImageView) itemView.findViewById(R.id.image);
            mCheck = (CheckBox) itemView.findViewById(R.id.checkDelete);


        }
    }
}
