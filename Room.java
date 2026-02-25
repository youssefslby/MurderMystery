package FinalGame;


import java.util.HashMap;
import java.util.Set;

/**
 * Représente une pièce dans le jeu d'aventure, avec des sorties, des items et une image associée.
 * 
 * @author Youssef Shalaby
 * @version finale
 */
public class Room
{
    private String aDescription; // chaîne de caractères décrivant le lieu
    private HashMap<String, Room> aExits; // direction, chambre dans cette direction
    private String aImageName; // nom de l'image associée
    private ItemList aItems = new ItemList(); // liste des items présents dans la pièce
    
    /**
     * Constructeur de Room
     * @param pDescription La description textuelle de la pièce
     * @param pImageName Le nom du fichier image associé à cette pièce
     */
    public Room(final String pDescription, final String pImageName)
    {
        this.aDescription = pDescription; // description de la piece
        this.aExits = new HashMap<String, Room>(); 
        this.aImageName = pImageName;
    }
    
    /**
     * Retourne la description de la pièce
     * @return La description textuelle de la pièce
     */
    public String getDescription()
    {
        return this.aDescription;
    }
    
    /**
     * Définit une sortie pour cette pièce
     * @param pDirection La direction de la sortie (ex: "north")
     * @param pNeighbor La pièce voisine dans cette direction
     */
    public void setExit(final String pDirection, final Room pNeighbor) 
    {
        aExits.put(pDirection, pNeighbor);
    }
    
    /** 
     * Récupère un item présent dans la pièce
     * @param pName Le nom de l'item à récupérer
     * @return L'item correspondant, ou null s'il n'existe pas
     */
    public Item getItem(final String pName){
        return this.aItems.getItem(pName);
    }
    
    /**
     * Retourne la pièce voisine dans une direction donnée
     * @param pDirection La direction à vérifier
     * @return La pièce voisine, ou null si aucune sortie dans cette direction
     */
    public Room getExit(final String pDirection)
    {
        return this.aExits.get(pDirection);
    }
    
    /**
     * Génère une description des sorties disponibles
     * @return Une chaîne listant les directions possibles (ex: "You can go in the following directions: north south")
     */
    public String getExitString()
    {
        if (this.aExits.isEmpty()){
            return ("It seems like there are no exits...");
        }
        
        StringBuilder returnString = new StringBuilder("You can go in the following directions:");
        for (String vS : this.aExits.keySet())
            returnString.append(" " + vS);
        return returnString.toString();
    }
    
    /**
     * Retourne une description complète de la pièce (description + sorties + items)
     * @return Une description détaillée de la pièce et de son contenu
     */
    public String getLongDescription() {
        String vDesc = "You are " + aDescription + "\n" + getExitString();

        if (this.aItems.getKeys().isEmpty()) {
            vDesc += "\nThere are no items here.";
        } else {
            vDesc += "\nItems in this room:";
            for (String vItemName : this.aItems.getKeys()) {
                Item vItem = this.aItems.getItem(vItemName);
                vDesc += "\n- " + vItem.getName() + " (weighs " + vItem.getWeight() + "kg)";
            }
        }
        return vDesc;
    }

    /**
     * Retourne le nom de l'image associée à cette pièce
     * @return Le nom du fichier image
     */
    public String getImageName(){
       return this.aImageName;
    }
    
    /** 
     * Ajoute un item dans la pièce
     * @param pName Le nom associé à l'item
     * @param pItem L'item à ajouter
     */
    public void addItem(final String pName, final Item pItem){
        this.aItems.addItem(pName, pItem);
    }// addItem()
    
    /**
     * Retire un item de la pièce
     * @param pName Le nom de l'item à retirer
     * @return L'item retiré, ou null si l'item n'existait pas
     */
    public Item removeItem(final String pName){
        return this.aItems.removeItem(pName);
    }// removeItem()
    
    /**
     * Génère une liste des items présents dans la pièce
     * @return Une chaîne décrivant les items disponibles
     */
    public String getItemString(){
        StringBuilder returnString = new StringBuilder("Items:");
        Set<String> vObjects = aItems.getKeys(); // vObjects sont les noms des objects présents dans une room quelconque
        for (String vKey : vObjects){
            returnString.append(" " + vKey);
        }
        return returnString.toString();
    }
    
    /**
     * Vérifie si une pièce donnée est une sortie valide
     * @param pRoom La pièce à vérifier
     * @return true si la pièce est accessible depuis cette pièce, false sinon
     */
    public boolean isValidExit(final Room pRoom){
        if (this.aExits.containsValue(pRoom)){ // regarde si aExits contient la pRoom
           return true;
        }
        return false;
    }
} // Room
