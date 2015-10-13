package gestion;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import javax.xml.stream.XMLStreamException;

import gestion.exceptions.*;
import gestion.fichiers.*;

//classe de test des rejets
public class TestRejet {
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
				for(Commande c:comrejet){
					System.out.println("le fournisseur "+f.getId()+" ne peut pas fournir des planches pour la commande "
							+c.getId());
				}
				System.out.println("nombre total des rejets pour le fournisseur "+f.getId()+" est "+rejet.getNombre());
			}
		}catch(XMLStreamException e){
			System.out.println(e);
		}catch(FileNotFoundException e){
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
