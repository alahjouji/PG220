package gestion;

import gestion.exceptions.*;
import gestion.fichiers.*;

import java.util.ArrayList;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

//classe de test de la lecture du fichier fournisseurs.xml
public class TestLectureFournisseurs {
	public static void main(String[] args) {
		try{
			ArrayList<ElementLecture> elements = XML.readXML(args[0]);
			for(ElementLecture e:elements){
				Fournisseur f=(Fournisseur)e;
				System.out.println("le fournisseur "+f.getId()+" fournit des planches de largeur "+f.getLargeur()+
							   " et de longueur "+f.getLongueur()+" pour le prix unitaire de "+f.getPrix());
			}
				
		}catch(XMLStreamException e){
			System.out.println(e);
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(DimsFournisseurInvalides e){
			System.out.println(e);
		}catch(DimsCommandeInvalides e){
			System.out.println(e);
		}
	}
}
