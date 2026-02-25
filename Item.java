package FinalGame;


/**
 * Représente un objet dans le jeu, avec un nom, une description et un poids.
 * Les items peuvent être manipulés par le joueur.
 *
 * @author Youssef Shalaby
 * @version finale
 */
public class Item
{
    private String aName;// Nom de l'objet
    private String aDescription; // Description de l'objet
    private double aWeight;// Poids de l'objet en Kg
    /**
     * Constructeur d'Item
     * @param pName Le nom de l'objet 
     * @param pDescription La description de l'objet 
     * @param pWeight Le poids de l'objet en kg 
     */
    public Item(final String pName, final String pDescription, final double pWeight)
    {
        // initialisation des variables d'instance
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }

    /**
     * Retourne le nom de l'objet
     * @return Le nom de l'objet
     */
    public String getName() {
        return this.aName;
    }
    
    /**
     * Retourne la description détaillée de l'objet
     * @return La description 
     */
    public String getDescription() {
        return this.aDescription;
    }
    
    /**
     * Retourne le poids de l'objet
     * @return Le poids en kg
     */
    public double getWeight() {
        return this.aWeight;
    }
    
    /**
     * Génère une description complète de l'objet
     * @return Une chaîne 
     */
    public String getItemDescription() {
        return "There is " + this.aDescription + ". This item weighs " + this.aWeight + "kg";
    }
}
