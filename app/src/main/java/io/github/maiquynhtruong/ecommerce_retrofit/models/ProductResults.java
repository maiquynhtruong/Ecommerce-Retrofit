package io.github.maiquynhtruong.ecommerce_retrofit.models;

import java.util.ArrayList;
import java.util.List;

import io.github.maiquynhtruong.ecommerce_retrofit.models.Product;

/**
 * Created by Mai on 2/7/2017.
 */

public class ProductResults {
    public ArrayList<Product> results;
    public ArrayList<Product> getProducts() {
        return results;
    }
}
