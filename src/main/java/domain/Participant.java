package domain;

import java.util.List;

public abstract class Participant {
    private final Hand hand;

    protected Participant(Hand hand){
        this.hand=hand;
    }

    public List<Card> getHandCards(){
        return hand.getCards();
    }

    public Hand getHand(){
        return hand;
    }

    public void addHandCard(Card card){
        hand.addCard(card);
    }

    public boolean isBust(){
        return hand.getScore().isBust();
    }
}
