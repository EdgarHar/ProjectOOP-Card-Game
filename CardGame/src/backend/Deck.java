package backend;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> deck;

    Deck() {
        deck = new ArrayList<>(10);
        fillDeck();
    }


    public Card getCard() {
        Random random = new Random();
        int randomCardIndex = random.nextInt(deck.size());
        Card randomCard = deck.get(randomCardIndex);
        deck.remove(randomCardIndex);
        return randomCard;
    }


    private void fillDeck() { // read from file
        deck.add(new Card("Dog", 1, 2, 1));
        deck.add(new Card("Dog", 1, 2, 1));
        deck.add(new Card("Wolf", 2, 2, 3));
        deck.add(new Card("Wolf", 2, 2, 3));
        deck.add(new Card("Bear", 4, 5, 5));
        deck.add(new Card("Bear", 4, 5, 5));
        deck.add(new Angel());
        deck.add(new Angel());
        deck.add(new Witch());
        deck.add(new Witch());
    }


}
