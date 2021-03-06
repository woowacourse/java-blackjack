package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;

import java.util.List;
import java.util.stream.Collectors;

public interface User {
    String getName();

    List<Card> getCards();

    boolean isGameOver(int gameOverScore);

    default void addFirstCards(List<Card> cards) {
        getCards().addAll(cards);
    }

    default int calculateScore(int gameOverScore) {
        int score = getCards().stream()
                .mapToInt(Card::getScore)
                .sum();
        int aceCount = 0;
        if (score > gameOverScore) {
            aceCount = findAceCount();
        }

        while (emptyAceCard(aceCount) && !belowScore(score, gameOverScore)) {
            score = Number.ACE.useSecondScore(score);
        }
        return score;
    }

    default boolean belowScore(int score, int gameOverScore) {
        return score <= gameOverScore;
    }

    default boolean emptyAceCard(int aceCount) {
        return aceCount > 0;
    }

    default int findAceCount() {
        return (int) getCards().stream()
                .filter(Card::containAce)
                .count();
    }

    default void addCard(Card card) {
        getCards().add(card);
    }

    default List<String> getCardsStatus() {
        return getCards().stream()
                .map(card -> card.getCardStatus())
                .collect(Collectors.toList());
    }
}
