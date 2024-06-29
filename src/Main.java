import java.text.DecimalFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            System.out.println("Lista de Funcionários");
            Funcionario.imprimirFuncionarios(funcionarios);
            // 3.4
            funcionarios.forEach(Funcionario::aplicarBonus);
            // 3.5
            Map<String, List<Funcionario>> funcionariosPorFuncao = Funcionario.agruparPorFuncao(funcionarios);
            // 3.6
            System.out.println("-------------\nOrganizando por Funções\n");
            Funcionario.imprimirFuncionariosAgrupados(funcionariosPorFuncao);
            // 3.8 (não existe 3.7?)
            System.out.println("-------------\n");
            System.out.println("Funcionários que fazem aniversário em outubro e dezembro:");
            Funcionario.imprimirFuncionariosAniversarioMes(funcionarios, Month.OCTOBER, Month.DECEMBER);

            // 3.9
            System.out.println("\n-------------\n");
            Funcionario.imprimirFuncionarioMaisVelho(funcionarios);

            // 3.10
            System.out.println("\n-------------\n");
            System.out.println("Funcionários em ordem alfabética:");
            Funcionario.imprimirFuncionariosOrdemAlfabetica(funcionarios);

            // 3.11
            System.out.println("\n-------------\n");
            DecimalFormat salarioFormatter = Funcionario.getSalarioFormato();
            System.out.println("Soma dos Salários formatada: " + salarioFormatter.format(Funcionario.calcularSomaSalarios(funcionarios)));


            // 3.12
            System.out.println("\n-------------\n");
            System.out.println("Funcionários e quantidade de salários mínimos:");
            for (Funcionario funcionario : funcionarios) {
                funcionario.imprimirNomeESalarioMinimo();
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}


