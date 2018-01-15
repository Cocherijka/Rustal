package com.example.stas.rustal.OrderListActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.stas.rustal.MyService;
import com.example.stas.rustal.OrderModel.OrderListModel;
import com.example.stas.rustal.R;
import com.example.stas.rustal.RestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderListActivity extends AppCompatActivity {

    Retrofit retrofit;
    RestApi api;
    RecyclerView recyclerView;
    private ArrayList<OrderListModel> data;
    private OrderListDataAdapter adapter;
    String token;
    RecyclerLoaderTask rlt;
    ProgressDialog dialog;
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);


        dialog = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
        dialog.setMessage("Загрузка данных...");
        dialog.show();


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        TextView title = findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Список заказов");

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            rlt = new RecyclerLoaderTask();
            rlt.execute();
        });


        recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);



    }

    @Override
    protected void onStart() {
        rlt = new RecyclerLoaderTask();
        rlt.execute();
        super.onStart();
    }

    private void load(){

        String baseURL = "http://rustralcom.nichost.ru/";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(RestApi.class);

        System.out.println("refreshing");

        SharedPreferences sharedPref = this.getSharedPreferences("tokenSave", Context.MODE_PRIVATE);
        token = sharedPref.getString("token", "NIHUA NET");

        Call<List<OrderListModel>> call = api.getOrderList(token);

        call.enqueue(new Callback<List<OrderListModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<OrderListModel>> call, @NonNull Response<List<OrderListModel>> response) {
                List<OrderListModel> rs = response.body();

                data = new ArrayList<>(rs);
                adapter = new OrderListDataAdapter(data);
                recyclerView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);


            }

            @Override
            public void onFailure(@NonNull Call<List<OrderListModel>> call, @NonNull Throwable t) {
                System.out.println("error from enqueue");
                Log.d("Error",t.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

        dialog.dismiss();
        startService(new Intent(this, MyService.class));

    }


    @SuppressLint("StaticFieldLeak")
    class RecyclerLoaderTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            load();
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }




    @Override
    public void onBackPressed() {

    }
}
