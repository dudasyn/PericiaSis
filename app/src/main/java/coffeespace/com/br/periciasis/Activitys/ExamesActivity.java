package coffeespace.com.br.periciasis.Activitys;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import coffeespace.com.br.periciasis.Google.CreateEmptyFileActivity;
import coffeespace.com.br.periciasis.R;
import coffeespace.com.br.periciasis.Sistema.Objetos.Cadaver;
import coffeespace.com.br.periciasis.Sistema.Objetos.Objeto;
import coffeespace.com.br.periciasis.Sistema.Objetos.Veiculo;

import static coffeespace.com.br.periciasis.Activitys.MainActivity.pericia;

/**
 * Created by user on 01/11/2017.
 */

public class ExamesActivity extends android.support.v4.app.Fragment {
    TextView lbmsgtopo;

    static int indexdoobjeto;
    MainActivity act;
    Button btnovoobjeto;
    Spinner spinnerobjetos;
    RadioButton rddano, rdipaf, rdviolacao,rdmorte;
    RadioGroup rdgroup;
    static ArrayList<String> listObjetos = new ArrayList<String>();
    static ArrayAdapter<String> adapterlistobjetos;
    static ArrayList<String> tipoobjeto;
    static int poslistview;
    Cadaver cadaver;



    public MainActivity getAct() {
        return act;
    }

    public void setAct(MainActivity act) {
        this.act = act;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exames, container, false);

        /*
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d("TAG", "TOUCH!!");
                return true;
            }
        });
        */
        // String tipolocal = act.getO
        lbmsgtopo = (TextView) view.findViewById(R.id.lbmsgtopo);

        lbmsgtopo.setText("CLIQUE PARA INSERIR VEÍCULOS,CADÁVER, etc...");
        rddano = (RadioButton) view.findViewById(R.id.rddano);
        rdipaf = (RadioButton) view.findViewById(R.id.rdipaf);
        rdmorte = (RadioButton) view.findViewById(R.id.rdmorte);
        rdviolacao = (RadioButton)view.findViewById(R.id.rdviolacao);
        rdgroup = (RadioGroup) view.findViewById(R.id.rdgroup);

        spinnerobjetos = (Spinner) view.findViewById(R.id.spinnerobjetos);
        ListView listview = (ListView) view.findViewById(R.id.lvobjetos);
        btnovoobjeto = (Button) view.findViewById(R.id.btnovoobjeto);
        tipoobjeto = new ArrayList<>(Arrays.asList("Veículo","Cadáver", "Objeto qualquer"));
        ArrayAdapter<String> adapterobjetos;
        adapterobjetos = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, tipoobjeto);
        spinnerobjetos.setAdapter(adapterobjetos);

        adapterlistobjetos = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, listObjetos);
        listview.setAdapter(adapterlistobjetos);

        btnovoobjeto.setOnClickListener(new btNovoObjetoClicked());
        listview.setOnItemClickListener(new listItemClicked());


        /* Just for test */

        return view;
    }


    class listItemClicked implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



            if (rdmorte.isChecked()) {
                Log.d("TAG", "morte impl");
                Toast.makeText(act, "Implementar", Toast.LENGTH_SHORT).show();
            }

            if (rddano.isChecked()) {

                if (pericia.getaObjetos().get(i) instanceof Cadaver) {
                    Toast.makeText(act, "Não é possível constatar dano em cadáver", Toast.LENGTH_SHORT).show();

                } else {
                    startActivity(new Intent(act, DanoActivity.class));
                }
            }
            if (rdipaf.isChecked()) {

                poslistview=i;
                Log.d("TAG2","poslistview " + poslistview);
                startActivity(new Intent(act, IpafActivity.class));
            }
            if (rdmorte.isChecked()) {
                Log.d("TAG", "morte impl");
                Toast.makeText(act, "Implementar", Toast.LENGTH_SHORT).show();
            }

            if (rdviolacao.isChecked()) {
                poslistview=i;
                Log.d("TAG", "morte impl");
                Toast.makeText(act, "Implementar", Toast.LENGTH_SHORT).show();

                // Toast.makeText(act, "Implementar", Toast.LENGTH_SHORT).show();
            }

        }
    }

    class btNovoObjetoClicked implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            String tipodeobjeto = spinnerobjetos.getSelectedItem().toString();

            Toast.makeText(act, tipodeobjeto, Toast.LENGTH_SHORT).show();

            switch (spinnerobjetos.getSelectedItem().toString()) {

                case "Veículo":
                    startActivity(new Intent(getActivity(), VeiculosActivity.class));
                    break;

                case "Cadáver":
                    cadaver = new Cadaver();
                    pericia.getaCadaveres().add(cadaver);
                    cadaver.setPos(pericia.getaObjetos().size());
                    pericia.getaObjetos().add(cadaver);
                    listObjetos.add("Cadáver (" + pericia.getaCadaveres().size() + ")");
                    ExamesActivity.adapterlistobjetos.notifyDataSetChanged();
                    break;
                case "Objeto qualquer":
                    startActivity(new Intent(getActivity(), ObjetosActivity.class));
                    break;
            }
        }
    }
/*
    public void setLabelTopo() {



        if (tipolocal == "Ipaf") {
            lbmsgtopo.setText("Toque para inserir um objeto e constatar IPAF");
        }
        if (tipolocal == "Dano") {
            lbmsgtopo.setText("Toque para inserir um objeto e constatar DANO");
        }
        if (tipolocal == "Arrombamento") {
            lbmsgtopo.setText("Toque para inserir um acesso e constatar Rompimento de Obstáculo");
        }
        if (tipolocal == "Acidente de Trânsito") {
            lbmsgtopo.setText("Toque para inserir veículos e constatar ACIDENTE DE TRÂNSITO");
        }
        if (tipolocal == "Homicídio") {
            lbmsgtopo.setText("Toque para inserir cadáveres e constatar HOMICÍDIOS");
        }
        if (tipolocal == "Suicídio") {
            lbmsgtopo.setText("Toque para inserir cadáveres e constatar HOMICÍDIOS");
        }
    }


*/

}
