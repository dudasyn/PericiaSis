package coffeespace.com.br.periciasis.Google;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;
import com.google.android.gms.drive.MetadataChangeSet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import coffeespace.com.br.periciasis.Activitys.ExamesActivity;
import coffeespace.com.br.periciasis.Activitys.MainActivity;
import coffeespace.com.br.periciasis.R;


/**
 * An activity to illustrate how to create a file.
 */
public class CreateEmptyFileActivity extends BaseDemoActivity {

    private static final String TAG = "CreateFileActivity";
    String nomedoarquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_empty_file);



        String warningtext = "<html><head><title>ATENÇÃO</title></head><body><br><br><hr><center>" +
                "<b>ATENÇÃO</b><br><hr></center><br><center>O conteúdo do laudo é de responsabilidade do perito, revise antes de entregar.</center></body></html>";
        WebView viewwarning = (WebView)findViewById(R.id.warning);
        viewwarning.loadDataWithBaseURL(null, warningtext, "text/html", "utf-8", null);


    }

    @Override
    public void onConnected(Bundle connectionHint) {
        super.onConnected(connectionHint);
        // create new contents resource
        Drive.DriveApi.newDriveContents(getGoogleApiClient())
                .setResultCallback(driveContentsCallback);
    }

    final private ResultCallback<DriveContentsResult> driveContentsCallback = new
            ResultCallback<DriveContentsResult>() {
                @Override
                public void onResult(DriveContentsResult result) {
                    if (!result.getStatus().isSuccess()) {
                        showMessage("Error while trying to create new file contents");
                        return;
                    }
                    final DriveContents driveContents = result.getDriveContents();

                    // Perform I/O off the UI thread.
                    new Thread() {
                        @Override
                        public void run() {
                            // write content to DriveContents

                            OutputStream outputStream = driveContents.getOutputStream();

                            Writer writer = new OutputStreamWriter(outputStream);
                            try {
                                Bundle extras = getIntent().getExtras();
                                if (extras != null) {
                                    final String laudo = extras.getString("laudofinal");

                                    writer.write(laudo);
                                    writer.close();
                                    nomedoarquivo = extras.getString("nomedoarquivo"); //application/vnd.google-apps.document text/html
                                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder().setTitle(nomedoarquivo).setMimeType("text/html").setStarred(true).build();
// create a file on root folder
                                    Drive.DriveApi.getRootFolder(getGoogleApiClient())
                                            .createFile(getGoogleApiClient(), changeSet, driveContents)
                                            .setResultCallback(fileCallback);


                                }
                            } catch (IOException e) {
                                Log.e(TAG, e.getMessage());
                            }

                        }
                    }.start();
                }
            };

    final private ResultCallback<DriveFileResult> fileCallback = new
            ResultCallback<DriveFileResult>() {
                @Override
                public void onResult(DriveFileResult result) {
                    if (!result.getStatus().isSuccess()) {
                        showMessage("Error while trying to create the file");
                        return;
                    }


                    showMessage("Created a file with content: " + result.getDriveFile().getDriveId());

                }
            };
    @Override
    public void onBackPressed() {

        Toast.makeText(this, "Clicou no voltar", Toast.LENGTH_SHORT).show();
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}