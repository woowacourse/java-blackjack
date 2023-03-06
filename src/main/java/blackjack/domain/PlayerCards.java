package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards() {
        this.cards = new ArrayList<>();
    }

    private int getTotalScore(int score, Card card) {
        if (card.isAce() && ScoreState.of(score)
                                      .isBust()) {
            score -= 10;
        }
        return score;
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

    public List<Card> toList() {
        return List.copyOf(cards);
    }
}
