package domain.card;

import domain.score.Score;

import java.util.ArrayList;
import java.util.List;

public final class Cards {

    private static final int MAX_SCORE = 21;
    private static final int BIGGER_A_SCORE = 10;
    private static final int BLACKJACK_SIZE = 2;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (canUseBiggerAce(sum)) {
            sum += BIGGER_A_SCORE;
        }

        return Score.from(sum);
    }

    private boolean canUseBiggerAce(final int sum) {
        return sum + BIGGER_A_SCORE <= MAX_SCORE && isContainAce();
    }

    private boolean isContainAce() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore().isBlackjackCandidate();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
