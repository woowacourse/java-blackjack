package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Player {

    private final Name name;
    private final Cards cards;

    public Player(Name name) {
        this.name = name;
        this.cards = Cards.create();
    }

    public abstract boolean isHit();

    public void addCard(Card card) {
        cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }

    public int getPoint() {
        return getCards().getPoint();
    }

    public boolean isBust() {
        return getCards().isBust();
    }

    public String getName() {
        return name.getName();
    }
}
