package coffeespace.com.br.periciasis.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import coffeespace.com.br.periciasis.R;
import coffeespace.com.br.periciasis.Sistema.Objetos.Objeto;

public class CapturaActivity extends AppCompatActivity {


    Button btcapturar, btpegardosd;
    ImageView imagem;
    private static final int CAM_REQUEST = 1313;
    Objeto objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);
        imagem = (ImageView) findViewById(R.id.imagemcapturada);
        btcapturar = (Button) findViewById(R.id.btcapturarimagem);

        btcapturar.setOnClickListener(new btnTakenPhotoClicked());


    }

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

    private Bitmap getImageFileFromSDCard(String filename) {
        Bitmap bitmap = null;

        File imageFile = new File(Environment.getExternalStorageDirectory() + filename);
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

    private void storeCameraPhotoInSDCard(Bitmap bitmap, String currentDate) {
        File outputFile = new File(Environment.getExternalStorageDirectory(), "photo_" + currentDate + ".jpg");
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

    class btnTakenPhotoClicked implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            CapturaActivity.this.startActivityForResult(intent, CAM_REQUEST);

        }
    }
}
