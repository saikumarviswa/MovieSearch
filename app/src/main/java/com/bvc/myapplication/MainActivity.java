package com.bvc.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.transition.TransitionManager;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bvc.myapplication.model.Movie;
import com.bvc.myapplication.network.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Animation mSlideUp;
    private Animation mSlideDown;
    CardView searchLayout;
    ImageView closeSearch;
    ImageView movieSearch;
    EditText searchMovieName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        searchLayout = (CardView)findViewById(R.id.searchLayout);
        closeSearch = (ImageView)findViewById(R.id.closeSearchLayoutImage);
        movieSearch = (ImageView)findViewById(R.id.movieSearch);
        searchMovieName = (EditText) findViewById(R.id.searchMovieName);

        closeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layout_slid_up);
                mSlideDown = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layut_slide_down);

                searchLayout.setAnimation(mSlideUp);
                searchLayout.setVisibility(View.GONE);

            }
        });

        movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchMovieName.getText().toString().isEmpty()){

                    mSlideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layout_slid_up);
                    mSlideDown = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layut_slide_down);

                    searchLayout.setAnimation(mSlideUp);
                    searchLayout.setVisibility(View.GONE);

                    Methods methods = RetrofitClient.getRetrofitInstance(searchMovieName.getText().toString(),"movie").create(Methods.class);
                    Call<Movie> call = methods.getAllData();
                    call.equals(new Callback<Movie>(){
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {

                            Log.d(TAG, "onResponse: 111111111111111111111111111111111"+response.toString());

                        }
                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                        }
                    });

                }else {
                    Toast.makeText(getApplicationContext(),"Please entae the Movie Name",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {


            case R.id.action_search:
                /*Intent intent1 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent1);*/
                mSlideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layout_slid_up);
                mSlideDown = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layut_slide_down);

                searchLayout.setAnimation(mSlideDown);
                searchLayout.setVisibility(View.VISIBLE);

                return true;

            /*case R.id.action_search:

                TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.toolbar));
                MenuItemCompat.expandActionView(item);
                return true;*/




            default:
                return super.onOptionsItemSelected(item);
        }
    }

}