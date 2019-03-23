package agents;

import behaviours.BehaviourSoma;
import jade.core.Agent;

public class Soma extends Agent{
    private double valores[];
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.println("Soma: Inicializado com sucesso !");
		addBehaviour (new BehaviourSoma(this, valores)) ;
	}

}
