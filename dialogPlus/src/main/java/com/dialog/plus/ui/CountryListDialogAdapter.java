package com.dialog.plus.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dialog.plus.R;
import com.dialog.plus.databinding.DialogCountryRowBinding;

import java.util.ArrayList;

/**
 * Created by Muhammad Noamany on 25,March,2019
 */
class CountryListDialogAdapter extends RecyclerView.Adapter<CountryListDialogAdapter.ViewHolder> implements Filterable {
    private ArrayList<CountryDataModel> unfilteredData, dataList;
    private DialogCountryRowBinding binding;
    private DialogPlus dialogPlus;
    private DialogPlus.CountriesDialogListener onItemClickListener;
    private Filter filter;
    private boolean showCode;


    CountryListDialogAdapter(DialogPlus dialogPlus, ArrayList<CountryDataModel> unfilteredData, boolean showCode, DialogPlus.CountriesDialogListener onItemClickListener) {
        super();
        this.onItemClickListener = onItemClickListener;
        this.dialogPlus = dialogPlus;
        this.showCode = showCode;
        setUnfilteredData(unfilteredData);
        setFilter();
    }

    public void setUnfilteredData(ArrayList<CountryDataModel> list) {
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
                    ArrayList<CountryDataModel> filteredList = new ArrayList<>();
                    for (CountryDataModel row : unfilteredData)
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())
                                || row.getCode().toLowerCase().contains(charString.toLowerCase()))
                            filteredList.add(row);
                    dataList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataList = (ArrayList<CountryDataModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public CountryListDialogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        renderView(LayoutInflater.from(parent.getContext()), parent);
        return new CountryListDialogAdapter.ViewHolder(binding);
    }

    protected void renderView(LayoutInflater layoutInflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_country_row, parent, false);
    }

    @Override
    public void onBindViewHolder(CountryListDialogAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public ArrayList<CountryDataModel> getData() {
        return dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private DialogCountryRowBinding binding;

        public ViewHolder(DialogCountryRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            setListener();
        }

        private void setListener() {
            if (onItemClickListener != null)
                this.binding.row.setOnClickListener(v -> {
                    int desiredIndex = unfilteredData.indexOf(dataList.get(getAdapterPosition()));
                    onItemClickListener.onItemClicked(unfilteredData.get(desiredIndex), dialogPlus);
                });
        }

        public void bind(int position) {
            binding.setDataModel(dataList.get(position));
            binding.setShowCode(showCode);
        }

    }
}



