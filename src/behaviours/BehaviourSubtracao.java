
package behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourSubtracao extends Behaviour{
    	private double valores[];
	private double result;
	private boolean sucess = false;
	
	public BehaviourSubtracao(Agent agent, double valores[]) {
		super(agent);
		this.valores = valores;
		this.result = 0;
	}

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			ACLMessage resposta = msg.createReply();
			String conteudo = msg.getContent();
			String dados[] = conteudo.split("->");
			valores = new double[2];
			valores[0] = Double.parseDouble(dados[0]);
			valores[1] = Double.parseDouble(dados[1]);
			result = valores[0] - valores[1];
			System.out.println("Resolvendo Subtracao: " + valores[0]+ "-" + valores[1] + " e enviando resultado");
			resposta.setPerformative(ACLMessage.INFORM);
			resposta.setContent("" + result);
			myAgent.send(resposta);
			block();
		}		
	}

	@Override
	public boolean done() {
		return sucess;
	}

}
