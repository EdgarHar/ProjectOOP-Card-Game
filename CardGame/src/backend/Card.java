package backend;

public class Card {
    //attributes that every card is going to have
    private String name;
    private int cost;
    private int health;
    private int attack;

    //constructor which will be inherited
    public Card(String name, int cost, int health, int attack) {
        this.name = name;
        this.cost = cost;
        this.health = cost;
        this.attack = attack;
    }

    public Card() {}

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void attack(Card attackedCard) { //what happens if a card is attacked
        this.health = this.health - attackedCard.attack;
        attackedCard.health = attackedCard.health - this.attack;
    }

    public void attack(Player attackerPlayer) { //what happens if a player is attacked
        attackerPlayer.setHealth(attackerPlayer.getHealth() - this.attack);
    }

    public boolean isCardDestroyed() { //checks if the card is destroyed
        return health <= 0;
    }


}
