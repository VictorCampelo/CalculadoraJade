package agents;

import behaviours.BehaviourSoma;
import behaviours.BehaviourControle;
import behaviours.BehaviourDivisao;
import behaviours.BehaviourMultiplicacao;
import behaviours.BehaviourPotencia;
import behaviours.BehaviourSubtracao;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class Potencia extends Agent{
    private int valores[];
	
	private static final long serialVersionUID = 2L;

	@Override
	protected void setup() {
		System.out.println("Potencia: Inicializado com sucesso !");
		addBehaviour (new BehaviourPotencia(this, valores)) ;
	}

}
