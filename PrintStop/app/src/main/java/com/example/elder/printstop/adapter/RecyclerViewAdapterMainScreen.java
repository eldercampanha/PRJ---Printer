package com.example.elder.printstop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elder.printstop.R;
import com.example.elder.printstop.model.FileToPrint;

import java.util.ArrayList;

/**
 * Created by Elder on 3/5/2016.
 */
public class RecyclerViewAdapterMainScreen extends RecyclerView.Adapter<RecyclerViewAdapterMainScreen.Holder>{

    private final Context mContext;
    private final ArrayList<FileToPrint> mFiles;
    private View mView;

    public RecyclerViewAdapterMainScreen(Context context, ArrayList<FileToPrint> files) {
        mContext = context;
        mFiles = files;
    }



    @Override
    public RecyclerViewAdapterMainScreen.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        mView = LayoutInflater.from(mContext).inflate(R.layout.row_main_file, parent, false);
        Holder holder = new Holder(mView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterMainScreen.Holder holder, int position) {

        FileToPrint fileToPrint = mFiles.get(position);

        holder.fileName.setText(fileToPrint.getName());
        holder.fileInfo.setText("Size: " + fileToPrint.getFileSize());
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView fileName;
        public TextView fileInfo;

        public Holder(View itemView) {
            super(itemView);

            fileName = (TextView)itemView.findViewById(R.id.txt_file_name);
            fileInfo = (TextView)itemView.findViewById(R.id.txt_file_info);
        }
    }
}
