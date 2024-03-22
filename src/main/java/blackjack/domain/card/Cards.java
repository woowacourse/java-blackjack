package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Cards createEmpty() {
        return new Cards(new ArrayList<>());
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public void addAll(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int calculateScore() {
        int totalScore = cards.stream()
                .map(Card::getScore)
                .reduce(0, Integer::sum);

        return calculateAceScore(totalScore);
    }

    private int calculateAceScore(final int totalScore) {
        boolean hasAceCard = cards.stream()
                .anyMatch(Card::isAce);

        if (hasAceCard && totalScore + ACE_BONUS_SCORE <= BLACKJACK_SCORE) {
            return totalScore + ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    public boolean isBurst() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public int size() {
        return cards.size();
    }

    public Card getFirst() {
        return cards.get(0);
    }

    public boolean isScoreLessOrEqual(final int value) {
        int score = calculateScore();
        return value >= score;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cards=" + cards +
                '}';
    }
}
