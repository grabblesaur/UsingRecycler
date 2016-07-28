package com.example.bayar.usingrecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bayar.usingrecycler.model.Landscape;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<Landscape> mLandscapeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpRecycler();
    }

    private void setUpRecycler() {
        mLandscapeList = Landscape.getData();
        LandscapeAdapter adapter = new LandscapeAdapter(mLandscapeList);
        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
    }
}