package com.example.elder.printstop.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.elder.printstop.AddMoreFiles;
import com.example.elder.printstop.R;
import com.example.elder.printstop.SimpleDividerItemDecoration;
import com.example.elder.printstop.adapter.RecyclerViewAdapterMainScreen;
import com.example.elder.printstop.model.FileToPrint;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<FileToPrint> mFiles;
    private RecyclerViewAdapterMainScreen mRecyclerViewAdapterMainScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //TODO:remove later
        mFiles = getFakeData();

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_main_files_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapterMainScreen = new RecyclerViewAdapterMainScreen(this,mFiles);
        mRecyclerView.setAdapter(mRecyclerViewAdapterMainScreen);

//        Used to add a line between the cells
//        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
    }


    public void btnHelpClicked(View view){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    public void btnAddMoreFilesClicked (View view){
        Intent intent = new Intent(this, AddMoreFiles.class);
        startActivity(intent);
    }

    public ArrayList<FileToPrint> getFakeData() {

        ArrayList<FileToPrint> files = new ArrayList<>();

        FileToPrint file = new FileToPrint();
        file.setName("Trabalho de Quimica.pdf");
        file.setFileSize(20);

        FileToPrint file1 = new FileToPrint();
        file1.setName("Conta de luz.pdf");
        file1.setFileSize(5);

        FileToPrint file2 = new FileToPrint();
        file2.setName("asssassasjashasu.pdf");
        file2.setFileSize(15);

        FileToPrint file3 = new FileToPrint();
        file3.setName("Cavalo de troia.pdf");
        file3.setFileSize(13);

        files.add(file);
        files.add(file1);
        files.add(file2);
        files.add(file3);
        files.add(file);
        files.add(file1);
        files.add(file2);
        files.add(file3);
        files.add(file);
        files.add(file1);
        files.add(file2);
        files.add(file3);

        return files;
    }
}
