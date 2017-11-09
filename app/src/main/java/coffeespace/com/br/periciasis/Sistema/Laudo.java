package coffeespace.com.br.periciasis.Sistema;

/**
 * Created by user on 23/10/2017.
 */

public class Laudo {

    private String corpodolaudo;
    private Perito perito;

    public Perito getPerito() {
        return perito;
    }

    public void setPerito(Perito perito) {
        this.perito = perito;
    }

    int index = 3;

    public Laudo() {
    }

    public String getCorpodolaudo() {
        return corpodolaudo;
    }

    public void setCorpodolaudo(String corpodolaudo) {
        this.corpodolaudo = corpodolaudo;
    }

    public Laudo(String corpodolaudo) {
        this.corpodolaudo = corpodolaudo;
    }

    public String imprimeLaudo() {

        String corpo=corpodolaudo;

        String ld = "<html><head>" + getCss() + "</head><body>"+corpodolaudo +"<br>" +
              //  "<center>_____________________________________</center><center>Laudo<br>" +perito.getNome() + " - " + "ID: " + perito.getId() + "</center>" +
                "</body></html>";

        return ld;

    }

    public String getCss() {
        String style = "<style TYPE=\"text/css\">" +
                "<!--  " +
                "body { font: normal 16px  Serif}" +
                "li.lialf {list-style-type: lower-alpha;}" +

                //"li { list-style-type: circle; }" +
              /*  "#imgA{background-image:url('file:///android_res/drawable/tux.jpg');" +
                "width:300px;" +
                "height:300px;}" +
*/
                "--></style>";

        return style;
    }
}
