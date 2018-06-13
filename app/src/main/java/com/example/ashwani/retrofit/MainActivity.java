package com.example.ashwani.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ashwani.retrofit.UserApi.getUserService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main Activity";
    Button bt;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.userdataTV);

        getData();

        bt = findViewById(R.id.post);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Resp> call = UserApi.getUserService().postUser(new UserListPojo("abc",
                        "this is an address", "araju@mail.com ", "9911416633", "asd12AAW"));

                call.enqueue(new Callback<Resp>() {
                    @Override
                    public void onResponse(Call<Resp> call, Response<Resp> response) {
                        Toast.makeText(MainActivity.this, "posts happen", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: of post" + response.raw());
                    }

                    @Override
                    public void onFailure(Call<Resp> call, Throwable t) {
                        Log.d(TAG, "onFailure: post wale " + t);
                    }
                });
            }
        });

//UserApi.getUserService().postUser(new UserListPojo("abc",
//                        "this is an address", "araju@mail.com ", "9911416633","asd12AAW"));
//        Toast.makeText(MainActivity.this, "posts happen", Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "onResponse: of post" + response.raw());
//        Log.d(TAG, "onFailure: post wale " + t);

    }

    public void getData() {
        Call<List<UserListPojo>> userListPojoCall = getUserService().getUserList();
        userListPojoCall.enqueue(new Callback<List<UserListPojo>>() {
            @Override
            public void onResponse(Call<List<UserListPojo>> call, Response<List<UserListPojo>> response) {
                List<UserListPojo> userListPojo = response.body();
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: " + userListPojo);
                Log.d(TAG, "onResponse: fialed" + "\n" + response.raw());

                if (userListPojo != null)
                    tv.setText(userListPojo.get(0).getName() + "\n" + userListPojo.size());
            }

            @Override
            public void onFailure(Call<List<UserListPojo>> call, Throwable t) {
                //failed
                Toast.makeText(MainActivity.this, "failed bitch", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t);
            }
        });


    }
}
