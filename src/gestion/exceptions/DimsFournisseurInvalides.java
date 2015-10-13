package gestion.exceptions;

/* classe d'exception leve si la longueur d'une planche d'un fournisseur est inferieur a sa largeur */

//pour eviter un Warning de serial version Id carla classe exception implemente l'interface serializable
@SuppressWarnings("serial")
public class DimsFournisseurInvalides extends Exception{
	public DimsFournisseurInvalides() {
		super("la largeur des planches fournis par un fournisseur doit etre inferieure ou egale a leur longueur");
	}
}
