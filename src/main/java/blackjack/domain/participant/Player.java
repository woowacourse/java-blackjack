package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.machine.Score;

public abstract class Player {

    protected Deck deck;
    protected String name;

    public void addCard(Card card) {
        deck.addCard(card);
    }

    public boolean isOverLimit(int limit) {
        return deck.isOverLimit(limit);
    }

    public boolean isUnderLimit(int limit) {
        return deck.isUnderLimit(limit);
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }

    public Score getScore() {
        return deck.score();
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
