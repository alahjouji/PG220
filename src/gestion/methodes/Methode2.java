package gestion.methodes;

import gestion.*;
import gestion.exceptions.*;

import java.util.*;

/* la classe Methode1 est une classe abstraite qui contient 1 methode statique pour decouper une liste
de commandes sur les planches fournit par un certain fournisseur selon la deuxieme methode */


public abstract class Methode2 {
	
	//methode de decoupe (on lui donne une liste de commandes qui ne contient pas les commandes rejetes)
	public static ArrayList<Decoupe> decouper(ArrayList<Commande> commandes, Fournisseur f)throws DimsFournisseurInvalides, DimsCommandeInvalides{
		ArrayList<Planche> planches=new ArrayList<Planche>();
		ArrayList<Decoupe> decoupes=new ArrayList<Decoupe>();
		ArrayList<Commande> commandes1=new ArrayList<Commande>();
		Decoupe d;
		int i;
		int length;
		int length1;
		int larg;
		//creer une liste de commandes en duppliquant les commandes de quantite>1
		for(Commande c:commandes){
			for(i=0;i<c.getQuantite();i++){
				Commande c1=new Commande(c.getId(), c.getLargeur(), c.getLongueur(),1);
				commandes1.add(c1);
			}
		}
		//trier les commandes par ordre de largeur decroissante (en cas d'egalite par ordre de longueur 
		//decroissante)
		Collections.sort(commandes1,Commande.CommandeLargeurComparator);
		
		int j=1;
		//tant q'on a pas atteint la fin de la liste on cree une nouvelle planche
		while(commandes1.size()>0){
			Planche p=new Planche(j, f.getId(), f.getLargeur(), f.getLongueur(), f.getPrix());
			planches.add(p);
			//variable pour stocker la somme des longueurs des planches mises a gauche
			length=commandes1.get(0).getLongueur();
			//tant qu'on a pas depasser la longueur de la planche on ajoute la premiere decoupe
			while(length<=f.getLongueur()){
				length1=commandes1.get(0).getLongueur();
				d=new Decoupe(0,length-length1,p,commandes1.get(0));
				decoupes.add(d);
				//variable pour stocker la somme des largeurs des decoupes mises l'une a cote de l'autre
				larg=commandes1.get(0).getLargeur();
				//on retire la decoupe ajoutee de la liste des decoupes
				commandes1.remove(0);
	    		i=0;
	    		//tant qu'on a pas atteint la fin de la liste on verifient les 2 conditions et on ajoute 
	    		//la decoupe au bon endroit
	    		while(i<commandes1.size()){
	    			if(commandes1.get(i).getLongueur()<length1&& larg+commandes1.get(i).getLargeur()<=f.getLargeur()){
	    				d=new Decoupe(larg,length-length1,p,commandes1.get(i));
	    				decoupes.add(d);
	    				larg=larg+commandes1.get(i).getLargeur();
	    				//on retire la decoupe ajoutee de la liste des decoupes
	    				commandes1.remove(i);
	    				i--;
	   			    }
	    			i++;
    			}
	    		//si la liste contient toujours des elements incrementer la valeur de la variable de test
	    		if(commandes1.size()>0)
	    			length=length+commandes1.get(0).getLongueur();
	    		//sinon sortir de la boucle en donnant a la variable de test une valeur qui ne verifie pas 
	    		//la condition
	    		else
	    			length=f.getLongueur()+1;
    		}
			j++;
		}
		
		return decoupes;
	}
}
