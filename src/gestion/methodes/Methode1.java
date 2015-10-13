package gestion.methodes;
import gestion.*;
import gestion.exceptions.*;

import java.util.*;

/* la classe Methode1 est une classe abstraite qui contient 1 methode statique pour decouper une liste
 de commandes sur les planches fournit par un certain fournisseur selon la premiere methode */


public abstract class Methode1{
	
	//methode de decoupe (on lui donne une liste de commandes qui ne contient pas les commandes rejetes)
	public static ArrayList<Decoupe> decouper(ArrayList<Commande> commandes, Fournisseur f)throws DimsFournisseurInvalides, DimsCommandeInvalides{
		ArrayList<Planche> planches=new ArrayList<Planche>();
		ArrayList<Decoupe> decoupes=new ArrayList<Decoupe>();
		ArrayList<Commande> commandes1=new ArrayList<Commande>();
		int i;
		int length;
		//creer une liste de commandes en duppliquant les commandes de quantite>1
		for(Commande c:commandes){
			for(i=0;i<c.getQuantite();i++){
				Commande c1=new Commande(c.getId(), c.getLargeur(), c.getLongueur(),1);
				commandes1.add(c1);
			}
		}
		//trier les commandes par ordre de longueur decroissante (en cas d'egalite par ordre de largeur 
		//decroissante)
		Collections.sort(commandes1,Commande.CommandeLongueurComparator);
		i=0;
		int j=1;
		//tant q'on a pas atteint la fin de la liste on cree une nouvelle planche on met les decoupes l'une 
		//au dessus de l'autre tant que la somme des longueurs des decoupes precedentes avec celle la 
		//est inferieur a la longueur de la planche
		while(i<commandes1.size()){
			length=0;
			Planche p=new Planche(j, f.getId(), f.getLargeur(), f.getLongueur(), f.getPrix());
			planches.add(p);
			while(i<commandes1.size() && length+commandes1.get(i).getLongueur()<=f.getLongueur()){
				Decoupe d=new Decoupe(0,length,p,commandes1.get(i));
				decoupes.add(d);
				length=length+commandes1.get(i).getLongueur();
				i++;
			}
			j++;
		}

		return decoupes;
	}
	
}
