package coffeespace.com.br.periciasis.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import coffeespace.com.br.periciasis.Google.CreateFile;
import coffeespace.com.br.periciasis.R;
import coffeespace.com.br.periciasis.Sistema.Laudo;

import static android.app.Activity.RESULT_OK;
import static coffeespace.com.br.periciasis.Activitys.MainActivity.ocorrencia;
import static coffeespace.com.br.periciasis.Activitys.MainActivity.pericia;

/**
 * Created by user on 01/11/2017.
 */

public class LaudoActivity extends android.support.v4.app.Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Button button_create_file;
    private Button button_upload_to_google_drive;
    File theFile;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "<< DRIVE >>";
    protected static final int REQUEST_CODE_RESOLUTION = 1337;
    private String FOLDER_NAME = "Laudos";
    private String RAIZ = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    private String nome_laudo, content_laudo;

    WebView weblaudo;
    static Laudo ld;

    MainActivity act;

    public MainActivity getAct() {
        return act;
    }

    public void setAct(MainActivity act) {
        this.act = act;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.laudo, container, false);
        Button btfinalizarlaudo = (Button) view.findViewById(R.id.btfinalizarlaudo);

        button_create_file = (Button) view.findViewById(R.id.button_create_file);
        button_upload_to_google_drive = (Button) view.findViewById(R.id.button_upload_to_google_drive);

        if (ocorrencia != null) {
            ld = new Laudo();
            ld.setPerito(pericia.getPeritodesignado());
            ld.setCorpodolaudo(pericia.imprimeCorpoLaudo());

            nome_laudo = "RO 0" + firstTwo(ocorrencia.getDp()) + "-" + ocorrencia.getRo() + "-" + ocorrencia.getAno() + " CI " + ocorrencia.getCi() + "-10" + firstTwo(ocorrencia.getDp()) + "-" + ocorrencia.getAno() +
                    " Laudo " + ocorrencia.getLaudo() + "-" + ocorrencia.getAno() + " (" + ocorrencia.getDia() + "-" + ocorrencia.getMes() + "-" + ocorrencia.getAno() + ")";

            content_laudo = ld.imprimeLaudo();

            weblaudo = (WebView) view.findViewById(R.id.vizualizacaodelaudo);
            weblaudo.loadDataWithBaseURL(null, ld.imprimeLaudo(), "text/html", "utf-8", null);

        }

