package gestion;


import gestion.exceptions.*;
import gestion.fichiers.XML;

import java.io.*;
import java.util.ArrayList;


import javax.xml.stream.XMLStreamException;

//classe de test de la generation des fichiers xml et svg de simulation pour un certain 
//fournisseur et des commandes selon l'algorithme 1

public class TestEcritureXML {
	public static void main(String[] args) {
		try{
			Rejet rejet=new Rejet();
			ArrayList<Commande> commandes =new ArrayList<Commande>();
			Commande c1=new Commande(1,3,4,1);
			commandes.add(c1);
			Commande c2=new Commande(2,4,6,2);
			commandes.add(c2);
			Commande c3=new Commande(3,4,15,1);
			commandes.add(c3);
			
			Fournisseur f=new Fournisseur(1,8,11,4);

			ArrayList<Planche> planches = new ArrayList<Planche>();
			Planche p1=new Planche(1,f.getId(),f.getLargeur(),f.getLongueur(),f.getPrix());
			Planche p2=new Planche(2,f.getId(),f.getLargeur(),f.getLongueur(),f.getPrix());
			planches.add(p1);
			planches.add(p2);
			ArrayList<Decoupe> decoupes = new ArrayList<Decoupe>();
			Decoupe d1=new Decoupe(0,0,p1,c2);
			Decoupe d2=new Decoupe(0,0,p2,c2);
			Decoupe d3=new Decoupe(0,6,p2,c1);
			decoupes.add(d1);
			decoupes.add(d2);
			decoupes.add(d3);
			rejet.rejeter(commandes, f);
			XML.writeXML(planches, decoupes, rejet, f, 1, 8);
			
			System.out.println("les fichiers de simulation qui correspondent au fournisseur selon la methode1" +
					"sont generes dans le dossier generated qui se trouve dans le dossier bin");
			
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
		}
	}
}
