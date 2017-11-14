package coffeespace.com.br.periciasis.Activitys;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import coffeespace.com.br.periciasis.R;
import coffeespace.com.br.periciasis.Sistema.Ocorrencia;
import coffeespace.com.br.periciasis.Sistema.Pericia;
import coffeespace.com.br.periciasis.Sistema.PericiaArrombamento;
import coffeespace.com.br.periciasis.Sistema.PericiaDano;
import coffeespace.com.br.periciasis.Sistema.PericiaIpaf;
import coffeespace.com.br.periciasis.Sistema.PericiaIpafVeiculo;
import coffeespace.com.br.periciasis.Sistema.PericiaMorte;
import coffeespace.com.br.periciasis.Sistema.PericiaSuicidio;
import coffeespace.com.br.periciasis.Sistema.PericiaTransito;
import coffeespace.com.br.periciasis.Sistema.Perito;

import static coffeespace.com.br.periciasis.Activitys.MainActivity.ocorrencia;
import static coffeespace.com.br.periciasis.Activitys.MainActivity.pericia;
import static coffeespace.com.br.periciasis.Activitys.MainActivity.perito;

/**
 * Created by user on 01/11/2017.
 */

public class OcorrenciaActivity extends android.support.v4.app.Fragment {

    Button btiniciarpericia;
    EditText editro, editci, edithora, editdata, editlaudo, editendereco;
    Spinner spinnerdp, spinnertipopericia;
    private int dia, mes, ano, hora, minutos;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

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
        View view = inflater.inflate(R.layout.ocorrencia, container, false);


        btiniciarpericia = (Button) view.findViewById(R.id.btiniciarpericia);
        btiniciarpericia.setOnClickListener(new btIniciarPericiaClicked());
        btiniciarpericia = (Button) view.findViewById(R.id.btiniciarpericia);
        // btsair = (Button)view.findViewById(R.id.btcancelarocorrencia);
        // EditText's
        editro = (EditText) view.findViewById(R.id.editro);
        editci = (EditText) view.findViewById(R.id.editcontroleinterno);
        editdata = (EditText) view.findViewById(R.id.editdata);
        edithora = (EditText) view.findViewById(R.id.edithora);
        editlaudo = (EditText) view.findViewById(R.id.editlaudo);
        editendereco = (EditText) view.findViewById(R.id.editendereco);
        // Spinner's
        spinnerdp = (Spinner) view.findViewById(R.id.spinnerdp);
        inflaSpinnerDp();
        spinnertipopericia = (Spinner) view.findViewById(R.id.spinnertipopericia);
        inflaSpinnerTipoPericia();

