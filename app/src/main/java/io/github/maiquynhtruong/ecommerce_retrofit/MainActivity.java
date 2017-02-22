package io.github.maiquynhtruong.ecommerce_retrofit;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.maiquynhtruong.ecommerce_retrofit.databinding.ActivityMainBinding;
import io.github.maiquynhtruong.ecommerce_retrofit.models.Product;
import io.github.maiquynhtruong.ecommerce_retrofit.models.ProductResults;
import io.github.maiquynhtruong.ecommerce_retrofit.rest.ProductService;
import io.github.maiquynhtruong.ecommerce_retrofit.view.adapters.ProductListAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvProduct;
    ArrayList<Product> productList;
    ProductListAdapter adapter;
//    ActivityMainBinding binding;
    public static final String API_KEY = "b743e26728e16b81da139182bb2094357c31d331";
    public static final String API_BASE_URL = "https://api.zappos.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productList = new ArrayList<>();
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        rvProduct = (RecyclerView) findViewById(R.id.recycler_view);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            fetchResults(query);
        }
        fetchResults("nike");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchResults(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchResults(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            handleSearch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void handleSearch() {
        String string = "nike";
        fetchResults(string);
    }
    public void fetchResults(String input) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        ProductService service = retrofit.create(ProductService.class);
        Call<ProductResults> call = service.getProducts(input, API_KEY);

        call.enqueue(new Callback<ProductResults>() {
            @Override
            public void onResponse(Call<ProductResults> call, Response<ProductResults> response) {
                ProductResults products = response.body();
                productList = products.getProducts();

                if (productList.size() > 0) {
                    adapter = new ProductListAdapter(productList);
                    rvProduct.setAdapter(adapter);
                } else {
                    Toast.makeText(getBaseContext(), R.string.no_item_found, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResults> call, Throwable t) {
                Toast.makeText(getBaseContext(), R.string.failed_get_search_result, Toast.LENGTH_LONG).show();
            }
        });
    }

}
