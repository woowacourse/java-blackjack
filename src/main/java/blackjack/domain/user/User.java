package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class User {
    protected Name name;
    protected Cards cards;

    public User(Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void draw(Card card) {
        this.cards.draw(card);
    }

    public int getScore() {
        return this.cards.getScore();
    }

    public boolean isBlackJack() {
        return this.cards.isBlackJack();
    }

    public boolean isBust() {
        return this.cards.isBust();
    }

    public Cards getCards() {
        return this.cards;
    }

    public String getName() {
        return this.name.getName();
    }

    abstract boolean canContinue();
}
