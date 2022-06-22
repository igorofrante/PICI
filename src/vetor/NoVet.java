package vetor;

import dados.ItemVet;

public class NoVet { //classe criada para separar o vertice da lista de adjacentes
	private ItemVet info;
	
	public NoVet(ItemVet elem) {
		this.info = elem;
	}
	public ItemVet getInfo() {
		return info;
	}
	public void setInfo(ItemVet info) {
		this.info = info;
	}
	
}
