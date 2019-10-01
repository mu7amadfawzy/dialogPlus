package com.dialog.plus.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dialog.plus.R;
import com.dialog.plus.databinding.ListDialogRowBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Noamany on 25,March,2019
 */
class ListDialogAdapter extends RecyclerView.Adapter<ListDialogAdapter.ViewHolder> {
    private ArrayList<String> dataList;
    private ListDialogRowBinding binding;
    private DialogPlus dialogPlus;
    private DialogPlus.DialogListListener onItemClickListener;


    ListDialogAdapter(DialogPlus dialogPlus, List<String> dataList, DialogPlus.DialogListListener onItemClickListener) {
        super();
        this.dataList = new ArrayList<>(dataList);
        this.onItemClickListener = onItemClickListener;
        this.dialogPlus = dialogPlus;
    }

    public void setDataList(List<String> list) {
        this.dataList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public void filter(String str) {

    }


    @Override
    public ListDialogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        renderView(LayoutInflater.from(parent.getContext()), parent);
        return new ListDialogAdapter.ViewHolder(binding);
    }

    protected void renderView(LayoutInflater layoutInflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_dialog_row, parent, false);
    }

    @Override
    public void onBindViewHolder(ListDialogAdapter.ViewHolder holder, int position) {
        holder.bind(position);


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public List<String> getData() {
        return dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListDialogRowBinding binding;

        public ViewHolder(ListDialogRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            setListener();
        }

        private void setListener() {
            if (onItemClickListener != null)
                this.binding.item.setOnClickListener(v -> {
                    onItemClickListener.onItemClicked(dataList.get(getAdapterPosition()), getAdapterPosition(), dialogPlus);
                });
        }

        public void bind(int position) {
            binding.setTitle(dataList.get(getAdapterPosition()));
            if (getAdapterPosition() == dataList.size() - 1) binding.dages.setHidden(true);
            else binding.dages.setHidden(false);
        }

    }
}