        // set DATA
        editdata.setOnClickListener(new editDataClicked());
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //Log.d("AG",i +"/" + i1 +"/" +i2);
                month++;
                editdata.setText(Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year));

                dia = day;
                mes = month;
                ano = year;

            }
        };
        // END OF SET DATA

        // SET HORA
        edithora.setOnClickListener(new editHoraClicked());
        // END OF SET HORA


        btiniciarpericia.setOnClickListener(new btIniciarPericiaClicked());


        return view;

    }

    class btIniciarPericiaClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {

            if (isEmpty(editro) == true) {
                // mensagem de vazio
                Log.d("Deve ter ao menos um RO", " Vazio");
                Toast.makeText(act, "Para criar a ocorrência deve existir ao menos um RO ativo.", Toast.LENGTH_SHORT).show();
            } else {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(act);
                builder1.setMessage("Deseja iniciar nova pericia?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(act, "Boa sorte", Toast.LENGTH_SHORT).show();
                                iniciarNovaPericia();


                            }
                        });

                builder1.setNegativeButton(
                        "Não",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }

        }
    }

    class editDataClicked implements EditText.OnClickListener {

        @Override
        public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            dia = cal.get(Calendar.DAY_OF_MONTH);
            mes = cal.get(Calendar.MONTH);
            ano = cal.get(Calendar.YEAR);

            DatePickerDialog dialog = new DatePickerDialog(act,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    ano, mes, dia);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }

    class editHoraClicked implements EditText.OnClickListener {

        @Override
        public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            hora = cal.get(Calendar.HOUR_OF_DAY);
            minutos = cal.get(Calendar.MINUTE);


            TimePickerDialog timePickerDialog = new TimePickerDialog(act, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                    edithora.setText(hour + ":" + minute);
                    hora = hour;
                    minutos = minute;

                }
            }, hora, minutos, false);
            timePickerDialog.show();

        }
    }


    public void inflaSpinnerTipoPericia() {
        // final ArrayList<String> tipopericia = new ArrayList<String>(Arrays.asList("Ipaf em Local", "Modelo Ipaf em Veículo na DP", "Modelo Arrombamento","Modelo Acidente de Trânsito","Modelo Dano", "Modelo Suicídio", "Genérico"));
        ArrayAdapter<CharSequence> adaptertipopericia = ArrayAdapter.createFromResource(act, R.array.tipo_pericia, android.R.layout.simple_list_item_1);
        spinnertipopericia.setAdapter(adaptertipopericia);
    }

    public void inflaSpinnerDp() {
        ArrayAdapter<CharSequence> dpadapter = ArrayAdapter.createFromResource(act, R.array.delegacias_solicitantes, android.R.layout.simple_spinner_item);
        dpadapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerdp.setAdapter(dpadapter);
    }

    public void iniciarNovaPericia() {
        pericia.getaCadaveres().clear();    // limpo o array de cadaveres
        pericia.getaVeiculosExaminados().clear(); // limpo o array de veiculos
        ExamesActivity.listObjetos.clear();         // limpo a listview
        ExamesActivity.adapterlistobjetos.notifyDataSetChanged(); // notifico


        String ro = editro.getText().toString();
        String ci = editci.getText().toString();
        String tipopericia = spinnertipopericia.getSelectedItem().toString();
        String delegaciasolicitante = spinnerdp.getSelectedItem().toString();
        ocorrencia = new Ocorrencia(ro, ci, delegaciasolicitante);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(act); // help to save and get data
        String nomeperito = sp.getString(getString(R.string.nome_perito), "Pendente");
        String idperito = sp.getString(getString(R.string.id_perito), "Pendente");
        String lotacaoperito = sp.getString(getString(R.string.lot_perito), "Pendente");
        String orgaoperito = sp.getString(getString(R.string.org_perito), "Pendenre");
        String enderecolotacao = sp.getString("endereco_lotacao", "Pendente");

        act.ocorrencia.setEnderecodp(enderecolotacao);
        // sp.getString(

        perito = new Perito(nomeperito);
        perito.setLotacao(lotacaoperito);
        perito.setOrgao(orgaoperito);
        perito.setId(idperito);


        if (tipopericia == getString(R.string.tipo_local)) {
            Toast.makeText(act, "Laudo de Local", Toast.LENGTH_SHORT).show();
            pericia = new Pericia();
            pericia.setOcorrencia(ocorrencia);
            pericia.setPeritodesignado(perito);
        }
        if (tipopericia == getString(R.string.tipo_ipaf_dp)) {

            Toast.makeText(act, "Modelo de Ipaf em veículo iniciada", Toast.LENGTH_SHORT).show();
            pericia = new PericiaIpafVeiculo();
            pericia.setOcorrencia(ocorrencia);
            pericia.setPeritodesignado(perito);

        }
        if (tipopericia == getString(R.string.tipo_arrombamento)) {
            Toast.makeText(act, "Modelo de Arrombamento Iniciado", Toast.LENGTH_SHORT).show();
            pericia = new PericiaArrombamento();
            pericia.setOcorrencia(ocorrencia);
            pericia.setPeritodesignado(perito);

        }
        if (tipopericia == getString(R.string.tipo_transito)) {
            Toast.makeText(act, "Modelo de Acidente de Trânsito iniciado", Toast.LENGTH_SHORT).show();
            pericia = new PericiaTransito();
            pericia.setOcorrencia(ocorrencia);
            pericia.setPeritodesignado(perito);
        }
        if (tipopericia == getString(R.string.tipo_ipaf_local)) {
            Toast.makeText(act, "Nova Pericia Ipaf iniciada", Toast.LENGTH_SHORT).show();
            pericia = new PericiaIpaf();
            pericia.setOcorrencia(ocorrencia);
            pericia.setPeritodesignado(perito);

        }
        if (tipopericia == getString(R.string.tipo_dano)) {
            Toast.makeText(act, "Modelo de Dano iniciado", Toast.LENGTH_SHORT).show();
            pericia = new PericiaDano();
            pericia.setOcorrencia(ocorrencia);
            pericia.setPeritodesignado(perito);
        }
        if (tipopericia == getString(R.string.tipo_suicidio)) {
            Toast.makeText(act, "Laudo de Suicídio", Toast.LENGTH_SHORT).show();
            pericia = new PericiaSuicidio();
            pericia.setOcorrencia(ocorrencia);
            pericia.setPeritodesignado(perito);

        }
        if (tipopericia == getString(R.string.tipo_morte)) {
            Toast.makeText(act, "Laudo em local de Morte", Toast.LENGTH_SHORT).show();
            pericia = new PericiaMorte();
            pericia.setOcorrencia(ocorrencia);
            pericia.setPeritodesignado(perito);

        }

        // Cria a ocorrência
        isPendente(act.ocorrencia);

        ocorrencia.setHora(Integer.toString(hora));
        ocorrencia.setMinuto(Integer.toString(minutos));
        ocorrencia.setData(editdata.getText().toString());
        ocorrencia.setLaudo(editlaudo.getText().toString());
        ocorrencia.setEndereco(editendereco.getText().toString());
        ocorrencia.setTipolocal(spinnertipopericia.getSelectedItem().toString());
        ocorrencia.setDia(Integer.toString(dia));
        ocorrencia.setMes(Integer.toString(mes));
        ocorrencia.setAno(Integer.toString(ano));
        ocorrencia.setHora(Integer.toString(hora));

    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void isPendente(Ocorrencia oc) {
        if (isEmpty(edithora) == true) {
            oc.setHora("Pendente");
        }
        if (isEmpty(editdata) == true) {
            oc.setData("Pendente");
        }
        if (isEmpty(editci) == true) {
            oc.setCi("Pendente");
        }
        if (isEmpty(editendereco) == true) {
            oc.setEndereco("Pendente");
        }
        if (isEmpty(editlaudo) == true) {
            oc.setLaudo("Pendente");
        }

    }


}
