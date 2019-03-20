package agents;

import behaviours.BehaviourSoma;
import behaviours.BehaviourControle;
import behaviours.BehaviourDivisao;
import behaviours.BehaviourMultiplicacao;
import behaviours.BehaviourSubtracao;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class Divisao extends Agent{
    private int valores[];
	
	private static final long serialVersionUID = 2L;

	@Override
	protected void setup() {
		System.out.println("Divisao: Inicializado com sucesso !");
		addBehaviour (new BehaviourDivisao(this, valores)) ;
	}

}
