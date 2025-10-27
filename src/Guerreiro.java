public class Guerreiro extends Jogador {
    public Guerreiro(String nome) {
        super(nome, 150, 10, 14);
    }

    @Override
    public void ataqueEspecial(Jogador alvo) {
        System.out.println(this.nome + " usa ATAQUE PODEROSO!");
        int rolagemDeAtaque = dado.nextInt(20) + 1;

        if (rolagemDeAtaque >= alvo.getArmadura() + 2) {
            int danoCausado = this.danoBase * 2;
            System.out.printf(">>> ACERTO CRÍTICO! Um golpe devastador causa %d de dano!%n", danoCausado);
            alvo.receberDano(danoCausado);
        } else {
            System.out.println(">>> O ataque foi poderoso, mas o inimigo conseguiu desviar!");
        }
    }
}