package domain.gamer;

import domain.card.CardGenerator;
import domain.card.CardGroup;
import java.util.Objects;

public class Player extends Gamer {
    private final String name;

    public Player(String name, CardGroup cardGroup, CardGenerator cardGenerator) {
        super(cardGroup, cardGenerator);
        this.name = name;
    }

    public Player(Player player) {
        super(player.cardGroup, player.cardGenerator);
        this.name = player.name;
    }

    public String getName() {
        return name;
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
