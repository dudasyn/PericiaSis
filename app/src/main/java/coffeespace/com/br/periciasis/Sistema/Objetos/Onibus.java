package coffeespace.com.br.periciasis.Sistema.Objetos;

/**
 * Created by user on 20/10/2017.
 */

public class Onibus extends Veiculo {
    private String nordem,empresa;

    public Onibus(String placa, String tipo) {
        super(placa, tipo);
    }

    public Onibus(String placa) {
        super(placa);
    }

    public Onibus() {
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

    public String getNordem() {
        return nordem;
    }

    public void setNordem(String nordem) {
        this.nordem = nordem;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
