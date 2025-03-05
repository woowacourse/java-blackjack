package domain;

import java.util.List;
import java.util.Objects;

public class Player extends Participant{

    private final Name name;

    public Player(Name name, Cards cards) {
        super(cards);
        this.name = name;
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public Participant createParticipant(List<Card> providedCards) {
        return new Player(name, cards.addCards(providedCards));
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
