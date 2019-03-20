
package behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourDivisao extends Behaviour{
    	private int valores[];
	private int result;
	private boolean sucess = false;
	
	public BehaviourDivisao(Agent agent, int valores[]) {
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
			valores = new int[2];
			valores[0] = Integer.parseInt(dados[0]);
			valores[1] = Integer.parseInt(dados[1]);
			result = valores[0] / valores[1];
			System.out.println("Resolvendo Divisao: " + valores[0]+ "/" + valores[1] + " e enviando resultado");
			resposta.setPerformative(ACLMessage.INFORM);
			resposta.setContent("" + result);
			myAgent.send(resposta);
			block();
		}else{
			//System.out.print(".");
		}		
	}

	@Override
	public boolean done() {
		return sucess;
	}

}
