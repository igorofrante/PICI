package listaSimples;

import dados.Item;

public class ListaSimples {
	private No prim;
	private No ult;
	private int quantNos;

	public ListaSimples() {
		this.prim = null;
		this.ult = null;
		this.quantNos = 0;
	}

	public No getPrim() {
		return prim;
	}

	public void setPrim(No prim) {
		this.prim = prim;
	}

	public No getUlt() {
		return ult;
	}

	public void setUlt(No ult) {
		this.ult = ult;
	}

	public boolean eVazia() {
		return (this.prim == null);
	}

	public int getQuantNos() {
		return quantNos;
	}

	public void setQuantNos(int quantNos) {
		this.quantNos = quantNos;
	}

	// insere na ultima posicao da lista
	public void inserirUltimo(Item elem) {
		No novoNo = new No(elem);
		if (this.eVazia()) {
			this.prim = novoNo;
		} else {
			this.ult.setProx(novoNo);
		}
		this.ult = novoNo;
		this.quantNos++;
	}

	// remove um determinado nó em qualquer posição na lista.
	public boolean removerNo(int chave) {
		No atual = this.prim;
		No ant = null;
		if (eVazia()) {
			return false;
		} else {
			while ((atual != null) && (atual.getInfo().getVertice() != chave)) {
				ant = atual;
				atual = atual.getProx();
			}
			if (atual == null) {
				return false;
			} else {
				if (atual == this.prim) {
					if (this.prim == this.ult) {
						this.ult = null;
					}
					this.prim = this.prim.getProx();
				} else {
					if (atual == this.ult) {
						this.ult = ant;
					}
					ant.setProx(atual.getProx());
				}
				this.quantNos--;
				return true;
			}
		}
	}
	
	//retorna o caminho ordenado
	public ListaSimples reverterCaminho() {
		ListaSimples caminho = new ListaSimples();
		No atual = this.getUlt();
		
		while (atual != null) {
			caminho.inserirUltimo(atual.getInfo());
			this.removerNo(atual.getInfo().getVertice());
			atual = this.getUlt();
		}
		
		return caminho;
	}
	
	//mostra o caminho ordenado
	public String imprimirCaminho() {
		String msg = "";
		No atual = this.getPrim();
		while (atual != null) {
			if (atual.getProx() != null) {
				msg += atual.getInfo().getVertice() + " -> ";
			}else {
				msg += atual.getInfo().getVertice();
			}
			atual = atual.getProx();
		}
		return msg;
	}

	public String toString() {
		String msg = "";
		No atual = this.getPrim();
		while (atual != null) {
			msg += atual.getInfo().getVertice() + " " + atual.getInfo().getPeso() + "	";
			atual = atual.getProx();
		}
		return msg;
	}

}
