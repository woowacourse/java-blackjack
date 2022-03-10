package blackjack.domain.human;

import blackjack.domain.card.Cards;
import java.util.Objects;

public class Dealer extends Human {
    private static final int HIT_STANDARD_NUMBER = 16;

    private Dealer() {
        super(Cards.of(), "딜러");
    }

    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public boolean isAbleToHit() {
        return cards.getPoint() <= HIT_STANDARD_NUMBER;
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
