package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {

	public static void main(String[] args) { 

		Etal etal = new Etal(); 
		Gaulois abraracourcix = new Gaulois("Abraracourcix", 5);
		Village village = new Village("Village gaulois", 10, 5);

		etal.libererEtal(); 

		try {
		    System.out.println(etal.acheterProduit(0, abraracourcix));
		    System.out.println(village.afficherVillageois());
		} 
		catch (IllegalArgumentException e) {
		    System.out.println("Erreur : " + e.getMessage());
		} 
		catch (IllegalStateException e) {
		    System.out.println("Erreur : " + e.getMessage());
		}
		catch (VillageSansChefException e) {
		    System.out.println("Erreur : " + e.getMessage());
		}

		System.out.println("Fin du test"); 
	} 
}