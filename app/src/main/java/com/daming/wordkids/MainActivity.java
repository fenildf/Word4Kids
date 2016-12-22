package com.daming.wordkids;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.daming.wordkids.adapter.ListAdapter;
import com.daming.wordkids.adapter.ListItemListener;
import com.daming.wordkids.bean.Category;
import com.daming.wordkids.databinding.ActivityMainBinding;
import com.daming.wordkids.service.GenServiceUtil;
import com.daming.wordkids.service.WordService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Dashu";
    private WordService service;
    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    private ListAdapter<Category> menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = GenServiceUtil.createService(WordService.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        drawer = binding.drawerLayout;
        menuAdapter = new ListAdapter<>(this);
        binding.setMenu_adapter(menuAdapter);
        menuAdapter.setListener(menuListener);
        
        initDrawerLayout();

        initMenu();
    }

    private ListItemListener<Category> menuListener = new ListItemListener<Category>() {

        @Override
        public void onItemClicked(Category category) {
            Log.d(TAG, "item selected " + category.id + "  " + category.name);
            drawer.closeDrawers();
        }
    };

    private void initMenu() {
        final Dialog loading = ProgressDialog.show(this, "network request", "loading");
        final Call<List<Category>> call = service.getCategories();
        Observable<List<Category>> myObserve = Observable.create(new Observable.OnSubscribe<List<Category>>() {
            @Override
            public void call(Subscriber<? super List<Category>> subscriber) {
                try {
                    Response<List<Category>> categories = call.execute();
                    subscriber.onNext(categories.body());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });

        myObserve
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Category>>() {
                    @Override
                    public void onCompleted() {
                        loading.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.dismiss();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Category> categories) {
                        menuAdapter.setList(categories);
                    }
                });
    }

    private ActionBarDrawerToggle toggle;

    private void initDrawerLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged --");
        toggle.syncState();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}
