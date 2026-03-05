package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMax) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtalsMax);
	}

	private class Marche {
		private Etal[] etals;

		private Marche(int nbEtalsMax) {
			etals = new Etal[nbEtalsMax];
			for (int i = 0; i < nbEtalsMax; i++) {
				etals[i] = new Etal();
			}
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (!etals[indiceEtal].isEtalOccupe()) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);

			} else {
				System.out.println("L'étal " + (indiceEtal + 1) + " est déjà occupé.");
			}
		}

		public int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() == false) {
					return i;
				}
			}
			return -1;

		}
		
		public Etal[] trouverEtals(String produit) {
			int nombreEtalSelectionne=0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nombreEtalSelectionne++;
						
				}
			}
			Etal[] nouveau = new Etal[nombreEtalSelectionne];
			for (int i = 0, j =0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					 nouveau[j]=etals[i];
					 j++;
				}

		}
			return nouveau;
	}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i=0;i<etals.length;i++) {
				if (etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		public String afficherMarche(){
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide=0;
			for(int i=0;i<etals.length;i++) {
				if (etals[i].isEtalOccupe()==false ) {
					nbEtalVide++;	
				}else {
					chaine.append(etals[i].afficherEtal());
				}
				
				}
			chaine.append("Il reste " + nbEtalVide
                    + " étals non utilisés dans le marché.\n");
			return chaine.toString();
		}
		
		
		
		
		
	}	

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		if (chef == null) {
	        throw new VillageSansChefException("Le village n'a pas de chef !");
	    }
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	 public String installerVendeur(Gaulois vendeur, String produit,int 
			 nbProduit) {
		 StringBuilder chaine = new StringBuilder();
		    chaine.append(vendeur.getNom() +
		            " cherche un endroit pour vendre "
		            + nbProduit + " " + produit + ".\n");

		    int indice = marche.trouverEtalLibre();

		    if (indice == -1) {
		        chaine.append("Il n'y a plus d'étal libre.\n");
		    } else {
		        marche.utiliserEtal(indice, vendeur, produit, nbProduit);
		        chaine.append("Le vendeur " + vendeur.getNom()
		                + " vend des " + produit
		                + " à l'étal n°" + (indice + 1) + ".\n");
		    }

		    return chaine.toString();
	 }
	 
	 
	 public String rechercherVendeursProduit(String produit) {
		 StringBuilder chaine = new StringBuilder();

		    Etal[] etalsProduit = marche.trouverEtals(produit);

		    if (etalsProduit.length == 0) {
		        chaine.append("Il n'y a pas de vendeur qui propose des "
		                + produit + " au marché.\n");
		    } else if (etalsProduit.length == 1) {
		        chaine.append("Seul le vendeur "
		                + etalsProduit[0].getVendeur().getNom()
		                + " propose des " + produit + " au marché.\n");
		    } else {
		        chaine.append("Les vendeurs qui proposent des "
		                + produit + " sont : ");
		        for (Etal etal : etalsProduit) {
		            chaine.append(" - "
		                    + etal.getVendeur().getNom());
		        }
		        chaine.append("\n");
		    }

		    return chaine.toString();
	 }
	 
	 
	 public Etal rechercherEtal(Gaulois vendeur) {
		    return marche.trouverVendeur(vendeur);
		}
	 
	 
	 public String partirVendeur(Gaulois vendeur) {

		    StringBuilder chaine = new StringBuilder();

		    Etal etal = marche.trouverVendeur(vendeur);

		    if (etal != null) {
		        chaine.append(etal.libererEtal());
		    }

		    return chaine.toString();
		}
	 
	 public String afficherMarche() {

		    StringBuilder chaine = new StringBuilder();

		    chaine.append("Le marché du village \""
		            + nom + "\" possède plusieurs étals :\n");

		    chaine.append(marche.afficherMarche());

		    return chaine.toString();
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}