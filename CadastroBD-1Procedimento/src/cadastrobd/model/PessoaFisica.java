package cadastrobd.model;

public class PessoaFisica extends Pessoa {
    private String cpf;

    // Construtor
    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email); // Chama o construtor da classe pai
        this.cpf = cpf;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void exibir() {
        System.out.println("Pessoa Física: " + getNome() + ", CPF: " + cpf);
    }
}
