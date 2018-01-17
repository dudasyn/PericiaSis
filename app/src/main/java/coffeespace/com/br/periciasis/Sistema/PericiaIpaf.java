package coffeespace.com.br.periciasis.Sistema;

import android.util.Log;

import java.util.ArrayList;

import coffeespace.com.br.periciasis.Sistema.Objetos.Automovel;
import coffeespace.com.br.periciasis.Sistema.Objetos.Cadaver;
import coffeespace.com.br.periciasis.Sistema.Objetos.Caminhao;
import coffeespace.com.br.periciasis.Sistema.Objetos.Motocicleta;
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

    public String geraTabela() {
        String c = "";
        int nobjetos = getaObjetos().size();

        c = c + "<ol>";
        for (int i = 0; i < nobjetos; i++) {
            String caracteristicas = "";
            int npafs = getaObjetos().get(i).getaIpafs().size();


            c = c + "<table border=1>";
            for (int j = 0; j < npafs; j++) {

                formato = getaObjetos().get(i).getaIpafs().get(j).getFormato();
                distsolo = getaObjetos().get(i).getaIpafs().get(j).distsolo;
                angulacao = getaObjetos().get(i).getaIpafs().get(j).angulacao;
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

                String transfixou = "";
                if (!getaObjetos().get(i).getaIpafs().get(j).getTransfixed()) {
                    transfixou = "transfixante";
                } else {
                    transfixou = "não transfixante";
                }
                caracteristicas = "impacto " + transfixou + " de " +
                        formato + ", distando " + distsolo + "m do solo" +
                        ", apresenta medidas para o eixo  maior de " + eixomaior + " mm, e  " + eixomenor + "mm para o eixo menor, apresentando angulacao de " + angulacao + ", com a seguinte trajetória: " + trajetoria;


                c = c + "<tr><td rowspan=3><b>IPAF " + (j + 1) + "</b></td><td><b>Objeto Examinado</b></td><td><b>LOCALIZAÇÃO</b></td><td><b>DIREÇÃO E SENTIDO</b></td><td><b>ORIGEM</b></td></tr>" +


                        "<td>" + retTipo(getaObjetos().get(i)) + "</td>" +
                        "<td>" + localizacao + "</td>" +
                        "<td>" + orientacao + "</td>" +
                        "<td>" + origem + "</td>" +
                        "</tr><tr><td colspan=4>Informações adicionais: " + caracteristicas + "</td></tr></tr>";


            }
            c = c + "</table>";

        }
        c = c + "</ol>";

/*

        for (String item : getaItemConstatacao()) {
            c = c + "<li> " + item + ";</li>";
        }
       */
        return c;
    }


    public String geraConstatacaoObjeto() {
        String c = "";
        int nobjetos = getaVeiculosExaminados().size();

        c = c + "<ol>";
        for (int i = 0; i < getaObjetos().size(); i++) {


            if (!(getaObjetos().get(i) instanceof Veiculo)) {


                c = c + "<li> No local foi examinado um(a) " + getaObjetos().get(i).getNome() + " com as seguintes características: " + getaObjetos().get(i).getDescricao() + ";</li>";

            } else {

                c = c + "<li> Das constatações no " + retTipo(getaObjetos().get(i))+  " (" + ((Veiculo) getaObjetos().get(i)).getPlaca() + "): </li>";
            }

            int npafs = getaObjetos().get(i).getaIpafs().size();
            c = c + "<ol>";
            for (int j = 0; j < npafs; j++) {

                formato = getaObjetos().get(i).getaIpafs().get(j).getFormato();
                distsolo = getaObjetos().get(i).getaIpafs().get(j).distsolo;
                angulacao = getaObjetos().get(i).getaIpafs().get(j).angulacao;
                eixomaior = getaObjetos().get(i).getaIpafs().get(j).eixomaior;
                eixomenor = getaObjetos().get(i).getaIpafs().get(j).eixomenor;
                orientacao = getaObjetos().get(i).getaIpafs().get(j).getOrientacao();
                localizacao = getaObjetos().get(i).getaIpafs().get(j).getLocalizacao();
                origem = getaObjetos().get(i).getaIpafs().get(j).getOrigem();
                trajetoria = getaObjetos().get(i).getaIpafs().get(j).getTrajetoria();

                String transfixou = "";
                if (!getaObjetos().get(i).getaIpafs().get(j).getTransfixed()) {
                    transfixou = "transfixante";
                } else {
                    transfixou = "não transfixante";
                }

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
                    c = c + "<li class=\"lialf\">Impacto de projétil de arma de fogo de " + formato + ", localizado no(a) " + localizacao + ", de direção " + orientacao + ", com origem " + origem + "  trajetoria, sendo identificada a seguinte trajetória: " + trajetoria + "</li>";
                } else {
                    c = c + "<li>IPAF(" + (j + 1) + "): Impacto de " + formato + ", localizado no(a) " + localizacao + ", de direção " + orientacao + ", com origem " + origem + "  trajetoria, sendo identificada a seguinte trajetória: " + trajetoria + "</li>";
                }
            }
            c = c + "</ol>";
        }
        c = c + "</ol>";
        /*
        for (String item : getaItemConstatacao()) {
            c = c + "<li> " + item + ";</li>";
        }*/
        return c;
    }

    public String geraConstatacao() {

        String c = "";

        if (!empty(geraConstatacaoObjeto())) {

            c = c + geraConstatacaoObjeto();
        }
        if (!empty(geraTabela())) {

            c = c + geraTabela();
        }
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

    public String retTipo(Objeto o) {

        String tipo = "";
        if (o instanceof Veiculo) {
            if (o instanceof Motocicleta) {
                tipo = "motocicleta(" +((Motocicleta) o).getPlaca() ;
            }
            if (o instanceof Automovel) {
                tipo = "automóvel(" +((Automovel) o).getPlaca() ;
            }
            if (o instanceof Caminhao) {
                tipo = "caminhão(" +((Caminhao) o).getPlaca() ;
            }
            if (o instanceof Onibus) {
                tipo = "onibus(" +((Onibus) o).getPlaca() ;
            }
        }else {
            if (o instanceof Cadaver) {
                tipo = "cadáver";
            }else{
                tipo = o.getNome();
            }
        }

        return tipo;
    }


}