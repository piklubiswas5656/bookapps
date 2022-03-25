package ncert.solutions.pdfviewdown;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import ncert.solutions.MainActivity;
import ncert.solutions.R;

public class pdfviewdow extends AppCompatActivity {
    private String url;
    private String chiled;
    private Button pdfdownlaod;
    private String baseurl;
    private TextView perstage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewdow);
        perstage = findViewById(R.id.progress_text);
        perstage.setVisibility(View.INVISIBLE);
        fix();
        pdfdownlaod = (Button) findViewById(R.id.pdfdownlaod);

        PRDownloader.initialize(getApplicationContext());

//        url = getIntent().getStringExtra("URL");
        baseurl = "https://westbalsevnclassbook.s3.ap-south-1.amazonaws.com/";

//        chiled = URLUtil.guessFileName(url, null, null);
        chiled = "Ganit+Prabha+Class+VI(1).pdf";
        url=baseurl+chiled;
        fileexits();

    }

    private void intentcheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.
                getActiveNetworkInfo().isConnected()) {
            downloadpdf();

        } else {
            dilog();
        }
    }

    private void dilog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Check Your Inter Connection")
                .setTitle("No Internet")
                .setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intentcheck();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void downloadpdf() {

        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + chiled);
        if (pdfFile.exists()) //Checking if the file exists or not
        {
            pdfopen();

        } else {
            /*ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Download");
            pd.setMessage("One Time Downloading...");
            pd.setCancelable(false);
            pd.show();*/
            perstage.setVisibility(View.VISIBLE);
            perstage.setText("Downloading:0%");
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            PRDownloader.download(url, file.getPath(), URLUtil.guessFileName(url, null, null))
                    .build()
                    .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                        @Override
                        public void onStartOrResume() {

                        }
                    })
                    .setOnPauseListener(new OnPauseListener() {
                        @Override
                        public void onPause() {

                        }
                    })
                    .setOnCancelListener(new OnCancelListener() {
                        @Override
                        public void onCancel() {

                        }
                    })
                    .setOnProgressListener(new OnProgressListener() {
                        @Override
                        public void onProgress(Progress progress) {
                            long per = progress.currentBytes * 100 / progress.totalBytes;
//                            pd.setMessage("Downloading:" + per);
                            perstage.setText("Downloading:" + per + "%");

                        }
                    })
                    .start(new OnDownloadListener() {
                        @Override
                        public void onDownloadComplete() {
//                            pd.dismiss();
                            perstage.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Download Complete", Toast.LENGTH_SHORT);
                            fileexits();
                        }

                        @Override
                        public void onError(Error error) {
//                            pd.dismiss();
                            perstage.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                        }


                    });
        }
    }

    private void pdfopen() {
        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + chiled);
       String pdfFilepath =String.valueOf(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + chiled));
        if (pdfFile.exists()) //Checking if the file exists or not
        {
            /*Uri path = Uri.fromFile(pdfFile);
            Intent objIntent = new Intent(Intent.ACTION_VIEW);
            objIntent.setDataAndType(path, "application/pdf");
            objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(objIntent);//Starting the pdf viewer
             */
            Intent intent = new Intent(getApplicationContext(), PdfViewer.class);
            intent.putExtra("path", pdfFilepath);
            startActivity(intent);
        } else {

            Toast.makeText(getApplicationContext(), "The file not exists! ", Toast.LENGTH_SHORT).show();

        }
    }

    public void king() {
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    intentcheck();
                } else {
                    Toast.makeText(getApplicationContext(), "Allow All Permissions", Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }

    private void fix() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    private void fileexits() {
        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + chiled);
        if (pdfFile.exists()) //Checking if the file exists or not
        {
            pdfdownlaod.setText("OPEN");
        }
    }


    public void downBtn(View view) {
        king();
    }
}