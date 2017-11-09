package coffeespace.com.br.periciasis.Sistema;

import android.util.Log;

import java.util.ArrayList;

import coffeespace.com.br.periciasis.Activitys.MainActivity;
import coffeespace.com.br.periciasis.Sistema.Objetos.Cadaver;
import coffeespace.com.br.periciasis.Sistema.Objetos.Objeto;
import coffeespace.com.br.periciasis.Sistema.Objetos.Onibus;
import coffeespace.com.br.periciasis.Sistema.Objetos.Veiculo;


/**
 * Created by user on 20/10/2017.
 */

public class Pericia {
    private Ocorrencia ocorrencia;
    private Perito peritodesignado;
    private String dinamica, conclusao, local, preservacaodolocal, outroselementos,constatacao;
    private int nobjetos;
    private Perito perito;
    private int pos = 0;

    public Pericia() {
    }
    public Pericia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }


    private ArrayList<Veiculo> aVeiculosExaminados = new ArrayList<Veiculo>(); // que poder ser veiculo, mais de um veiculo, porta, parede etc
    private ArrayList<Cadaver> aCadaveres = new ArrayList<Cadaver>();
    private ArrayList<Objeto> aObjetos = new ArrayList<Objeto>();
    private ArrayList<String> aItemConstatacao = new ArrayList<String>();
    private ArrayList<String> aItemOutrosElementos = new ArrayList<String>();

    public ArrayList<Objeto> getaObjetos() {
        return aObjetos;
    }
    public void setaObjetos(ArrayList<Objeto> aObjetos) {
        this.aObjetos = aObjetos;
    }


    public ArrayList<String> getaItemConstatacao() {
        return aItemConstatacao;
    }
    public void setaItemConstatacao(ArrayList<String> aItemConstatacao) {
        this.aItemConstatacao = aItemConstatacao;
    }

    public ArrayList<String> getaItemOutrosElementos() {
        return aItemOutrosElementos;
    }
    public void setaItemOutrosElementos(ArrayList<String> aItemOutrosElementos) {
        this.aItemOutrosElementos = aItemOutrosElementos;
    }

    public int getPos() {
        return pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getConstatacao() {
        return constatacao;
    }
    public void setConstatacao(String constatacao) {

        aItemConstatacao.add(constatacao);
        this.constatacao = constatacao;
    }

    public void setOutroselementos(String outroselementos) {
        aItemOutrosElementos.add(outroselementos);
        this.outroselementos = outroselementos;
    }
    public String getOutroselementos() {
        return outroselementos;
    }

    public Perito getPerito() {
        return perito;
    }
    public void setPerito(Perito perito) {
        this.perito = perito;
    }

    public ArrayList<Cadaver> getaCadaveres() {
        return aCadaveres;
    }
    public void setaCadaveres(ArrayList<Cadaver> aCadaveres) {
        this.aCadaveres = aCadaveres;
    }

    public String geraTitulo() {

        return "LAUDO DE EXAME EM LOCAL";

    }
    public String geraHeader() {


        String header = "Laudo: " + peritodesignado.getLotacao() + "-" + peritodesignado.getOrgao() + "-" + ocorrencia.getLaudo() + "/" + ocorrencia.getAno() + "<br>" +
                "Procedimento: 0" + firstTwo(ocorrencia.getDp()) + "-" + ocorrencia.getRo() + "/" + ocorrencia.getAno() + "<br>" +
                "Controle Interno " + ocorrencia.getCi() + "-10" + firstTwo(ocorrencia.getDp()) + "/" + ocorrencia.getAno() + "<br>" +
                "Destino: " + ocorrencia.getDp();
        return header;
    }
    public String geraPreambulo() {


        int dia = Integer.parseInt(ocorrencia.getDia());
        String inicial = "Aos " + ocorrencia.getDia() + " dias";
        if (dia == 1) {
            inicial = "No dia " + ocorrencia.getDia() + "º";
        }

        String mes = nToMonth(ocorrencia.getMes());

        String pre = inicial + " do mês de " + mes +
                " do ano de " + ocorrencia.getAno() + ", neste Estado do Rio de Janeiro e no INSTITUTO DE CRIMINALÍSTICA CARLOS ÉBOLI da " +
                "Secretaria de Estado de Segurança, de acordo com a legislação em vigor, o Diretor do " + getPeritodesignado().getOrgao() + " designou " +
                "o Perito Criminal " + getPeritodesignado().getNome() + " atendendo a requisição da Autoridade Policial da " + ocorrencia.getDp() +
                ", descrevendo com a verdade e com todas as circunstâncias o que encontrar.";

        return pre;
    }
    public String geraHistorico() {
        int dia = Integer.parseInt(ocorrencia.getDia());
        String inicial = ocorrencia.getDia();
        if (dia == 1) {
            inicial = ocorrencia.getDia() + "º";
        }
        String tipo;
        if (ocorrencia.getTipolocal() == "Ipaf") {
            tipo = "impacto de projetil de arma de fogo";
        } else {
            tipo = "local";
        }
        String mes = nToMonth(ocorrencia.getMes());

        String hist = "Às " + ocorrencia.getHora() + "h:" + ocorrencia.getMinuto() + "min do dia " + dia + " de " + mes + " do ano de " + ocorrencia.getAno() +
                ", atendendo à solicitação da Autoridade Policial da " + ocorrencia.getDp() + ", o Perito Relator supra assinalado, " +
                " compareceu à " + ocorrencia.getEndereco() + ", a fim de realizar exame em local. Os exames passam a ser relatados nos termos do presente laudo.";

        return hist;

    }
    public String geraDaPreservacao() {

        return "O local não se encontrava preservado, em desacordo com a Resolução Conjunta nº 052/91.";

    }
    public String geraLocal() {

        if (!empty(getLocal())) {
            return getLocal();
        } else {
            return "[ENTRE COM A CONSTATAÇÃO DO LOCAL PERITO]";
        }
    }
    public String geraConstatacao() {

        String ret = "";
        ret = ret + "<ol>";
        for (String item:aItemConstatacao){

            ret = ret + "<li class=\"lialf\"> " + item + ";</li>";
        }
        ret = ret + "</ol>";

        return ret;


    }
    public String geraOutrosElementos(){
        String ret = "";
        ret = ret + "<ol>";

        for (String item:aItemOutrosElementos){

            ret = ret + "<li class=\"lialf\"> " + item + ";</li>";
        }

        ret = ret + "</ol>";

        return ret;

    }

    public String geraDasVitimas() {

        String vitima = "";
        String topo = "";
        int ncad = getaCadaveres().size();

        vitima = vitima + "<ol>";

        for (int i = 0; i < ncad; i++) {
            if (getaCadaveres().size() == 1) {
                topo = "<li><b>Do Cadáver examinado: </b></li>";
            } else {
                topo = "<li><b>Do " + (i + 1) + "º Cadáver examinado: </b></li>";
            }
            vitima = vitima + topo;
            vitima = vitima + "<ol>";
            vitima = vitima + "<li class=\"lialf\">Identificação: cadáver do sexo (masculino/feminino), travestido de (homem/mulher), de cútis (preta/parda/branca), de compleição física (franzina/mediana/robusta), estatura (baixa/mediana/alta), cabelos de cor (campo aberto), (rapado/rente/curto/médio/longo),(encarapinhado/crespo/liso), com bigode, com barba (em formação/em formato de cavanhaque/completa), aparentando ter idade entre (XX) e (XX) anos de idade quando em vida;    Portava documento de identificação em nome de (XX)Apresentava tatuagem(s) na(s) região(ões). (o indumento consistia em/o cadáver estava desprovido de indumento(nu)) trajava xxx</li>";
            vitima = vitima + "<li class=\"lialf\">Posicionamento: encontrava-se (em decúbito dorsal/em decúbito ventral/em decúbito lateral direito/em decúbito lateral esquerdo/sentado/em posição fetal/em suspensão total/em suspensão parcial/em posição arrumada/em submersão parcial/em submersão total/em posição genopeitoral/em posição boxeador), manietado (à região anterior do tronco/à região posterior do tronco) com membro superior direito (fletido/estendido), membro superior esquerdo (fletido/estendido), membro inferior direito (fletido/estendido), membro inferior esquerdo (fletido/estendido), localizado no (colocar o lugar)";
            vitima = vitima + "<li class=\"lialf\">Cronotanatognose: apresentava-se (em fase de flacidez/em fase de rigidez cadavérica parcial/em fase de rigidez cadavérica generalizada/em fase de putrefação/carbonizado/parcialmente carbonizado/esqueletizado), com livores de hipóstase (convergentes/divergentes) com o posicionamento do cadáver";
            vitima = vitima + "<li class=\"lialf\">Perinecroscopia: Foi constatada a presença de (zona de tatuagem, compatível com tiro a curta distância/zona de esfumaçamento, compatível com tiro a curta distância/zona de chamuscamento, compatível com tiro a curta distância/câmara de mina de Hoffman, compatível com tiro encostado/ferimento resvalante compatível com PAF); na(s) região(ões) xxx;";
            vitima = vitima + "<br>Presença de XX(extenso) ferida(s) (contusa(s)/incisa(s)/corto-contusa(s)/pérfuro-incisa(s)/pérfuro-contusa(s)/pérfuro contusa(s) típica(s) das produzida(s) por Projétil de Arma de Fogo(PAF)), na(a) região (ões) xxx.";
            vitima = vitima + "<br> Presença de (pontilhados hemorrágicos  nas conjuntivas/escoriações/equimose(s)/surdimentos hemorrágicos oriundos das fossas nasais/surdimentos hemorrágicos via oral/otorragia/língua protusa/cogumelo de espuma esbranquiçado/cogumelo de espuma avermelhado/circulação póstuma de Brouardel/descolamento de epiderme/maceração/tumefação violácea/sulco pálido ascendente/ sulco pálido descendente/sulco pergaminhado ascendente/sulco pergaminhado descendente/sulco pardo-avermelhado ascendente/sulco pardo-avermelhado descendente)";
            vitima = vitima + "<br>  Ao exame perinecroscópico não foram constatados quaisquer ferimentos visíveis.";
            vitima = vitima + "<br> A esse respeito e, com maiores detalhes, irão se reportar os senhores Doutores Peritos Legistas, que realizarem o exame cadavérico em local e condições apropriados</li>";
            vitima = vitima + "</ol>";
        }
        vitima = vitima + "</ol>";
        return vitima;

    }
    public String geraDosVeiculosEnvolvidos() {
        String ret = "";
        int nv = 0;

        nv = getaVeiculosExaminados().size();
        ret = ret + "<ol>";
        for (int i = 0; i < nv; i++) {

            if (nv == 1) {
                ret = ret + "<li> <b>" + (getaVeiculosExaminados().get(i)).getTipo() + "</b> da marca ";

            }
            if (nv > 1) {
                ret = ret + "<li><b>Veículo " + (i+1) + " (V" + (i+1) + "): " + (getaVeiculosExaminados().get(i)).getTipo() + "</b> da marca ";
            }
            ret = ret
                    + (getaVeiculosExaminados().get(i)).getMarca() + ", modelo "
                    + (getaVeiculosExaminados().get(i)).getModelo() + ", de coloração "
                    + (getaVeiculosExaminados().get(i)).getCor() + ", portando placa "
                    + (getaVeiculosExaminados().get(i)).getPlaca() + ", ";

            if (getaVeiculosExaminados().get(i) instanceof Onibus) {
                ret = ret + "número de ordem " + ((Onibus) getaVeiculosExaminados().get(i)).getNordem() + ", " +
                        "ostentando inscrição referente à empresa " + ((Onibus) getaVeiculosExaminados().get(i)).getEmpresa() + ", ";

            }
            String avarias = getaVeiculosExaminados().get(i).getAvarias();

            ret = ret + "do Estado do "
                    + (getaVeiculosExaminados().get(i)).getEstado() + ", do Município de "
                    + (getaVeiculosExaminados().get(i)).getMunicipio() + ".";
            if (!empty(avarias)) {
                ret = ret + (getaVeiculosExaminados().get(i)).getMunicipio() + ".<br><b>DAS AVARIAS VISÍVEIS:</b>" + getaVeiculosExaminados().get(i).getAvarias() + "<br>";
            }
            ret = ret + "</li>";
        }
        ret = ret + "</ol>";
        return ret;
    }
// EOF COMUM A TODOS OS LAUDOS


/*
    public String geraDeOutrosElementos() {

        aItemOutrosElementos.add("<li class=\"lialf\"> " + outroselementos + ";</li>");
        String ret = "";
        ret = ret + "<ol>";

        for (String item:aItemOutrosElementos){
            ret = ret + "<li class=\"lialf\"> " + item + "</li>";
        }

        ret = ret + "</ol>";

        return ret;

    }
*/
    public String geraDinamica() {

        if (!empty(getDinamica())) {
            return getDinamica();
        } else {
            return null;
        }
    }

    public ArrayList<Veiculo> getaVeiculosExaminados() {
        return aVeiculosExaminados;
    }

    public void setaVeiculosExaminados(ArrayList<Veiculo> aVeiculosExaminados) {
        this.aVeiculosExaminados = aVeiculosExaminados;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }
    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }
    public Perito getPeritodesignado() {
        return peritodesignado;
    }
    public void setPeritodesignado(Perito peritodesignado) {
        this.peritodesignado = peritodesignado;
    }


    public String firstTwo(String str) {

        if (str.length() < 2) {
            return str;
        } else {
            return str.substring(0, 2);
        }
    }

    public String geraConclusao() {
        if (!empty(getConclusao())) {
            return getConclusao();
        } else {
            return "Ante o esposto limita-se o perito às constatações descritas no corpo do laudo, ficando o pleno esclarecimento do evento a cargo da Autoridade Policial competente. Nada mais havendo a lavrar encerra-se o presente laudo que segue assinado pelo Perito Criminal designado.";
        }

    }

    public String imprimeCorpoLaudo() {
        int t = 4;
        String ld;

        ld = "<center>" + geraTitulo() + "</center><br><hr>" +
                "<br>" + geraHeader() + "<br><hr>";
        ld = ld + "<br>" + Tab() + geraPreambulo() + "<br>";
        ld = ld + "<b>1. HISTÓRICO</b><br>" + Tab() + geraHistorico() + "<br>";
        ld = ld + "<b>2. DA PRESERVAÇÃO DO LOCAL</b><br>" + Tab() + geraDaPreservacao() + "<br>";
        ld = ld + "<b>3. DO LOCAL</b><br>" + Tab() + geraLocal() + "<br>";
        if (getaCadaveres().size() == 1) {
            ld = ld + "<b>" + t + ". DO CADÁVER</b><br>" + geraDasVitimas() + "<br>";
            t++;
        }
        if (getaCadaveres().size() > 1) {
            ld = ld + "<b>" + t + ". DOS CADÁVERES</b><br>" + geraDasVitimas() + "<br>";
            t++;
        }
        if (getaVeiculosExaminados().size() == 1) {

            ld = ld + "<b>" + t + ". DO VEÍCULO EXAMINADO: </b>" + geraDosVeiculosEnvolvidos() + "<br>";
            t++;
        }
        if (getaVeiculosExaminados().size() > 1) {
            ld = ld + "<b>" + t + ". DOS VEÍCULOS EXAMINADOS, </b>" + geraDosVeiculosEnvolvidos() + "<br>";
            t++;
        }
        ld = ld + "<b>" + t + ". DAS CONSTATAÇÕES</b><br>" + geraConstatacao() + "<br>";
        t++;
        if (!empty(geraOutrosElementos())) {
            ld = ld + "<b>" + t + ". DE OUTROS ELEMENTOS</b><br>" + geraOutrosElementos() + "<br>";
            t++;
        }
        if (!empty(geraDinamica())) {
            ld = ld + "<b>" + t + ". DA DINÂMICA DO EVENTO</b><br>" + Tab() + geraDinamica() + "<br>";
            t++;
        }
        ld = ld + "<b>" + t + ". CONCLUSÃO</b><br>" + Tab() +
                geraConclusao() + "<br><br><br>" +
                "<center>_____________________________________</center><br><center>" + getPeritodesignado().getNome() + "</center><center>Perito Criminal " + "ID: " + getPeritodesignado().getId() + "</center>";
        return ld;
    }


    public String getDinamica() {
        return dinamica;
    }

    public void setDinamica(String dinamica) {


        this.dinamica = dinamica;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getPreservacaodolocal() {
        return preservacaodolocal;
    }

    public void setPreservacaodolocal(String preservacaodolocal) {
        this.preservacaodolocal = preservacaodolocal;
    }

    public String nToMonth(String s) {

        String monthString;
        switch (s) {
            case "1":
                monthString = "janeiro";
                break;
            case "2":
                monthString = "fevereiro";
                break;
            case "3":
                monthString = "março";
                break;
            case "4":
                monthString = "abril";
                break;
            case "5":
                monthString = "maio";
                break;
            case "6":
                monthString = "junho";
                break;
            case "7":
                monthString = "julio";
                break;
            case "8":
                monthString = "agosto";
                break;
            case "9":
                monthString = "setembro";
                break;
            case "10":
                monthString = "outubro";
                break;
            case "11":
                monthString = "novembro";
                break;
            case "12":
                monthString = "dezembro";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        return monthString;
    }

    public static boolean empty(final String s) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }

    public String Tab() {
        return "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
    }

}
