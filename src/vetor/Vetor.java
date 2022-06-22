package vetor;

import dados.Item;
import listaSimples.ListaSimples;
import listaSimples.No;

public class Vetor {

	private NoVet[] vet;
	private int quantVet;

	public Vetor(int tamanho) {
		this.vet = new NoVet[tamanho];
		quantVet = 0;
	}

	public int getQuantVet() {
		return this.quantVet;
	}

	public NoVet getVet(int indice) {
		return vet[indice];
	}

	public void setVetor(int indice, NoVet lista) {
		this.vet[indice] = lista;
	}

	public void setQuantVet(int novoValor) {
		this.quantVet = novoValor;
	}

	public boolean eVazia() {
		return this.quantVet == 0;
	}

	public boolean eCheia() {
		return this.quantVet == this.vet.length;
	}

	// inserir dados no vetor
	public boolean inserirDados(NoVet no) {
		if (this.eCheia()) {
			return false;
		} else {
			this.vet[this.quantVet] = no;
			this.quantVet++;
			return true;
		}
	}

	//mostra o conteudo do vetor
	public String toString() {
		String msg = "";
		No atual;
		for (int i = 0; i < this.vet.length; i++) {
			msg += this.vet[i].getInfo().getVertice() + "	";
			atual = this.vet[i].getInfo().getLista().getPrim();
			while (atual != null) {
				msg += atual.getInfo().getVertice() + " " + atual.getInfo().getPeso() + "	";
				atual = atual.getProx();
			}
			msg += "\n";
		}
		return msg;
	}
	
    //retorna o nó com o menor valor da lista
	public No procurarMenor(ListaSimples lista) {
		No atual;
		No menor;
		atual = lista.getPrim();
		menor = lista.getPrim();
		while (atual != null) {
			if (menor.getInfo().getPeso() > atual.getInfo().getPeso()) {
				menor = atual;
			}
			atual = atual.getProx();
		} 
		return menor;
	}
	
    //retorna a lista preenchida com os vertices. 
	public ListaSimples preencherLista() {
		ListaSimples lista = new ListaSimples();
		for (int i = 0; i < this.quantVet; i++) {
			lista.inserirUltimo(new Item(this.vet[i].getInfo().getVertice(), (double) i));
		}
		return lista;
	}
	
	//retornar uma lista copia.
	public ListaSimples copiarLista (int i) {
		ListaSimples lista = new ListaSimples();
		No atual = this.vet[i].getInfo().getLista().getPrim();
		while (atual != null) {
			lista.inserirUltimo(new Item(atual.getInfo().getVertice(), atual.getInfo().getPeso()));	
			atual = atual.getProx();
		}
		return lista;
	}
	
	//faz a soma do valor do nó com os seus adjacentes, e retorna a lista com os valores somados.
	public ListaSimples somarValores(int i, double peso) {
		No atual = copiarLista(i).getPrim();
		ListaSimples aux = new ListaSimples();
		No aux2;
		while (atual != null) {
			aux.inserirUltimo(atual.getInfo());
			atual = atual.getProx();
		}
		aux2 = aux.getPrim();
		while (aux2 != null) {
			if (i == 0) {
				aux2.getInfo().setPeso(aux2.getInfo().getPeso() + 0.0);
			} else {
				aux2.getInfo().setPeso(aux2.getInfo().getPeso() + peso);
			}
			aux2 = aux2.getProx();
		}
		return aux;
	}
	
    //retorna o indice da chave no vetor.
	public int procuraI(char chave) {
		int i = 0;
		while (this.vet[i].getInfo().getVertice() != chave) {
			i++;
		}
		return i;
	}
	
    //retorna o indice da chave no vetor indice passado por parâmetro
	public int procurarIndice(char[] indice, char chave) {
		int i = 1;
		while (indice[i] != chave) {
			i++;
		}
		return i;
	}

