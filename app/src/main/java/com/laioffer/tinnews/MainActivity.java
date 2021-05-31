package com.laioffer.tinnews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);//得到bottom nav_view
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);//得到中间nav_host_fragment

        navController = navHostFragment.getNavController();//得到中间fragment nav_controller
        NavigationUI.setupWithNavController(navView, navController);//把nav_host_fragment和下面的bottom nav_view联系起来
        NavigationUI.setupActionBarWithNavController(this, navController);//把nav_host_fragment和上面的actionBar联系起来

        //因为在onCreate里， 所以在app启动时会运行下面的代码
        //不在view层做network request
//        NewsApi api = RetrofitClient.newInstance().create(NewsApi.class);
//        api.getTopHeadlines("US").enqueue(new Callback<NewsResponse>() {
//            @Override
//            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
//                if (response.isSuccessful()) {
//                    Log.d("getTopHeadlines", response.body().toString());
//                } else {
//                    Log.d("getTopHeadlines", response.toString());
//                }
//            }
//            @Override
//            public void onFailure(Call<NewsResponse> call, Throwable t) {
//                Log.d("getToHeadlines", t.toString());
//
//            }
//        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();//实现action bar 回退到home的功能
    }
}