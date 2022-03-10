package domain.participant;

import domain.card.Cards;

public class Dealer extends Participant {

    public static final int DRAW_STANDARD = 16;

    public Dealer(Name name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean isFinished() {
        return cards.isBust() || cards.sum() > DRAW_STANDARD || cards.isBlackJack();
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
