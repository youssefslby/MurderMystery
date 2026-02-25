package FinalGame;

import java.util.StringTokenizer;

/** 
 * Analyseur de commandes qui interprète les entrées utilisateur.
 * Lit une ligne de texte et tente de la décomposer en commande valide.
 * Retourne un objet Command contenant la commande interprétée.
 * 
 * Le parser maintient une liste de commandes valides et vérifie
 * si l'entrée utilisateur correspond à l'une d'entre elles.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + Youssef Shalaby
 * @version 2008.03.30 + 2013.09.15 + 2025.05.17
 */
public class Parser 
{
    private CommandWords aValidCommands; //Stockage des CommandWords
    
    /**
     * Constructeur qui initialise le parser avec les commandes valides
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();
    } // Parser()

    /**
     * Analyse une ligne de commande et retourne un objet Command correspondant
     * @param pInputLine La ligne de commande à analyser
     * @return Un objet Command représentant la commande interprétée
     */
    public Command getCommand(final String pInputLine) 
    {
        String vWord1; // Premier mot de la commande
        String vWord2; // Deuxième mot de la commande
        String vRestOfLine = ""; // Le reste de la ligne après le premier mot

        StringTokenizer tokenizer = new StringTokenizer(pInputLine);

        if (tokenizer.hasMoreTokens())
            vWord1 = tokenizer.nextToken();
        else
            vWord1 = null;

        if (tokenizer.hasMoreTokens()) {
            vWord2 = tokenizer.nextToken();
            vRestOfLine = vWord2;
        } else {
            vWord2 = null;
        }

        while (tokenizer.hasMoreTokens()) {
            vRestOfLine += " " + tokenizer.nextToken();
        }

        // Vérifie si le premier mot est une commande valide
        if (this.aValidCommands.isCommand(vWord1)) {
            return new Command(vWord1, vWord2, vRestOfLine);
        } else {
            return new Command(null, vWord2, vRestOfLine);
        }
    }
    
    /**
     * Retourne la liste des commandes valides sous forme de String
     * @return Une chaîne 
     */
    public String getCommandString(){
        return this.aValidCommands.getCommandList();
    }// getCommands
} // Parser
