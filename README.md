# **Jogo de RPG em Texto**

Bem-vindo a este simples, porém interativo, jogo de RPG de texto desenvolvido em Java. Crie seu herói, escolha uma classe e enfrente inimigos perigosos em um combate estratégico por turnos. Ganhe experiência, suba de nível e torne-se uma lenda!

## ✨ **Funcionalidades**

* **Combate em Turnos:** Sistema de combate clássico onde herói e inimigo se revezam em suas ações.
* **Sistema de Classes:** Escolha entre o resistente **Guerreiro** e o poderoso **Mago**, cada um com um ataque especial único.
* **Progressão e Níveis:** Derrote inimigos para ganhar pontos de experiência (XP) e subir de nível, melhorando seus atributos.
* **Inimigos Aleatórios:** A cada batalha, enfrente um inimigo diferente (Goblin, Orc, Lobo), garantindo que nenhuma luta seja igual à outra.
* **Combate Estratégico:** Decida quando **Atacar**, se **Defender** para reduzir o dano, usar uma **Poção de Cura** para sobreviver ou desferir um **Ataque Especial** devastador.

## ⚙️ **Pré-requisitos**

Antes de começar, garanta que você tenha o seguinte software instalado em sua máquina:
* **Java Development Kit (JDK)** - Versão 11 ou superior. Você pode baixá-lo [aqui](https://www.oracle.com/java/technologies/downloads/).

## 🚀 **Como Rodar o Jogo**

### Usando uma IDE (IntelliJ IDEA)**

1.  **Abra o Projeto:**
    * Clone ou baixe os arquivos do projeto para o seu computador.
    * Abra sua IDE e selecione a opção "Open Project" ou "Import Project".
    * Navegue até a pasta onde você salvou os arquivos e selecione-a.

2.  **Localize a Classe Principal:**
    * Na estrutura do projeto, encontre o arquivo `Main.java` dentro da pasta `src`.

3.  **Rode o Jogo:**
    * Clique com o botão direito no arquivo `Main.java`.
    * Selecione a opção **"Run 'Main.main()'"**.
    * O jogo será executado no console da sua IDE.


## 🎮 **Como Jogar**

1.  Ao iniciar, um menu principal será exibido.
2.  Use a opção **1. Criar Herói** para dar um nome e escolher uma classe para seu personagem.
3.  Você pode criar quantos heróis quiser. Use a opção **2. Ver Heróis** para listar todos eles.
4.  Para lutar, escolha a opção **3. Iniciar Batalha**, digite o nome do herói que você quer usar e prepare-se!
5.  Durante a batalha, escolha suas ações a cada turno para derrotar o inimigo.

## 📁 **Estrutura do Projeto**


jogo/
<br>
└── src/
<br>
├── Guerreiro.java
<br>
├── Inimigo.java
<br>
├── Jogador.java
<br>
├── Mago.java
<br>
├── Main.java
<br>
└── OpcaoInvalidaException.java
