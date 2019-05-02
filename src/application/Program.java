package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		System.out.print("Path: ");
		String path = sc.nextLine();

		System.out.print("Salary: ");
		double salaryValue = sc.nextDouble();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String file = br.readLine();

			List<Funcionario> listFunc = new ArrayList<Funcionario>();

			while (file != null) {
				String[] vet = file.split(",");
				listFunc.add(new Funcionario(vet[0], vet[1], Double.parseDouble(vet[2])));
				file = br.readLine();
			}

			System.out.println("\nEmail of people whose salary is more than " + String.format("%.2f", salaryValue));

			// pipeline stream para funões agregadas

			List<String> listEmails = new ArrayList<String>();

			listEmails = listFunc.stream() // lista de funções agregadas
					.filter(x -> x.getSalario() > salaryValue) // filter com predicado
					.map(x -> x.getEmail()) // map de nova lista stream
					.sorted() // ordenação
					.collect(Collectors.toList()); // transformando stream para lista

			listEmails.forEach(System.out::println);

			// Pipeline soma dos salários dos funcionários cujo nome começa com a letra 'M'

			double sum = listFunc.stream() // Lista stream
					.filter(x -> x.getName().charAt(0) == 'M') // filter apemas com nomes com M
					.map(x -> x.getSalario())// nova lista stream apenas com salarios
					.reduce(0.0, (x, y) -> x + y);

			System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			sc.close();
		}

	}

}
