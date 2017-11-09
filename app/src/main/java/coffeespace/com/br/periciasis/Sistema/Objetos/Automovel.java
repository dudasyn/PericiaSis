package coffeespace.com.br.periciasis.Sistema.Objetos;

import java.util.ArrayList;

import coffeespace.com.br.periciasis.Sistema.Dano;
import coffeespace.com.br.periciasis.Sistema.Ipaf;

/**
 * Created by user on 20/10/2017.
 */


public class Automovel extends Veiculo {

    @Override
    public ArrayList<Ipaf> getaIpafs() {
        return super.getaIpafs();
    }

    @Override
    public void setaIpafs(ArrayList<Ipaf> aIpafs) {
        super.setaIpafs(aIpafs);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public ArrayList<Dano> getaDanos() {
        return super.getaDanos();
    }

    @Override
    public void setaDanos(ArrayList<Dano> aDanos) {
        super.setaDanos(aDanos);
    }

    public Automovel(String placa) {
        super(placa);
    }

    public Automovel() {
        super();
    }

    @Override
    public String getMarca() {
        return super.getMarca();
    }

    @Override
    public void setMarca(String marca) {
        super.setMarca(marca);
    }

    @Override
    public String getModelo() {
        return super.getModelo();
    }

    @Override
    public void setModelo(String modelo) {
        super.setModelo(modelo);
    }

    @Override
    public String getPlaca() {
        return super.getPlaca();
    }

    @Override
    public void setPlaca(String placa) {
        super.setPlaca(placa);
    }

    @Override
    public String getCor() {
        return super.getCor();
    }

    @Override
    public void setCor(String cor) {
        super.setCor(cor);
    }

    @Override
    public String getMunicipio() {
        return super.getMunicipio();
    }

    @Override
    public void setMunicipio(String municipio) {
        super.setMunicipio(municipio);
    }

    @Override
    public String getEstado() {
        return super.getEstado();
    }

    @Override
    public void setEstado(String estado) {
        super.setEstado(estado);
    }

    public Automovel(String placa, String tipo) {
        super(placa, tipo);
    }
}
