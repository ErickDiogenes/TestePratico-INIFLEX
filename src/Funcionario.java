import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    /// removido os setters como programação defensiva e adicionado o final para o atributo função, já que no programa é imutável
    private final String funcao;
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    private static final DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.US);
    private static final DecimalFormat formato = new DecimalFormat("0.00", simbolos);
    private static final DecimalFormat salarioFormato = getSalarioFormato();

    public Funcionario(String nome, String nascimento, String salario, String funcao) {
        super(nome, nascimento);
        this.salario = parseSalario(salario);
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }
    public String getFuncao() {
        return funcao;
    }

    // Método para converter a String para BigDecimal e tratar exceções
    private BigDecimal parseSalario(String salario) {
        // define que o retorno seja BigDecimal, pode ser Number (generic) ou outros
        formato.setParseBigDecimal(true);
        try {
            return (BigDecimal) formato.parse(salario);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Salário no formato inválido. Use ponto como separador decimal.");
        }
    }

    public void aplicarBonus() {
        this.salario = this.salario.multiply(new BigDecimal("1.10"));
    }

    public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        // usado o TreeMap para ordenar alfabéticamente já que no exercício não menciona ordenação
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, TreeMap::new, Collectors.toList()));
    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            System.out.println(f.toString(salarioFormato));
        }
    }

    public static void imprimirFuncionariosAgrupados(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            String funcao = entry.getKey();
            List<Funcionario> funcionarios = entry.getValue();

            // Usando StringBuilder para construir a saída formatada
            StringBuilder sb = new StringBuilder();
            sb.append("Função: ").append(funcao).append("\n");

            for (Funcionario f : funcionarios) {
                // deixei exibindo apenas o nome para simplificar a exibição e evitar repetir os dados já conhecidos
                sb.append(f.getNome()).append("\n");
            }
            // Imprimir o resultado completo para a função atual
            System.out.println(sb);

        }
    }

    public static void imprimirFuncionariosAniversarioMes(List<Funcionario> funcionarios, Month... meses) {
        // Criar um Set com os meses aniversariantes para facilitar a verificação
        Set<Month> mesesAniversario = new HashSet<>(Arrays.asList(meses));

        // Filtrar os funcionários que fazem aniversário nos meses especificados
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> mesesAniversario.contains(f.getNascimento().getMonth()))
                .collect(Collectors.toList());

        // Imprimir os funcionários aniversariantes formatados
        for (Funcionario f : aniversariantes) {
            System.out.println(f.getNome()+" - Nascimento: " + f.getNascimento());
        }

    }

    public static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparing(Funcionario::getNascimento))
                .orElse(null);

        // não coloquei condicional para lista vazia pois o fluxo do exercício não permite uma lista vazia
        if (funcionarioMaisVelho != null) {
            System.out.println("Funcionário(a) com a maior idade:");
            System.out.println(funcionarioMaisVelho);
        }
    }

    public static void imprimirFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());

        for (Funcionario f : funcionariosOrdenados) {
            System.out.println(f.getNome());
        }
    }

    public static BigDecimal calcularSomaSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularSalariosMinimos() {
        return salario.divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
    }

    public void imprimirNomeESalarioMinimo() {
        System.out.println(this.getNome());
        // imprimindo em inteiro, pois no enunciado não especifica
        int salariosMinimos = calcularSalariosMinimos().intValue();
        System.out.println("Salários Mínimos: " + salariosMinimos);
    }

    public static DecimalFormat getSalarioFormato() {
        DecimalFormatSymbols decimalSimbolo = new DecimalFormatSymbols(Locale.getDefault());
        decimalSimbolo.setDecimalSeparator(',');
        decimalSimbolo.setGroupingSeparator('.');
        return new DecimalFormat("#,##0.00", decimalSimbolo);
    }


    public String toString(DecimalFormat salarioFormatter) {
        String dataNascimentoFormatada = this.getNascimento().format(Pessoa.dateFormato);
        String salarioFormatado = salarioFormatter.format(this.salario);
        return "Nome: " + this.getNome() +
                "\nNascimento: " + dataNascimentoFormatada +
                "\nSalário: " + salarioFormatado +
                "\nFunção: " + this.funcao +
                "\n";
    }
}
