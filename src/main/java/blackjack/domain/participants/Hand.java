package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.game.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {
    private final List<Card> possessedCards;

    Hand() {
        possessedCards = new ArrayList<>();
    }

    void addCard(Card card) {
        possessedCards.add(card);
    }

    Score getScore() {
        int countOfAce = countAce();
        Score scoreOfCards = calculateMinimumScore();

        for (int i = 0; i < countOfAce; i++) scoreOfCards = scoreOfCards.plusTenIfNotBusted();
        return scoreOfCards;
    }

    private int countAce() {
        return (int) possessedCards.stream()
                .filter(this::isAce)
                .count();
    }

    private boolean isAce(Card card) {
        return card.getDenomination() == Denomination.ACE;
    }

    private Score calculateMinimumScore() {
        return new Score(possessedCards.stream()
                .mapToInt(Card::getScore)
                .sum());
    }


    boolean isBusted() {
        return getScore().isOverThanMax();
    }

    public boolean isBlackjack() {
        return getScore().isMax() && possessedCards.size() == 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand that = (Hand) o;
        return Objects.equals(possessedCards, that.possessedCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(possessedCards);
    }

    List<Card> getPossessedCards() {
        return List.copyOf(possessedCards);
    }

}
