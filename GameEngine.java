package FinalGame;

import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Classe principale du moteur de jeu qui gère tout le jeu d'aventure.
 * Contient la carte du jeu, le player, et traite toutes les commandes.
 *
 * @author Youssef Shalaby
 * @version finale
 */
public class GameEngine
{
   private Stack <Room> aPreviousRooms; // Historique des pièces visitées
   private Parser aParser; // Parser
   private UserInterface aGui; // Interface utilisateur
   private Player aPlayer;// Joueur principal
   private final int aMaxMoves=30; // Nombre maximum de déplacements autorisés
   private HashMap <String, Room> aRooms; // Carte du jeu 
   // Liste des items nécessaires pour gagner:
   private final String[] ItemsToWin = {"broken watch","fountain pen","handkerchief","bloody knife","wayne key","poisoned wine glass"};
   private boolean aJudgement; // pour la condition de victoire
   /**
    * Constructeur par défaut qui initialise les composants du jeu
    */
   public GameEngine(){ // constructeur par defaut
       this.aRooms=new HashMap<String, Room>();
       this.aPreviousRooms= new Stack<Room>();
       this.createRooms();
       this.aParser= new Parser();
       this.aJudgement=false;
    } 
    
   /**
    * Définit l'interface utilisateur
    * @param pUserInterface L'interface à utiliser
    */
   public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }
    
    /**
     * Affiche les informations sur la position actuelle du joueur
     */
private void printLocationInfo()
{
   this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
}// printLocationInfo
    
/**
 * Affiche le message de bienvenue
 */
