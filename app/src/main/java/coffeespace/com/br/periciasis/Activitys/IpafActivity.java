package coffeespace.com.br.periciasis.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import coffeespace.com.br.periciasis.R;
import coffeespace.com.br.periciasis.Sistema.Ipaf;
import coffeespace.com.br.periciasis.Sistema.Objetos.Objeto;
import coffeespace.com.br.periciasis.Sistema.Objetos.Veiculo;

import static coffeespace.com.br.periciasis.Activitys.MainActivity.pericia;


public class IpafActivity extends AppCompatActivity {
    Button btinseriripaf,btinserirfotoipaf;
    EditText editangulacao, editlocalizacao, editeixomaior, editeixomenor, editdistsolo, edittrajetoria;
    TextView labelipaf, labelobjimpactado;
    Spinner sptransfixante, spformato, sporientacao, sporigem;
    private Objeto o;
    private Ipaf ipaf;
    static int npafs=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipaf);

        labelipaf = (TextView) findViewById(R.id.labelipaf);


         npafs = (pericia.getaObjetos().get(ExamesActivity.poslistview).getaIpafs().size());
        labelipaf.setText("Inserir " + (npafs + 1) + "º Ipaf no objeto: ");

        btinseriripaf = (Button) findViewById(R.id.btinseriripaf);
         btinserirfotoipaf = (Button) findViewById(R.id.btinserifotoipaf);
        editangulacao = (EditText) findViewById(R.id.editangulacao);
        editlocalizacao = (EditText) findViewById(R.id.editlocalicacao);
        editeixomaior = (EditText) findViewById(R.id.editeixomaior);
        editeixomenor = (EditText) findViewById(R.id.editeixomenor);
        editdistsolo = (EditText) findViewById(R.id.editdistsolo);
        //editazimute = (EditText) findViewById(R.id.editazimute);
        sporigem = (Spinner) findViewById(R.id.sporigemipaf);
        edittrajetoria = (EditText) findViewById(R.id.edittrajetoria);

        sptransfixante = (Spinner) findViewById(R.id.sptransfixante);
        sporientacao = (Spinner) findViewById(R.id.sporientacaoipaf);
        spformato = (Spinner) findViewById(R.id.spformatoipaf);


        sptransfixante.setOnItemSelectedListener(new spTransfixanteChanged());


        labelobjimpactado = (TextView) findViewById(R.id.objetoimpactado);


        btinseriripaf.setOnClickListener(new btInserirIpafClicked());
        btinserirfotoipaf.setOnClickListener(new btInserirFotoIpafClicked());


    }


    class spTransfixanteChanged implements Spinner.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i == 0) {

                edittrajetoria.setVisibility(View.VISIBLE);
                Log.d("TAG", "SIM");
            } else {
                edittrajetoria.setVisibility(View.GONE);
                Log.d("TAG", "NÃO");
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    class btInserirFotoIpafClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {


            startActivity(new Intent(IpafActivity.this, CapturaActivity.class));
        }
    }

    class btInserirIpafClicked implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            labelipaf.setText("Inserir " + (pericia.getaObjetos().get(ExamesActivity.poslistview).getaIpafs().size() + 1) + "º Ipaf no objeto: ");

            ipaf = new Ipaf(editlocalizacao.getText().toString());
            ipaf.setAngulacao(editangulacao.getText().toString());
            ipaf.setEixomaior(editeixomaior.getText().toString());
            ipaf.setEixomenor(editeixomenor.getText().toString());
            ipaf.setOrigem(sporigem.getSelectedItem().toString());
            ipaf.setFormato(spformato.getSelectedItem().toString());
            ipaf.setOrientacao(sporientacao.getSelectedItem().toString());
            //ipaf.setAzimute(editazimute.getText().toString());

            ipaf.setTrajetoria(edittrajetoria.getText().toString());
            ipaf.setDistsolo(editdistsolo.getText().toString());
            ipaf.setOrigem(sporigem.getSelectedItem().toString());
            if (sptransfixante.getSelectedItem().toString() == "Sim") {
                ipaf.setTransfixed(true);
            } else {
                ipaf.setTransfixed(false);
            }
            pericia.getaObjetos().get(ExamesActivity.poslistview).getaIpafs().add(ipaf);
            int npafs = pericia.getaObjetos().get(ExamesActivity.poslistview).getaIpafs().size();
            Log.d("TAG","POslistview" + ExamesActivity.poslistview);
            labelipaf.setText("Inserir " + (npafs + 1) + "º Ipaf no objeto: ");
            if (pericia.getaObjetos().get(ExamesActivity.poslistview) instanceof Veiculo) {
                Log.d("TAG", "veicu" + ((Veiculo) pericia.getaObjetos().get(ExamesActivity.poslistview)).getPlaca());
            }
            Toast.makeText(IpafActivity.this, "Ipaf Inserido", Toast.LENGTH_SHORT).show();
            eraseFields();
        }
    }


    private void eraseFields() {

        editangulacao.setText("");
        edittrajetoria.setText("");
        editlocalizacao.setText("");
        // editazimute.setText("");
        editdistsolo.setText("");
        editeixomaior.setText("");
        editeixomenor.setText("");
        sporientacao.setSelection(0);
        spformato.setSelection(0);
        sporigem.setSelection(0);
        sptransfixante.setSelection(0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
