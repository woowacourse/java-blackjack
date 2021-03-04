package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    private static final int BLACKJACK_SIZE_CONDITION = 2;
    private static final int BUST_CONDITION = 21;
    private static final int ACE_SCORE = 1;
    private static final int TEN_SCORE = 10;
    private static final int ELEVEN_SCORE = 11;

    protected String name;
    protected List<Card> cards;

    public User() {
        this.cards = new ArrayList<>();
    }

    public abstract void hit(Card card);

    public boolean isBlackJack() {
        if (this.cards.stream().anyMatch(card -> card.getScore() == ACE_SCORE) && this.cards.size() == BLACKJACK_SIZE_CONDITION) {
            return cards.stream()
                    .anyMatch(card -> card.getScore() == TEN_SCORE);
        }
        return false;
    }

    public int getScore() {
        if (this.isBlackJack()) {
            return Card.BLACKJACK_SCORE;
        }

        int score = calculateMaximumScore();
        if (score > BUST_CONDITION) {
            score = Card.BUST;
        }
        return score;
    }

    private int calculateMaximumScore() {
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

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String getName() {
        return this.name;
    }
}
