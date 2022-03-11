package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Player extends Participant {

    private boolean isStay = false;

    public Player(Name name, Cards cards) {
        super(name, cards);
    }

    public void stay() {
        isStay = true;
    }
    
    public boolean isStay() {
        return isStay;
    }

    @Override
    public boolean isFinished() {
        return cards.isBust() || cards.isBlackJack() || isStay();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
