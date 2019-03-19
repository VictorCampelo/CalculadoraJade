package agents;

import behaviours.BehaviourControle;
import jade.core.Agent;

public class Controle extends Agent {

    private String expresao = "";

    @Override
    protected void setup() {
        System.out.println("Controle: Agente principal Inicializado!");

        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            expresao = (String) args[0];
            if (args.length == 2) {
                expresao = expresao.concat(",");
                expresao = expresao.concat((String) args[1]);
            }
            System.out.println("Controle: Expressao: " + expresao);
            addBehaviour(new BehaviourControle(this, expresao));
        } else {
            System.out.println("Controle: Aguardando Expressao!");
            addBehaviour(new BehaviourControle(this, null));
        }

    }
}
