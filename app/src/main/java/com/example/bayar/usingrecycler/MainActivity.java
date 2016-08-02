package com.example.bayar.usingrecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bayar.usingrecycler.adapter.LandscapeAdapter;
import com.example.bayar.usingrecycler.model.Landscape;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setUpRecycler();
    }

    private void setUpRecycler() {
        List<Landscape> landscapeList = Landscape.getData();
        LandscapeAdapter adapter = new LandscapeAdapter(landscapeList);
        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_vertical_linear_layout:
                LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this);
                linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(linearLayoutManagerVertical);
                break;
            case R.id.menu_horizontal_linear_layout:
                LinearLayoutManager linearLayoutManagerHorizontal = new LinearLayoutManager(this);
                linearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(linearLayoutManagerHorizontal);
                break;
            case R.id.menu_grid:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.menu_horizontal_staggered:
                StaggeredGridLayoutManager staggeredGridLayoutManagerHorizontal =
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(staggeredGridLayoutManagerHorizontal);
                break;
            case R.id.menu_vertical_staggered:
                StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}