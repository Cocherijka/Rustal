package com.example.stas.rustal.OrderActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stas.rustal.OrderModel.OrderListModel;
import com.example.stas.rustal.R;
import com.example.stas.rustal.RestApi;
import com.example.stas.rustal.StatusModel.StatusModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    final String baseURL = "http://rustralcom.nichost.ru/";
    RestApi api;
    DirectionsResult result;
    MyMapview mapView;
    CameraUpdate cu;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    String token, orderID;
    TextView order_id_tv, title;
    ImageView image_upload;
    Button image_load_btn, image_upload_btn, change_status_btn;
    MultipartBody.Part dImage;
    RatingBar r_b;
    TextView rate_tv;
    ArrayList<String> alValue;
    ArrayList<String> alProp;
    Retrofit retrofit;
    int item;
    private CompositeDisposable compositeDisposable;
    Bundle bundle;
    Bitmap bitmap;

    private static int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(RestApi.class);

        SharedPreferences sharedPref = this.getSharedPreferences("tokenSave", Context.MODE_PRIVATE);
        token = sharedPref.getString("token", "null");

        orderID = getIntent().getStringExtra("id");

        bundle = savedInstanceState;

        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage("Загрузка данных...");
        progressDialog.show();

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(bundle);
        mapView.getMapAsync(this);

        initViews();
        loadJSON();

    }


    @Override
    protected void onStart() {
        super.onStart();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        title = findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Информация о заказе №" + orderID);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setBuildingsEnabled(false);
        map.setIndoorEnabled(false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        map.setMyLocationEnabled(true);

        map.setOnMapLoadedCallback(() -> {

            LatLng startpoint = new LatLng(result.routes[0].legs[0].startLocation.lat, result.routes[0].legs[0].startLocation.lng);
            LatLng finishpoint = new LatLng(result.routes[0].legs[0].endLocation.lat, result.routes[0].legs[0].endLocation.lng);

            Marker startmarker = map.addMarker(new MarkerOptions().position(startpoint).title("Отсюда: " + result.routes[0].legs[0].startAddress).icon(getMarkerIcon()));
            Marker finishmarker = map.addMarker(new MarkerOptions().position(finishpoint).title("Сюда: " + result.routes[0].legs[0].endAddress));

            List<LatLng> decoded = PolyUtil.decode(result.routes[0].overviewPolyline.getEncodedPath());

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(startmarker.getPosition());
            builder.include(finishmarker.getPosition());
            LatLngBounds bounds = builder.build();

            cu = CameraUpdateFactory.newLatLngBounds(bounds, 90);
            map.addPolyline(new PolylineOptions().addAll(decoded).width(5).color(Color.BLUE));
            map.moveCamera(cu);
            progressDialog.dismiss();


        });

    }



    public BitmapDescriptor getMarkerIcon() {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor("#34CA39"), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }


    public void loadImage() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }


    public void uploadImage(){

        progressDialog.setMessage("Отправка изображения");
        progressDialog.show();

        compositeDisposable = new CompositeDisposable();

        RestApi requestInterface = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RestApi.class);

        compositeDisposable.add(requestInterface.uploadImage(orderID, token, dImage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onImageUploaded, this::onImageUploadFailrue));
    }

    private void onImageUploadFailrue(Throwable throwable) {
        Toast.makeText(this, "Возникла проблема", Toast.LENGTH_SHORT).show();

    }


    private void onImageUploaded(ResponseBody responseBody) {

        image_upload.setImageResource(R.drawable.photo_default_source);
        bitmap.recycle();
        Toast.makeText(OrderActivity.this, "Загружено", Toast.LENGTH_SHORT).show();
        image_upload_btn.setEnabled(false);
        progressDialog.dismiss();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {

            image_upload_btn.setEnabled(true);

            Uri imageUri = data.getData();
            String picturePath = getRealPathFromURI(imageUri);
            File file = new File(picturePath);




            BitmapFactory.Options bounds = new BitmapFactory.Options();
            bounds.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picturePath, bounds);
            if ((bounds.outWidth == -1) || (bounds.outHeight == -1)) {
                bitmap = null;
            }
            int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
                    : bounds.outWidth;
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = originalSize / 256;
            bitmap = BitmapFactory.decodeFile(picturePath, opts);

            Bitmap bitmapOriginal = BitmapFactory.decodeFile(picturePath);
            Bitmap bitmapsimplesize = Bitmap.createScaledBitmap(bitmapOriginal,bitmapOriginal.getWidth() / 8, bitmapOriginal.getHeight() /8, true);
            bitmapOriginal.recycle();
            image_upload.setImageBitmap(bitmap);

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)), file);

            dImage = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void showStatusDialog() {

        Call<StatusModel> call = api.getPropertys(token);
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(@NonNull Call<StatusModel> call, @NonNull Response<StatusModel> response) {

                StatusModel statusList = response.body();
                alValue = new ArrayList<>();
                alProp = new ArrayList<>();

                if(statusList.getVALUES().get45().getVALUE() != null) {
                    alValue.add(statusList.getVALUES().get45().getVALUE());
                    alProp.add(statusList.getVALUES().get45().getID());
                }
                if(statusList.getVALUES().get46().getVALUE() != null) {
                    alValue.add(statusList.getVALUES().get46().getVALUE());
                    alProp.add(statusList.getVALUES().get46().getID());
                }
                if(statusList.getVALUES().get47().getVALUE() != null) {
                    alValue.add(statusList.getVALUES().get47().getVALUE());
                    alProp.add(statusList.getVALUES().get47().getID());
                }
                if(statusList.getVALUES().get48().getVALUE() != null) {
                    alValue.add(statusList.getVALUES().get48().getVALUE());
                    alProp.add(statusList.getVALUES().get48().getID());
                }
                if(statusList.getVALUES().get49().getVALUE() != null) {
                    alValue.add(statusList.getVALUES().get49().getVALUE());
                    alProp.add(statusList.getVALUES().get49().getID());
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setTitle("Смена статуса");

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(OrderActivity.this,
                        android.R.layout.simple_list_item_single_choice, alValue);

                builder.setSingleChoiceItems(dataAdapter, 0, (dialog, which) -> item = which);

                builder.setPositiveButton("Поддтвердить", (dialog, which) -> {

                    compositeDisposable = new CompositeDisposable();


                    RestApi requestInterface = new Retrofit.Builder()
                            .baseUrl(baseURL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(RestApi.class);

                    compositeDisposable.add(requestInterface.setStatus(orderID, token, alProp.get(item))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe());

                });


                builder.setNegativeButton("Отмена", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onFailure(@NonNull Call<StatusModel> call, @NonNull Throwable t) {

                System.out.println(t.getMessage());

            }
        });

    }

    private void loadJSON() {

        compositeDisposable = new CompositeDisposable();


        RestApi requestInterface = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RestApi.class);

        compositeDisposable.add(requestInterface.getOrder(token, orderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }


    @SuppressLint("SetTextI18n")
    private void handleResponse(OrderListModel response) {

        if (response.getUserRate().getVALUE()!=null){
            rate_tv.setText(response.getUserRate().getNAME());
            r_b.setRating( Integer.parseInt((String) response.getUserRate().getVALUE())/20);
        } else { rate_tv.setText("Оценка отсутствует");
        }

        //order_id_tv.setText("Заказ номер : " + response.getID());

        OrderDataAdapter adapter = new OrderDataAdapter(response);
        recyclerView.setAdapter(adapter);

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBYdXcchQJ4qd_6CiBXWrx-3sr4AdV61Mg")
                .build();
        try {
            result = DirectionsApi.newRequest(context).mode(TravelMode.DRIVING).origin(response.getAddressLoading().getVALUE()).destination(response.getAddress().getVALUE()).language("ru").await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        progressDialog.dismiss();
        mapView.onStart();

    }



    private void handleError(Throwable error) {
        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }


    public void initViews(){

        r_b = findViewById(R.id.ratingBar);

        //order_id_tv = findViewById(R.id.order_main_id);
        image_upload = findViewById(R.id.setted_image);


        image_load_btn = findViewById(R.id.image_load_button);
        image_load_btn.setOnClickListener(view -> loadImage());

        rate_tv = findViewById(R.id.rate_tv);

        image_upload_btn = findViewById(R.id.image_upload_button);
        image_upload_btn.setEnabled(false);
        image_upload_btn.setOnClickListener(view -> uploadImage());

        change_status_btn = findViewById(R.id.change_status_button);

        change_status_btn.setOnClickListener(view -> showStatusDialog());

        recyclerView = findViewById(R.id.scrollView2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

    }
}