	//faz o algoritmo
	public ListaSimples algoritmo(char chave) {
		No aux = null;
		double peso = 0.0;
		ListaSimples lista = new ListaSimples();
		ListaSimples naoVisitados = new ListaSimples();
		ListaSimples estimativa = new ListaSimples();   //custo
		char[] precedentes = new char[this.vet.length]; 
		char[] indice = new char[this.vet.length];      //é usado para "atribuir um indice" a cada Nó do vetor
		estimativa = preencherLista();                  //preenche a estimativa com todos os vertices do vetor
		No atual = estimativa.getPrim();
		No atual2;
		int i = 0;

		//preenche o vetor com todos os vertices presentes na estimativa
		while (atual != null) {
			indice[i] = atual.getInfo().getVertice();
			atual = atual.getProx();
			i++;
		}

		//preenche a lista de "estimativa" com o valor infinito e o vetor de "precendente" com 0. 
		i = 0;
		atual = estimativa.getPrim();
		while (atual != null) {
			atual.getInfo().setPeso(Integer.MAX_VALUE);
			precedentes[i] = '0';
			i++;
			atual = atual.getProx();
		}
		
		naoVisitados = preencherLista(); //preenche a lista "nãoVisitados" com todos os vertices
		i = 0;
		while (!naoVisitados.eVazia()) {
			lista = somarValores(i, peso);
			
			atual2 = lista.getPrim(); 
			atual = estimativa.getPrim(); 
			while (atual2 != null) {
				while (atual != null) {
					if (atual.getInfo().getVertice() == atual2.getInfo().getVertice()) { //faz a comparação do valor da estimativa com o valor da lista com os adjacentes somados. 
						if (atual2.getInfo().getPeso() < atual.getInfo().getPeso()) {    //verifica se o valor do peso do nó somado é menor do que o que está na estimativa.
							atual.getInfo().setPeso(atual2.getInfo().getPeso());         //coloca o valor menor na lista de estimativa.
							precedentes[procurarIndice(indice, atual.getInfo().getVertice())] = indice[i];
						}
					}
					atual = atual.getProx();
				}
				atual = estimativa.getPrim();
				atual2 = atual2.getProx();
			}
			naoVisitados.removerNo(indice[i]);                      //remove os nos que ja processados.
			aux = procurarMenor(verificarNos(naoVisitados, lista)); //veririficarNos: retornar a lista de nos não visitados. O procurarMenor: procurar menor valor da lista retornada.  
			if (naoVisitados.getPrim() != null) {
				if (aux == null) {
					lista = verificarNos(estimativa, naoVisitados); 
					aux = procurarMenor(lista);
					peso = aux.getInfo().getPeso();
					i = procuraI(aux.getInfo().getVertice());
				} else {
					peso = aux.getInfo().getPeso();
					i = procuraI(aux.getInfo().getVertice());
				}
			}	
		}	
		return fazerCaminho(indice, precedentes, chave);
	}
	
	//retorna o menor caminho ordenado.
	public ListaSimples fazerCaminho(char[] indice, char[] precedentes, char chave) {
		ListaSimples caminho = new ListaSimples();
		caminho.inserirUltimo(new Item(chave,0.0));
		char ult = chave;
		while (caminho.getUlt().getInfo().getVertice()!='A') {
			caminho.inserirUltimo(new Item(precedentes[procurarIndice(indice, ult)],0.0));
			ult=caminho.getUlt().getInfo().getVertice();
		}
		caminho = caminho.reverterCaminho();
		return caminho;
	}
	
	//faz a comparação dos nós de duas lista, e retorna uma lista com os nos iguais.
	public ListaSimples verificarNos(ListaSimples L1, ListaSimples L2) {
		ListaSimples lista = new ListaSimples();
		No atual = L1.getPrim(); // ANDA PRIMEIRO
		No atual2 = L2.getPrim(); // PARADO
		while (atual2 != null) {
			while (atual != null) {
				if (atual.getInfo().getVertice() == atual2.getInfo().getVertice()) {
					lista.inserirUltimo(atual.getInfo());
				}
				atual = atual.getProx();
			}
			atual = L1.getPrim();
			atual2 = atual2.getProx();
		}
		return lista;
	}
}
