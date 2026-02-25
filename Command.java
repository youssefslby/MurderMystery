package FinalGame;


/**
 * Représente une commande utilisateur.
 * Une commande est composée d'un mot de commande principal,
 * éventuellement d'un second mot, et du reste de la ligne car quelques items 
 * ont plusieurs mots.
 * 
 * @author Youssef Shalaby
 * @version finale
 */
public class Command
{
    private String aCommandWord;// Mot de commande principal
    private String aSecondWord; // Deuxième mot de la commande 
    private String aRestOfLine; // pour l item compose de plusieurs mots
    
    /**
     * Constructeur de Command
     * @param pCommandWord Le mot de commande principal (ex: "go")
     * @param pSecondWord Le deuxième mot de la commande (ex: "north")
     * @param pRestOfLine Le reste de la ligne après le premier mot
     */
    public Command(final String pCommandWord, final String pSecondWord, final String pRestOfLine) {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
        this.aRestOfLine = pRestOfLine;
    } // Constructeur naturel
    
    /**
     * Retourne le reste de la ligne/commande après le premier mot
     * @return Le texte restant après le premier mot
     */
    public String getRestOfLine() {
        return this.aRestOfLine;
    }

    /**
     * Retourne le mot de commande principal
     * @return Le premier mot de la commande
     */
    public String getCommandWord() { 
        return this.aCommandWord;
    }
    
    /**
     * Retourne le deuxième mot de la commande
     * @return Le second mot de la commande, ou null si absent
     */
    public String getSecondWord() { 
        return this.aSecondWord;
    }

    /**
     * Vérifie si la commande contient un deuxième mot
     * @return true si un deuxième mot est présent, false sinon
     */
    public boolean hasSecondWord() {
        return this.aSecondWord != null;
    }
    
    /**
     * Vérifie si la commande est inconnue
     * @return true si la commande n'est pas reconnue, false sinon
     */
    public boolean isUnknown() {
        return this.aCommandWord == null;
    }
} // Command
