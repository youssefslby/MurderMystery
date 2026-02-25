package FinalGame;


import java.util.HashMap;
import java.util.Set;
/**
 * Représente une collection d'Items dans le jeu.
 * Utilise une HashMap pour stocker et gérer les items par leur nom.
 *
 * @author Youssef Shalaby
 * @version finale
 */
public class ItemList
{
    private HashMap<String, Item> aItems;// Stockage des items dans une HashMap
    
    /**
     * Constructeur qui initialise une liste d'items vide
     */
    public ItemList(){
        this.aItems = new HashMap<String, Item>();
    }   
    
    /**
     * Ajoute un item à la liste
     * @param pName Le nom de l'item
     * @param pItem L'objet Item à ajouter
     */
    public void addItem(final String pName, final Item pItem){
       this.aItems.put(pName, pItem);
    }
    
    /**
     * Récupère un item par son nom
     * @param pName Le nom de l'item à récupérer
     * @return L'Item correspondant, ou null si non trouvé
     */
    public Item getItem(final String pName){
       return this.aItems.get(pName);
    }// getItem()
    
    /**
     * Vérifie si la liste est vide
     * @return true si la liste ne contient aucun item, false sinon
     */
    public boolean isEmpty(){
        return this.aItems.isEmpty();
    }
    
    /**
     * Retourne l'ensemble des noms/clés des items
     * @return Un Set contenant tous les noms d'items
     */
    public Set<String> getKeys(){
       return this.aItems.keySet();
    }
    
    /**
     * Supprime et retourne un item de la liste
     * @param pName Le nom de l'item à supprimer
     * @return L'Item supprimé, ou null si non trouvé
     */
    public Item removeItem(final String pName){
        return this.aItems.remove(pName);
    }// removeItem()
}
