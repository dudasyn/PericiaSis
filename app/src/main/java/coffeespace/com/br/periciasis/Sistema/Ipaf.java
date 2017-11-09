package coffeespace.com.br.periciasis.Sistema;

/**
 * Created by user on 20/10/2017.
 */

public class Ipaf {

    String eixomaior,eixomenor,localizacao,distsolo,angulacao,observacoes,formato,azimute,origem,orientacao,trajetoria;
    Boolean isTransfixed;



    public Boolean getTransfixed() {
        return isTransfixed;
    }

    public void setTransfixed(Boolean transfixed) {
        isTransfixed = transfixed;
    }

    public String getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(String orientacao) {
        this.orientacao = orientacao;
    }

    public String getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(String trajetoria) {
        this.trajetoria = trajetoria;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getAzimute() {
        return azimute;
    }

    public void setAzimute(String azimute) {
        this.azimute = azimute;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Ipaf(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getEixomaior() {
        return eixomaior;
    }

    public void setEixomaior(String eixomaior) {
        this.eixomaior = eixomaior;
    }

    public String getEixomenor() {
        return eixomenor;
    }

    public void setEixomenor(String eixomenor) {
        this.eixomenor = eixomenor;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDistsolo() {
        return distsolo;
    }

    public void setDistsolo(String distsolo) {
        this.distsolo = distsolo;
    }

    public String getAngulacao() {
        return angulacao;
    }

    public void setAngulacao(String angulacao) {
        this.angulacao = angulacao;
    }
}
