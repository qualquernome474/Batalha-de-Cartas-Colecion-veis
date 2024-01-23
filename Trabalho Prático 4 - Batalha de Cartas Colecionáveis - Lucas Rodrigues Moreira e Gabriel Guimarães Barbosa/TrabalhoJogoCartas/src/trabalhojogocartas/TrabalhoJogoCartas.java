package trabalhojogocartas;

import java.util.Scanner;
//Nomes: Lucas Rodrigues Moreira e Gabriel Guimnarães Barbosa

public class TrabalhoJogoCartas {

    public static void main(String[] args) {
        //Criamos o Scanner para que o programa possa receber valores vindo dos usuários
        Scanner entrada = new Scanner(System.in);
        
        //Criamos a variável que receberá a resposta do usuário
        int t = 0;

        while (t != 1) {
            //Mostramos um menu de opções e salvamos o valor informado na variável t
            System.out.println("Você quer: ");
            System.out.println("1 - iniciar jogo: ");
            System.out.println("2 - como funciona o jogo: ");
            t = entrada.nextInt();
            
            //Se o jogador escolha começar o jogo
            if (t == 1) {
                //Criamos a variável indice, que armazenará quantas cartas que devem ser sorteadas para os jogadores e em seguida invocamos o método sorteiacata da classe Jogatina
                int indice = 5;
                Jogatina.sorteiacarta(indice);

            }
            //Se o jogador escolha ver as instruções de como se joga o jogo
            if (t == 2) {
                //Invocamos o método instrucoes
                instrucoes();
            }
            //Se o jogador infromar uma opção diferente das disponíveis
            if(t!=1 && t!=2){
                System.out.println("índice não disponível");
            
            }
        }
    }

    public static void instrucoes() {
        //Mostramos um texto explicando como funciona o jogo

        System.out.println("Distribuição inicial de cartas: Cada jogador receberá aleatoriamente 5 cartas da coleção para formar sua mão inicial.");
        System.out.println("");
        System.out.println("Posicionamento de monstros: A cada rodada os jogadores podem selecionar entre os monstros disponíveis nas cartas na mão e "
                + "posicionar o monstro no tabuleiro. Os jogadores podem manter posicionados em seu tabuleiro no máximo 5 monstros. \n"
                + "Caso o limite de 5 monstros seja alcançado, o jogador não poderá posicionar mais monstros até que algum seja removido/destruído. "
                + "Ao posicionar um monstro no tabuleiro, o usuário pode escolher se o mesmo será inserido em estado de ataque ou defesa. O valor atual do"
                + " monstro depende exclusivamente do estado ao qual está no momento.\n"
                + "\n"
                + "a. Ex: Um Monstro com 2500 de ataque e 1700 de defesa. Se o mesmo está em estado de ataque vamos considerar o valor de 2500. Se o monstro "
                + "está em estado de defesa vamos considerar o valor de 1700.\n"
                + "b. Um monstro pode realizar ataques apenas se estiver em estado de ataque");

        System.out.println("");
        System.out.println("Limite de cartas na mão: Cada jogador pode ter no máximo 10 cartas em sua mão. Caso esse limite seja ultrapassado, o jogador "
                + "deve descartar cartas suficientes para não exceder o limite.");
        System.out.println("");

        System.out.println("Realização de rodadas: O jogo é realizado em rodadas. A cada rodada, os jogadores recebem uma nova carta em suas mãos e"
                + " escolhem suas ações.");
        System.out.println("");

        System.out.println("Ataques: Durante um ataque, o jogador deve escolher qual monstro do oponente deseja atacar. Se o monstro do oponente estiver"
                + " na posição de ataque, o oponente perde pontos relativos à diferença de ataque entre os monstros. Se o monstro do oponente estiver na"
                + " posição de defesa e a defesa for menor que o ataque do jogador, o monstro é destruído e o oponente não perde\n"
                + "pontos de ataque. Caso a defesa seja maior que o ataque, o jogador atual(“atacante”) perde pontos.\n"
                + "a. Quando um monstro realiza um ataque, o usuário ficará impedido de alterar o estado do mesmo na rodada corrente. Podendo alterar "
                + "o estado apenas na sua próxima rodada.");
        System.out.println("");
        System.out.println("Restrição de ataque: Cada monstro posicionado no tabuleiro só pode atacar uma vez a cada rodada.");
        System.out.println("Cada jogador deve iniciar o jogo com 10000 pontos. Caso a pontuação do jogador seja igual a 0 (zero), o oponente será o vencedor."
                + " Caso o baralho não tenham mais cartas, o jogador com a maior pontuação será considerado o vencedor.");
        System.out.println("");
        System.out.println("Caso o usuário não tenha posicionado nenhum monstro no tabuleiro, seu adversário poderá infringir dano direto aos seus pontos.\n"
                + "a. Os ataques podem ser realizados apenas a partir da segunda rodada.");
        
        System.out.println("Quando o jogo solicitar que o usuário digite o indice de alguma carta, o padrao de resposta que usuário deverá usar será, "
                + "por exemplo, em um conjunto de cartas fenix flamejante, arqueiro mágico, dragão do trovão, caso o usuário queira selecionar a carta "
                + "fenix flamejante ele deverá digitar 1 como índice, 2 para a carta arqueiro mágico, etc.");
        System.out.println("");
    }

}
