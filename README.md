Criação de um sistema calculadora utilizando JADE. Com o JADE é 
possivel gerenciar a criação e manipulação dos agentes utilizados 
na resolução das expressões de entrada na aplicação.

Seu funcionamento consistem em um agente de controle que fica responsável pelo desmembramento da equação de
entrada e encaminhar cada operação para os agentes responsáveis.

COMO EXECUTAR:<br><br>
Adicionar Bibliotecas:<br>
o Clique com botão direito no projeto vá até ‘Build Path → Configure
Build Path’. Na aba ‘Libraries’ clique em ‘Add External JARs’ e
selecione todas as bibliotecas no diretório ‘libs’ da pasta do projeto,
em seguida clique ‘Apply and Close’.<br><br>
Run Configurations:<br>
o Clique com botão direito no projeto vá em ‘Run As → Run
Configurations’. Em ‘Java Application → New Configuration’ na aba
‘Main’ selecione o projeto e em ‘Mainclass’ selecione ‘jade.Boot’. 
Na aba ‘Arguments’ preencha o campo de argumentos com:<br>

<b>-gui -agents controle:agents.Controle;potencia:agents.Potencia;divisao:agents.Divisao;
multiplicacao:agents.Multiplicacao;soma:agents.Soma;subtracao:agents.Subtracao;</b>
<br><br>
Clique em ‘Apply’ e depois ‘Run’ para executar o projeto. Após esta configuração,
para rodar o projeto basta clicar no botão ‘Run’ da barra superior.
