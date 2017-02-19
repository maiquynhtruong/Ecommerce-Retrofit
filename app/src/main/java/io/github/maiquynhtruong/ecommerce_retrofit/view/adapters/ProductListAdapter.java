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
import android.widget.TextView;

import java.util.ArrayList;

import io.github.maiquynhtruong.ecommerce_retrofit.R;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        BindingHolder vh = new BindingHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Product product = products.get(position);
        holder.productName.setText(product.getProductName());
        holder.originalPrice.setText(product.getOriginalPrice());
//        holder.productId.setText(product.getProductId());
        holder.price.setText(product.getPrice());
        holder.percentOff.setText(product.getPercentOff());
        holder.thumbnailImageUrl.setImageResource(R.drawable.ic_shoe);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private TextView productName, originalPrice, productId, price,
                percentOff, productUrl;
        private ImageView thumbnailImageUrl;

        public BindingHolder(View itemView) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name);
            originalPrice = (TextView) itemView.findViewById(R.id.product_original_price);
            percentOff = (TextView) itemView.findViewById(R.id.product_percent_off);
            price = (TextView) itemView.findViewById(R.id.product_price);
            thumbnailImageUrl = (ImageView) itemView.findViewById(R.id.product_image);
        }
    }


}
