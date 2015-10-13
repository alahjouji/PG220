package gestion;

import gestion.exceptions.*;
import gestion.fichiers.*;

import java.util.ArrayList;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

//classe de test de la lecture du fichier commandes.xml
public class TestLectureCommandes {
	public static void main(String[] args) {
		try{
			ArrayList<ElementLecture> elements = XML.readXML(args[0]);
			for(ElementLecture e:elements){
				for(ElementLecture e1:elements){
					if(e.largeur==e1.largeur && e.longueur==e1.longueur && e.id!=e1.id)
						throw new IdCommandeInvalide();
				}
			}
			for(ElementLecture e:elements){
				Commande c=(Commande)e;
				System.out.println("la commande "+c.getId()+" consiste en "+c.getQuantite()+
				" decoupes de largeur "+c.getLargeur()+" et de longueur "+c.getLongueur());
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
