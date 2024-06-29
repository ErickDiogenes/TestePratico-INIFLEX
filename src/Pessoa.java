import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Pessoa {
   public static final DateTimeFormatter dateFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   // foi removido os métodos setters, já que os atributos são imutáveis no programa também foi definido como final
   private final String nome;
   private final LocalDate nascimento;

    public Pessoa(String nome, String nascimento) {
        this.nome = nome;
        this.nascimento = parseDate(nascimento);
    }
    public String getNome() {
        return nome;
    }
    public LocalDate getNascimento() {
        return nascimento;
    }

    // Método privado para validar e converter a data de nascimento
    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, dateFormato);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data de nascimento no formato inválido. Use dia/mes/ano.");
        }
    }
    public int getIdade() {
        return Period.between(this.nascimento, LocalDate.now()).getYears();
    }
    public String toString() {
        return "Nome: " + nome
                + ", Idade: " + getIdade()
                + " anos";
    }

}
