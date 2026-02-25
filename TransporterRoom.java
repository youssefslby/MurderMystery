package FinalGame;

import java.util.Random;

/**
 * Une salle spéciale qui permet de se téléporter aléatoirement vers d'autres salles.
 * Hérite de la classe Room et ajoute des fonctionnalités de téléportation aléatoire.
 *
 * @author Youssef Shalaby
 * @version finale
 */
public class TransporterRoom extends Room
{
    private Room[] aExitRooms; // Tableau des salles accessibles depuis la Transporter Room
    private Random vRandom; // Générateur de nombres pour la sélection de salle

    /**
     * Constructeur de TransporterRoom
     * @param pDescription Description de la salle
     * @param pImageName Nom du fichier image associé à la salle
     */
    public TransporterRoom(final String pDescription, final String pImageName) {
         super(pDescription, pImageName);
         this.vRandom = new Random();
    }// TransporterRoom()
    
    /**
     * Définit les salles accessibles par téléportation
     * @param pRooms Tableau des salles de destination possibles
     */
    public void setExitRooms(final Room[] pRooms) {
        this.aExitRooms = pRooms;
    }// setExitRooms()
    
    /**
     * Sélectionne et retourne une salle de destination aléatoire
     * @return Une salle choisie aléatoirement parmi les destinations possibles, ou la salle actuelle
     */
    public Room getRandomRoom() {
        if (this.aExitRooms == null || this.aExitRooms.length == 0) {
            return this; // Retourne la salle actuelle si pas de destinations
        }
        int vNbSalle = this.vRandom.nextInt(this.aExitRooms.length);
        return this.aExitRooms[vNbSalle];
    }//getRandomRoom()
}
