package trabalhojogocartas;

import java.util.Scanner;
import java.util.Vector;

public class Ataque {
    
    //Método que realizará o ataque entre monstros
    public static Vector ataque(Vector<Integer> pontuacao, int pontosjogador1, int pontosjogador2, int jogador, Vector<cartas> cartasjogadasjogador1, Vector<cartas> cartasjogadasjogador2, Vector<String> estadoscartasjogadasjogador1, Vector<String> estadoscartasjogadasjogador2, Vector<Integer> situacaocartasjogadasjogador1, Vector<Integer> situacaocartasjogadasjogador2) {
        Scanner entrada = new Scanner(System.in);
        
        //Caso o jogador seja o jogador 1
        if (jogador == 1) {
            
            //Criamos a variável r, que receberá o índice do monstro que o jogador 1 irá usar para atacar, e a variável foieliminado, que registrará se o monstro que o jogador 1 usou no ataque foi destruído
            Integer r = 0;
            Integer foieliminado = null;

            //Criamos um laço de repetição em que a condição seja que o jogador 1 ainda tenha mosntros que podem atacar na rodada
            while (situacaocartasjogadasjogador1.contains(0) == true) {
                
                //Em caso afirmativo, os pontos dos jogadores são armazenados em suas respectivas variáveis
                pontosjogador1 = pontuacao.get(0);
                pontosjogador2 = pontuacao.get(1);
                
                //É mostrado uma mensagem para o jogador pedindo para ele escolher um mostro que queira usar no ataque
                System.out.println("Digite o monstro que você deseja usar:");
                
                //Salvamos a informação digitada pelo jogador na variável r 
                r = entrada.nextInt();
                
                //Subtraímos r em 1, pois índices de vetores começam em 0
                r--;
                
                //Se o índice informado pelo jogador for válido e se a carta pode atacar nessa rodada
                if (r >= 0 && r <= situacaocartasjogadasjogador1.size() - 1 && situacaocartasjogadasjogador1.get(r) == 0) {
                    
                    //Salvamos a carta informada pelo jogador em uma variável do tipo monstro
                    cartas monstroescolhido = cartasjogadasjogador1.get(r);
                    
                    //Verificamos se o estado da carta escolhida pelo jogador está no estado de ataque
                    if (estadoscartasjogadasjogador1.get(r).equalsIgnoreCase("ataque")) {
                        
                        //Verificamos se o monstro escolhido pelo jogador pode atacar nessa rodada
                        if (situacaocartasjogadasjogador1.get(r) == 0) {
                            
                            //Caso o jogador 2 não tenha colocado nenhuma cata
                            if (cartasjogadasjogador2.isEmpty() == true) {
                                
                                //Verificamos se a carta escolhida pelo jogador 1 está no modo de ataque
                                if (estadoscartasjogadasjogador1.get(r).equalsIgnoreCase("ataque")) {
                                    
                                    //Salvamos a nova pontação do jogador 2
                                    pontosjogador2 = pontosjogador2 - monstroescolhido.getAtaque();
                                    
                                    //Mostramos uma mensagem informando como ocorreu o ataque
                                    System.out.println("O jogador 1 atacou com a carta " + monstroescolhido + " e já que o jogador 2 não havia posicionado nenhuma carta, o jogador 2 perdeu " + monstroescolhido.getAtaque() + " pontos");
                                }
                                //Atualizamos a situação da carta informada pelo jogador 1
                                situacaocartasjogadasjogador1.set(r, 1);

                            }
                            
                            //Caso o jogador 2 tenha colocado catas na área de cartas
                            if (cartasjogadasjogador2.isEmpty() == false) {
                                
                                //Se a carta escolhida pelo jogador 1 pode atacar nessa rodada
                                if (situacaocartasjogadasjogador1.get(r) == 0) {
                                    
                                    //Perguntamos ao jogador qual mosntro inimigo ele quer atacar
                                    System.out.println("Digite o monstro inimigo que você deseja atacar:");
                                    
                                    //Salvamos o índice informado enba variável g
                                    int g = entrada.nextInt();
                                    
                                    //Subtraímos g em 1, pois índices de vetores começam em 0
                                    g--;
                                    
                                    //Verificamos se o índice informado pelo jogador for válido
                                    if (g >= 0 && g <= estadoscartasjogadasjogador2.size() - 1) {
                                        
                                        //Inicializamos a variável foieliminado
                                        foieliminado = 0;
                                        
                                        //Caso a carta atacada estiver no modo de ataque
                                        if (estadoscartasjogadasjogador2.get(g).equalsIgnoreCase("ataque")) {
                                            
                                            //É mostrada uma mensagem explicando a carta usada pelo jogador 1 e a carta atacada(e seu estado) 
                                            System.out.println("O jogador 1 usou a carta " + cartasjogadasjogador1.get(r) + " para atacar a carta " + cartasjogadasjogador2.get(g) + " do jogador 2, que estava no modo de ataque");

                                            
                                            //Caso o ataque da carta escolhida do jogador 1 for maior que o ataque da carta atacada do jogador 2
                                            if (cartasjogadasjogador2.get(g).getAtaque() < cartasjogadasjogador1.get(r).getAtaque()) {
                                                
                                                //É mostrada uma mensagem explicando a relação entre os ataques das cartas escolhidas
                                                System.out.println("Já que a carta " + cartasjogadasjogador2.get(g) + " do jogador 2 tinha menor valor de ataque que a carta " + cartasjogadasjogador1.get(r) + " a carta " + cartasjogadasjogador2.get(g) + " do jogador 2 foi destruída");
                                                
                                                //Calculamos a diferença entre os ataque e salvamos na variável resultado
                                                int resultado = cartasjogadasjogador1.get(r).getAtaque() - cartasjogadasjogador2.get(g).getAtaque();
                                                
                                                //Atualizamos o ataque da carta usada pelo jogador 1 no ataque
                                                cartasjogadasjogador1.get(r).setAtaque(resultado);
                                                
                                                //Já que a carta atacada tinha menor valor de ataque, ela é destruída, então removemos essa carta dos vetores onde suas informações estavam armazenadas
                                                cartasjogadasjogador2.remove(cartasjogadasjogador2.get(g));
                                                situacaocartasjogadasjogador2.remove(situacaocartasjogadasjogador2.get(g));
                                                estadoscartasjogadasjogador2.remove(estadoscartasjogadasjogador2.get(g));
                                                
                                                //A pontuação do jogador 2 é atualizada
                                                pontosjogador2 = pontosjogador2 - resultado;
                                                
                                                //Mostramos o valor que o jogador 2 tomou de dano
                                                System.out.println("E o jogador 2 tomou " + resultado + " de dano");
                                                
                                                //Caso o ataque da carta escolhida do jogador 1 não seja maior que o ataque da carta atacada do jogador 2
                                            } else {
                                                
                                                //Caso o ataque da carta escolhida do jogador 1 for igual ao ataque da carta atacada do jogador 2
                                                if (cartasjogadasjogador2.get(g).getAtaque() == cartasjogadasjogador1.get(r).getAtaque()) {
                                                    
                                                    //É mostrada uma mensagem explicando a relação entre os ataques das cartas escolhidas
                                                    System.out.println("Já que a carta " + cartasjogadasjogador2.get(g) + " do jogador 2 tinha o mesmo valor de ataque que a carta " + cartasjogadasjogador1.get(r) + " do jogador 1, as duas cartas foram destruídas e nenhum jogador sofreu danos");
                                                    
                                                    //Já que os ataques são iguais, as duas cartas são destruídas
                                                    
                                                    //Já que as duas cartas foram destruídas, então removemos a carta escolhida pelo jogador 1 dos vetores onde suas informações estavam armazenadas
                                                    cartasjogadasjogador1.remove(cartasjogadasjogador1.get(r));
                                                    situacaocartasjogadasjogador1.remove(situacaocartasjogadasjogador1.get(r));
                                                    estadoscartasjogadasjogador1.remove(estadoscartasjogadasjogador1.get(r));
                                                    
                                                    //Já que a carta usada pelo jogador 1 foi destruída, atualizamos a variável foieliminado
                                                    foieliminado = 1;
                                                    
                                                    //Já que as duas cartas foram destruídas, então removemos a carta escolhida pelo jogador 2 dos vetores onde suas informações estavam armazenadas
                                                    cartasjogadasjogador2.remove(cartasjogadasjogador2.get(g));
                                                    situacaocartasjogadasjogador2.remove(situacaocartasjogadasjogador2.get(g));
                                                    estadoscartasjogadasjogador2.remove(estadoscartasjogadasjogador2.get(g));
                                                
                                                //Caso o ataque da carta escolhida do jogador 1 não seja igual ao ataque da carta atacada do jogador 2
                                                } else {
                                                    
                                                    //Caso o ataque da carta escolhida do jogador 1 for menor que o ataque da carta atacada do jogador 2
                                                    if (cartasjogadasjogador2.get(g).getAtaque() > cartasjogadasjogador1.get(r).getAtaque()) {
                                                        
                                                        //É mostrada uma mensagem explicando a relação entre os ataques das cartas escolhidas
                                                        System.out.println("Já que a carta " + cartasjogadasjogador2.get(g) + " do jogador 2 tinha maior valor de ataque que a carta " + cartasjogadasjogador1.get(r) + " a carta " + cartasjogadasjogador1.get(r) + " do jogador 1 foi destruída");
                                                        
                                                        //Calculmos a diferença entre os ataque e salvamos na variável resultado
                                                        int resultado = cartasjogadasjogador2.get(g).getAtaque() - cartasjogadasjogador1.get(r).getAtaque();
                                                        
                                                        //Atualizamos o ataque da carta atacada
                                                        cartasjogadasjogador2.get(g).setAtaque(resultado);
                                                        
                                                        //Já que a carta que atacou tinha menor valor de ataque, ela é destruída, então removemos essa carta dos vetores onde suas informações estavam armazenadas
                                                        cartasjogadasjogador1.remove(cartasjogadasjogador1.get(r));
                                                        situacaocartasjogadasjogador1.remove(situacaocartasjogadasjogador1.get(r));
                                                        estadoscartasjogadasjogador1.remove(estadoscartasjogadasjogador1.get(r));
                                                        
                                                        //Já que a carta usada pelo jogador 1 foi destruída, atualizamos a variável foieliminado
                                                        foieliminado = 1;
                                                        
                                                        //A pontuação do jogador 1 é atualizada
                                                        pontosjogador1 = pontosjogador1 - resultado;
                                                        
                                                        //Mostramos o valor que o jogador 1 tomou de dano
                                                        System.out.println("E o jogador 1 tomou " + resultado + " de dano");
                                                    }
                                                }
                                            }
                                            
                                        //Caso a carta atacada não estiver no modo de ataque
                                        } else {
                                            
                                            //Caso a carta atacada estiver no modo de defesa
                                            if (estadoscartasjogadasjogador2.get(g).equalsIgnoreCase("defesa")) {
                                                
                                                //É mostrada uma mensagem explicando a carta usada pelo jogador 1 e a carta atacada(e seu estado) 
                                                System.out.println("O jogador 1 usou a carta " + cartasjogadasjogador1.get(r) + " para atacar a carta " + cartasjogadasjogador2.get(g) + " do jogador 2, que estava no modo de defesa");
                                                
                                                //Verificamos se os valores do ataque da carta atacante e da defesa da carta atacada forem diferentes
                                                if (cartasjogadasjogador2.get(g).getDefesa() != cartasjogadasjogador1.get(r).getAtaque()) {
                                                    
                                                    //Caso o ataque da carta atacante for maior que a defesa da carta atacada
                                                    if (cartasjogadasjogador2.get(g).getDefesa() < cartasjogadasjogador1.get(r).getAtaque()) {
                                                        
                                                        //É mostrada uma mensagem explicando a relação entre o ataque da carta atacante e a defesa da carta atacada 
                                                        System.out.println("Já que a carta " + cartasjogadasjogador2.get(g) + " do jogador 2 tinha valor de defesa menor que o valor de ataque que a carta " + cartasjogadasjogador1.get(r) + " do jogador 1, a carta " + cartasjogadasjogador2.get(g) + " do jogador 2 foi destruída");
                                                        
                                                        //Calculmos a diferença entre os ataque da carta atacante e a defesa da carta atacada e salvamos na variável resultado
                                                        int resultado = cartasjogadasjogador1.get(r).getAtaque() - cartasjogadasjogador2.get(g).getDefesa();
                                                        
                                                        //Já que a carta atacada tinha defesa menor que o ataque da carta atacante, ela é destruída, então removemos essa carta dos vetores onde suas informações estavam armazenadas
                                                        cartasjogadasjogador2.remove(cartasjogadasjogador2.get(g));
                                                        situacaocartasjogadasjogador2.remove(situacaocartasjogadasjogador2.get(g));
                                                        estadoscartasjogadasjogador2.remove(estadoscartasjogadasjogador2.get(g));
                                                        
                                                        //A pontuação do jogador 2 é atualizada
                                                        pontosjogador2 = pontosjogador2 - resultado;
                                                        
                                                        //Mostramos o valor que o jogador 2 tomou de dano
                                                        System.out.println("E o jogador 2 tomou " + resultado + " de dano");
                                                        
                                                    //Caso o ataque da carta atacante não seja maior que a defesa da carta atacada, ou seja, for menor
                                                    } else {
                                                        
                                                        //Calculmos a diferença entre os ataque da carta atacante e a defesa da carta atacada e salvamos na variável resultado
                                                        int resultado = cartasjogadasjogador2.get(g).getDefesa() - cartasjogadasjogador1.get(r).getAtaque();
                                                        
                                                        //Atualizamos a defesa da carta atacada
                                                        cartasjogadasjogador2.get(g).setDefesa(resultado);
                                                        
                                                        //É mostrada uma mensagem explicando a relação entre o ataque da carta atacante e a defesa da carta atacada 
                                                        System.out.println("Já que a carta " + cartasjogadasjogador2.get(g) + " do jogador 2 tinha valor de defesa maior que o valor de ataque que a carta " + cartasjogadasjogador1.get(r) + " do jogador 1, ao jogador 1 tomou " + resultado + " de dano");
                                                        
                                                        //A pontuação do jogador 1 é atualizada
                                                        pontosjogador1 = pontosjogador1 - resultado;

                                                    }
                                                }
                                            }
                                        }
                                        
                                    //Caso o índice informado para a carta atacada seja inválido
                                    } else {
                                        //É mostrado para o jogador informando que o índice informado é inválido
                                        System.out.println("O índice escolhido não é válido");

                                    }
                                }
                            }
                        }
                    //Caso a carta indicada como atacante esteja no modo de defesa
                    } else {
                        
                        //Se a carta indicada como atacante esteja no modo de defesa
                        if (estadoscartasjogadasjogador1.get(r).equalsIgnoreCase("defesa")) {
                            
                            //É mostrada uma mensagem informando para o jogador que não é possível atacar usando uma carta no modo de defesa
                            System.out.println("Não é possível atacar com uma carta na posição de defesa");
                            
                            //O vetor das pontuações dos jogadores é retornado
                            return pontuacao;
                        }
                    }
                    
                    //Verificamos se a variável foieliminado não foi alterada, ou seja, a carta atacante não foi destruída
                    if (foieliminado != null) {
                        //Se a variável foieliminado não foi atualizada
                        if (foieliminado != 1) {
                            //Atualizamos a situação da carta atacante
                            situacaocartasjogadasjogador1.set(r, 1);
                        }
                    //Se a variável foieliminado foi alterada, ou seja, a carta atacante foi destruída
                    } else {
                        //Nenhum comando é realizado
                    }
                    
                    //Limpamos o vetor que armazena as pontuações dos jogadores
                    pontuacao.clear();
                    
                    //Verificamos se o vetor que armazena as pontuações dos jogadores foi limpo
                    if (pontuacao.isEmpty()) {
                        
                        //Atualizamos o vetor que armazena as pontuações dos jogadores com as pontuações atualizadas
                        pontuacao.add(pontosjogador1);
                        pontuacao.add(pontosjogador2);
                    }
                    
                    //O vetor que armazena as pontuações dos jogadores é retornado
                    return pontuacao;
                    
                //Caso o índice informado para a carta atacante não seja inválido ou não possa atacar na rodada
                } else {
                    
                    //Caso o índice informado para a carta atacante seja inválido
                    if (r < 0 || r > situacaocartasjogadasjogador1.size() - 1) {
                        //É mostrada uma mensagem para o jogador
                        System.out.println("O índice escolhido não é válido");
                        
                    //Caso o índice informado para a carta atacante seja inválido
                    } else {
                        
                        //Caso a carta atacante informada não possa atacar na rodada e esteja no modo de ataque
                        if (estadoscartasjogadasjogador1.get(r).equalsIgnoreCase("ataque")) {
                            //É mostrada uma mensagem para o jogador
                            System.out.println("A carta escolhida pelo jogador " + jogador + " já atacou nesse turno e só poderá atacar na próxima rodada, escolha outra opção ou passe sua vez");
                        }
                        
                        //Caso a carta atacante informada não possa atacar na rodada e esteja no modo de defesa
                        if (estadoscartasjogadasjogador1.get(r).equalsIgnoreCase("defesa")) {
                            //É mostrada uma mensagem para o jogador
                            System.out.println("Não é possível atacar com uma carta na posição de defesa");

                        }
                    }
                    //O vetor que armazena as pontuações dos jogadores é retornado
                    return pontuacao;

                }

            }

        }
        
        //Caso o jogador seja o jogador 2
        if (jogador == 2) {
            
            //Criamos a variável r, que receberá o índice do monstro que o jogador 2 irá usar para atacar, e a variável foieliminado, que registrará se o monstro que o jogador 2 usou no ataque foi destruído
            Integer r = 0;
            Integer foieliminado = null;
            
            //Criamos um laço de repetição em que a condição seja que o jogador 2 ainda tenha mosntros que podem atacar na rodada
            while (situacaocartasjogadasjogador2.contains(0) == true) {
                
                //Em caso afirmativo, os pontos dos jogadores são armazenados em suas respectivas variáveis
                pontosjogador1 = pontuacao.get(0);
                pontosjogador2 = pontuacao.get(1);
                
                //É mostrado uma mensagem para o jogador pedindo para ele escolher um mostro que queira usar no ataque
                System.out.println("Digite o monstro que você deseja usar:");
                
                //Salvamos a informação digitada pelo jogador na variável r 
                r = entrada.nextInt();
                
                //Subtraímos r em 1, pois índices de vetores começam em 0
                r--;
                
                //Verificamos se o índice informado pelo jogador for válido e se a carta pode atacar nessa rodada
                if (r >= 0 && r <= situacaocartasjogadasjogador2.size() - 1 && situacaocartasjogadasjogador2.get(r) == 0) {
                    
                    //Salvamos a carta informada pelo jogador em uma variável do tipo monstro
                    cartas monstroescolhido = cartasjogadasjogador2.get(r);
                    
                    //Verificamos se o estado da carta escolhida pelo jogador está no estado de ataque
                    if (estadoscartasjogadasjogador2.get(r).equalsIgnoreCase("ataque")) {
                        
                        //Verificamos se o monstro escolhido pelo jogador pode atacar nessa rodada
                        if (situacaocartasjogadasjogador2.get(r) == 0) {
                            
                            //Caso o jogador 1 não tenha colocado nenhuma cata
                            if (cartasjogadasjogador1.isEmpty() == true) {
                                
                                //Verificamos se a carta escolhida pelo jogador 2 está no modo de ataque
                                if (estadoscartasjogadasjogador2.get(r).equalsIgnoreCase("ataque")) {

                                    //Salvamos a nova pontação do jogador 1
                                    pontosjogador1 = pontosjogador1 - monstroescolhido.getAtaque();
                                    
                                    //Mostramos uma mensagem informando como ocorreu o ataque
                                    System.out.println("O jogador 2 atacou com a carta " + monstroescolhido + " e já que o jogador 1 não havia posicionado nenhuma carta, o jogador 1 perdeu " + monstroescolhido.getAtaque() + " pontos");
                                }
                                
                                //Atualizamos a situação da carta informada pelo jogador 2
                                situacaocartasjogadasjogador2.set(r, 1);

                            }
                            
                            //Caso o jogador 1 tenha colocado catas na área de cartas
                            if (cartasjogadasjogador1.isEmpty() == false) {
                                
                                //VErificamos se a carta escolhida pelo jogador 2 pode atacar nessa rodada
                                if (situacaocartasjogadasjogador2.get(r) == 0) {
                                    
                                    //Perguntamos ao jogador qual mosntro inimigo ele quer atacar
                                    System.out.println("Digite o monstro inimigo que você deseja atacar:");
                                    
                                    //Salvamos o índice informado enba variável g
                                    int g = entrada.nextInt();
                                    
                                    //Subtraímos g em 1, pois índices de vetores começam em 0
                                    g--;
                                    
                                    //Verificamos se o índice informado pelo jogador for válido
                                    if (g >= 0 && g <= estadoscartasjogadasjogador1.size() - 1) {
                                        
                                        //Inicializamos a variável foieliminado
                                        foieliminado = 0;
                                        
                                        //Caso a carta atacada estiver no modo de ataque
                                        if (estadoscartasjogadasjogador1.get(g).equalsIgnoreCase("ataque")) {
                                            
                                            //É mostrada uma mensagem explicando a carta usada pelo jogador 2 e a carta atacada(e seu estado) 
                                            System.out.println("O jogador 2 usou a carta " + cartasjogadasjogador2.get(r) + " para atacar a carta " + cartasjogadasjogador1.get(g) + " do jogador 1, que estava no modo de ataque");
                                            
                                            //Caso o ataque da carta escolhida do jogador 2 for maior que o ataque da carta atacada do jogador 1
                                            if (cartasjogadasjogador1.get(g).getAtaque() < cartasjogadasjogador2.get(r).getAtaque()) {
                                                
                                                //É mostrada uma mensagem explicando a relação entre os ataques das cartas escolhidas
                                                System.out.println("Já que a carta " + cartasjogadasjogador1.get(g) + " do jogador 1 tinha menor valor de ataque que a carta " + cartasjogadasjogador2.get(r) + " a carta " + cartasjogadasjogador1.get(g) + " do jogador 1 foi destruída");
                                                
                                                //Calculamos a diferença entre os ataque e salvamos na variável resultado
                                                int resultado = cartasjogadasjogador2.get(r).getAtaque() - cartasjogadasjogador1.get(g).getAtaque();
                                                
                                                //Atualizamos o ataque da carta usada pelo jogador 2 no ataque
                                                cartasjogadasjogador2.get(r).setAtaque(resultado);
                                                
                                                //Já que a carta atacada tinha menor valor de ataque, ela é destruída, então removemos essa carta dos vetores onde suas informações estavam armazenadas
                                                cartasjogadasjogador1.remove(cartasjogadasjogador1.get(g));
                                                situacaocartasjogadasjogador1.remove(situacaocartasjogadasjogador1.get(g));
                                                estadoscartasjogadasjogador1.remove(estadoscartasjogadasjogador1.get(g));
                                                
                                                //A pontuação do jogador 1 é atualizada
                                                pontosjogador1 = pontosjogador1 - resultado;
                                                
                                                //Mostramos o valor que o jogador 1 tomou de dano
                                                System.out.println("E o jogador 1 tomou " + resultado + " de dano");
                                            
                                            //Caso o ataque da carta escolhida do jogador 2 não seja maior que o ataque da carta atacada do jogador 1
                                            } else {
                                                
                                                //Caso o ataque da carta escolhida do jogador 2 for igual ao ataque da carta atacada do jogador 2
                                                if (cartasjogadasjogador1.get(g).getAtaque() == cartasjogadasjogador2.get(r).getAtaque()) {
                                                    
                                                    //É mostrada uma mensagem explicando a relação entre os ataques das cartas escolhidas
                                                    System.out.println("Já que a carta " + cartasjogadasjogador2.get(r) + " do jogador 2 tinha o mesmo valor de ataque que a carta " + cartasjogadasjogador1.get(g) + " do jogador 1, as duas cartas foram destruídas e nenhum jogador sofreu danos");
                                                    
                                                    //Já que os ataques são iguais, as duas cartas são destruídas
                                                    
                                                    //Já que as duas cartas foram destruídas, então removemos a carta escolhida pelo jogador 2 dos vetores onde suas informações estavam armazenadas                 
                                                    cartasjogadasjogador2.remove(cartasjogadasjogador2.get(r));
                                                    situacaocartasjogadasjogador2.remove(situacaocartasjogadasjogador2.get(r));
                                                    estadoscartasjogadasjogador2.remove(estadoscartasjogadasjogador2.get(r));
                                                    
                                                    //Já que a carta usada pelo jogador 2 foi destruída, atualizamos a variável foieliminado
                                                    foieliminado = 1;
                                                    
                                                    //Já que as duas cartas foram destruídas, então removemos a carta escolhida pelo jogador 1 dos vetores onde suas informações estavam armazenadas
                                                    cartasjogadasjogador1.remove(cartasjogadasjogador1.get(g));
                                                    situacaocartasjogadasjogador1.remove(situacaocartasjogadasjogador1.get(g));
                                                    estadoscartasjogadasjogador1.remove(estadoscartasjogadasjogador1.get(g));
                                                
                                                //Caso o ataque da carta escolhida do jogador 2 não seja igual ao ataque da carta atacada do jogador 1
                                                } else {
                                                    
                                                    //Caso o ataque da carta escolhida do jogador 2 for menor que o ataque da carta atacada do jogador 1
                                                    if (cartasjogadasjogador1.get(g).getAtaque() > cartasjogadasjogador2.get(r).getAtaque()) {
                                                        
                                                        //É mostrada uma mensagem explicando a relação entre os ataques das cartas escolhidas
                                                        System.out.println("Já que a carta " + cartasjogadasjogador1.get(g) + " do jogador 1 tinha maior valor de ataque que a carta " + cartasjogadasjogador2.get(r) + " a carta " + cartasjogadasjogador2.get(r) + " do jogador 2 foi destruída");
                                                        
                                                        //Calculmos a diferença entre os ataque e salvamos na variável resultado
                                                        int resultado = cartasjogadasjogador1.get(g).getAtaque() - cartasjogadasjogador2.get(r).getAtaque();
                                                        
                                                        //Atualizamos o ataque da carta atacada
                                                        cartasjogadasjogador1.get(g).setAtaque(resultado);
                                                        
                                                        //Já que a carta que atacou tinha menor valor de ataque, ela é destruída, então removemos essa carta dos vetores onde suas informações estavam armazenadas
                                                        cartasjogadasjogador2.remove(cartasjogadasjogador2.get(r));
                                                        situacaocartasjogadasjogador2.remove(situacaocartasjogadasjogador2.get(r));
                                                        estadoscartasjogadasjogador2.remove(estadoscartasjogadasjogador2.get(r));
                                                        
                                                        //Já que a carta usada pelo jogador 2 foi destruída, atualizamos a variável foieliminado
                                                        foieliminado = 1;
                                                        
                                                        //A pontuação do jogador 2 é atualizada
                                                        pontosjogador2 = pontosjogador2 - resultado;
                                                        
                                                        //Mostramos o valor que o jogador 2 tomou de dano
                                                        System.out.println("E o jogador 2 tomou " + resultado + " de dano");
                                                    }
                                                }
                                            }
                                         
                                        //Caso a carta atacada não estiver no modo de ataque
                                        } else {
                                            
                                            //Caso a carta atacada estiver no modo de defesa
                                            if (estadoscartasjogadasjogador1.get(g).equalsIgnoreCase("defesa")) {
                                                
                                                //É mostrada uma mensagem explicando a carta usada pelo jogador 2 e a carta atacada(e seu estado) 
                                                System.out.println("O jogador 2 usou a carta " + cartasjogadasjogador2.get(r) + " para atacar a carta " + cartasjogadasjogador1.get(g) + " do jogador 1, que estava no modo de defesa");

                                                //Verificamos se os valores do ataque da carta atacante e da defesa da carta atacada forem diferentes
                                                if (cartasjogadasjogador1.get(g).getDefesa() != cartasjogadasjogador2.get(r).getAtaque()) {
                                                    
                                                    //Caso o ataque da carta atacante for maior que a defesa da carta atacada
                                                    if (cartasjogadasjogador1.get(g).getDefesa() < cartasjogadasjogador2.get(r).getAtaque()) {
                                                        
                                                        //É mostrada uma mensagem explicando a relação entre o ataque da carta atacante e a defesa da carta atacada
                                                        System.out.println("Já que a carta " + cartasjogadasjogador1.get(g) + " do jogador 1 tinha valor de defesa menor que o valor de ataque que a carta " + cartasjogadasjogador2.get(r) + " do jogador 2, a carta " + cartasjogadasjogador1.get(g) + " do jogador 1 foi destruída");
                                                        
                                                        //Calculmos a diferença entre os ataque da carta atacante e a defesa da carta atacada e salvamos na variável resultado
                                                        int resultado = cartasjogadasjogador2.get(r).getAtaque() - cartasjogadasjogador1.get(g).getDefesa();
                                                        
                                                        //Já que a carta atacada tinha defesa menor que o ataque da carta atacante, ela é destruída, então removemos essa carta dos vetores onde suas informações estavam armazenadas
                                                        cartasjogadasjogador1.remove(cartasjogadasjogador1.get(g));
                                                        situacaocartasjogadasjogador1.remove(situacaocartasjogadasjogador1.get(g));
                                                        estadoscartasjogadasjogador1.remove(estadoscartasjogadasjogador1.get(g));
                                                        
                                                        //A pontuação do jogador 1 é atualizada
                                                        pontosjogador1 = pontosjogador1 - resultado;
                                                        
                                                        //Mostramos o valor que o jogador 1 tomou de dano
                                                        System.out.println("E o jogador 1 tomou " + resultado + " de dano");
                                                    
                                                    //Caso o ataque da carta atacante não seja maior que a defesa da carta atacada, ou seja, for menor
                                                    } else {
                                                        
                                                        //Calculmos a diferença entre os ataque da carta atacante e a defesa da carta atacada e salvamos na variável resultado
                                                        int resultado = cartasjogadasjogador1.get(g).getDefesa() - cartasjogadasjogador2.get(r).getAtaque();
                                                        
                                                        //Atualizamos a defesa da carta atacada
                                                        cartasjogadasjogador1.get(g).setDefesa(resultado);

                                                        //É mostrada uma mensagem explicando a relação entre o ataque da carta atacante e a defesa da carta atacada 
                                                        System.out.println("Já que a carta " + cartasjogadasjogador1.get(g) + " do jogador 1 tinha valor de defesa maior que o valor de ataque que a carta " + cartasjogadasjogador2.get(r) + " do jogador 2, ao jogador 2 tomou " + resultado + " de dano");
                                                        
                                                        //A pontuação do jogador 2 é atualizada
                                                        pontosjogador2 = pontosjogador2 - resultado;

                                                    }
                                                }
                                            }
                                        }
                                     
                                    //Caso o índice informado para a carta atacada seja inválido
                                    } else {
                                        //É mostrado para o jogador informando que o índice informado é inválido
                                        System.out.println("O índice escolhido não é válido");

                                    }
                                }
                            }
                        }
                    
                    //Caso a carta indicada como atacante esteja no modo de defesa
                    } else {
                        
                        //Se a carta indicada como atacante esteja no modo de defesa
                        if (estadoscartasjogadasjogador2.get(r).equalsIgnoreCase("defesa")) {
                            
                            //É mostrada uma mensagem informando para o jogador que não é possível atacar usando uma carta no modo de defesa
                            System.out.println("Não é possível atacar com uma carta na posição de defesa");
                            
                            //O vetor das pontuações dos jogadores é retornado
                            return pontuacao;
                        }
                    }
                    
                    //Verificamos se a variável foieliminado não foi alterada, ou seja, a carta atacante não foi destruída
                    if (foieliminado != null) {
                        
                        //Se a variável foieliminado não foi atualizada
                        if (foieliminado != 1 && foieliminado != null) {
                            
                            //Atualizamos a situação da carta atacante
                            situacaocartasjogadasjogador2.set(r, 1);
                        }
                    
                    //Se a variável foieliminado foi alterada, ou seja, a carta atacante foi destruída
                    } else {
                        //Nenhum comando é realizado
                    }
                    
                    //Limpamos o vetor que armazena as pontuações dos jogadores
                    pontuacao.clear();
                    
                    //Verificamos se o vetor que armazena as pontuações dos jogadores foi limpo
                    if (pontuacao.isEmpty()) {
                        
                        //Atualizamos o vetor que armazena as pontuações dos jogadores com as pontuações atualizadas
                        pontuacao.add(pontosjogador1);
                        pontuacao.add(pontosjogador2);
                    }
                    
                    //O vetor que armazena as pontuações dos jogadores é retornado
                    return pontuacao;

                //Caso o índice informado para a carta atacante não seja inválido ou não possa atacar na rodada
                } else {
                    
                    //Caso o índice informado para a carta atacante seja inválido
                    if (r < 0 || r > situacaocartasjogadasjogador2.size() - 1) {
                        
                        //É mostrada uma mensagem para o jogador
                        System.out.println("O índice escolhido não é válido");
                        
                    //Caso o índice informado para a carta atacante seja válido
                    } else {
                        
                        //Caso a carta atacante informada não possa atacar na rodada e esteja no modo de ataque
                        if (estadoscartasjogadasjogador2.get(r).equalsIgnoreCase("ataque")) {
                            //É mostrada uma mensagem para o jogador
                            System.out.println("A carta escolhida pelo jogador " + jogador + " já atacou nesse turno e só poderá atacar na próxima rodada, escolha outra opção ou passe sua vez");
                        }
                        
                        //Caso a carta atacante informada não possa atacar na rodada e esteja no modo de defesa
                        if (estadoscartasjogadasjogador2.get(r).equalsIgnoreCase("defesa")) {
                            //É mostrada uma mensagem para o jogador
                            System.out.println("Não é possível atacar com uma carta na posição de defesa");

                        }
                    }
                    
                    //O vetor que armazena as pontuações dos jogadores é retornado
                    return pontuacao;

                }

            }

        }
        //O vetor que armazena as pontuações dos jogadores é retornado
        return pontuacao;
    }

}
