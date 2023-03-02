package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.HashSet;
import java.util.Set;

public class Participant {

    private final Set<Card> cards;

    public Participant() {
        this.cards = new HashSet<>();
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public int calculateSumOfRank() {
        return cards.stream()
                .mapToInt(card -> card.getRank().getValue())
                .sum();
    }

    public Set<Card> getCards() {
        return cards;
    }

    public boolean isUnderThanBoundary(int number) {
        return this.calculateSumOfRank() < number;
    }
}
