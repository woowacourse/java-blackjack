package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {
    protected final Cards hand;
    private final Name name;

    public Player(final Cards cards, final Name name) {
        this.hand = new Cards(cards.getList());
        this.name = new Name(name.value());
    }

    public abstract List<Card> getInitCards();

    public void receiveMoreCard(final Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand.getList();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int getScore() {
        return this.hand.getScore();
    }

    public String getName() {
        return name.value();
    }
}
