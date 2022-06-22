package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import dados.Item;
import dados.ItemVet;
import listaSimples.ListaSimples;
import vetor.NoVet;
import vetor.Vetor;

public class Main {

	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		Vetor vet = new Vetor(8);

		char opcao;
		do {
			System.out.println("========Menu========\n"
					+ "1 - Ler arquivos\n"
					+ "2 - Mostrar Leitura\n"
					+ "3 - Algoritmo de dijkstra\n"
					+ "0 - Sair do programa\n\n"
					+ "Selecione a opção desejada: ");
			opcao = input.next().charAt(0);
			switch (opcao) {
			case '1':
				System.out.println();
				lerArquivos(vet);
				break;
			case '2':
				mostrarDados(vet);
				break;
			case '3':
				algoritmoNoGrafo(vet);
				break;
			case '0':
				System.out.println("Programa finalizado!\n");
				System.out.println("Colaboradores:"
						+ "\nIgor Ofrante, "
						+ "\nKaren Alcantara, "
						+ "\nLucas Sarmento, "
						+ "\nMackweyd Gomes, "
						+ "\nPedro Henrique Fernandes.");
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}
		} while (opcao != '0');

	}

	static void lerArquivos(Vetor vet) {
		String linha = "";
		String narq[] = { "A", "B", "C", "D", "E", "F", "G", "H"}; //vetor com os nomes dos arquivos
		int i = 0;

		try {
			BufferedReader arqEntrada;//objeto para leitura dos arquivos.txt
			int primeiro = 0, fim = 0;
			char letra = ' ';
			double dist = 0.0;
			ListaSimples lista = new ListaSimples();
			for (i = 0; i < narq.length; i++) {
				arqEntrada = new BufferedReader(new FileReader(narq[i] + ".txt"));
				linha = "";
				while ((linha = arqEntrada.readLine()) != null) {
					// vertice
					linha = linha + "\n";
					fim = linha.indexOf("\t");
					letra = linha.substring(0, fim).charAt(0);

					// dist
					primeiro = linha.indexOf("\t") + 1;
					fim = linha.indexOf("\n");
					dist = Double.parseDouble(linha.substring(primeiro, fim));

					// insere na lista
					lista.inserirUltimo(new Item(letra, dist));

				}
				NoVet no = new NoVet(new ItemVet(narq[i].charAt(0), lista));
				vet.inserirDados(no); //insere Nó no vetor
				arqEntrada.close();
				System.out.println("O arquivo " + (narq[i]) + ".txt" + " foi lido.");
				lista = new ListaSimples();
			}
			// fim for
			System.out.println("Leitura Finalizada!");
			System.out.println();
		} catch (Exception e) {
			System.out.println("Leitura com erro\n");
		}

	}

	static void mostrarDados(Vetor v) {
		if (!v.eVazia()) {
			System.out.println("\nOs dados inseridos foram:\n");
			System.out.println(v.toString());		
		}else {
			System.out.println("\nO vetor esta vazio!\n");
		}
	}
	
	static void algoritmoNoGrafo(Vetor vet) {
		if(!vet.eVazia()) {
			System.out.println("\nTecle a cidade de destino(B à H)");
			char cidade = input.next().charAt(0);
			if(cidade>=97 && cidade <=104 || cidade>=65 && cidade<=72) {// Tabela ASCII
				if(cidade>=97 && cidade <=104) { // minusculo para maisculo
					cidade-=32;
				}
				System.out.println();
				System.out.println(vet.algoritmo(cidade).imprimirCaminho());
				System.out.println();
				System.out.println("Para exibir o caminho de outra região,\nselecione a opção 3 novamente.\n");
			}else {
				System.out.println("Essa cidade não está no mapa!");
				System.out.println();
			}		
		}else {
			System.out.println("O vetor está vazio!");
		}
	}	

}
