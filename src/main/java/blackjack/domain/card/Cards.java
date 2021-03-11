package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_SIZE_CONDITION = 2;
    private static final int BUST_CONDITION = 21;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int ACE_SCORE_CHANGEABLE_CONDITION = 11;
    private static final int BLACKJACK_CONDITION_CARD_SCORE_TEN = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void draw(Card card) {
        this.cards.add(card);
    }

    public int getScore() {
        int score = this.cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (score <= ACE_SCORE_CHANGEABLE_CONDITION && hasAce()) {
            score += ACE_BONUS_SCORE;
        }
        return score;
    }

    private boolean hasAce() {
        return this.cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBlackJack() {
        if (this.cards.size() == BLACKJACK_SIZE_CONDITION && this.hasAce()) {
            return cards.stream()
                    .anyMatch(card -> card.getScore() == BLACKJACK_CONDITION_CARD_SCORE_TEN);
        }
        return false;
    }

    public boolean isBust() {
        return this.getScore() > BUST_CONDITION;
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(this.cards);
    }
}
