package trabalhojogocartas;

//imports que serão usados na aplicação
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


public class Jogatina {
    
    //Criamos as variáveis que serão usadas na aplicação
    
    //Variável que será usada para acessar a classe FakeBD e utilizar seus métodos
    static FakeBD meuBanco = new FakeBD();
    
    //Variável quer armazenará a rodada na qual o jogo está
    static int rodada = 1;
    
    //Variável quer registrará se o vetor pontuacao foi inicializado
    static int controle = 0;
    
    //Variáveis que armazenam as pontuações dos jogadores
    static int pontosjogador1 = 0;
    static int pontosjogador2 = 0;
    
    //Vetor que armazena as pontuações dos jogadores
    static Vector<Integer> pontuacao = new Vector<>();

    //Método que mostrará a mão do jogador que escolher a opção de mostrar a sua mão de cartas
    public static void mostramaos(Vector<cartas> maojogador1, Vector<cartas> maojogador2, int jogador) {
        
        //Se o jogador for igual à 1
        if (jogador == 1) {
            
            //Mostramos uma mensagem mostrando de qual jogador é a mão mostrada
            System.out.println("Mão do jogador 1: ");
            
            //Criamos um laço de repetição para mostrar todas as cartas que estão na mão do jogador
            for (int i = 0; i < maojogador1.size(); i++) {
                
                //Mostramos para o jogador a carta referente ao índice
                System.out.print(maojogador1.get(i));
                
                //Para separar os nomes das cartas da mão, usamos a vírgula entre os nomes, exceto no início da primeira carta e no fim da última carta
                if (i < maojogador1.size() - 1) {
                    System.out.print(", ");
                }
            }
            //Soltamos uma linha para que os textos não fiquem embolados
            System.out.println("");

        }
        
        //Se o jogador for igual à 2
        if (jogador == 2) {
            
            //Mostramos uma mensagem mostrando de qual jogador é a mão mostrada
            System.out.println("Mão do jogador 2:");
            
            //Criamos um laço de repetição para mostrar todas as cartas que estão na mão do jogador
            for (int i = 0; i < maojogador2.size(); i++) {
                
                //Mostramos para o jogador a carta referente ao índice
                System.out.print(maojogador2.get(i));

                //Para separar os nomes das cartas da mão, usamos a vírgula entre os nomes, exceto no início da primeira carta e no fim da última carta
                if (i < maojogador2.size() - 1) {
                    System.out.print(", ");
                }
            }
            
            //Soltamos uma linha para que os textos não fiquem embolados
            System.out.println("");
        }

    }
    
    //Método que lê o arquivo .csv das cartas, as insere no baralho, invoca o método da classe FakeDB que retira a linha em branco e a que sorteia os baralhos iniciais  
    public static void sorteiacarta(int indice) {
        
        //Criamos os vetores que armazenarão as mãos dos jogadores
        Vector<cartas> maojogador1 = new Vector<>();
        Vector<cartas> maojogador2 = new Vector<>();
        
        //Criamos um file referenciando o link de onde o arquivo .csv está localizado no computador, caso o arquivo .csv esteja em outro endereço, altere o link.
        File arquivoCartas = new File("C:\Users\Admin\Desktop\Batalha-de-Cartas-Colecion-veis-main\Batalha-de-Cartas-Colecion-veis-main\Trabalho Prático 4 - Batalha de Cartas Colecionáveis - Lucas Rodrigues Moreira e Gabriel Guimarães Barbosa\TrabalhoJogoCartas\src\trabalhojogocartas\cartas.csv");
        
        //Verificamos se o arquivo existe
        if (arquivoCartas.exists()) {
            
            //Em caso afirmativo, usamos um try catch para tratar erros na execução
            try {
                
                //Criamos um FileReader para ler o file
                FileReader marcaleitura = new FileReader(arquivoCartas);
                
                //Criamos um buffer para salvar os dados lidos no FileReader
                BufferedReader buffleitura = new BufferedReader(marcaleitura);
                
                //Criamos a variável que receberá o conteúdo lido linha por linha da planilha
                String linha;
                
                //Criamos um laço de repetição para que todas as linhas do arquivo sejam lidas
                do {
                    //Salvamos o conteúdo salvo na linha na variável linha
                    linha = buffleitura.readLine();
                    
                    //Verificamos se a linha lida tinha conteúdo, ou seja, a leitura das linhas ainda não chegou ao final do arquivo
                    if (linha != null) {
                        
                        //Usamos .split para separar as informações lidas na linha em valores separados e salvamos em um vetor
                        String dadoslinha[] = linha.split(";");
                        
                        //Criamos as variáveis que receberão os valores do ataque e da defesa da carta, após esses valores passarem por um parseInt para tranformar os respectivos dados lidos em números inteiros
                        int ataque = Integer.parseInt(dadoslinha[2]);
                        int defesa = Integer.parseInt(dadoslinha[3]);
                        
                        //Criamos uma variável do tipo carta e salvamos nela os dados da carta lida no arquivo.csv
                        cartas cartas = new cartas(dadoslinha[0], dadoslinha[1], ataque, defesa, dadoslinha[4]);
                        
                        //Invocamos o método inserecartasorteada da classe FakeBD para que a carta seja adicionada ao baralho
                        meuBanco.inserecartasorteada(cartas);

                    }
                //Condição de repetição do laço de repetição
                } while (linha != null);
                
                //Invocamos o método removelinhaembranco da classe FakeBD para que a linha em rbanco do arquivo .vcs seja removida e não atrapalhe os jogadores
                meuBanco.removelinhaembranco();
                
                //Criamos e inicializamos a variável que salvará qual o jogador que está jogando
                int jogador = 1;
                
                //Invocamos o método embaralhar da classe FakeBD para que a mão inicial do jogador 1 seja sorteada
                maojogador1 = meuBanco.embaralhar(jogador, indice);
                
                //Atualizamos a variável jogador para sortearmos sua mão incial
                jogador = 2;
                
                //Invocamos o método embaralhar da classe FakeBD para que a mão inicial do jogador 2 seja sorteada
                maojogador2 = meuBanco.embaralhar(jogador, indice);
                
            //Caso o arquivo não seja encontrado no link que foi especificado, a seguinte mensagem é mostrada ao usuário
            } catch (FileNotFoundException ex) {
                System.err.println("O arquivo nao existe no caminho especificado");
            
            //Caso ocorra algum problema na leitura do arquivo .csv, a seguinte mensagem é mostrada ao usuário
            } catch (IOException ex) {
                System.out.println("Problema na leitura dos dados do arquivo");
            }
            
        //Caso o File arquivocartas não exista, ou seja, o link informado para o arquivo .csv está incorreto, a seguinte mensagem é mostrada ao usuário
        } else {

            System.err.println("O arquivo nao existe no caminho especificado");
        }
        
        //Invocamos o método no qual criará as opções as quais os jogadores vão interagir com o jogo 
        jogo(maojogador1, maojogador2);

    }
    
