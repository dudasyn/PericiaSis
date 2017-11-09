package coffeespace.com.br.periciasis.Sistema.Objetos;

import java.util.ArrayList;

import coffeespace.com.br.periciasis.Sistema.Dano;
import coffeespace.com.br.periciasis.Sistema.Ipaf;

/**
 * Created by user on 20/10/2017.
 */

public class Objeto {
    private int id;
    private ArrayList<Ipaf> aIpafs = new ArrayList<Ipaf>();
    private ArrayList<Dano> aDanos = new ArrayList<Dano>();
    private int pos;
    private String avarias;

    public String getAvarias() {
        return avarias;
    }

    public void setAvarias(String avarias) {
        this.avarias = avarias;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Ipaf> getaIpafs() {
        return aIpafs;
    }

    public void setaIpafs(ArrayList<Ipaf> aIpafs) {
        this.aIpafs = aIpafs;
    }

    public ArrayList<Dano> getaDanos() {
        return aDanos;
    }

    public void setaDanos(ArrayList<Dano> aDanos) {
        this.aDanos = aDanos;
    }
}
