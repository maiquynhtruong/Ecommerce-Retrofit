package io.github.maiquynhtruong.ecommerce_retrofit.view.adapters;

import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/**
 * Created by Mai on 2/7/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.BindingHolder> {

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        public BindingHolder(View itemView) {
            super(itemView);
        }
    }


}