    //Método onde o jogo será realizado
    public static void jogo(Vector<cartas> maojogador1, Vector<cartas> maojogador2) {
        
        //Criamos o Scanner para que os jogadores possam informar valores para a aplicação
        Scanner entrada = new Scanner(System.in);
        
        //Mostramos uma mensagem avisando que as mãos iniciais são foram sorteadas e entregues para os jogadores
        System.out.println("Mãos iniciais já sorteadas e entregues aos jogadores");
        
        //Verificamos se os valores dos pontos dos jogadores ainda não foram inicializados, se sim, os inicializamos com 10000 pontos cada
        if (pontosjogador1 == 0 && pontosjogador2 == 0) {
            pontosjogador1 = 10000;
            pontosjogador2 = 10000;
        }
        
        //Verificamos se o vetorque armazena os valores dos pontos dos jogadores ainda não foi inicializado, se sim, adicionamos os valores dos pontos dos jogadores ao vetor
        if (controle == 0) {
            pontuacao.add(10000);
            pontuacao.add(10000);
            
            //Atualizamos o valor da variável controle
            controle = 1;
        }
        
        //Criamos os vetores que receberão as cartas jogadas pelos jogadores, seus estados( ataque ou defesa) e suas situações (podem atacar ou não)
        Vector<cartas> cartasjogadasjogador1 = new Vector<>();
        Vector<String> estadoscartasjogadasjogador1 = new Vector<>();
        Vector<cartas> cartasjogadasjogador2 = new Vector<>();
        Vector<String> estadoscartasjogadasjogador2 = new Vector<>();
        Vector<Integer> situacaocartasjogadasjogador1 = new Vector<>();
        Vector<Integer> situacaocartasjogadasjogador2 = new Vector<>();
        
        //Criamos a variável x, que receberá a opção escolhida pelo jogador
        int x = -1;
        
        //Criamos e analisamos a variável que armazena qual jogador está jogando
        int jogador = 1;
        
        //Criamos o vetor que registrará se os jogadores podem colocar equipamentos na rodada
        Vector<Integer> colocouequipamento = new Vector<>();
        
        //Inicializamos o vetor colocouequipamento
        colocouequipamento.add(0);
        colocouequipamento.add(0);
        
        //Criamos um laço de repetição que repetirá enquanto nenhum jogador tenha perdido
        while (pontosjogador1 > 0 && pontosjogador2 > 0) {
            
            //Analizamos se o jogador não tenha escolhido passar sua vez
            if (x != 4) {
                
                //Enquanto o jogador não tenha escolhido passar sua vez repetimos o conteúdo do laço de repetição
                while (x != 4) {
                    
                    //Se o jogador escolheu passar sua vez, usamos o break para parar a execução do laço de repetição
                    if (x == 4) {

                        break;
                    }
                    
                    //Analisamos se nenhum jogador perdeu
                    if (pontosjogador1 > 0 && pontosjogador2 > 0) {
                        //Em caso afirmativo, mostramos um menu com as opções disponíveis para os jogadores
                        
                        System.out.println("");
                        System.out.println("Rodada: " + rodada);

                        System.out.println("Pontuação:");
                        System.out.println("Jogador 1: " + pontuacao.get(0));
                        System.out.println("Jogador 2: " + pontuacao.get(1));
                        System.out.println("");

                        System.out.println("Jogador " + jogador + ", você quer:");
                        System.out.println("-1-ver cartas nas mãos: ");
                        System.out.println("0-Descartar uma carta da mão: ");
                        System.out.println("1-posicionar monstro:");
                        System.out.println("2-adicionar equipamento:");
                        System.out.println("3-mostrar area de cartas:");
                        System.out.println("4-passar turno:");
                        System.out.println("5-ver detalhes sobre as cartas nas mãos: ");
                        
                        //Analisamos se o jogador já jogou alguma carta, se sim, mostramos para ele a opção de alterar estados de cartas
                        if (jogador == 1) {
                            if (cartasjogadasjogador1.isEmpty() == false) {
                                System.out.println("6-alterar estado de cartas:");

                            }
                        }

                        if (jogador == 2) {
                            if (cartasjogadasjogador2.isEmpty() == false) {
                                System.out.println("6-alterar estado de cartas:");

                            }
                        }
                        
                        //Analisamos se o jogador já jogou alguma carta e o jogo já passou da segunda rodada, se sim, mostramos para ele a opção de atacar o oponente
                        if (jogador == 1 && rodada > 2) {
                            if (cartasjogadasjogador1.isEmpty() == false) {

                                System.out.println("7-atacar:");
                            }
                        }

                        if (jogador == 2 && rodada > 2) {
                            if (cartasjogadasjogador2.isEmpty() == false) {

                                System.out.println("7-atacar:");
                            }
                        }
                        
                        //Salvamos a opção escolhida pelo usuário na variável x
                        x = entrada.nextInt();
                        
                        //Se a opção digitada não é uma opção válida, uma mensagem é mostrada para o jogador
                        if (x < -1 || x > 7) {
                            System.out.println("opção selecionada não disponível");
                        }
                        
                        //Caso o jogador escolha descartar um carta da mão
                        if (x == 0) {
                            
                            //Mostramos para o jogador uma mensagem solicitando que ele informe o índice da carta que ele quer descartar
                            System.out.println("Digite a carta que você quer descartar");
                            
                            //Salvamos o valor informado na variável cartadescartada
                            int cartadescartada = entrada.nextInt();
                            
                            //Diminuímos o valor da variável cartadescartada em 1, pois os índices de vetores começam em 0
                            cartadescartada--;
                            
                            //Caso o jogador seja o jogador 1 e o índice informado for válido 
                            if (jogador == 1 && cartadescartada >= 0 && cartadescartada < maojogador1.size()) {
                                
                                //informamos ao usuário que a carta foi descartada
                                System.out.println("O jogador 1 descartou a carta " + maojogador1.get(cartadescartada));
                                
                                //Invocamos o método insere carta sorteada da classe fakeDB para que a carta retorne para o baralho
                                meuBanco.inserecartasorteada(maojogador1.get(cartadescartada));
                                
                                //Removemos a carta da mão do jogador 1
                                maojogador1.remove(cartadescartada);
                                
                            //Caso o jogador seja o jogador 1 e o índice informado seja inválido 
                            } else {
                                //Caso o índice seja negativo ou maior que a quantidade de cartas da mão do jogador
                                if ((jogador == 1 && cartadescartada < 0) || (jogador == 1 && cartadescartada >= maojogador1.size())) {
                                    //Informamos ao usuário que o índice informado não é válido
                                    System.out.println("Indice não disponivel");
                                }
                            }
                            
                            //Caso o jogador seja o jogador 2 e o índice informado for válido 
                            if (jogador == 2 && cartadescartada >= 0 && cartadescartada < maojogador2.size()) {
                                //informamos ao usuário que a carta foi descartada 
                                System.out.println("O jogador 2 descartou a carta " + maojogador2.get(cartadescartada));
                                
                                //Invocamos o método insere carta sorteada da classe fakeDB para que a carta retorne para o baralho
                                meuBanco.inserecartasorteada(maojogador2.get(cartadescartada));
                                
                                //Removemos a carta da mão do jogador 2
                                maojogador2.remove(cartadescartada);
                                
                            //Caso o jogador seja o jogador 2 e o índice informado seja inválido 
                            } else {
                                
                                //Caso o índice seja negativo ou maior que a quantidade de cartas da mão do jogador
                                if ((jogador == 2 && cartadescartada < 0) || (jogador == 2 && cartadescartada >= maojogador2.size())) {
                                    
                                    //Informamos ao usuário que o índice informado não é válido
                                    System.out.println("Indice não disponivel");
                                }
                            }

                        }
                        
                        //Caso o jogador escolha ver as cartas que estão na sua mão, invocamos o método mostramaos
                        if (x == -1) {

                            mostramaos(maojogador1, maojogador2, jogador);

                        }
                        
                        //Caso o jogador escolha ver informações sobre as cartas que estão em suas mãos, invocamos o método descricaomao 
                        if (x == 5) {
                            descricaomao(maojogador1, maojogador2, jogador);
                        }
                        
                        //Caso o jogador escolha alterar o estado das cartas
                        if (x == 6) {
                            
                            //Caso o jogador seja o jogador 1 e o jogador tenha cartas colocadas na área de cartas, invocamos o método alteraestado e atualizamos o vetor estadoscartasjogador1
                            if (jogador == 1 && cartasjogadasjogador1.isEmpty() == false) {
                                
                                estadoscartasjogadasjogador1 = alteraestado(jogador, cartasjogadasjogador1, estadoscartasjogadasjogador1, situacaocartasjogadasjogador1, cartasjogadasjogador2, estadoscartasjogadasjogador2, situacaocartasjogadasjogador2);
                                
                            
                            } else {
                                //Caso o jogador seja o jogador 1 e o jogador não tenha cartas colocadas na área de cartas, informamos ao jogador que ele não possuí cartas para para alterar estados
                                if (jogador == 1 && cartasjogadasjogador1.isEmpty() == true) {
                                    System.out.println("opção indisponível");
                                }

                            }
                            
                            //Caso o jogador seja o jogador 2 e o jogador tenha cartas colocadas na área de cartas, invocamos o método alteraestado e atualizamos o vetor estadoscartasjogador2
                            if (jogador == 2 && cartasjogadasjogador2.isEmpty() == false) {
                                estadoscartasjogadasjogador2 = alteraestado(jogador, cartasjogadasjogador1, estadoscartasjogadasjogador1, situacaocartasjogadasjogador1, cartasjogadasjogador2, estadoscartasjogadasjogador2, situacaocartasjogadasjogador2);
                            
                            
                            } else {
                                //Caso o jogador seja o jogador 2 e o jogador não tenha cartas colocadas na área de cartas, informamos ao jogador que ele não possuí cartas para para alterar estados
                                if (jogador == 2 && cartasjogadasjogador2.isEmpty() == true) {
                                    System.out.println("opção indisponível");
                                }

                            }

                        }
                        
                        //Caso o jogador escolha atacar o oponente e o jogo estja em uma rodada maior que 2
                        if (x == 7 && rodada > 2) {
                            
                            //Se o jogador for o jogador 1
                            if (jogador == 1) {
                                
                                //Caso o jogador 1 possui cartas que podem atacar na rodada
                                if (situacaocartasjogadasjogador1.contains(0) == true) {
                                    
                                    //Invocamos o método ataque da Classe Ataque e atualizamos o vetor pontuacao
                                    pontuacao = Ataque.ataque(pontuacao, pontosjogador1, pontosjogador2, jogador, cartasjogadasjogador1, cartasjogadasjogador2, estadoscartasjogadasjogador1, estadoscartasjogadasjogador2, situacaocartasjogadasjogador1, situacaocartasjogadasjogador2);
                                    
                                    //Verificamos se algum jogador ficou com pontuação menor igual à 0, ou seja se alguém ganhou
                                    if (pontuacao.get(0) <= 0 || pontuacao.get(1) <= 0) {
                                        
                                        //Em caso afirmativo, mostramos um menu com as novas pontuações dos jogadores
                                        System.out.println("");
                                        System.out.println("Pontuação:");
                                        System.out.println("Jogador 1: " + pontuacao.get(0));
                                        System.out.println("Jogador 2: " + pontuacao.get(1));
                                        System.out.println("");
                                        
                                        //Analisamos qual jogador ganhou e informamos qual jogador ganhou
                                        if (pontuacao.get(1) <= 0) {
                                            System.out.println("O jogador 1 ganhou");
                                        }
                                        if (pontuacao.get(0) <= 0) {
                                            System.out.println("O jogador 2 ganhou");
                                        }
                                        
                                        //E, já que tivemos um vencedor, finalizamos a execução do programa
                                        System.exit(0);
                                    }
                                    
                                //Caso o jogador 1 não possua cartas que podem atacar na rodada
                                } else {
                                    
                                    //Caso o campo de cartas do jogador 1 esteja vazio, informamos ao jogador que a opção informada não está disponível
                                    if (cartasjogadasjogador1.isEmpty() == true) {
                                        System.out.println("Opção indisponível");
                                    } else {
                                        
                                        //Caso o campo de cartas do jogador 1 não esteja vazio, informamos ao jogador que ele não possuí cartas que podem atacar na rodada
                                        System.out.println("O jogador " + jogador + " não possui cartas que podem atacar");
                                    }
                                }

                            }
                            
                            //Se o jogador for o jogador 2
                            if (jogador == 2) {
                                
                                //Caso o jogador 2 possui cartas que podem atacar na rodada
                                if (situacaocartasjogadasjogador2.contains(0) == true) {
                                    
                                    //Invocamos o método ataque da Classe Ataque e atualizamos o vetor pontuacao
                                    pontuacao = Ataque.ataque(pontuacao, pontosjogador1, pontosjogador2, jogador, cartasjogadasjogador1, cartasjogadasjogador2, estadoscartasjogadasjogador1, estadoscartasjogadasjogador2, situacaocartasjogadasjogador1, situacaocartasjogadasjogador2);
                                    
                                    //Verificamos se algum jogador ficou com pontuação menor igual à 0, ou seja se alguém ganhou
                                    if (pontuacao.get(0) <= 0 || pontuacao.get(1) <= 0) {
                                        
                                        //Em caso afirmativo, mostramos um menu com as novas pontuações dos jogadores
                                        System.out.println("");
                                        System.out.println("Pontuação:");
                                        System.out.println("Jogador 1: " + pontuacao.get(0));
                                        System.out.println("Jogador 2: " + pontuacao.get(1));
                                        System.out.println("");
                                        
                                        //Analisamos qual jogador ganhou e informamos qual jogador ganhou
                                        if (pontuacao.get(1) <= 0) {
                                            System.out.println("O jogador 1 ganhou");
                                        }
                                        if (pontuacao.get(0) <= 0) {
                                            System.out.println("O jogador 2 ganhou");
                                        }
                                        
                                        //E, já que tivemos um vencedor, finalizamos a execução do programa
                                        System.exit(0);
                                    }
                                    
                                //Caso o jogador 2 não possua cartas que podem atacar na rodada
                                } else {
                                    
                                    //Caso o campo de cartas do jogador 2 esteja vazio, informamos ao jogador que a opção informada não está disponível
                                    if (cartasjogadasjogador2.isEmpty() == true) {
                                        System.out.println("Opção indisponível");
                                    } else {
                                        
                                        //Caso o campo de cartas do jogador 2 não esteja vazio, informamos ao jogador que ele não possuí cartas que podem atacar na rodada
                                        System.out.println("O jogador " + jogador + " não possui cartas que podem atacar");
                                    }
                                }

                            }
                        
                        //Caso o jogador escolha atacar o oponente e o jogo não estja em uma rodada maior que 2
                        } else {
                            
                            if (x == 7 && rodada <= 2) {
                                
                                //Informamos que a opção informada não está dsiponível
                                System.out.println("opção indisponível");
                            }
                        }
                        
                        //Caso o jogador escolha passar sua vez
                        if (x == 4) {
                            
                            //Se o jogador seja o jogador 1
                            if (jogador == 1) {
                                
                                //O vetor colocouequipamento é restaurado para 0 no índice 0(índice do jogador 1)
                                colocouequipamento.set(0, 0);
                                
                                //Criamos um laço de repetição para que passe sobre todas as cartas que o jogador colocaou na área de cartas
                                for (int i = 0; i < situacaocartasjogadasjogador1.size(); i++) {
                                    
                                    //Caso o estado da carta do índice esteja no estado de ataque, "setamos" como 0(pode atacar) no índice i 
                                    if (estadoscartasjogadasjogador1.get(i).equalsIgnoreCase("ataque")) {
                                        situacaocartasjogadasjogador1.set(i, 0);
                                    }
                                    
                                    //Caso o estado da carta do índice esteja no estado de defesa, "setamos" como 1(não pode atacar) no índice i 
                                    if (estadoscartasjogadasjogador1.get(i).equalsIgnoreCase("defesa")) {
                                        situacaocartasjogadasjogador1.set(i, 1);
                                    }
                                }
                            }
                            
                            //Se o jogador seja o jogador 2
                            if (jogador == 2) {
                                
                                //O vetor colocouequipamento é restaurado para 0 no índice 1(índice do jogador 2)
                                colocouequipamento.set(1, 0);
                                
                                //Criamos um laço de repetição para que passe sobre todas as cartas que o jogador colocaou na área de cartas
                                for (int i = 0; i < situacaocartasjogadasjogador2.size(); i++) {
                                    
                                    //Caso o estado da carta do índice esteja no estado de ataque, "setamos" como 0(pode atacar) no índice i
                                    if (estadoscartasjogadasjogador2.get(i).equalsIgnoreCase("ataque")) {
                                        situacaocartasjogadasjogador2.set(i, 0);
                                    }
                                    
                                    //Caso o estado da carta do índice esteja no estado de defesa, "setamos" como 1(não pode atacar) no índice i 
                                    if (estadoscartasjogadasjogador2.get(i).equalsIgnoreCase("defesa")) {
                                        situacaocartasjogadasjogador2.set(i, 1);
                                    }
                                }
                            }
                            //A rodada é passda para à próxima
                            rodada++;
                            
                            //Criamos o vetor que receberá a carta que os jogadores recebem quando passam a sua vez
                            Vector<cartas> vetorcartasorteada = new Vector<>();
                            
                            //Se o jogador for o jogador 1 e ainda tenha espaço nas mãos(que podem ser colocadas no máximo 10 cartas)
                            if (jogador == 1 && maojogador1.size() <= 9) {
                                
                                //Invocamos o método refil da classe FakeBD e salvamos no vetor vetorcartasorteada
                                vetorcartasorteada = meuBanco.refil(maojogador1, maojogador2, jogador, pontosjogador1, pontosjogador2);
                                
                                //E adicionamos a carta sorteada(que está no índice 0 pois índices de veotres começam em 0 e foi sorteado uma carta) à mão do jogador 1
                                maojogador1.add(vetorcartasorteada.get(0));
                            }
                            
                            //Se o jogador for o jogador 2 e ainda tenha espaço nas mãos(que podem ser colocadas no máximo 10 cartas)
                            if (jogador == 2 && maojogador2.size() <= 9) {
                                
                                //Invocamos o método refil da classe FakeBD e salvamos no vetor vetorcartasorteada
                                vetorcartasorteada = meuBanco.refil(maojogador1, maojogador2, jogador, pontosjogador1, pontosjogador2);
                                
                                //E adicionamos a carta sorteada(que está no índice 0 pois índices de veotres começam em 0 e foi sorteado uma carta) à mão do jogador 2
                                maojogador2.add(vetorcartasorteada.get(0));
                            }
                            
                            //Limpamos o veotr vetorcartasorteada
                            vetorcartasorteada.clear();
                            
                            //Finalizamos a execução do laço de repetição
                            break;
                        }
                        
                        
                        //Caso o jogador escolha adicionar monstro ao campo de cartas
                        if (x == 1) {
                            
                            //Inicializamos a variável que receberá o estado da carta informada pelo jogador
                            String estado = null;
                            
                            //Criamos a variável que receberá o índice informado pelo jogador
                            int q = 0;
                            
                            //Caso o jogador seja o jogador 1 e ainda tenha espaço na área de cartas(que podem ser colocadas no máximo 10 cartas)
                            if (jogador == 1 && cartasjogadasjogador1.size() < 5) {
                                //Pedimos ao jogador que informe a carta desejada
                                System.out.println("Digite a posição da carta na mão");
                                
                                //Salvamos o valor informdo na variável q e subtraímos q em 1, pois índices de vetores começam em 0
                                q = entrada.nextInt();
                                q--;
                                
                                //Caso o índice informado for válido e a carta informada for do tipo monstro
                                if (q >= 0 && q <= maojogador1.size() - 1 && maojogador1.get(q).getTipo().equalsIgnoreCase("monstro")) {
                                    
                                    //Perguntamos ao jogador o estado da carta 
                                    System.out.println("Você que colocar a carta no modo ataque ou defesa?");
                                    
                                    //Salvamos o estado informado à variável estado
                                    estado = entrada.next();
                                    
                                    //Caso o estado informado for válido (ataque ou defesa)
                                    if (estado.equalsIgnoreCase("ataque") || estado.equalsIgnoreCase("defesa")) {
                                        
                                        //Invocamos o método adicionaraocampo da classe FakeBD e atualizamos o vetor cartascartasjogador1
                                        cartasjogadasjogador1 = meuBanco.adicionaraocampo(q, jogador, maojogador1);
                                        
                                        //Invocamos o método adicionaestado da classe FakeBD e atualizamos o vetor estadoscartasjogador1
                                        estadoscartasjogadasjogador1 = meuBanco.adicionarestado(estado, jogador);
                                        
                                        //Informamos ao usuário que a carta foin posicionada e seu estado
                                        System.out.println("O jogador 1 colocou a carta " + maojogador1.get(q) + " na posição de " + estado);
                                        
                                        //Removemos a carta posicionada da mão do jogador
                                        maojogador1.remove(q);
                                        
                                        //Caso o estado da carta selecionada(última carta do campo) seja ataque, definimos como 0(pode atacar) a situação da carta  
                                        if (estadoscartasjogadasjogador1.get(cartasjogadasjogador1.size() - 1).equalsIgnoreCase("ataque")) {
                                            situacaocartasjogadasjogador1.add(0);
                                        }
                                        
                                        //Caso o estado da carta selecionada(última carta do campo) seja defesa, definimos como 1(não pode atacar) a situação da carta 
                                        if (estadoscartasjogadasjogador1.get(cartasjogadasjogador1.size() - 1).equalsIgnoreCase("defesa")) {
                                            situacaocartasjogadasjogador1.add(1);
                                        }
                                    //Caso o estado informado não seja ataque ou defesa, informamos ao jogador que o estado informado não é válido    
                                    } else {
                                        System.out.println("O modo escolhido não é válido");
                                    }
                                
                                
                                } else {
                                    //Caso o índice informado for inválido, informamos isso ao jogador
                                    if (q < 0 || q > maojogador1.size() - 1) {
                                        System.out.println("O índice escolhido não é válido");
                                    } else {
                                        
                                        //Caso a carta selecionada for do tipo equipamento, informamos isso ao jogador
                                        if (maojogador1.get(q).getTipo().equalsIgnoreCase("equipamento")) {
                                            System.out.println("A carta informada não é uma carta do tipo monstro");
                                        }
                                    }
                                }
                                
                            //Caso o jogador 1 tenha 5 cartas posicionadas, informamos ao jogador que não é possivel adicionar outro     
                            } else {
                                if (jogador == 1 && cartasjogadasjogador1.size() >= 5) {
                                    System.out.println("Não foi possível posicionar o monstro selecionado pois há 5 monstros posicionados pelo jogador " + jogador);

                                }

                            }
                            
                            //Caso o jogador seja o jogador 2 e ainda tenha espaço na área de cartas(que podem ser colocadas no máximo 10 cartas)
                            if (jogador == 2 && cartasjogadasjogador2.size() < 5) {
                                //Pedimos ao jogador que informe a carta desejada                
                                System.out.println("Digite a posição da carta na mão");
                                
                                //Salvamos o valor informdo na variável q e subtraímos q em 1, pois índices de vetores começam em 0
                                q = entrada.nextInt();
                                q--;
                                
                                //Caso o índice informado for válido e a carta informada for do tipo monstro
                                if (q >= 0 && q <= maojogador2.size() - 1 && maojogador2.get(q).getTipo().equalsIgnoreCase("monstro")) {
                                    //Perguntamos ao jogador o estado da carta 
                                    System.out.println("Você que colocar a carta no modo ataque ou defesa?");
                                    
                                    //Salvamos o estado informado à variável estado
                                    estado = entrada.next();
                                    
                                    //Caso o estado informado for válido (ataque ou defesa)
                                    if (estado.equalsIgnoreCase("ataque") || estado.equalsIgnoreCase("defesa")) {
                                        
                                        //Invocamos o método adicionaraocampo da classe FakeBD e atualizamos o vetor cartascartasjogador2
                                        cartasjogadasjogador2 = meuBanco.adicionaraocampo(q, jogador, maojogador2);
                                        
                                        //Invocamos o método adicionaestado da classe FakeBD e atualizamos o vetor estadoscartasjogador2
                                        estadoscartasjogadasjogador2 = meuBanco.adicionarestado(estado, jogador);
                                        
                                        //Informamos ao usuário que a carta foin posicionada e seu estado
                                        System.out.println("O jogador 2 colocou a carta " + maojogador2.get(q) + " na posição de " + estado);
                                        
                                        //Removemos a carta posicionada da mão do jogador
                                        maojogador2.remove(q);
                                        
                                        
                                        //Caso o estado da carta selecionada(última carta do campo) seja ataque, definimos como 0(pode atacar) a situação da carta  
                                        if (estadoscartasjogadasjogador2.get(cartasjogadasjogador2.size() - 1).equalsIgnoreCase("ataque")) {
                                            situacaocartasjogadasjogador2.add(0);
                                        }
                                        
                                        //Caso o estado da carta selecionada(última carta do campo) seja defesa, definimos como 1(não pode atacar) a situação da carta  
                                        if (estadoscartasjogadasjogador2.get(cartasjogadasjogador2.size() - 1).equalsIgnoreCase("defesa")) {
                                            situacaocartasjogadasjogador2.add(1);
                                        }
                                        
                                    //Caso o estado informado não seja ataque ou defesa, informamos ao jogador que o estado informado não é válido    
                                    } else {
                                        System.out.println("O modo escolhido não é válido");
                                    }
                                    
                                } else {
                                    
                                    //Caso o índice informado for inválido, informamos isso ao jogador
                                    if (q < 0 || q > maojogador2.size() - 1) {
                                        System.out.println("O índice escolhido não é válido");
                                    } else {
                                        
                                        //Caso a carta selecionada for do tipo equipamento, informamos isso ao jogador
                                        if (maojogador2.get(q).getTipo().equalsIgnoreCase("equipamento")) {
                                            System.out.println("A carta informada não é uma carta do tipo monstro");
                                        }
                                    }
                                }

                            } else {
                                //Caso o jogador 2 tenha 5 cartas posicionadas, informamos ao jogador que não é possivel adicionar outro
                                if (jogador == 2 && cartasjogadasjogador2.size() >= 5) {
                                    System.out.println("Não foi possível posicionar o monstro selecionado pois há 5 monstros posicionados pelo jogador " + jogador);
                                }

                            }
                        }
                        
                        //Caso o jogador escolha colocar um equipamento
                        if (x == 2) {
                            
                            //Se o jogador seja o jogador 1 
                            if (jogador == 1) {
                                
                                //Se o jogador tenha cartas posicionadas e não tenha colocado equipamento nessa rodada
                                if (cartasjogadasjogador1.isEmpty() == false && colocouequipamento.get(0) == 0) {
                                    
                                    //pedimos que o jogador informe o índice, salvamos na variável posicaoequi e diminuimos em 1 pois índices de vetores comecam em 1 
                                    System.out.println("Qual a posicao do equipamento na mao?");
                                    int posicaoequi = entrada.nextInt();
                                    posicaoequi--;
                                    
                                    //Se o índice informado é válido e a carta informada for do tipo equipamento
                                    if (posicaoequi >= 0 && posicaoequi <= maojogador1.size() - 1 && maojogador1.get(posicaoequi).getTipo().equalsIgnoreCase("equipamento")) {
                                        
                                        //perguntamos a carta que o equipamentos erá adicionado, salvamos na variável equi e diminuimos em 1 pois índices de vetores comecam em 1 
                                        System.out.println("Em qual carta você quer adicionar o equipamento?");
                                        int equi = entrada.nextInt();
                                        equi--;
                                        
                                        //Se o índice informado é válido
                                        if (equi >= 0 && equi <= cartasjogadasjogador1.size() - 1) {
                                            
                                            //Salvamos os novos valores de ataque e defesa e ataque em novas variáveis
                                            int novoataque = cartasjogadasjogador1.get(equi).getAtaque() + maojogador1.get(posicaoequi).getAtaque();
                                            int novadefesa = cartasjogadasjogador1.get(equi).getDefesa() + maojogador1.get(posicaoequi).getDefesa();
                                            
                                            //Adicionamos os novos valores de ataque e defesa na carta selecionada
                                            cartasjogadasjogador1.get(equi).setAtaque(novoataque);
                                            cartasjogadasjogador1.get(equi).setDefesa(novadefesa);
                                            
                                            //Informamos ao jogador que on equipamento for adicionado
                                            System.out.println("O equipamento " + maojogador1.get(posicaoequi) + " foi adicionado a carta " + cartasjogadasjogador1.get(equi));
                                            System.out.println("");
                                            
                                            //Removemos o equipamento adicionado da mão do jogador
                                            maojogador1.remove(posicaoequi);
                                            
                                            //Informamos para o vetor colocouequipamento que o jogador colocou equipamentos nessa rodada
                                            colocouequipamento.set(0, 1);
                                        
                                        //Se o índice da carta mosntro informado não é válido
                                        } else {
                                            System.out.println("O índice escolhido não é válido");
                                        }
                                        
                                        
                                    } else {
                                        //Se o índice da carta equipamento informado não é válido, informamos isso ao usuário
                                        if (posicaoequi < 0 || posicaoequi > maojogador1.size() - 1) {
                                            System.out.println("O índice escolhido não é válido");
                                        } else {
                                            
                                            //Se a carta selecionada for do tipo mosntro, informamos isso ao usuário
                                            if (maojogador1.get(posicaoequi).getTipo().equalsIgnoreCase("monstro")) {
                                                System.out.println("A carta informada não é uma carta do tipo equipamento");
                                            }
                                        }
                                    }
                                
                                //Se o jogador já colocou carta nessa rodada
                                } else {
                                    if (colocouequipamento.get(0) == 1) {
                                        System.out.println("O jogador 1 já usou uma carta do tipo equipamento nessa rodada");
                                    }

                                }
                                
                            //Se o jogador seja o jogador
                            } else {
                                
                                //Se jogador ainda não colocou equipamentos nessa rodada e ele tem cartas posicionadas
                                if (cartasjogadasjogador2.isEmpty() == false && colocouequipamento.get(1) == 0) {
                                    
                                    //perguntamos a carta que o equipamentos erá adicionado, salvamos na variável equi e diminuimos em 1 pois índices de vetores comecam em 1 
                                    System.out.println("Qual a posicao do equipamento na mao?");
                                    int posicaoequi = entrada.nextInt();
                                    posicaoequi--;
                                    
                                    //Se o índice informado é válido e a carta informada é do tipon equipamento
                                    if (posicaoequi >= 0 && posicaoequi <= maojogador2.size() - 1 && maojogador2.get(posicaoequi).getTipo().equalsIgnoreCase("equipamento")) {
                                        System.out.println("Em qual carta você quer adicionar o equipamento?");
                                        int equi = entrada.nextInt();
                                        equi--;
                                        
                                        //Se o ínndice infromado é válido
                                        if (equi >= 0 && equi <= cartasjogadasjogador2.size() - 1) {
                                            //Salvamos os novos valores de ataque e defesa e ataque em novas variáveis
                                            int novoataque = cartasjogadasjogador2.get(equi).getAtaque() + maojogador2.get(posicaoequi).getAtaque();
                                            int novadefesa = cartasjogadasjogador2.get(equi).getDefesa() + maojogador2.get(posicaoequi).getDefesa();
                                            
                                            
                                            //Adicionamos os novos valores de ataque e defesa na carta selecionada
                                            cartasjogadasjogador2.get(equi).setAtaque(novoataque);
                                            cartasjogadasjogador2.get(equi).setDefesa(novadefesa);
                                            
                                            //Informamos ao jogador que on equipamento for adicionado
                                            System.out.println("O equipamento " + maojogador2.get(posicaoequi) + " foi adicionado a carta " + cartasjogadasjogador2.get(equi));
                                            System.out.println("");
                                            
                                            //Removemos o equipamento adicionado da mão do jogador
                                            maojogador2.remove(posicaoequi);
                                            
                                            //Informamos para o vetor colocouequipamento que o jogador colocou equipamentos nessa rodada
                                            colocouequipamento.set(1, 1);
                                        //Se o índice da carta mosntro informado não é válido
                                        } else {
                                            System.out.println("O índice escolhido não é válido");
                                        }
                                        
                                    } else {
                                        //Se o índice da carta equipamento informado não é válido, informamos isso ao usuário
                                        if (posicaoequi < 0 || posicaoequi > maojogador2.size() - 1) {
                                            System.out.println("O índice escolhido não é válido");
                                        } else {
                                            
                                             //Se a carta selecionada for do tipo mosntro, informamos isso ao usuário
                                            if (maojogador2.get(posicaoequi).getTipo().equalsIgnoreCase("monstro")) {
                                                System.out.println("A carta informada não é uma carta do tipo equipamento");
                                            }
                                        }
                                    }
                                //Se o jogador já colocou carta nessa rodada    
                                } else {
                                    if (colocouequipamento.get(1) == 1) {
                                        System.out.println("O jogador 2 já usou uma carta do tipo equipamento nessa rodada");
                                    }

                                }

                            }
                        }
                        
                        //Caso o jogador escolah ver a área de cartas
                        if (x == 3) {
                            
                            //Mostramos m modelo de valores e o jogador 1
                            System.out.println("Modelo de saída: nome, ataque, defesa, estado");
                            System.out.println("Jogador 1:");
                            
                            //Criamos um Laço de repetição para mostrar todas sas cartas fo jogador
                            for (int i = 0; i < cartasjogadasjogador1.size(); i++) {
                                System.out.print(cartasjogadasjogador1.get(i).getNome() + "--" + cartasjogadasjogador1.get(i).getAtaque() + "--" + cartasjogadasjogador1.get(i).getDefesa() + "--" + estadoscartasjogadasjogador1.get(i));
                                System.out.println("");
                            }
                            //Mostramos jogador 2

                            System.out.println("Jogador 2:");
                            
                            //Criamos um Laço de repetição para mostrar todas sas cartas fo jogador
                            for (int i = 0; i < cartasjogadasjogador2.size(); i++) {
                                System.out.print(cartasjogadasjogador2.get(i).getNome() + "--" + cartasjogadasjogador2.get(i).getAtaque() + "--" + cartasjogadasjogador2.get(i).getDefesa() + "--" + estadoscartasjogadasjogador2.get(i));
                                System.out.println("");
                            }

                        }

                    }
                }
                
                //Quando trocamos os turnos invertemos os jogadores
                if (jogador == 1) {
                    jogador = 2;
                } else {
                    if (jogador == 2) {
                        jogador = 1;
                    }

                }
                
                //A variávelm que recebe a opção informada pelos jogadaores é reiniciada
                x = -1;
                

            }

        }
    }
    
