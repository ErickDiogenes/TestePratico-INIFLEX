import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Pessoa {
   public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   private String nome;
   private LocalDate nascimento;

    public Pessoa(String nome, String nascimento) {
        this.nome = nome;
        this.nascimento = parseDate(nascimento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }


    // Utilizei o Chat GPT para criar uma formatação e uma captura de exceção para que o usuario sempre coloque a data no formato padrão.
    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data de nascimento no formato inválido. Use dd/MM/yyyy.");
        }
    }
}
