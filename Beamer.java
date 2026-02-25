package FinalGame;

/**
 * Un Beamer est un item qui peut mémoriser une pièce
 * et permettre au joueur de s'y téléporter.
 * Hérite de la classe Item.
 *
 * @author Youssef Shalaby
 * @version finale
 */
public class Beamer extends Item
{
    private Room aChargedRoom; // Pièce ou l'on charge le Beamer
    
    /**
     * Constructeur du Beamer
     * @param pName Nom du Beamer
     * @param pDescription Description du Beamer
     * @param pWeight Poids du Beamer en kg
     */
    public Beamer(final String pName, final String pDescription, final double pWeight)
    {
        super(pName, pDescription, pWeight);
    }// Beamer()
    
    /**
     * Retourne la pièce mémorisée par le Beamer
     * @return La pièce chargée, et null si le Beamer n'est pas chargé
     */
    public Room getChargedRoom()
    {
        return this.aChargedRoom;
    }// getChargedRoom()
    
    /**
     * Charge le Beamer avec une pièce spécifique
     * @param pRoom La pièce à mémoriser pour la téléportation
     */
    public void setChargedRoom(final Room pRoom)
    {
        this.aChargedRoom = pRoom;
    }// setChargedRoom()
    
    /**
     * Vérifie si le Beamer est chargé et prêt à être utilisé
     * @return true si le Beamer contient une pièce mémorisée, false sinon
     */
    public boolean BeamerCharged()
    {
        return this.aChargedRoom != null;
    }// BeamerCharged()
}// Beamer