    //Método que altera o estado de cartas nas mãos
    public static Vector<String> alteraestado(int jogador, Vector<cartas> cartasjogadasjogador1, Vector<String> estadoscartasjogadasjogador1, Vector<Integer> situacaocartasjogadasjogador1, Vector<cartas> cartasjogadasjogador2, Vector<String> estadoscartasjogadasjogador2, Vector<Integer> situacaocartasjogadasjogador2) {
        
        //Criamso o Scanner para que o método possa receber dados vindo dos jogadores
        Scanner entrada = new Scanner(System.in);
        
        //pedimos para o usuário informar o pindice, salvamos ele em uma variável e diminuímos o valor em 1
        System.out.println("Digite a carta que você deseja alterar o estado: ");
        int indice = entrada.nextInt();
        indice--;
        
        //Se o jogador informado dor o jogador 1
        if (jogador == 1) {
            
            //Se o índice é válido
            if (indice >= 0 && indice <= situacaocartasjogadasjogador1.size() - 1) {
                
                //Se a carta atacou e é do tipo mosntro, a seguinte mensagem é mostrada para os jogador
                if (situacaocartasjogadasjogador1.get(indice) == 1 && estadoscartasjogadasjogador1.get(indice).equalsIgnoreCase("ataque")) {
                    System.out.println("Não é possível alterar o estado da carta selecionada, pois quando um monstro realiza um ataque, o usuário ficará impedido de alterar o estado do mesmo na rodada corrente. Podendo alterar o estado apenas na sua próxima rodada. ");
                
                //Se a carta não atacou
                } else {
                    
                    //Invertemos os estados(de ataque para dedefsa e vice versa) e informamos ao usuáriio que isso aconteceu
                    if (estadoscartasjogadasjogador1.get(indice).equalsIgnoreCase("ataque")) {
                        estadoscartasjogadasjogador1.set(indice, "defesa");

                        System.out.println("O estado da carta " + cartasjogadasjogador1.get(indice) + " foi mudada do modo de ataque para o de defesa");
                        situacaocartasjogadasjogador1.set(indice, 1);
                    } else {
                        estadoscartasjogadasjogador1.set(indice, "ataque");
                        System.out.println("O estado da carta " + cartasjogadasjogador1.get(indice) + " foi mudada do modo de defesa para o de ataque");
                        situacaocartasjogadasjogador1.set(indice, 0);
                    }
                }
                
            //Se o índice for inválido, mostramos ao usuário que isso aconteceu    
            } else {
                System.out.println("O índice escolhido não é válido");
            }
            
            //O vetor estadoscartasjogadasjogador1 é retornado
            return estadoscartasjogadasjogador1;
        }

        //Se o jogador informado dor o jogador 2
        if (jogador == 2) {
            
            //Se o índice é válido
            if (indice >= 0 && indice <= situacaocartasjogadasjogador2.size() - 1) {
                
                //Se a carta atacou e é do tipo mosntro, a seguinte mensagem é mostrada para os jogador
                if (situacaocartasjogadasjogador2.get(indice) == 1 && estadoscartasjogadasjogador2.get(indice).equalsIgnoreCase("ataque")) {
                    System.out.println("Não é possível alterar o estado da carta selecionada, pois quando um monstro realiza um ataque, o usuário ficará impedido de alterar o estado do mesmo na rodada corrente. Podendo alterar o estado apenas na sua próxima rodada. ");
                
                //Se a carta não atacou
                } else {    
                    //Invertemos os estados(de ataque para dedefsa e vice versa) e informamos ao usuáriio que isso aconteceu
                    if (estadoscartasjogadasjogador2.get(indice).equalsIgnoreCase("ataque")) {
                        estadoscartasjogadasjogador2.set(indice, "defesa");

                        System.out.println("O estado da carta " + cartasjogadasjogador2.get(indice) + " foi mudada do modo de ataque para o de defesa");

                        situacaocartasjogadasjogador2.set(indice, 1);
                    } else {
                        estadoscartasjogadasjogador2.set(indice, "ataque");
                        System.out.println("O estado da carta " + cartasjogadasjogador2.get(indice) + " foi mudada do modo de defesa para o de ataque");
                        situacaocartasjogadasjogador2.set(indice, 0);
                    }
                }
            //Se o índice for inválido, mostramos ao usuário que isso aconteceu     
            } else {

                System.out.println("O índice escolhido não é válido");
            }
            //O vetor estadoscartasjogadasjogador2 é retornado
            return estadoscartasjogadasjogador2;
        }
        //Se o jogador for diferente de 1 e 2, retorna null
        return null;
    }
    
