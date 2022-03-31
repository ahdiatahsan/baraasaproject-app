package com.baraasa.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.baraasa.project.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Jurnal extends AppCompatActivity {

    Toolbar toolbar;
    PDFView pdfView;
    ProgressBar progressBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurnal);

        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbarjurnal);
        progressBar = findViewById(R.id.progres_bar);
        pdfView = findViewById(R.id.pdfView);
        toolbar.setTitle("Jurnal");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.download);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_download) {
                    showDialog();
                }
                return false;
            }
        });
        new RetrievePDFStream().execute(getIntent().getSerializableExtra("file").toString());
//        imageView.setBackground(Drawable.createFromPath(gmbr));
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title dialog
        alertDialogBuilder.setTitle("Download Jurnal Pdf ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage(getIntent().getSerializableExtra("judul").toString())
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title1 = getIntent().getSerializableExtra("judul").toString();
                        String url = getIntent().getSerializableExtra("file").toString();
                        DownloadBooks(url, title1);
//                        downloadTask(url);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }


    void DownloadBooks(String url, String title1) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        String tempTitle = title1.replace("", "");
        request.setTitle(tempTitle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, tempTitle + ".pdf");
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        request.setMimeType("application/pdf");
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadManager.enqueue(request);
    }

//    private boolean downloadTask(String url) throws Exception {
//        if (!url.startsWith("http")) {
//            return false;
//        }
//        String name = "temp.mcaddon";
//        try {
//            File file = new File(Environment.getExternalStorageDirectory(), "Download");
//            if (!file.exists()) {
//                //noinspection ResultOfMethodCallIgnored
//                file.mkdirs();
//            }
//            File result = new File(file.getAbsolutePath() + File.separator + name);
//            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
//            request.setDestinationUri(Uri.fromFile(result));
//            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//            if (downloadManager != null) {
//                downloadManager.enqueue(request);
//            }
//            //mToast(mContext, "Starting download...");
//            MediaScannerConnection.scanFile(Jurnal.this, new String[]{result.toString()}, null,
//                    new MediaScannerConnection.OnScanCompletedListener() {
//                        public void onScanCompleted(String path, Uri uri) {
//                        }
//                    });
//        } catch (Exception e) {
//            Log.e(">>>>>", e.toString());
//            //mToast(this, e.toString());
//            return false;
//        }
//        return true;
//    }

    class RetrievePDFStream extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {

            pdfView.fromStream(inputStream).load();
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Jurnal.this, "Mohon tunggu...", Toast.LENGTH_SHORT).show();

        }


    }

}