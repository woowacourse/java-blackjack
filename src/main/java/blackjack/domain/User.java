package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    private static final int BLACKJACK_SIZE_CONDITION = 2;
    private static final int TEN_SCORE = 10;
    private static final int ACE_CHECK_SCORE = 11;
    private static final int BLACKJACK_SCORE = 21;

    protected String name;
    protected List<Card> cards;

    public User() {
        this.cards = new ArrayList<>();
    }

    public abstract void hit(Card card);

    public abstract boolean isStay();

    public int getScore() {
        if (this.isBlackJack()) {
            return Card.BLACKJACK_SCORE;
        }

        int score = this.cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        score += checkAce(score);

        return checkBust(score);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String getName() {
        return this.name;
    }

    public boolean isBlackJack() {
        if (this.cards.stream().anyMatch(Card::isAce) &&
                this.cards.size() == BLACKJACK_SIZE_CONDITION) {
            return cards.stream()
                    .anyMatch(card -> card.getScore() == TEN_SCORE);
        }
        return false;
    }

    private int checkBust(int score){
        if(score > BLACKJACK_SCORE) {
            return Card.BUST;
        }
        return score;
    }

    private int checkAce(int score){
        if (this.cards.stream()
                .anyMatch(Card::isAce) && score <= ACE_CHECK_SCORE) {
            return TEN_SCORE;
        }
        return 0;
    }
}
