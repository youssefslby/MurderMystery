package FinalGame;

/**
 * Cette classe maintient la liste des commandes valides et directions valides du jeu.
 * Elle permet de vérifier si une commande ou direction entrée par le joueur est reconnue.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + Youssef Shalaby
 * @version 2008.03.30 + 2019.09.25 + 2025.05.17
 */
public class CommandWords
{
    // Tableau des commandes valides dans le jeu
    private static final String[] aValidCommands = {"go", "back", "help", "quit", "look", "eat", "test", "take", "drop", "inventory", "drink", "fire", "charge", "alea"};
    // Tableau des directions valides
    private static final String[] aValidDirections = {"north", "south", "east", "west", "up", "down", "out"};
    
    /**
     * Constructeur de la classe CommandWords
     */
    public CommandWords() {
        // Constructeur vide pour javadoc
    }
    /**
     * Vérifie si une chaîne correspond à une commande valide
     * @param pString La chaîne à vérifier
     * @return true si la chaîne est une commande valide, false sinon
     */
    public boolean isCommand(final String pString)
    {
        for (int vI = 0; vI < aValidCommands.length; vI++) {
            if (aValidCommands[vI].equals(pString))
                return true;
        } // for
        // si on arrive ici, il n'y a pas de commande 
        return false;
    } // isCommand()
    
    /**
     * Vérifie si une chaîne correspond à une direction valide
     * @param pString La chaîne à vérifier
     * @return true si la chaîne est une direction valide, false sinon
     */
    public static boolean isDirections(final String pString)
    {
        for (int vI = 0; vI < aValidDirections.length; vI++) {
            if (aValidDirections[vI].equals(pString))
                return true;
        } // for
        // si on arrive ici, la direction n'a pas été trouvée
        return false;
    } // isDirections()

    /**
     * Retourne la liste des commandes valides sous forme de chaîne
     * @return Une chaîne contenant toutes les commandes valides séparées par des espaces
     */    
    public String getCommandList() {
        String vString = "";
        for (String vCommand : aValidCommands) {
            vString += vCommand + " ";
        }
        return vString;
    }
} // CommandWords
