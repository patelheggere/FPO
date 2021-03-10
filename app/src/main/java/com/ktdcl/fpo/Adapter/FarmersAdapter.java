package com.ktdcl.fpo.Adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.ktdcl.fpo.R;
import com.ktdcl.fpo.model.FarmerModel;
import com.ktdcl.fpo.utils.AppUtils;

import java.text.DecimalFormat;
import java.util.List;



/**
 * Created by Veerendra Patel on 3/6/19.
 */

public class FarmersAdapter extends RecyclerView.Adapter<FarmersAdapter.MyViewHolder> {
    private final String TAG = "KnowledgefAdapter";
    private Context context;
    private boolean isClicked = false;
    private long nbId=0;
    private DecimalFormat formatter;
    private List<FarmerModel> dataModelList;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private SelectItem mListener;
    private String type;

    public FarmersAdapter(Context context, List<FarmerModel> dataList) {
        this.context = context;
        this.dataModelList = dataList;
    }
    

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time, place, url, name;
        ImageView imageView;
        private LinearLayout mShareCommentLayout;
        private LinearLayout mLinearLayoutLike, mLinearLayoutComment, mLinearLayoutShare, mLinearLayoutPlace;
        private TextView mTextViewLikeCount, mTextViewShareCount, mTextViewCommentCount, textViewPlace;

        MyViewHolder(View view) {
            super(view);
            place = (TextView)itemView.findViewById(R.id.place);
            name = (TextView)itemView.findViewById(R.id.name);

        }
    }
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmer_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            final FarmerModel dataModel = dataModelList.get(position);
            holder.name.setText(dataModel.getName());
            holder.place.setText(dataModel.getVillage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
        //releasePlayer(player);
    }


    public void onClickItem(int position) {
        if (mListener != null) {
            mListener.selectedPosition(position);
        }
    }
    public interface SelectItem{
        void selectedPosition(int position);
    }


}


