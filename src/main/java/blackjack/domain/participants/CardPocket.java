package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardPocket {

    private static final int BLACKJACK = 21;
    private static final int BIGGER_VALUE_OF_ACE = 10;
    private final List<Card> possessedCards;

    public CardPocket() {
        possessedCards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        possessedCards.add(card);
    }

    public int getScore() {
        final int countOfAce = countAce();
        int scoreOfCards = calculateMinimumScore();

        for (int i = 0; i < countOfAce; i++) {
            scoreOfCards = calculateMaximumScore(scoreOfCards);
        }
        return scoreOfCards;
    }

    private int countAce() {
        return (int) possessedCards.stream()
                .filter(card -> card.getSymbol() == Symbol.ACE)
                .count();
    }

    private int calculateMinimumScore() {
        return possessedCards.stream()
                .mapToInt(card -> card.getSymbol()
                        .getScore())
                .sum();
    }

    private int calculateMaximumScore(final int score) {
        if (score + BIGGER_VALUE_OF_ACE > BLACKJACK) {
            return score;
        }
        return score + BIGGER_VALUE_OF_ACE;
    }

    public boolean isBusted() {
        return getScore() > BLACKJACK;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CardPocket that = (CardPocket) o;
        return Objects.equals(possessedCards, that.possessedCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(possessedCards);
    }

    public List<Card> getPossessedCards() {
        return List.copyOf(possessedCards);
    }

}
