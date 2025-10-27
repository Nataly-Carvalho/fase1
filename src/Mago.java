public class Mago extends Jogador {
    public Mago(String nome) {
        super(nome, 125, 12, 12);
    }

    @Override
    public void ataqueEspecial(Jogador alvo) {
        System.out.println(this.nome + " conjura BOLA DE FOGO!");
        int danoCausado = dado.nextInt(this.danoBase) + (this.danoBase / 2); // Dano é 50% a 150% do dano base
        System.out.printf(">>> A bola de fogo envolve %s, causando %d de dano mágico!%n", alvo.getNome(), danoCausado);
        alvo.receberDano(danoCausado);
    }
}