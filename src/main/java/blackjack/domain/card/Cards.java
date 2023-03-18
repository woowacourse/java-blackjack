package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        if (cards.size() == 2) {
            return this.getScore() == BLACKJACK_SCORE;
        }
        return false;
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }

    public int getScore() {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (hasAce()) {
            return calculateAce(score);
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateAce(int score) {
        if (score + ACE_BONUS_SCORE <= BLACKJACK_SCORE) {
            return score + ACE_BONUS_SCORE;
        }
        return score;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
