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
    String formato, angulacao, distsolo, eixomaior, eixomenor, orientacao, origem, trajetoria, localizacao;
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

    public String geraConstatacaoVeiculo() {
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
            c = c + "<ol><li class=\"lialf\"><table border=1>";

            for (int j = 0; j < npafs; j++) {

                String transfixou = "";
                if (!getaVeiculosExaminados().get(i).getaIpafs().get(j).getTransfixed()) {
                    transfixou = "transfixante";
                } else {
                    transfixou = "não transfixante";
                }
                caracteristicas = "impacto " + transfixou + " de " +
                        formato + ", distando " + distsolo + "m do solo" +
                        ", apresenta medidas para o eixo  maior de " + eixomaior + " mm, e  " + eixomenor + "mm para o eixo menor, apresentando angulacao de " + angulacao + ", com a seguinte trajetória: " + trajetoria;


                formato = getaVeiculosExaminados().get(i).getaIpafs().get(j).getFormato();
                distsolo = getaVeiculosExaminados().get(i).getaIpafs().get(j).distsolo;
                angulacao = getaVeiculosExaminados().get(i).getaIpafs().get(j).angulacao;
                eixomaior = getaVeiculosExaminados().get(i).getaIpafs().get(j).eixomaior;
                eixomenor = getaVeiculosExaminados().get(i).getaIpafs().get(j).eixomenor;
                orientacao = getaVeiculosExaminados().get(i).getaIpafs().get(j).getOrientacao();
                localizacao = getaVeiculosExaminados().get(i).getaIpafs().get(j).getLocalizacao();
                origem = getaVeiculosExaminados().get(i).getaIpafs().get(j).getOrigem();
                trajetoria = getaVeiculosExaminados().get(i).getaIpafs().get(j).getTrajetoria();

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
                c = c + "IPAF " + (j + 1) + ": Impacto de " + formato + ", localizado na " + localizacao + ", de direção " + orientacao + ", origem " + origem + "  trajetoria, sendo identificada a seguinte trajetória: " + trajetoria + "<br>";

                c = c + "<tr><td rowspan=3><b>IPAF " + (j + 1) + "</b></td><td><b>LOCALIZAÇÃO</b></td><td><b>DIREÇÃO E SENTIDO</b></td><td><b>ORIGEM</b></td></tr>" +
                        "<td>" + localizacao + "</td>" +
                        "<td>" + orientacao + "</td>" +
                        "<td>" + origem + "</td>" +
                        "</tr><tr><td colspan=4>Informações adicionais: " + caracteristicas + "</td></tr></tr>";
            }
            c = c + "</table>" +

                    "</li></ol>";
        }
        for (String item : getaItemConstatacao()) {
            c = c + "<li> " + item + ";</li>";
        }
        c = c + "</ol>";
        return c;
    }

    public String geraConstatacaoObjeto() {
        String c = "";
        int nobjetos = getaVeiculosExaminados().size();


        for (int i = 0; i < getaObjetos().size(); i++) {

            if (!(getaObjetos().get(i) instanceof Veiculo)) {

                /*
                if (getaObjetos().get(i).getaIpafs().size() == 1) {
                    c = c + "<li>IPAF no objeto " + getaObjetos().get(i).getNome() + "</li>";
                } else {
                    c = c + "<li>Foram constatados os seguintes IPAF's:</li><ol>";
                    for (int j = 0; j < getaObjetos().get(i).getaIpafs().size(); j++) {
                        c = c + "<li>IPAF(" + i + ") no objeto " + getaObjetos().get(i).getNome() + "</li>";
                    }
                    c = c + "</ol>";
                }
                */
                int npafs = getaObjetos().get(i).getaIpafs().size();


                for (int j = 0; j < npafs; j++) {



                    String transfixou = "";
                    if (!getaObjetos().get(i).getaIpafs().get(j).getTransfixed()) {
                        transfixou = "transfixante";
                    } else {
                        transfixou = "não transfixante";
                    }

                    formato = getaObjetos().get(i).getaIpafs().get(j).getFormato();
                    distsolo = getaObjetos().get(i).getaIpafs().get(j).distsolo;
                    angulacao =getaObjetos().get(i).getaIpafs().get(j).angulacao;
                    eixomaior = getaObjetos().get(i).getaIpafs().get(j).eixomaior;
                    eixomenor = getaObjetos().get(i).getaIpafs().get(j).eixomenor;
                    orientacao = getaObjetos().get(i).getaIpafs().get(j).getOrientacao();
                    localizacao = getaObjetos().get(i).getaIpafs().get(j).getLocalizacao();
                    origem = getaObjetos().get(i).getaIpafs().get(j).getOrigem();
                    trajetoria = getaObjetos().get(i).getaIpafs().get(j).getTrajetoria();

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

                    if (getaObjetos().get(i).getaIpafs().size() == 1) {
                        c = c + "<li>IPAF(" + j + ")  no objeto " + getaObjetos().get(i).getNome() + "</li>";
                    } else {
                        c = c + "<li>Foram constatados os seguintes IPAF's:</li><ol>";
                            c = c + "<li>IPAF " + (j + 1) + ": Impacto de " + formato + ", localizado na " + localizacao + ", de direção " + orientacao + ", origem " + origem + "  trajetoria, sendo identificada a seguinte trajetória: " + trajetoria + "</li><br>";

                        c = c + "</ol>";
                    }

                }
                 c = c +  "</li>";
            }

        }


        /*
        for (String item : getaItemConstatacao()) {
            c = c + "<li> " + item + ";</li>";
        }*/
        return c;
}


    public String geraConstatacao() {

        String c = "";

        if (!empty(geraConstatacaoVeiculo())) {

            c = c + geraConstatacaoVeiculo();
        }

        if (!empty(geraConstatacaoObjeto())) {

            c = c + geraConstatacaoObjeto();
        }
        /*
        for (int i = 0; i < getaObjetos().size(); i++) {

            if (!(getaObjetos().get(i) instanceof Veiculo)) {

                for (int j = 0; j < getaObjetos().get(i).getaIpafs().size(); j++) {
                    c = c + "IPAF " + (i + 1) + geraConstatacaoObjeto();
                }
            }
        }
*/
        return c;

    }


    public boolean outrosCamposVazios() {
        if (empty(eixomaior) && empty(eixomenor) && empty(angulacao) && empty(distsolo)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean empty(final String s) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }


}