/*

        button_create_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //WRITE A SIMPLE TEXT FILE IN SDCARD. BE CAREFUL TO GRANT PERMISSION IN ANDROID 6+
                writeToFile("tehfile", "<html><head><title> fuck google...</title></head><body>Just a simple text</body></html>");
            }
        });
*/
        button_upload_to_google_drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGoogleApiClient != null) {
                    upload_to_drive();
                } else {
                    Log.e(TAG, "Could not fucking connect to google drive manager");
                }
            }
        });


        //// MY CODE
        btfinalizarlaudo.setOnClickListener(new btFinalizarClicked());


        return view;
    }


    class btFinalizarClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {

            Log.d("TAG", "CRIANDO LAUDO");

            String filename = "RO 0" + firstTwo(ocorrencia.getDp()) + "-" + ocorrencia.getRo() + "-" + ocorrencia.getAno() + " CI " + ocorrencia.getCi() + "-10" + firstTwo(ocorrencia.getDp()) + "-" + ocorrencia.getAno() +
                    " Laudo " + ocorrencia.getLaudo() + "-" + ocorrencia.getAno() + " (" + ocorrencia.getDia() + "-" + ocorrencia.getMes() + "-" + ocorrencia.getAno() + ")";
             Toast.makeText(act, "Enviado pro Google Drive " + filename, Toast.LENGTH_SHORT).show();

            String laudo = ld.imprimeLaudo();

            Intent intent = new Intent(getContext(), CreateFile.class);
            intent.putExtra("laudofinal", laudo);
            intent.putExtra("nomedoarquivo", filename);
            startActivity(intent);

        }

        public boolean canWriteOnExternalStorage() {
            // get the state of your external storage
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // if storage is mounted return true
                Log.d("TAG", "Pode Gravar");
                return true;
            }
            return false;
        }

    }


    public String firstTwo(String str) {

        if (str.length() < 2) {
            return str;
        } else {
            return str.substring(0, 2);
        }
    }


    private void upload_to_drive() {

        //async check if folder exists... if not, create it. continue after with create_file_in_folder(driveId);
        check_folder_exists();
    }

    private void check_folder_exists() {
        Query query =
                new Query.Builder().addFilter(Filters.and(Filters.eq(SearchableField.TITLE, FOLDER_NAME), Filters.eq(SearchableField.TRASHED, false)))
                        .build();
        Drive.DriveApi.query(mGoogleApiClient, query).setResultCallback(new ResultCallback<DriveApi.MetadataBufferResult>() {
            @Override
            public void onResult(DriveApi.MetadataBufferResult result) {
                if (!result.getStatus().isSuccess()) {
                    Log.e(TAG, "Cannot create folder in the root.");
                } else {

                    boolean isFound = false;
                    for (Metadata m : result.getMetadataBuffer()) {
                        if (m.getTitle().equals(FOLDER_NAME)) {
                            Log.e(TAG, "Folder exists");
                            isFound = true;
                            DriveId driveId = m.getDriveId();

                            if (pericia.getaNamePictures().size() == 0) {
                                Log.d("TAG2","there is no foto");
                            } else {
                                for (int i = 0; i < pericia.getaNamePictures().size(); i++) {
                                    Log.d("TAG2", "raiz " + RAIZ);
                                    Log.d("TAG2", "nofor" + RAIZ + pericia.getaNamePictures().get(i));
                                    create_image(driveId, RAIZ + pericia.getaNamePictures().get(i));
                                }
                            }
                            create_laudo(driveId, nome_laudo, content_laudo);
                            Toast.makeText(act, "Fechando em 5 segundos ", Toast.LENGTH_SHORT).show();
                            new CountDownTimer(5000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    Log.d("TAG2","seconds remaing" + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    Log.d("TAG2","done!");
                                    Intent i = getContext().getPackageManager()
                                            .getLaunchIntentForPackage( getContext().getPackageName() );
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                }
                            }.start();
                            break;
                        }
                    }
                    if (!isFound) {
                        Log.i(TAG, "Folder not found; creating it.");
                        MetadataChangeSet changeSet = new MetadataChangeSet.Builder().setTitle(FOLDER_NAME).build();
                        Drive.DriveApi.getRootFolder(mGoogleApiClient)
                                .createFolder(mGoogleApiClient, changeSet)
                                .setResultCallback(new ResultCallback<DriveFolder.DriveFolderResult>() {
                                    @Override
                                    public void onResult(DriveFolder.DriveFolderResult result) {
                                        if (!result.getStatus().isSuccess()) {
                                            Log.e(TAG, "Não foi possível criar o diretório");
                                        } else {
                                            Log.i(TAG, "Created a folder");
                                            DriveId driveId = result.getDriveFolder().getDriveId();
                                            if (pericia.getaNamePictures().size() == 0) {
                                                Log.d("TAG2","there is no foto");
                                            } else {
                                                for (int i = 0; i < pericia.getaNamePictures().size(); i++) {
                                                    Log.d("TAG2", "raiz " + RAIZ);
                                                    Log.d("TAG2", "nofor" + RAIZ + pericia.getaNamePictures().get(i));
                                                    create_image(driveId, RAIZ + pericia.getaNamePictures().get(i));
                                                }
                                            }

                                            create_laudo(driveId, nome_laudo, content_laudo);
                                            // Enviou laudo e imagens
                                            ExamesActivity.listObjetos.clear();
                                            ExamesActivity.adapterlistobjetos.notifyDataSetChanged();

                                            Toast.makeText(act, "Fechando em 5 segundos ", Toast.LENGTH_SHORT).show();
                                            new CountDownTimer(5000, 1000) {

                                                public void onTick(long millisUntilFinished) {
                                                    Log.d("TAG2","seconds remaing" + millisUntilFinished / 1000);
                                                }

                                                public void onFinish() {
                                                    Log.d("TAG2","done!");
                                                    Intent i = getContext().getPackageManager()
                                                            .getLaunchIntentForPackage( getContext().getPackageName() );
                                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(i);
                                                }
                                            }.start();


                                            }
                                        }
                                    });
                                }
                    }
                }
            });
        }


    private void create_image(final DriveId driveId, final String name) {

        Drive.DriveApi.newDriveContents(mGoogleApiClient).setResultCallback(new ResultCallback<DriveApi.DriveContentsResult>() {
            @Override
            public void onResult(@NonNull DriveApi.DriveContentsResult driveContentsResult) {
                if (!driveContentsResult.getStatus().isSuccess()) {
                    Log.e(TAG, "U AR A MORON! Error while trying to create new file contents");
                    return;
                }


                OutputStream outputStream = driveContentsResult.getDriveContents().getOutputStream();

                //------ THIS IS AN EXAMPLE FOR PICTURE ------
                // ByteArrayOutputStream bitmapStream = new ByteArrayOutputStream();
                //Image  image;

                //image.compress(Bitmap.CompressFormat.PNG, 100, bitmapStream);
                //try {
                //  outputStream.write(bitmapStream.toByteArray());
                //} catch (IOException e1) {
                //  Log.i(TAG, "Unable to write file contents.");
                //}
                //// Create the initial metadata - MIME type and title.
                //// Note that the user will be able to change the title later.
                //MetadataChangeSet metadataChangeSet = new MetadataChangeSet.Builder()
                //    .setMimeType("image/jpeg").setTitle("Android Photo.png").build();

                //------ THIS IS AN EXAMPLE FOR FILE --------
                //Drive  drive;
                // com.google.api.services.drive.Drive drive2;
                // com.google.api.services.drive.Drive drive3;

                //File file = drive.files().create(fileMetadata, mediaContent)
                //        .setFields("id")
                //          .execute();
                //Toast.makeText(act, "Uploading to drive. If you didn't fucked up something like usual you should see it there", Toast.LENGTH_LONG).show();


                theFile = new File(name); //>>>>>> WHAT FILE ?
                Log.d("TAG2", "in_create_file" + name);

                try {
                    FileInputStream fileInputStream = new FileInputStream(theFile);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e1) {
                    Log.i(TAG, "Unable to write file contents");
                }
                //Writer writer = new OutputStreamWriter(outputStream);
                // writer.write("Cojoasd");
                // outputStream.write("<html><head><title></title><body>CONVERT MODA FOCA</body></html>",0,);
                MetadataChangeSet changeSet = new MetadataChangeSet.Builder().setTitle(theFile.getName()).setMimeType("image/jpeg").setStarred(false).build();
                DriveFolder folder = driveId.asDriveFolder();
                folder.createFile(mGoogleApiClient, changeSet, driveContentsResult.getDriveContents())
                        .setResultCallback(new ResultCallback<DriveFolder.DriveFileResult>() {
                            @Override
                            public void onResult(@NonNull DriveFolder.DriveFileResult driveFileResult) {
                                if (!driveFileResult.getStatus().isSuccess()) {
                                    Log.e(TAG, "U AR A MORON!  Error while trying to create the file");
                                    return;
                                }
                                Toast.makeText(act, "Imagens enviadas pro Gdrive: " + driveFileResult.getDriveFile().getDriveId() , Toast.LENGTH_SHORT).show();
                                Log.v(TAG, "Created a file: " + driveFileResult.getDriveFile().getDriveId());
                            }
                        });

            }
        });

    }

    private void create_laudo(final DriveId driveId, final String name, final String content) {


        Drive.DriveApi.newDriveContents(mGoogleApiClient).setResultCallback(new ResultCallback<DriveApi.DriveContentsResult>() {
            @Override
            public void onResult(@NonNull DriveApi.DriveContentsResult driveContentsResult) {
                if (!driveContentsResult.getStatus().isSuccess()) {
                    Log.e(TAG, "U AR A MORON! Error while trying to create new file contents");
                    return;
                }

                OutputStream outputStream = driveContentsResult.getDriveContents().getOutputStream();
                theFile = new File(name); //>>>>>> WHAT FILE ?
                Log.d("TAG2", "in_create_file" + name);

                try {
                    FileInputStream fileInputStream = new FileInputStream(theFile);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e1) {
                    Log.i(TAG, "Unable to write file contents");
                }

                Writer writer = new OutputStreamWriter(outputStream);
                try {
                    writer.write(content);
                } catch (IOException e) {
                    Log.d("TAG2", "Nao conseguiu escrever o conteúdo");
                    e.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // outputStream.write("<html><head><title></title><body>CONVERT MODA FOCA</body></html>",0,);
                MetadataChangeSet changeSet = new MetadataChangeSet.Builder().setTitle(name).setMimeType("text/html").setStarred(false).build();
                DriveFolder folder = driveId.asDriveFolder();
                folder.createFile(mGoogleApiClient, changeSet, driveContentsResult.getDriveContents())
                        .setResultCallback(new ResultCallback<DriveFolder.DriveFileResult>() {
                            @Override
                            public void onResult(@NonNull DriveFolder.DriveFileResult driveFileResult) {
                                if (!driveFileResult.getStatus().isSuccess()) {
                                    Log.e(TAG, " Error while trying to create the file");
                                    return;
                                }
                                Toast.makeText(act, "Laudo enviado pro Gdrive: " + driveFileResult.getDriveFile().getDriveId() , Toast.LENGTH_SHORT).show();

                                Log.v(TAG, "Created a file: " + driveFileResult.getDriveFile().getDriveId());
                            }
                        });

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(act).addApi(Drive.API)
                    .addScope(Drive.SCOPE_FILE)
                    .addScope(Drive.SCOPE_APPFOLDER) // required for App Folder sample
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
        mGoogleApiClient.connect();
    }

    public void writeToFile(String fileName, String body) {
        FileOutputStream fos = null;
        try {
            final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/xtests/");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    Log.e("ALERT", "U AR A MORON!  could not create the directories. CHECK THE FUCKING PERMISSIONS SON!");
                }
            }


            final File myFile = new File(dir, fileName + "_" + String.valueOf(System.currentTimeMillis()) + ".html");
            if (!myFile.exists()) {
                myFile.createNewFile();
            }

            fos = new FileOutputStream(myFile);
            fos.write(body.getBytes());
            fos.close();
            Toast.makeText(act, "File created ok! Let me give you a fucking congratulations!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.v(TAG, "+++++++++++++++++++ onConnected +++++++++++++++++++");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG, "onConnectionSuspended [" + String.valueOf(i) + "]");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.i(TAG, "GoogleApiClient connection failed: " + result.toString());
        if (!result.hasResolution()) {
            // show the localized error dialog.
            GoogleApiAvailability.getInstance().getErrorDialog(act, result.getErrorCode(), 0).show();
            return;
        }
        try {
            result.startResolutionForResult(act, REQUEST_CODE_RESOLUTION);
        } catch (IntentSender.SendIntentException e) {
            Log.e(TAG, "U AR A MORON! Exception while starting resolution activity", e);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RESOLUTION && resultCode == RESULT_OK) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onPause() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }

}

