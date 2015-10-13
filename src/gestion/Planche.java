package gestion;

import gestion.exceptions.*;

/*La classe Planche est une sous classe de Fournisseur qui contient les attributs id (fournisseur), longueur, 
 largeur, prix et id (planche). Elle contient aussi un constructeur et les accesseurs qui correspondent a 
 ses attributs.*/

public class Planche  extends Fournisseur {
	private int id;

	public Planche(int id, int idf, int largeur, int longueur, float prix)throws DimsFournisseurInvalides{
		super(idf, largeur, longueur, prix);
		this.id=id;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
