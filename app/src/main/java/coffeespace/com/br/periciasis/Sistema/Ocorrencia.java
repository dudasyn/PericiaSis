package coffeespace.com.br.periciasis.Sistema;

/**
 * Created by user on 20/10/2017.
 */
public class Ocorrencia {
    private String ro,ci,dp,laudo,data,hora,minuto,dia,mes,ano,tipolocal,endereco,enderecodp;

    public Ocorrencia(String ro, String ci, String dp) {
        this.ro = ro;
        this.ci = ci;
        this.dp = dp;
    }
    public Ocorrencia(){
    }

    public String getEnderecodp() {
        return enderecodp;
    }

    public void setEnderecodp(String enderecodp) {
        this.enderecodp = enderecodp;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipolocal() {
        return tipolocal;
    }

    public void setTipolocal(String tipolocal) {
        this.tipolocal = tipolocal;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getRo() {
        return ro;
    }

    public void setRo(String ro) {
        this.ro = ro;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

