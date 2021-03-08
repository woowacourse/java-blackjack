package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class User {
    protected String name;
    protected Cards cards;

    public User() {
        this.cards = new Cards();
    }

    public void addCard(Card card) {
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
