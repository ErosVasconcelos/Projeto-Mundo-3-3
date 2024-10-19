package cadastrobd.model;

public class Pessoa {
    private int id;
    private String nome;
    private String logradouro;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;

    // Construtor padr�o
    public Pessoa() {}

    // Construtor completo, sem ID (para inclus�o no banco)
    public Pessoa(String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }

    // Construtor com ID (para objetos j� persistidos no banco)
    public Pessoa(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    // Setters com poss�veis valida��es
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome n�o pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // M�todo para exibir os dados da pessoa
    public void exibir() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
          .append(", Nome: ").append(nome)
          .append(", Logradouro: ").append(logradouro)
          .append(", Cidade: ").append(cidade)
          .append(", Estado: ").append(estado)
          .append(", Telefone: ").append(telefone)
          .append(", Email: ").append(email);

        System.out.println(sb.toString());
    }
}
