package coffeespace.com.br.periciasis.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import coffeespace.com.br.periciasis.R;
import coffeespace.com.br.periciasis.Sistema.Objetos.Automovel;
import coffeespace.com.br.periciasis.Sistema.Objetos.Caminhao;
import coffeespace.com.br.periciasis.Sistema.Objetos.Objeto;
import coffeespace.com.br.periciasis.Sistema.Objetos.Onibus;
import coffeespace.com.br.periciasis.Sistema.Objetos.Veiculo;

import static coffeespace.com.br.periciasis.Activitys.MainActivity.pericia;

public class ObjetosActivity extends AppCompatActivity {

    Button btinserirobjetos, btinserirfotoobjetos; //btveiculosvoltar,
    //Spinner spinnertipoveiculo;
    EditText editnomeobjeto, editdescricaoobjeto;
    TextView labelobjetos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetos);

        labelobjetos = (TextView) findViewById(R.id.labelobjetos);
        String label;
        labelobjetos.setText("Inserir objeto " + Integer.toString((MainActivity.pericia.getaObjetos().size()) + 1));

        btinserirfotoobjetos = (Button) findViewById(R.id.btfotografarobjeto);
        btinserirobjetos = (Button) findViewById(R.id.btinserirobjetos);
//        spinnertipoveiculo = (Spinner) findViewById(R.id.spinnertipoveiculos);

        //ArrayList<String> tipodeveiculos = new ArrayList<String>(Arrays.asList("Automóvel", "Motocicleta", "Ônibus", "Caminhão", "Caminhonete", "Caminhoneta", "Jet Ski"));
        //ArrayAdapter<String> adaptertipodeveiculos = new ArrayAdapter<String>(ObjetosActivity.this, android.R.layout.simple_list_item_1, tipodeveiculos);
        //spinnertipoveiculo.setAdapter(adaptertipodeveiculos);
        editnomeobjeto = (EditText) findViewById(R.id.editnomeobjeto);
        editdescricaoobjeto = (EditText) findViewById(R.id.editdescricaoobjeto);
        btinserirfotoobjetos.setOnClickListener(new btInserirFotoObjetoClicked());
        btinserirobjetos.setOnClickListener(new btInserirObjetosClicked());

    }

    class btInserirObjetosClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {


            String nome = editnomeobjeto.getText().toString();
            String descricao = editdescricaoobjeto.getText().toString();
            int index = 0;

            Objeto objeto = new Objeto();
            objeto.setNome(nome);
            objeto.setDescricao(descricao);

            pericia.getaObjetos().add(objeto);
            Log.d("TAG2","nobjetos -> " + pericia.getaObjetos().size());


            Log.d("TAG", "listOsize" + ExamesActivity.listObjetos.size());
            Toast.makeText(getApplicationContext(), "Novo Objeto inserido", Toast.LENGTH_SHORT).show();


            labelobjetos.setText("Inserir objeto " + (pericia.getaObjetos().size() + 1));

            ExamesActivity.listObjetos.add(nome + "(" + pericia.getaObjetos().size() + ")");

            ExamesActivity.adapterlistobjetos.notifyDataSetChanged();
            eraseFields();

        }
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    class btInserirFotoObjetoClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(ObjetosActivity.this, CapturaActivity.class));
        }
    }

    private void addFieldsInObjeto(Objeto o) {


    }


    private void eraseFields() {

        editdescricaoobjeto.setText("");
        editnomeobjeto.setText("");
    }


}




