package FinalGame;

/**
 *  La classe principale du jeu "All Aboard for Murder".
 * 
 *  Pour jouer, il vous suffit de créer une instance de cette classe.
 *  
 * @author  Michael Kolling and David J. Barnes + Youssef Shalaby
 * @version 2.0 (Jan 2003) DB edited (2019) YS edited (2025)
 */

public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
    /**
     * Crée le jeu et l'initialise
     */
    public Game() 
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }
}
