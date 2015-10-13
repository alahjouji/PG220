package gestion;
import gestion.exceptions.*;
import gestion.fichiers.*;
import gestion.methodes.*;

import java.io.*;
import java.util.*;

import javax.xml.stream.XMLStreamException;

/*la classe Devis est la classe de lancement du projet. Elle recupere la liste des commandes d'un client et
 la liste des fournisseurs a partir de la ligne de commande. Elle essaye de verifier s'il n'y a pas de
 commandes avec meme longueur, meme largeur et des id different (si oui renvoi a une exception).
 Pour chaque fournisseur elle definit la liste de rejet, supprime les commandes a rejeter de la liste des
 commandes , decoupe les commandes restantes a chaque fois selon l'une des 3 methodes pour avoir une liste 
 de decoupes, cree le nombre de planches necessaires pour tous les decoupes puis genere pour chaque methode 
 le fichier xml et svg de sumulation pour le fournisseur concerne.*/
public class Devis {
	public static void main(String[] args) {
		try{
			Rejet rejet=new Rejet();		
			ArrayList<ElementLecture> commandes = XML.readXML(args[0]);
		    //renvoyer une exception dans le cas de 2 commandes avec les memes dimensions et des id 
			//differents
			for(ElementLecture e:commandes){
				for(ElementLecture e1:commandes){
					if(e.largeur==e1.largeur && e1.longueur==e.longueur && e.id!=e1.id)
						throw new IdCommandeInvalide();
				}
			}
			ArrayList<ElementLecture> fournisseurs = XML.readXML(args[1]);
			
			ArrayList<Commande> commandes1 =new ArrayList<Commande>();
			//creer une liste de commandes a partir de la liste des ElementLecture recupere a partir du 
			//fichier commandes.txt
			for(ElementLecture e:commandes){
				Commande c=(Commande)e;
				commandes1.add(c);
			}
			
			for(ElementLecture e:fournisseurs){
				//creer une liste de fournisseurs a partir de la liste des ElementLecture recupere 
			    //a partir du fichier fournisseurs.txt 
				Fournisseur f=(Fournisseur)e;
				//trouver la liste de rejet pour chaque fournisseur a partir de la liste des commandes
				rejet.rejeter(commandes1, f);
				ArrayList<Commande> comrejet=rejet.getComrejet();
				ArrayList<Commande> commandes2=new ArrayList<Commande>();
				//creer une liste de commandes a qui on va retirer les commandes a rejeter
				for(Commande c:commandes1){
					commandes2.add(c);
				}
				Iterator<Commande> itr = commandes2.iterator();
				
			    while(itr.hasNext()){
			    	 Commande c = itr.next();
			    	 if(comrejet.contains(c)){
			    		 itr.remove();
			    	 }
			    }			
			    for(int j=1;j<=3;j++){
			    	ArrayList<Decoupe> decoupes=new ArrayList<Decoupe>();
			    	if(j==1){
			    	//decouper la liste des commandes dans des planches que fournit f selon la methode1
			    		decoupes=Methode1.decouper(commandes2, f);
			    	}else if(j==2){
			    	//decouper la liste des commandes dans des planches que fournit f selon la methode2
			    		decoupes=Methode2.decouper(commandes2, f);
			    	}else if(j==3){
			    	//decouper la liste des commandes dans des planches que fournit f selon la methode3
			    		decoupes=Methode3.decouper(commandes2, f);
			    	}
					ArrayList<Planche> planches=new ArrayList<Planche>();
					int a=0;
					//le nombre de planches necessaires est l'id de la planche qui contient la derniere 
					//decoupe
					for(Decoupe d:decoupes){
						a=d.getP().getId();
					}
					//creer les planches necessaires
					for(int i=0;i<a;i++){
						Planche p=new Planche(i+1, f.getId(), f.getLargeur(), f.getLongueur(), f.getPrix());
						planches.add(p);
					}
					//generer le fichier xml et svg des decoupes selon l'une des methodes pour  
					//le fourisseur correspondant
					XML.writeXML(planches, decoupes, rejet, f, j, f.getPrix()*planches.size());
			    }				   
				System.out.println("les fichiers de simulation qui correspondent au fournisseur "
			    +f.getId()+" selon les 3 methodes sont generes dans le dossier generated qui se trouve " +
			    "dans le dossier bin");
			
			}
		//les exceptions a gerer
		}catch(XMLStreamException e){
			System.out.println(e);
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}catch(DimsFournisseurInvalides e){
			System.out.println(e);
		}catch(DimsCommandeInvalides e){
			System.out.println(e);
		}catch(IdCommandeInvalide e){
			System.out.println(e);
		}
	}
}
