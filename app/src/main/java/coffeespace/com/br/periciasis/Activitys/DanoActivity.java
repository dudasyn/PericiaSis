package coffeespace.com.br.periciasis.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import coffeespace.com.br.periciasis.R;
import coffeespace.com.br.periciasis.Sistema.Dano;

import static coffeespace.com.br.periciasis.Activitys.MainActivity.pericia;

public class DanoActivity extends AppCompatActivity {
    EditText editdano;
    Button btinserirdano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dano);

        editdano = (EditText) findViewById(R.id.editdano);
        btinserirdano = (Button) findViewById(R.id.btdano);

        btinserirdano.setOnClickListener(new btInserirDanoClicked());
    }

    class btInserirDanoClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {

           // Dano dano = new Dano();
           // dano.setTipo(editdano.getText().toString());
            pericia.getaObjetos().get(ExamesActivity.poslistview).setAvarias(editdano.getText().toString());
            Toast.makeText(DanoActivity.this, "Avaria constatada no Objeto", Toast.LENGTH_SHORT).show();

        }
    }
}

