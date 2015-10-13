package gestion;

import gestion.exceptions.*;

import java.util.Comparator;

/*La classe Commande est une sous classe de Elementlecture qui contient les attributs id, largeur, longueur 
 et quantite. Elle contient aussi 2 comparateurs de commandes; le premier compare selon la plus grande 
 longueur et en cas d'egalite la plus grande largeur alors que le deuxieme compare selon la plus grande 
 largeur et en cas d'egalite la plus grande longueur. La classe contient aussi les accesseurs qui 
 correspondent a ses attributs. Le constructeur renvoi a une exception dans le cas d'une 
 commande de largeur superieur a la longueur*/

public class Commande extends ElementLecture{

	private int quantite;
	
	public Commande(int id, int largeur, int longueur, int quantite) throws DimsCommandeInvalides{
		super(id, largeur, longueur);
		if (longueur < largeur) {
			throw new DimsCommandeInvalides();
		}
		setQuantite(quantite);
		
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public static Comparator<Commande> CommandeLongueurComparator = new Comparator<Commande>() {
	
		public int compare(Commande c1, Commande c2) {
		
			int long1 = c1.getLongueur();
			int long2 = c2.getLongueur();
			int larg1 = c1.getLargeur();
			int larg2 = c2.getLargeur();
			
			if (long2-long1!=0)
				return long2-long1;
			else
				return larg2-larg1;
		
		}
	};
		public static Comparator<Commande> CommandeLargeurComparator = new Comparator<Commande>() {
			
			public int compare(Commande c1, Commande c2) {
			
				int long1 = c1.getLongueur();
				int long2 = c2.getLongueur();
				int larg1 = c1.getLargeur();
				int larg2 = c2.getLargeur();
				
				if (larg2-larg1!=0)
					return larg2-larg1;
				else
					return long2-long1;
			
			}

	};
	
}
