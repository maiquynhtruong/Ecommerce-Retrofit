package io.github.maiquynhtruong.ecommerce_retrofit.rest;

import java.util.List;

import io.github.maiquynhtruong.ecommerce_retrofit.models.Product;
import io.github.maiquynhtruong.ecommerce_retrofit.models.ProductResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mai on 2/7/2017.
 */

public interface ProductService {

    @GET("Search?")
    Call<ProductResults> getProducts(@Query("term") String keyword, @Query("key") String apiKey);

}
