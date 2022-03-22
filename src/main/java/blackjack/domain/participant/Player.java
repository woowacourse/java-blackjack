package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.machine.Score;

public abstract class Player {

    private static final String DEALER_NAME = "딜러";

    protected Cards cards;
    protected Name name;

    Player() {
        this(DEALER_NAME);
    }

    Player(String name) {
        this.cards = new Cards();
        this.name = new Name(name);
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public boolean isOverLimit(int limit) {
        return cards.isOverLimit(limit);
    }

    public boolean canGetMoreCard() {
        return cards.isOverLimit(limit());
    }

    abstract int limit();

    public Name getName() {
        return name;
    }

    public Cards getDeck() {
        return cards;
    }

    public Score getScore() {
        return cards.score();
    }

    public abstract boolean isDealer();

    public abstract boolean isGuest();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;

        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
