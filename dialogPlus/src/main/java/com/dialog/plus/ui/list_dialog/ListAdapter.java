package com.dialog.plus.ui.list_dialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dialog.plus.R;
import com.dialog.plus.databinding.ListDialogRowBinding;
import com.dialog.plus.ui.DialogPlus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Noamany on 25,March,2019
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<String> dataList;
    private ListDialogRowBinding binding;
    private LayoutInflater layoutInflater;
    private DialogPlus dialogPlus;
    private DialogPlus.DialogListListener onItemClickListener;

    public ListAdapter(DialogPlus dialogPlus, List<String> dataList, DialogPlus.DialogListListener onItemClickListener) {
        super();
        this.dataList = new ArrayList<>(dataList);
        this.onItemClickListener = onItemClickListener;
        this.dialogPlus = dialogPlus;
    }

    public void setDataList(List<String> list) {
        this.dataList = new ArrayList<>(list);
        notifyDataSetChanged();
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        renderView(layoutInflater, parent);
        return new ListAdapter.ViewHolder(binding);
    }

    protected void renderView(LayoutInflater layoutInflater, ViewGroup parent) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_dialog_row, parent, false);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.bind(position);


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public List<String> getData() {
        return dataList;
    }

    public class ViewHolder extends BaseHolder {
        private ListDialogRowBinding binding;

        public ViewHolder(ListDialogRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(int position) {
            super.bind(position);
            binding.setTitle(dataList.get(getAdapterPosition()));
            if (getAdapterPosition() == dataList.size() - 1) binding.dages.setHidden(true);
            else binding.dages.setHidden(false);
            if (onItemClickListener != null)
                this.binding.item.setOnClickListener(v -> {
                    onItemClickListener.onItemClicked(dataList.get(getAdapterPosition()),getAdapterPosition(), dialogPlus);
                });
        }

    }
}



