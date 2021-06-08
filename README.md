# discover-address

#### Descrição: 
API responsável por retornar endereços a partir de um CEP informado.

___
#### Estrutura do projeto:
Projeto foi feito com a SpringBoot e Java 11, os pacotes uma arquitetura MVC híbrida
juntamente com um design orientado a domínios.

___
#### Tecnologias utilizadas:
- SpringBoot
- SpringActuator
- Lombok
- MapStruct
- Gradle
- Java 11

---
#### Tecnologias utilizadas nos testes:
- JUnit
- WireMock
- RestAssured

___
#### A escolha da linguagem Java:
O uso do Java juntamente com o Spring foi totalmente baseada na experiência do 
programador com tal linguagem, e algumas vantagens sobre outras linguagens como:
- MultiThread;
- Comunidade muito ampla;
- Diferentes formas de se chegar no mesmo resultado e não necessariamente ser errado;
- Usabilidade em qualquer sistema;
- Documentação bem estrututurada;
- Está em constante evolução;
- Vários recursos que ajudam no desenvolvimento (plugins, libs, etc...).

___
#### Pergunta 2:
Quando você digita a URL de um site (http://www.netshoes.com.br) no browser e pressiona
enter, explique da forma que preferir, o que ocorre nesse processo do protocolo HTTP 
entre o Client e o Server.

##### Resposta:
O HTTP funciona com uma linha de comunicação entre cliente-servidor onde enviamos uma requisição
e obtemos uma resposta. O HTTP usa o protocolos TCP e IP para fazer essas transferências de
dados e comunicação entre cliente-servidor.
 
Após a requisição HTTP sair do cliente ela necessita passar por diversos proxys para que 
até chegue no servidor onde será processo determinada requisição, temos o proxy de 
dominios do DNS que um dos mais importantes nele que saberemos qual servidor 
aquele a requisição será enviada.

Exemplificando em alguns passos o envio de uma requisição HTTP para http://www.netshoes.com.br :
 - Servidor DNS descobre qual o IP que a requisição precisara redirecionar;
 - O Servidor processa essa requisição;
 - O Servidor devolve uma resposta;
 - O browser interpetra os dados contidos na resposta;
 - O browser constrói na tela.




