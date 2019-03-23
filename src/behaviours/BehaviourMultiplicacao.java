package behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.text.DecimalFormat;
import java.util.Locale;

public class BehaviourMultiplicacao extends Behaviour {

    private double valores[];
    private double result;
    private boolean sucess = false;

    public BehaviourMultiplicacao(Agent agent, double valores[]) {
        super(agent);
        this.valores = valores;
        this.result = 0;
    }

    @Override
    public void action() {
        Locale.setDefault(Locale.US);
        ACLMessage msg = myAgent.receive();
        DecimalFormat df = new DecimalFormat("0.####");
        if (msg != null) {
            ACLMessage resposta = msg.createReply();
            String conteudo = msg.getContent();
            String dados[] = conteudo.split("->");
            valores = new double[2];
            valores[0] = Double.parseDouble(dados[0]);
            valores[1] = Double.parseDouble(dados[1]);
            result = valores[0] * valores[1];
            System.out.println("Resolvendo Multiplicacao: " + valores[0] + "*" + valores[1] + " e enviando resultado");
            resposta.setPerformative(ACLMessage.INFORM);
            resposta.setContent("" + df.format(result));
            myAgent.send(resposta);
            block();
        }
    }

    @Override
    public boolean done() {
        return sucess;
    }

}
