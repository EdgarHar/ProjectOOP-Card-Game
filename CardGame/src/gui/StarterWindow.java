package gui;

import backend.Card;
import backend.Game;
import backend.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class StarterWindow { //created GUI components for use
    private JPanel panelCont;
    private JPanel gamePanel;
    private JPanel playerPanel;
    private JTextField firstPlayerNamField;
    private JTextField secondPlayerNameField;
    private JButton startTheGameButton;
    private JPanel panelTop;
    private JPanel panelMiddle;
    private JPanel panelButtom;
    private JLabel firstPlayerNameLabel;
    private JLabel secondPlayerNameLabel;
    private JButton endTurnButton;
    private JButton showButtonFirstPlayer1;
    private JButton playButtonFirstPlayer1;
    private JButton showButtonFirstPlayer2;
    private JButton playButtonFirstPlayer2;
    private JButton showButtonFirstPlayer3;
    private JButton playButtonFirstPlayer3;
    private JButton showButtonFirstPlayer4;
    private JButton playButtonFirstPlayer4;
    private JButton showButtonSecondPlayer1;
    private JButton playButtonSecondPlayer1;
    private JButton showButtonSecondPlayer2;
    private JButton playButtonSecondPlayer2;
    private JButton showButtonSecondPlayer3;
    private JButton playButtonSecondPlayer3;
    private JButton showButtonSecondPlayer4;
    private JLabel labelFirstPlayer1;
    private JLabel labelFirstPlayer2;
    private JLabel labelFirstPlayer3;
    private JLabel labelFirstPlayer4;
    private JLabel labelSecondPlayer1;
    private JLabel labelSecondPlayer2;
    private JLabel labelSecondPlayer3;
    private JLabel labelSecondPlayer4;
    private JPanel cardPanelFirstPlayer1;
    private JPanel cardPanelFirstPlayer2;
    private JPanel cardPanelFirstPlayer3;
    private JPanel cardPanelFirstPlayer4;
    private JPanel cardPanelSecondPlayer1;
    private JPanel cardPanelSecondPlayer2;
    private JPanel cardPanelSecondPlayer3;
    private JPanel cardPanelSecondPlayer4;
    private JButton playButtonSecondPlayer4;
    private JLabel healthFirstPlayer;
    private JLabel crystalFirstPlayer;
    private JLabel healthSecondPlayer;
    private JLabel crystalSecondPlayer;
    private JLabel boardCardsByPlayerLabel;
    private JLabel firstPlayerBoardCardLabel1;
    private JLabel firstPlayerBoardCardLabel2;
    private JLabel secondPlayerBoardCardLabel1;
    private JLabel secondPlayerBoardCardLabel2;
    private JButton attackFirstPlayerBoardCardButton1;
    private JButton attackFirstPlayerBoardCardButton2;
    private JButton attackSecondPlayerBoardCardButton1;
    private JButton attackSecondPlayerBoardCardButton2;
    private JPanel cardFirstPlayerPanel1;
    private JPanel cardFirstPlayerPanel2;
    private JPanel cardSecondPlayerPanel1;
    private JPanel cardSecondPlayerPanel2;
    private JPanel cardFirstPlayerParentPanel1;
    private JPanel cardFirstPlayerParentPanel2;
    private JPanel cardSecondPlayerParentPanel1;
    private JPanel cardSecondPlayerParentPanel2;
    private JLabel whoseTurnLabel1;
    private JLabel whoseTurnLabel2;
    private JCheckBox attackMeCheckBoxPlayer1Card1;
    private JCheckBox attackMeCheckBoxPlayer1Card2;
    private JCheckBox attackMeCheckBoxPlayer2Card1;
    private JCheckBox attackMeCheckBoxPlayer2Card2;
    private JCheckBox attackMeCheckBoxPlayer1;
    private JCheckBox attackMeCheckBoxPlayer2;
    private JPanel finishPanel;
    private JLabel winnerLabel;
    private JButton attackButton;
    private JPanel panel1;
    private JPanel panel3;
    private JPanel panel2;
    private CardLayout cardLayout;
    private Game game;
    private ArrayList<JLabel> labelsFirstPlayerCardsInHand;  // Lists of labels and buttons to simplify the code
    private ArrayList<JLabel> labelsSecondPlayerCardsInHand;
    private ArrayList<JButton> showButtonsFirstPlayerList;
    private ArrayList<JButton> showButtonsSecondPlayerList;
    private ArrayList<JButton> playButtonFirstPlayerList;
    private ArrayList<JButton> playButtonSecondPlayerList;
    private ArrayList<JButton> attackButtonList;
    Hashtable<JPanel,Card> panelCard; // hashtable to keep panel - card relationship and update


    StarterWindow() {
        cardLayout = new CardLayout(); // cardLayout sets different panels to show at different times (initially players' info fields, then game, then finish panel)
        panelCont = new JPanel();
        panelCard = new Hashtable<JPanel, Card>();
        panelCont.setLayout(cardLayout);
        panelCont.add(playerPanel, "1");
        panelCont.add(gamePanel, "2");
        panelCont.add(finishPanel, "3");
        cardLayout.show(panelCont, "1"); // set to first panel by default
    }


    void start() {
        startTheGameButton.addActionListener(this::actionPerformed);
    }


    private void addListenersToGame() {     // adding action listeners to all the buttons which need it and setting the actionPerformed to the overridden actionPerformed
        for(JButton button: showButtonsFirstPlayerList) {
            button.addActionListener(this::actionPerformed);
        }
        for(JButton button: showButtonsSecondPlayerList) {
            button.addActionListener(this::actionPerformed);
        }
        for(JButton button: playButtonFirstPlayerList) {
            button.addActionListener(this::actionPerformed);
        }
        for(JButton button: playButtonSecondPlayerList) {
            button.addActionListener(this::actionPerformed);
        }
        endTurnButton.addActionListener(this::actionPerformed);
        for(JButton button: attackButtonList) {
            button.addActionListener(this::actionPerformed);
        }

    }


    public void actionPerformed(ActionEvent e) { // functionality based on different events triggered by different actions
        if (e.getSource() == startTheGameButton) {
            String firstPlayerName = firstPlayerNamField.getText();
            String secondPlayerName = secondPlayerNameField.getText();

            if (firstPlayerName.isEmpty() || secondPlayerName.isEmpty()) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "One of the player names is empty. Please fill in both of the names to start the Game.");
            } else if (firstPlayerName.equals(secondPlayerName)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Players' names cannot be the same, please change one of the names!");
            }
            else {
                game = Game.createNewGame(firstPlayerName, secondPlayerName); //creates the game according to it's constructor
                renderNewGame(); // executes necessary methods to start the game
                cardLayout.show(panelCont, "2"); // set to second panel
                addListenersToGame(); // adding actionListeners to various components of the GUI
            }
        }

        else if (showButtonsFirstPlayerList.contains(e.getSource()) || showButtonsSecondPlayerList.contains(e.getSource())) {
                JButton button = (JButton) e.getSource(); //get the button from which the action was performed
                JPanel panel = (JPanel) button.getParent(); //get the panel of the button
                Component[] components = panel.getComponents(); //get array of the panel components
                JLabel label = (JLabel) components[0]; //label is the first component of the panel

                if(button.getText().equals("Show")) {
                    label.setVisible(true);
                    button.setText("Hide");
                } else if(button.getText().equals("Hide")) {
                    label.setVisible(false);
                    button.setText("Show");
                }

        }

        else if (playButtonFirstPlayerList.contains(e.getSource())) {
            if(game.isFirstPlayerTurn()) {
                JButton button = (JButton) e.getSource(); //get the button from which the action was performed
                JPanel panel = (JPanel) button.getParent(); //get the panel, to which the button belongs to
                addCardToBoard(panel, 1);
            }
            else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "It is not your turn, you can play only during your turn.");
            }

        }

        else if (playButtonSecondPlayerList.contains(e.getSource())) { //same thing as above, but for the second player
            if(!game.isFirstPlayerTurn()) {
                JButton button = (JButton) e.getSource();
                JPanel panel = (JPanel) button.getParent();
                addCardToBoard(panel, 2);
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "It is not your turn, you can play only during your turn.");
            }
        }

        else if (e.getSource() == endTurnButton) { //this executes when "end turn" is clicked, it changed whos turn it in the game object, and renders whose turn label, and deselects all the "attack me" checkboxes, as otherwise they always stay selected.
            game.changeWhoseTurn();
            renderWhoseTurn();
            deselectCheckBoxes();
        }

        else if (attackButtonList.contains(e.getSource())) { //this is executed when "Attack" is clicked and only one "attack me" checkbox is selected
            JButton attackButton = (JButton) e.getSource(); //gets button which was clicked
            ArrayList<JCheckBox> checkedBoxesList = getCheckedCheckboxes(); //gets all the checked "Attack me" boxes(to which the card needs to attack)
            // If special cards are considered, this should be changed
            if(checkedBoxesList.size() > 1) { //if more than one checkboxes are checked, does not let an attack
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Only 1 card or player can be attacked at a time.");
            } else if (checkedBoxesList.size() < 1){ //if none are selected, does not let an attack
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "No card or player has been selected to be attacked.");
            } else {
                JPanel attackButtonParentPanel = (JPanel) attackButton.getParent(); //gets the panel of the button that was clicked
                Card attackerCard = panelCard.get(attackButtonParentPanel); //taking the card from the hashtable via its "key", which is the panel
                if(checkedBoxesList.get(0).getText().equals("Attack Card")) { //taking first element of the arraylist as there is only one element if the "else" statement is executed
                    JPanel attackedCheckboxParentPanel = (JPanel) checkedBoxesList.get(0).getParent(); //taking the panel of the attacked card
                    Card attackedCard = panelCard.get(attackedCheckboxParentPanel); //getting attacked card from the hashtable
                    attackerCard.attack(attackedCard); //attack is done.
                    checkAttackState(attackerCard, attackedCard); //checks the aftermath, to update the stats of the cards respectively
                } else { // if checkedBoxesList.get(0).getText().equals("Attack Card") is not true, then a player has been attacked
                    if(game.isFirstPlayerTurn()) { // if it is first player's turn, then the second player has been attacked
                        attackerCard.attack(game.getSecondPlayer()); // attack is done to the player (attack() is an overloaded method)
                        checkAttackState(attackerCard, game.getSecondPlayer()); // stats are checked again to update attacked player's health
                    } else {
                        attackerCard.attack(game.getFirstPlayer()); //same thing but for the first player
                        checkAttackState(attackerCard, game.getFirstPlayer());
                    }
                }
            }
            attackButton.setEnabled(false); // set visibility to false so the same card cannot attack another card/player during that turn
        }
    }



    private void deselectCheckBoxes() { //this method deselects all the "attack me" boxes, as otherwise they would have stayed checked
        for(JCheckBox checkBox: getCheckedCheckboxes()) {
            checkBox.setSelected(false);
        }
    }

    private void checkAttackState(Card attackerCard, Card attackedCard) { //updating panels and labels based on attacked state of cards

        JPanel attackerPanel = findValueFromKey(attackerCard); //getting key from value
        JPanel attackedPanel = findValueFromKey(attackedCard); //getting key from value

        if(attackerCard.isCardDestroyed() && attackedCard.isCardDestroyed()) {
            attackerPanel.getParent().setVisible(false);
            game.destroyCard(attackerCard);
            panelCard.remove(attackerPanel);
            attackedPanel.getParent().setVisible(false);
            game.destroyCard(attackedCard);
            panelCard.remove(attackedPanel);
        } else if (attackerCard.isCardDestroyed()) {
            attackerPanel.getParent().setVisible(false);
            game.destroyCard(attackerCard);
            panelCard.remove(attackerPanel);
            JPanel attackedPanelUpdated = findValueFromKey(attackedCard);
            updateCardOnBoard(attackedPanelUpdated, attackedCard);
        } else if(attackedCard.isCardDestroyed()) {
            attackedPanel.getParent().setVisible(false);
            game.destroyCard(attackedCard);
            panelCard.remove(attackedPanel);
            JPanel attackerPanelUpdated = findValueFromKey(attackerCard);
            updateCardOnBoard(attackerPanelUpdated, attackerCard);
        } else {
            JPanel attackerPanelUpdated = findValueFromKey(attackerCard);
            JPanel attackedPanelUpdated = findValueFromKey(attackedCard);
            updateCardOnBoard(attackerPanelUpdated, attackerCard);
            updateCardOnBoard(attackedPanelUpdated, attackedCard);
        }
    }

    private JPanel findValueFromKey(Card card) { //chgitem es incher anum, hashtablei het kap uni bayc
        JPanel panel = null;
        for (Map.Entry<JPanel, Card> entry : panelCard.entrySet()) {
            if (entry.getValue() == card) {
                panel = entry.getKey();
            }
        }
        return panel;
    }



    private void checkAttackState(Card attackerCard, Player attackedPlayer) { //overloaded method for player
        updatePlayerInfo(attackedPlayer);
        if(attackedPlayer.isPlayerDead()) {
            finish(attackedPlayer);
        }
    }


    private void finish(Player lostPlayer) { //changes to 3rd panel, which is the win screen
        Player winnerPlayer = game.getWinnerPlayer(lostPlayer);
        winnerLabel.setText("THE WINNER IS " + winnerPlayer.getName() + " ! CONGRATS !");
        cardLayout.show(panelCont, "3");
    }

    private void updatePlayerInfo(Player player) {
        if(game.getFirstPlayer() == player) {
            healthFirstPlayer.setText("Health: " + player.getHealth());
        } else {
            healthSecondPlayer.setText("Health: " + player.getHealth());
        }
    }


    private ArrayList<JCheckBox> getCheckedCheckboxes() { //makes a checkbox list, to set the checkboxes "checks" to false after end of every turn or to determine if an attack can be made
        ArrayList<JCheckBox> checkedBoxesList = new ArrayList<>();
        if(attackMeCheckBoxPlayer1.isSelected()) {checkedBoxesList.add(attackMeCheckBoxPlayer1);}
        if(attackMeCheckBoxPlayer2.isSelected()) {checkedBoxesList.add(attackMeCheckBoxPlayer2);}
        if(attackMeCheckBoxPlayer1Card1.isSelected()) {checkedBoxesList.add(attackMeCheckBoxPlayer1Card1);}
        if(attackMeCheckBoxPlayer1Card2.isSelected()) {checkedBoxesList.add(attackMeCheckBoxPlayer1Card2);}
        if(attackMeCheckBoxPlayer2Card1.isSelected()) {checkedBoxesList.add(attackMeCheckBoxPlayer2Card1);}
        if(attackMeCheckBoxPlayer2Card2.isSelected()) {checkedBoxesList.add(attackMeCheckBoxPlayer2Card2);}
        return checkedBoxesList;
    }

    private void updateCardOnBoard(JPanel panel, Card card) {
        JLabel label = (JLabel) panel.getComponents()[0];
        label.setText("<html>Name: " + card.getName() + "<br>" + "Cost: " + card.getCost() + "<br>" + "Attack: " + card.getAttack() + "<br>" + "Health: " + card.getHealth() + "</html>");
        JCheckBox checkBox = (JCheckBox) panel.getComponents()[2];
        checkBox.setSelected(false);
    }


    private void addCardToBoard(JPanel panel, int player) {
        Card card = panelCard.get(panel); //panelcard is our hashtable, which links panels to card objects(key, value), Key=panel, value=card
        if(player == 1) {
            JLabel label = (JLabel) panel.getComponents()[0];
            if(!cardFirstPlayerParentPanel1.isVisible()) { //checks if first space is free
                panelCard.put((JPanel) firstPlayerBoardCardLabel1.getParent(), card); //adds the (boardPanel, card) pair to the hashtable
                firstPlayerBoardCardLabel1.setText(label.getText()); //sets played card information on board's label
                panel.setVisible(false); //played card's panel visibility is set to false as the played card is on the board and not on hand
                cardFirstPlayerParentPanel1.setVisible(true); //setting visiblity of board panel to true
            } else if(!cardFirstPlayerParentPanel2.isVisible()) {
                panelCard.put((JPanel) firstPlayerBoardCardLabel2.getParent(), card);
                firstPlayerBoardCardLabel2.setText(label.getText());
                panel.setVisible(false);
                cardFirstPlayerParentPanel2.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "You cannot add a new card, as two cards are already on the board.");
            }
            panelCard.remove(panel); //removes the (panel, card) (which was played) pair from the hashtable
            game.getFirstPlayer().getCardsInHand().remove(card); //removing from the first player's card in hand
            game.getFirstPlayer().getCardsOnBoard().add(card); //adding the card to the board
        }
        if(player == 2) {
            JLabel label = (JLabel) panel.getComponents()[0];
            if(!cardSecondPlayerParentPanel1.isVisible()) {
                panelCard.put((JPanel) secondPlayerBoardCardLabel1.getParent(), card);
                secondPlayerBoardCardLabel1.setText(label.getText());
                panel.setVisible(false);
                cardSecondPlayerParentPanel1.setVisible(true);
            } else if(!cardSecondPlayerParentPanel2.isVisible()) {
                panelCard.put((JPanel) secondPlayerBoardCardLabel2.getParent(), card);
                secondPlayerBoardCardLabel2.setText(label.getText());
                panel.setVisible(false);
                cardSecondPlayerParentPanel2.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "You cannot add a new card, as two cards are already on the board.");
            }
            panelCard.remove(panel);
            game.getSecondPlayer().getCardsInHand().remove(card);
            game.getSecondPlayer().getCardsOnBoard().add(card);
        }
    }


    private void renderNewGame() {
        setPlayerInfo(); // sets player's name label, health and crystals
        setPlayerCardsInHandLabelsButtonsLists(); // add labels and buttons to arrayLists to handle easily in actionPerformed and other methods
        setLabelsForCardsInHand(); // sets labels for players' cards in hand
        hideLabelsByDefault(); // hide the card information by default and show when "Show" is clicked
        hideBoardCardsByDefault(); // hide the cards' panels on the board by default
        setBoardCardsLabel(); // set the label showing the cards on board for each player
        renderWhoseTurn(); // making changes on the board based on player's turns
    }

    private void renderWhoseTurn() {
        if(game.isFirstPlayerTurn()) {
            whoseTurnLabel1.setText("It is " + game.getFirstPlayer().getName() + "'s turn!");
            whoseTurnLabel2.setText("");
            attackMeCheckBoxPlayer1Card1.setEnabled(false);
            attackMeCheckBoxPlayer1Card2.setEnabled(false);
            attackMeCheckBoxPlayer1.setEnabled(false);
            attackMeCheckBoxPlayer2Card1.setEnabled(true);
            attackMeCheckBoxPlayer2Card2.setEnabled(true);
            attackMeCheckBoxPlayer2.setEnabled(true);
            attackSecondPlayerBoardCardButton1.setEnabled(false);
            attackSecondPlayerBoardCardButton2.setEnabled(false);
            attackFirstPlayerBoardCardButton1.setEnabled(true);
            attackFirstPlayerBoardCardButton2.setEnabled(true);
        } else {
            whoseTurnLabel2.setText("It is " + game.getSecondPlayer().getName() + "'s turn!");
            whoseTurnLabel1.setText("");
            attackMeCheckBoxPlayer1Card1.setEnabled(true);
            attackMeCheckBoxPlayer1Card2.setEnabled(true);
            attackMeCheckBoxPlayer1.setEnabled(true);
            attackMeCheckBoxPlayer2Card1.setEnabled(false);
            attackMeCheckBoxPlayer2Card2.setEnabled(false);
            attackMeCheckBoxPlayer2.setEnabled(false);
            attackSecondPlayerBoardCardButton1.setEnabled(true);
            attackSecondPlayerBoardCardButton2.setEnabled(true);
            attackFirstPlayerBoardCardButton1.setEnabled(false);
            attackFirstPlayerBoardCardButton2.setEnabled(false);
        }
    }

    private void setBoardCardsLabel() {
        boardCardsByPlayerLabel.setText("<html>" + "&lt;- Cards of " + game.getFirstPlayer().getName() + "<br><br><br>" + "Cards of " + game.getSecondPlayer().getName() + " ->"+ "</html>");
    }


    private void hideBoardCardsByDefault() {
        cardFirstPlayerParentPanel1.setVisible(false);
        cardFirstPlayerParentPanel2.setVisible(false);
        cardSecondPlayerParentPanel1.setVisible(false);
        cardSecondPlayerParentPanel2.setVisible(false);
    }


    private void setPlayerInfo() {
        firstPlayerNameLabel.setText("Player: " + game.getFirstPlayer().getName());
        secondPlayerNameLabel.setText("Player: " + game.getSecondPlayer().getName());
        healthFirstPlayer.setText("Health: " + game.getFirstPlayer().getHealth());
        healthSecondPlayer.setText("Health: " + game.getSecondPlayer().getHealth());
        crystalFirstPlayer.setText("Crystals: " + game.getFirstPlayer().getCrystals());
        crystalSecondPlayer.setText("Crystals: " + game.getSecondPlayer().getCrystals());
    }


    private void hideLabelsByDefault() {
        for(JLabel label: labelsFirstPlayerCardsInHand) {
            label.setVisible(false);
        }
        for(JLabel label: labelsSecondPlayerCardsInHand) {
            label.setVisible(false);
        }
    }


    private void setPlayerCardsInHandLabelsButtonsLists() {
        labelsFirstPlayerCardsInHand = new ArrayList<>();
        labelsFirstPlayerCardsInHand.add(labelFirstPlayer1);
        labelsFirstPlayerCardsInHand.add(labelFirstPlayer2);
        labelsFirstPlayerCardsInHand.add(labelFirstPlayer3);
        labelsFirstPlayerCardsInHand.add(labelFirstPlayer4);

        labelsSecondPlayerCardsInHand = new ArrayList<>();
        labelsSecondPlayerCardsInHand.add(labelSecondPlayer1);
        labelsSecondPlayerCardsInHand.add(labelSecondPlayer2);
        labelsSecondPlayerCardsInHand.add(labelSecondPlayer3);
        labelsSecondPlayerCardsInHand.add(labelSecondPlayer4);

        showButtonsFirstPlayerList = new ArrayList<>();
        showButtonsFirstPlayerList.add(showButtonFirstPlayer1);
        showButtonsFirstPlayerList.add(showButtonFirstPlayer2);
        showButtonsFirstPlayerList.add(showButtonFirstPlayer3);
        showButtonsFirstPlayerList.add(showButtonFirstPlayer4);

        showButtonsSecondPlayerList = new ArrayList<>();
        showButtonsSecondPlayerList.add(showButtonSecondPlayer1);
        showButtonsSecondPlayerList.add(showButtonSecondPlayer2);
        showButtonsSecondPlayerList.add(showButtonSecondPlayer3);
        showButtonsSecondPlayerList.add(showButtonSecondPlayer4);

        playButtonFirstPlayerList = new ArrayList<>();
        playButtonFirstPlayerList.add(playButtonFirstPlayer1);
        playButtonFirstPlayerList.add(playButtonFirstPlayer2);
        playButtonFirstPlayerList.add(playButtonFirstPlayer3);
        playButtonFirstPlayerList.add(playButtonFirstPlayer4);

        playButtonSecondPlayerList = new ArrayList<>();
        playButtonSecondPlayerList.add(playButtonSecondPlayer1);
        playButtonSecondPlayerList.add(playButtonSecondPlayer2);
        playButtonSecondPlayerList.add(playButtonSecondPlayer3);
        playButtonSecondPlayerList.add(playButtonSecondPlayer4);

        attackButtonList = new ArrayList<>();
        attackButtonList.add(attackFirstPlayerBoardCardButton1);
        attackButtonList.add(attackFirstPlayerBoardCardButton2);
        attackButtonList.add(attackSecondPlayerBoardCardButton1);
        attackButtonList.add(attackSecondPlayerBoardCardButton2);

    }


    private void setLabelsForCardsInHand() {
        Player player1 = game.getFirstPlayer();
        Player player2 = game.getSecondPlayer();
        for(int i = 0; i < player1.getCardsInHand().size(); i++) {
            labelsFirstPlayerCardsInHand.get(i).setText("<html>Name: " + player1.getCardsInHand().get(i).getName() + "<br>" + "Cost: " + player1.getCardsInHand().get(i).getCost() + "<br>" + "Attack: " + player1.getCardsInHand().get(i).getAttack() + "<br>" + "Health: " + player1.getCardsInHand().get(i).getHealth() + "</html>");
            panelCard.put((JPanel) labelsFirstPlayerCardsInHand.get(i).getParent(), player1.getCardsInHand().get(i));
            labelsSecondPlayerCardsInHand.get(i).setText("<html>Name: " + player2.getCardsInHand().get(i).getName() + "<br>" + "Cost: " + player2.getCardsInHand().get(i).getCost() + "<br>" + "Attack: " + player2.getCardsInHand().get(i).getAttack() + "<br>" + "Health: " + player2.getCardsInHand().get(i).getHealth() + "</html>");
            panelCard.put((JPanel) labelsSecondPlayerCardsInHand.get(i).getParent(), player2.getCardsInHand().get(i));
        }
    }


    public JPanel getPanelCont() {
        return panelCont;
    }


}


// 1. On each turn a card should be added to Player's cards on hand and should be removed from deck
// 2. Crystals should be considered when playing the cards
// 3. Crystal = crystal - cost on each played card
// 4. Add abilities.