package coffeespace.com.br.periciasis.Sistema.Objetos;

/**
 * Created by user on 20/10/2017.
 */

public class Veiculo extends Objeto {

    String marca,modelo,placa,cor,municipio,estado,tipo;

    @Override
    public String getAvarias() {
        return super.getAvarias();
    }

    public Veiculo(String placa) {
        this.placa = placa;
    }
    public Veiculo(){
    }

    public Veiculo(String placa, String tipo) {
        this.placa = placa;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
