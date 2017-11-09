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
import coffeespace.com.br.periciasis.Sistema.Objetos.Onibus;
import coffeespace.com.br.periciasis.Sistema.Objetos.Veiculo;

import static coffeespace.com.br.periciasis.Activitys.MainActivity.pericia;

public class VeiculosActivity extends AppCompatActivity {

    Button btinserirveiculos,btinserirfotoveiculos; //btveiculosvoltar,
    Spinner spinnertipoveiculo;
    EditText editplaca, editmarca, editmodelo, editestado, editmunicipio, editcor, editempresa, editnordem;
    TextView labelveiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculos);

        labelveiculos = (TextView) findViewById(R.id.labelveiculos);
        String label;
        labelveiculos.setText("Inserir veiculo " + Integer.toString((MainActivity.pericia.getaVeiculosExaminados().size()) + 1));

        btinserirfotoveiculos = (Button)findViewById(R.id.btinserirfotoveiculos);
        btinserirveiculos = (Button) findViewById(R.id.btinserirveiculos);
        spinnertipoveiculo = (Spinner) findViewById(R.id.spinnertipoveiculos);
        btinserirveiculos.setOnClickListener(new btInserirVeiculosClicked());

        ArrayList<String> tipodeveiculos = new ArrayList<String>(Arrays.asList("Automóvel", "Motocicleta", "Ônibus", "Caminhão", "Caminhonete", "Caminhoneta", "Jet Ski"));
        ArrayAdapter<String> adaptertipodeveiculos = new ArrayAdapter<String>(VeiculosActivity.this, android.R.layout.simple_list_item_1, tipodeveiculos);
        spinnertipoveiculo.setAdapter(adaptertipodeveiculos);

        editnordem = (EditText) findViewById(R.id.editnordem);
        editempresa = (EditText) findViewById(R.id.editempresa);
btinserirfotoveiculos.setOnClickListener(new btInserirFotoVeiculosClicked());
        spinnertipoveiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    editnordem.setVisibility(View.VISIBLE);
                    editempresa.setVisibility(View.VISIBLE);
                } else {
                    editempresa.setVisibility(View.INVISIBLE);
                    editnordem.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    class btInserirFotoVeiculosClicked implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            startActivity(new Intent(VeiculosActivity.this, CapturaActivity.class));
        }
    }
    private void addFieldsInVeiculo(Veiculo v) {

        if (isEmpty(editmarca) == true) {
            v.setMarca("[PENDENTE]");
        } else {
            v.setMarca(editmarca.getText().toString());
        }

        if (isEmpty(editmodelo) == true) {
            v.setModelo("[PENDENTE]");
        } else {
            v.setModelo(editmodelo.getText().toString());
        }

        if (isEmpty(editcor) == true) {
            v.setCor("[PENDENTE]");
        } else {
            v.setCor(editcor.getText().toString());
        }

        if (isEmpty(editestado) == true) {
            v.setEstado("[PENDENTE]");
        } else {
            v.setEstado(editestado.getText().toString());
        }

        if (isEmpty(editmunicipio) == true) {
            v.setMunicipio("[PENDENTE]");
        } else {
            v.setMunicipio(editmunicipio.getText().toString());
        }

    }

    private void addFieldsInOnibus(Onibus b) {
        addFieldsInVeiculo(b);
        b.setNordem(editnordem.getText().toString());
        b.setEmpresa(editempresa.getText().toString());

    }
    private void addFieldsInCaminhao(Caminhao c) {
        addFieldsInVeiculo(c);
        c.setNordem(editnordem.getText().toString());
        c.setEmpresa(editempresa.getText().toString());

    }

    private void eraseFields() {

        editplaca.setText("");
        editmarca.setText("");
        editmodelo.setText("");
        editestado.setText("");
        editmunicipio.setText("");
        editcor.setText("");
        editnordem.setText("");
        editempresa.setText("");

    }

    private void addFieldsInAutomovel(Automovel a) {
        addFieldsInVeiculo(a);

    }

    class btInserirVeiculosClicked implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            editplaca = (EditText) findViewById(R.id.editplaca);
            editmarca = (EditText) findViewById(R.id.editmarca);
            editmodelo = (EditText) findViewById(R.id.editmodelo);
            editestado = (EditText) findViewById(R.id.editestado);
            editmunicipio = (EditText) findViewById(R.id.editmunicipio);
            editcor = (EditText) findViewById(R.id.editcor);


            if (isEmpty(editplaca) == true) {
                // mensagem de vazio
                Toast.makeText(VeiculosActivity.this, "Placa Vazia", Toast.LENGTH_SHORT).show();

            } else {

                String placa = editplaca.getText().toString();
                spinnertipoveiculo.getSelectedItem();
                int index = 0;
                switch (spinnertipoveiculo.getSelectedItem().toString()) {

                    case "Automóvel":
                        Automovel a = new Automovel(placa,"automóvel");
                        index = pericia.getaVeiculosExaminados().size();
                        pericia.getaVeiculosExaminados().add(a);
                        pericia.getaObjetos().add(a);
                        Log.d ("TAG","listOsize" + ExamesActivity.listObjetos.size());
                        addFieldsInAutomovel((Automovel) pericia.getaVeiculosExaminados().get(index));
                        Toast.makeText(getApplicationContext(), "Novo Automóvel inserido", Toast.LENGTH_SHORT).show();
                        break;
                    case "Ônibus":
                        Onibus o = new Onibus(placa,"ônibus");
                        index = pericia.getaVeiculosExaminados().size();
                        pericia.getaVeiculosExaminados().add(o);
                        pericia.getaObjetos().add(o);
                        addFieldsInOnibus((Onibus) pericia.getaVeiculosExaminados().get(index));
                        pericia.getaVeiculosExaminados().get(index).setPos(ExamesActivity.listObjetos.size());
                        Toast.makeText(getApplicationContext(), "Novo Ônibus inserido", Toast.LENGTH_SHORT).show();

                        break;
                    case "Caminhão":
                        Caminhao c = new Caminhao(placa,"caminhão");
                        index = pericia.getaVeiculosExaminados().size();
                        pericia.getaVeiculosExaminados().add(c);
                        pericia.getaObjetos().add(c);
                        addFieldsInCaminhao((Caminhao) pericia.getaVeiculosExaminados().get(index));
                        pericia.getaVeiculosExaminados().get(index).setPos(ExamesActivity.listObjetos.size());
                        Toast.makeText(getApplicationContext(), "Novo Caminhão inserido", Toast.LENGTH_SHORT).show();
                        break;
                    case "Motocicleta":
                        Caminhao m = new Caminhao(placa,"motocicleta");
                        index = pericia.getaVeiculosExaminados().size();
                        pericia.getaVeiculosExaminados().add(m);
                        pericia.getaObjetos().add(m);
                        addFieldsInCaminhao((Caminhao) pericia.getaVeiculosExaminados().get(index));
                        pericia.getaVeiculosExaminados().get(index).setPos(ExamesActivity.listObjetos.size());
                        Toast.makeText(getApplicationContext(), "Nova Motocicleta inserida", Toast.LENGTH_SHORT).show();
                        break;

                }


                labelveiculos.setText("Inserir veiculo " + (pericia.getaVeiculosExaminados().size() + 1));

                ExamesActivity.listObjetos.add(placa + " (" + spinnertipoveiculo.getSelectedItem() + ")");
                ExamesActivity.adapterlistobjetos.notifyDataSetChanged();
                eraseFields();
            }
        } // EOnclclick

    }


}




