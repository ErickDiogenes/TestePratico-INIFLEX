import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            // 3.1
            funcionarios.add(new Funcionario("Maria", "18/10/2000", "2009.44", "Operador"));
            funcionarios.add(new Funcionario("João", "12/05/1990", "2284.38", "Operador"));
            funcionarios.add(new Funcionario("Caio", "02/05/1961", "9836.14", "Coordenador"));
            funcionarios.add(new Funcionario("Miguel", "14/10/1988", "19119.88", "Diretor"));
            funcionarios.add(new Funcionario("Alice", "05/01/1995", "2234.68", "Recepcionista"));
            funcionarios.add(new Funcionario("Heitor", "19/11/1999", "1582.72", "Operador"));
            funcionarios.add(new Funcionario("Arthur", "31/03/1993", "4071.84", "Contador"));
            funcionarios.add(new Funcionario("Laura", "08/07/1994", "3017.45", "Gerente"));
            funcionarios.add(new Funcionario("Heloísa", "24/05/2003", "1606.85", "Eletricista"));
            funcionarios.add(new Funcionario("Helena", "02/09/1996", "2799.93", "Gerente"));

            // 3.2
            funcionarios.removeIf(f -> f.getNome().equals("João"));
            // 3.3
            imprimirFuncionarios(funcionarios);

            // 3.4
            funcionarios.forEach(Funcionario::aplicarBonus);



        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Com auxílio do Chat GPT foi criado a formatação necessária para exibir os dados
    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        // Configurar formatters para data e salário
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat salarioFormatter = new DecimalFormat("#,##0.00", symbols);

        // Exibir informações formatadas dos funcionários
        for (Funcionario f : funcionarios) {
            String dataNascimentoFormatada = f.getNascimento().format(Pessoa.dateFormatter);
            String salarioFormatado = salarioFormatter.format(f.getSalario());

            System.out.println("Nome: " + f.getNome());
            System.out.println("Nascimento: " + dataNascimentoFormatada);
            System.out.println("Salário: " + salarioFormatado);
            System.out.println("Função: " + f.getFuncao());
            System.out.println();
        }
    }
}


