package domain.player;

import domain.card.Card;
import domain.card.Cards;

public class Dealer implements Participant {
    private static final int DRAW_STANDARD = 16;

    private final String name = "딜러";
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

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
