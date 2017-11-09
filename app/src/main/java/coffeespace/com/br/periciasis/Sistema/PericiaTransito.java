package coffeespace.com.br.periciasis.Sistema;

/**
 * Created by user on 02/11/2017.
 */

public class PericiaTransito extends Pericia {


    public String geraTitulo() {

        return "<b>LAUDO DE EXAME EM LOCAL DE ACIDENTE DE TRÂNSITO</b>";

    }

    @Override
    public void setConstatacao(String constatacao) {
                /*
        String common = "<li class=\"lialf\">Passou da sub classe e depois pegou o da super como eu queria </li>" +
                "<li class=\"lialf\">Constatação 2</li>" +
                "<li class=\"lialf\">Constatação 2</li>";
        */
        getaItemConstatacao().add("O piso encontrava-se seco/molhado;");
        getaItemConstatacao().add("Visibilidade na via era boa/ruim/regular/ e praticada por luz artificial, chovia quando realizados os exames, sem iluminação na via");
        getaItemConstatacao().add("Intervisibilidade adequadaVisibilidade na via era boa/ruim/regular/ e praticada por luz artifiial, sem iluminação na via");
        super.setConstatacao(constatacao);
    }

    @Override
    public void setOutroselementos(String outroselementos) {
        getaItemOutrosElementos().add("Os exames foram acompanhados em sua totalidade pelo Senhor xx, RG/CPF nº xxx;");
        super.setOutroselementos(outroselementos);
        getaItemOutrosElementos().add("Nada mais de interesse criminalístico a ser constatado.");
    }
}
