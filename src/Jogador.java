import java.util.Random;

public abstract class Jogador {
    protected String nome;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int danoBase;
    protected int armadura;
    protected Random dado = new Random();
    protected int nivel = 1;
    protected int xp = 0;
    protected int xpParaProximoNivel = 100;
    protected int pocoesDeCura = 3;
    protected boolean estaDefendendo = false;

    public Jogador(String nome, int vida, int danoBase, int armadura) {
        this.nome = nome;
        this.vidaMaxima = vida;
        this.vidaAtual = vida; // Começa com a vida cheia
        this.danoBase = danoBase;
        this.armadura = armadura;
    }

    public void atacar(Jogador alvo) {
        this.estaDefendendo = false;

        int rolagemDeAtaque = dado.nextInt(20) + 1;
        System.out.printf("%s ataca %s... (Rolagem: %d vs Armadura do alvo: %d)%n", this.nome, alvo.nome, rolagemDeAtaque, alvo.getArmadura());

        if (rolagemDeAtaque >= alvo.getArmadura()) {
            int danoCausado = dado.nextInt(this.danoBase) + 1;
            System.out.printf(">>> ACERTOU! %s brande sua arma e causa %d de dano!%n", this.nome, danoCausado);
            alvo.receberDano(danoCausado);
        } else {
            System.out.println(">>> ERROU! O ataque de " + this.nome + " passa longe do alvo.");
        }
    }

    public void defender() {
        this.estaDefendendo = true;
        System.out.printf("%s levanta seu escudo (ou prepara uma barreira mágica), preparando-se para o próximo ataque!%n", this.nome);
    }

    public void usarPocao() {
        if (pocoesDeCura > 0) {
            int cura = vidaMaxima / 2; // Cura 50% da vida máxima
            this.vidaAtual += cura;
            if (this.vidaAtual > this.vidaMaxima) {
                this.vidaAtual = this.vidaMaxima;
            }
            this.pocoesDeCura--;
            System.out.printf("%s bebe uma poção e recupera %d de vida! (Poções restantes: %d)%n", this.nome, cura, this.pocoesDeCura);
        } else {
            System.out.println("Você não tem mais poções de cura!");
        }
    }

    public abstract void ataqueEspecial(Jogador alvo);

    public void receberDano(int danoSofrido) {
        this.vidaAtual -= danoSofrido;
        if (this.vidaAtual < 0) this.vidaAtual = 0;
    }

    public void ganharXp(int xpGanha) {
        this.xp += xpGanha;
        System.out.printf("%s ganhou %d de XP! (XP Atual: %d/%d)%n", this.nome, xpGanha, this.xp, this.xpParaProximoNivel);
        if (this.xp >= this.xpParaProximoNivel) {
            subirDeNivel();
        }
    }

    private void subirDeNivel() {
        this.nivel++;
        this.xp -= this.xpParaProximoNivel;
        this.xpParaProximoNivel *= 1.5;
        this.vidaMaxima += 20;
        this.danoBase += 3;
        this.armadura += 1;
        this.vidaAtual = this.vidaMaxima; // Cura total ao subir de nível

        System.out.println("========================================");
        System.out.println("🎉 LEVEL UP! " + this.nome + " alcançou o Nível " + this.nivel + "! 🎉");
        System.out.println("Vida aumentada, mais forte e resistente!");
        System.out.println("========================================");
    }

    public String getNome() { return nome; }
    public int getVidaAtual() { return vidaAtual; }
    public boolean estaVivo() { return this.vidaAtual > 0; }
    public int getArmadura() {
        return this.estaDefendendo ? this.armadura + 5 : this.armadura;
    }

    @Override
    public String toString() {
        return String.format("Nv. %d %-10s | %-20s | Vida: %d/%d | Dano: %d | Armadura: %d | Poções: %d",
                this.nivel, this.getClass().getSimpleName(), this.nome, this.vidaAtual, this.vidaMaxima, this.danoBase, this.armadura, this.pocoesDeCura);
    }
}