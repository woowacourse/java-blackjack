package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class User {
    private static final int BLACKJACK_SIZE_CONDITION = 2;
    private static final int BUST_CONDITION = 21;
    private static final int ACE_SCORE = 1;
    private static final int TEN_SCORE = 10;

    protected String name;
    protected Cards cards;

    public User() {
        this.cards = new Cards();
    }

    public void hit(Card card) {
        cards.hit(card);
    }

    public boolean isBlackJack() {
        return this.cards.isBlackJack();
    }

    public int getScore() {
        return this.cards.getScore();
    }

    public Cards getCards() {
        return this.cards;
    }

    public String getName() {
        return this.name;
    }
}
