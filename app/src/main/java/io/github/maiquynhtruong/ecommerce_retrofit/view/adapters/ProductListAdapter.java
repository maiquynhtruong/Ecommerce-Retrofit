package io.github.maiquynhtruong.ecommerce_retrofit.view.adapters;

import android.database.DataSetObserver;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.tool.Binding;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import io.github.maiquynhtruong.ecommerce_retrofit.BR;
import io.github.maiquynhtruong.ecommerce_retrofit.R;
import io.github.maiquynhtruong.ecommerce_retrofit.models.Product;

/**
 * Created by Mai on 2/7/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.BindingHolder> {
    List<Product> products;

    public ProductListAdapter(List<Product> products) {
        this.products = products;
    }
    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        BindingHolder vh = new BindingHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Product product = products.get(position);
        holder.bindHolder(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
            this.binding = DataBindingUtil.bind(itemView);
        }
        public void bindHolder(Product product) {
            binding.setVariable(BR.product, product);
            binding.executePendingBindings();
        }


    }


}
