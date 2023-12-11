package industry;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class testeProgramacao {
	
	public static class Pessoa {
		private String nome;
		private LocalDate dataNascimento;
		
		public Pessoa(String nome, LocalDate dataNascimento) {
			this.nome = nome;
			this.dataNascimento = dataNascimento;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public LocalDate getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
		
	}
	
	public static class Funcionario extends Pessoa {
		private BigDecimal salario;
		private String funcao;

		public Funcionario(BigDecimal salario, String funcao, String nome, LocalDate dataNascimento) {
            super(nome, dataNascimento);
            this.salario = salario;
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
		
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		// 1. Inserir todos os funcionários
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios.add(new Funcionario(BigDecimal.valueOf(2009.44), "Operador", "Maria", LocalDate.of(2000, 10, 18)));
		funcionarios.add(new Funcionario(BigDecimal.valueOf(2284.38), "Operador", "João", LocalDate.of(1990, 5, 12)));
		funcionarios.add(new Funcionario(BigDecimal.valueOf(9836.14), "Coordenador", "Caio", LocalDate.of(1961, 5, 2)));
		funcionarios.add(new Funcionario(BigDecimal.valueOf(19119.88), "Diretor", "Miguel", LocalDate.of(1988, 10, 14)));
		funcionarios.add(new Funcionario(BigDecimal.valueOf(2234.68), "Recepcionista", "Alice", LocalDate.of(1995, 1, 5)));
		funcionarios.add(new Funcionario(BigDecimal.valueOf(1582.72), "Operador", "Heitor", LocalDate.of(1999, 11, 19)));
		funcionarios.add(new Funcionario(BigDecimal.valueOf(5071.84), "Contador", "Arthur", LocalDate.of(1993, 3, 31)));
		funcionarios.add(new Funcionario(BigDecimal.valueOf(3017.45), "Gerente", "Laura", LocalDate.of(1994, 7, 8)));
		funcionarios.add(new Funcionario(BigDecimal.valueOf(1606.85), "Eletricista", "Heloísa", LocalDate.of(2003, 5, 24)));
		funcionarios.add(new Funcionario(BigDecimal.valueOf(2799.93), "Gerente", "Helena", LocalDate.of(1996, 9, 2)));
        
        // 2. Remover o funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3. Imprimir todos os funcionários
        imprimirFuncionarios(funcionarios);

        // 4. Aumentar salários em 10%
        funcionarios.forEach(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.valueOf(1.1));
            funcionario.setSalario(novoSalario);
        });

        // 5. Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 6. Imprimir funcionários agrupados por função
        imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        // 7. Imprimir funcionários que fazem aniversário nos meses 10 e 12
        imprimirAniversariantes(funcionarios, 10);
        imprimirAniversariantes(funcionarios, 12);

        // 8. Imprimir funcionário com a maior idade
        imprimirFuncionarioMaiorIdade(funcionarios);

        // 9. Imprimir lista de funcionários por ordem alfabética
        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));
        imprimirFuncionarios(funcionarios);

        // 10. Imprimir total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + totalSalarios);

        // 11. Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        funcionarios.forEach(funcionario ->
                System.out.println(funcionario.getNome() + " ganha " +
                        funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP) + " salários mínimos"));

	}
	
	private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Lista de Funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome() +
                    ", Data de Nascimento: " + funcionario.getDataNascimento().format(formatter) +
                    ", Salário: " + funcionario.getSalario() +
                    ", Função: " + funcionario.getFuncao());
        }
    }
	
	private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(funcionario -> System.out.println("  " + funcionario.getNome()));
        });
    }
	
	private static void imprimirAniversariantes(List<Funcionario> funcionarios, int mes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\nAniversariantes do mês " + mes + ":");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mes)
                .forEach(funcionario -> System.out.println(funcionario.getNome() +
                        " - " + funcionario.getDataNascimento().format(formatter)));
    }
	
	private static void imprimirFuncionarioMaiorIdade(List<Funcionario> funcionarios) {
        LocalDate hoje = LocalDate.now();
        System.out.println("\nFuncionário com a maior idade:");
        funcionarios.stream()
                .max(Comparator.comparing(funcionario -> hoje.minusYears(funcionario.getDataNascimento().getYear())))
                .ifPresent(funcionario -> {
                    long idade = hoje.minusYears(funcionario.getDataNascimento().getYear()).getYear();
                    System.out.println("Nome: " + funcionario.getNome() + ", Idade: " + idade);
                });
    }

}
