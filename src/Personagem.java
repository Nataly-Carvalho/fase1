public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected int ataque;
    protected int defesa;

    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public void atacar(Personagem alvo) {
        int dano = this.ataque - alvo.defesa;
        if (dano > 0) {
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.nome + " causando " + dano + " de dano.");
        } else {
            System.out.println(this.nome + " atacou " + alvo.nome + ", mas a defesa do alvo foi muito forte!");
        }
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            System.out.println(this.nome + " foi derrotado!");
            this.vida = 0;
        }
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }

    @Override
    public String toString() {
        return String.format("Nome: %-15s | Vida: %-4d | Ataque: %-4d | Defesa: %-4d",
                this.nome, this.vida, this.ataque, this.defesa);
    }
}