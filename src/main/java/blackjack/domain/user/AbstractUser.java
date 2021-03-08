package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.Round.GAME_OVER_SCORE;

public abstract class AbstractUser {
    private final List<Card> cards = new ArrayList<>();

    public final List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public final int getScore() {
        int score = cards.stream()
                .mapToInt(Card::numberScore)
                .sum();
        int aceCount = getAceCount();
        while (aceCount-- > 0 && score > GAME_OVER_SCORE) {
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

    public final int getAceCount() {
        return (int) cards.stream()
                .filter(Card::containAce)
                .count();
    }

    public abstract String getName();

    public abstract boolean isGameOver(final int gameOverScore);
}
