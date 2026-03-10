package domain;

import domain.card.Card;

import java.util.List;

public class Dealer {
    private static final int HIT_LIMIT = 17;
    private final Hand hand;

    public Dealer(){
        hand = new Hand();
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    public void draw(Card card){
        hand.addCard(card);
    }

    public List<Card> getCards(){
        return hand.getCards();
    }

    public Card getFirstCard(){
        return hand.getFirstCard();
    }

    public boolean shouldHit() {
        return hand.getSum() < HIT_LIMIT;
    }

    public int getHandSize(){
        return hand.getSize();
    }

    public int getScore() {
        return hand.getSum();
    }
}
