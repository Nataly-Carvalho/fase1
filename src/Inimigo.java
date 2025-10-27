public class Inimigo extends Jogador {
    public Inimigo(String nome, int vida, int danoBase, int armadura) {
        super(nome, vida, danoBase, armadura);
    }

    @Override
    public void ataqueEspecial(Jogador alvo) {
        System.out.println(this.nome + " tenta um ataque feroz!");
        this.atacar(alvo);
    }
}