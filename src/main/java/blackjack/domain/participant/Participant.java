package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.HashSet;
import java.util.Set;

public class Participant {

    public static final int BUST_BOUNDARY = 21;
    private static final int ELEVEN_ACE_VALUE = 10;
    private static final int NUMBER_OF_BLACKJACK_CARD = 2;

    private final Set<Card> cards;

    public Participant() {
        this.cards = new HashSet<>();
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public int calculateSumOfRank() {
        int sumOfRank = cards.stream()
                .mapToInt(card -> card.getRank().getValue())
                .sum();
        if (canCalculateToElevenAce(sumOfRank)) {
            return sumOfRank + ELEVEN_ACE_VALUE;
        }
        return sumOfRank;
    }

    private boolean canCalculateToElevenAce(int sumOfRank) {
        return hasAce() && (sumOfRank + ELEVEN_ACE_VALUE <= BUST_BOUNDARY);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isUnderThanBoundary(final int number) {
        return this.calculateSumOfRank() < number;
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
