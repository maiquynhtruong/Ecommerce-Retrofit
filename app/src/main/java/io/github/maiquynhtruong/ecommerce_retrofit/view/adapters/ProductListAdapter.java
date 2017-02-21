package io.github.maiquynhtruong.ecommerce_retrofit.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.github.maiquynhtruong.ecommerce_retrofit.R;
import io.github.maiquynhtruong.ecommerce_retrofit.databinding.ProductBinding;
import io.github.maiquynhtruong.ecommerce_retrofit.models.Product;

/**
 * Created by Mai on 2/7/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.BindingHolder> {
    ArrayList<Product> products;

    public ProductListAdapter(ArrayList<Product> products) {

        this.products = products;
    }
    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        return new BindingHolder(view);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.setProduct(product);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        ProductBinding binding;
        public BindingHolder(View itemView) {
            super(itemView);
            this.binding = DataBindingUtil.bind(itemView);

        }

    }


}
