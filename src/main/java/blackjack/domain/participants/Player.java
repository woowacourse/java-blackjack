package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Objects;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name.trim();
        this.cards = cards;
    }

    public void send(Card... cards) {
        this.cards.take(cards);
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Player player = (Player) object;
        return getName().equals(player.getName()) && Objects.equals(getCards(), player.getCards());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + Objects.hashCode(getCards());
        return result;
    }
}
