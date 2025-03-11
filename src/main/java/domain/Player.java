package domain;

import java.util.List;
import java.util.Objects;

public class Player extends Participant {

    private int betAmount;

    public Player(String name, List<Card> cards, int betAmount) {
        super(name, cards);
        this.betAmount = betAmount;
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
