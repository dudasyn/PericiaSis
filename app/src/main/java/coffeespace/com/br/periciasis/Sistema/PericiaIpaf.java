package coffeespace.com.br.periciasis.Sistema;

import android.util.Log;

import java.util.ArrayList;

import coffeespace.com.br.periciasis.Sistema.Objetos.Objeto;
import coffeespace.com.br.periciasis.Sistema.Objetos.Onibus;
import coffeespace.com.br.periciasis.Sistema.Objetos.Veiculo;

/**
 * Created by user on 20/10/2017.
 */

public class PericiaIpaf extends Pericia {
    String formato, angulacao, distsolo, eixomaior, eixomenor;
    String nomedoperito;
    String iddoperito;

    public PericiaIpaf() {
    }

    @Override
    public ArrayList<Veiculo> getaVeiculosExaminados() {
        return super.getaVeiculosExaminados();
    }

    @Override
    public void setaVeiculosExaminados(ArrayList<Veiculo> aVeiculosExaminados) {
        super.setaVeiculosExaminados(aVeiculosExaminados);
    }


    public String geraTitulo() {
        return "<b>LAUDO DE EXAME EM LOCAL DE IMPACTO DE PROJETIL DE ARMA DE FOGO</b>";
    }

    public String geraConstatacao() {

        String c = "";
        int nveiculos = getaVeiculosExaminados().size();

        c = "<ol>";
        for (int i = 0; i < nveiculos; i++) {
            String caracteristicas = "";
            int npafs = getaVeiculosExaminados().get(i).getaIpafs().size();
            if (nveiculos == 1) {

                if (npafs == 1) {
                    c = c + "<li>Das constatações no Veículo: foi constatado um impacto de projétil de arma de fogo com as seguintes características:</li><br>";
                } else {
                    if (npafs > 1) {
                        c = c + "<li>Das constatações no Veículo: foram constatados impactos de projétis de arma de fogo com as seguintes características:</li><br>";
                    } else {
                        c = c + "<li>Das constatações no Veículo:: <b>(IPAF NÃO INSERIDO)</b>:</li><br>";
                    }
                }


            } else {
                if (npafs == 1) {
                    c = c + "<li>Das constatações em V" + (i + 1) + "(" + getaVeiculosExaminados().get(i).getPlaca() + "): foi constatado um impacto de projétil de arma de fogo com as seguintes características:</li><br>";
                } else {
                    if (npafs > 1) {
                        c = c + "<li>Das constatações em V" + (i + 1) + "(" + getaVeiculosExaminados().get(i).getPlaca() + "): foram constatados impactos de projétis de arma de fogo com as seguintes características:</li><br>";

                    } else {
                        c = c + "<li>Das constatações em V" + (i + 1) + "(" + getaVeiculosExaminados().get(i).getPlaca() + "):<b> (IPAF NÃO INSERIDO)</b></li><br>";
                    }
                }

            }
            c = c + "<ol><li class=\"lialf\"><table border=1><b>Tabela com informação acerca dos IPAF's:</b>";

            for (int j = 0; j < npafs; j++) {

                String transfixou = "";
                if (getaVeiculosExaminados().get(i).getaIpafs().get(j).getTransfixed()) {
                    transfixou = "transfixante";
                } else {
                    transfixou = "não transfixante";
                }

                formato = getaVeiculosExaminados().get(i).getaIpafs().get(j).formato;
                distsolo = getaVeiculosExaminados().get(i).getaIpafs().get(j).distsolo;
                angulacao = getaVeiculosExaminados().get(i).getaIpafs().get(j).angulacao;
                eixomaior = getaVeiculosExaminados().get(i).getaIpafs().get(j).eixomaior;
                eixomenor = getaVeiculosExaminados().get(i).getaIpafs().get(j).eixomenor;
                if (empty(formato)) {
                    formato = "[NÃO INSERIDO]";
                }
                if (empty(distsolo)) {
                    distsolo = "[NÃO INSERIDO]";
                }
                if (empty(angulacao)) {
                    angulacao = "[NÃO INSERIDO]";
                }
                if (empty(eixomaior)) {
                    eixomaior = "[NÃO INSERIDO]";
                }
                if (empty(eixomenor)) {
                    eixomenor = "[NÃO INSERIDO]";
                }
                caracteristicas = "impacto " + transfixou + " de " +
                        formato + ", distando " + distsolo + "m do solo" +
                        ", apresenta medidas para o eixo  maior de " + eixomaior + " mm, e  " + eixomenor + "mm para o eixo menor, apresentando angulacao de " + angulacao;

                c = c + "<tr><td rowspan=3><b>IPAF " + (j + 1) + "</b></td><td><b>LOCALIZAÇÃO</b></td><td><b>DIREÇÃO E SENTIDO</b></td><td><b>ORIGEM</b></td><td><b>TRAJETÓRIA</b></td></tr>" +
                        "<td>" + getaVeiculosExaminados().get(i).getaIpafs().get(j).getLocalizacao() + "</td>" +
                        "<td>" + getaVeiculosExaminados().get(i).getaIpafs().get(j).getOrientacao() + "</td>" +
                        "<td>" + getaVeiculosExaminados().get(i).getaIpafs().get(j).getOrigem() + "</td>" +
                        "<td>" + getaVeiculosExaminados().get(i).getaIpafs().get(j).getTrajetoria() + "</td>" +
                        "</tr><tr><td colspan=4>Informações adicionais: " + caracteristicas + "</td></tr></tr>";
            }
            c = c + "</table></li></ol>";
        }
        for (String item : getaItemConstatacao()) {
            c = c + "<li> " + item + ";</li>";
        }
        c = c + "</ol>";
        return c;
    }

