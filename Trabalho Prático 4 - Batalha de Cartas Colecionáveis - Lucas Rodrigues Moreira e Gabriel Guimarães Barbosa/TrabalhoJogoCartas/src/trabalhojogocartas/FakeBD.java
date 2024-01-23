package trabalhojogocartas;

import java.util.Collections;
import java.util.Vector;

public class FakeBD {
    
    //Criamos os vetores que serão usados para armazenar os dados sobre as mãos dos jogadores, sobre o baralho, as cartas jogadas pelos jogadores e seus estados(ataque ou defesa)
    private Vector<cartas> maojogador1;
    private Vector<cartas> maojogador2;
    private Vector<cartas> Baralho;
    private Vector<cartas> cartasjogadasjogador1;
    private Vector<String> estadoscartasjogadasjogador1;
    private Vector<cartas> cartasjogadasjogador2;
    private Vector<String> estadoscartasjogadasjogador2;

    //Inicializamos os vetores
    public FakeBD() {
        this.maojogador1 = new Vector<>();
        this.maojogador2 = new Vector<>();

        this.Baralho = new Vector<>();

        this.cartasjogadasjogador1 = new Vector<>();
        this.estadoscartasjogadasjogador1 = new Vector<>();
        this.cartasjogadasjogador2 = new Vector<>();
        this.estadoscartasjogadasjogador2 = new Vector<>();

    }
    
    //Método que insere a carta no baralho
    public void inserecartasorteada(cartas cartasorteada) {
        this.Baralho.add(cartasorteada);
        
    }
    //Já que no arquivo .csv tem uma linha em branco, para que nenhum jogador seja afetado, vamos remover essa linha do baralho
    public void removelinhaembranco() {
        
        this.Baralho.remove(36);

    }
    //Método que embaralha as cartas e entrega para os jogadores
    public Vector<cartas> embaralhar(int jogador, int indice) {

        if (jogador == 1) {
            //O baralho é embaralhado
            Collections.shuffle(Baralho);
            for (int i = 0; i < indice; i++) {
                
                //Salvaos a carta sorteada em uma variável do tipo carta
                cartas cartasorteada = Baralho.get(i);
                
                //Adicionamos a carta à mão do jogador
                maojogador1.add(cartasorteada);
                
                //Removemos a carta entregue ao jogador do baralho
                Baralho.remove(i);
            }
            //O método retorna a mão do jogaodor
            return maojogador1;
        }

        if (jogador == 2) {
            //O baralho é embaralhado
            Collections.shuffle(Baralho);
            for (int i = 0; i < indice; i++) {
                
                //Salvaos a carta sorteada em uma variável do tipo carta
                cartas cartasorteada = Baralho.get(i);
                
                //Adicionamos a carta à mão do jogador
                maojogador2.add(cartasorteada);
                
                //Removemos a carta entregue ao jogador do baralho
                Baralho.remove(i);
            }
            //O método retorna a mão do jogaodor
            return maojogador2;
        }
        //Caso o jogador seja diferente de 1 ou 2, o método retorna valor nulo
        return null;
    }
    //Método que adiciona as cartas dos jogadores ao campo de cartas
    public Vector<cartas> adicionaraocampo(int q, int jogador, Vector<cartas> maojogador1) {
        if (jogador == 1) {
            
            //A carta informada é adicionada ao vetor de cartas posicionadas do jogador
            cartasjogadasjogador1.add(maojogador1.get(q));
            
            //O método retorna o vetor de cartas jogadas posicionadas pelo jogador
            return cartasjogadasjogador1;
        }

        if (jogador == 2) {
            
            //A carta informada é adicionada ao vetor de cartas posicionadas do jogador
            cartasjogadasjogador2.add(maojogador1.get(q));
            
            //O método retorna o vetor de cartas jogadas posicionadas pelo jogador
            return cartasjogadasjogador2;
        }
        //Caso o jogador seja diferente de 1 ou 2, o método retorna valor nulo
        return null;
    }
    
    //Método que adiciona os estados(ataque ou defesa) das cartas à seus respectivos vetores dos jogadores
    public Vector<String> adicionarestado(String estado, int jogador) {
        if (jogador == 1) {
            
            //O estado carta informada é adicionada ao vetor de cartas posicionadas do jogador
            estadoscartasjogadasjogador1.add(estado);
            
            //O método retorna o vetor de cartas jogadas posicionadas pelo jogador
            return estadoscartasjogadasjogador1;
        }

        if (jogador == 2) {
            
            //O estado carta informada é adicionada ao vetor de cartas posicionadas do jogador
            estadoscartasjogadasjogador2.add(estado);
            
            //O método retorna o vetor de cartas jogadas posicionadas pelo jogador
            return estadoscartasjogadasjogador2;
        }
        //Caso o jogador seja diferente de 1 ou 2, o método retorna valor nulo
        return null;
    }
    
