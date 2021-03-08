package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {

    protected final Cards cards;
    private final Name name;

    public Player(final Cards cards, final Name name) {
        this.cards = new Cards(cards.getCardsAsList());
        this.name = new Name(name.getName());
    }

    public abstract List<Card> getInitCardsAsList();

    public List<Card> getCardsAsList() {
        return cards.getCardsAsList();
    }

    public int getScore() {
        return this.cards.getScore();
    }

    public String getNameAsString() {
        return name.getName();
    }

    public void receiveMoreCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isBust();
    }
}
