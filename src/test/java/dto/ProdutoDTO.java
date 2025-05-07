package dto;

public class ProdutoDTO {
    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;
    private String _id;

    public ProdutoDTO(String nome, int preco, String descricao, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
    public String getId() { return _id; }
    public void setId(String id) { this._id = id; }
}
