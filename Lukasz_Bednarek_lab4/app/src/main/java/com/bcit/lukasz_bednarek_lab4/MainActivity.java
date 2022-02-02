package com.bcit.lukasz_bednarek_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerItemView();
    }

    public void setUpRecyclerItemView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView_main);

        // array data. each index will be a row. Take some out or put more and see how the rendered items
        // the viewer change
        String[] rows = {"1", "2", "3", "4", "5"};

        // this is how we pass the data to the layout. This will become the localStringData.
        ItemAdapter itemAdapter = new ItemAdapter(rows);
        // this is just stuff to initialize it
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}