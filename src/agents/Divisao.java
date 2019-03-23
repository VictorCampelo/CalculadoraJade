package agents;

import behaviours.BehaviourDivisao;
import jade.core.Agent;

public class Divisao extends Agent{
    private double valores[];
	
	private static final long serialVersionUID = 2L;

	@Override
	protected void setup() {
		System.out.println("Divisao: Inicializado com sucesso !");
		addBehaviour (new BehaviourDivisao(this, valores)) ;
	}

}
