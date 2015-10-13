package gestion.exceptions;

/* classe d'exception leve si la longueur d'une commande d'un client est inferieur a sa largeur */

//pour eviter un Warning de serial version Id carla classe exception implemente l'interface serializable
@SuppressWarnings("serial")
public class DimsCommandeInvalides extends Exception{
	public DimsCommandeInvalides() {
		super("la largeur des decoupes commandes par un client doit etre inferieure ou egale a leur longueur");
	}
}
