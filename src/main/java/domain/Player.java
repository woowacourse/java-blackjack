package domain;

import java.util.List;
import java.util.Objects;

public class Player extends Participant<Player>{

    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public Player createParticipant(List<Card> providedCards) {
        return new Player(name.getName(), cards.addCards(providedCards));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
