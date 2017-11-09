package coffeespace.com.br.periciasis.Activitys;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import coffeespace.com.br.periciasis.Google.CreateEmptyFileActivity;
import coffeespace.com.br.periciasis.R;

import static coffeespace.com.br.periciasis.Activitys.MainActivity.ocorrencia;
import static coffeespace.com.br.periciasis.Activitys.MainActivity.pericia;

/**
 * Created by user on 01/11/2017.
 */

public class DadosActivity extends android.support.v4.app.Fragment {
    MainActivity act;
    Button btdinamica, btconstatarlocal, btconclusao, btinserirdados, btoutroselementos, btconstatacao;
    RadioButton rdyes, rdno;
    EditText editdados;
    String flag;
    ListView lvitensconstatacao, lvitensoe;
    ArrayAdapter adapteritensconstatacao;
    ArrayAdapter adapteritensoe;

    public MainActivity getAct() {
        return act;
    }

    public void setAct(MainActivity act) {
        this.act = act;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dados, container, false);
        lvitensconstatacao = (ListView) view.findViewById(R.id.lvitensconstatacao);
        lvitensoe = (ListView)view.findViewById(R.id.lvitensoe);

        adapteritensconstatacao = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, pericia.getaItemConstatacao());
        adapteritensoe = new ArrayAdapter<String>(act,android.R.layout.simple_list_item_1,pericia.getaItemOutrosElementos());

        btconstatarlocal = (Button) view.findViewById(R.id.btconstatarlocal);
        btdinamica = (Button) view.findViewById(R.id.btdinamica);
        btconclusao = (Button) view.findViewById(R.id.btconclusao);
        btoutroselementos = (Button) view.findViewById(R.id.btoutroselementos);
        btinserirdados = (Button) view.findViewById(R.id.btinserirdados);
        btconstatacao = (Button) view.findViewById(R.id.btconstatacao);

        editdados = (EditText) view.findViewById(R.id.editinserirdados);
        rdyes = (RadioButton) view.findViewById(R.id.rdyes);
        rdno = (RadioButton) view.findViewById(R.id.rdno);

        btconstatarlocal.setOnClickListener(new btLocalClicked());
        btdinamica.setOnClickListener(new btDinamicaClicked());
        btconclusao.setOnClickListener(new btConclusaoClicked());


        btoutroselementos.setOnClickListener(new btOutrosElementosClicked());
        btinserirdados.setOnClickListener(new btInserirDadosClicked());
        btconstatacao.setOnClickListener(new btConstatacaoClicked());
        rdyes.setOnClickListener(new rdYesClicked());
        rdno.setOnClickListener(new rdNoClicked());
        return view;
    }



    class rdYesClicked implements RadioButton.OnClickListener {
        @Override
        public void onClick(View view) {
            editdados.setVisibility(View.INVISIBLE);
            btinserirdados.setVisibility(View.INVISIBLE);
        }
    }

    class rdNoClicked implements RadioButton.OnClickListener {
        @Override
        public void onClick(View view) {
            editdados.setVisibility(View.VISIBLE);
            btinserirdados.setVisibility(View.VISIBLE);
        }
    }

    class btInserirDadosClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            String str = editdados.getText().toString();

            switch (flag) {
                case "local":
                    Toast.makeText(act, "Local inserido", Toast.LENGTH_SHORT).show();
                    pericia.setLocal(str);
                    break;

                case "dinamica":
                    Toast.makeText(act, "Dinâmica inserida", Toast.LENGTH_SHORT).show();
                    pericia.setDinamica(str);
                    break;
                case "conclusao":
                    Toast.makeText(act, "Conclusão inserida", Toast.LENGTH_SHORT).show();
                    pericia.setConclusao(str);
                    break;
                case "oe":
                    Toast.makeText(act, "Informações Adicionais inseridas", Toast.LENGTH_SHORT).show();
                    lvitensoe.setAdapter(adapteritensoe);
                    pericia.setOutroselementos(str);
                    adapteritensoe.notifyDataSetChanged();
                    editdados.setText("");
                    break;
                case "constatacao":
                    Toast.makeText(act, "Item constatado", Toast.LENGTH_SHORT).show();
                    lvitensconstatacao.setAdapter(adapteritensconstatacao);
                    pericia.setConstatacao(str);
                    adapteritensconstatacao.notifyDataSetChanged();
                    editdados.setText("");
                    break;
            }


        }
    }

    class btLocalClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            flag = "local";
            lvitensconstatacao.setVisibility(View.GONE);
            lvitensoe.setVisibility(View.GONE);

            if (rdyes.isChecked()) {
                Toast.makeText(act, "Modo dinâmico não implementado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(act, "Constatação Livre (inserir)", Toast.LENGTH_SHORT).show();
                String marrombamento, mtransito, mipaf, mipafnadp, mdano, mlocal;

                marrombamento = "Imóvel residencial/comercial/misto situado na Rua das pacas, número 16, no Bairro de Teresópolis. O local é dotado de x pavimentos e possui quarto, sala, cozinha, banheiro, possuindo acesso único voltado para via xxx. Ofereceu interesse à Perícia o acesso principal do imóvel, local onde concentraram-se os exames.";
                mtransito = "Trata-se da Avenida das Américas uma via (reta, em aclive ou declive), com piso (de terra batida, capeado a concreto asfáltico, capeado a blocos de paralelepípedos), (não) admitindo tráfego de veículos em regime de mão dupla/única, (possuindo ao centro pista destinada ao tráfego do BRT (Bus Rapid Transit) (des) provida de iluminação pública, apresentando-se como via de natureza XXX(residencial, comercial, mista). Ofereceu interesse à perícia o trecho da via com sentido xx, local conde concentraram-se os exames";

                mlocal = "Via pública: identifica-se o local como um trecho (reto, aclive ou declive), piso (de terra batida, capeado a concreto asfáltico, capeado a blocos de paralelepípedos), (não) admitindo tráfego de veículos,  (des) provida de iluminação pública, apresentando-se como via de natureza XXX(residencial, comercial, mista). \n" +
                        "\n" +
                        "Área imediata: \n";
                mipaf = "Via pública: identifica-se o local como um trecho (reto, aclive ou declive), piso (de terra batida, capeado a concreto asfáltico, capeado a blocos de paralelepípedos), (não) admitindo tráfego de veículos,  (des) provida de iluminação pública, apresentando-se como via de natureza XXX(residencial, comercial, mista). \n" +
                        "\n" +
                        "Área imediata: \n";
                mdano = "Via pública: identifica-se o local como um trecho (reto, aclive ou declive), piso (de terra batida, capeado a concreto asfáltico, capeado a blocos de paralelepípedos), (não) admitindo tráfego de veículos,  (des) provida de iluminação pública, apresentando-se como via de natureza XXX(residencial, comercial, mista). \n" +
                        "\n" +
                        "Área imediata: \n";
                mipafnadp = ("Os exames foram acompanhados no pátio da xx Delegacia de Polícia, situada no endereço xxx");

                if (ocorrencia.getTipolocal() == getString(R.string.tipo_transito)) {
                    editdados.setText(mtransito);

                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_arrombamento)) {
                    editdados.setText(marrombamento);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_ipaf_local)) {
                    editdados.setText(mipaf);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_ipaf_dp)) {
                    editdados.setText(mipafnadp);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_dano)) {
                    editdados.setText(mdano);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_local)) {
                    editdados.setText(mlocal);
                }

            }
        }
    }
    class btConstatacaoClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {

            lvitensconstatacao.setVisibility(View.VISIBLE);
            lvitensoe.setVisibility(View.GONE);
            flag="constatacao";

            if (rdyes.isChecked()) {
                Toast.makeText(act, "Modo dinâmico não implementado", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(act, "Constate um fato", Toast.LENGTH_SHORT).show();
                editdados.setText("");
                editdados.setHint("DAS CONSTATAÇÕES: Entre com as constatações uma a uma e confira no laudo");
            }

        }
    }
    class btOutrosElementosClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            lvitensconstatacao.setVisibility(View.GONE);
            lvitensoe.setVisibility(View.VISIBLE);
            flag = "oe";

            if (rdyes.isChecked()) {
                Toast.makeText(act, "Modo dinâmico não implementado", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(act, "Constate informações adicionais", Toast.LENGTH_SHORT).show();
                editdados.setText("");
                editdados.setHint("DE OUTROS ELEMENTOS: Entre com as informações complementares uma a uma e confira no laudo");
            }
        }
    }

    class btDinamicaClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            flag = "dinamica";
            lvitensconstatacao.setVisibility(View.GONE);
            lvitensoe.setVisibility(View.GONE);

            if (rdyes.isChecked()) {
                Toast.makeText(act, "Modo dinâmico não implementado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(act, "Dinâmica Livre (inserir)", Toast.LENGTH_SHORT).show();
                String marrombamento, macidentetransito, mipafnadp, mipaf, mdano, mlocal;

                marrombamento = "(MODELO) Pelos elementos materiais coligidos no local é o perito levado a inferir a seguinte dinâmica: o agente da ação por meio de objeto contundente/cortante;pérfuro-contundente/pérfuro-cortante, sistema de alavanca e ação de força física] violou o acesso mencionado e adentrou ao imóvel, conforme processo descrito no corpo do laudo. o agente da ação por meio de objeto contundente/cortante;pérfuro-contundente/pérfuro-cortante, sistema de alavanca e ação de força física] violou o acesso mencionado e adentrou ao imóvel, conforme processo descrito no corpo do laudo.";
                macidentetransito = "(MODELO) Pelos elementos materiais coligidos no local é o perito levado a inferir a seguinte dinâmica:";
                mipaf = "(MODELO) Pelos elementos materiais coligidos no local é o perito levado a inferir a seguinte dinâmica: o agente da ação efetuou disparos, contra o veiculo, objeto, etc..";
                mipafnadp = "(MODELO) Ante a falta de elementos técnicos geradores de convicção, e a característica do exame não ser no local do crime, deixa o Perito de determinar uma completa dinâmica do evento.";
                mdano = "(MODELO) Pelos elementos materiais coligidos no local é o perito levado a inferir a seguinte dinâmica: o agente da ação por meio de objeto contundente/cortante;pérfuro-contundente/pérfuro-cortante, sistema de alavanca e ação de força física] realizou as avarias descritas conforme processo descrito no corpo do laudo.";
                mlocal = "(MODELO) Pelos elementos materiais coligidos no local é o perito levado a inferir a seguinte dinâmica:";

                if (ocorrencia.getTipolocal() == getString(R.string.tipo_transito)) {
                    editdados.setText(macidentetransito);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_arrombamento)) {
                    editdados.setText(marrombamento);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_ipaf_local)) {
                    editdados.setText(mipaf);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_ipaf_dp)) {
                    editdados.setText(mipafnadp);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_dano)) {
                    editdados.setText(mdano);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_local)) {
                    editdados.setText(mlocal);
                }

            }
        }

    }

    class btConclusaoClicked implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            flag = "conclusao";
            lvitensconstatacao.setVisibility(View.GONE);
            lvitensoe.setVisibility(View.GONE);

            if (rdyes.isChecked()) {
                Toast.makeText(act, "Modo dinâmico não implementado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(act, "Constatação Livre (inserir)", Toast.LENGTH_SHORT).show();
                String marrombamento, macidentetransito, mipafnadp, mipaf, mdano, mlocal, msuicidio,mgene;

                marrombamento = "(MODELO) Ante o exposto [Destaca o signatário não ter sido verificado\n" +
                        "nos exames vestígios de abertura forçada ou arrombamento nos acessos à área interna do imóvel], conclui o perito ter ocorrido no local objeto do exame um rompimento de \n" +
                        "obstáculo mediante o emprego de instrumento [contundente/cortante/pérfuro-contundente/pérfuro-cortante], [sistema de alavanca e a ação de força física],\n" +
                        "[desalinhos típicos da busca indiscriminada de valores]. Pelos elementos encontrados fica caracterizado que o agente acessou o estabelecimento periciado, mediante\n" +
                        "[escalada/destreza (tendo em vista a,b,c) através do imóvel vizinho/do muro que delimita a área externa da interna]. Não foi possível identificar a hora do evento bem\n" +
                        "como o número de agentes envolvidos devido a falta de elementos materiais geradores de convicção. Nada mais havendo a lavrar o acrescentar, encerra-se o \n" +
                        "presente laudo que segue assinado pelo perito criminal designado. \n";
                macidentetransito = "(MODELO) Diante do exposto conclui o Perito que houve no local objeto dos exames um acidente de trânsito [com vítima fatal] cuja causa foi o [desvio direcional implementado/ fato do condutor do veículo x trafegar na contra mão de direção / a não observância das condições de tráfego por parte do condutor do veículo / a não observância das condições de tráfego na via face à sinalização da via / a postergação da sinalização semafórica por parte de um dos condutores / a não observância das condições de tráfego a frente resultando na colisão. Nada mais havendo a lavrar, encerra-se o presente laudo que segue assinado pelo perito criminal designado.";
                mipaf = "(MODELO) Ante o exposto, de acordo com os exames técnicos procedidos conclui o Perito Criminal, designado que no local em causa e objeto de exame ocorreram impactos de projetis de arma de fogo conforme processo descrito na constatação e na dinâmica do evento. Deixa a perícia de indicar o número de agentes devido à falta de elementos materiais geradores de convicção. Nada mais havendo a examinar ou lavrar, é encerrado o presente laudo que é assinado pelo Perito Criminal designado.";
                mipafnadp = "(MODELO) Com base nos elementos coligidos, acima relatados e devidamente analisados, conclui" +
                        "o Perito que o veículo objeto de exame foi alvejado por projetis disparados por arma(s) de fogo," +
                        "resultado da produção de ao menos dois (dois) disparos efetuados a distância, destacando que" +
                        "a quantidade de disparos e as posições dos IPAFs convergem para uma ação intencional de" +
                        "disparos de arma de fogo contra a célula de sobrevivência do veículo.";

                mdano = "(MODELO) Ante o exposto com base no elementos coligidos, acima relatados e devidamente analisados, conclui o Perito que houve dano produzido por ação (tipo de ação) conforme processo descrito no corpo do laudo. Deixa-se de determinar a hora do evento bem como o número de agente envolvidos devido à falta de elementos materiais geradores de convicção. Nada mais havendo a lavrar encerra-se o presente laudo que segue assinado pelo Perito Criminal designado.";
                mlocal = "(MODELO) Ante o exposto, limita-se o perito às constatações descritas no corpo do laudo, ficando a investigação dos fatos a cargo da Autoridade Policial competente. Nada mais havanedo a lavrar ou acrescentar, encerra-se o presente laudo que segue assinado pelo Perito Criminal designado.";
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_transito)) {
                    editdados.setText(macidentetransito);

                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_arrombamento)) {
                    editdados.setText(marrombamento);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_ipaf_local)) {
                    editdados.setText(mipaf);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_ipaf_dp)) {
                    editdados.setText(mipafnadp);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_dano)) {
                    editdados.setText(mdano);
                }
                if (ocorrencia.getTipolocal() == getString(R.string.tipo_local)) {
                    editdados.setText(mlocal);
                }

            }
        }
    }


}
