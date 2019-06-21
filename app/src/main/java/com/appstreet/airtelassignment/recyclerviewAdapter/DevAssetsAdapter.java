package com.appstreet.airtelassignment.recyclerviewAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appstreet.airtelassignment.R;
import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.appstreet.airtelassignment.views.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class DevAssetsAdapter extends RecyclerView.Adapter<DevAssetsAdapter.OptionsHolder> {

    private Activity activity;
    private List<DevAssets> itemList;

    public DevAssetsAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public OptionsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_devassets, viewGroup, false);
        return new OptionsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsHolder holder, final int i) {
        holder.assetName.setText(itemList.get(i).getUsername());
        holder.cName.setText(itemList.get(i).getRepo().getName());
        Glide.with(activity).load(itemList.get(i).getAvatar()).into(holder.icon);

        holder.mainLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailActivity.class);
//                intent.putExtra("index", i);
                intent.putExtra("devdata", itemList.get(i));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemList == null) {
            return 0;
        } else {
            return itemList.size();
        }
    }

    class OptionsHolder extends RecyclerView.ViewHolder {
        TextView assetName, cName;
        ImageView icon;
        LinearLayout mainLay;

        public OptionsHolder(@NonNull View itemView) {
            super(itemView);

            assetName = (TextView) itemView.findViewById(R.id.asset_name);
            cName = (TextView) itemView.findViewById(R.id.c_name);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            mainLay = (LinearLayout) itemView.findViewById(R.id.main_lay);
        }
    }

    public void updateList(List<DevAssets> itemList){
        this.itemList = itemList;
        notifyDataSetChanged();
    }
}
