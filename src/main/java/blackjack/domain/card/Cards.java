package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_SIZE_CONDITION = 2;
    private static final int BUST_CONDITION = 21;
    private static final int ACE_SCORE = 1;
    private static final int TEN_SCORE = 10;
    private static final int ELEVEN_SCORE = 11;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void hit(Card card) {
        this.cards.add(card);
    }

    public boolean isBlackJack() {
        if (this.cards.size() == BLACKJACK_SIZE_CONDITION && this.hasAce()) {
            return cards.stream()
                    .anyMatch(card -> card.getScore() == TEN_SCORE);
        }
        return false;
    }

    public boolean isBust() {
        return this.getScore() > BUST_CONDITION;
    }

    public int getScore() {
        int score = this.cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (score <= ELEVEN_SCORE && hasAce()) {
            score += TEN_SCORE;
        }
        return score;
    }

    private boolean hasAce() {
        return this.cards.stream().anyMatch(card -> card.getScore() == ACE_SCORE);
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(this.cards);
    }
}
