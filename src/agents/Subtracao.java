package agents;

import behaviours.BehaviourSoma;
import behaviours.BehaviourControle;
import behaviours.BehaviourSubtracao;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class Subtracao extends Agent{
    private int valores[];
	
	private static final long serialVersionUID = 2L;

	@Override
	protected void setup() {
		System.out.println("Subtracao: Inicializado com sucesso !");
		addBehaviour (new BehaviourSubtracao(this, valores)) ;
	}

}
