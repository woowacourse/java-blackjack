package blackjack.domain.participant;

import java.util.Objects;

public class Player extends Participant {

    private final PlayerName name;

    public Player(final String name) {
        super();
        this.name = new PlayerName(name);
    }

    public PlayerName getName() {
        return name;
    }

    @Override
    public boolean isDrawable() {
        return hand.calculateTotalScore() < BLACK_JACK_SCORE;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
