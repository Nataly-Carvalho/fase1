import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Jogador> personagens = new HashMap<>();
    private static Random random = new Random();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Criar Herói");
            System.out.println("2. Ver Heróis");
            System.out.println("3. Iniciar Batalha");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1: criarPersonagem(); break;
                    case 2: verPersonagens(); break;
                    case 3: iniciarBatalha(); break;
                    case 4: System.out.println("Até a próxima aventura!"); return;
                    default: throw new OpcaoInvalidaException("Opção inválida. Por favor, escolha de 1 a 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERRO] Entrada inválida! Por favor, digite um número.");
                scanner.nextLine();
            } catch (OpcaoInvalidaException e) {
                System.out.println("\n[ERRO] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n[ERRO] Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }
    private static void criarPersonagem() {
        System.out.print("Digite o nome do seu novo herói: ");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("[ERRO] O nome não pode ser vazio.");
            return;
        }
        if (personagens.containsKey(nome)) {
            System.out.println("[ERRO] Já existe um herói com este nome.");
            return;
        }

        System.out.println("Escolha a classe do seu herói:");
        System.out.println("1. Guerreiro (Vida alta, Defesa alta)");
        System.out.println("2. Mago (Dano alto, Vida baixa)");
        System.out.print("Escolha a classe: ");

        try {
            int tipo = scanner.nextInt();
            scanner.nextLine();

            Jogador novoHeroi;
            if (tipo == 1) {
                novoHeroi = new Guerreiro(nome);
            } else if (tipo == 2) {
                novoHeroi = new Mago(nome);
            } else {
                System.out.println("[ERRO] Classe inválida!");
                return;
            }

            personagens.put(nome, novoHeroi);
            System.out.println("\nHerói '" + nome + "' da classe " + novoHeroi.getClass().getSimpleName() + " criado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Escolha de classe inválida. Digite 1 ou 2.");
            scanner.nextLine();
        }
    }
    private static void verPersonagens() {
        System.out.println("\n--- Seus Heróis Criados ---");
        if (personagens.isEmpty()) {
            System.out.println("Nenhum herói foi criado ainda. Use a opção 1 para criar.");
        } else {
            for (Jogador heroi : personagens.values()) {
                System.out.println(heroi.toString());
            }
        }
    }
    private static void iniciarBatalha() {
        if (personagens.isEmpty()){
            System.out.println("[ERRO] Você precisa criar um herói antes de poder lutar!");
            return;
        }

        System.out.print("Digite o nome do seu herói para lutar: ");
        String nomeHeroi = scanner.nextLine();

        Jogador heroi = personagens.get(nomeHeroi);
        if (heroi == null || heroi instanceof Inimigo) {
            System.out.println("[ERRO] Herói não encontrado ou inválido.");
            return;
        }

        Jogador inimigo = criarInimigoAleatorio();
        System.out.println("\nUm " + inimigo.getNome() + " selvagem aparece!");
        pausa();

        System.out.println("========= BATALHA INICIADA =========");
        System.out.printf("%s VS %s%n", heroi.getNome(), inimigo.getNome());
        System.out.println("====================================");

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            turnoDoHeroi(heroi, inimigo);
            if (!inimigo.estaVivo()) break;

            pausa();

            System.out.println("\n--- Turno de " + inimigo.getNome() + " ---");
            inimigo.atacar(heroi);
            System.out.println("Vida do seu herói: " + heroi.getVidaAtual());
            if (!heroi.estaVivo()) break;

            pausa();
        }

        System.out.println("\n========= FIM DA BATALHA =========");
        if (heroi.estaVivo()) {
            System.out.println("🎉 VITÓRIA! Você derrotou o " + inimigo.getNome() + "! 🎉");
            heroi.ganharXp(50);
        } else {
            System.out.println("DERROTA... Você foi vencido. Tente novamente.");
        }
        System.out.println("====================================");
    }

    private static void turnoDoHeroi(Jogador heroi, Jogador inimigo) {
        System.out.println("\n--- Seu Turno ---");
        System.out.println(heroi);
        System.out.println("VIDA DO INIMIGO: " + inimigo.getVidaAtual());
        System.out.println("\nO que você vai fazer?");
        System.out.println("1. Atacar");
        System.out.println("2. Defender");
        System.out.println("3. Usar Poção de Cura");
        System.out.println("4. Ataque Especial");
        System.out.print("Escolha sua ação: ");

        try {
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1: heroi.atacar(inimigo); break;
                case 2: heroi.defender(); break;
                case 3: heroi.usarPocao(); break;
                case 4: heroi.ataqueEspecial(inimigo); break;
                default: System.out.println("Ação inválida! Você perdeu o turno."); break;
            }
        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Ação inválida! Você perdeu o turno.");
            scanner.nextLine();
        }
    }

    private static Jogador criarInimigoAleatorio() {
        int tipoInimigo = random.nextInt(3);
        switch (tipoInimigo) {
            case 0: return new Inimigo("Goblin Ladrão", 70, 8, 10);
            case 1: return new Inimigo("Orc Bruto", 140, 15, 13);
            case 2: return new Inimigo("Lobo das Sombras", 90, 12, 12);
            default: return new Inimigo("Goblin Ladrão", 70, 8, 10);
        }
    }

    private static void pausa() {
        System.out.println("\n(Pressione ENTER para continuar...)");
        scanner.nextLine();
    }
}