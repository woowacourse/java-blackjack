package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Dealer extends AbstractPlayer implements Player {

    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_LIMIT_POINT = 16;

    public Dealer() {
        this.cards = new Deck();
        this.name = DEALER_NAME;
    }

    @Override
    public boolean isDealer() {
        return true;
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

        return cards != null ? cards.equals(dealer.cards) : dealer.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
