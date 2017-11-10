package coffeespace.com.br.periciasis.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import coffeespace.com.br.periciasis.R;
import coffeespace.com.br.periciasis.Sistema.Objetos.Objeto;

import static coffeespace.com.br.periciasis.Activitys.MainActivity.pericia;

public class CapturaActivity extends AppCompatActivity {

    //private static final int CAM_REQUEST = 1888;
    String RAIZ = Environment.getExternalStorageDirectory().getAbsolutePath();
    private ImageView imageView;
    Button btcapturar, btpegardosd;
    ImageView imagem;
    int n=1;
    private static final int CAM_REQUEST = 1313;
    static String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);
        imagem = (ImageView) findViewById(R.id.imagemcapturada);
        btcapturar = (Button) findViewById(R.id.btcapturarimagem);

        btcapturar.setOnClickListener(new btnTakenPhotoClicked());


    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
           // imagem.setImageBitmap(photo);

            String partFilename = currentDateFormat();
            String storeFilename = "photo_" + partFilename + ".jpg";
            Log.d("TAG2","store - " +storeFilename);
            pericia.getaNamePictures().add(storeFilename);
            Log.d("TAG2","FORA");
            for (int i=0;i<pericia.getaNamePictures().size();i++){
                Log.d("TAG2",pericia.getaNamePictures().get(i));
            }

            storeCameraPhotoInSDCard(photo, storeFilename);
            // display the image from SD Card to ImageView Control

            Bitmap mBitmap = getImageFileFromSDCard(storeFilename);
/*

            File imageFile = new File(Environment.getExternalStorageDirectory() + storeFilename);
            try {
                FileInputStream fis = new FileInputStream(imageFile);
                mBitmap = BitmapFactory.decodeStream(fis);
            } catch (FileNotFoundException e) {
                Log.d("TAG","ERROR AO PUCAR");
                e.printStackTrace();
            }
            */
            imagem.setImageBitmap(photo);


            //------ THIS IS AN EXAMPLE FOR PICTURE ------
            //ByteArrayOutputStream bitmapStream = new ByteArrayOutputStream();
            //image.compress(Bitmap.CompressFormat.PNG, 100, bitmapStream);
            //try {
            //  outputStream.write(bitmapStream.toByteArray());
            //} catch (IOException e1) {
            //  Log.i(TAG, "Unable to write file contents.");
            //}
            Toast.makeText(this, "Imagem Armazenada no SDCARD", Toast.LENGTH_SHORT).show();
            n++;
        }
    }

    class btnTakenPhotoClicked implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAM_REQUEST);


        }
    }


/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAM_REQUEST) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imagem.setImageBitmap(bitmap);

            String partFilename = currentDateFormat();
            storeCameraPhotoInSDCard(bitmap, partFilename);

            // display the image from SD Card to ImageView Control
            String storeFilename = "photo_" + partFilename + ".jpg";
            Bitmap mBitmap = getImageFileFromSDCard(storeFilename);
            imagem.setImageBitmap(mBitmap);
        }
        if (requestCode == CAM_REQUEST) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imagem.setImageBitmap(bitmap);

            String partFilename = currentDateFormat();
            storeCameraPhotoInSDCard(bitmap, partFilename);

            // display the image from SD Card to ImageView Control
            String storeFilename = "photo_" + partFilename + ".jpg";
            Bitmap mBitmap = getImageFileFromSDCard(storeFilename);
            imagem.setImageBitmap(mBitmap);
        }
    }
*/
    private Bitmap getImageFileFromSDCard(String filename) {
        Bitmap bitmap = null;

        File imageFile = new File(RAIZ + filename);
        Log.d("TAG2","DENTRO");
        Log.d ("TAG2","raiz " + RAIZ);
        Log.d("TAG2","filename " + filename);
        try {
            FileInputStream fis = new FileInputStream(imageFile);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private String currentDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    private void storeCameraPhotoInSDCard(Bitmap bitmap, String name) {
        File outputFile = new File(Environment.getExternalStorageDirectory(), name);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    class btnTakenPhotoClicked implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            CapturaActivity.this.startActivityForResult(intent, CAM_REQUEST);



        }
    }
*/
}
