package backend;

import java.util.ArrayList;

public class Player {
    //general attributes for both players
    private String name;
    private int health;
    private int crystals;
    //cards in the hand of a player that can be played, you get a new card every round by taking a card from the deck
    private ArrayList<Card> cardsInHand;
    private ArrayList<Card> cardsOnBoard;

    public Player(String name) {
        this.name = name;
        this.health = 25;
        this.crystals = 1;
        this.cardsInHand = new ArrayList<>(4);
        this.cardsOnBoard = new ArrayList<>();
    }

    public Player(String name, int health, int crystals, ArrayList<Card> cardsInHand, ArrayList<Card> cardsOnBoard) { //a constructor that if you want you can make custom stats
        this.name = name;
        this.health = health;
        this.crystals = crystals;
        this.cardsInHand = cardsInHand;
        this.cardsOnBoard = cardsOnBoard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public int getCrystals() {
        return crystals;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public ArrayList<Card> getCardsOnBoard() {
        return cardsOnBoard;
    }

    public void addToCardsInHand(Card card) {
        cardsInHand.add(card);
    }

    public void addCrystal() {
        if(crystals < 10) {crystals++;}
    }

    public void getInitialCards(Deck deck) {
        for(int i = 0; i < 4; i++) { // players have 4 initial cards at the start of the game
            cardsInHand.add(deck.getCard());
        }
    }

    public boolean isPlayerDead() { //checks if the player is dead
        return health <= 0;
    }

    public void removeFromCardsOnBoard(Card card) {
        cardsOnBoard.remove(card);
    }


}
