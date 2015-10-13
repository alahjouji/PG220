package gestion;

import gestion.exceptions.*;

/*La classe Fournisseur est une sous classe de Elementlecture qui contient les attributs id, largeur, longueur 
et prix. Elle contient aussi les accesseurs qui correspondent a ses attributs. Le constructeur 
renvoi a une exception dans le cas d'un fournisseur de planches de largeur superieur a la longueur*/

public class Fournisseur extends ElementLecture{

	protected float prix;
	
	public Fournisseur(int id, int largeur, int longueur, float prix)throws DimsFournisseurInvalides{
		super(id, largeur, longueur);		
		if (longueur < largeur) {
			throw new DimsFournisseurInvalides();
		}
		setPrix(prix);
		
	}
	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

}
