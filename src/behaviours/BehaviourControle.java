/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours;

import agents.Controle;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Victor Campelo
 */
public class BehaviourControle extends CyclicBehaviour {

    private String exp = "";

    public BehaviourControle(Controle aThis, String expresao) {
        super(aThis);
    }

    @Override
    public void action() {

        ACLMessage msg = myAgent.receive();

        if (msg != null) {
            String conteudo = msg.getContent();
            extrairNivel0(conteudo);
            done();
        }

    }

    private String extrairNivel0(String exp) {
        String aux = "";
        String left = "";
        String right = "";
        String nova = exp;
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') {
                if (i == 0) {
                    for (int j = i + 1; j < exp.length(); j++) {
                        switch (exp.charAt(j)) {
                            case '(':
                                while (exp.charAt(j) != ')') {
                                    aux += exp.charAt(j);
                                    j++;
                                }   break;
                            case ')':
                                aux = extrairNivel0(aux);
                                for (int k = j+1; k < exp.length(); k++) {
                                    right += exp.charAt(k);
                                }   nova = aux + right;
                                System.out.println("nova: " + nova);
                                extrairNivel0(nova);
                                break;
                            default:
                                aux += exp.charAt(j);
                                break;
                        }
                    }
                } else if (i > 0 && i < exp.length()) {
                    for (int j = i + 1; j < exp.length(); j++) {
                        if (exp.charAt(j) == ')') {
                            if (exp.charAt(j) == '(') {
                                while (exp.charAt(j) != ')') {
                                    aux += exp.charAt(j);
                                    j++;
                                }
                            }
                            aux = extrairNivel0(aux);
                            for (int k = j+1; k < exp.length(); k++) {
                                right += exp.charAt(k);
                            }
                            nova = left + aux + right;
                            System.out.println("nova: " + nova);
                            extrairNivel0(nova);
                        } else {
                            aux += exp.charAt(j);
                        }
                    }
                }
            }
            left += exp.charAt(i);
        }
        return extrairNivel4(extrairNivel3(extrairNivel2((extrairNivel1((nova))))));
    }

    private String extrairNivel1(String exp) {
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '^') {
                exp = extrairNivel4(extrairNivel3(extrairNivel2((extrairNivel1(termos(i, exp, 5))))));
                break;
            }
        }
        return exp;
    }

    private String extrairNivel2(String exp) {
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '*') {
                exp = extrairNivel4(extrairNivel3(extrairNivel2(termos(i, exp, 3))));
                break;
            }
        }
        return exp;
    }

    private String extrairNivel3(String exp) {
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '/') {
                exp = extrairNivel4(extrairNivel3(termos(i, exp, 4)));
                break;
            }
        }
        return exp;
    }

    private String extrairNivel4(String exp) {
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '+') {
                if(exp.charAt(i+1) == '-'){
                    String aux = exp.substring(0,i-1) +"-"+ exp.substring(i+2);
                    exp = extrairNivel4(termos(i,aux,2));
                    break;
                }
                if(exp.charAt(i+1) == '+'){
                    String aux = exp.substring(0,i-1) +"+"+ exp.substring(i+2);
                    exp = extrairNivel4(termos(i,aux,1));
                    break;
                }
                exp = extrairNivel4(termos(i, exp, 1));
                break;
            }
            if (exp.charAt(i) == '-' && i > 0) {
                if(exp.charAt(i+1) == '+'){
                    String aux = exp.substring(0,i-1) +"-"+ exp.substring(i+2);
                    exp = extrairNivel4(termos(i,aux,2));
                    break;
                }
                if(exp.charAt(i+1) == '-'){
                    String aux = exp.substring(0,i-1) +"+"+ exp.substring(i+2);
                    exp = extrairNivel4(termos(i,aux,1));
                    break;
                }
                exp = extrairNivel4(termos(i, exp, 2));
                break;
            }
        }
        return exp;
    }

    private String termos(int i, String exp1, int tipo) throws NumberFormatException {
        //encontrar os valores parentes desse operador
        System.out.println(exp1);
        String left = "";
        String right = "";
        String opLeft = "";
        String opRight = "";
        int j = i - 1;
        //busca o valor da esquerda do operador
        while (j > 0) {
            if (exp1.charAt(j) == '^') {
                opLeft = "^";
                break;
            }
            if (exp1.charAt(j) == '*') {
                opLeft = "*";
                break;
            }
            if (exp1.charAt(j) == '/') {
                opLeft = "/";
                break;
            }
            if (exp1.charAt(j) == '+') {
                opLeft = "+";
                break;
            }
            if (exp1.charAt(j) == '-') {
                if (j - 1 == 0) {
                    break;
                } else {
                    opLeft = "-";
                    break;
                }
            }
            j--;
        }

        //guarda a expressão do lado esquerdo do fator analisado
        for (int k = 0; k < j; k++) {
            left += exp1.charAt(k);
        }

        String expv1 = "";
        String expv2 = "";
        int v1, v2;
        //formar a String que forma correntamente o numero do lado esquerdo do operados avaliado
        if (j == 0 && exp1.charAt(j) == '-') {
            expv1 += exp1.charAt(j);
            j++;
            while (exp1.charAt(j) != '+' && exp1.charAt(j) != '-'
                    && exp1.charAt(j) != '*' && exp1.charAt(j) != '/'
                    && exp1.charAt(j) != '^') {
                expv1 += exp1.charAt(j);
                j++;
            }
        } else {
            if (j == 0) {
                while (exp1.charAt(j) != '+' && exp1.charAt(j) != '-'
                        && exp1.charAt(j) != '*' && exp1.charAt(j) != '/'
                        && exp1.charAt(j) != '^') {
                    expv1 += exp1.charAt(j);
                    j++;
                }
            } else {
                j++;
                while (exp1.charAt(j) != '+' && exp1.charAt(j) != '-'
                        && exp1.charAt(j) != '*' && exp1.charAt(j) != '/'
                        && exp1.charAt(j) != '^') {
                    expv1 += exp1.charAt(j);
                    j++;
                }
            }
        }
        v1 = Integer.parseInt(expv1);
        j++;
        //burca o valor da direita do operador
        while (j < exp1.length()) {
            if (exp1.charAt(j) == '^') {
                opRight = "^";
                break;
            }
            if (exp1.charAt(j) == '*') {
                opRight = "*";
                break;
            }
            if (exp1.charAt(j) == '/') {
                opRight = "/";
                break;
            }
            if (exp1.charAt(j) == '+') {             
                opRight = "+";
                break;
            }
            if (exp1.charAt(j) == '-') {
                opRight = "-";
                break;
            }
            expv2 += exp1.charAt(j);
            j++;
        }
        //guarda a expressão do lado direitor do fator analisado
        for (int k = j + 1; k < exp1.length(); k++) {
            right += exp1.charAt(k);
        }

        v2 = Integer.parseInt(expv2);

        switch (tipo) {
            case 1:
                soma(v1, v2);
                break;
            case 2:
                subtracao(v1, v2);
                break;
            case 3:
                multiplicacao(v1, v2);
                break;
            case 4:
                divisao(v1, v2);
                break;
            case 5:
                potencia(v1, v2);
                break;
        }

        //remontar expressão
        if ("".equals(left) && "".equals(right)) {
            return String.valueOf(obterResposta());
        } else if ("".equals(left) && !right.isEmpty()) {
            return String.valueOf(obterResposta()) + opRight + right;
        } else if (!left.isEmpty() && "".equals(right)) {
            return left + opLeft + String.valueOf(obterResposta());
        } else {
            return left + opLeft + String.valueOf(obterResposta()) + opRight + right;
        }
    }

    private void soma(int v1, int v2) {
        ACLMessage msg;
        System.out.println("solicitando operacao para AgenteSoma (" + v1 + " + " + v2 + ")");
        msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("soma", AID.ISLOCALNAME));
        msg.setLanguage("Portugues");
        msg.setOntology("OperacaoAritmetica");
        msg.setContent(v1 + "->" + v2);
        myAgent.send(msg);
    }

    private void subtracao(int v1, int v2) {
        ACLMessage msg;
        System.out.println("solicitando operacao para subtracao (" + v1 + " - " + v2 + ")");
        msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("subtracao", AID.ISLOCALNAME));
        msg.setLanguage("Portugues");
        msg.setOntology("OperacaoAritmetica");
        msg.setContent(v1 + "->" + v2);
        myAgent.send(msg);
    }

    private void multiplicacao(int v1, int v2) {
        ACLMessage msg;
        System.out.println("solicitando operacao para multiplicacao (" + v1 + " * " + v2 + ")");
        msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("multiplicacao", AID.ISLOCALNAME));
        msg.setLanguage("Portugues");
        msg.setOntology("OperacaoAritmetica");
        msg.setContent(v1 + "->" + v2);
        myAgent.send(msg);
    }

    private void divisao(int v1, int v2) {
        ACLMessage msg;
        System.out.println("solicitando operacao para multiplicacao (" + v1 + " / " + v2 + ")");
        msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("divisao", AID.ISLOCALNAME));
        msg.setLanguage("Portugues");
        msg.setOntology("OperacaoAritmetica");
        msg.setContent(v1 + "->" + v2);
        myAgent.send(msg);
    }

    private void potencia(int v1, int v2) {
        ACLMessage msg;
        System.out.println("solicitando operacao para potencia (" + v1 + "^" + v2 + ")");
        msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("potencia", AID.ISLOCALNAME));
        msg.setLanguage("Portugues");
        msg.setOntology("OperacaoAritmetica");
        msg.setContent(v1 + "->" + v2);
        myAgent.send(msg);
    }

    private int obterResposta() {
        int resultado = 0;
        boolean calculado = true;
        ACLMessage msg;
        do {
            msg = myAgent.receive();

            if (msg != null) {
                resultado = Integer.parseInt(msg.getContent());
                System.out.println("Agente controle: " + msg.getSender().getName() + " enviou resultado: " + resultado);
                calculado = false;
            }
        } while (calculado);

        return resultado;
    }
}
