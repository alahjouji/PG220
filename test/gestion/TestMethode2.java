package gestion;


import gestion.exceptions.*;
import gestion.fichiers.*;
import gestion.methodes.*;

import java.io.*;
import java.util.*;

import javax.xml.stream.XMLStreamException;

//classe pour tester la methode2 genere pour chaque fournisseur le fichier xml et svg de simulation selon 
//la methode2
public class TestMethode2 {
	public static void main(String[] args) {
		try{
			Rejet rejet=new Rejet();		
			ArrayList<ElementLecture> commandes = XML.readXML(args[0]);
			for(ElementLecture e:commandes){
				for(ElementLecture e1:commandes){
					if(e.largeur==e1.largeur && e.longueur==e1.longueur && e.id!=e1.id)
						throw new IdCommandeInvalide();
				}
			}
			ArrayList<ElementLecture> fournisseurs = XML.readXML(args[1]);

			ArrayList<Commande> commandes1 =new ArrayList<Commande>();
			for(ElementLecture e:commandes){
				Commande c=(Commande)e;
				commandes1.add(c);
			}
			for(ElementLecture e:fournisseurs){
				Fournisseur f=(Fournisseur)e;
				rejet.rejeter(commandes1, f);
				ArrayList<Commande> comrejet=rejet.getComrejet();
				ArrayList<Commande> commandes2=new ArrayList<Commande>();
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
			    ArrayList<Decoupe> decoupes=Methode2.decouper(commandes2, f);
				ArrayList<Planche> planches=new ArrayList<Planche>();
				int a=0;
				for(Decoupe d:decoupes){
					a=d.getP().getId();
				}
				for(int i=0;i<a;i++){
					Planche p=new Planche(i+1, f.getId(), f.getLargeur(), f.getLongueur(), f.getPrix());
					planches.add(p);
				}
			    
			    XML.writeXML(planches, decoupes, rejet, f, 2, f.getPrix()*planches.size());
			    System.out.println("les fichiers de simulation qui correspondent au fournisseur "
					    +f.getId()+" selon la methode2 sont generes dans le dossier generated qui se trouve " +
					    "dans le dossier bin");
			}
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
