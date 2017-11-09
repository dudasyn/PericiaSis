package coffeespace.com.br.periciasis.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import coffeespace.com.br.periciasis.R;

public class LocalActivity extends AppCompatActivity {

    Button btinserirlocal,btsairlocal;
    EditText editlocal,editdapreservacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        btinserirlocal = (Button)findViewById(R.id.btinserirlocal);
        btsairlocal = (Button)findViewById(R.id.btsairlocal);
        editlocal = (EditText)findViewById(R.id.editlocal);
        editdapreservacao = (EditText)findViewById(R.id.editdapreservacao);


        btsairlocal.setOnClickListener(new btSairLocalClicked());
        btinserirlocal.setOnClickListener(new btInserirLocalClicked());

    }

    class btSairLocalClicked implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            startActivity(new Intent(LocalActivity.this, ExamesActivity.class));
            finish();
        }
    }
    class btInserirLocalClicked implements Button.OnClickListener{

        @Override
        public void onClick(View view) {
            Toast.makeText(LocalActivity.this, "Novo local inserido", Toast.LENGTH_SHORT).show();
            String preservacao = editdapreservacao.getText().toString();
            String local = editlocal.getText().toString();
            MainActivity.pericia.setLocal(local);
            MainActivity.pericia.setPreservacaodolocal(preservacao);
        }
    }

}
