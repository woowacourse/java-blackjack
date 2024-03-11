package domain;

public class Player extends Gamer{
    public Player(Name name){
        super(name);
    }
    @Override
    public int hit(Deck deck) {
        hand.add(deck.draw());
        return 1;
    }
}