private void printWelcome(){
    this.aGui.print( "\n" );
    this.aGui.print("Welcome Aboard the Orient-Express! ");
    this.aGui.print("In this brand-new adventure game, you, Detective Poirot,");
    this.aGui.print("will be going after the perpetrators of a murder that has occured during the night.");
    this.aGui.print("Good luck with your investigation! ");
    this.aGui.print("Do not hesitate to click on the Help Menu or type help if you need help."+"\n");
    printLocationInfo();
    if (this.aPlayer.getCurrentRoom().getImageName() != null){
        this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());  
    }
}

   /**
    * Crée et initialise toutes les pièces du jeu avec leurs sorties et items
    */
   private void createRooms(){ 
       Room vOutside, vFallZone, vReception, vWayneCabin, vHallway, vMajorCabin, vLocomotive, vLangleyCabin, vRestaurant, vServiceRoom, vBaggage, vRoof;
       // TransporterRoom
       TransporterRoom vTransporterRoom = new TransporterRoom("in the transporter room.", "Images/vTransporterRoom.jpg"); 
       
       //Rooms       
       vOutside = new Room("outside the train, by the tracks.", "Images/vOutside.jpg");
       vReception = new Room("in the reception.", "Images/vReception.jpg");
       vWayneCabin = new Room("in Thomas Wayne's cabin.", "Images/vWayneCabin.jpg");
       vHallway = new Room("in the hallway.", "Images/vHallway.jpg");
       vMajorCabin = new Room("in Major Blackwood's cabin.", "Images/vMajorCabin.jpg");
       vLocomotive = new Room("in the locomotive, at the front of the train.", "Images/vLocomotive.jpg");
       vLangleyCabin = new Room("in Miss Langley's cabin.", "Images/vLangleyCabin.jpg");
       vRestaurant = new Room("in the restaurant.", "Images/vRestaurant.jpg");
       vServiceRoom = new Room("in the service room.", "Images/vServiceRoom.jpg");
       vBaggage = new Room("in the baggage compartment.", "Images/vBaggage.jpg");
       vRoof = new Room("on the train's roof.", "Images/vRoof.jpg");
       vFallZone = new Room("in the snow. There's no way back.", "Images/vFallZone.jpg");
       
       Room[] vExitRooms = new Room[]{ vOutside, vFallZone, vReception, vWayneCabin, vHallway, vMajorCabin, vLocomotive, vLangleyCabin, vRestaurant, vServiceRoom, vBaggage, vRoof};
       vTransporterRoom.setExitRooms(vExitRooms);
       
       //Items
       vOutside.addItem("fountain pen", new Item("a fountain pen", "This pen is just like any other.", 0.1));
       vLocomotive.addItem("stopwatch", new Item("a stopwatch", "This stopwatch presents a time difference", 0.2));
       vBaggage.addItem("uniform", new Item("a uniform", "This is an extra train driver's uniform, although there is usually only one on board.", 2));
       vWayneCabin.addItem("broken watch", new Item("a broken watch (1:15)", "This watch stopped at 1:15, possibly indicating the time of the murder.", 0.3));
       vReception.addItem("handkerchief", new Item("an embroidered handkerchief", "This delicate handkerchief has a single embroidered initial. Whose is it?", 0.1));
       vRestaurant.addItem("poisoned wine glass", new Item("a poisoned wine glass", "There are faint traces of poison in this wine glass. Was someone targeted?", 0.4));
       vHallway.addItem("bloody knife", new Item("a bloody knife", "The blade is stained with blood. It was found tucked away behind a wood panel.", 1.5));
       vServiceRoom.addItem("wayne key", new Item("a key labeled 'Wayne'", "This key opens Thomas Wayne's cabin. Why was it hidden here?", 0.1));
       vServiceRoom.addItem("beamer", new Beamer("a beamer (teleportation device)", "This device can teleport you to a previous location. ", 2));
       vMajorCabin.addItem("crumpled letter", new Item("a crumpled letter", "The letter is addressed to Major Blackwood, and its tone is accusatory.", 0.2));
       vMajorCabin.addItem("doc's elixir", new Item("the doc's Elixir", "an elegant black vial labeled 'The Doctor's Elixir' rumored to grant unnatural strength to those who drink it.", 0.2));
       vLangleyCabin.addItem("different train ticket", new Item("a different train ticket", "This ticket has a different departure location than Miss Langley's claimed boarding point.", 0.2));
       
       //les differentes salles et leurs sorties respectives en fonction de la direction.
        
        vOutside.setExit("east", vReception);
        
        vReception.setExit("north", vWayneCabin);
        vReception.setExit("east", vHallway);
        vReception.setExit("west", vOutside);
        vReception.setExit("south", vLangleyCabin);
        
        vWayneCabin.setExit("south", vReception);
        
        vHallway.setExit("north", vLocomotive);
        vHallway.setExit("south", vRestaurant);
        vHallway.setExit("east", vMajorCabin);
        vHallway.setExit("west", vReception);
        vHallway.setExit("up", vRoof);
        
        vMajorCabin.setExit("west", vHallway);
        
        vLocomotive.setExit("south", vHallway);
        
        vLangleyCabin.setExit("north", vReception);
  
        vRestaurant.setExit("north", vHallway);
        vRestaurant.setExit("east", vServiceRoom);
        vRestaurant.setExit("west", vBaggage);
        vRestaurant.setExit("south", vTransporterRoom);
        
        vTransporterRoom.setExit("north", vRestaurant); 

        vServiceRoom.setExit("west", vRestaurant);
        vBaggage.setExit("east", vRestaurant);
        vBaggage.setExit("out", vFallZone);
        
        this.aRooms.put("outside", vOutside);
        this.aRooms.put("fallzone", vFallZone);
        this.aRooms.put("reception", vReception);
        this.aRooms.put("waynecabin", vWayneCabin);
        this.aRooms.put("hallway", vHallway);
        this.aRooms.put("majorcabin", vMajorCabin);
        this.aRooms.put("locomotive", vLocomotive);
        this.aRooms.put("langleycabin", vLangleyCabin);
        this.aRooms.put("restaurant", vRestaurant);
        this.aRooms.put("serviceroom", vServiceRoom);
        this.aRooms.put("baggage", vBaggage);
        this.aRooms.put("roof", vRoof);
        this.aRooms.put("transporterroom", vTransporterRoom);


        //Initialisation
       this.aPlayer = new Player ("Youssef", vOutside);
    }
   
   /**
    * Déplace le joueur dans une nouvelle pièce
    * @param pInput La commande contenant la direction
    */
private void goRoom(final Command pInput) {
   if (this.aPlayer.getMoves() >= this.aMaxMoves) {
      this.lose();
      return;
   }

   if (!pInput.hasSecondWord()) {
      this.aGui.println("Go where ?");
      return;
   } // si il n y a pas de deuxieme mot on affiche "Go Where?"

   String vDirection = pInput.getSecondWord();
   Room vCurrentRoom = this.aPlayer.getCurrentRoom();
   Room vNextRoom = vCurrentRoom.getExit(vDirection);
   
   if (vNextRoom == null){
      this.aGui.println("There is no door!");
      return;
   } // end if
   
   if (vCurrentRoom.getDescription().equals("in the transporter room.")){
      TransporterRoom vTransporterRoom = (TransporterRoom) vCurrentRoom;
      Room vRandomizedRoom = vTransporterRoom.getRandomRoom();
      this.aPreviousRooms.push(vCurrentRoom); // on ajoute a la pile la room actuelle
      this.aPlayer.setCurrentRoom(vRandomizedRoom);
   }
   else{
       this.aPlayer.goRoom(vDirection);
   }
   
   this.aPlayer.addMove();
   printLocationInfo();
   int vMovesLeft = this.aMaxMoves - this.aPlayer.getMoves();
   this.aGui.println("You have " + vMovesLeft + " moves left.");
   
   if (this.aPlayer.getCurrentRoom().getImageName() != null) {
      this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
   }
   
   //if (this.aPlayer.getCurrentRoom().hasNoExits()){
   //   this.endGame();
   //   return;
   //} // end if
}//goRoom()


