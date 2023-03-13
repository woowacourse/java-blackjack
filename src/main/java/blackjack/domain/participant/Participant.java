package blackjack.domain.participant;

import java.util.LinkedHashSet;
import java.util.Set;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;

public class Participant {

    private static final int BUST_BOUNDARY = 21;
    private static final int ELEVEN_ACE_VALUE = 11;
    private static final int NUMBER_OF_BLACKJACK_CARD = 2;

    private final Set<Card> cards;

    public Participant() {
        this.cards = new LinkedHashSet<>();
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    private boolean isElevenAce() {
        return hasAce() && (calculateSumOfRank() + ELEVEN_ACE_VALUE <= BUST_BOUNDARY);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getRank() == Rank.ACE);
    }

    public int calculateSumOfRank() {
        int sum = cards.stream()
                .mapToInt(card -> card.getRank().getValue())
                .sum();
        if (isElevenAce()) {
            sum += 10;
        }
        return sum;
    }

    public boolean isUnderThanBoundary(final int boundary) {
        return this.calculateSumOfRank() < boundary;
    }

    public boolean isBlackJack() {
        return calculateSumOfRank() == BUST_BOUNDARY && cards.size() == NUMBER_OF_BLACKJACK_CARD;
    }

    public boolean isBust() {
        return calculateSumOfRank() > BUST_BOUNDARY;
    }

    public Set<Card> getCards() {
        return this.cards;
    }
}
