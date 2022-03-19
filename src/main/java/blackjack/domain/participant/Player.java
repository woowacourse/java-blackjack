package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.machine.Score;

public abstract class Player {

    protected Deck cards;
    protected String name;

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public boolean isOverLimit(int limit) {
        return cards.sumPoints() > limit;
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return cards;
    }

    public Score getScore() {
        return cards.score();
    }

    public abstract boolean isDealer();

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
