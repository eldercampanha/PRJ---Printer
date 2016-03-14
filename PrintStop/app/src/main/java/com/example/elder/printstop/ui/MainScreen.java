package com.example.elder.printstop.ui;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.elder.printstop.AddMoreFiles;
import com.example.elder.printstop.R;
import com.example.elder.printstop.adapter.RecyclerViewAdapterMainScreen;
import com.example.elder.printstop.model.FileToPrint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainScreen extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<FileToPrint> mFiles;
    private RecyclerViewAdapterMainScreen mRecyclerViewAdapterMainScreen;
    private ImageView mImageView;

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

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.print_fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

//        Used to add a line between the cells
//        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
//        render();
    }


    @Override
    protected void onResume() {
        super.onResume();
       }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        render();
    }

    private void render() {

        try {
            mImageView = (ImageView) findViewById(R.id.img_pdf_file);
            int REQ_WIDTH = mImageView.getWidth();
            int REQ_HEIGHT = mImageView.getHeight() + 1000;
            int currentPage = 0;

            Log.i("SSS", Environment.getExternalStorageDirectory().getAbsolutePath());// + APP_THUMBNAIL_PATH_SD_CARD;)


            Bitmap bitmap = Bitmap.createBitmap(REQ_WIDTH, REQ_HEIGHT, Bitmap.Config.ARGB_4444);

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            File file = new File(path + "/marm08.pdf");
            PdfRenderer pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));

            Matrix m = mImageView.getMatrix();
            Rect rec = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
            pdfRenderer.openPage(currentPage).render(bitmap,rec, m, PdfRenderer.Page.RENDER_MODE_FOR_PRINT);
            mImageView.setImageMatrix(m);
            mImageView.setImageBitmap(bitmap);
            mImageView.invalidate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
