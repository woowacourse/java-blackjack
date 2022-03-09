package blackjack.domain;

import java.util.List;

public class Dealer {
    public static final int DRAWABLE_LIMIT_VALUE = 16;
    private final Cards cards;

    public Dealer(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public boolean isDrawable() {
        return cards.calculateTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }

    public void combine(Card card) {
        cards.combine(card);
    }

    public List<Card> getCards() {
        return cards.getValue();
    }
}
