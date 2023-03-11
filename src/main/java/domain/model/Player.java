package domain.model;

import domain.vo.Bet;
import java.util.Objects;

public class Player extends Participant {

    private final String name;
    private final Bet bet;

    public Player(final Cards cards, final String name, final Bet bet) {
        super(cards);
        this.name = name;
        this.bet = bet;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public String getName() {
        return name;
    }

    public Bet getBet() {
        return bet;
    }
}
