package domain;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    public void prepareGame(CardDeck deck) {
        hit(deck);
        hit(deck);
    }

    @Override
    public boolean canHit() {
        return !isBurst();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
