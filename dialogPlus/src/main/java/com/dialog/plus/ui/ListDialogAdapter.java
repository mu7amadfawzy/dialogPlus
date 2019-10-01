package com.dialog.plus.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dialog.plus.R;
import com.dialog.plus.databinding.ListDialogRowBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Noamany on 25,March,2019
 */
class ListDialogAdapter extends RecyclerView.Adapter<ListDialogAdapter.ViewHolder> implements Filterable {
    private ArrayList<String> unfilteredData, dataList;
    private ListDialogRowBinding binding;
    private DialogPlus dialogPlus;
    private DialogPlus.DialogListListener onItemClickListener;
    private Filter filter;


    ListDialogAdapter(DialogPlus dialogPlus, ArrayList<String> unfilteredData, DialogPlus.DialogListListener onItemClickListener) {
        super();
        this.onItemClickListener = onItemClickListener;
        this.dialogPlus = dialogPlus;
        setUnfilteredData(unfilteredData);
        setFilter();
    }

    public void setUnfilteredData(List<String> list) {
        this.unfilteredData = new ArrayList<>(list);
        this.dataList = unfilteredData;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private void setFilter() {
        filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataList = unfilteredData;
                } else {
                    ArrayList<String> filteredList = new ArrayList<>();
                    for (String row : unfilteredData)
                        if (row.toLowerCase().contains(charString.toLowerCase()))
                            filteredList.add(row);
                    dataList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataList = (ArrayList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
                this.binding.getRoot().setOnClickListener(v -> {
                    int desiredIndex = unfilteredData.indexOf(dataList.get(getAdapterPosition()));
                    onItemClickListener.onItemClicked(unfilteredData.get(desiredIndex), desiredIndex, dialogPlus);
                });
        }

        public void bind(int position) {
            binding.setTitle(dataList.get(getAdapterPosition()));
        }

    }
}



