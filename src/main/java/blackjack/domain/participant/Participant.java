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
    private int numberOfElevenAce = 0;

    public Participant() {
        this.cards = new LinkedHashSet<>();
    }

    public void receiveCard(final Card card) {
        if (isElevenAce(card)) {
            numberOfElevenAce++;
        }
        cards.add(card);
    }

    private boolean isElevenAce(final Card card) {
        return card.getRank() == Rank.ACE && (calculateSumOfRank() + ELEVEN_ACE_VALUE < BUST_BOUNDARY);
    }

    public int calculateSumOfRank() {
        return cards.stream()
                .mapToInt(card -> card.getRank().getValue())
                .sum() + numberOfElevenAce * 10;
    }

    public boolean isUnderThanBoundary(final int boundary) {
        return this.calculateSumOfRank() < boundary;
    }

    public boolean isBlackJack() {
        return calculateSumOfRank() == BUST_BOUNDARY && cards.size() == NUMBER_OF_BLACKJACK_CARD;
    }

    public boolean isBust() {
        return calculateSumOfRank() >= BUST_BOUNDARY;
    }

    public Set<Card> getCards() {
        return this.cards;
    }
}
