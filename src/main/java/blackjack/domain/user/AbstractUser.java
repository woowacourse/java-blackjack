package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.Round.GAME_OVER_SCORE;

public abstract class AbstractUser {
    private final List<Card> cards = new ArrayList<>();

    public final List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public final List<String> getCardsStatus() {
        return cards.stream()
                .map(Card::getCardStatus)
                .collect(Collectors.toList());
    }

    public final int getScore() {
        int score = cards.stream()
                .mapToInt(Card::numberScore)
                .sum();
        while (containAceCount() && score > GAME_OVER_SCORE) {
            score = Number.ACE.useSecondScore(score);
        }
        return score;
    }

    public final void addFirstCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public final void addCard(final Card card) {
        cards.add(card);
    }

    public final boolean containAceCount() {
        return cards.stream()
                .anyMatch(Card::containAce);
    }

    abstract String getName();

    abstract boolean isGameOver(final int gameOverScore);
}
