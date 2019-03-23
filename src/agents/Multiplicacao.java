package agents;

import behaviours.BehaviourMultiplicacao;
import jade.core.Agent;

public class Multiplicacao extends Agent{
    private double valores[];
	
	private static final long serialVersionUID = 2L;

	@Override
	protected void setup() {
		System.out.println("Multiplicacao: Inicializado com sucesso !");
		addBehaviour (new BehaviourMultiplicacao(this, valores)) ;
	}

}
