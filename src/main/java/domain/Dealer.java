package domain;

import java.util.List;
import java.util.Objects;

public class Dealer extends Participant<Dealer>{

    private static final String DEALER_DEFAULT_NAME = "딜러";

    public Dealer(Cards cards) {
        super(new Name(DEALER_DEFAULT_NAME), cards);
    }

    public boolean shouldHit() {
        return !cards.isDealerDrawLimitExceeded();
    }

    public Card getInitialCard() {
        return cards.getInitialCard();
    }

    @Override
    public Dealer createParticipant(List<Card> providedCards) {
        return new Dealer(cards.addCards(providedCards));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(cards, dealer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