    //Método que adiciona uma carta à mão do jogador quando ele passa sua vez
    public Vector<cartas> refil(Vector<cartas> maojogador1, Vector<cartas> maojogador2, int jogador, int pontosjogador1, int pontosjogador2) {
        
        //Vetor que armazenará a carta sorteada
        Vector<cartas> vetorcartasorteada = new Vector<>();
        
        //Caso tenha alguma carta anteriormente salva no vetor, o limpamos
        if (vetorcartasorteada.isEmpty() == false) {
            vetorcartasorteada.clear();

        }

        if (jogador == 1) {
            
            //O baralho é embaralhado
            Collections.shuffle(Baralho);
            for (int i = 0; i < 1; i++) {
                
                //Adicionamos a carta sorteada ao vetor que criamos anteriormente no método
                vetorcartasorteada.add(Baralho.get(i));
                
                //Removemos a carta sorteada do baralho
                Baralho.remove(i);
                
                //Verificamos se o baralho não tem mais cartas disponíveis
                if (Baralho.size() == 0) {
                    
                    //Em caso afirmativo, mostramos um menu com as pontuações dos usuários
                    System.out.println("Pontuação:");
                    System.out.println("Jogador 1: " + pontosjogador1);
                    System.out.println("Jogador 2: " + pontosjogador2);
                    System.out.println("");
                    
                    //Analisamos qual jogador que possui maior pontuação, e mostramos uma mensagem falando que as cartas do baralho acabaram e o jogador com maior pontuação ganhou
                    if (pontosjogador1 > pontosjogador2) {
                        System.out.println("As cartas do baralho acabaram e o jogador 1 tem maior pontuação, logo o jogador 1 ganhou");
                    }
                    if (pontosjogador1 < pontosjogador2) {
                        System.out.println("As cartas do baralho acabaram e o jogador 2 tem maior pontuação, logo o jogador 2 ganhou");
                    }
                    if (pontosjogador1 == pontosjogador2) {
                        System.out.println("As cartas do baralho acabaram e os jogadores possuem a mesma pontuação, logo houve um empate");
                    }
                    
                    //A execução do programa é finalizada, pois já temos um vencedor
                    System.exit(0);
                }
            }

        }

        if (jogador == 2) {
            
            //O baralho é embaralhado
            Collections.shuffle(Baralho);
            for (int i = 0; i < 1; i++) {
                
                //Adicionamos a carta sorteada ao vetor que criamos anteriormente no método
                vetorcartasorteada.add(Baralho.get(i));
                
                //Removemos a carta sorteada do baralho
                Baralho.remove(i);
                
                //Verificamos se o baralho não tem mais cartas disponíveis
                if (Baralho.size() == 0) {
                    
                    //Em caso afirmativo, mostramos um menu com as pontuações dos usuários
                    System.out.println("Pontuação:");
                    System.out.println("Jogador 1: " + pontosjogador1);
                    System.out.println("Jogador 2: " + pontosjogador2);
                    System.out.println("");
                    
                    //Analisamos qual jogador que possui maior pontuação, e mostramos uma mensagem falando que as cartas do baralho acabaram e o jogador com maior pontuação ganhou
                    if (pontosjogador1 > pontosjogador2) {
                        System.out.println("As cartas do baralho acabaram e o jogador 1 tem maior pontuação, logo o jogador 1 ganhou");
                    }
                    if (pontosjogador1 < pontosjogador2) {
                        System.out.println("As cartas do baralho acabaram e o jogador 2 tem maior pontuação, logo o jogador 2 ganhou");
                    }
                    if (pontosjogador1 == pontosjogador2) {
                        System.out.println("As cartas do baralho acabaram e os jogadores possuem a mesma pontuação, logo houve um empate");
                    }
                    
                    //A execução do programa é finalizada, pois já temos um vencedor
                    System.exit(0);
                }
            }

        }
        
        //Caso o jogador seja diferente de 1 ou 2, o método retorna valor nulo
        return vetorcartasorteada;
    }

}
