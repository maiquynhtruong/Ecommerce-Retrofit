package io.github.maiquynhtruong.ecommerce_retrofit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
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
    public static final String API_KEY = "b743e26728e16b81da139182bb2094357c31d331";
    public static final String API_BASE_URL = "https://api.zappos.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserQuery();
            }
        });
        productList= new ArrayList<>();

        rvProduct = (RecyclerView) findViewById(R.id.recycler_view);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));

//        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Product product = new Product("First", "10.0", "7.5", "1234", "25%", "zappos.com/first", "tumblr.com/image");
        productList.add(product);
        fetchResults("nike");
//        binding.setProducts(productList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getUserQuery() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.fragment_search, null);
        final EditText editText = (EditText) view.findViewById(R.id.edit_query);
        builder.setTitle("Search").setView(view);
        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = editText.getText().toString();
                fetchResults(input);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

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
                productList = new ArrayList<>(Arrays.asList(products.getProducts()));

                if (productList.size() > 0) {
                    for (Product p : productList) {
                        Log.d("Main", p.getProductName());
                    }
                    adapter = new ProductListAdapter(productList);
                    rvProduct.setAdapter(adapter);
                } else {
                    Toast.makeText(getBaseContext(), "No Item found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResults> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Failed to get search results. Try again later", Toast.LENGTH_LONG).show();
            }
        });
    }

}
