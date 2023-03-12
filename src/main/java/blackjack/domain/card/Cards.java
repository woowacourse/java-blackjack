package blackjack.domain.card;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.ArrayList;
import java.util.List;

public final class Cards {
    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int BLACKJACK_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int totalScore() {
        int score = minScore();
        if (hasAce() && canChangeAceScore(score)) {
            return score + ACE_ADDITIONAL_SCORE;
        }
        return score;
    }

    private int minScore() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean canChangeAceScore(final int score) {
        return score + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_COUNT && totalScore() == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return BLACKJACK_SCORE < minScore();
    }

    public boolean isBlackjackScore() {
        return cards.size() != BLACKJACK_COUNT && totalScore() == BLACKJACK_SCORE;
    }

    public List<String> getSymbols() {
        return cards.stream()
                .map(Card::getSymbol)
                .collect(toUnmodifiableList());
    }
}
