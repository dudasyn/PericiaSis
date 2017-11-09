/**
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package coffeespace.com.br.periciasis.Google;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;
import com.google.android.gms.drive.MetadataChangeSet;

import java.io.IOException;

import coffeespace.com.br.periciasis.R;

/**
 * An activity that creates a text file in the App Folder.
 */
public class CreateFileInAppFolderActivity extends BaseDemoActivity {

    String nomedoarquivo;
    private static final String TAG = "CreateFileActivity";
    @Override
    public void onConnected(Bundle connectionHint) {
        super.onConnected(connectionHint);
        // create new contents resource
        Drive.DriveApi.newDriveContents(getGoogleApiClient())
                .setResultCallback(driveContentsCallback);
    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_empty_file);

        String warningtext = "<html><head><title>ATENÇÃO</title></head><body><br><br><hr><center>" +
                "<b>ATENÇÃO</b><br><hr></center><br><center>O conteúdo do laudo é de responsabilidade do perito, revise antes de entregar.</center></body></html>";
        WebView viewwarning = (WebView)findViewById(R.id.warning);
        viewwarning.loadDataWithBaseURL(null, warningtext, "text/html", "utf-8", null);
    }
*/
    // [START drive_contents_callback]
    final private ResultCallback<DriveContentsResult> driveContentsCallback =
            new ResultCallback<DriveContentsResult>() {
        @Override
        public void onResult(DriveContentsResult result) {
            if (!result.getStatus().isSuccess()) {
                showMessage("Error while trying to create new file contents");
                return;
            }

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                final String laudo = extras.getString("laudofinal");

                nomedoarquivo = extras.getString("nomedoarquivo"); //application/vnd.google-apps.document text/html


                MetadataChangeSet changeSet = new MetadataChangeSet.Builder().setTitle(nomedoarquivo).setMimeType("text/html").build();
// create a file on root folder
                Drive.DriveApi.getAppFolder(getGoogleApiClient())
                        .createFile(getGoogleApiClient(), changeSet, result.getDriveContents())
                        .setResultCallback(fileCallback);
            }
        }
    };
    // [END drive_contents_callback]

    final private ResultCallback<DriveFileResult> fileCallback = new
            ResultCallback<DriveFileResult>() {
        @Override
        public void onResult(DriveFileResult result) {
            if (!result.getStatus().isSuccess()) {
                showMessage("Error while trying to create the file");
                return;
            }
            String teste = result.getDriveFile().getDriveId().toString();
            Toast.makeText(CreateFileInAppFolderActivity.this, teste, Toast.LENGTH_LONG).show();
            showMessage("Created a file in App Folder: "
                    + result.getDriveFile().getDriveId());

        }
    };
}
