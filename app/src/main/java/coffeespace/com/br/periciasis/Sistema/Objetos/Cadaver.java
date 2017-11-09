package coffeespace.com.br.periciasis.Sistema.Objetos;

import java.util.ArrayList;

import coffeespace.com.br.periciasis.Sistema.Dano;
import coffeespace.com.br.periciasis.Sistema.Ipaf;

/**
 * Created by user on 02/11/2017.
 */

public class Cadaver extends Objeto {

    @Override
    public String getAvarias() {
        return super.getAvarias();
    }

    @Override
    public int getPos() {
        return super.getPos();
    }

    @Override
    public void setPos(int pos) {
        super.setPos(pos);
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
    public ArrayList<Ipaf> getaIpafs() {
        return super.getaIpafs();
    }

    @Override
    public void setaIpafs(ArrayList<Ipaf> aIpafs) {
        super.setaIpafs(aIpafs);
    }

    @Override
    public ArrayList<Dano> getaDanos() {
        return super.getaDanos();
    }

    @Override
    public void setaDanos(ArrayList<Dano> aDanos) {
        super.setaDanos(aDanos);
    }
}
