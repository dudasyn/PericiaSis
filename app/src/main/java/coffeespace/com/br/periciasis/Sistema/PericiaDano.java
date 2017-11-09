package coffeespace.com.br.periciasis.Sistema;

import java.util.ArrayList;

/**
 * Created by user on 27/10/2017.
 */

public class PericiaDano extends Pericia {
    private ArrayList<Dano> danos;

    public String geraTitulo() {
        return "<b>LAUDO DE EXAME EM LOCAL DE DANO</b>";
    }

    public String geraConclusao() {
        return "Ante o exposto, limita-se o perito a relatar o dano conforme especificado no corpo do laudo, ressaltando que o mesmo foi produzido por ação de objeto contundente. Não foi possível determinar o horário do evento, assim como o número de agentes envolvidos, devido à falta de elementos técnicos geradores de convicção. Nada mais havendo a lavrar, encerra-se o presente laudo que segue assinado pelo perito signatário.";
    }

}
