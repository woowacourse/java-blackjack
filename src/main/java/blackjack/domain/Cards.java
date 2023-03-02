package blackjack.domain;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.ArrayList;
import java.util.List;

class Cards {
    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int BUST_LOWER_BOUND = 22;
    private static final int BLACKJACK_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards = new ArrayList<>();

    public Cards() {
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateMinScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int calculateTotalScore() {
        int score = calculateMinScore();
        if (hasAce() && canChangeAceScore(score)) {
            return score + ACE_ADDITIONAL_SCORE;
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean canChangeAceScore(final int score) {
        return score + ACE_ADDITIONAL_SCORE < BUST_LOWER_BOUND;
    }

    public List<String> getCardLetters() {
        return cards.stream()
                .map(Card::getLetter)
                .collect(toUnmodifiableList());
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_COUNT && calculateTotalScore() == BLACKJACK_SCORE;
    }
}
