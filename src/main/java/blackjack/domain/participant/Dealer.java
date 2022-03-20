package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    public static final int DRAW_STANDARD = 16;

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public Dealer(Cards cards) {
        this();
        super.cards = cards;
    }

    @Override
    public boolean isFinished() {
        return cards.sum() > DRAW_STANDARD;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
