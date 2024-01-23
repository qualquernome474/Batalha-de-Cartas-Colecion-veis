package trabalhojogocartas;

public class cartas {
    
    //Criamos as variáveis que receberão os valores dos atributos das cartas
    private String nome;
    private String descricao;
    private int ataque;
    private int defesa;
    private String tipo;
    
    //Criamos o construtor da classe
    public cartas(String nome, String descricao, int ataque, int defesa, String tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.ataque = ataque;
        this.defesa = defesa;
        this.tipo = tipo;
    }
    
    //Criamos os set's e get's dos atributos das cartas
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    //Usamos o override no nome das cartas para que os nomes das cartas sejam mostrados para os jogadores como palavras, em vez de códigos 
    @Override
    public String toString() {
        return nome;
    }

}
