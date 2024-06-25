import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Pessoa {
   protected String nome;
   protected LocalDate nascimento;


    public Pessoa(String nome, String nascimento) {
        this.nome = nome;
        this.nascimento = parseDate(nascimento);
    }

    // Utilizei o Chat GPT para criar uma formatação e uma captura de exceção para que o usuario sempre coloque a data no formato padrão.
    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data de nascimento no formato inválido. Use dd/MM/yyyy.");
        }
    }
}