/**
 * Affiche les commandes disponibles au joueur
 */
private void printHelp(){
    this.aGui.println("You are lost. You are alone.");
    this.aGui.println("You wander around the train's premises.");
    this.aGui.println("Your command words are:\n"+ this.aParser.getCommandString());
    
}

/**
 * Termine le jeu proprement
 */
private void endGame(){
    this.aGui.println( "Thank you for playing, Good bye!" );
    this.aGui.enable( false );
}// endGame()

/**
 * Interprète et exécute la commande entrée par le joueur
 * @param pCommandLine est la commande entrée par le joueur
 */
public void interpretCommand(final String pCommandLine){
    this.aGui.println("> " + pCommandLine);
    Command vCommand = this.aParser.getCommand(pCommandLine);
    String vInput = pCommandLine;
    
    if (this.aJudgement==true){
        if ("1".equals(vInput)) {
           this.aGui.println("You are all the murderers. Did you think me, Detective Poirot, wouldn't be able to realise that?");
           this.aGui.println("You will all be reported to the authorities upon arrival.");
           this.aGui.println("You have won!");
            this.endGame();
        }
        else if("2".equals(vInput)){
            this.aGui.println("I can't think of anything.");
            this.aGui.println("You lost the game. What a disgrace, Poirot.");
            this.endGame();
        }
        else{
            this.aGui.println("Please type either 1 or 2.");
        }
        return;
    }
    
    if (vCommand.isUnknown()){
        this.aGui.println("I don't know what you mean...");
        return;
    }
    String vCommandWord = vCommand.getCommandWord();
    String vRestOfLine = vCommand.getRestOfLine();
    
    if ( vCommandWord.equals( "test" ) )
        this.test(vCommand); 
    else if ( vCommandWord.equals( "charge" ) )
        this.charge(vCommand);
    else if ( vCommandWord.equals( "alea" ) )
        this.alea(vCommand);
    else if ( vCommandWord.equals( "fire" ) )
        this.fire(vCommand);
    else if ( vCommandWord.equals( "drop" ) )
        this.drop(vCommand);
    else if ( vCommandWord.equals( "help" ) )
        this.printHelp();
    else if ( vCommandWord.equals( "drink" ) )
        this.drink(vCommand);
    else if ( vCommandWord.equals( "inventory" ) )
        this.inventory();
    else if ( vCommandWord.equals( "take" ) )
        this.take(vCommand);
    else if ( vCommandWord.equals( "go" ) )
        this.goRoom(vCommand);
    else if ( vCommandWord.equals( "look" ) )
        this.look();
    else if ( vCommandWord.equals( "back" ) )
        this.back();
    else if ( vCommandWord.equals( "quit" ) ) {
        if ( vCommand.hasSecondWord() )
            this.aGui.println( "Quit what?" );
        else
            this.endGame();
        }
}

    /**
     * Exécute un fichier de test
     * @param pInput La commande contenant le nom du fichier
     */
    private void test(final Command pInput)
    {
        //test absence du second mot
        if (!pInput.hasSecondWord()){
            this.aGui.println("Test which file?");
            return;
        }
        //si presence du second mot
        String vFile = pInput.getSecondWord();
        try { // teste le fichier
            Scanner vScan= new Scanner(new File(""+ vFile +".txt"));
            this.aGui.println("Now testing " + vFile + ".");
            while (vScan.hasNextLine()){
                interpretCommand(vScan.nextLine());
            }
        } catch(final FileNotFoundException pFileNotFound){ // catch en cas d'absence du fichier
            this.aGui.println("Such file does not exist.");
        }// final: pFileNotFound ne peut subir aucune modification
    }

    /**
     * Permet au joueur de prendre un objet
     * @param pInput La commande contenant le nom de l'objet
     */
    private void take (final Command pInput){
        String vItemName = pInput.getRestOfLine();
        if(vItemName==null || vItemName.isEmpty()){
            this.aGui.println("Take what?");
            return;
        }       
        
        Item vItem = this.aPlayer.getCurrentRoom().getItem(vItemName);
        if(vItem == null){
            this.aGui.println("There's no such item here !");
            return;
        }
        
        double vCurrentWeight = this.aPlayer.getWeight();
        double vItemWeight = vItem.getWeight();
        double vMaxWeight = this.aPlayer.getMaxWeight();
        
        if (vCurrentWeight + vItemWeight > vMaxWeight) {
            this.aGui.println("You can't carry that much weight.");
            return;
        }
        
        this.aPlayer.addItem(vItemName, vItem);
        this.aPlayer.setWeight(vCurrentWeight + vItemWeight);
        this.aPlayer.getCurrentRoom().removeItem(vItemName);
        this.aGui.println("You picked up the " + vItemName + ".");
        checkVictoryCondition();
    } //take()
    
    /**
     * Permet au joueur de déposer un objet
     * @param pInput La commande contenant le nom de l'objet
     */
    private void drop (final Command pInput){
        String vItemName = pInput.getRestOfLine();
        if(!pInput.hasSecondWord()){
            this.aGui.println("Drop what?");
            return;
        }       
        
        Item vItem = this.aPlayer.getItem(vItemName);
        if(vItem == null){
            this.aGui.println("you don't have that item !");
            return;
        }
        double vCurrentWeight = this.aPlayer.getWeight();
        double vItemWeight = vItem.getWeight();
        this.aPlayer.setWeight(vCurrentWeight - vItemWeight);
        this.aPlayer.getCurrentRoom().addItem(vItemName, vItem);
        this.aPlayer.removeItem(vItemName);
        this.aGui.println("You dropped the " + vItemName + ".");
    } //drop()
    
    /**
     * Permet au joueur de revenir à la pièce précédente
     */
    private void back() {
        if (this.aPlayer.getPreviousRooms().empty()) {
            this.aGui.println("There isn't any previous room.");
        } else {
            Room vPreviousRoom= this.aPlayer.getPreviousRooms().peek(); 
            Room vCurrentRoom= this.aPlayer.getCurrentRoom();

            if (vCurrentRoom.isValidExit(vPreviousRoom)) {
                this.aPlayer.back(); 
            } else {
                this.aGui.println("Can't go back !"); 
            }
        }
        this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
        printLocationInfo();
    } //back()

    /**
     * Affiche la description de la pièce actuelle
     */
    private void look()
    {
       this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }//look()

    /**
     * Permet au joueur de boire
     * @param pInput La commande contenant le nom de l'item à boire
     */
    private void drink (final Command pInput){
        String vItemName = pInput.getRestOfLine();
        if(!pInput.hasSecondWord()){
            this.aGui.println("Drink what?");
            return;
        }
        
        Item vItem = this.aPlayer.getItem(vItemName);
        if(vItem == null){
            this.aGui.println("There is no such drink !");
            return;
        }
        
        if(!vItemName.equals("doc's elixir")){
            this.aGui.println("You can't drink that.");
            return;
        }
        double vCurrentWeight = this.aPlayer.getWeight();
        double vItemWeight = vItem.getWeight();
        
        this.aPlayer.doubleMaxWeight();
        double vMaxWeight = this.aPlayer.getMaxWeight();
        
        this.aPlayer.removeItem(vItemName);
        this.aPlayer.setWeight(vCurrentWeight - vItemWeight);
        this.aGui.println("You drank the " + vItemName + ". Your maximum load has been increased to " + vMaxWeight + " kg.");    
    }//drink

    /**
     * Charge le beamer avec la pièce actuelle
     * @param pInput La commande contenant le nom du beamer
     */
    private void charge(final Command pInput){
        String vItemName = pInput.getRestOfLine();
        if(!pInput.hasSecondWord()){
           this.aGui.println("Charge what?");
           return;
        }
        
        Item vItem = this.aPlayer.getItem(vItemName);
        if (vItem == null){
           this.aGui.println("You don't have a " + vItemName + "!");
           return;
        } // cas ou l'objet n'est pas trouve dans l'inventaire
        
        if (!(vItem instanceof Beamer)){
           this.aGui.println("You can't charge the " + vItemName + "!");
           return;
        } // on utilise instanceof pour verifier que l item est bien un beamer 
        
        Beamer vBeamer= (Beamer) vItem;
        vBeamer.setChargedRoom(this.aPlayer.getCurrentRoom());
        this.aGui.println("The beamer has been charged. If you fire it, you will come back to this room.");
    }//charge()

    /**
     * Téléporte le joueur vers la pièce enregistrée dans le beamer
     * @param pInput La commande contenant le nom du beamer
     */
    private void fire(final Command pInput) {
        String vItemName = pInput.getRestOfLine();
        if (!pInput.hasSecondWord()) {
            this.aGui.println("Fire what?");
            return;
        }
        
        Item vItem = this.aPlayer.getItem(vItemName);
        if (vItem == null) {
           this.aGui.println("You don't have a " + vItemName + "!");
           return;
        }

        if (!(vItem instanceof Beamer)){
           this.aGui.println("You can't fire the " + vItemName + "!");
           return;
        }

        Beamer vBeamer = (Beamer) vItem;
        if (!vBeamer.BeamerCharged()){
           this.aGui.println("The beamer is not charged!");
           return;
        }
        
        Room vBeamerDestination = vBeamer.getChargedRoom();
        Room vCurrentRoom= this.aPlayer.getCurrentRoom();

        if(vCurrentRoom.getExitString().equals("It seems like there are no exits...")){
           this.aGui.println("You can't teleport yourself out, you're trapped.");
           return;
        }
        
        this.aPlayer.setCurrentRoom(vBeamerDestination);
        this.aGui.println("You have been teleported back to the room where you charged the beamer.");
        
        vBeamer.setChargedRoom(null); // met a zero la charge du beamer
        printLocationInfo();
        if (vBeamerDestination.getImageName() != null) {
            this.aGui.showImage(vBeamerDestination.getImageName());
        }
        this.aPlayer.addMove();
        int vMovesLeft = this.aMaxMoves - this.aPlayer.getMoves();
        this.aGui.println("You have " + vMovesLeft + " moves left.");
    }

    /**
     * Affiche l'inventaire du joueur
     */
    private void inventory(){
        ItemList vItems = this.aPlayer.getInventory();

        if (vItems.isEmpty()) {
            this.aGui.println("You are not carrying anything.");
        } else {
            this.aGui.println("You are carrying:");
            double vTotalWeight =0;
            for (String vItemName : vItems.getKeys()) {
                Item vItem = vItems.getItem(vItemName);
                double vItemWeight = vItem.getWeight();
                vTotalWeight+= vItemWeight;
                this.aGui.println("- " + vItemName + ": " + vItem.getDescription() + " (Weight: "+ vItemWeight+ " Kg)") ;
            }
            this.aGui.println("You are carrying " + vTotalWeight + " kg " + "worth of items.");
        }
    }// inventory()

    /**
     * Téléporte le joueur vers une pièce
     * @param pInput La commande contenant le nom de la pièce
     */
    public void alea(final Command pInput)
    {
        if (!pInput.hasSecondWord()) {
            this.aGui.println("Alea where?");
            return;
        }

        String vRoomName = pInput.getSecondWord();
        Room vAleaRoom = this.aRooms.get(vRoomName);
        Room vCurrentRoom = this.aPlayer.getCurrentRoom();

        if (vAleaRoom == null) {
            this.aGui.println("The room " + vRoomName + " does not exist!");
            return;
        }

        if (!(vCurrentRoom.getDescription().equals("in the transporter room."))){
            this.aGui.println("The command 'alea' can only be used if you are in the Transporter Room.");
            return;
        }

        this.aPreviousRooms.push(vCurrentRoom);
        this.aPlayer.setCurrentRoom(vAleaRoom);
        printLocationInfo();
        
        this.aPlayer.addMove();
        int vMovesLeft = this.aMaxMoves - this.aPlayer.getMoves();
        this.aGui.println("You have " + vMovesLeft + " moves left.");
        if (vAleaRoom.getImageName() != null) {
            this.aGui.showImage(vAleaRoom.getImageName());
        }
    }

    /**
     * Termine le jeu en cas de défaite
     */
    private void lose(){
        this.aGui.println( "You lost the game." );
        this.endGame();
    }

    /**
     * Vérifie si le joueur a tous les items pour gagner
     */
    private void checkVictoryCondition(){
        ItemList inventory = this.aPlayer.getInventory();

        for (String vItemName: ItemsToWin) {
            if (inventory.getItem(vItemName)== null) {
                return;
            }
        }
        this.aGui.println("You have gathered enough evidence.");
        this.aGui.println("What is your judgement, Poirot?");
        this.aGui.println("Type 1 to accuse or 2 to give up:");
        this.aJudgement = true;
    }    
}//GameEngine


