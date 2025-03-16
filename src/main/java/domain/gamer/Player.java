package domain.gamer;

import domain.Betting;
import domain.card.CardGroup;
import java.util.Objects;

public class Player extends Gamer {
    private final String name;

    public Player(String name, CardGroup cardGroup) {
        super(cardGroup);
        this.name = name;
    }

    public Player(final String name, final CardGroup cardGroup, final Betting betting) {
        super(cardGroup, betting);
        this.name = name;
    }

    private Player(final Player player) {
        super(player.getCardGroup());
        this.name = player.name;
    }

    public static Player newInstance(final Player player) {
        return new Player(player);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean canReceiveCard() {
        return !(isBust() || isBlackjack());
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Player player)) {
            return false;
        }
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
