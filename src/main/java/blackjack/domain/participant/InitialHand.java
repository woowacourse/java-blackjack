package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class InitialHand {
    private final Name name;
    private final List<Card> initialHand;

    public InitialHand(Participant participant) {
        this.name = participant.getName();
        this.initialHand = participant.getInitialOpenedCards();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return initialHand;
    }
}
