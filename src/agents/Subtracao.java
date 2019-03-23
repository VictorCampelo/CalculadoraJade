package agents;

import behaviours.BehaviourSubtracao;
import jade.core.Agent;

public class Subtracao extends Agent{
    private double valores[];
	
	private static final long serialVersionUID = 2L;

	@Override
	protected void setup() {
		System.out.println("Subtracao: Inicializado com sucesso !");
		addBehaviour (new BehaviourSubtracao(this, valores)) ;
	}

}
