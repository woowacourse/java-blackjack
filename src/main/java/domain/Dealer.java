package domain;

import domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    public Dealer(List<Card> holdCards) {
        super(holdCards);
    }

    public boolean isReceiveCard() {
        return calculateScore() <= 16;
    }
}
