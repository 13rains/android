package com.a13rain.unodir.aqualife.controls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a13rain.unodir.aqualife.db.Aquarium;

import java.util.List;

public class AquariumListAdapter extends RecyclerView.Adapter<AquariumListAdapter.AquariumViewHolder> {
    class AquariumViewHolder extends RecyclerView.ViewHolder {

        //private final TextView wordItemView;
        ItemView mItemView;

        private AquariumViewHolder(View itemView) {
            super(itemView);
            //wordItemView=itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Aquarium> mAquariums;
    Context mContext;

    public AquariumListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AquariumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View itemView = mInflater.inflate(R.layout.listlayout, parent, false);
        ItemView itemView = new ItemView(mContext);
        return new AquariumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AquariumViewHolder holder, int position) {
        if (mAquariums != null) {
            Aquarium current = mAquariums.get(position);
            holder.mItemView.setText(0, current.getName());
            holder.mItemView.setText(0, Integer.toString(current.getX()));
            holder.mItemView.setText(0, current.getSummary());
        } else {
            //holder.mItemData.setText("No Word");
        }
    }

    public void setAquariums(List<Aquarium> aquariums) {
        mAquariums = aquariums;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAquariums != null)
            return mAquariums.size();
        else return 0;
    }
}