    //Método que descreve as cartas que estão nas mãos
    public static void descricaomao(Vector<cartas> maojogador1, Vector<cartas> maojogador2, int jogador) {
        
        //Se o jogador for o jogador 1
        if (jogador == 1) {
            
            //Mostramos um menu com as cartas, seus nomes, ataque, defesa, descrição e tipo 
            System.out.println("Cartas da mão do jogador 1");
            System.out.println("--------------------------");
            for (int i = 0; i < maojogador1.size(); i++) {
                System.out.println("nome: " + maojogador1.get(i).getNome());

                System.out.println("Descricao: " + maojogador1.get(i).getDescricao());

                System.out.println("ataque: " + maojogador1.get(i).getAtaque());

                System.out.println("defesa: " + maojogador1.get(i).getDefesa());

                System.out.println("tipo: " + maojogador1.get(i).getTipo());

                System.out.println("");

            }
        }
        
        //Se o jogador for o jogador 2
        if (jogador == 2) {
            
            //Mostramos um menu com as cartas, seus nomes, ataque, defesa, descrição e tipo 
            System.out.println("Cartas da mão do jogador 2");
            System.out.println("--------------------------");
            for (int i = 0; i < maojogador2.size(); i++) {
                System.out.println("nome: " + maojogador2.get(i).getNome());

                System.out.println("Descricao: " + maojogador2.get(i).getDescricao());

                System.out.println("ataque: " + maojogador2.get(i).getAtaque());

                System.out.println("defesa: " + maojogador2.get(i).getDefesa());

                System.out.println("tipo: " + maojogador2.get(i).getTipo());

                System.out.println("");
            }
        }

    }
}
