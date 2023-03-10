package blackjack.domain.card;

import blackjack.domain.game.ScoreState;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            CardNumber cardNumber = card.getNumber();
            score += cardNumber.getScore();
        }
        return handleAce(score);
    }

    private int handleAce(int score) {
        for (Card card : cards) {
            score = getTotalScore(score, card);
        }
        return score;
    }

    private int getTotalScore(int score, Card card) {
        if (card.isAce() && ScoreState.of(score).isBust()) {
            score -= 10;
        }
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerCards that = (PlayerCards) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public List<Card> toList() {
        return List.copyOf(cards);
    }
}
