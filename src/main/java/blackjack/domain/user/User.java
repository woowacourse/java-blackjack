package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;

import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.Round.GAME_OVER_SCORE;

public interface User {
    List<Card> getCards();

    String getName();

    boolean isGameOver(final int gameOverScore);

    default List<String> getCardsStatus() {
        return getCards().stream()
                .map(Card::getCardStatus)
                .collect(Collectors.toList());
    }

    default int getScore() {
        int score = getCards().stream()
                .mapToInt(Card::getScore)
                .sum();
        while (containAceCount() && score > GAME_OVER_SCORE) {
            score = Number.ACE.useSecondScore(score);
        }
        return score;
    }

    default void addFirstCards(final List<Card> cards) {
        getCards().addAll(cards);
    }

    default void addCard(final Card card) {
        getCards().add(card);
    }

    default void removeAllCards() {
        getCards().clear();
    }

    default boolean containAceCount() {
        return getCards().stream()
                .anyMatch(Card::containAce);
    }
}
