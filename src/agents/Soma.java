package agents;

import behaviours.BehaviourSoma;
import behaviours.BehaviourControle;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class Soma extends Agent{
    private int valores[];
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.println("Soma: Inicializado com sucesso !");
		addBehaviour (new BehaviourSoma(this, valores)) ;
	}

}
