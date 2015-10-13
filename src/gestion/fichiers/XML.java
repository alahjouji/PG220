package gestion.fichiers;

import gestion.*;

import java.util.ArrayList;

import java.io.*;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import gestion.exceptions.*;
public abstract class XML {

/*la classe XML est une classe abstraite qui contient 2 methodes statiques une pour lire un fichier xml et
 l'autre pour generer les 2 fichiers xml et svg*/	
	
	//cette methode genere une liste d'ElementLecture a partir du fichier (commandes.txt ou fournisseurs.txt)
	public static ArrayList<ElementLecture> readXML(String file) throws XMLStreamException, FileNotFoundException, DimsFournisseurInvalides, DimsCommandeInvalides{
		
		ArrayList<ElementLecture> elements = new ArrayList<ElementLecture>();
		Fournisseur f;
		Commande c;
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new FileReader(file));
		int eventType;
		while (xmlsr.hasNext()) {
			eventType = xmlsr.next();
			if(eventType==XMLEvent.START_ELEMENT && xmlsr.getLocalName()=="fournisseur") {
				//si on retrouve un element "fournisseur" on cree un objet de classe Fournisseur et on 
				//l'ajoute a la liste d'ElementLecture
				f=new Fournisseur(Integer.parseInt(xmlsr.getAttributeValue(null, "id")),
								  Integer.parseInt(xmlsr.getAttributeValue(null, "largeur")),
								  Integer.parseInt(xmlsr.getAttributeValue(null, "longueur")),
								  Float.valueOf(xmlsr.getAttributeValue(null, "prix")));
				elements.add(f);
			}else if(eventType==XMLEvent.START_ELEMENT && xmlsr.getLocalName()=="commande") {
				//si on retrouve un element "commande" on cree un objet de classe Commande et on 
				//l'ajoute a la liste d'ElementLecture
				c=new Commande(Integer.parseInt(xmlsr.getAttributeValue(null, "id")),
								  Integer.parseInt(xmlsr.getAttributeValue(null, "largeur")),
								  Integer.parseInt(xmlsr.getAttributeValue(null, "longueur")),
								  Integer.parseInt(xmlsr.getAttributeValue(null, "quantite")));
				elements.add(c);
			}
		}
		return elements;
	}
	
	//cette methode genere le fichier xml et le fichier svg de la simulation des decoupes en entree
	public static void writeXML(ArrayList<Planche> planches, ArrayList<Decoupe> decoupes, Rejet rejet, Fournisseur f, int algo, float prix) throws XMLStreamException, IOException{
	    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
	    //les fichiers generes se trouvent dans le dossier generated qui se trouve dans le dossier bin
	    
	    //l'objet qui correspond au fichier resultat qui decrit les decoupes selon l'algo
	    FileWriter output = new FileWriter(new File("generated/results.f"+f.getId()+".m"+algo+".xml"));
	    XMLStreamWriter xmlsw = outputFactory.createXMLStreamWriter(output);
	  //l'objet qui correspond au fichier resultat qui montre graphiquement les decoupes selon l'algo
	    FileWriter output1 = new FileWriter(new File("generated/results.f"+f.getId()+".m"+algo+".svg"));
	    XMLStreamWriter xmlsw1 = outputFactory.createXMLStreamWriter(output1);
	    ArrayList<Commande> comrejet=new ArrayList<Commande>();
	    xmlsw.writeStartDocument();
	    xmlsw.writeStartElement("simulation");
    	xmlsw.writeAttribute("fournisseur",Integer.toString(f.getId()));
    	xmlsw.writeAttribute("prix",Float.toString(prix));
    	xmlsw.writeAttribute("rejets",Integer.toString(rejet.getNombre()));
    	xmlsw.writeAttribute("algorithme",Integer.toString(algo));
    		xmlsw.writeStartElement("rejets");
	    		comrejet=rejet.getComrejet();
	    		for(Commande c:comrejet){
					xmlsw.writeStartElement("rejet");
					xmlsw.writeAttribute("commande",Integer.toString(c.getId()));
					xmlsw.writeEndElement();
	    		}
    		xmlsw.writeEndElement();
	    	xmlsw.writeStartElement("planches");
		    	for(Planche p:planches){
					xmlsw.writeStartElement("planche");
					xmlsw.writeAttribute("id",Integer.toString(p.getId()));
			    		for(Decoupe d:decoupes){
							if(d.getP().getId()==p.getId()){
								xmlsw.writeStartElement("decoupe");
								xmlsw.writeAttribute("commande",Integer.toString(d.getC().getId()));
								xmlsw.writeAttribute("x",Integer.toString(d.getX()));
								xmlsw.writeAttribute("y",Integer.toString(d.getY()));
								xmlsw.writeEndElement();
							}
			    		}
					
					xmlsw.writeEndElement();
		    	}
	    	xmlsw.writeEndElement();
	    	
    	xmlsw.writeEndElement();
    	xmlsw.flush();
    	xmlsw.close();
    	xmlsw1.writeStartDocument();
	    xmlsw1.writeStartElement("svg");
	    //pour la lecture dans un navigateur web on ajoute l'attribut xmlns avec la valeur suivante
	    //(w3c est l'organisme de normalisation qui a developpe le format svg)
    	xmlsw1.writeAttribute("xmlns","http://www.w3.org/2000/svg");
    	xmlsw1.writeAttribute("version","1.1");
    	//les attributs width et height pour definir la longueur et la largeur de l'image svg
    	//elle depend du nombre de planches et leurs dimensions
    	//pour bien voir l'image svg on a multiplie les dimensions par 10
    	xmlsw1.writeAttribute("width",String.format("%d", 10*(10+f.getLargeur())));
    	xmlsw1.writeAttribute("height",String.format("%d", 10*(planches.size()*(f.getLongueur()+5))+10));
    	int i=0;
	    	for(Planche p:planches){
				xmlsw1.writeStartElement("text");
				//le texte "planche i" est ecrit au dessus des rectangles bleus
		    	xmlsw1.writeAttribute("x",String.format("%d",10*(5)));
		    	xmlsw1.writeAttribute("y",String.format("%d", 10*(4+i*(f.getLongueur()+5)))); 
		    	xmlsw1.writeCharacters(String.format("planche %d",i+1));
		    	xmlsw1.writeEndElement();
		    	//le grand rectangle bleu
				xmlsw1.writeStartElement("rect");
		    	xmlsw1.writeAttribute("x",String.format("%d",10*(5)));
		    	xmlsw1.writeAttribute("y",String.format("%d", 10*(5+i*(f.getLongueur()+5))));    	
		    	xmlsw1.writeAttribute("width",String.format("%d", 10*(f.getLargeur())));
		    	xmlsw1.writeAttribute("height",String.format("%d", 10*(f.getLongueur())));
		    	xmlsw1.writeAttribute("style","fill:rgb(128,208,208);stroke:rgb(0,0,0)");
		    	xmlsw1.writeEndElement();
		    		for(Decoupe d:decoupes){
						if(d.getP().getId()==p.getId()){
							//les petits rectangles jaunes chacun a sa position qui depend des dimensions 
							//de chaque decoupe et du decalage
							xmlsw1.writeStartElement("rect");
					    	xmlsw1.writeAttribute("x",String.format("%d",10*(5+d.getX())));
					    	xmlsw1.writeAttribute("y",String.format("%d", 10*(5+i*(f.getLongueur()+5)+d.getY())));    	
					    	xmlsw1.writeAttribute("width",String.format("%d", 10*(d.getC().getLargeur())));
					    	xmlsw1.writeAttribute("height",String.format("%d", 10*(d.getC().getLongueur())));
					    	xmlsw1.writeAttribute("style","fill:rgb(255,255,0);stroke:rgb(0,0,0)");
					    	xmlsw1.writeEndElement();
					    	//l'id de la commande a laquelle appartient la decoupe est ecrit dessus
							xmlsw1.writeStartElement("text");
					    	xmlsw1.writeAttribute("x",String.format("%d",10*(5+d.getX())+4*(d.getC().getLargeur())));
					    	xmlsw1.writeAttribute("y",String.format("%d",10*(5+i*(f.getLongueur()+5)+d.getY())+ 5*(d.getC().getLongueur()))); 
					    	xmlsw1.writeAttribute("style","stroke:rgb(0,0,0)");
					    	xmlsw1.writeCharacters(String.format("%d",d.getC().getId()));
					    	xmlsw1.writeEndElement();
						}
		    		}
					i++;
		    	}
	    	
	    	
    	xmlsw1.writeEndElement();
    	xmlsw1.flush();
    	xmlsw1.close();
	}
}
