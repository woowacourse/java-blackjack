package domain.people;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import domain.card.Card;

public final class Dealer {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_MINIMUM_VALUE = 17;

    private final Participant participant;

    public Dealer() {
        participant = new Participant(new ArrayList<>(), DEALER_NAME);
    }

    public void receiveCard(final Card card) {
        participant.receiveCard(card);
    }

    public List<Card> fetchHand() {
        return participant.fetchHand();
    }

    public int fetchHandValue() {
        return participant.fetchHandValue();
    }

    public boolean shouldHit() {
        return participant.fetchHandValue() < DEALER_MINIMUM_VALUE;
    }

    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Dealer dealer = (Dealer)o;
        return Objects.equals(participant, dealer.participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participant);
    }
}
