package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.game.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardPocket {
    private final List<Card> possessedCards;

    public CardPocket() {
        possessedCards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        possessedCards.add(card);
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
        return (int) possessedCards.stream()
                .filter(this::isAce)
                .count();
    }

    private boolean isAce(final Card card) {
        return card.getSymbol() == Symbol.ACE;
    }

    private Score calculateMinimumScore() {
        return new Score(possessedCards.stream()
                .mapToInt(Card::getScore)
                .sum());
    }

    public boolean isBusted() {
        return getScore().isOverBlackjack();
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
