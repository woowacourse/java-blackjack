package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    private static final int ACE_SCORE = 1;
    private static final int TEN_SCORE = 10;

    protected String name;
    protected List<Card> cards;

    public User() {
        this.cards = new ArrayList<>();
    }

    public abstract void hit(Card card);

    public abstract boolean isStay();

    public int getScore() {
        return this.cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String getName() {
        return this.name;
    }

    public boolean isBlackJack() {
        if (this.cards.stream().anyMatch(card -> card.getScore() == ACE_SCORE)) {
            return cards.stream()
                    .anyMatch(card -> card.getScore() == TEN_SCORE);
        }
        return false;
    }
}
