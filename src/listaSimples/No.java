package listaSimples;

import dados.Item;

public class No {
	private Item info;
	private No prox;
	
	public No(Item elem) {
		this.info = elem;
		this.prox = null;
	}
	public Item getInfo() {
		return info;
	}
	public void setInfo(Item info) {
		this.info = info;
	}
	public No getProx() {
		return prox;
	}
	public void setProx(No prox) {
		this.prox = prox;
	}
	
}
