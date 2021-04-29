package backend;

public class Game {

    private Player firstPlayer;
    private Player secondPlayer;
    private Deck deck;
    private Player whoseTurn;

    public Game(String firstPlayerName, String secondPlayerName) {
        firstPlayer = new Player(firstPlayerName);
        secondPlayer = new Player(secondPlayerName);
        deck = new Deck();
        giveInitialPlayerCards();
        whoseTurn = firstPlayer;
    }

    public Player getWhoseTurn() {
        return whoseTurn;
    }

    public void setWhoseTurn(Player whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    public boolean isFirstPlayerTurn() { //checks if it's the first player's turn, if it's false, then its the second player's turn
        return whoseTurn == firstPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void giveInitialPlayerCards() { //gives initial 4 cards to the players
        firstPlayer.getInitialCards(deck);
        secondPlayer.getInitialCards(deck);
    }

    public void changeWhoseTurn() {
        if(whoseTurn == firstPlayer) {
            setWhoseTurn(secondPlayer);
        } else {
            setWhoseTurn(firstPlayer);
        }
    }

    public static Game createNewGame(String firstPlayerName, String secondPlayerName) {
        Game game = new Game(firstPlayerName, secondPlayerName);
        return game;
    }

    public void destroyCard(Card card) { //
        if(firstPlayer.getCardsOnBoard().contains(card)) {
            firstPlayer.removeFromCardsOnBoard(card);
        } else {
            secondPlayer.removeFromCardsOnBoard(card);
        }
    }

    public Player getWinnerPlayer(Player lostPlayer) {
        return lostPlayer == secondPlayer ? firstPlayer: secondPlayer;
    }
}
