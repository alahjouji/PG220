package gestion;

/*La classe Elementlecture contient les attributs id, largeur et longueur . Elle contient aussi un 
 constructeur et les accesseurs qui correspondent a ses attributs.*/

public abstract class ElementLecture {
	protected int id;
	protected int largeur;
	protected int longueur;
	
	public ElementLecture(int id, int largeur, int longueur){
		setId(id);
		setLargeur(largeur);
		setLongueur(longueur);
	}
	public int getId() {
		return id;
	}
	public int getLargeur() {
		return largeur;
	}
	public int getLongueur() {
		return longueur;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
}
