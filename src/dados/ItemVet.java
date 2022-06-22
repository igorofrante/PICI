package dados;

import listaSimples.ListaSimples;

public class ItemVet { 
	private char vertice; //vertice 
	private ListaSimples lista; //lista de adjacentes ao vertice
	
	public ItemVet(char vertice, ListaSimples lista) {
		this.vertice = vertice;
		this.lista = lista;
	}

	public char getVertice() {
		return vertice;
	}

	public void setVertice(char vertice) {
		this.vertice = vertice;
	}

	public ListaSimples getLista() {
		return lista;
	}

	public void setLista(ListaSimples lista) {
		this.lista = lista;
	}

}
