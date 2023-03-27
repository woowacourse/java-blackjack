package blackjack.domain.card;

import blackjack.domain.ScoreState;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private static final int ACE_HANDLE_SCORE = 10;

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
        if (hasAceCard()) {
            return getFinalScore(score);
        }

        return score;
    }

    private int getFinalScore(int score) {
        ScoreState scoreState = ScoreState.of(score + ACE_HANDLE_SCORE, cards.size());
        if (scoreState.isBust()) {
            return score;
        }

        return score + ACE_HANDLE_SCORE;
    }

    private boolean hasAceCard() {
        return cards.stream()
                    .anyMatch(Card::isAce);
    }

    public List<Card> toList() {
        return List.copyOf(cards);
    }

    public int size() {
        return cards.size();
    }
}
