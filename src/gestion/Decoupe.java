package gestion;

/*La classe Decoupe contient les attributs x, y, p et c Elle contient aussi un constructeur 
 et les accesseurs qui correspondent a ses attributs.*/

public class Decoupe{

	  private int x;//coordonnee x du point en haut a gauche de la decoupe
	  private int y;//coordonnee y du point en haut a gauche de la decoupe
	  private Planche p;//la planche sur laquelle est place la decoupe
	  private Commande c;//la commande qui correspond a la decoupe
	  
    public  Decoupe(int x, int y, Planche p, Commande c){
    	setX(x);
    	setY(y);
    	setP(p);
    	setC(c);
    }
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Planche getP() {
		return p;
	}
	public Commande getC() {
		return c;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setP(Planche p) {
		this.p = p;
	}
	public void setC(Commande c) {
		this.c = c;
	}
	}
