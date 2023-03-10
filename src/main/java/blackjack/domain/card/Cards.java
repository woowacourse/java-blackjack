package blackjack.domain.card;

import blackjack.domain.Score;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int MAX_SCORE = 21;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACKJACK_REQUIRE_COUNT = 2;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (hasAce()) {
            score = calculateAce(score);
        }
        return new Score(score);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateAce(int score) {
        if (score + ACE_BONUS_SCORE <= MAX_SCORE) {
            return score + ACE_BONUS_SCORE;
        }
        return score;
    }

    public boolean isBlackjack() {
        return calculateScore().isMaxScore() && cards.size() == BLACKJACK_REQUIRE_COUNT;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
