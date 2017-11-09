package coffeespace.com.br.periciasis.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import coffeespace.com.br.periciasis.R;

public class ConcDinActivity extends AppCompatActivity {

    Button btinserirdinconc,btsairdinconc;
    EditText editdin,editconc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conc_din);

        btinserirdinconc = (Button)findViewById(R.id.btinserirdinamicaeconclusao);
        btsairdinconc = (Button)findViewById(R.id.btsairdinamicaeconclusao);
        editconc = (EditText)findViewById(R.id.editconclusao);
        editdin = (EditText)findViewById(R.id.editdinamica);

        btinserirdinconc.setOnClickListener(new btInserirDinConcClicked());
        btsairdinconc.setOnClickListener(new btSairDinConcClicked());

    }
class btInserirDinConcClicked implements Button.OnClickListener{
    @Override
    public void onClick(View view) {

        String dinamica = editdin.getText().toString();
        String conclusao = editconc.getText().toString();


    }
}

class btSairDinConcClicked implements Button.OnClickListener{
    @Override
    public void onClick(View view) {
        startActivity(new Intent(ConcDinActivity.this, ExamesActivity.class));

    }
}
}
