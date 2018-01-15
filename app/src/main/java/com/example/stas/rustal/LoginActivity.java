package com.example.stas.rustal;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stas.rustal.OrderListActivity.OrderListActivity;
import com.testfairy.TestFairy;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Retrofit retrofit;
    RestApi api;
    Intent intent;
    EditText editText1, editText2;
    Toast toast;
    ImageView loginerror, passworderror, connectionerror;
    ProgressDialog dialog;
    private CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TestFairy.begin(this, "f5e75280c04fc7ae44af36f6c8d122b5b2c24629");

        toast = new Toast(this);
        intent = new Intent(this, OrderListActivity.class);

        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        loginerror = findViewById(R.id.login_field_error);
        passworderror = findViewById(R.id.password_field_error);
        connectionerror = findViewById(R.id.connection_error_image);



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(view -> {

            loginerror.setVisibility(View.INVISIBLE);
            passworderror.setVisibility(View.INVISIBLE);
            connectionerror.setVisibility(View.INVISIBLE);

            if(editText1.getText().toString().equals("") || editText2.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
                if (editText1.getText().toString().equals("")) loginerror.setVisibility(View.VISIBLE);
                if (editText2.getText().toString().equals("")) passworderror.setVisibility(View.VISIBLE);
            } else {
                authTry(editText1.getText().toString(), editText2.getText().toString());
            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    void auth(LoginModel response) {

        System.out.println("is login: " + response.getISLOGIN() + "\n" + "token: " + response.getUFUSERTOKEN());
        SharedPreferences sharedPref = this.getSharedPreferences("tokenSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", (String) response.getUFUSERTOKEN());
        editor.apply();
        System.out.println("Токен из сохранённого    " + sharedPref.getString("token", null));
        startActivity(intent);
        dialog.dismiss();

    }


    void authTry(String login, String password){

        dialog = ProgressDialog.show(LoginActivity.this, "",
                "Подождите, идёт авторизация...", true);

        RestApi requestInterface = new Retrofit.Builder()
                .baseUrl("http://rustralcom.nichost.ru/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RestApi.class);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(requestInterface.login("yes", login, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));


        }

    private void handleResponse(LoginModel response) {

        if(response.getISLOGIN().toString().equals("true")) {auth(response);}

        if(response.getISLOGIN().toString().equals("false")) {
            editText2.setText("");
            dialog.dismiss();
            Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
            dialog.dismiss();

        }
    }

    private void handleError(Throwable error) {

        dialog.dismiss();
        connectionerror.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Проверьте подключение к сети", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
