package domain;

import domain.card.Card;

import java.util.List;

public abstract class Participant {
    protected final String name;
    protected final Hand hand;

    protected Participant(String name){
        this.name = name;
        hand = new Hand();
    }

    public void draw(Card card){
        hand.addCard(card);
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    public abstract boolean canHit();

    public String getName(){
        return name;
    }

    public int getScore() {
        return hand.getSum();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getHandSize() {
        return hand.getSize();
    }
}
