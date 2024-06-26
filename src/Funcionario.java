import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, String nascimento, String salario, String funcao) {
        super(nome, nascimento);
        this.salario = parseSalario(salario);
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    // Também foi utilizado o Chat GPT para criar a formatação, e a captura de erro
    private BigDecimal parseSalario(String salario) {
        // Analisado a planilha e visto que o salário sempre está com duas casas e com o ponto final como separador de casas por isso foi realizado dessa forma
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat format = new DecimalFormat("0.00", symbols);
        format.setParseBigDecimal(true);
        try {
            return (BigDecimal) format.parse(salario);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Salário no formato inválido. Use ponto como separador decimal.");
        }
    }
    // Método para aplicar um bônus de 10% no salário
    public void aplicarBonus() {
        BigDecimal aumento = this.salario.multiply(new BigDecimal("0.10"));
        this.salario = this.salario.add(aumento);
    }
}

