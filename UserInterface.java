package FinalGame;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.net.URL;
import java.awt.event.ActionEvent;

/**
 * Interface utilisateur graphique pour le jeu d'aventure.
 * Contient une zone de texte pour les commandes, une zone d'affichage,
 * une image et des boutons pour les actions courantes.
 * 
 * @author Michael Kolling + Denis Bureau + Youssef Shalaby
 * @version 1.0 (Jan 2003) DB edited (2023) YS edited (2025)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;

    private JFrame aMyFrame;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JLabel aImage;
    // Boutons de direction
    private JButton aEastButton;
    private JButton aUpButton;
    private JButton aWestButton;
    private JButton aNorthButton;
    private JButton aSouthButton;
    private JButton aOutButton;
    // Boutons d'actions
    private JButton aInventoryButton;
    private JButton aLookButton;
    private JButton aQuitButton;
    private JButton aHelpButton;

    /**
     * Construit l'interface 
     * @param pGameEngine Le moteur de jeu qui traitera les commandes
     */
    public UserInterface(final GameEngine pGameEngine) {
        this.aEngine = pGameEngine;
        this.createGUI();
    }

    /**
     * Affiche du texte
     * @param pText Le texte à afficher
     */
    public void print(final String pText) {
        this.aLog.append(pText);
        this.aLog.setCaretPosition(this.aLog.getDocument().getLength());
    }

    /**
     * Affiche du texte avec un saut de ligne
     * @param pText Le texte à afficher
     */
    public void println(final String pText) {
        this.print(pText + "\n");
    }

    /**
     * Affiche une image dans l'interface
     * @param pImageName Le nom du fichier image à afficher
     */
    public void showImage(final String pImageName) {
        String vImagePath = "" + pImageName;
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null)
            System.out.println("Image not found: " + vImagePath);
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aImage.setIcon(vIcon);
            this.aMyFrame.pack();
        }
    }

    /**
     * Active ou désactive le champ de saisie dans la console
     * @param pOnOff true pour activer, false pour désactiver
     */
    public void enable(final boolean pOnOff) {
        this.aEntryField.setEditable(pOnOff);
        if (pOnOff) {
            this.aEntryField.getCaret().setBlinkRate(500);
            this.aEntryField.addActionListener(this);
        } else {
            this.aEntryField.getCaret().setBlinkRate(0);
            this.aEntryField.removeActionListener(this);
        }
    }

    /**
     * Crée et configure l'interface graphique
     */
    private void createGUI() {
        this.aMyFrame = new JFrame("All Aboard for Murder");
        JPanel vPanel = new JPanel();
        JPanel vButtonPanel = new JPanel(new GridLayout(4,4));
        
        this.aEntryField = new JTextField(34);
        
        // Boutons de direction
        this.aEastButton = new JButton("Go East");
        this.aWestButton = new JButton("Go West");
        this.aNorthButton = new JButton("Go North");
        this.aSouthButton = new JButton("Go South");
        this.aOutButton = new JButton("Go Out");
        this.aUpButton = new JButton("Go Up");
        
        // Boutons d'actions
        this.aInventoryButton = new JButton("See Inventory");
        this.aLookButton = new JButton("Look Around");
        this.aQuitButton = new JButton("Quit");
        this.aHelpButton = new JButton("Help Menu");
        
        this.aImage = new JLabel();
        this.aLog = new JTextArea();
        this.aLog.setEditable(false);
        JScrollPane vListScroller = new JScrollPane(this.aLog);
        vListScroller.setPreferredSize(new Dimension(200, 200));
        vListScroller.setMinimumSize(new Dimension(100,100));

        vButtonPanel.add(this.aNorthButton);
        vButtonPanel.add(this.aSouthButton);
        vButtonPanel.add(this.aEastButton);
        vButtonPanel.add(this.aWestButton);
        vButtonPanel.add(this.aUpButton);
        vButtonPanel.add(this.aOutButton);
        vButtonPanel.add(this.aInventoryButton);
        vButtonPanel.add(this.aLookButton);
        vButtonPanel.add(this.aQuitButton);
        vButtonPanel.add(this.aHelpButton);
    
        vPanel.setLayout(new BorderLayout());
        vPanel.add(this.aImage, BorderLayout.NORTH);
        vPanel.add(vListScroller, BorderLayout.CENTER);
        vPanel.add(this.aEntryField, BorderLayout.SOUTH);
        vPanel.add(vButtonPanel, BorderLayout.EAST);

        this.aMyFrame.getContentPane().add(vPanel, BorderLayout.CENTER);
        this.aEntryField.addActionListener(this);
        this.aEastButton.addActionListener(this);
        this.aWestButton.addActionListener(this);
        this.aNorthButton.addActionListener(this);
        this.aSouthButton.addActionListener(this);
        this.aUpButton.addActionListener(this);
        this.aOutButton.addActionListener(this);
        this.aInventoryButton.addActionListener(this);
        this.aLookButton.addActionListener(this);
        this.aQuitButton.addActionListener(this);
        this.aHelpButton.addActionListener(this);
        
        // fermeture de la fenêtre
        this.aMyFrame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(final WindowEvent pE) {
                System.exit(0);
            }
        });

        this.aMyFrame.pack();
        this.aMyFrame.setVisible(true);
        this.aEntryField.requestFocus();
    }

    /**
     * Gère les actions sur les boutons et le texte
     * @param pE L'événement 
     */
    @Override public void actionPerformed(final ActionEvent pE) {
        if (pE.getSource() == this.aEastButton) {
            this.aEngine.interpretCommand("go east");
        }
        else if (pE.getSource() == this.aWestButton) {
            this.aEngine.interpretCommand("go west");
        }
        else if (pE.getSource() == this.aNorthButton) {
            this.aEngine.interpretCommand("go north");
        }
        else if (pE.getSource() == this.aSouthButton) {
            this.aEngine.interpretCommand("go south");
        }
        else if (pE.getSource() == this.aUpButton) {
            this.aEngine.interpretCommand("go up");
        }
        else if (pE.getSource() == this.aOutButton) {
            this.aEngine.interpretCommand("go out");
        }
        else if (pE.getSource() == this.aInventoryButton) {
            this.aEngine.interpretCommand("inventory");
        }
        else if (pE.getSource() == this.aQuitButton) {
            this.aEngine.interpretCommand("quit");
        }
        else if (pE.getSource() == this.aLookButton) {
            this.aEngine.interpretCommand("look");
        }
        else if (pE.getSource() == this.aHelpButton) {
            this.aEngine.interpretCommand("help");
        }
        else { 
            this.processCommand();
        }
    }

    /**
     * Traite une commande entrée dans le champ de texte
     */
    private void processCommand() {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText("");
        this.aEngine.interpretCommand(vInput);
    }
} 
