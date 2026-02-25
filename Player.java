package FinalGame;

import java.util.Stack;
import java.util.HashMap;

/**
 * Représente un joueur dans le jeu d'aventure, avec son inventaire, sa position et son le poids qu'il porte.
 * Le joueur peut se déplacer entre les pièces, transporter des objets et revenir en arrière.
 * 
 * @author Youssef Shalaby
 * @version finale
 */
public class Player
{
    private String aPseudonym; // pseudo au cas où il y a plusieurs players
    private Room aCurrentRoom; // pièce actuelle
    private Stack<Room> aPreviousRooms; // historique des pièces précédentes
    private ItemList aItems = new ItemList(); // inventaire du joueur
    private double aMaxWeight = 1.6; // poids maximum transportable par défaut
    private double aWeight = 0; // poids actuellement transporté
    private int aMoves; // nombre de déplacements effectués
    
    /**
     * Constructeur du Player
     * @param pPseudonym Le nom du joueur
     * @param pCurrentRoom La pièce de départ du joueur
     */
    public Player(final String pPseudonym, final Room pCurrentRoom) {
        this.aPseudonym = pPseudonym; 
        this.aCurrentRoom = pCurrentRoom;
        this.aMoves = 0;
        this.aPreviousRooms = new Stack<Room>();
    }// Player()
    
    /**
     * Retourne le pseudonyme du joueur
     * @return Le nom du joueur
     */
    public String getPseudonym() {
        return this.aPseudonym;
    }// getPseudo()
    
    /**
     * Retourne la pièce actuelle du joueur
     * @return La pièce où se trouve le joueur
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }// getCurrentRoom()
    
    /**
     * Définit la pièce actuelle du joueur
     * @param pRoom La nouvelle pièce courante
     */
    public void setCurrentRoom(final Room pRoom) {
        this.aCurrentRoom = pRoom;
    }

    /**
     * Retourne l'historique des pièces visitées
     * @return Une pile contenant les pièces précédemment visitées
     */
    public Stack<Room> getPreviousRooms() {
        return this.aPreviousRooms;
    }// getPreviousRooms()
    
    /**
     * Déplace le joueur vers une pièce adjacente
     * @param pRoom La direction dans laquelle se déplacer
     */
    public void goRoom(final String pRoom) {
        Room vNextRoom = this.aCurrentRoom.getExit(pRoom); // appel de getExit() pour connaitre la prochaine room
        this.aPreviousRooms.push(this.aCurrentRoom); // Modification de la pièce précédente par la pièce actuelle grace au "push"
        this.aCurrentRoom = vNextRoom; // changement de la pièce actuelle par celle d'après
    }// goRoom()
    
    /**
     * Fait revenir le joueur à la pièce précédente
     */
    public void back() {
        this.aCurrentRoom = this.aPreviousRooms.pop(); // Change la pièce courante par celle d'avant
    }// back()
    
    /**
     * Ajoute un objet à l'inventaire du joueur
     * @param pName Le nom de l'objet
     * @param pItem L'objet à ajouter
     */
    public void addItem(final String pName, final Item pItem) {
        this.aItems.addItem(pName, pItem);
    }

    /**
     * Récupère un objet de l'inventaire
     * @param pName Le nom de l'objet à récupérer
     * @return L'objet correspondant, ou null s'il n'existe pas
     */
    public Item getItem(final String pName) {
        return this.aItems.getItem(pName);
    }// getItem()
    
    /**
     * Retire un objet de l'inventaire
     * @param pName Le nom de l'objet à retirer
     * @return L'objet retiré, ou null s'il n'existait pas
     */
    public Item removeItem(final String pName) {
        return this.aItems.removeItem(pName);
    }// removeItem()
    
    /**
     * Retourne l'inventaire complet du joueur
     * @return La liste des objets transportés
     */
    public ItemList getInventory() {
        return this.aItems;
    }
    
    /**
     * Retourne le poids actuellement transporté
     * @return Le poids total des objets transportés
     */
    public double getWeight() {
        return this.aWeight;
    }

    /**
     * Définit le poids actuellement transporté
     * @param pWeight Le nouveau poids total
     */
    public void setWeight(final double pWeight) {
        this.aWeight = pWeight;
    }
    
    /**
     * Retourne le poids maximum transportable
     * @return La capacité de portage maximale
     */
    public double getMaxWeight() {
        return this.aMaxWeight;
    }

    /**
     * Double le poids maximal 
     */
    public void doubleMaxWeight() {
        this.aMaxWeight = this.aMaxWeight*2;
    }
    
    /**
     * Incrémente le compteur de déplacements
     */
    public void addMove() {
        this.aMoves++; // ajoute 1 au moves 
    }
    
    /**
     * Retourne le nombre de déplacements effectués
     * @return Le nombre total de déplacements
     */
    public int getMoves() {
        return this.aMoves;
    }
}// Player
