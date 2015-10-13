package gestion.methodes;

import gestion.*;

import gestion.exceptions.*;

import java.util.*;

/* la classe Methode1 est une classe abstraite qui contient 1 methode statique pour decouper une liste
de commandes sur les planches fournit par un certain fournisseur selon la troisieme methode */

/*le principe est de classer les decoupes par longueur decroissante (en cas d'egalite par largeur
decroissante). La premiere decoupe d0 est placee si possible a la coordonnee (0,0) ensuite les decoupes
suivantes sont parcourues par ordre de longueur decroissante et la premiere decoupe verifiant l(di1)<=l(d0)
et L(di1)+L(d0)<=L(P). Cette decoupe est alors placee a la coordonnee (0,L(d0)). Ensuite les decoupes
suivantes sont parcourues par ordre de largeur decroissantepour trouver une decoupe di2 telle que
l(di2)<=l(d0) et L(di2)+L(di1)+L(d0)<=L(P). Elle est placee a la coordonnee (0,L(d0)+L(di1)). Cette
operation est repetee jusqu'a ce que l'on ne trouve plus de decoupe de largeur inferieure ou egale a
l(d0) et rentrant sur la longueur de planche restante. Quand c'est le cas on regarde les decoupes
di1 di2 di3... deja places au dessous de d0 si la premiere qui verifie l(d)<l(d0) est par exemple
di4 on parcours les decoupes restantes par ordre de longueur decroissante et la premiere decoupe dn
verifiant l(dn)+l(di4)<=l(d0) et L(dn)+L(di1)+...+ L(di3)<=L(P) "cette condition sera forcement verifiee 
pour la premiere occurence". Cette decoupe est alors placee
a la coordonnee (l(di4),L(di1)+...+ L(di3)).Cette operation est repetee jusqu'a ce que l'on 
ne trouve plus de decoupe de largeur inferieure ou egale a l(d0)-l(di4) et rentrant sur la 
longueur de planche restante. Quand c'est le cas on passe a la decoupe la plus longue restante 
dm qui est placee a la coordonne (l(d0),O) est le processus reprend. S'il n'est plus possible de 
placer cette decoupe sur la planche en cours car la largeur restante est tros petite 
(l(dm)+l(d0)>l(P)) il faut passer a une nouvelle planche.*/

public abstract class Methode3 {
	
	//methode de decoupe (on lui donne une liste de commandes qui ne contient pas les commandes rejetes)
	public static ArrayList<Decoupe> decouper(ArrayList<Commande> commandes, Fournisseur f)throws DimsFournisseurInvalides, DimsCommandeInvalides{
		ArrayList<Planche> planches=new ArrayList<Planche>();
		ArrayList<Decoupe> decoupes=new ArrayList<Decoupe>();
		ArrayList<Commande> commandes1=new ArrayList<Commande>();
		Decoupe d;
		int i;
		int width;
		int width1;
		int length;
		int width2;
		int length1=0;
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
		
		int j=1;
		//tant q'on a pas atteint la fin de la liste on cree une nouvelle planche
		while(commandes1.size()>0){
			Planche p=new Planche(j, f.getId(), f.getLargeur(), f.getLongueur(), f.getPrix());
			planches.add(p);
			//variable pour stocker la somme des largeurs des planches mises en haut
			width=commandes1.get(0).getLargeur();
			//tant qu'on a pas depasser la largeur de la planche on ajoute la premiere decoupe
			while(width<=f.getLargeur()){
				width1=commandes1.get(0).getLargeur();
				d=new Decoupe(width-width1,0,p,commandes1.get(0));
				decoupes.add(d);
				//variable pour stocker la somme des longueurs des decoupes mises l'une au dessous de l'autre
				length=commandes1.get(0).getLongueur();
				//on retire la decoupe ajoutee de la liste des decoupes
				commandes1.remove(0);
	    		i=0;
	    		width2=-1;
	    		//tant qu'on a pas atteint la fin de la liste on verifient les 2 conditions et on ajoute 
	    		//la decoupe au bon endroit
	    		while(i<commandes1.size()){
	    			if(commandes1.get(i).getLargeur()<=width1&& length+commandes1.get(i).getLongueur()<=f.getLongueur()){
	    				if(width2==-1 || width2==0){
	    					//stocker la difference de largeur entre la decoupe mise en haut et celle
	    					//au dessous (pas forcement directement) qui a une largeur strictement inferieure
	    					//(width2>0) et la longueur totale des decoupes au dessus de celle la
	    					width2=width1-commandes1.get(i).getLargeur();
	    					length1=length;
	    				}
	    				d=new Decoupe(width-width1,length,p,commandes1.get(i));
	    				decoupes.add(d);
	    				length=length+commandes1.get(i).getLongueur();
	    				//on retire la decoupe ajoutee de la liste des decoupes
	    				commandes1.remove(i);
	    				i--;
	   			    }
	    			i++;
    			}
	    		i=0;
	    		//si on a trouve une decoupe qui verifie ce qui etait dit precedemment
	    		if(width2>0){
	    			//tant qu'on a pas atteint la fin de la liste on verifient les 2 conditions et on ajoute 
		    		//la decoupe au bon endroit
		    		while(i<commandes1.size()){
		    			if(commandes1.get(i).getLargeur()<=width2&& length1+commandes1.get(i).getLongueur()<=f.getLongueur()){
		    				d=new Decoupe(width-width2,length1,p,commandes1.get(i));
		    				decoupes.add(d);
		    				length1=length1+commandes1.get(i).getLongueur();
		    				//on retire la decoupe ajoutee de la liste des decoupes
		    				commandes1.remove(i);
		    				i--;
		   			    }
		    			i++;
	    			}
	    		}
	    		//si la liste contient toujours des elements incrementer la valeur de la variable de test
	    		if(commandes1.size()>0)
	    			width=width+commandes1.get(0).getLargeur();
	    		//sinon sortir de la boucle en donnant a la variable de test une valeur qui ne verifie pas 
	    		//la condition
	    		else
	    			width=f.getLargeur()+1;
    		}
			j++;
		}
		
		return decoupes;
	}
}
