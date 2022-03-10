package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Objects;

public class Dealer extends Human {
    private static final String DEALER_NAME = "딜러";
    public static final int HIT_STANDARD_NUMBER = 16;
    private final Cards cards;

    private Dealer() {
        this.cards = Cards.of();
    }

    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public boolean isHit() {
        return cards.getPoint() <= HIT_STANDARD_NUMBER;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public void addCard(final Card card) {
        cards.add(card);
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dealer dealer = (Dealer) o;
        return Objects.equals(cards, dealer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
