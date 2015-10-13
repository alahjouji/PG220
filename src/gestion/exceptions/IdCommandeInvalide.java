package gestion.exceptions;

/* classe d'exception leve si 2 commandes ayant memes dimensions ont des id differents */

//pour eviter un Warning de serial version Id carla classe exception implemente l'interface serializable
@SuppressWarnings("serial")
public class IdCommandeInvalide extends Exception{
	public IdCommandeInvalide() {
		super("les commandes ayant des largeurs et longueurs egaux ne peuvent avoir des id differents");
	}
}
