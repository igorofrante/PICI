package dados;

public class Item {
	private char vertice;
	private double peso;
	
	public Item(char vertice, double peso) {
		this.peso = peso;
		this.vertice = vertice;
	}	

	public char getVertice() {
		return vertice;
	}

	public void setVertice(char vertice) {
		this.vertice = vertice;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
}
