package blackjack.domain.participant;

import blackjack.domain.machine.Betting;
import java.util.Objects;

public class Player extends Participant {
    private final Betting betting;

    public Player(String name, long bettingMoney) {
        super(name);
        betting = new Betting(bettingMoney);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
