package gestion;

import java.util.ArrayList;

/*La classe Rejet contient les attributs comrejet et nombre. Elle contient aussi une methode rejeter 
et les accesseurs qui correspondent a ses attributs.*/

public class Rejet {
	private ArrayList<Commande> comrejet;//tableau de commandes a rejeter
	int nombre;//nombre de commandes a rejeter
	
	public ArrayList<Commande> getComrejet() {
		return comrejet;
	}

	public int getNombre() {
		return nombre;
	}

	public void setComrejet(ArrayList<Commande> comrejet) {
		this.comrejet = comrejet;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
/*la methode rejeter remplit les champs comrejet et nombre d'un objet de classe Rejet avec les commandes 
 a rejeter et leur nombre parmi les commandes en entree selon les specifites du fournisseur en entree;
 Les commandes a rejeter ont une largeur OU longueur superieur a celle des planches du fournisseur*/
	public void rejeter(ArrayList<Commande> commandes, Fournisseur f){
		
		comrejet = new ArrayList<Commande>();
		nombre=0;
		for(Commande c:commandes){
			if(c.getLargeur()>f.getLargeur() || c.getLongueur()>f.getLongueur()){
				comrejet.add(c);
				nombre++;
			}
		}

	}
	
}
