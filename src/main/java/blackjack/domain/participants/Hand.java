package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.game.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {

    private final List<Card> cards;

    private Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public Hand() {
        this(Collections.emptyList());
    }

    public Hand addCard(final Card card) {
        final List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);

        return new Hand(newCards);
    }

    public Score getScore() {
        final int countOfAce = countAce();
        Score scoreOfCards = calculateMinimumScore();

        for (int i = 0; i < countOfAce; i++) {
            scoreOfCards = scoreOfCards.plusTenIfNotBusted();
        }
        return scoreOfCards;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(this::isAce)
                .count();
    }

    private boolean isAce(final Card card) {
        return card.getDenomination() == Denomination.ACE;
    }

    private Score calculateMinimumScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.minScore(), Score::sum);
    }

    public boolean isBust() {
        return getScore().isOverThanMax();
    }

    public boolean isBlackjack() {
        return getScore().isMax() && cards.size() == 2;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

}
