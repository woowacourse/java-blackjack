package domain.player;

import domain.card.Card;
import domain.card.Cards;

public class Dealer implements Participant {
    private static final String DEALER_NAME = "딜러";
    public static final int DRAW_STANDARD = 16;

    private final Name name = new Name(DEALER_NAME);
    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public boolean isFinished() {
        return cards.isBust() || cards.sum() > DRAW_STANDARD || cards.isBlackJack();
    }

    @Override
    public void drawCard(Card card) {
        cards.add(card);
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
