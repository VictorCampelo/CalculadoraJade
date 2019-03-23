package agents;

import behaviours.BehaviourPotencia;
import jade.core.Agent;

public class Potencia extends Agent{
    private double valores[];
	
	private static final long serialVersionUID = 2L;

	@Override
	protected void setup() {
		System.out.println("Potencia: Inicializado com sucesso !");
		addBehaviour (new BehaviourPotencia(this, valores)) ;
	}

}