    public boolean outrosCamposVazios() {
        if (empty(eixomaior) && empty(eixomenor) && empty(angulacao) && empty(distsolo)) {
            return true;
        } else {
            return false;
        }
    }
    /*
    public String geraTitulo() {
        return "<b>LAUDO DE EXAME EM LOCAL DE IMPACTO DE PROJETIL DE ARMA DE FOGO</b>";
    }

    public String geraConstatacao() {

        String c = "Foram constatados impactos de projétil de arma de fogo com as seguintes características:<br> ";

        int index = this.getaVeiculosExaminados().size();
        Log.d("TAG", Integer.toString(index));

        c = c + "<ol>";
        for (int i = 0; i < index; i++) {

            String placa = "";

            int npafs = getaVeiculosExaminados().get(i).getaIpafs().size();
            if (npafs == 0) {
                c = c + "<li><b>CONSTATAÇÃO DE IPAF PENDENTE</li>";
            } else {
                placa = ((getaVeiculosExaminados().get(i)).getPlaca());

                if (getaVeiculosExaminados().size() > 1) {
                    c = c + "<li><b>Dos impactos no Veiculo " + (i + 1) + " (" + placa + "):</b></li>";
                }else{
                    c = c + "<li><b>Dos impactos no Veiculo (" + placa + "):</b></li>";
                }
            }

            c = c + "<ol>";
            for (int j = 0; j < npafs; j++) {
                String transfixou = "";
                if (getaVeiculosExaminados().get(i).getaIpafs().get(j).getTransfixed()) {
                    transfixou = "transfixante";
                } else {
                    transfixou = "não transfixante";
                }
                c = c + "<li>Do " + (j + 1) + "º Impacto constatado: impacto " + transfixou + " de " + getaVeiculosExaminados().get(i).getaIpafs().get(j).getFormato() + " com as seguintes características:</li>";
                c = c + "<ol>";
                c = c + "<li class=\"lialf\">Localização: " + getaVeiculosExaminados().get(i).getaIpafs().get(j).getLocalizacao() + ";</li>" +
                        "<li class=\"lialf\">Direção e sentido: " + getaVeiculosExaminados().get(i).getaIpafs().get(j).getTrajetoria() + ";</li>" +
                        "<li class=\"lialf\">Origem: " + getaVeiculosExaminados().get(i).getaIpafs().get(j).getOrigem() + ";</li>";
                if (!empty(getaVeiculosExaminados().get(i).getaIpafs().get(j).getAngulacao())) {
                    c = c + "<li class=\"lialf\">Angulação: " + getaVeiculosExaminados().get(i).getaIpafs().get(j).getAngulacao() + "</li>";
                }
                if (!empty(getaVeiculosExaminados().get(i).getaIpafs().get(j).getDistsolo())) {
                    c = c + "<li class=\"lialf\">Distância do solo: " + getaVeiculosExaminados().get(i).getaIpafs().get(j).getDistsolo() + "</li>";
                }
                if (!empty(getaVeiculosExaminados().get(i).getaIpafs().get(j).getEixomaior())) {
                    c = c + "<li class=\"lialf\">Eixo Maior: " + getaVeiculosExaminados().get(i).getaIpafs().get(j).getEixomaior() + "</li>";
                }
                if (!empty(getaVeiculosExaminados().get(i).getaIpafs().get(j).getEixomenor())) {
                    c = c + "<li class=\"lialf\">Eixo Menor: " + getaVeiculosExaminados().get(i).getaIpafs().get(j).getEixomenor() + "</li>";
                }
                if (!empty(getaVeiculosExaminados().get(i).getaIpafs().get(j).getObservacoes())) {
                    c = c + "<li class=\"lialf\">Observações: " + getaVeiculosExaminados().get(i).getaIpafs().get(j).getObservacoes() + "</li>";
                }
                /*
                if (!empty(getaObjetosImpactados().get(i).getaIpafs().get(j).getAzimute())) {
                    c = c + "<li>Azimute: " + getaObjetosImpactados().get(i).getaIpafs().get(j).getObservacoes() + "</li>";
                }

                c = c + "</ol>";

            }
            c = c + "</ol>";
        }
        c = c + "</ol>";

        return c;
    }

*/

    public static boolean empty(final String s) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }


}