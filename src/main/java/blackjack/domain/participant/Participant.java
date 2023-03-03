package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;

import java.util.HashSet;
import java.util.Set;

public class Participant {

    private final Set<Card> cards;
    private int numberOfElevenAce = 0;

    public Participant() {
        this.cards = new HashSet<>();
    }

    public void receiveCard(final Card card) {
        if (card.getRank() == Rank.ACE && calculateSumOfRank() + 11 < 21) {
            numberOfElevenAce++;
        }
        cards.add(card);
    }

    public int calculateSumOfRank() {
        return cards.stream()
                .mapToInt(card -> card.getRank().getValue())
                .sum() + numberOfElevenAce * 10;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public boolean isUnderThanBoundary(int number) {
        return this.calculateSumOfRank() < number;
    }

    public boolean isBlackJack() {
        return calculateSumOfRank() == 21 && cards.size() == 2;
    }

    public boolean isBust() {
        return calculateSumOfRank() >= 21;
    }
}
