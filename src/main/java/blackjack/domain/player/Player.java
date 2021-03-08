package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {
    protected final Cards cards;
    private final Name name;

    public Player(final Cards cards, final Name name) {
        this.cards = new Cards(cards.getCardsList());
        this.name = new Name(name.value());
    }

    public abstract List<Card> getInitCards();

    public List<Card> getCardsList() {
        return cards.getCardsList();
    }

    public int getScore() {
        return this.cards.getScore();
    }

    public String getName() {
        return name.value();
    }

    public void receiveMoreCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isBust();
    }
}
